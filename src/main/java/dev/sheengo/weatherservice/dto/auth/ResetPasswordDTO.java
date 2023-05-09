package dev.sheengo.weatherservice.dto.auth;

import lombok.Data;
import org.springframework.lang.NonNull;

import javax.validation.constraints.Pattern;

@Data
public class ResetPasswordDTO{
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$", message = "Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter and one number")
    String newPassword;
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$", message = "Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter and one number")
    String confirmNewPassword;

}
