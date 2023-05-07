package dev.sheengo.weatherservice.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AuthUser extends Auditable {
    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    @JsonIgnore
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private ROLES role;

    private boolean active;

    public enum ROLES{
        USER,ADMIN
    }

    @Builder(builderMethodName = "childBuilder")

    public AuthUser(Long id, LocalDateTime createdAt, LocalDateTime updateAt, Long createdBy, Long updatedBy, boolean deleted, STATE state, String username, String password, String email, ROLES role, boolean active) {
        super(id, createdAt, updateAt, createdBy, updatedBy, deleted, state);
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.active = active;

    }
}
