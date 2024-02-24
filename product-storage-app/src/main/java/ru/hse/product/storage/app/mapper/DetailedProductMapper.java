package ru.hse.product.storage.app.mapper;

import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface DetailedProductMapper {

  ru.hse.product.storage.app.dto.DetailedProduct apiDto2AppDto(
      ru.hse.product.storage.api.dto.DetailedProduct detailedProduct);

  ru.hse.product.storage.data.api.model.Product appDto2DataModel(
      UUID id, ru.hse.product.storage.app.dto.DetailedProduct detailedProduct);

  ru.hse.product.storage.api.dto.DetailedProduct appDto2ApiDto(
      ru.hse.product.storage.app.dto.DetailedProduct detailedProduct);

  ru.hse.product.storage.app.dto.DetailedProduct dataModel2AppDto(
      ru.hse.product.storage.data.api.model.Product product);
}
