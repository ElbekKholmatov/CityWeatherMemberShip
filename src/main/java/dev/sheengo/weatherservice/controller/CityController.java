package dev.sheengo.weatherservice.controller;

import dev.sheengo.weatherservice.criteria.CityCreateCriteria;
import dev.sheengo.weatherservice.criteria.CityShowCriteria;
import dev.sheengo.weatherservice.domains.City;
import dev.sheengo.weatherservice.dto.CityIdDTO;
import dev.sheengo.weatherservice.dto.CityUpdateDto;
import dev.sheengo.weatherservice.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.validation.Valid;


@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/city")
public class CityController {

    private final CityService cityService;

    @GetMapping("/all/{size}/{page}")
    public Page<City> getAllCities(
            @PathVariable Integer size,
            @PathVariable Integer page
    ) {
        if (page < 0 || size < 0) {
            throw new IllegalArgumentException("Page and size must be positive");
        }
        Pageable pageable = PageRequest.of(page, size);
        return cityService.getCitiesPagination(pageable);
    }

    @GetMapping("/{cityName}")
    public ResponseEntity<City> getCityByName(
            @PathVariable String cityName
    ) {
        return ResponseEntity.of(cityService.getCityByName(cityName));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<City> getCityById(
            @PathVariable Long id
    ) {
        return ResponseEntity.of(cityService.getCityById(id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<City> addCity(
            @Valid @RequestBody CityCreateCriteria dto
    ) {
        return ResponseEntity.ok(cityService.save(dto));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
    @PutMapping("/update")
    public ResponseEntity<City> updateCity(
            CityUpdateDto dto
    ) {
        return ResponseEntity.ok(cityService.update(dto));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCity(
             @PathVariable Long id
    ) {
         cityService.delete(id);
         return ResponseEntity.ok().build();
    }

}
