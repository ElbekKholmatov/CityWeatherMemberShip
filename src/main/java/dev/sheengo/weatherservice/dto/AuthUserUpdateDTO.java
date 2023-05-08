package dev.sheengo.weatherservice.dto;

import lombok.Data;

@Data
public class AuthUserUpdateDTO {
    private Long id;
    private String username;
    private String email;
}
