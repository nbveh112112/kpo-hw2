package ru.hse.product.storage.app.service;

import ru.hse.product.storage.app.dto.DetailedProduct;
import ru.hse.product.storage.app.dto.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {

  List<Product> findProducts(String name, List<UUID> ids);

  DetailedProduct getProductById(UUID id);

  Product createProduct(DetailedProduct detailedProduct);

  void editProduct(UUID id, DetailedProduct detailedProduct);

  void deleteProduct(UUID id);
}
