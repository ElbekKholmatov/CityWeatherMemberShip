package dev.sheengo.weatherservice.repository;

import dev.sheengo.weatherservice.domains.Weather;
import dev.sheengo.weatherservice.dto.WeatherInfoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherRepository extends JpaRepository<Weather, Long> {

}