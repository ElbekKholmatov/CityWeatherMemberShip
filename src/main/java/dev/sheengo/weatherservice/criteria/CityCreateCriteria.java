package dev.sheengo.weatherservice.criteria;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
public class CityCreateCriteria {
    @NotBlank(message = "name can not be blank")
    @Parameter(required = true, example = "Tashkent",name = "City Name")
    private String name;

    @NonNull
    @Parameter(required = true, example = "41.264651",name = "latitude")
    private Double lat;

    @NonNull
    @Parameter(required = true, example = "69.21627",name = "longitude")
    private Double lon;
}
