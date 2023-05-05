package dev.sheengo.weatherservice.dto.auth;

import dev.sheengo.weatherservice.domains.AuthUser;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class TokenRequest{
    @NotBlank String username;
    @NotBlank String password;
}
