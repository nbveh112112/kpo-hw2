package ru.hse.product.storage.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ProductList {

  private List<Product> products;
}
