package dev.sheengo.weatherservice.domains;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Location extends Auditable{
    @Column(nullable = false)
    private Double longitude;
    @Column(nullable = false)
    private Double latitude;



    @Builder(builderMethodName = "childBuilder")

    public Location(Long id, LocalDateTime createdAt, LocalDateTime updateAt, Long createdBy, Long updatedBy, boolean deleted, STATE state, Double longitude, Double latitude) {
        super(id, createdAt, updateAt, createdBy, updatedBy, deleted, state);
        this.longitude = longitude;
        this.latitude = latitude;
    }
}