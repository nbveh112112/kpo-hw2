package ru.hse.product.storage.app.mapper;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderProductMapper {
    ru.hse.product.storage.api.dto.OrderProduct appDto2ApiDto(
            ru.hse.product.storage.app.dto.OrderProduct orderProduct);

    ru.hse.product.storage.app.dto.OrderProduct apiDto2AppDto(
            ru.hse.product.storage.api.dto.OrderProduct orderProduct);

    ru.hse.product.storage.app.dto.OrderProduct dataModel2AppDto(
            ru.hse.product.storage.data.api.model.OrderProduct orderProduct);

    ru.hse.product.storage.data.api.model.OrderProduct appDto2DataModel(
            ru.hse.product.storage.app.dto.OrderProduct orderProduct);
}
