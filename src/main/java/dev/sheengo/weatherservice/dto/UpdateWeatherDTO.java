package dev.sheengo.weatherservice.dto;

import lombok.Data;


@Data
public class UpdateWeatherDTO {
    private Double temperatureMax;
    private Double temperatureAvg;
    private Double temperatureMin;
    private Double airDensity;
    private Double gust;
    private Double windSpeed;
    private Short windDirection;
}
