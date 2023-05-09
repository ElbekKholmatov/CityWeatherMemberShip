package dev.sheengo.weatherservice.controller;

import dev.sheengo.weatherservice.domains.AuthUser;
import dev.sheengo.weatherservice.domains.City;
import dev.sheengo.weatherservice.dto.MemberShipCitiesDTO;
import dev.sheengo.weatherservice.dto.UserWeatherInfoDTO;
import dev.sheengo.weatherservice.dto.WeatherInfoDTO;
import dev.sheengo.weatherservice.service.AuthService;
import dev.sheengo.weatherservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.units.qual.C;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/getMemberShips")
    public ResponseEntity<Void> getMemberShips(
            MemberShipCitiesDTO cities
    ) {
        userService.getMemberShips(cities);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/cancelMemberShips")
    public ResponseEntity<Void> deleteMemberShips(
            MemberShipCitiesDTO cities
    ) {
        userService.cancelMemberShips(cities);
        return ResponseEntity.status(204).build();
    }
    @PostMapping("/getMemberShip")
    public ResponseEntity<?> getMemberShip(String city) {
            userService.getMemberShip(city);
            return ResponseEntity.ok().build();
    }

    @DeleteMapping("/cancelMemberShip")
    public ResponseEntity<?> deleteMemberShip(
            String city
    ) {
        userService.cancelMemberShip(city);
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/getInfoAboutMyMemberShipCitiesWeather/{page}/{size}")
    public Page<WeatherInfoDTO> getInfoAboutMyMemberShipCitiesWeather(
            @PathVariable Integer page,
            @PathVariable Integer size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return userService.getInfoAboutMyMemberShipCitiesWeather(pageable);
    }

    @GetMapping("/getCitiesList/{page}/{size}")
    public Page<City> getCitiesList(
            @PathVariable Integer page,
            @PathVariable Integer size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return userService.getCitiesList(pageable);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
    @GetMapping("/getUserAndTheirSubscriptions/{page}/{size}")
    public Page<UserWeatherInfoDTO> getUserAndTheirSubscriptions(
            @PathVariable Integer page,
            @PathVariable Integer size,
            String username
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return userService.getUserAndTheirSubscriptions(pageable,username);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
    @GetMapping("/getUsersList/{page}/{size}")
    public Page<AuthUser> getUsersList(
            @PathVariable Integer page,
            @PathVariable Integer size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return userService.getUsersList(pageable);
    }
}
