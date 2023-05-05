package dev.sheengo.weatherservice.service;


import dev.sheengo.weatherservice.domains.AuthUser;
import dev.sheengo.weatherservice.dto.auth.AuthUserCreateDTO;
import dev.sheengo.weatherservice.dto.auth.RefreshTokenRequest;
import dev.sheengo.weatherservice.dto.auth.TokenRequest;
import dev.sheengo.weatherservice.dto.auth.TokenResponse;
import org.springframework.lang.NonNull;

public interface AuthService {

    TokenResponse generateToken(@NonNull TokenRequest tokenRequest);
    AuthUser create(@NonNull AuthUserCreateDTO dto);
    TokenResponse refreshToken(@NonNull RefreshTokenRequest refreshTokenRequest);

}
