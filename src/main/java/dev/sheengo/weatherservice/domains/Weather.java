package dev.sheengo.weatherservice.domains;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Weather extends Auditable {
    private LocalDate date;
    private Double temperatureMax;
    private Double temperatureAvg;
    private Double temperatureMin;
    private Double airDensity;
    private Double gust;
    private Double windSpeed;
    private Short windDirection;
    @OneToOne
    private Location location;

    @Builder(builderMethodName = "childBuilder")

    public Weather(Long id, LocalDateTime createdAt, LocalDateTime updateAt, Long createdBy, Long updatedBy, boolean deleted, STATE state, LocalDate date, Double temperatureMax, Double temperatureAvg, Double temperatureMin, Double airDensity, Double gust, Double windSpeed, Short windDirection, Location location) {
        super(id, createdAt, updateAt, createdBy, updatedBy, deleted, state);
        this.date = date;
        this.temperatureMax = temperatureMax;
        this.temperatureAvg = temperatureAvg;
        this.temperatureMin = temperatureMin;
        this.airDensity = airDensity;
        this.gust = gust;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.location = location;
    }
}
