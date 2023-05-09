package dev.sheengo.weatherservice.service;


import dev.sheengo.weatherservice.config.security.JwtTokenUtil;
import dev.sheengo.weatherservice.config.security.SessionUser;
import dev.sheengo.weatherservice.domains.AuthUser;
import dev.sheengo.weatherservice.domains.VerificationCode;
import dev.sheengo.weatherservice.dto.ActivateUserDTO;
import dev.sheengo.weatherservice.dto.AuthUserUpdateDTO;
import dev.sheengo.weatherservice.dto.ResendActivationDTO;
import dev.sheengo.weatherservice.dto.auth.*;
import dev.sheengo.weatherservice.repository.AuthUserRepository;
import dev.sheengo.weatherservice.repository.VerificationCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static dev.sheengo.weatherservice.enums.TokenType.REFRESH;
import static dev.sheengo.weatherservice.mapper.AuthUserMapper.AUTH_USER_MAPPER;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final AuthUserRepository authUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final SessionUser sessionUser;
    private final VerificationCodeRepository verificationCodeService;

    @Override
    public TokenResponse generateToken(@NonNull TokenRequest tokenRequest) {
        String username = tokenRequest.getUsername();
        String password = tokenRequest.getPassword();
        AuthUser.ROLES role = authUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"))
                .getRole();

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(username, password);
        authenticationManager.authenticate(authentication);
        return jwtTokenUtil.generateToken(username,role);
    }

    @Override
    public AuthUser create(@NonNull @NotNull AuthUserCreateDTO dto) {
        AuthUser authUser = AUTH_USER_MAPPER.toEntity(dto);
        authUser.setPassword(passwordEncoder.encode(dto.getPassword()));
        authUser.setRole(AuthUser.ROLES.USER);
       return authUserRepository.save(authUser);
    }

    @Override
    public TokenResponse refreshToken(@NonNull @NotNull RefreshTokenRequest refreshTokenRequest) {
        String refreshToken = refreshTokenRequest.getRefreshToken();
        if (!jwtTokenUtil.isValid(refreshToken, REFRESH))
            throw new CredentialsExpiredException("Token is invalid");
        String username = jwtTokenUtil.getUsername(refreshToken, REFRESH);
        AuthUser.ROLES role = jwtTokenUtil.getRole(refreshToken, REFRESH);
        authUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found "));

        TokenResponse tokenResponse = TokenResponse.builder()
                .refreshToken(refreshToken)
                .refreshTokenExpiry(jwtTokenUtil.getExpiry(refreshToken, REFRESH))
                .build();
        return jwtTokenUtil.generateAccessToken(username, role,tokenResponse);
    }

    public AuthUser getAuthUser() {
        return authUserRepository.findById(sessionUser.id())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public AuthUser update(@NonNull AuthUserUpdateDTO dto) {
        AuthUser authUser = AUTH_USER_MAPPER.updateEntity(dto);
        authUserRepository.save(authUser);
        return authUser;
    }

    @Override
    public AuthUser resetPassword(@NonNull ResetPasswordDTO dto) {
        AuthUser authUser = authUserRepository.findById(sessionUser.id())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if (!dto.getNewPassword().equals(dto.getConfirmNewPassword()))
            throw new CredentialsExpiredException("Password is invalid");
        authUser.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        authUserRepository.save(authUser);
        return authUser;
    }

    @Override
    public String resendActivation(@NonNull ResendActivationDTO dto) {
        authUserRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        try {
            List<VerificationCode> allByEmailAndDeleted = verificationCodeService.findAllByEmailAndDeleted(dto.getEmail());
            allByEmailAndDeleted.forEach(verificationCode -> verificationCode.setDeleted(true));
            return otp(dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void activate(ActivateUserDTO activationCode) {
        VerificationCode verificationCode = verificationCodeService.findByCode(activationCode.getActivationCode())
                .orElseThrow(() -> new UsernameNotFoundException("Activation code not found"));
        if (verificationCode.getExpiryDate().after(new Date()))
            throw new CredentialsExpiredException("Activation code is invalid");
        AuthUser authUser = authUserRepository.findByUsername(verificationCode.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        authUser.setActive(true);
        authUserRepository.save(authUser);
        verificationCode.setDeleted(true);
        verificationCodeService.save(verificationCode);
    }

    @Override
    public String getActivationCode(String username) {
        AuthUser authUser = authUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        List<VerificationCode> allByEmail = verificationCodeService.findAllByEmail(authUser.getEmail());
        if (!allByEmail.isEmpty())
            throw new CredentialsExpiredException(
                    "Activation code already sent." +
                            "If you don't get your code use resend Activation API");

        return otp(new ResendActivationDTO(authUser.getUsername(),authUser.getEmail()));
    }

    private String otp(ResendActivationDTO dto) {
        String otp = String.valueOf(new Random().nextInt(999999));
        verificationCode(otp, dto.getEmail(), dto.getUsername());
        return otp;
    }

    private void verificationCode(String otp, String email, String username) {
        //expiry date from now + 30 minutes
        VerificationCode verificationCode = VerificationCode.childBuilder()
                .code(otp)
                .expiryDate(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(30)))
                .username(username)
                .email(email)
                .build();

        verificationCodeService.save(verificationCode);
    }

}
