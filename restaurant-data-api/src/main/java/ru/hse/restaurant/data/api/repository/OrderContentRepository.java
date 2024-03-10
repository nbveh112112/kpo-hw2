package ru.hse.restaurant.data.api.repository;

import ru.hse.restaurant.data.api.model.OrderProduct;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public interface OrderContentRepository {

    UUID save(UUID orderId, UUID productId, Timestamp time);

    void delete(UUID id);

    void deleteByOrderId(UUID orderId, UUID productId);

    List<OrderProduct> findByOrderId(UUID orderId);
}
