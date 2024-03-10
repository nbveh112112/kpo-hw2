package ru.hse.restaurant.app.mapper;

import org.mapstruct.Mapper;
import ru.hse.restaurant.app.dto.Order;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    ru.hse.restaurant.api.dto.Order appDto2ApiDto(
            Order order);

    Order apiDto2AppDto(
            ru.hse.restaurant.api.dto.Order order);

    Order dataModel2AppDto(
            ru.hse.restaurant.data.api.model.Order order);

    ru.hse.restaurant.data.api.model.Order appDto2DataModel(
            Order order);
}
