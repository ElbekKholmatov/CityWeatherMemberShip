package dev.sheengo.weatherservice.dto.auth;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class RefreshTokenRequest{
        @NotBlank String refreshToken;
}
