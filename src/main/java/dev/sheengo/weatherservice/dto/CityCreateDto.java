package dev.sheengo.weatherservice.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link dev.sheengo.weatherservice.domains.City} entity
 */
@Data
public class CityCreateDto implements Serializable {
    private final String name;
    private final double lat;
    private final double lon;


}