package ru.hse.restaurant.api;

import ru.hse.product.storage.api.dto.*;
import ru.hse.restaurant.api.dto.*;

import java.util.UUID;

public interface ProductApi {

  ProductList findProducts(RequestString name);

  Product getProductById(RequestUUID id);

  Product createProduct(RequestProduct product);

  void editProduct(UUID id, RequestProduct product);

  void deleteProduct(RequestUUID id);
}
