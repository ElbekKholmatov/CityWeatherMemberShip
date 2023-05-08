package dev.sheengo.weatherservice.dto;

import dev.sheengo.weatherservice.domains.City;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
public class WeatherInfoDTO {
    private final String cityName;
    private final LocalDate date;
    private final Double temperatureMax;
    private final Double temperatureAvg;
    private final Double temperatureMin;
    private final Double airDensity;
    private final Double gust;
    private final Double windSpeed;
    private final Short windDirection;

    public WeatherInfoDTO(City city) {
        this.cityName = city.getName();
        this.date = city.getWeather().getDate();
        this.temperatureMax = city.getWeather().getTemperatureMax();
        this.temperatureAvg = city.getWeather().getTemperatureAvg();
        this.temperatureMin = city.getWeather().getTemperatureMin();
        this.airDensity = city.getWeather().getAirDensity();
        this.gust = city.getWeather().getGust();
        this.windSpeed = city.getWeather().getWindSpeed();
        this.windDirection = city.getWeather().getWindDirection();
    }
}
