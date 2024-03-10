package ru.hse.restaurant.app.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hse.restaurant.app.dto.DetailedOrder;
import ru.hse.restaurant.app.dto.Order;
import ru.hse.restaurant.app.dto.OrderContent;
import ru.hse.restaurant.app.exception.Gone;
import ru.hse.restaurant.app.exception.NotFoundById;
import ru.hse.restaurant.app.handler.OrderHandler;
import ru.hse.restaurant.app.mapper.OrderMapper;
import ru.hse.restaurant.app.mapper.OrderProductMapper;
import ru.hse.restaurant.data.api.repository.MenuRepository;
import ru.hse.restaurant.data.api.repository.OrderContentRepository;
import ru.hse.restaurant.data.api.repository.OrderRepository;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;
@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final OrderHandler orderHandler;
    private final OrderRepository orderRepository;
    private final OrderContentRepository orderContentRepository;
    private final OrderMapper orderMapper;
    private final OrderProductMapper orderProductMapper;
    private final MenuRepository menuRepository;
    @Override
    public Order createOrder(Order order, OrderContent orderContent, Timestamp time) {
        order.setStatus("starting");
        order = orderMapper.dataModel2AppDto(
                orderRepository.save(orderMapper.appDto2DataModel(order)));
        orderContent.setOrder_id(order.getId());
        addProductToOrder(orderContent, time);
        orderHandler.handleOrder(order.getId());
        return order;
    }

    @Override
    public void deleteOrder(UUID id) {
        orderRepository.deleteById(id);
    }

    @Override
    public void changeOrderStatus(UUID id, String status) {
        orderRepository.updateStatusById(id, status);
    }

    @Override
    public DetailedOrder getOrderById(UUID id) {
        Order order = orderMapper.dataModel2AppDto(orderRepository.findById(id));
        if (order == null) {
            throw new NotFoundById(id);
        }
        return new DetailedOrder(order.getId(), order.getUser_id(), order.getStatus(), orderContentRepository.findByOrderId(id).stream().map(orderProductMapper::dataModel2AppDto).toList());
    }

    @Override
    public void addProductToOrder(OrderContent orderContent, Timestamp time) {
        if (orderContent == null) {
            return;
        }
        if (orderRepository.findById(orderContent.getOrder_id()) == null) {
            return;
        }
        if (Objects.equals(orderRepository.findById(orderContent.getOrder_id()).getStatus(), "ready")) {
            throw new Gone(orderContent.getOrder_id());
        }
        for (UUID productId : orderContent.getProduct_ids()) {
            if (menuRepository.exist(productId)) {
                orderContentRepository.save(orderContent.getOrder_id(), productId, time);
            }
        }
    }

    @Override
    public void removeProductFromOrder(OrderContent orderContent) {
        if (orderContent == null) {
            return;
        }
        if (orderRepository.findById(orderContent.getOrder_id()) == null) {
            return;
        }
        if (Objects.equals(orderRepository.findById(orderContent.getOrder_id()).getStatus(), "ready")) {
            throw new Gone(orderContent.getOrder_id());
        }
        for (UUID productId : orderContent.getProduct_ids()) {
            orderContentRepository.deleteByOrderId(orderContent.getOrder_id(), productId);
        }
    }
}
