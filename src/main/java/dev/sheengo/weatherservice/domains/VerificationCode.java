package dev.sheengo.weatherservice.domains;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "verification_code")
public class VerificationCode extends Auditable {

    private String code;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "timestamp default now()+interval '2 minutes'")
    private Date expiryDate;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Builder(builderMethodName = "childBuilder")

    public VerificationCode(Long id, LocalDateTime createdAt, LocalDateTime updateAt, Long createdBy, Long updatedBy, boolean deleted, STATE state, String code, Date expiryDate, String username, String email) {
        super(id, createdAt, updateAt, createdBy, updatedBy, deleted, state);
        this.code = code;
        this.expiryDate = expiryDate;
        this.username = username;
        this.email = email;
    }
}
