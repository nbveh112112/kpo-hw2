package ru.hse.restaurant.app.mapper;

import org.mapstruct.Mapper;
import ru.hse.restaurant.app.dto.DetailedOrder;

@Mapper(componentModel = "spring")
public interface DetailedOrderMapper {
    ru.hse.restaurant.api.dto.DetailedOrder appDto2ApiDto(
            DetailedOrder detailedOrder);

    DetailedOrder apiDto2AppDto(
            ru.hse.restaurant.api.dto.DetailedOrder detailedOrder);
}
