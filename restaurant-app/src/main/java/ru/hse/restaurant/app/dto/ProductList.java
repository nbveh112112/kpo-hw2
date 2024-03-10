package ru.hse.restaurant.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.hse.restaurant.api.dto.Product;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductList {

  private List<Product> products;
}
