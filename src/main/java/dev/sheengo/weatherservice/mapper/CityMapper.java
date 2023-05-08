package dev.sheengo.weatherservice.mapper;


import dev.sheengo.weatherservice.domains.AuthUser;
import dev.sheengo.weatherservice.domains.City;
import dev.sheengo.weatherservice.dto.CityCreateDto;
import dev.sheengo.weatherservice.dto.UserWeatherInfoDTO;
import dev.sheengo.weatherservice.dto.WeatherInfoDTO;
import dev.sheengo.weatherservice.dto.auth.AuthUserCreateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.lang.NonNull;

@Mapper(componentModel = "spring")
public interface CityMapper {
    CityMapper CITY_MAPPER = Mappers.getMapper(CityMapper.class);

    City toEntity(@NonNull CityCreateDto dto);


    WeatherInfoDTO toDTO(@NonNull City city);

    WeatherInfoDTO toWeatherInfoDTO(City city);

    UserWeatherInfoDTO toUserWeatherInfoDTO(City city);
}
