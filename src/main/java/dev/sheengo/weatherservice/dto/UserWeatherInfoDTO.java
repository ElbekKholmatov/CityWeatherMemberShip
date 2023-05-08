package dev.sheengo.weatherservice.dto;

import dev.sheengo.weatherservice.domains.City;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class UserWeatherInfoDTO {
    String username;
    String cityName;

    public UserWeatherInfoDTO(City city) {
        this.cityName = city.getName();
    }
}
