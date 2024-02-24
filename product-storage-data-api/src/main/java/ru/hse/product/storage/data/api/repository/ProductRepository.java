package ru.hse.product.storage.data.api.repository;

import ru.hse.product.storage.data.api.model.Product;

import java.util.List;
import java.util.UUID;

public interface ProductRepository {

  List<Product> findByName(String name);

  Product findById(UUID id);

  boolean existsById(UUID id);

  Product save(Product product);

  void deleteById(UUID id);

  void updateById(Product product);
}
