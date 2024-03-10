package ru.hse.restaurant.app.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hse.restaurant.app.dto.Product;
import ru.hse.restaurant.app.exception.NotFoundById;

import ru.hse.restaurant.app.mapper.ProductMapper;
import ru.hse.restaurant.data.api.repository.ProductRepository;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
  private final UserService userService;

  private final ProductRepository productRepository;

  private final ProductMapper productMapper;

  @Override
  public List<Product> findProducts(String name) {
    var list = productRepository.findByName(name);

    return list.stream()
        .map(productMapper::dataModel2AppDto)
        .toList();
  }

  @Override
  public Product getProductById(UUID id) {
    if (!productRepository.existsById(id)) {
      throw new NotFoundById(id);
    }

    return productMapper.dataModel2AppDto(productRepository.findById(id));
  }

  @Override
  public Product createProduct(Product product) {
    return productMapper.dataModel2AppDto(
        productRepository.save(productMapper.appDto2DataModel(product)));
  }

  @Override
  public void editProduct(Product product) {
    if (!productRepository.existsById(product.getId())) {
      throw new NotFoundById(product.getId());
    }

    productRepository.updateById(productMapper.appDto2DataModel(product));
  }

  @Override
  public void deleteProduct(UUID id) {
    if (!productRepository.existsById(id)) {
      throw new NotFoundById(id);
    }

    productRepository.deleteById(id);
  }
}
