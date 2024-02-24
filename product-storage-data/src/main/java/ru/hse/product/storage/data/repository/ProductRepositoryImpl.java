package ru.hse.product.storage.data.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.hse.product.storage.data.api.model.Product;
import ru.hse.product.storage.data.api.repository.ProductRepository;

import java.util.List;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

  public static final RowMapper<Product> PRODUCT_ROW_MAPPER =
      (rs, i) -> {
        Product product = new Product();

        product.setId(UUID.fromString(rs.getString("id")));
        product.setName(rs.getString("name"));
        product.setDescription(rs.getString("description"));
        product.setCarbohydrate(rs.getInt("carbohydrate"));
        product.setFat(rs.getInt("fat"));
        product.setProtein(rs.getInt("protein"));
        product.setPrice(rs.getInt("price"));
        product.setImageUrl(rs.getString("image_url"));

        return product;
      };

  private final JdbcTemplate jdbcTemplate;

  @Override
  public List<Product> findByName(String name) {
    return jdbcTemplate.query(
        "select * from product where lower(name) like lower(concat('%', ?, '%'))",
        PRODUCT_ROW_MAPPER, name);
  }

  @Override
  public Product findById(UUID id) {
    return jdbcTemplate.queryForObject("select * from product where id=?", PRODUCT_ROW_MAPPER, id);
  }

  @Override
  public boolean existsById(UUID id) {
    return jdbcTemplate.queryForObject(
        "select exists(select * from product where id=?)", Boolean.class, id);
  }

  @Override
  public Product save(Product product) {
    return jdbcTemplate.queryForObject(
        "insert into product(id, name, description, image_url, price, protein, fat, carbohydrate) "
            + "values (uuid_generate_v4(), ?, ?, ?, ?, ?, ?, ?) "
            + "returning id, name, description, image_url, price, protein, fat, carbohydrate",
        PRODUCT_ROW_MAPPER,
        product.getName(),
        product.getDescription(),
        product.getImageUrl(),
        product.getPrice(),
        product.getProtein(),
        product.getFat(),
        product.getCarbohydrate());
  }

  @Override
  public void deleteById(UUID id) {
    jdbcTemplate.update("delete from product where id=?", id);
  }

  @Override
  public void updateById(Product product) {
    jdbcTemplate.update(
        "update product set "
            + "name = ?, "
            + "description = ?, "
            + "image_url  = ?, "
            + "price = ?, "
            + "protein = ?, "
            + "fat = ?, "
            + "carbohydrate = ? "
            + "where id = ?",
        product.getName(),
        product.getDescription(),
        product.getImageUrl(),
        product.getPrice(),
        product.getProtein(),
        product.getFat(),
        product.getCarbohydrate(),
        product.getId());
  }
}
