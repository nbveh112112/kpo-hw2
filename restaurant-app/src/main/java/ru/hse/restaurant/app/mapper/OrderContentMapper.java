package ru.hse.restaurant.app.mapper;

import org.mapstruct.Mapper;
import ru.hse.restaurant.app.dto.OrderContent;

@Mapper(componentModel = "spring")
public interface OrderContentMapper {
    ru.hse.restaurant.api.dto.OrderContent appDto2ApiDto(
            OrderContent orderContent);

    OrderContent apiDto2AppDto(
            ru.hse.restaurant.api.dto.OrderContent orderContent);
}
