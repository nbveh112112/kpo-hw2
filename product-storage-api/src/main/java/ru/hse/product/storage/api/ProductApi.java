package ru.hse.product.storage.api;

import ru.hse.product.storage.api.dto.DetailedProduct;
import ru.hse.product.storage.api.dto.Product;
import ru.hse.product.storage.api.dto.ProductList;

import java.util.List;
import java.util.UUID;

public interface ProductApi {

  ProductList findProducts(String name, List<UUID> ids);

  DetailedProduct getProductById(UUID id);

  Product createProduct(DetailedProduct detailedProduct);

  void editProduct(UUID id, DetailedProduct detailedProduct);

  void deleteProduct(UUID id);
}
