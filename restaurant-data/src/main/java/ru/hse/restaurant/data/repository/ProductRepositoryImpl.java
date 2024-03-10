package ru.hse.restaurant.data.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.hse.restaurant.data.api.model.Product;
import ru.hse.restaurant.data.api.repository.ProductRepository;

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
        product.setPrice(rs.getInt("price"));
        product.setTime(rs.getInt("time"));
        return product;
      };

  private final JdbcTemplate jdbcTemplate;

  @Override
  public List<Product> findByName(String name) {
    return jdbcTemplate.query(
        "select * from products where lower(name) like lower(concat('%', ?, '%'))",
        PRODUCT_ROW_MAPPER, name);
  }

  public List<Product> findByNameAndIdIn(String name, List<UUID> ids) {
    return jdbcTemplate.query(
        "select * from products where lower(name) like lower(concat('%',?,'%')) and id in ?",
        PRODUCT_ROW_MAPPER, name, ids);
  }

  @Override
  public Product findById(UUID id) {
    return jdbcTemplate.queryForObject("select * from products where id=?", PRODUCT_ROW_MAPPER, id);
  }

  @Override
  public boolean existsById(UUID id) {
    return Boolean.TRUE.equals(jdbcTemplate.queryForObject(
            "select exists(select * from products where id=?)", Boolean.class, id));
  }

  @Override
  public Product save(Product product) {
    return jdbcTemplate.queryForObject(
        "insert into products(id, name, description, price, time) "
            + "values (uuid_generate_v4(), ?, ?, ?, ?) "
            + "returning id, name, description, price, time",
        PRODUCT_ROW_MAPPER,
        product.getName(),
        product.getDescription(),
        product.getPrice(),
        product.getTime());
  }

  @Override
  public void deleteById(UUID id) {
    jdbcTemplate.update("delete from products where id=?", id);
  }

  @Override
  public void updateById(Product product) {
    jdbcTemplate.update(
        "update products set "
            + "name = ?, "
            + "description = ?, "
            + "price = ?, "
            + "time = ?,"
            + "where id = ?",
        product.getName(),
        product.getDescription(),
        product.getPrice(),
        product.getTime(),
        product.getId());
  }
}
