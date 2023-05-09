package dev.sheengo.weatherservice.controller;

import dev.sheengo.weatherservice.domains.AuthUser;
import dev.sheengo.weatherservice.dto.ActivateUserDTO;
import dev.sheengo.weatherservice.dto.AuthUserUpdateDTO;
import dev.sheengo.weatherservice.dto.ResendActivationDTO;
import dev.sheengo.weatherservice.dto.auth.*;
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

    @PutMapping("/reset/password")
    public ResponseEntity<AuthUser> resetPassword(@NonNull @Valid @RequestBody ResetPasswordDTO dto) {
        return ResponseEntity.ok(authService.resetPassword(dto));
    }

    @PutMapping("/resend/activation")
    public ResponseEntity<String> resendActivation(@NonNull @Valid @RequestBody ResendActivationDTO dto) {
        return ResponseEntity.ok(authService.resendActivation(dto));
    }

    @PostMapping("/activate")
    public ResponseEntity<String> activate(@NonNull @Valid @RequestBody ActivateUserDTO activationCode) {
        authService.activate(activationCode);
        return ResponseEntity.ok("User activated successfully");
    }

    @GetMapping("/get/activation/code")
    public ResponseEntity<String> getActivationCode(@NonNull String username) {
        return ResponseEntity.ok(authService.getActivationCode(username));
    }


}
