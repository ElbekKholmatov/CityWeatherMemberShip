package dev.sheengo.weatherservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.sheengo.weatherservice.config.security.JwtTokenUtil;
import dev.sheengo.weatherservice.config.security.SessionUser;
import dev.sheengo.weatherservice.criteria.CityCreateCriteria;
import dev.sheengo.weatherservice.domains.Auditable;
import dev.sheengo.weatherservice.domains.AuthUser;
import dev.sheengo.weatherservice.domains.City;
import dev.sheengo.weatherservice.domains.Location;
import dev.sheengo.weatherservice.domains.Weather;
import dev.sheengo.weatherservice.dto.CityUpdateDto;
import dev.sheengo.weatherservice.dto.UserWeatherInfoDTO;
import dev.sheengo.weatherservice.repository.AuthUserRepository;
import dev.sheengo.weatherservice.repository.CityRepository;
import dev.sheengo.weatherservice.repository.LocationRepository;
import dev.sheengo.weatherservice.repository.WeatherRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.intercept.RunAsImplAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CityService.class})
@ExtendWith(SpringExtension.class)
class CityServiceTest {
    @MockBean
    private AuthServiceImpl authServiceImpl;

    @MockBean
    private AuthUserRepository authUserRepository;

    @MockBean
    private CityRepository cityRepository;

    @Autowired
    private CityService cityService;

    @MockBean
    private LocationService locationService;

    @MockBean
    private WeatherService weatherService;

    /**
     * Method under test: {@link CityService#save(CityCreateCriteria)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSave() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at dev.sheengo.weatherservice.service.LocationService.save(LocationService.java:26)
        //       at dev.sheengo.weatherservice.service.CityService.save(CityService.java:40)
        //   See https://diff.blue/R013 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: A parent AuthenticationManager or a list of AuthenticationProviders is required
        //   See https://diff.blue/R013 to resolve this issue.

        CityRepository cityRepository = mock(CityRepository.class);
        LocationService locationService = new LocationService(mock(LocationRepository.class));
        WeatherService weatherService = new WeatherService(mock(WeatherRepository.class), mock(CityRepository.class));

        ProviderManager authenticationManager = new ProviderManager(new ArrayList<>());
        AuthUserRepository authUserRepository = mock(AuthUserRepository.class);
        Argon2PasswordEncoder passwordEncoder = new Argon2PasswordEncoder();
        JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
        CityService cityService = new CityService(cityRepository, locationService, weatherService,
                new AuthServiceImpl(authenticationManager, authUserRepository, passwordEncoder, jwtTokenUtil,
                        new SessionUser()),
                mock(AuthUserRepository.class));
        cityService.save(new CityCreateCriteria("Name", 10.0d, 10.0d));
    }

    /**
     * Method under test: {@link CityService#save(CityCreateCriteria)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSave2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at dev.sheengo.weatherservice.service.LocationService.save(LocationService.java:26)
        //       at dev.sheengo.weatherservice.service.CityService.save(CityService.java:40)
        //   See https://diff.blue/R013 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R011 Sandboxing policy violation.
        //   Diffblue Cover ran code in your project that tried
        //     to access the network.
        //   Diffblue Cover's default sandboxing policy disallows this in order to prevent
        //   your code from damaging your system environment.
        //   See https://diff.blue/R011 to resolve this issue.

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
        LocationRepository locationRepository = mock(LocationRepository.class);
        when(locationRepository.save(Mockito.<Location>any())).thenReturn(location);
        LocationService locationService = new LocationService(locationRepository);

        ArrayList<AuthenticationProvider> providers = new ArrayList<>();
        providers.add(new RunAsImplAuthenticationProvider());
        ProviderManager authenticationManager = new ProviderManager(providers);
        AuthUserRepository authUserRepository = mock(AuthUserRepository.class);
        Argon2PasswordEncoder passwordEncoder = new Argon2PasswordEncoder();
        JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
        AuthServiceImpl authService = new AuthServiceImpl(authenticationManager, authUserRepository, passwordEncoder,
                jwtTokenUtil, new SessionUser());

        CityRepository cityRepository = mock(CityRepository.class);
        CityService cityService = new CityService(cityRepository, locationService,
                new WeatherService(mock(WeatherRepository.class), mock(CityRepository.class)), authService,
                mock(AuthUserRepository.class));
        cityService.save(new CityCreateCriteria("Name", 10.0d, 10.0d));
    }

    /**
     * Method under test: {@link CityService#save(CityCreateCriteria)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSave3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at dev.sheengo.weatherservice.service.LocationService.save(LocationService.java:26)
        //       at dev.sheengo.weatherservice.service.CityService.save(CityService.java:40)
        //   See https://diff.blue/R013 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R011 Sandboxing policy violation.
        //   Diffblue Cover ran code in your project that tried
        //     to access the network.
        //   Diffblue Cover's default sandboxing policy disallows this in order to prevent
        //   your code from damaging your system environment.
        //   See https://diff.blue/R011 to resolve this issue.

        Location location = mock(Location.class);
        when(location.getLatitude()).thenReturn(10.0d);
        when(location.getLongitude()).thenReturn(10.0d);
        doNothing().when(location).setCreatedAt(Mockito.<LocalDateTime>any());
        doNothing().when(location).setCreatedBy(Mockito.<Long>any());
        doNothing().when(location).setDeleted(anyBoolean());
        doNothing().when(location).setId(Mockito.<Long>any());
        doNothing().when(location).setState(Mockito.<Auditable.STATE>any());
        doNothing().when(location).setUpdateAt(Mockito.<LocalDateTime>any());
        doNothing().when(location).setUpdatedBy(Mockito.<Long>any());
        doNothing().when(location).setLatitude(Mockito.<Double>any());
        doNothing().when(location).setLongitude(Mockito.<Double>any());
        location.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location.setCreatedBy(1L);
        location.setDeleted(true);
        location.setId(1L);
        location.setLatitude(10.0d);
        location.setLongitude(10.0d);
        location.setState(Auditable.STATE.ACTIVE);
        location.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location.setUpdatedBy(1L);
        LocationRepository locationRepository = mock(LocationRepository.class);
        when(locationRepository.save(Mockito.<Location>any())).thenReturn(location);
        LocationService locationService = new LocationService(locationRepository);

        ArrayList<AuthenticationProvider> providers = new ArrayList<>();
        providers.add(new RunAsImplAuthenticationProvider());
        ProviderManager authenticationManager = new ProviderManager(providers);
        AuthUserRepository authUserRepository = mock(AuthUserRepository.class);
        Argon2PasswordEncoder passwordEncoder = new Argon2PasswordEncoder();
        JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
        AuthServiceImpl authService = new AuthServiceImpl(authenticationManager, authUserRepository, passwordEncoder,
                jwtTokenUtil, new SessionUser());

        CityRepository cityRepository = mock(CityRepository.class);
        CityService cityService = new CityService(cityRepository, locationService,
                new WeatherService(mock(WeatherRepository.class), mock(CityRepository.class)), authService,
                mock(AuthUserRepository.class));
        cityService.save(new CityCreateCriteria("Name", 10.0d, 10.0d));
    }

    /**
     * Method under test: {@link CityService#save(CityCreateCriteria)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSave4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at dev.sheengo.weatherservice.service.LocationService.save(LocationService.java:26)
        //       at dev.sheengo.weatherservice.service.CityService.save(CityService.java:40)
        //   See https://diff.blue/R013 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at dev.sheengo.weatherservice.service.CityService.save(CityService.java:40)
        //   See https://diff.blue/R013 to resolve this issue.

        Location location = mock(Location.class);
        when(location.getLatitude()).thenReturn(10.0d);
        when(location.getLongitude()).thenReturn(10.0d);
        doNothing().when(location).setCreatedAt(Mockito.<LocalDateTime>any());
        doNothing().when(location).setCreatedBy(Mockito.<Long>any());
        doNothing().when(location).setDeleted(anyBoolean());
        doNothing().when(location).setId(Mockito.<Long>any());
        doNothing().when(location).setState(Mockito.<Auditable.STATE>any());
        doNothing().when(location).setUpdateAt(Mockito.<LocalDateTime>any());
        doNothing().when(location).setUpdatedBy(Mockito.<Long>any());
        doNothing().when(location).setLatitude(Mockito.<Double>any());
        doNothing().when(location).setLongitude(Mockito.<Double>any());
        location.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location.setCreatedBy(1L);
        location.setDeleted(true);
        location.setId(1L);
        location.setLatitude(10.0d);
        location.setLongitude(10.0d);
        location.setState(Auditable.STATE.ACTIVE);
        location.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location.setUpdatedBy(1L);

        ArrayList<AuthenticationProvider> providers = new ArrayList<>();
        providers.add(new RunAsImplAuthenticationProvider());
        ProviderManager authenticationManager = new ProviderManager(providers);
        AuthUserRepository authUserRepository = mock(AuthUserRepository.class);
        Argon2PasswordEncoder passwordEncoder = new Argon2PasswordEncoder();
        JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
        AuthServiceImpl authService = new AuthServiceImpl(authenticationManager, authUserRepository, passwordEncoder,
                jwtTokenUtil, new SessionUser());

        CityRepository cityRepository = mock(CityRepository.class);
        CityService cityService = new CityService(cityRepository, null,
                new WeatherService(mock(WeatherRepository.class), mock(CityRepository.class)), authService,
                mock(AuthUserRepository.class));
        cityService.save(new CityCreateCriteria("Name", 10.0d, 10.0d));
    }

    /**
     * Method under test: {@link CityService#save(CityCreateCriteria)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSave5() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at dev.sheengo.weatherservice.service.LocationService.save(LocationService.java:26)
        //       at dev.sheengo.weatherservice.service.CityService.save(CityService.java:40)
        //   See https://diff.blue/R013 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R011 Sandboxing policy violation.
        //   Diffblue Cover ran code in your project that tried
        //     to access the network.
        //   Diffblue Cover's default sandboxing policy disallows this in order to prevent
        //   your code from damaging your system environment.
        //   See https://diff.blue/R011 to resolve this issue.

        Location location = mock(Location.class);
        when(location.getLatitude()).thenReturn(10.0d);
        when(location.getLongitude()).thenReturn(10.0d);
        doNothing().when(location).setCreatedAt(Mockito.<LocalDateTime>any());
        doNothing().when(location).setCreatedBy(Mockito.<Long>any());
        doNothing().when(location).setDeleted(anyBoolean());
        doNothing().when(location).setId(Mockito.<Long>any());
        doNothing().when(location).setState(Mockito.<Auditable.STATE>any());
        doNothing().when(location).setUpdateAt(Mockito.<LocalDateTime>any());
        doNothing().when(location).setUpdatedBy(Mockito.<Long>any());
        doNothing().when(location).setLatitude(Mockito.<Double>any());
        doNothing().when(location).setLongitude(Mockito.<Double>any());
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
        LocationService locationService = mock(LocationService.class);
        when(locationService.save(Mockito.<Location>any())).thenReturn(location2);

        ArrayList<AuthenticationProvider> providers = new ArrayList<>();
        providers.add(new RunAsImplAuthenticationProvider());
        ProviderManager authenticationManager = new ProviderManager(providers);
        AuthUserRepository authUserRepository = mock(AuthUserRepository.class);
        Argon2PasswordEncoder passwordEncoder = new Argon2PasswordEncoder();
        JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
        AuthServiceImpl authService = new AuthServiceImpl(authenticationManager, authUserRepository, passwordEncoder,
                jwtTokenUtil, new SessionUser());

        CityRepository cityRepository = mock(CityRepository.class);
        CityService cityService = new CityService(cityRepository, locationService,
                new WeatherService(mock(WeatherRepository.class), mock(CityRepository.class)), authService,
                mock(AuthUserRepository.class));
        cityService.save(new CityCreateCriteria("Name", 10.0d, 10.0d));
    }

    /**
     * Method under test: {@link CityService#save(CityCreateCriteria)}
     */
    @Test
    void testSave6() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at dev.sheengo.weatherservice.service.LocationService.save(LocationService.java:26)
        //       at dev.sheengo.weatherservice.service.CityService.save(CityService.java:40)
        //   See https://diff.blue/R013 to resolve this issue.

        Location location = mock(Location.class);
        when(location.getLatitude()).thenReturn(10.0d);
        when(location.getLongitude()).thenReturn(10.0d);
        doNothing().when(location).setCreatedAt(Mockito.<LocalDateTime>any());
        doNothing().when(location).setCreatedBy(Mockito.<Long>any());
        doNothing().when(location).setDeleted(anyBoolean());
        doNothing().when(location).setId(Mockito.<Long>any());
        doNothing().when(location).setState(Mockito.<Auditable.STATE>any());
        doNothing().when(location).setUpdateAt(Mockito.<LocalDateTime>any());
        doNothing().when(location).setUpdatedBy(Mockito.<Long>any());
        doNothing().when(location).setLatitude(Mockito.<Double>any());
        doNothing().when(location).setLongitude(Mockito.<Double>any());
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

        Location location3 = new Location();
        location3.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location3.setCreatedBy(1L);
        location3.setDeleted(true);
        location3.setId(1L);
        location3.setLatitude(10.0d);
        location3.setLongitude(10.0d);
        location3.setState(Auditable.STATE.ACTIVE);
        location3.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location3.setUpdatedBy(1L);

        Weather weather = new Weather();
        weather.setAirDensity(10.0d);
        weather.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        weather.setCreatedBy(1L);
        weather.setDate(LocalDate.of(1970, 1, 1));
        weather.setDeleted(true);
        weather.setGust(10.0d);
        weather.setId(1L);
        weather.setLocation(location3);
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
        city.setLocation(location2);
        city.setName("Name");
        city.setState(Auditable.STATE.ACTIVE);
        city.setSubscriptions(new ArrayList<>());
        city.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        city.setUpdatedBy(1L);
        city.setWeather(weather);
        CityRepository cityRepository = mock(CityRepository.class);
        when(cityRepository.save(Mockito.<City>any())).thenReturn(city);

        Location location4 = new Location();
        location4.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location4.setCreatedBy(1L);
        location4.setDeleted(true);
        location4.setId(1L);
        location4.setLatitude(10.0d);
        location4.setLongitude(10.0d);
        location4.setState(Auditable.STATE.ACTIVE);
        location4.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location4.setUpdatedBy(1L);
        LocationService locationService = mock(LocationService.class);
        when(locationService.save(Mockito.<Location>any())).thenReturn(location4);

        Location location5 = new Location();
        location5.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location5.setCreatedBy(1L);
        location5.setDeleted(true);
        location5.setId(1L);
        location5.setLatitude(10.0d);
        location5.setLongitude(10.0d);
        location5.setState(Auditable.STATE.ACTIVE);
        location5.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location5.setUpdatedBy(1L);

        Weather weather2 = new Weather();
        weather2.setAirDensity(10.0d);
        weather2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        weather2.setCreatedBy(1L);
        weather2.setDate(LocalDate.of(1970, 1, 1));
        weather2.setDeleted(true);
        weather2.setGust(10.0d);
        weather2.setId(1L);
        weather2.setLocation(location5);
        weather2.setState(Auditable.STATE.ACTIVE);
        weather2.setTemperatureAvg(10.0d);
        weather2.setTemperatureMax(10.0d);
        weather2.setTemperatureMin(10.0d);
        weather2.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        weather2.setUpdatedBy(1L);
        weather2.setWindDirection((short) 1);
        weather2.setWindSpeed(10.0d);
        WeatherService weatherService = mock(WeatherService.class);
        when(weatherService.save(Mockito.<Weather>any())).thenReturn(weather2);

        ArrayList<AuthenticationProvider> providers = new ArrayList<>();
        providers.add(new RunAsImplAuthenticationProvider());
        ProviderManager authenticationManager = new ProviderManager(providers);
        AuthUserRepository authUserRepository = mock(AuthUserRepository.class);
        Argon2PasswordEncoder passwordEncoder = new Argon2PasswordEncoder();
        JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
        CityService cityService = new CityService(cityRepository, locationService, weatherService,
                new AuthServiceImpl(authenticationManager, authUserRepository, passwordEncoder, jwtTokenUtil,
                        new SessionUser()),
                mock(AuthUserRepository.class));
        assertSame(city, cityService.save(new CityCreateCriteria("Name", 10.0d, 10.0d)));
        verify(cityRepository).save(Mockito.<City>any());
        verify(location).setCreatedAt(Mockito.<LocalDateTime>any());
        verify(location).setCreatedBy(Mockito.<Long>any());
        verify(location).setDeleted(anyBoolean());
        verify(location).setId(Mockito.<Long>any());
        verify(location).setState(Mockito.<Auditable.STATE>any());
        verify(location).setUpdateAt(Mockito.<LocalDateTime>any());
        verify(location).setUpdatedBy(Mockito.<Long>any());
        verify(location).setLatitude(Mockito.<Double>any());
        verify(location).setLongitude(Mockito.<Double>any());
        verify(locationService).save(Mockito.<Location>any());
        verify(weatherService).save(Mockito.<Weather>any());
    }

    /**
     * Method under test: {@link CityService#getCitiesPagination(Pageable)}
     */
    @Test
    void testGetCitiesPagination() {
        PageImpl<City> pageImpl = new PageImpl<>(new ArrayList<>());
        when(cityRepository.findAll(Mockito.<Pageable>any())).thenReturn(pageImpl);
        Page<City> actualCitiesPagination = cityService.getCitiesPagination(null);
        assertSame(pageImpl, actualCitiesPagination);
        assertTrue(actualCitiesPagination.toList().isEmpty());
        verify(cityRepository).findAll(Mockito.<Pageable>any());
    }

    /**
     * Method under test: {@link CityService#getCitiesPagination(Pageable)}
     */
    @Test
    void testGetCitiesPagination2() {
        when(cityRepository.findAll(Mockito.<Pageable>any())).thenThrow(new RuntimeException("An error occurred"));
        assertThrows(RuntimeException.class, () -> cityService.getCitiesPagination(null));
        verify(cityRepository).findAll(Mockito.<Pageable>any());
    }

    /**
     * Method under test: {@link CityService#getCityByName(String)}
     */
    @Test
    void testGetCityByName() {
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
        when(cityRepository.findByName(Mockito.<String>any())).thenReturn(ofResult);
        Optional<City> actualCityByName = cityService.getCityByName("Oxford");
        assertSame(ofResult, actualCityByName);
        assertTrue(actualCityByName.isPresent());
        verify(cityRepository).findByName(Mockito.<String>any());
    }

    /**
     * Method under test: {@link CityService#getCityById(Long)}
     */
    @Test
    void testGetCityById() {
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
        when(cityRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        Optional<City> actualCityById = cityService.getCityById(1L);
        assertSame(ofResult, actualCityById);
        assertTrue(actualCityById.isPresent());
        verify(cityRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link CityService#update(CityUpdateDto)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdate() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at dev.sheengo.weatherservice.service.LocationService.save(LocationService.java:26)
        //       at dev.sheengo.weatherservice.service.CityService.update(CityService.java:80)
        //   See https://diff.blue/R013 to resolve this issue.

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
        when(cityRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        CityUpdateDto dto = new CityUpdateDto();
        dto.setAirDensity(10.0d);
        dto.setGust(10.0d);
        dto.setId(1L);
        dto.setLat(10.0d);
        dto.setLon(10.0d);
        dto.setName("Name");
        dto.setTempAVG(10.0d);
        dto.setTempMAX(10.0d);
        dto.setTempMIN(10.0d);
        dto.setWindDirection((short) 1);
        dto.setWindSpeed(10.0d);
        cityService.update(dto);
    }

    /**
     * Method under test: {@link CityService#update(CityUpdateDto)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdate2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at dev.sheengo.weatherservice.service.LocationService.save(LocationService.java:26)
        //       at dev.sheengo.weatherservice.service.CityService.update(CityService.java:80)
        //   See https://diff.blue/R013 to resolve this issue.

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
        when(cityRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        CityUpdateDto dto = new CityUpdateDto();
        dto.setAirDensity(10.0d);
        dto.setGust(10.0d);
        dto.setId(1L);
        dto.setLat(10.0d);
        dto.setLon(10.0d);
        dto.setName("Name");
        dto.setTempAVG(10.0d);
        dto.setTempMAX(10.0d);
        dto.setTempMIN(10.0d);
        dto.setWindDirection((short) 1);
        dto.setWindSpeed(10.0d);
        cityService.update(dto);
    }

    /**
     * Method under test: {@link CityService#update(CityUpdateDto)}
     */
    @Test
    void testUpdate3() {
        when(cityRepository.findById(Mockito.<Long>any())).thenReturn(Optional.empty());

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

        CityUpdateDto dto = new CityUpdateDto();
        dto.setAirDensity(10.0d);
        dto.setGust(10.0d);
        dto.setId(1L);
        dto.setLat(10.0d);
        dto.setLon(10.0d);
        dto.setName("Name");
        dto.setTempAVG(10.0d);
        dto.setTempMAX(10.0d);
        dto.setTempMIN(10.0d);
        dto.setWindDirection((short) 1);
        dto.setWindSpeed(10.0d);
        assertThrows(RuntimeException.class, () -> cityService.update(dto));
        verify(cityRepository).findById(Mockito.<Long>any());
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
    }

    /**
     * Method under test: {@link CityService#delete(Long)}
     */
    @Test
    void testDelete() {
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

        Location location3 = new Location();
        location3.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location3.setCreatedBy(1L);
        location3.setDeleted(true);
        location3.setId(1L);
        location3.setLatitude(10.0d);
        location3.setLongitude(10.0d);
        location3.setState(Auditable.STATE.ACTIVE);
        location3.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location3.setUpdatedBy(1L);

        Location location4 = new Location();
        location4.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location4.setCreatedBy(1L);
        location4.setDeleted(true);
        location4.setId(1L);
        location4.setLatitude(10.0d);
        location4.setLongitude(10.0d);
        location4.setState(Auditable.STATE.ACTIVE);
        location4.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location4.setUpdatedBy(1L);

        Weather weather2 = new Weather();
        weather2.setAirDensity(10.0d);
        weather2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        weather2.setCreatedBy(1L);
        weather2.setDate(LocalDate.of(1970, 1, 1));
        weather2.setDeleted(true);
        weather2.setGust(10.0d);
        weather2.setId(1L);
        weather2.setLocation(location4);
        weather2.setState(Auditable.STATE.ACTIVE);
        weather2.setTemperatureAvg(10.0d);
        weather2.setTemperatureMax(10.0d);
        weather2.setTemperatureMin(10.0d);
        weather2.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        weather2.setUpdatedBy(1L);
        weather2.setWindDirection((short) 1);
        weather2.setWindSpeed(10.0d);

        City city2 = new City();
        city2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        city2.setCreatedBy(1L);
        city2.setDeleted(true);
        city2.setId(1L);
        city2.setLocation(location3);
        city2.setName("Name");
        city2.setState(Auditable.STATE.ACTIVE);
        city2.setSubscriptions(new ArrayList<>());
        city2.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        city2.setUpdatedBy(1L);
        city2.setWeather(weather2);
        when(cityRepository.save(Mockito.<City>any())).thenReturn(city2);
        when(cityRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        cityService.delete(1L);
        verify(cityRepository).save(Mockito.<City>any());
        verify(cityRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link CityService#delete(Long)}
     */
    @Test
    void testDelete2() {
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
        when(cityRepository.save(Mockito.<City>any())).thenThrow(new RuntimeException("An error occurred"));
        when(cityRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(RuntimeException.class, () -> cityService.delete(1L));
        verify(cityRepository).save(Mockito.<City>any());
        verify(cityRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link CityService#getCitiesWeatherBySubscription(Pageable)}
     */
    @Test
    void testGetCitiesWeatherBySubscription() {
        when(cityRepository.findCitiesWeatherByUser(Mockito.<AuthUser>any(), Mockito.<Pageable>any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));

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
        when(authServiceImpl.getAuthUser()).thenReturn(authUser);
        assertTrue(cityService.getCitiesWeatherBySubscription(null).toList().isEmpty());
        verify(cityRepository).findCitiesWeatherByUser(Mockito.<AuthUser>any(), Mockito.<Pageable>any());
        verify(authServiceImpl).getAuthUser();
    }

    /**
     * Method under test: {@link CityService#getCitiesWeatherBySubscription(Pageable)}
     */
    @Test
    void testGetCitiesWeatherBySubscription2() {
        when(cityRepository.findCitiesWeatherByUser(Mockito.<AuthUser>any(), Mockito.<Pageable>any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        when(authServiceImpl.getAuthUser()).thenThrow(new RuntimeException("An error occurred"));
        assertThrows(RuntimeException.class, () -> cityService.getCitiesWeatherBySubscription(null));
        verify(authServiceImpl).getAuthUser();
    }

    /**
     * Method under test: {@link CityService#getCitiesWeatherBySubscription(Pageable)}
     */
    @Test
    void testGetCitiesWeatherBySubscription3() {
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

        ArrayList<City> content = new ArrayList<>();
        content.add(city);
        PageImpl<City> pageImpl = new PageImpl<>(content);
        when(cityRepository.findCitiesWeatherByUser(Mockito.<AuthUser>any(), Mockito.<Pageable>any()))
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
        when(authServiceImpl.getAuthUser()).thenReturn(authUser);
        assertEquals(1, cityService.getCitiesWeatherBySubscription(null).toList().size());
        verify(cityRepository).findCitiesWeatherByUser(Mockito.<AuthUser>any(), Mockito.<Pageable>any());
        verify(authServiceImpl).getAuthUser();
    }

    /**
     * Method under test: {@link CityService#getCitiesWeatherBySubscription(Pageable)}
     */
    @Test
    void testGetCitiesWeatherBySubscription4() {
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

        Location location3 = new Location();
        location3.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location3.setCreatedBy(0L);
        location3.setDeleted(false);
        location3.setId(2L);
        location3.setLatitude(0.5d);
        location3.setLongitude(0.5d);
        location3.setState(Auditable.STATE.INACTIVE);
        location3.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location3.setUpdatedBy(0L);

        Location location4 = new Location();
        location4.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location4.setCreatedBy(0L);
        location4.setDeleted(false);
        location4.setId(2L);
        location4.setLatitude(0.5d);
        location4.setLongitude(0.5d);
        location4.setState(Auditable.STATE.INACTIVE);
        location4.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location4.setUpdatedBy(0L);

        Weather weather2 = new Weather();
        weather2.setAirDensity(0.5d);
        weather2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        weather2.setCreatedBy(0L);
        weather2.setDate(LocalDate.of(1970, 1, 1));
        weather2.setDeleted(false);
        weather2.setGust(0.5d);
        weather2.setId(2L);
        weather2.setLocation(location4);
        weather2.setState(Auditable.STATE.INACTIVE);
        weather2.setTemperatureAvg(0.5d);
        weather2.setTemperatureMax(0.5d);
        weather2.setTemperatureMin(0.5d);
        weather2.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        weather2.setUpdatedBy(0L);
        weather2.setWindDirection((short) 0);
        weather2.setWindSpeed(0.5d);

        City city2 = new City();
        city2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        city2.setCreatedBy(0L);
        city2.setDeleted(false);
        city2.setId(2L);
        city2.setLocation(location3);
        city2.setName("42");
        city2.setState(Auditable.STATE.INACTIVE);
        city2.setSubscriptions(new ArrayList<>());
        city2.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        city2.setUpdatedBy(0L);
        city2.setWeather(weather2);

        ArrayList<City> content = new ArrayList<>();
        content.add(city2);
        content.add(city);
        PageImpl<City> pageImpl = new PageImpl<>(content);
        when(cityRepository.findCitiesWeatherByUser(Mockito.<AuthUser>any(), Mockito.<Pageable>any()))
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
        when(authServiceImpl.getAuthUser()).thenReturn(authUser);
        assertEquals(2, cityService.getCitiesWeatherBySubscription(null).toList().size());
        verify(cityRepository).findCitiesWeatherByUser(Mockito.<AuthUser>any(), Mockito.<Pageable>any());
        verify(authServiceImpl).getAuthUser();
    }

    /**
     * Method under test: {@link CityService#getCitiesWeatherBySubscription(Pageable)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetCitiesWeatherBySubscription5() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at dev.sheengo.weatherservice.service.CityService.getCitiesWeatherBySubscription(CityService.java:119)
        //   See https://diff.blue/R013 to resolve this issue.

        when(cityRepository.findCitiesWeatherByUser(Mockito.<AuthUser>any(), Mockito.<Pageable>any())).thenReturn(null);

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
        when(authServiceImpl.getAuthUser()).thenReturn(authUser);
        cityService.getCitiesWeatherBySubscription(null);
    }

    /**
     * Method under test: {@link CityService#getCitiesWeatherBySubscription(Pageable)}
     */
    @Test
    void testGetCitiesWeatherBySubscription6() {
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

        Location location3 = new Location();
        location3.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location3.setCreatedBy(1L);
        location3.setDeleted(true);
        location3.setId(1L);
        location3.setLatitude(10.0d);
        location3.setLongitude(10.0d);
        location3.setState(Auditable.STATE.ACTIVE);
        location3.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location3.setUpdatedBy(1L);

        Weather weather2 = new Weather();
        weather2.setAirDensity(10.0d);
        weather2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        weather2.setCreatedBy(1L);
        weather2.setDate(LocalDate.of(1970, 1, 1));
        weather2.setDeleted(true);
        weather2.setGust(10.0d);
        weather2.setId(1L);
        weather2.setLocation(location3);
        weather2.setState(Auditable.STATE.ACTIVE);
        weather2.setTemperatureAvg(10.0d);
        weather2.setTemperatureMax(10.0d);
        weather2.setTemperatureMin(10.0d);
        weather2.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        weather2.setUpdatedBy(1L);
        weather2.setWindDirection((short) 1);
        weather2.setWindSpeed(10.0d);
        City city = mock(City.class);
        when(city.getWeather()).thenReturn(weather2);
        when(city.getName()).thenReturn("Name");
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

        ArrayList<City> content = new ArrayList<>();
        content.add(city);
        PageImpl<City> pageImpl = new PageImpl<>(content);
        when(cityRepository.findCitiesWeatherByUser(Mockito.<AuthUser>any(), Mockito.<Pageable>any()))
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
        when(authServiceImpl.getAuthUser()).thenReturn(authUser);
        assertEquals(1, cityService.getCitiesWeatherBySubscription(null).toList().size());
        verify(cityRepository).findCitiesWeatherByUser(Mockito.<AuthUser>any(), Mockito.<Pageable>any());
        verify(city, atLeast(1)).getWeather();
        verify(city).getName();
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
        verify(authServiceImpl).getAuthUser();
    }

    /**
     * Method under test: {@link CityService#findByName(String)}
     */
    @Test
    void testFindByName() {
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
        when(cityRepository.findByName(Mockito.<String>any())).thenReturn(ofResult);
        Optional<City> actualFindByNameResult = cityService.findByName("Oxford");
        assertSame(ofResult, actualFindByNameResult);
        assertTrue(actualFindByNameResult.isPresent());
        verify(cityRepository).findByName(Mockito.<String>any());
    }

    /**
     * Method under test: {@link CityService#saveM(City)}
     */
    @Test
    void testSaveM() {
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
        ArrayList<AuthUser> subscriptions = new ArrayList<>();
        city.setSubscriptions(subscriptions);
        city.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        city.setUpdatedBy(1L);
        city.setWeather(weather);
        when(cityRepository.save(Mockito.<City>any())).thenReturn(city);

        Location location3 = new Location();
        location3.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location3.setCreatedBy(1L);
        location3.setDeleted(true);
        location3.setId(1L);
        location3.setLatitude(10.0d);
        location3.setLongitude(10.0d);
        location3.setState(Auditable.STATE.ACTIVE);
        location3.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location3.setUpdatedBy(1L);

        Location location4 = new Location();
        location4.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location4.setCreatedBy(1L);
        location4.setDeleted(true);
        location4.setId(1L);
        location4.setLatitude(10.0d);
        location4.setLongitude(10.0d);
        location4.setState(Auditable.STATE.ACTIVE);
        location4.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location4.setUpdatedBy(1L);

        Weather weather2 = new Weather();
        weather2.setAirDensity(10.0d);
        weather2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        weather2.setCreatedBy(1L);
        weather2.setDate(LocalDate.of(1970, 1, 1));
        weather2.setDeleted(true);
        weather2.setGust(10.0d);
        weather2.setId(1L);
        weather2.setLocation(location4);
        weather2.setState(Auditable.STATE.ACTIVE);
        weather2.setTemperatureAvg(10.0d);
        weather2.setTemperatureMax(10.0d);
        weather2.setTemperatureMin(10.0d);
        weather2.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        weather2.setUpdatedBy(1L);
        weather2.setWindDirection((short) 1);
        weather2.setWindSpeed(10.0d);

        City city2 = new City();
        city2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        city2.setCreatedBy(1L);
        city2.setDeleted(true);
        city2.setId(1L);
        city2.setLocation(location3);
        city2.setName("Name");
        city2.setState(Auditable.STATE.ACTIVE);
        city2.setSubscriptions(new ArrayList<>());
        city2.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        city2.setUpdatedBy(1L);
        city2.setWeather(weather2);
        cityService.saveM(city2);
        verify(cityRepository).save(Mockito.<City>any());
        assertTrue(city2.isDeleted());
        assertEquals("00:00", city2.getCreatedAt().toLocalTime().toString());
        assertSame(weather2, city2.getWeather());
        assertEquals(1L, city2.getUpdatedBy().longValue());
        assertEquals(1L, city2.getCreatedBy().longValue());
        assertSame(location3, city2.getLocation());
        assertEquals("Name", city2.getName());
        assertEquals("00:00", city2.getUpdateAt().toLocalTime().toString());
        assertEquals(1L, city2.getId().longValue());
        assertEquals(Auditable.STATE.ACTIVE, city2.getState());
        assertEquals(subscriptions, city2.getSubscriptions());
    }

    /**
     * Method under test: {@link CityService#getUserAndTheirSubscriptions(Pageable, AuthUser)}
     */
    @Test
    void testGetUserAndTheirSubscriptions() {
        when(cityRepository.findUserAndTheirSubscriptions(Mockito.<AuthUser>any(), Mockito.<Pageable>any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));

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
        assertTrue(cityService.getUserAndTheirSubscriptions(null, authUser).toList().isEmpty());
        verify(cityRepository).findUserAndTheirSubscriptions(Mockito.<AuthUser>any(), Mockito.<Pageable>any());
    }

    /**
     * Method under test: {@link CityService#getUserAndTheirSubscriptions(Pageable, AuthUser)}
     */
    @Test
    void testGetUserAndTheirSubscriptions2() {
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

        ArrayList<City> content = new ArrayList<>();
        content.add(city);
        PageImpl<City> pageImpl = new PageImpl<>(content);
        when(cityRepository.findUserAndTheirSubscriptions(Mockito.<AuthUser>any(), Mockito.<Pageable>any()))
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
        List<UserWeatherInfoDTO> toListResult = cityService.getUserAndTheirSubscriptions(null, authUser).toList();
        assertEquals(1, toListResult.size());
        UserWeatherInfoDTO getResult = toListResult.get(0);
        assertEquals("Name", getResult.getCityName());
        assertEquals("janedoe", getResult.getUsername());
        verify(cityRepository).findUserAndTheirSubscriptions(Mockito.<AuthUser>any(), Mockito.<Pageable>any());
    }

    /**
     * Method under test: {@link CityService#getUserAndTheirSubscriptions(Pageable, AuthUser)}
     */
    @Test
    void testGetUserAndTheirSubscriptions3() {
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

        Location location3 = new Location();
        location3.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location3.setCreatedBy(0L);
        location3.setDeleted(false);
        location3.setId(2L);
        location3.setLatitude(0.5d);
        location3.setLongitude(0.5d);
        location3.setState(Auditable.STATE.INACTIVE);
        location3.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location3.setUpdatedBy(0L);

        Location location4 = new Location();
        location4.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location4.setCreatedBy(0L);
        location4.setDeleted(false);
        location4.setId(2L);
        location4.setLatitude(0.5d);
        location4.setLongitude(0.5d);
        location4.setState(Auditable.STATE.INACTIVE);
        location4.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        location4.setUpdatedBy(0L);

        Weather weather2 = new Weather();
        weather2.setAirDensity(0.5d);
        weather2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        weather2.setCreatedBy(0L);
        weather2.setDate(LocalDate.of(1970, 1, 1));
        weather2.setDeleted(false);
        weather2.setGust(0.5d);
        weather2.setId(2L);
        weather2.setLocation(location4);
        weather2.setState(Auditable.STATE.INACTIVE);
        weather2.setTemperatureAvg(0.5d);
        weather2.setTemperatureMax(0.5d);
        weather2.setTemperatureMin(0.5d);
        weather2.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        weather2.setUpdatedBy(0L);
        weather2.setWindDirection((short) 0);
        weather2.setWindSpeed(0.5d);

        City city2 = new City();
        city2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        city2.setCreatedBy(0L);
        city2.setDeleted(false);
        city2.setId(2L);
        city2.setLocation(location3);
        city2.setName("42");
        city2.setState(Auditable.STATE.INACTIVE);
        city2.setSubscriptions(new ArrayList<>());
        city2.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        city2.setUpdatedBy(0L);
        city2.setWeather(weather2);

        ArrayList<City> content = new ArrayList<>();
        content.add(city2);
        content.add(city);
        PageImpl<City> pageImpl = new PageImpl<>(content);
        when(cityRepository.findUserAndTheirSubscriptions(Mockito.<AuthUser>any(), Mockito.<Pageable>any()))
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
        List<UserWeatherInfoDTO> toListResult = cityService.getUserAndTheirSubscriptions(null, authUser).toList();
        assertEquals(2, toListResult.size());
        UserWeatherInfoDTO getResult = toListResult.get(0);
        assertEquals("janedoe", getResult.getUsername());
        UserWeatherInfoDTO getResult2 = toListResult.get(1);
        assertEquals("janedoe", getResult2.getUsername());
        assertEquals("Name", getResult2.getCityName());
        assertEquals("42", getResult.getCityName());
        verify(cityRepository).findUserAndTheirSubscriptions(Mockito.<AuthUser>any(), Mockito.<Pageable>any());
    }

    /**
     * Method under test: {@link CityService#getUserAndTheirSubscriptions(Pageable, AuthUser)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetUserAndTheirSubscriptions4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at dev.sheengo.weatherservice.service.CityService.getUserAndTheirSubscriptions(CityService.java:131)
        //   See https://diff.blue/R013 to resolve this issue.

        when(cityRepository.findUserAndTheirSubscriptions(Mockito.<AuthUser>any(), Mockito.<Pageable>any()))
                .thenReturn(null);

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
        cityService.getUserAndTheirSubscriptions(null, authUser);
    }

    /**
     * Method under test: {@link CityService#getUserAndTheirSubscriptions(Pageable, AuthUser)}
     */
    @Test
    void testGetUserAndTheirSubscriptions5() {
        when(cityRepository.findUserAndTheirSubscriptions(Mockito.<AuthUser>any(), Mockito.<Pageable>any()))
                .thenThrow(new RuntimeException("An error occurred"));

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
        assertThrows(RuntimeException.class, () -> cityService.getUserAndTheirSubscriptions(null, authUser));
        verify(cityRepository).findUserAndTheirSubscriptions(Mockito.<AuthUser>any(), Mockito.<Pageable>any());
    }
}

