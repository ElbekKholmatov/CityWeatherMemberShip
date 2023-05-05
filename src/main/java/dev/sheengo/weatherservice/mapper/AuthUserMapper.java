package dev.sheengo.weatherservice.mapper;


import dev.sheengo.weatherservice.domains.AuthUser;
import dev.sheengo.weatherservice.dto.auth.AuthUserCreateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.lang.NonNull;

@Mapper(componentModel = "spring")
public interface AuthUserMapper {
    AuthUserMapper AUTH_USER_MAPPER= Mappers.getMapper(AuthUserMapper.class);
    AuthUser toEntity(@NonNull AuthUserCreateDTO dto);

}
