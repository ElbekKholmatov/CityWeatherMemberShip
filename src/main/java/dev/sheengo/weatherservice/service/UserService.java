package dev.sheengo.weatherservice.service;

import dev.sheengo.weatherservice.config.security.SessionUser;
import dev.sheengo.weatherservice.domains.AuthUser;
import dev.sheengo.weatherservice.domains.City;
import dev.sheengo.weatherservice.dto.MemberShipCitiesDTO;
import dev.sheengo.weatherservice.dto.UserWeatherInfoDTO;
import dev.sheengo.weatherservice.dto.WeatherInfoDTO;
import dev.sheengo.weatherservice.exceptions.NotFoundException;
import dev.sheengo.weatherservice.repository.AuthUserRepository;
import dev.sheengo.weatherservice.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final CityService cityService;
    private final SessionUser sessionUser;
    private final AuthUserRepository authUserRepository;


    public void getMemberShips(MemberShipCitiesDTO cities) {
        AuthUser authUser = authUserRepository.findById(sessionUser.id()).orElseThrow(() -> new IllegalArgumentException("Authorization Error"));
        cities.getCities().forEach(
                this::getMemberShip
        );
    }

    public void cancelMemberShips(MemberShipCitiesDTO cities) {
        AuthUser authUser = authUserRepository.findById(sessionUser.id()).orElseThrow(() -> new IllegalArgumentException("Authorization Error"));
        cities.getCities().forEach(
                this::cancelMemberShip
        );
    }

    public void getMemberShip(String cityName) {
        AuthUser authUser = authUserRepository.findById(sessionUser.id()).orElseThrow(() -> new IllegalArgumentException("Authorization Error"));
        City city = cityService.findByName(cityName).orElseThrow(() -> new NotFoundException("City Not Found"));
        city.getSubscriptions().add(authUser);
        cityService.saveM(city);
    }

    public void cancelMemberShip(String cityName) {
        AuthUser authUser = authUserRepository.findById(sessionUser.id()).orElseThrow(() -> new IllegalArgumentException("Authorization Error"));
        City city = cityService.findByName(cityName).orElseThrow(() -> new NotFoundException("City Not Found"));
        city.getSubscriptions().remove(authUser);
        cityService.saveM(city);
    }


    public Page<WeatherInfoDTO> getInfoAboutMyMemberShipCitiesWeather(Pageable pageable) {
        return cityService.getCitiesWeatherBySubscription(pageable);
    }

    public Page<City> getCitiesList(Pageable pageable) {
        return cityService.getCitiesPagination(pageable);
    }

    public Page<UserWeatherInfoDTO> getUserAndTheirSubscriptions(Pageable pageable,String username) {
        AuthUser authUser = authUserRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("User Not Found"));
        return cityService.getUserAndTheirSubscriptions(pageable,authUser);
    }

    public Page<AuthUser> getUsersList(Pageable pageable) {
        return authUserRepository.findAll(pageable);
    }

}
