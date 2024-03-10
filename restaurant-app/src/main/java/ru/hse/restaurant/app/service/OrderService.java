package ru.hse.restaurant.app.service;

import ru.hse.restaurant.app.dto.DetailedOrder;
import ru.hse.restaurant.app.dto.Order;
import ru.hse.restaurant.app.dto.OrderContent;

import java.sql.Timestamp;
import java.util.UUID;

public interface OrderService {
    Order createOrder(Order order, OrderContent orderContent, Timestamp time);
    void deleteOrder(UUID id);
    void changeOrderStatus(UUID id, String status);
    DetailedOrder getOrderById(UUID id);
    void addProductToOrder(OrderContent orderContent, Timestamp time);
    void removeProductFromOrder(OrderContent orderContent);
}
