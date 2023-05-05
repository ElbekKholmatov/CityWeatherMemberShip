package dev.sheengo.weatherservice.repository;

import dev.sheengo.weatherservice.domains.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}