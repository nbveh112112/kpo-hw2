package ru.hse.product.storage.app.mapper;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

  ru.hse.product.storage.api.dto.Product appDto2ApiDto(
      ru.hse.product.storage.app.dto.Product product);

  ru.hse.product.storage.app.dto.Product dataModel2AppDto(
      ru.hse.product.storage.data.api.model.Product product);
}
