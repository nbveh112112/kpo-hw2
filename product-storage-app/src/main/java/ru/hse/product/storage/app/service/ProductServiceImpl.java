package ru.hse.product.storage.app.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hse.product.storage.app.dto.DetailedProduct;
import ru.hse.product.storage.app.dto.Product;
import ru.hse.product.storage.app.exception.ProductNotFoundById;
import ru.hse.product.storage.app.mapper.DetailedProductMapper;
import ru.hse.product.storage.app.mapper.ProductMapper;
import ru.hse.product.storage.data.api.repository.ProductRepository;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  private final ProductMapper productMapper;

  private final DetailedProductMapper detailedProductMapper;

  @Override
  public List<Product> findProducts(String name) {
    return productRepository.findByName(name).stream()
        .map(productMapper::dataModel2AppDto)
        .toList();
  }

  @Override
  public DetailedProduct getProductById(UUID id) {
    if (!productRepository.existsById(id)) {
      throw new ProductNotFoundById(id);
    }

    return detailedProductMapper.dataModel2AppDto(productRepository.findById(id));
  }

  @Override
  public Product createProduct(DetailedProduct detailedProduct) {
    return productMapper.dataModel2AppDto(
        productRepository.save(detailedProductMapper.appDto2DataModel(null, detailedProduct)));
  }

  @Override
  public void editProduct(UUID id, DetailedProduct detailedProduct) {
    if (!productRepository.existsById(id)) {
      throw new ProductNotFoundById(id);
    }

    productRepository.updateById(detailedProductMapper.appDto2DataModel(id, detailedProduct));
  }

  @Override
  public void deleteProduct(UUID id) {
    if (!productRepository.existsById(id)) {
      throw new ProductNotFoundById(id);
    }

    productRepository.deleteById(id);
  }
}
