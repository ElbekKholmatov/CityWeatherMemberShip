package dev.sheengo.weatherservice.dto;

import lombok.Data;

@Data
public class ActivateUserDTO {
    String activationCode;
    String username;
}
