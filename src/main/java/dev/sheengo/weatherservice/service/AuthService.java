package dev.sheengo.weatherservice.service;


import dev.sheengo.weatherservice.domains.AuthUser;
import dev.sheengo.weatherservice.dto.ActivateUserDTO;
import dev.sheengo.weatherservice.dto.AuthUserUpdateDTO;
import dev.sheengo.weatherservice.dto.ResendActivationDTO;
import dev.sheengo.weatherservice.dto.auth.*;
import org.springframework.lang.NonNull;

public interface AuthService {

    TokenResponse generateToken(@NonNull TokenRequest tokenRequest);
    AuthUser create(@NonNull AuthUserCreateDTO dto);
    TokenResponse refreshToken(@NonNull RefreshTokenRequest refreshTokenRequest);
    AuthUser update(@NonNull AuthUserUpdateDTO dto);

    AuthUser resetPassword(@NonNull ResetPasswordDTO dto);

    String resendActivation(@NonNull ResendActivationDTO dto);

    void activate(ActivateUserDTO activationCode);

    String getActivationCode(String username);
}
