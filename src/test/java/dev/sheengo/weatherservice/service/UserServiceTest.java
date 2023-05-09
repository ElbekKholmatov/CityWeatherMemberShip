package dev.sheengo.weatherservice.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.sheengo.weatherservice.config.security.SessionUser;
import dev.sheengo.weatherservice.domains.Auditable;
import dev.sheengo.weatherservice.domains.AuthUser;
import dev.sheengo.weatherservice.domains.City;
import dev.sheengo.weatherservice.domains.Location;
import dev.sheengo.weatherservice.domains.Weather;
import dev.sheengo.weatherservice.dto.MemberShipCitiesDTO;
import dev.sheengo.weatherservice.dto.UserWeatherInfoDTO;
import dev.sheengo.weatherservice.dto.WeatherInfoDTO;
import dev.sheengo.weatherservice.exceptions.NotFoundException;
import dev.sheengo.weatherservice.repository.AuthUserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserService.class})
@ExtendWith(SpringExtension.class)
class UserServiceTest {
    @MockBean
    private AuthUserRepository authUserRepository;

    @MockBean
    private CityService cityService;

    @MockBean
    private SessionUser sessionUser;

    @Autowired
    private UserService userService;

    /**
     * Method under test: {@link UserService#getMemberShips(MemberShipCitiesDTO)}
     */
    @Test
    void testGetMemberShips() {
        when(sessionUser.id()).thenReturn(1L);

        AuthUser authUser = new AuthUser();
        authUser.setActive(true);
        authUser.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        authUser.setCreatedBy(1L);
        authUser.setDeleted(true);
        authUser.setEmail("jane.doe@example.org");
        authUser.setId(1L);
        authUser.setPassword("iloveyou");
        authUser.setRole(AuthUser.ROLES.USER);
        authUser.setState(Auditable.STATE.ACTIVE);
        authUser.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        authUser.setUpdatedBy(1L);
        authUser.setUsername("janedoe");
        Optional<AuthUser> ofResult = Optional.of(authUser);
        when(authUserRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        MemberShipCitiesDTO cities = new MemberShipCitiesDTO();
        cities.setCities(new ArrayList<>());
        userService.getMemberShips(cities);
        verify(sessionUser).id();
        verify(authUserRepository).findById(Mockito.<Long>any());
        assertTrue(cities.getCities().isEmpty());
    }

    /**
     * Method under test: {@link UserService#getMemberShips(MemberShipCitiesDTO)}
     */
    @Test
    void testGetMemberShips2() {
        when(sessionUser.id()).thenReturn(1L);
        when(authUserRepository.findById(Mockito.<Long>any())).thenThrow(new NotFoundException("An error occurred"));

        MemberShipCitiesDTO cities = new MemberShipCitiesDTO();
        cities.setCities(new ArrayList<>());
        assertThrows(NotFoundException.class, () -> userService.getMemberShips(cities));
        verify(sessionUser).id();
        verify(authUserRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link UserService#getMemberShips(MemberShipCitiesDTO)}
     */
    @Test
    void testGetMemberShips3() {
        when(sessionUser.id()).thenReturn(1L);
        when(authUserRepository.findById(Mockito.<Long>any())).thenReturn(Optional.empty());

        MemberShipCitiesDTO cities = new MemberShipCitiesDTO();
        cities.setCities(new ArrayList<>());
        assertThrows(IllegalArgumentException.class, () -> userService.getMemberShips(cities));
        verify(sessionUser).id();
        verify(authUserRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link UserService#getMemberShips(MemberShipCitiesDTO)}
     */
    @Test
    void testGetMemberShips4() {
        Location location = new Location();
        location.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location.setCreatedBy(1L);
        location.setDeleted(true);
        location.setId(1L);
        location.setLatitude(10.0d);
        location.setLongitude(10.0d);
        location.setState(Auditable.STATE.ACTIVE);
        location.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location.setUpdatedBy(1L);

        Location location2 = new Location();
        location2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location2.setCreatedBy(1L);
        location2.setDeleted(true);
        location2.setId(1L);
        location2.setLatitude(10.0d);
        location2.setLongitude(10.0d);
        location2.setState(Auditable.STATE.ACTIVE);
        location2.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location2.setUpdatedBy(1L);

        Weather weather = new Weather();
        weather.setAirDensity(10.0d);
        weather.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        weather.setCreatedBy(1L);
        weather.setDate(LocalDate.of(1970, 1, 1));
        weather.setDeleted(true);
        weather.setGust(10.0d);
        weather.setId(1L);
        weather.setLocation(location2);
        weather.setState(Auditable.STATE.ACTIVE);
        weather.setTemperatureAvg(10.0d);
        weather.setTemperatureMax(10.0d);
        weather.setTemperatureMin(10.0d);
        weather.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        weather.setUpdatedBy(1L);
        weather.setWindDirection((short) 1);
        weather.setWindSpeed(10.0d);

        City city = new City();
        city.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        city.setCreatedBy(1L);
        city.setDeleted(true);
        city.setId(1L);
        city.setLocation(location);
        city.setName("Name");
        city.setState(Auditable.STATE.ACTIVE);
        city.setSubscriptions(new ArrayList<>());
        city.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        city.setUpdatedBy(1L);
        city.setWeather(weather);
        Optional<City> ofResult = Optional.of(city);
        doNothing().when(cityService).saveM(Mockito.<City>any());
        when(cityService.findByName(Mockito.<String>any())).thenReturn(ofResult);
        when(sessionUser.id()).thenReturn(1L);

        AuthUser authUser = new AuthUser();
        authUser.setActive(true);
        authUser.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        authUser.setCreatedBy(1L);
        authUser.setDeleted(true);
        authUser.setEmail("jane.doe@example.org");
        authUser.setId(1L);
        authUser.setPassword("iloveyou");
        authUser.setRole(AuthUser.ROLES.USER);
        authUser.setState(Auditable.STATE.ACTIVE);
        authUser.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        authUser.setUpdatedBy(1L);
        authUser.setUsername("janedoe");
        Optional<AuthUser> ofResult2 = Optional.of(authUser);
        when(authUserRepository.findById(Mockito.<Long>any())).thenReturn(ofResult2);

        ArrayList<String> cities = new ArrayList<>();
        cities.add("foo");

        MemberShipCitiesDTO cities2 = new MemberShipCitiesDTO();
        cities2.setCities(cities);
        userService.getMemberShips(cities2);
        verify(cityService).findByName(Mockito.<String>any());
        verify(cityService).saveM(Mockito.<City>any());
        verify(sessionUser, atLeast(1)).id();
        verify(authUserRepository, atLeast(1)).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link UserService#getMemberShips(MemberShipCitiesDTO)}
     */
    @Test
    void testGetMemberShips5() {
        Location location = new Location();
        location.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location.setCreatedBy(1L);
        location.setDeleted(true);
        location.setId(1L);
        location.setLatitude(10.0d);
        location.setLongitude(10.0d);
        location.setState(Auditable.STATE.ACTIVE);
        location.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location.setUpdatedBy(1L);

        Location location2 = new Location();
        location2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location2.setCreatedBy(1L);
        location2.setDeleted(true);
        location2.setId(1L);
        location2.setLatitude(10.0d);
        location2.setLongitude(10.0d);
        location2.setState(Auditable.STATE.ACTIVE);
        location2.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location2.setUpdatedBy(1L);

        Weather weather = new Weather();
        weather.setAirDensity(10.0d);
        weather.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        weather.setCreatedBy(1L);
        weather.setDate(LocalDate.of(1970, 1, 1));
        weather.setDeleted(true);
        weather.setGust(10.0d);
        weather.setId(1L);
        weather.setLocation(location2);
        weather.setState(Auditable.STATE.ACTIVE);
        weather.setTemperatureAvg(10.0d);
        weather.setTemperatureMax(10.0d);
        weather.setTemperatureMin(10.0d);
        weather.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        weather.setUpdatedBy(1L);
        weather.setWindDirection((short) 1);
        weather.setWindSpeed(10.0d);

        City city = new City();
        city.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        city.setCreatedBy(1L);
        city.setDeleted(true);
        city.setId(1L);
        city.setLocation(location);
        city.setName("Name");
        city.setState(Auditable.STATE.ACTIVE);
        city.setSubscriptions(new ArrayList<>());
        city.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        city.setUpdatedBy(1L);
        city.setWeather(weather);
        Optional<City> ofResult = Optional.of(city);
        doThrow(new NotFoundException("An error occurred")).when(cityService).saveM(Mockito.<City>any());
        when(cityService.findByName(Mockito.<String>any())).thenReturn(ofResult);
        when(sessionUser.id()).thenReturn(1L);

        AuthUser authUser = new AuthUser();
        authUser.setActive(true);
        authUser.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        authUser.setCreatedBy(1L);
        authUser.setDeleted(true);
        authUser.setEmail("jane.doe@example.org");
        authUser.setId(1L);
        authUser.setPassword("iloveyou");
        authUser.setRole(AuthUser.ROLES.USER);
        authUser.setState(Auditable.STATE.ACTIVE);
        authUser.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        authUser.setUpdatedBy(1L);
        authUser.setUsername("janedoe");
        Optional<AuthUser> ofResult2 = Optional.of(authUser);
        when(authUserRepository.findById(Mockito.<Long>any())).thenReturn(ofResult2);

        ArrayList<String> cities = new ArrayList<>();
        cities.add("foo");

        MemberShipCitiesDTO cities2 = new MemberShipCitiesDTO();
        cities2.setCities(cities);
        assertThrows(NotFoundException.class, () -> userService.getMemberShips(cities2));
        verify(cityService).findByName(Mockito.<String>any());
        verify(cityService).saveM(Mockito.<City>any());
        verify(sessionUser, atLeast(1)).id();
        verify(authUserRepository, atLeast(1)).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link UserService#getMemberShips(MemberShipCitiesDTO)}
     */
    @Test
    void testGetMemberShips6() {
        Location location = new Location();
        location.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location.setCreatedBy(1L);
        location.setDeleted(true);
        location.setId(1L);
        location.setLatitude(10.0d);
        location.setLongitude(10.0d);
        location.setState(Auditable.STATE.ACTIVE);
        location.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location.setUpdatedBy(1L);

        Location location2 = new Location();
        location2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location2.setCreatedBy(1L);
        location2.setDeleted(true);
        location2.setId(1L);
        location2.setLatitude(10.0d);
        location2.setLongitude(10.0d);
        location2.setState(Auditable.STATE.ACTIVE);
        location2.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location2.setUpdatedBy(1L);

        Weather weather = new Weather();
        weather.setAirDensity(10.0d);
        weather.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        weather.setCreatedBy(1L);
        weather.setDate(LocalDate.of(1970, 1, 1));
        weather.setDeleted(true);
        weather.setGust(10.0d);
        weather.setId(1L);
        weather.setLocation(location2);
        weather.setState(Auditable.STATE.ACTIVE);
        weather.setTemperatureAvg(10.0d);
        weather.setTemperatureMax(10.0d);
        weather.setTemperatureMin(10.0d);
        weather.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        weather.setUpdatedBy(1L);
        weather.setWindDirection((short) 1);
        weather.setWindSpeed(10.0d);
        City city = mock(City.class);
        when(city.getSubscriptions()).thenReturn(new ArrayList<>());
        doNothing().when(city).setCreatedAt(Mockito.<LocalDateTime>any());
        doNothing().when(city).setCreatedBy(Mockito.<Long>any());
        doNothing().when(city).setDeleted(anyBoolean());
        doNothing().when(city).setId(Mockito.<Long>any());
        doNothing().when(city).setState(Mockito.<Auditable.STATE>any());
        doNothing().when(city).setUpdateAt(Mockito.<LocalDateTime>any());
        doNothing().when(city).setUpdatedBy(Mockito.<Long>any());
        doNothing().when(city).setLocation(Mockito.<Location>any());
        doNothing().when(city).setName(Mockito.<String>any());
        doNothing().when(city).setSubscriptions(Mockito.<List<AuthUser>>any());
        doNothing().when(city).setWeather(Mockito.<Weather>any());
        city.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        city.setCreatedBy(1L);
        city.setDeleted(true);
        city.setId(1L);
        city.setLocation(location);
        city.setName("Name");
        city.setState(Auditable.STATE.ACTIVE);
        city.setSubscriptions(new ArrayList<>());
        city.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        city.setUpdatedBy(1L);
        city.setWeather(weather);
        Optional<City> ofResult = Optional.of(city);
        doNothing().when(cityService).saveM(Mockito.<City>any());
        when(cityService.findByName(Mockito.<String>any())).thenReturn(ofResult);
        when(sessionUser.id()).thenReturn(1L);

        AuthUser authUser = new AuthUser();
        authUser.setActive(true);
        authUser.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        authUser.setCreatedBy(1L);
        authUser.setDeleted(true);
        authUser.setEmail("jane.doe@example.org");
        authUser.setId(1L);
        authUser.setPassword("iloveyou");
        authUser.setRole(AuthUser.ROLES.USER);
        authUser.setState(Auditable.STATE.ACTIVE);
        authUser.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        authUser.setUpdatedBy(1L);
        authUser.setUsername("janedoe");
        Optional<AuthUser> ofResult2 = Optional.of(authUser);
        when(authUserRepository.findById(Mockito.<Long>any())).thenReturn(ofResult2);

        ArrayList<String> cities = new ArrayList<>();
        cities.add("foo");

        MemberShipCitiesDTO cities2 = new MemberShipCitiesDTO();
        cities2.setCities(cities);
        userService.getMemberShips(cities2);
        verify(cityService).findByName(Mockito.<String>any());
        verify(cityService).saveM(Mockito.<City>any());
        verify(city).getSubscriptions();
        verify(city).setCreatedAt(Mockito.<LocalDateTime>any());
        verify(city).setCreatedBy(Mockito.<Long>any());
        verify(city).setDeleted(anyBoolean());
        verify(city).setId(Mockito.<Long>any());
        verify(city).setState(Mockito.<Auditable.STATE>any());
        verify(city).setUpdateAt(Mockito.<LocalDateTime>any());
        verify(city).setUpdatedBy(Mockito.<Long>any());
        verify(city).setLocation(Mockito.<Location>any());
        verify(city).setName(Mockito.<String>any());
        verify(city).setSubscriptions(Mockito.<List<AuthUser>>any());
        verify(city).setWeather(Mockito.<Weather>any());
        verify(sessionUser, atLeast(1)).id();
        verify(authUserRepository, atLeast(1)).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link UserService#cancelMemberShips(MemberShipCitiesDTO)}
     */
    @Test
    void testCancelMemberShips() {
        when(sessionUser.id()).thenReturn(1L);

        AuthUser authUser = new AuthUser();
        authUser.setActive(true);
        authUser.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        authUser.setCreatedBy(1L);
        authUser.setDeleted(true);
        authUser.setEmail("jane.doe@example.org");
        authUser.setId(1L);
        authUser.setPassword("iloveyou");
        authUser.setRole(AuthUser.ROLES.USER);
        authUser.setState(Auditable.STATE.ACTIVE);
        authUser.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        authUser.setUpdatedBy(1L);
        authUser.setUsername("janedoe");
        Optional<AuthUser> ofResult = Optional.of(authUser);
        when(authUserRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        MemberShipCitiesDTO cities = new MemberShipCitiesDTO();
        cities.setCities(new ArrayList<>());
        userService.cancelMemberShips(cities);
        verify(sessionUser).id();
        verify(authUserRepository).findById(Mockito.<Long>any());
        assertTrue(cities.getCities().isEmpty());
    }

    /**
     * Method under test: {@link UserService#cancelMemberShips(MemberShipCitiesDTO)}
     */
    @Test
    void testCancelMemberShips2() {
        when(sessionUser.id()).thenReturn(1L);
        when(authUserRepository.findById(Mockito.<Long>any())).thenThrow(new NotFoundException("An error occurred"));

        MemberShipCitiesDTO cities = new MemberShipCitiesDTO();
        cities.setCities(new ArrayList<>());
        assertThrows(NotFoundException.class, () -> userService.cancelMemberShips(cities));
        verify(sessionUser).id();
        verify(authUserRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link UserService#cancelMemberShips(MemberShipCitiesDTO)}
     */
    @Test
    void testCancelMemberShips3() {
        when(sessionUser.id()).thenReturn(1L);
        when(authUserRepository.findById(Mockito.<Long>any())).thenReturn(Optional.empty());

        MemberShipCitiesDTO cities = new MemberShipCitiesDTO();
        cities.setCities(new ArrayList<>());
        assertThrows(IllegalArgumentException.class, () -> userService.cancelMemberShips(cities));
        verify(sessionUser).id();
        verify(authUserRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link UserService#cancelMemberShips(MemberShipCitiesDTO)}
     */
    @Test
    void testCancelMemberShips4() {
        Location location = new Location();
        location.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location.setCreatedBy(1L);
        location.setDeleted(true);
        location.setId(1L);
        location.setLatitude(10.0d);
        location.setLongitude(10.0d);
        location.setState(Auditable.STATE.ACTIVE);
        location.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location.setUpdatedBy(1L);

        Location location2 = new Location();
        location2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location2.setCreatedBy(1L);
        location2.setDeleted(true);
        location2.setId(1L);
        location2.setLatitude(10.0d);
        location2.setLongitude(10.0d);
        location2.setState(Auditable.STATE.ACTIVE);
        location2.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location2.setUpdatedBy(1L);

        Weather weather = new Weather();
        weather.setAirDensity(10.0d);
        weather.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        weather.setCreatedBy(1L);
        weather.setDate(LocalDate.of(1970, 1, 1));
        weather.setDeleted(true);
        weather.setGust(10.0d);
        weather.setId(1L);
        weather.setLocation(location2);
        weather.setState(Auditable.STATE.ACTIVE);
        weather.setTemperatureAvg(10.0d);
        weather.setTemperatureMax(10.0d);
        weather.setTemperatureMin(10.0d);
        weather.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        weather.setUpdatedBy(1L);
        weather.setWindDirection((short) 1);
        weather.setWindSpeed(10.0d);

        City city = new City();
        city.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        city.setCreatedBy(1L);
        city.setDeleted(true);
        city.setId(1L);
        city.setLocation(location);
        city.setName("Name");
        city.setState(Auditable.STATE.ACTIVE);
        city.setSubscriptions(new ArrayList<>());
        city.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        city.setUpdatedBy(1L);
        city.setWeather(weather);
        Optional<City> ofResult = Optional.of(city);
        doNothing().when(cityService).saveM(Mockito.<City>any());
        when(cityService.findByName(Mockito.<String>any())).thenReturn(ofResult);
        when(sessionUser.id()).thenReturn(1L);

        AuthUser authUser = new AuthUser();
        authUser.setActive(true);
        authUser.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        authUser.setCreatedBy(1L);
        authUser.setDeleted(true);
        authUser.setEmail("jane.doe@example.org");
        authUser.setId(1L);
        authUser.setPassword("iloveyou");
        authUser.setRole(AuthUser.ROLES.USER);
        authUser.setState(Auditable.STATE.ACTIVE);
        authUser.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        authUser.setUpdatedBy(1L);
        authUser.setUsername("janedoe");
        Optional<AuthUser> ofResult2 = Optional.of(authUser);
        when(authUserRepository.findById(Mockito.<Long>any())).thenReturn(ofResult2);

        ArrayList<String> cities = new ArrayList<>();
        cities.add("foo");

        MemberShipCitiesDTO cities2 = new MemberShipCitiesDTO();
        cities2.setCities(cities);
        userService.cancelMemberShips(cities2);
        verify(cityService).findByName(Mockito.<String>any());
        verify(cityService).saveM(Mockito.<City>any());
        verify(sessionUser, atLeast(1)).id();
        verify(authUserRepository, atLeast(1)).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link UserService#cancelMemberShips(MemberShipCitiesDTO)}
     */
    @Test
    void testCancelMemberShips5() {
        Location location = new Location();
        location.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location.setCreatedBy(1L);
        location.setDeleted(true);
        location.setId(1L);
        location.setLatitude(10.0d);
        location.setLongitude(10.0d);
        location.setState(Auditable.STATE.ACTIVE);
        location.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location.setUpdatedBy(1L);

        Location location2 = new Location();
        location2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location2.setCreatedBy(1L);
        location2.setDeleted(true);
        location2.setId(1L);
        location2.setLatitude(10.0d);
        location2.setLongitude(10.0d);
        location2.setState(Auditable.STATE.ACTIVE);
        location2.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location2.setUpdatedBy(1L);

        Weather weather = new Weather();
        weather.setAirDensity(10.0d);
        weather.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        weather.setCreatedBy(1L);
        weather.setDate(LocalDate.of(1970, 1, 1));
        weather.setDeleted(true);
        weather.setGust(10.0d);
        weather.setId(1L);
        weather.setLocation(location2);
        weather.setState(Auditable.STATE.ACTIVE);
        weather.setTemperatureAvg(10.0d);
        weather.setTemperatureMax(10.0d);
        weather.setTemperatureMin(10.0d);
        weather.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        weather.setUpdatedBy(1L);
        weather.setWindDirection((short) 1);
        weather.setWindSpeed(10.0d);

        City city = new City();
        city.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        city.setCreatedBy(1L);
        city.setDeleted(true);
        city.setId(1L);
        city.setLocation(location);
        city.setName("Name");
        city.setState(Auditable.STATE.ACTIVE);
        city.setSubscriptions(new ArrayList<>());
        city.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        city.setUpdatedBy(1L);
        city.setWeather(weather);
        Optional<City> ofResult = Optional.of(city);
        doThrow(new NotFoundException("An error occurred")).when(cityService).saveM(Mockito.<City>any());
        when(cityService.findByName(Mockito.<String>any())).thenReturn(ofResult);
        when(sessionUser.id()).thenReturn(1L);

        AuthUser authUser = new AuthUser();
        authUser.setActive(true);
        authUser.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        authUser.setCreatedBy(1L);
        authUser.setDeleted(true);
        authUser.setEmail("jane.doe@example.org");
        authUser.setId(1L);
        authUser.setPassword("iloveyou");
        authUser.setRole(AuthUser.ROLES.USER);
        authUser.setState(Auditable.STATE.ACTIVE);
        authUser.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        authUser.setUpdatedBy(1L);
        authUser.setUsername("janedoe");
        Optional<AuthUser> ofResult2 = Optional.of(authUser);
        when(authUserRepository.findById(Mockito.<Long>any())).thenReturn(ofResult2);

        ArrayList<String> cities = new ArrayList<>();
        cities.add("foo");

        MemberShipCitiesDTO cities2 = new MemberShipCitiesDTO();
        cities2.setCities(cities);
        assertThrows(NotFoundException.class, () -> userService.cancelMemberShips(cities2));
        verify(cityService).findByName(Mockito.<String>any());
        verify(cityService).saveM(Mockito.<City>any());
        verify(sessionUser, atLeast(1)).id();
        verify(authUserRepository, atLeast(1)).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link UserService#cancelMemberShips(MemberShipCitiesDTO)}
     */
    @Test
    void testCancelMemberShips6() {
        Location location = new Location();
        location.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location.setCreatedBy(1L);
        location.setDeleted(true);
        location.setId(1L);
        location.setLatitude(10.0d);
        location.setLongitude(10.0d);
        location.setState(Auditable.STATE.ACTIVE);
        location.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location.setUpdatedBy(1L);

        Location location2 = new Location();
        location2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location2.setCreatedBy(1L);
        location2.setDeleted(true);
        location2.setId(1L);
        location2.setLatitude(10.0d);
        location2.setLongitude(10.0d);
        location2.setState(Auditable.STATE.ACTIVE);
        location2.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location2.setUpdatedBy(1L);

        Weather weather = new Weather();
        weather.setAirDensity(10.0d);
        weather.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        weather.setCreatedBy(1L);
        weather.setDate(LocalDate.of(1970, 1, 1));
        weather.setDeleted(true);
        weather.setGust(10.0d);
        weather.setId(1L);
        weather.setLocation(location2);
        weather.setState(Auditable.STATE.ACTIVE);
        weather.setTemperatureAvg(10.0d);
        weather.setTemperatureMax(10.0d);
        weather.setTemperatureMin(10.0d);
        weather.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        weather.setUpdatedBy(1L);
        weather.setWindDirection((short) 1);
        weather.setWindSpeed(10.0d);
        City city = mock(City.class);
        when(city.getSubscriptions()).thenReturn(new ArrayList<>());
        doNothing().when(city).setCreatedAt(Mockito.<LocalDateTime>any());
        doNothing().when(city).setCreatedBy(Mockito.<Long>any());
        doNothing().when(city).setDeleted(anyBoolean());
        doNothing().when(city).setId(Mockito.<Long>any());
        doNothing().when(city).setState(Mockito.<Auditable.STATE>any());
        doNothing().when(city).setUpdateAt(Mockito.<LocalDateTime>any());
        doNothing().when(city).setUpdatedBy(Mockito.<Long>any());
        doNothing().when(city).setLocation(Mockito.<Location>any());
        doNothing().when(city).setName(Mockito.<String>any());
        doNothing().when(city).setSubscriptions(Mockito.<List<AuthUser>>any());
        doNothing().when(city).setWeather(Mockito.<Weather>any());
        city.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        city.setCreatedBy(1L);
        city.setDeleted(true);
        city.setId(1L);
        city.setLocation(location);
        city.setName("Name");
        city.setState(Auditable.STATE.ACTIVE);
        city.setSubscriptions(new ArrayList<>());
        city.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        city.setUpdatedBy(1L);
        city.setWeather(weather);
        Optional<City> ofResult = Optional.of(city);
        doNothing().when(cityService).saveM(Mockito.<City>any());
        when(cityService.findByName(Mockito.<String>any())).thenReturn(ofResult);
        when(sessionUser.id()).thenReturn(1L);

        AuthUser authUser = new AuthUser();
        authUser.setActive(true);
        authUser.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        authUser.setCreatedBy(1L);
        authUser.setDeleted(true);
        authUser.setEmail("jane.doe@example.org");
        authUser.setId(1L);
        authUser.setPassword("iloveyou");
        authUser.setRole(AuthUser.ROLES.USER);
        authUser.setState(Auditable.STATE.ACTIVE);
        authUser.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        authUser.setUpdatedBy(1L);
        authUser.setUsername("janedoe");
        Optional<AuthUser> ofResult2 = Optional.of(authUser);
        when(authUserRepository.findById(Mockito.<Long>any())).thenReturn(ofResult2);

        ArrayList<String> cities = new ArrayList<>();
        cities.add("foo");

        MemberShipCitiesDTO cities2 = new MemberShipCitiesDTO();
        cities2.setCities(cities);
        userService.cancelMemberShips(cities2);
        verify(cityService).findByName(Mockito.<String>any());
        verify(cityService).saveM(Mockito.<City>any());
        verify(city).getSubscriptions();
        verify(city).setCreatedAt(Mockito.<LocalDateTime>any());
        verify(city).setCreatedBy(Mockito.<Long>any());
        verify(city).setDeleted(anyBoolean());
        verify(city).setId(Mockito.<Long>any());
        verify(city).setState(Mockito.<Auditable.STATE>any());
        verify(city).setUpdateAt(Mockito.<LocalDateTime>any());
        verify(city).setUpdatedBy(Mockito.<Long>any());
        verify(city).setLocation(Mockito.<Location>any());
        verify(city).setName(Mockito.<String>any());
        verify(city).setSubscriptions(Mockito.<List<AuthUser>>any());
        verify(city).setWeather(Mockito.<Weather>any());
        verify(sessionUser, atLeast(1)).id();
        verify(authUserRepository, atLeast(1)).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link UserService#getMemberShip(String)}
     */
    @Test
    void testGetMemberShip() {
        Location location = new Location();
        location.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location.setCreatedBy(1L);
        location.setDeleted(true);
        location.setId(1L);
        location.setLatitude(10.0d);
        location.setLongitude(10.0d);
        location.setState(Auditable.STATE.ACTIVE);
        location.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location.setUpdatedBy(1L);

        Location location2 = new Location();
        location2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location2.setCreatedBy(1L);
        location2.setDeleted(true);
        location2.setId(1L);
        location2.setLatitude(10.0d);
        location2.setLongitude(10.0d);
        location2.setState(Auditable.STATE.ACTIVE);
        location2.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location2.setUpdatedBy(1L);

        Weather weather = new Weather();
        weather.setAirDensity(10.0d);
        weather.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        weather.setCreatedBy(1L);
        weather.setDate(LocalDate.of(1970, 1, 1));
        weather.setDeleted(true);
        weather.setGust(10.0d);
        weather.setId(1L);
        weather.setLocation(location2);
        weather.setState(Auditable.STATE.ACTIVE);
        weather.setTemperatureAvg(10.0d);
        weather.setTemperatureMax(10.0d);
        weather.setTemperatureMin(10.0d);
        weather.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        weather.setUpdatedBy(1L);
        weather.setWindDirection((short) 1);
        weather.setWindSpeed(10.0d);

        City city = new City();
        city.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        city.setCreatedBy(1L);
        city.setDeleted(true);
        city.setId(1L);
        city.setLocation(location);
        city.setName("Name");
        city.setState(Auditable.STATE.ACTIVE);
        city.setSubscriptions(new ArrayList<>());
        city.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        city.setUpdatedBy(1L);
        city.setWeather(weather);
        Optional<City> ofResult = Optional.of(city);
        doNothing().when(cityService).saveM(Mockito.<City>any());
        when(cityService.findByName(Mockito.<String>any())).thenReturn(ofResult);
        when(sessionUser.id()).thenReturn(1L);

        AuthUser authUser = new AuthUser();
        authUser.setActive(true);
        authUser.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        authUser.setCreatedBy(1L);
        authUser.setDeleted(true);
        authUser.setEmail("jane.doe@example.org");
        authUser.setId(1L);
        authUser.setPassword("iloveyou");
        authUser.setRole(AuthUser.ROLES.USER);
        authUser.setState(Auditable.STATE.ACTIVE);
        authUser.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        authUser.setUpdatedBy(1L);
        authUser.setUsername("janedoe");
        Optional<AuthUser> ofResult2 = Optional.of(authUser);
        when(authUserRepository.findById(Mockito.<Long>any())).thenReturn(ofResult2);
        userService.getMemberShip("Oxford");
        verify(cityService).findByName(Mockito.<String>any());
        verify(cityService).saveM(Mockito.<City>any());
        verify(sessionUser).id();
        verify(authUserRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link UserService#getMemberShip(String)}
     */
    @Test
    void testGetMemberShip2() {
        Location location = new Location();
        location.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location.setCreatedBy(1L);
        location.setDeleted(true);
        location.setId(1L);
        location.setLatitude(10.0d);
        location.setLongitude(10.0d);
        location.setState(Auditable.STATE.ACTIVE);
        location.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location.setUpdatedBy(1L);

        Location location2 = new Location();
        location2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location2.setCreatedBy(1L);
        location2.setDeleted(true);
        location2.setId(1L);
        location2.setLatitude(10.0d);
        location2.setLongitude(10.0d);
        location2.setState(Auditable.STATE.ACTIVE);
        location2.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location2.setUpdatedBy(1L);

        Weather weather = new Weather();
        weather.setAirDensity(10.0d);
        weather.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        weather.setCreatedBy(1L);
        weather.setDate(LocalDate.of(1970, 1, 1));
        weather.setDeleted(true);
        weather.setGust(10.0d);
        weather.setId(1L);
        weather.setLocation(location2);
        weather.setState(Auditable.STATE.ACTIVE);
        weather.setTemperatureAvg(10.0d);
        weather.setTemperatureMax(10.0d);
        weather.setTemperatureMin(10.0d);
        weather.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        weather.setUpdatedBy(1L);
        weather.setWindDirection((short) 1);
        weather.setWindSpeed(10.0d);

        City city = new City();
        city.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        city.setCreatedBy(1L);
        city.setDeleted(true);
        city.setId(1L);
        city.setLocation(location);
        city.setName("Name");
        city.setState(Auditable.STATE.ACTIVE);
        city.setSubscriptions(new ArrayList<>());
        city.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        city.setUpdatedBy(1L);
        city.setWeather(weather);
        Optional<City> ofResult = Optional.of(city);
        doThrow(new NotFoundException("An error occurred")).when(cityService).saveM(Mockito.<City>any());
        when(cityService.findByName(Mockito.<String>any())).thenReturn(ofResult);
        when(sessionUser.id()).thenReturn(1L);

        AuthUser authUser = new AuthUser();
        authUser.setActive(true);
        authUser.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        authUser.setCreatedBy(1L);
        authUser.setDeleted(true);
        authUser.setEmail("jane.doe@example.org");
        authUser.setId(1L);
        authUser.setPassword("iloveyou");
        authUser.setRole(AuthUser.ROLES.USER);
        authUser.setState(Auditable.STATE.ACTIVE);
        authUser.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        authUser.setUpdatedBy(1L);
        authUser.setUsername("janedoe");
        Optional<AuthUser> ofResult2 = Optional.of(authUser);
        when(authUserRepository.findById(Mockito.<Long>any())).thenReturn(ofResult2);
        assertThrows(NotFoundException.class, () -> userService.getMemberShip("Oxford"));
        verify(cityService).findByName(Mockito.<String>any());
        verify(cityService).saveM(Mockito.<City>any());
        verify(sessionUser).id();
        verify(authUserRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link UserService#getMemberShip(String)}
     */
    @Test
    void testGetMemberShip3() {
        Location location = new Location();
        location.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location.setCreatedBy(1L);
        location.setDeleted(true);
        location.setId(1L);
        location.setLatitude(10.0d);
        location.setLongitude(10.0d);
        location.setState(Auditable.STATE.ACTIVE);
        location.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location.setUpdatedBy(1L);

        Location location2 = new Location();
        location2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location2.setCreatedBy(1L);
        location2.setDeleted(true);
        location2.setId(1L);
        location2.setLatitude(10.0d);
        location2.setLongitude(10.0d);
        location2.setState(Auditable.STATE.ACTIVE);
        location2.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location2.setUpdatedBy(1L);

        Weather weather = new Weather();
        weather.setAirDensity(10.0d);
        weather.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        weather.setCreatedBy(1L);
        weather.setDate(LocalDate.of(1970, 1, 1));
        weather.setDeleted(true);
        weather.setGust(10.0d);
        weather.setId(1L);
        weather.setLocation(location2);
        weather.setState(Auditable.STATE.ACTIVE);
        weather.setTemperatureAvg(10.0d);
        weather.setTemperatureMax(10.0d);
        weather.setTemperatureMin(10.0d);
        weather.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        weather.setUpdatedBy(1L);
        weather.setWindDirection((short) 1);
        weather.setWindSpeed(10.0d);
        City city = mock(City.class);
        when(city.getSubscriptions()).thenReturn(new ArrayList<>());
        doNothing().when(city).setCreatedAt(Mockito.<LocalDateTime>any());
        doNothing().when(city).setCreatedBy(Mockito.<Long>any());
        doNothing().when(city).setDeleted(anyBoolean());
        doNothing().when(city).setId(Mockito.<Long>any());
        doNothing().when(city).setState(Mockito.<Auditable.STATE>any());
        doNothing().when(city).setUpdateAt(Mockito.<LocalDateTime>any());
        doNothing().when(city).setUpdatedBy(Mockito.<Long>any());
        doNothing().when(city).setLocation(Mockito.<Location>any());
        doNothing().when(city).setName(Mockito.<String>any());
        doNothing().when(city).setSubscriptions(Mockito.<List<AuthUser>>any());
        doNothing().when(city).setWeather(Mockito.<Weather>any());
        city.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        city.setCreatedBy(1L);
        city.setDeleted(true);
        city.setId(1L);
        city.setLocation(location);
        city.setName("Name");
        city.setState(Auditable.STATE.ACTIVE);
        city.setSubscriptions(new ArrayList<>());
        city.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        city.setUpdatedBy(1L);
        city.setWeather(weather);
        Optional<City> ofResult = Optional.of(city);
        doNothing().when(cityService).saveM(Mockito.<City>any());
        when(cityService.findByName(Mockito.<String>any())).thenReturn(ofResult);
        when(sessionUser.id()).thenReturn(1L);

        AuthUser authUser = new AuthUser();
        authUser.setActive(true);
        authUser.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        authUser.setCreatedBy(1L);
        authUser.setDeleted(true);
        authUser.setEmail("jane.doe@example.org");
        authUser.setId(1L);
        authUser.setPassword("iloveyou");
        authUser.setRole(AuthUser.ROLES.USER);
        authUser.setState(Auditable.STATE.ACTIVE);
        authUser.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        authUser.setUpdatedBy(1L);
        authUser.setUsername("janedoe");
        Optional<AuthUser> ofResult2 = Optional.of(authUser);
        when(authUserRepository.findById(Mockito.<Long>any())).thenReturn(ofResult2);
        userService.getMemberShip("Oxford");
        verify(cityService).findByName(Mockito.<String>any());
        verify(cityService).saveM(Mockito.<City>any());
        verify(city).getSubscriptions();
        verify(city).setCreatedAt(Mockito.<LocalDateTime>any());
        verify(city).setCreatedBy(Mockito.<Long>any());
        verify(city).setDeleted(anyBoolean());
        verify(city).setId(Mockito.<Long>any());
        verify(city).setState(Mockito.<Auditable.STATE>any());
        verify(city).setUpdateAt(Mockito.<LocalDateTime>any());
        verify(city).setUpdatedBy(Mockito.<Long>any());
        verify(city).setLocation(Mockito.<Location>any());
        verify(city).setName(Mockito.<String>any());
        verify(city).setSubscriptions(Mockito.<List<AuthUser>>any());
        verify(city).setWeather(Mockito.<Weather>any());
        verify(sessionUser).id();
        verify(authUserRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link UserService#cancelMemberShip(String)}
     */
    @Test
    void testCancelMemberShip() {
        Location location = new Location();
        location.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location.setCreatedBy(1L);
        location.setDeleted(true);
        location.setId(1L);
        location.setLatitude(10.0d);
        location.setLongitude(10.0d);
        location.setState(Auditable.STATE.ACTIVE);
        location.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location.setUpdatedBy(1L);

        Location location2 = new Location();
        location2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location2.setCreatedBy(1L);
        location2.setDeleted(true);
        location2.setId(1L);
        location2.setLatitude(10.0d);
        location2.setLongitude(10.0d);
        location2.setState(Auditable.STATE.ACTIVE);
        location2.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location2.setUpdatedBy(1L);

        Weather weather = new Weather();
        weather.setAirDensity(10.0d);
        weather.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        weather.setCreatedBy(1L);
        weather.setDate(LocalDate.of(1970, 1, 1));
        weather.setDeleted(true);
        weather.setGust(10.0d);
        weather.setId(1L);
        weather.setLocation(location2);
        weather.setState(Auditable.STATE.ACTIVE);
        weather.setTemperatureAvg(10.0d);
        weather.setTemperatureMax(10.0d);
        weather.setTemperatureMin(10.0d);
        weather.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        weather.setUpdatedBy(1L);
        weather.setWindDirection((short) 1);
        weather.setWindSpeed(10.0d);

        City city = new City();
        city.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        city.setCreatedBy(1L);
        city.setDeleted(true);
        city.setId(1L);
        city.setLocation(location);
        city.setName("Name");
        city.setState(Auditable.STATE.ACTIVE);
        city.setSubscriptions(new ArrayList<>());
        city.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        city.setUpdatedBy(1L);
        city.setWeather(weather);
        Optional<City> ofResult = Optional.of(city);
        doNothing().when(cityService).saveM(Mockito.<City>any());
        when(cityService.findByName(Mockito.<String>any())).thenReturn(ofResult);
        when(sessionUser.id()).thenReturn(1L);

        AuthUser authUser = new AuthUser();
        authUser.setActive(true);
        authUser.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        authUser.setCreatedBy(1L);
        authUser.setDeleted(true);
        authUser.setEmail("jane.doe@example.org");
        authUser.setId(1L);
        authUser.setPassword("iloveyou");
        authUser.setRole(AuthUser.ROLES.USER);
        authUser.setState(Auditable.STATE.ACTIVE);
        authUser.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        authUser.setUpdatedBy(1L);
        authUser.setUsername("janedoe");
        Optional<AuthUser> ofResult2 = Optional.of(authUser);
        when(authUserRepository.findById(Mockito.<Long>any())).thenReturn(ofResult2);
        userService.cancelMemberShip("Oxford");
        verify(cityService).findByName(Mockito.<String>any());
        verify(cityService).saveM(Mockito.<City>any());
        verify(sessionUser).id();
        verify(authUserRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link UserService#cancelMemberShip(String)}
     */
    @Test
    void testCancelMemberShip2() {
        Location location = new Location();
        location.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location.setCreatedBy(1L);
        location.setDeleted(true);
        location.setId(1L);
        location.setLatitude(10.0d);
        location.setLongitude(10.0d);
        location.setState(Auditable.STATE.ACTIVE);
        location.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location.setUpdatedBy(1L);

        Location location2 = new Location();
        location2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location2.setCreatedBy(1L);
        location2.setDeleted(true);
        location2.setId(1L);
        location2.setLatitude(10.0d);
        location2.setLongitude(10.0d);
        location2.setState(Auditable.STATE.ACTIVE);
        location2.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location2.setUpdatedBy(1L);

        Weather weather = new Weather();
        weather.setAirDensity(10.0d);
        weather.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        weather.setCreatedBy(1L);
        weather.setDate(LocalDate.of(1970, 1, 1));
        weather.setDeleted(true);
        weather.setGust(10.0d);
        weather.setId(1L);
        weather.setLocation(location2);
        weather.setState(Auditable.STATE.ACTIVE);
        weather.setTemperatureAvg(10.0d);
        weather.setTemperatureMax(10.0d);
        weather.setTemperatureMin(10.0d);
        weather.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        weather.setUpdatedBy(1L);
        weather.setWindDirection((short) 1);
        weather.setWindSpeed(10.0d);

        City city = new City();
        city.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        city.setCreatedBy(1L);
        city.setDeleted(true);
        city.setId(1L);
        city.setLocation(location);
        city.setName("Name");
        city.setState(Auditable.STATE.ACTIVE);
        city.setSubscriptions(new ArrayList<>());
        city.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        city.setUpdatedBy(1L);
        city.setWeather(weather);
        Optional<City> ofResult = Optional.of(city);
        doThrow(new NotFoundException("An error occurred")).when(cityService).saveM(Mockito.<City>any());
        when(cityService.findByName(Mockito.<String>any())).thenReturn(ofResult);
        when(sessionUser.id()).thenReturn(1L);

        AuthUser authUser = new AuthUser();
        authUser.setActive(true);
        authUser.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        authUser.setCreatedBy(1L);
        authUser.setDeleted(true);
        authUser.setEmail("jane.doe@example.org");
        authUser.setId(1L);
        authUser.setPassword("iloveyou");
        authUser.setRole(AuthUser.ROLES.USER);
        authUser.setState(Auditable.STATE.ACTIVE);
        authUser.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        authUser.setUpdatedBy(1L);
        authUser.setUsername("janedoe");
        Optional<AuthUser> ofResult2 = Optional.of(authUser);
        when(authUserRepository.findById(Mockito.<Long>any())).thenReturn(ofResult2);
        assertThrows(NotFoundException.class, () -> userService.cancelMemberShip("Oxford"));
        verify(cityService).findByName(Mockito.<String>any());
        verify(cityService).saveM(Mockito.<City>any());
        verify(sessionUser).id();
        verify(authUserRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link UserService#cancelMemberShip(String)}
     */
    @Test
    void testCancelMemberShip3() {
        Location location = new Location();
        location.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location.setCreatedBy(1L);
        location.setDeleted(true);
        location.setId(1L);
        location.setLatitude(10.0d);
        location.setLongitude(10.0d);
        location.setState(Auditable.STATE.ACTIVE);
        location.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location.setUpdatedBy(1L);

        Location location2 = new Location();
        location2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location2.setCreatedBy(1L);
        location2.setDeleted(true);
        location2.setId(1L);
        location2.setLatitude(10.0d);
        location2.setLongitude(10.0d);
        location2.setState(Auditable.STATE.ACTIVE);
        location2.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location2.setUpdatedBy(1L);

        Weather weather = new Weather();
        weather.setAirDensity(10.0d);
        weather.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        weather.setCreatedBy(1L);
        weather.setDate(LocalDate.of(1970, 1, 1));
        weather.setDeleted(true);
        weather.setGust(10.0d);
        weather.setId(1L);
        weather.setLocation(location2);
        weather.setState(Auditable.STATE.ACTIVE);
        weather.setTemperatureAvg(10.0d);
        weather.setTemperatureMax(10.0d);
        weather.setTemperatureMin(10.0d);
        weather.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        weather.setUpdatedBy(1L);
        weather.setWindDirection((short) 1);
        weather.setWindSpeed(10.0d);
        City city = mock(City.class);
        when(city.getSubscriptions()).thenReturn(new ArrayList<>());
        doNothing().when(city).setCreatedAt(Mockito.<LocalDateTime>any());
        doNothing().when(city).setCreatedBy(Mockito.<Long>any());
        doNothing().when(city).setDeleted(anyBoolean());
        doNothing().when(city).setId(Mockito.<Long>any());
        doNothing().when(city).setState(Mockito.<Auditable.STATE>any());
        doNothing().when(city).setUpdateAt(Mockito.<LocalDateTime>any());
        doNothing().when(city).setUpdatedBy(Mockito.<Long>any());
        doNothing().when(city).setLocation(Mockito.<Location>any());
        doNothing().when(city).setName(Mockito.<String>any());
        doNothing().when(city).setSubscriptions(Mockito.<List<AuthUser>>any());
        doNothing().when(city).setWeather(Mockito.<Weather>any());
        city.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        city.setCreatedBy(1L);
        city.setDeleted(true);
        city.setId(1L);
        city.setLocation(location);
        city.setName("Name");
        city.setState(Auditable.STATE.ACTIVE);
        city.setSubscriptions(new ArrayList<>());
        city.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        city.setUpdatedBy(1L);
        city.setWeather(weather);
        Optional<City> ofResult = Optional.of(city);
        doNothing().when(cityService).saveM(Mockito.<City>any());
        when(cityService.findByName(Mockito.<String>any())).thenReturn(ofResult);
        when(sessionUser.id()).thenReturn(1L);

        AuthUser authUser = new AuthUser();
        authUser.setActive(true);
        authUser.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        authUser.setCreatedBy(1L);
        authUser.setDeleted(true);
        authUser.setEmail("jane.doe@example.org");
        authUser.setId(1L);
        authUser.setPassword("iloveyou");
        authUser.setRole(AuthUser.ROLES.USER);
        authUser.setState(Auditable.STATE.ACTIVE);
        authUser.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        authUser.setUpdatedBy(1L);
        authUser.setUsername("janedoe");
        Optional<AuthUser> ofResult2 = Optional.of(authUser);
        when(authUserRepository.findById(Mockito.<Long>any())).thenReturn(ofResult2);
        userService.cancelMemberShip("Oxford");
        verify(cityService).findByName(Mockito.<String>any());
        verify(cityService).saveM(Mockito.<City>any());
        verify(city).getSubscriptions();
        verify(city).setCreatedAt(Mockito.<LocalDateTime>any());
        verify(city).setCreatedBy(Mockito.<Long>any());
        verify(city).setDeleted(anyBoolean());
        verify(city).setId(Mockito.<Long>any());
        verify(city).setState(Mockito.<Auditable.STATE>any());
        verify(city).setUpdateAt(Mockito.<LocalDateTime>any());
        verify(city).setUpdatedBy(Mockito.<Long>any());
        verify(city).setLocation(Mockito.<Location>any());
        verify(city).setName(Mockito.<String>any());
        verify(city).setSubscriptions(Mockito.<List<AuthUser>>any());
        verify(city).setWeather(Mockito.<Weather>any());
        verify(sessionUser).id();
        verify(authUserRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link UserService#getInfoAboutMyMemberShipCitiesWeather(Pageable)}
     */
    @Test
    void testGetInfoAboutMyMemberShipCitiesWeather() {
        PageImpl<WeatherInfoDTO> pageImpl = new PageImpl<>(new ArrayList<>());
        when(cityService.getCitiesWeatherBySubscription(Mockito.<Pageable>any())).thenReturn(pageImpl);
        Page<WeatherInfoDTO> actualInfoAboutMyMemberShipCitiesWeather = userService
                .getInfoAboutMyMemberShipCitiesWeather(null);
        assertSame(pageImpl, actualInfoAboutMyMemberShipCitiesWeather);
        assertTrue(actualInfoAboutMyMemberShipCitiesWeather.toList().isEmpty());
        verify(cityService).getCitiesWeatherBySubscription(Mockito.<Pageable>any());
    }

    /**
     * Method under test: {@link UserService#getInfoAboutMyMemberShipCitiesWeather(Pageable)}
     */
    @Test
    void testGetInfoAboutMyMemberShipCitiesWeather2() {
        when(cityService.getCitiesWeatherBySubscription(Mockito.<Pageable>any()))
                .thenThrow(new NotFoundException("An error occurred"));
        assertThrows(NotFoundException.class, () -> userService.getInfoAboutMyMemberShipCitiesWeather(null));
        verify(cityService).getCitiesWeatherBySubscription(Mockito.<Pageable>any());
    }

    /**
     * Method under test: {@link UserService#getCitiesList(Pageable)}
     */
    @Test
    void testGetCitiesList() {
        PageImpl<City> pageImpl = new PageImpl<>(new ArrayList<>());
        when(cityService.getCitiesPagination(Mockito.<Pageable>any())).thenReturn(pageImpl);
        Page<City> actualCitiesList = userService.getCitiesList(null);
        assertSame(pageImpl, actualCitiesList);
        assertTrue(actualCitiesList.toList().isEmpty());
        verify(cityService).getCitiesPagination(Mockito.<Pageable>any());
    }

    /**
     * Method under test: {@link UserService#getCitiesList(Pageable)}
     */
    @Test
    void testGetCitiesList2() {
        when(cityService.getCitiesPagination(Mockito.<Pageable>any()))
                .thenThrow(new NotFoundException("An error occurred"));
        assertThrows(NotFoundException.class, () -> userService.getCitiesList(null));
        verify(cityService).getCitiesPagination(Mockito.<Pageable>any());
    }

    /**
     * Method under test: {@link UserService#getUserAndTheirSubscriptions(Pageable, String)}
     */
    @Test
    void testGetUserAndTheirSubscriptions() {
        PageImpl<UserWeatherInfoDTO> pageImpl = new PageImpl<>(new ArrayList<>());
        when(cityService.getUserAndTheirSubscriptions(Mockito.<Pageable>any(), Mockito.<AuthUser>any()))
                .thenReturn(pageImpl);

        AuthUser authUser = new AuthUser();
        authUser.setActive(true);
        authUser.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        authUser.setCreatedBy(1L);
        authUser.setDeleted(true);
        authUser.setEmail("jane.doe@example.org");
        authUser.setId(1L);
        authUser.setPassword("iloveyou");
        authUser.setRole(AuthUser.ROLES.USER);
        authUser.setState(Auditable.STATE.ACTIVE);
        authUser.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        authUser.setUpdatedBy(1L);
        authUser.setUsername("janedoe");
        Optional<AuthUser> ofResult = Optional.of(authUser);
        when(authUserRepository.findByUsername(Mockito.<String>any())).thenReturn(ofResult);
        Page<UserWeatherInfoDTO> actualUserAndTheirSubscriptions = userService.getUserAndTheirSubscriptions(null,
                "janedoe");
        assertSame(pageImpl, actualUserAndTheirSubscriptions);
        assertTrue(actualUserAndTheirSubscriptions.toList().isEmpty());
        verify(cityService).getUserAndTheirSubscriptions(Mockito.<Pageable>any(), Mockito.<AuthUser>any());
        verify(authUserRepository).findByUsername(Mockito.<String>any());
    }

    /**
     * Method under test: {@link UserService#getUserAndTheirSubscriptions(Pageable, String)}
     */
    @Test
    void testGetUserAndTheirSubscriptions2() {
        when(cityService.getUserAndTheirSubscriptions(Mockito.<Pageable>any(), Mockito.<AuthUser>any()))
                .thenThrow(new NotFoundException("An error occurred"));

        AuthUser authUser = new AuthUser();
        authUser.setActive(true);
        authUser.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        authUser.setCreatedBy(1L);
        authUser.setDeleted(true);
        authUser.setEmail("jane.doe@example.org");
        authUser.setId(1L);
        authUser.setPassword("iloveyou");
        authUser.setRole(AuthUser.ROLES.USER);
        authUser.setState(Auditable.STATE.ACTIVE);
        authUser.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        authUser.setUpdatedBy(1L);
        authUser.setUsername("janedoe");
        Optional<AuthUser> ofResult = Optional.of(authUser);
        when(authUserRepository.findByUsername(Mockito.<String>any())).thenReturn(ofResult);
        assertThrows(NotFoundException.class, () -> userService.getUserAndTheirSubscriptions(null, "janedoe"));
        verify(cityService).getUserAndTheirSubscriptions(Mockito.<Pageable>any(), Mockito.<AuthUser>any());
        verify(authUserRepository).findByUsername(Mockito.<String>any());
    }

    /**
     * Method under test: {@link UserService#getUserAndTheirSubscriptions(Pageable, String)}
     */
    @Test
    void testGetUserAndTheirSubscriptions3() {
        when(cityService.getUserAndTheirSubscriptions(Mockito.<Pageable>any(), Mockito.<AuthUser>any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        when(authUserRepository.findByUsername(Mockito.<String>any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> userService.getUserAndTheirSubscriptions(null, "janedoe"));
        verify(authUserRepository).findByUsername(Mockito.<String>any());
    }

    /**
     * Method under test: {@link UserService#getUsersList(Pageable)}
     */
    @Test
    void testGetUsersList() {
        PageImpl<AuthUser> pageImpl = new PageImpl<>(new ArrayList<>());
        when(authUserRepository.findAll(Mockito.<Pageable>any())).thenReturn(pageImpl);
        Page<AuthUser> actualUsersList = userService.getUsersList(null);
        assertSame(pageImpl, actualUsersList);
        assertTrue(actualUsersList.toList().isEmpty());
        verify(authUserRepository).findAll(Mockito.<Pageable>any());
    }

    /**
     * Method under test: {@link UserService#getUsersList(Pageable)}
     */
    @Test
    void testGetUsersList2() {
        when(authUserRepository.findAll(Mockito.<Pageable>any())).thenThrow(new NotFoundException("An error occurred"));
        assertThrows(NotFoundException.class, () -> userService.getUsersList(null));
        verify(authUserRepository).findAll(Mockito.<Pageable>any());
    }
}

