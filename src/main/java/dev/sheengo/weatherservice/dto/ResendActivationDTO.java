package dev.sheengo.weatherservice.dto;

import lombok.Data;

@Data
public class ResendActivationDTO {
    String username;
    String email;

    public ResendActivationDTO(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
