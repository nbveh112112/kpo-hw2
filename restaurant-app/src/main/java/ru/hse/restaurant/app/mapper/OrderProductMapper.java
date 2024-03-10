package ru.hse.restaurant.app.mapper;

import org.mapstruct.Mapper;
import ru.hse.restaurant.app.dto.OrderProduct;

@Mapper(componentModel = "spring")
public interface OrderProductMapper {
    ru.hse.restaurant.api.dto.OrderProduct appDto2ApiDto(
            OrderProduct orderProduct);

    OrderProduct apiDto2AppDto(
            ru.hse.restaurant.api.dto.OrderProduct orderProduct);

    OrderProduct dataModel2AppDto(
            ru.hse.restaurant.data.api.model.OrderProduct orderProduct);

    ru.hse.restaurant.data.api.model.OrderProduct appDto2DataModel(
            OrderProduct orderProduct);
}
