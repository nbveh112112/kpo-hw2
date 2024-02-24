package ru.hse.product.storage.app.controller;

import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.hse.product.storage.api.ProductApi;
import ru.hse.product.storage.api.dto.DetailedProduct;
import ru.hse.product.storage.api.dto.Product;
import ru.hse.product.storage.api.dto.ProductList;
import ru.hse.product.storage.app.mapper.DetailedProductMapper;
import ru.hse.product.storage.app.mapper.ProductMapper;
import ru.hse.product.storage.app.service.ProductService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController implements ProductApi {

  private final ProductService productService;

  private final ProductMapper productMapper;

  private final DetailedProductMapper detailedProductMapper;

  @Override
  @GetMapping
  public ProductList findProducts(@RequestParam(required = false, defaultValue = "") String name) {
    return new ProductList(
        productService.findProducts(name).stream().map(productMapper::appDto2ApiDto).toList());
  }

  @Override
  @GetMapping("/{id}")
  public DetailedProduct getProductById(@PathVariable UUID id) {
    return detailedProductMapper.appDto2ApiDto(productService.getProductById(id));
  }

  @Override
  @PostMapping
  public Product createProduct(@RequestBody DetailedProduct detailedProduct) {
    return productMapper.appDto2ApiDto(
        productService.createProduct(detailedProductMapper.apiDto2AppDto(detailedProduct)));
  }

  @Override
  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void editProduct(@PathVariable UUID id, @RequestBody DetailedProduct detailedProduct) {
    productService.editProduct(id, detailedProductMapper.apiDto2AppDto(detailedProduct));
  }

  @Override
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteProduct(@PathVariable UUID id) {
    productService.deleteProduct(id);
  }
}
