package dev.sheengo.weatherservice.controller;

import dev.sheengo.weatherservice.domains.AuthUser;
import dev.sheengo.weatherservice.dto.AuthUserUpdateDTO;
import dev.sheengo.weatherservice.dto.auth.AuthUserCreateDTO;
import dev.sheengo.weatherservice.dto.auth.RefreshTokenRequest;
import dev.sheengo.weatherservice.dto.auth.TokenRequest;
import dev.sheengo.weatherservice.dto.auth.TokenResponse;
import dev.sheengo.weatherservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/access/token")
    public ResponseEntity<TokenResponse> generateToken(@RequestBody TokenRequest tokenRequest) {
        return ResponseEntity.ok(authService.generateToken(tokenRequest));
    }

    @PostMapping("/refresh/token")
    public ResponseEntity<TokenResponse> refreshToken(
            @RequestBody RefreshTokenRequest refreshTokenRequest
    ) {
        return ResponseEntity.ok(authService.refreshToken(refreshTokenRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthUser> register(@NonNull @Valid @RequestBody AuthUserCreateDTO dto) {
        return ResponseEntity.ok(authService.create(dto));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
    @PutMapping("/update")
    public ResponseEntity<AuthUser> update(@NonNull @Valid @RequestBody AuthUserUpdateDTO dto) {
        return ResponseEntity.ok(authService.update(dto));
    }

}
