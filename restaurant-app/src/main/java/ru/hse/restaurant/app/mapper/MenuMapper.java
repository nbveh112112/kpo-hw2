package ru.hse.restaurant.app.mapper;

import org.mapstruct.Mapper;
import ru.hse.restaurant.app.dto.Menu;

@Mapper(componentModel = "spring")
public interface MenuMapper {
    ru.hse.restaurant.api.dto.Menu appDto2ApiDto(
            Menu menu);

    Menu apiDto2AppDto(
            ru.hse.restaurant.api.dto.Menu menu);
}
