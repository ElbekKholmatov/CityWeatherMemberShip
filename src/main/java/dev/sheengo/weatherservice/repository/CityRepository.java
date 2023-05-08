package dev.sheengo.weatherservice.repository;

import com.google.common.io.Files;
import dev.sheengo.weatherservice.domains.AuthUser;
import dev.sheengo.weatherservice.domains.City;
import dev.sheengo.weatherservice.dto.WeatherInfoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {
    @Query("SELECT c FROM City c WHERE c.name = ?1")
    Optional<City> findByName(String cityName);

    @Query("SELECT c FROM City c WHERE c.name IN ?1")
    List<City> findAllByNameIn(List<String> cities);

    @Query("SELECT c FROM City c WHERE ?1 MEMBER OF c.subscriptions")
    List<City> findCitiesByUser(AuthUser authUser);

    @Query("SELECT c FROM City c WHERE ?1 MEMBER OF c.subscriptions")
    Page<City> findCitiesWeatherByUser(AuthUser authUser, Pageable pageable);

    @Query("SELECT c FROM City c WHERE ?1 MEMBER OF c.subscriptions")
    Page<City> findUserAndTheirSubscriptions(AuthUser authUser, Pageable pageable);
    @Query("SELECT c FROM City c WHERE ?1 MEMBER OF c.subscriptions")
    Page<City> findAllUsersSubscriptions(Pageable pageable);
}