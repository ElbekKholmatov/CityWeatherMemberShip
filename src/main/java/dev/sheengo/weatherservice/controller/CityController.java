package dev.sheengo.weatherservice.controller;

import dev.sheengo.weatherservice.criteria.CityCreateCriteria;
import dev.sheengo.weatherservice.criteria.CityShowCriteria;
import dev.sheengo.weatherservice.domains.City;
import dev.sheengo.weatherservice.dto.CityUpdateDto;
import dev.sheengo.weatherservice.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/city")
public class CityController {
    private final CityService cityService;

    @GetMapping("/all")
    public Page<City> getAllCities(
//            @RequestBody CityShowCriteria dto,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "page", defaultValue = "0") int page
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return cityService.getCitiesPagination(pageable);
    }
    @GetMapping("/name")
    public ResponseEntity<City> getCityByName(
            @RequestBody String cityName
    ) {
        return ResponseEntity.of(cityService.getCityByName(cityName));
    }
    @GetMapping("/id")
    public ResponseEntity<City> getCityById(
            @RequestBody Long id
    ) {
        return ResponseEntity.of(cityService.getCityById(id));
    }
    @PostMapping("/create")
    public ResponseEntity<City> addCity(
            @Valid @RequestBody CityCreateCriteria dto
    ) {
        return ResponseEntity.ok(cityService.save(dto));
    }

    @PutMapping("/update")
    public ResponseEntity<City> updateCity(
            @RequestBody CityUpdateDto dto
    ) {
        return ResponseEntity.ok(cityService.update(dto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteCity(
            @RequestParam Long id
    ) {
         cityService.delete(id);
         return ResponseEntity.ok().build();
    }

}
