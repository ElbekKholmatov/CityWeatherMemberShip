package dev.sheengo.weatherservice.service;

import dev.sheengo.weatherservice.config.security.SessionUser;
import dev.sheengo.weatherservice.domains.AuthUser;
import dev.sheengo.weatherservice.domains.City;
import dev.sheengo.weatherservice.dto.MemberShipCitiesDTO;
import dev.sheengo.weatherservice.repository.AuthUserRepository;
import dev.sheengo.weatherservice.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final CityService cityService;
    private final SessionUser sessionUser;
    private final AuthServiceImpl authService;
    private final AuthUserRepository authUserRepository;
    private final CityRepository cityRepository;


    public void getMemberShip(MemberShipCitiesDTO cities) {
        AuthUser authUser = authUserRepository.findById(sessionUser.id()).orElseThrow(() -> new IllegalArgumentException("Authorization Error"));
        List<City> allByNames = cityService.findAllByNames(cities.getCities());
        allByNames.forEach(city -> {
            city.getSubscriptions().add(authUser);
            cityRepository.save(city);
        });
    }

    public void cancelMemberShip(MemberShipCitiesDTO cities) {
        AuthUser authUser = authUserRepository.findById(sessionUser.id()).orElseThrow(() -> new IllegalArgumentException("Authorization Error"));
        List<City> allByNames = cityService.findAllByNames(cities.getCities());
        allByNames.forEach(city -> {
            city.getSubscriptions().remove(authUser);
            cityRepository.save(city);
        });
    }
}
