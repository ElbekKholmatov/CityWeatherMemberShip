package dev.sheengo.weatherservice.service;


import dev.sheengo.weatherservice.config.security.JwtTokenUtil;
import dev.sheengo.weatherservice.domains.AuthUser;
import dev.sheengo.weatherservice.dto.auth.AuthUserCreateDTO;
import dev.sheengo.weatherservice.dto.auth.RefreshTokenRequest;
import dev.sheengo.weatherservice.dto.auth.TokenRequest;
import dev.sheengo.weatherservice.dto.auth.TokenResponse;
import dev.sheengo.weatherservice.repository.AuthUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

import static dev.sheengo.weatherservice.enums.TokenType.REFRESH;
import static dev.sheengo.weatherservice.mapper.AuthUserMapper.AUTH_USER_MAPPER;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final AuthUserRepository authUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;

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
    public AuthUser create(@NotNull AuthUserCreateDTO dto) {
        AuthUser authUser = AUTH_USER_MAPPER.toEntity(dto);
        authUser.setPassword(passwordEncoder.encode(dto.getPassword()));
        authUser.setRole(AuthUser.ROLES.USER);
       return authUserRepository.save(authUser);
    }

    @Override
    public TokenResponse refreshToken(@NotNull RefreshTokenRequest refreshTokenRequest) {
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
}
