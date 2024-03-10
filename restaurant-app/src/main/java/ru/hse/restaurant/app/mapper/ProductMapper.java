package ru.hse.restaurant.app.mapper;

import org.mapstruct.Mapper;
import ru.hse.restaurant.app.dto.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
  ru.hse.restaurant.api.dto.Product appDto2ApiDto(
          Product product);

  Product apiDto2AppDto(
          ru.hse.restaurant.api.dto.Product product);

  Product dataModel2AppDto(
          ru.hse.restaurant.data.api.model.Product product);

  ru.hse.restaurant.data.api.model.Product appDto2DataModel(
          Product product);
}
