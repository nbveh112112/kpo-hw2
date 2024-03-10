package ru.hse.restaurant.data.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.hse.restaurant.data.api.model.OrderProduct;
import ru.hse.restaurant.data.api.repository.OrderContentRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class OrderContentRepositoryImpl implements OrderContentRepository {

    private final JdbcTemplate jdbcTemplate;
    public static final RowMapper<OrderProduct> ORDER_PRODUCT_ROW_MAPPER =
            (rs, i) -> {
                OrderProduct product = new OrderProduct();
                product.setProduct_id(UUID.fromString(rs.getString("product_id")));
                product.setStart_time(rs.getTimestamp("start_time"));
                return product;
            };

    @Override
    public UUID save(UUID orderId, UUID productId, Timestamp time) {
        return jdbcTemplate.queryForObject(
                "insert into order_content(id, order_id, product_id, start_time) "
                        + "values (uuid_generate_v4() ,?, ?, ?) "
                        + "returning id",
                UUID.class,
                orderId,
                productId,
                time);
    }

    @Override
    public void delete(UUID id) {
        jdbcTemplate.update("delete from order_content where id = ?", id);
    }

    @Override
    public void deleteByOrderId(UUID orderId, UUID productId) {
        jdbcTemplate.update("delete from order_content where order_id = ? and product_id = ?", orderId, productId);
    }

    @Override
    public List<OrderProduct> findByOrderId(UUID orderId) {
        return jdbcTemplate.query("select product_id, start_time from order_content where order_id = ?", ORDER_PRODUCT_ROW_MAPPER, orderId);
    }
}
