package dev.sheengo.weatherservice.controller;

import dev.sheengo.weatherservice.dto.UpdateWeatherDTO;
import dev.sheengo.weatherservice.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.Update;
import org.mapstruct.ap.internal.model.assignment.UpdateWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/weather")
public class WeatherController {
    private final WeatherService weatherService;

    @PutMapping("/updateWeather")
    public ResponseEntity<?> updateCityWeather(String city, UpdateWeatherDTO updateWeatherDTO){
        weatherService.updateWeatherInGivenCity(city, updateWeatherDTO);
        return ResponseEntity.status(204).build();
    }
}
