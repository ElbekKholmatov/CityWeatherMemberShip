package dev.sheengo.weatherservice.repository;

import dev.sheengo.weatherservice.domains.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherRepository extends JpaRepository<Weather, Long> {
}