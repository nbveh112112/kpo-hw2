package ru.hse.restaurant.data.api.repository;

import ru.hse.restaurant.data.api.model.Order;

import java.util.List;
import java.util.UUID;

public interface OrderRepository {
    Order save(Order order);

    void updateById(Order order);

    Order findById(UUID id);

    List<Order> findByUserId(UUID userId);

    void deleteById(UUID id);

    boolean existsById(UUID id);

    void updateStatusById(UUID id, String status);
}
