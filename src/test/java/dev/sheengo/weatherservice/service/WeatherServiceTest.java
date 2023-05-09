package dev.sheengo.weatherservice.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.sheengo.weatherservice.domains.Auditable;
import dev.sheengo.weatherservice.domains.City;
import dev.sheengo.weatherservice.domains.Location;
import dev.sheengo.weatherservice.domains.Weather;
import dev.sheengo.weatherservice.dto.UpdateWeatherDTO;
import dev.sheengo.weatherservice.repository.CityRepository;
import dev.sheengo.weatherservice.repository.WeatherRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {WeatherService.class})
@ExtendWith(SpringExtension.class)
class WeatherServiceTest {
    @MockBean
    private CityRepository cityRepository;

    @MockBean
    private WeatherRepository weatherRepository;

    @Autowired
    private WeatherService weatherService;

    /**
     * Method under test: {@link WeatherService#save(Weather)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSave() {
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

        Weather weather = new Weather();
        weather.setAirDensity(10.0d);
        weather.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        weather.setCreatedBy(1L);
        weather.setDate(LocalDate.of(1970, 1, 1));
        weather.setDeleted(true);
        weather.setGust(10.0d);
        weather.setId(1L);
        weather.setLocation(location);
        weather.setState(Auditable.STATE.ACTIVE);
        weather.setTemperatureAvg(10.0d);
        weather.setTemperatureMax(10.0d);
        weather.setTemperatureMin(10.0d);
        weather.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        weather.setUpdatedBy(1L);
        weather.setWindDirection((short) 1);
        weather.setWindSpeed(10.0d);
        weatherService.save(weather);
    }

    /**
     * Method under test: {@link WeatherService#save(Weather)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSave2() {
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
        LocalDate date = LocalDate.of(1970, 1, 1);

        Weather weather = new Weather(date, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, (short) 1, new Location());
        weather.setAirDensity(10.0d);
        weather.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        weather.setCreatedBy(1L);
        weather.setDate(LocalDate.of(1970, 1, 1));
        weather.setDeleted(true);
        weather.setGust(10.0d);
        weather.setId(1L);
        weather.setLocation(location);
        weather.setState(Auditable.STATE.ACTIVE);
        weather.setTemperatureAvg(10.0d);
        weather.setTemperatureMax(10.0d);
        weather.setTemperatureMin(10.0d);
        weather.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        weather.setUpdatedBy(1L);
        weather.setWindDirection((short) 1);
        weather.setWindSpeed(10.0d);
        weatherService.save(weather);
    }

    /**
     * Method under test: {@link WeatherService#save(Weather)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSave3() {
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
        Weather weather = mock(Weather.class);
        when(weather.getLocation()).thenReturn(location2);
        doNothing().when(weather).setCreatedAt(Mockito.<LocalDateTime>any());
        doNothing().when(weather).setCreatedBy(Mockito.<Long>any());
        doNothing().when(weather).setDeleted(anyBoolean());
        doNothing().when(weather).setId(Mockito.<Long>any());
        doNothing().when(weather).setState(Mockito.<Auditable.STATE>any());
        doNothing().when(weather).setUpdateAt(Mockito.<LocalDateTime>any());
        doNothing().when(weather).setUpdatedBy(Mockito.<Long>any());
        doNothing().when(weather).setAirDensity(Mockito.<Double>any());
        doNothing().when(weather).setDate(Mockito.<LocalDate>any());
        doNothing().when(weather).setGust(Mockito.<Double>any());
        doNothing().when(weather).setLocation(Mockito.<Location>any());
        doNothing().when(weather).setTemperatureAvg(Mockito.<Double>any());
        doNothing().when(weather).setTemperatureMax(Mockito.<Double>any());
        doNothing().when(weather).setTemperatureMin(Mockito.<Double>any());
        doNothing().when(weather).setWindDirection(Mockito.<Short>any());
        doNothing().when(weather).setWindSpeed(Mockito.<Double>any());
        weather.setAirDensity(10.0d);
        weather.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        weather.setCreatedBy(1L);
        weather.setDate(LocalDate.of(1970, 1, 1));
        weather.setDeleted(true);
        weather.setGust(10.0d);
        weather.setId(1L);
        weather.setLocation(location);
        weather.setState(Auditable.STATE.ACTIVE);
        weather.setTemperatureAvg(10.0d);
        weather.setTemperatureMax(10.0d);
        weather.setTemperatureMin(10.0d);
        weather.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        weather.setUpdatedBy(1L);
        weather.setWindDirection((short) 1);
        weather.setWindSpeed(10.0d);
        weatherService.save(weather);
    }

    /**
     * Method under test: {@link WeatherService#updateWeatherInGivenCity(String, UpdateWeatherDTO)}
     */
    @Test
    void testUpdateWeatherInGivenCity() {
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
        when(cityRepository.findByName(Mockito.<String>any())).thenReturn(ofResult);

        UpdateWeatherDTO updateWeatherDTO = new UpdateWeatherDTO();
        updateWeatherDTO.setAirDensity(10.0d);
        updateWeatherDTO.setGust(10.0d);
        updateWeatherDTO.setTemperatureAvg(10.0d);
        updateWeatherDTO.setTemperatureMax(10.0d);
        updateWeatherDTO.setTemperatureMin(10.0d);
        updateWeatherDTO.setWindDirection((short) 1);
        updateWeatherDTO.setWindSpeed(10.0d);
        weatherService.updateWeatherInGivenCity("Oxford", updateWeatherDTO);
        verify(cityRepository).save(Mockito.<City>any());
        verify(cityRepository).findByName(Mockito.<String>any());
    }

    /**
     * Method under test: {@link WeatherService#updateWeatherInGivenCity(String, UpdateWeatherDTO)}
     */
    @Test
    void testUpdateWeatherInGivenCity2() {
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
        when(cityRepository.findByName(Mockito.<String>any())).thenReturn(ofResult);

        UpdateWeatherDTO updateWeatherDTO = new UpdateWeatherDTO();
        updateWeatherDTO.setAirDensity(10.0d);
        updateWeatherDTO.setGust(10.0d);
        updateWeatherDTO.setTemperatureAvg(10.0d);
        updateWeatherDTO.setTemperatureMax(10.0d);
        updateWeatherDTO.setTemperatureMin(10.0d);
        updateWeatherDTO.setWindDirection((short) 1);
        updateWeatherDTO.setWindSpeed(10.0d);
        assertThrows(RuntimeException.class, () -> weatherService.updateWeatherInGivenCity("Oxford", updateWeatherDTO));
        verify(cityRepository).save(Mockito.<City>any());
        verify(cityRepository).findByName(Mockito.<String>any());
    }
}

