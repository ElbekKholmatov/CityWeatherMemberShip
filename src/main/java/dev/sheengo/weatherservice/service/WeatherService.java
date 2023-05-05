package dev.sheengo.weatherservice.service;

import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.sheengo.weatherservice.domains.Weather;
import dev.sheengo.weatherservice.repository.WeatherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private final WeatherRepository weatherRepository;

//    public Weather getWeather(double lat, double lon){
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        JsonNode jsonNode;
//        String path2 = "https://my.meteoblue.com/packages/basic-day_solar-day_wind-day?apikey=qbINcP94WyEuAfG6&lat=38.9277&lon=66.9963&asl=907&format=json&forecast_days=1";
//        try {
//            jsonNode = mapper.readTree(new URL(path2));;
//
//            JsonNode dataDay = jsonNode.get("data_day");
//
//            JsonNode date = getJsonNode(dataDay, "time");
//
//            JsonNode temperatureMax = getJsonNode(dataDay,"temperature_max");
//            JsonNode temperatureAvg = getJsonNode(dataDay,"temperature_mean");
//            JsonNode temperatureMin = getJsonNode(dataDay,"temperature_min");
//
//            JsonNode airDensityAvg = getJsonNode(dataDay,"airdensity_mean");
//
//            JsonNode gustAvg = getJsonNode(dataDay,"gust_mean");
//
//            JsonNode windSpeedAvg = getJsonNode(dataDay,"windspeed_80m_mean");
//
//            JsonNode windDirection = getJsonNode(dataDay,"winddirection_80m");
//            LocalDate date1 = LocalDate.parse(date.asText());
//
//
////            return weatherRepository.save(Weather.childBuilder()
////                    .date(date1)
////                    .airDensity(airDensityAvg.asDouble())
////                    .gust(gustAvg.asDouble())
////                    .windSpeed(windSpeedAvg.asDouble())
////                    .windDirection(Short.parseShort(windDirection.asText()))
////                    .temperatureMax(temperatureMax.asDouble())
////                    .temperatureMin(temperatureMin.asDouble())
////                    .temperatureAvg(temperatureAvg.asDouble())
////                    .build());
//
//            return Weather.childBuilder()
//                    .date(date1)
//                    .airDensity(airDensityAvg.asDouble())
//                    .gust(gustAvg.asDouble())
//                    .windSpeed(windSpeedAvg.asDouble())
//                    .windDirection(Short.parseShort(windDirection.asText()))
//                    .temperatureMax(temperatureMax.asDouble())
//                    .temperatureMin(temperatureMin.asDouble())
//                    .temperatureAvg(temperatureAvg.asDouble())
//                    .build();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    private JsonNode getJsonNode(JsonNode node,String path) throws IOException {
        return node.get(path).get(0);
    }

    public Weather save(Weather weather) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JsonNode jsonNode;
        try {
            String path2 = "https://my.meteoblue.com/packages/basic-day_solar-day_wind-day?apikey=qbINcP94WyEuAfG6&lat="+weather.getLocation().getLatitude()+"&lon="+weather.getLocation().getLongitude()+"&asl=907&format=json&forecast_days=1";
            jsonNode = mapper.readTree(new URL(path2));;

            JsonNode dataDay = jsonNode.get("data_day");

            JsonNode date = getJsonNode(dataDay, "time");

            JsonNode temperatureMax = getJsonNode(dataDay,"temperature_max");
            JsonNode temperatureAvg = getJsonNode(dataDay,"temperature_mean");
            JsonNode temperatureMin = getJsonNode(dataDay,"temperature_min");

            JsonNode airDensityAvg = getJsonNode(dataDay,"airdensity_mean");

            JsonNode gustAvg = getJsonNode(dataDay,"gust_mean");

            JsonNode windSpeedAvg = getJsonNode(dataDay,"windspeed_80m_mean");

            JsonNode windDirection = getJsonNode(dataDay,"winddirection_80m");
            LocalDate date1 = LocalDate.parse(date.asText());

            return weatherRepository.save(Weather.childBuilder()
                    .date(date1)
                    .airDensity(airDensityAvg.asDouble())
                    .gust(gustAvg.asDouble())
                    .windSpeed(windSpeedAvg.asDouble())
                    .windDirection(Short.parseShort(windDirection.asText()))
                    .temperatureMax(temperatureMax.asDouble())
                    .temperatureMin(temperatureMin.asDouble())
                    .temperatureAvg(temperatureAvg.asDouble())
                    .location(weather.getLocation())
                    .build());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}