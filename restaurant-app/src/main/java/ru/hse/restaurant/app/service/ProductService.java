package ru.hse.restaurant.app.service;

import ru.hse.restaurant.app.dto.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {

  List<Product> findProducts(String name);

  Product getProductById(UUID id);

  Product createProduct(Product detailedProduct);

  void editProduct(Product product);

  void deleteProduct(UUID id);
}
