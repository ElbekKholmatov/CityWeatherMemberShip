package dev.sheengo.weatherservice.service;

import dev.sheengo.weatherservice.criteria.CityCreateCriteria;
import dev.sheengo.weatherservice.domains.City;
import dev.sheengo.weatherservice.domains.Location;
import dev.sheengo.weatherservice.domains.Weather;
import dev.sheengo.weatherservice.dto.CityCreateDto;
import dev.sheengo.weatherservice.dto.CityUpdateDto;
import dev.sheengo.weatherservice.repository.CityRepository;
import dev.sheengo.weatherservice.repository.WeatherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository cityRepository;
    private final LocationService locationService;
    private final WeatherService weatherService;
    private final WeatherRepository weatherRepository;

    public City save(CityCreateCriteria dto) {
        Location location = locationService.save(Location.childBuilder()
                .latitude(dto.getLat())
                .longitude(dto.getLon())
                .build());

        Weather weather = weatherService.save(
                Weather.childBuilder()
                        .location(location)
                        .build());

        City city = City.childBuilder()
                .name(dto.getName())
                .location(location)
                .weather(weather)
                .build();

        return cityRepository.save(city);
    }

    public Page<City> getCitiesPagination(Pageable pageable) {
        return cityRepository.findAll(pageable);
    }

    public Optional<City> getCityByName(String cityName) {
        return cityRepository.findByName(cityName);
    }

    public Optional<City> getCityById(Long id) {
        return cityRepository.findById(id);
    }

    public City update(CityUpdateDto dto) {
        City city = cityRepository.findById(dto.getId()).orElseThrow(
                () -> new RuntimeException("City not found")
        );
        if (Objects.nonNull(dto.getName())) {
            city.setName(dto.getName());
        }
        // Update city location if dto location is not null
        city.setLocation(
                locationService.save(
                        Location.childBuilder()
                                .latitude(dto.getLat())
                                .longitude(dto.getLon())
                                .build()
                )
        );
        // Update city weather if dto weather is not null
        Weather weather = city.getWeather();

        weather.setDate(LocalDate.now());
        weather.setTemperatureMax(dto.getTempMAX());
        weather.setTemperatureMin(dto.getTempMIN());
        weather.setTemperatureAvg(dto.getTempAVG());

        weather.setAirDensity(dto.getAirDensity());

        weather.setGust(dto.getGust());

        weather.setWindSpeed(dto.getWindSpeed());
        weather.setWindDirection(dto.getWindDirection());

        city.setWeather(weatherService.save(weather));

        return cityRepository.save(city);

    }

    public void delete(Long id) {
        City city = cityRepository.findById(id).orElseThrow(
                () -> new RuntimeException("City not found")
        );
        city.setDeleted(true);
        cityRepository.save(city);
    }
}
