package dev.sheengo.weatherservice.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.sheengo.weatherservice.domains.Auditable;
import dev.sheengo.weatherservice.domains.Location;
import dev.sheengo.weatherservice.repository.LocationRepository;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {LocationService.class})
@ExtendWith(SpringExtension.class)
class LocationServiceTest {
    @MockBean
    private LocationRepository locationRepository;

    @Autowired
    private LocationService locationService;

    /**
     * Method under test: {@link LocationService#save(Location)}
     */
    @Test
    void testSave() {
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
        when(locationRepository.save(Mockito.<Location>any())).thenReturn(location);

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
        assertSame(location, locationService.save(location2));
        verify(locationRepository).save(Mockito.<Location>any());
    }
}

