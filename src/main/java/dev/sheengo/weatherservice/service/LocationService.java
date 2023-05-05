package dev.sheengo.weatherservice.service;

import com.maxmind.geoip2.DatabaseReader;
import dev.sheengo.weatherservice.domains.Location;
import dev.sheengo.weatherservice.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepository locationRepository;

    Location save(Location location) {
        return locationRepository.save(location);
    }

}
