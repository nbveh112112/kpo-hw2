package ru.hse.restaurant.app.controller;

import java.util.UUID;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.hse.restaurant.api.ProductApi;
import ru.hse.restaurant.api.dto.*;
import ru.hse.restaurant.app.exception.Forbidden;
import ru.hse.restaurant.app.exception.Unauthorized;
import ru.hse.restaurant.app.mapper.ProductMapper;
import ru.hse.restaurant.app.service.ProductService;
import ru.hse.restaurant.app.service.RightsService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController implements ProductApi {

  private final ProductService productService;
  private final RightsService rightsService;
  private final ProductMapper productMapper;


  @Override
  @GetMapping
  public ProductList findProducts(RequestString name) {
    if (!rightsService.isUser(name.getToken())) {
      throw new Unauthorized();
    }
    if (!rightsService.isAdmin(name.getToken())) {
      throw new Forbidden(name.getToken());
    }
    return new ProductList(productService.findProducts(name.getData()).stream().map(productMapper::appDto2ApiDto).toList());
  }

  @Override
  @GetMapping("/{id}")
  public Product getProductById(@PathVariable RequestUUID id) {
    if (!rightsService.isUser(id.getToken())) {
      throw new Unauthorized();
    }
    if (!rightsService.isAdmin(id.getToken())) {
      throw new Forbidden(id.getToken());
    }
    return productMapper.appDto2ApiDto(productService.getProductById(id.getData()));
  }

  @Override
  @PostMapping
  public Product createProduct(@RequestBody RequestProduct product) {
    if (!rightsService.isUser(product.getToken())) {
      throw new Unauthorized();
    }
    if (!rightsService.isAdmin(product.getToken())) {
      throw new Forbidden(product.getToken());
    }
    return productMapper.appDto2ApiDto(
        productService.createProduct(productMapper.apiDto2AppDto(new Product(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getTime()))));
  }


  @Override
  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void editProduct(@PathVariable UUID id, @RequestBody RequestProduct product) {
    if (!rightsService.isUser(product.getToken())) {
      throw new Unauthorized();
    }
    if (!rightsService.isAdmin(product.getToken())) {
      throw new Forbidden(product.getToken());
    }
    productService.editProduct(productMapper.apiDto2AppDto(new Product(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getTime())));
  }

  @Override
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteProduct(@PathVariable RequestUUID id) {
    if (!rightsService.isUser(id.getToken())) {
      throw new Unauthorized();
    }
    if (!rightsService.isAdmin(id.getToken())) {
      throw new Forbidden(id.getToken());
    }
    productService.deleteProduct(id.getData());
  }
}
