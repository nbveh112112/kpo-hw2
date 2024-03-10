package ru.hse.restaurant.data.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.hse.restaurant.data.api.model.Order;
import ru.hse.restaurant.data.api.repository.OrderRepository;

import java.util.List;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

    public static final RowMapper<Order> ORDER_ROW_MAPPER =
            (rs, i) -> {
                Order order = new Order();

                order.setId(UUID.fromString(rs.getString("id")));
                order.setUser_id(UUID.fromString(rs.getString("owner_id")));
                order.setStatus(rs.getString("status"));
                return order;
            };

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Order save(Order order) {
        return jdbcTemplate.queryForObject(
                "insert into orders(id, owner_id, status) "
                        + "values (uuid_generate_v4(), ?, ?) "
                        + "returning id, owner_id, status",
                ORDER_ROW_MAPPER,
                order.getUser_id(),
                order.getStatus());
    }

    @Override
    public void updateById(Order order) {
        jdbcTemplate.update(
                "update orders set "
                        + "owner_id = ?, "
                        + "status = ? "
                        + "where id = ?",
                order.getUser_id(),
                order.getStatus(),
                order.getId());
    }

    @Override
    public Order findById(UUID id) {
        try {
            return jdbcTemplate.queryForObject("select id, owner_id, status from orders where id=?", ORDER_ROW_MAPPER, id);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Order> findByUserId(UUID userId) {
        return jdbcTemplate.query(
                "select * from orders where user_id = ?",
                ORDER_ROW_MAPPER, userId);
    }

    @Override
    public void deleteById(UUID id) {
        jdbcTemplate.update("delete from orders where id=?", id);
    }

    @Override
    public boolean existsById(UUID id) {
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(
                "select exists(select * from orders where id=?)", Boolean.class, id));
    }

    @Override
    public void updateStatusById(UUID id, String status) {
        jdbcTemplate.update("update orders set status = ? where id = ?", status, id);
    }
}
