package ru.hse.restaurant.app.handler;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ru.hse.restaurant.data.api.model.Order;
import ru.hse.restaurant.data.api.model.OrderProduct;
import ru.hse.restaurant.data.api.model.Product;
import ru.hse.restaurant.data.api.repository.OrderContentRepository;
import ru.hse.restaurant.data.api.repository.OrderRepository;
import ru.hse.restaurant.data.api.repository.ProductRepository;

import java.sql.Timestamp;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OrderHandler {
    private final OrderRepository orderRepository;
    private final OrderContentRepository orderContentRepository;
    private final ProductRepository productRepository;
    @Async
    public void handleOrder(UUID order_id) {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
        System.out.println(order_id);
        Order order = orderRepository.findById(order_id);
        order.setStatus("in work");
        orderRepository.updateById(order);
        while (true) {

            Timestamp now = new Timestamp(System.currentTimeMillis());
            boolean flag = true;
            for (OrderProduct oc: orderContentRepository.findByOrderId(order_id)) {
                Product product = productRepository.findById(oc.getProduct_id());
                if (oc.getStart_time().getTime() + product.getTime() * 1000L > now.getTime()) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                order = orderRepository.findById(order_id);
                order.setStatus("ready");
                orderRepository.updateById(order);
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }
        }
    }

}
