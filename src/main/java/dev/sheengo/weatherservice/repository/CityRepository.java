package dev.sheengo.weatherservice.repository;

import dev.sheengo.weatherservice.domains.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {
    @Query("SELECT c FROM City c WHERE c.name = ?1")
    Optional<City> findByName(String cityName);

    @Query("SELECT c FROM City c WHERE c.name IN ?1")
    List<City> findAllByNameIn(List<String> cities);
}