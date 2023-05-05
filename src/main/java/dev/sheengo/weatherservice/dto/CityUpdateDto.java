package dev.sheengo.weatherservice.dto;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;
import org.springframework.lang.NonNull;

@Data
public class CityUpdateDto {
    @Parameter(description = "City id", required = true)
    private Long id;
    @Parameter(description = "City name")
    private String name;

    @Parameter(description = "City latitude")
    private double lat;
    @Parameter(description = "City longitude")
    private double lon;

    @Parameter(description = "City temperature max")
    private double tempMAX;
    @Parameter(description = "City temperature min")
    private double tempMIN;
    @Parameter(description = "City temperature avg")
    private double tempAVG;
    @Parameter(description = "City air density")
    private double airDensity;
    @Parameter(description = "City gust")
    private double gust;
    @Parameter(description = "City wind speed")
    private double windSpeed;
    @Parameter(description = "City wind direction")
    private short windDirection;
}
