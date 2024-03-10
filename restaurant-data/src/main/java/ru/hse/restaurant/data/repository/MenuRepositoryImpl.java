package ru.hse.restaurant.data.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.hse.restaurant.data.api.repository.MenuRepository;

import java.util.List;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class MenuRepositoryImpl implements MenuRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void save(UUID productId) {
        jdbcTemplate.update(
                "insert into menu(product_id) "
                        + "values (?)",
                productId);
    }

    @Override
    public void delete(UUID productId) {
        jdbcTemplate.update("delete from menu where product_id = ?", productId);
    }

    @Override
    public List<UUID> findAll() {
        return jdbcTemplate.queryForList("select product_id from menu", UUID.class);
    }

    @Override
    public boolean exist(UUID productiId) {
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(
                "select exists(select * from menu where id=?)", Boolean.class, productiId));
    }
}
