package ru.hse.restaurant.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.hse.restaurant.api.OrderApi;
import ru.hse.restaurant.api.dto.*;
import ru.hse.restaurant.app.dto.Token;
import ru.hse.restaurant.app.exception.Forbidden;
import ru.hse.restaurant.app.exception.Unauthorized;
import ru.hse.restaurant.app.mapper.DetailedOrderMapper;
import ru.hse.restaurant.app.mapper.OrderContentMapper;
import ru.hse.restaurant.app.mapper.OrderMapper;
import ru.hse.restaurant.app.service.OrderService;
import ru.hse.restaurant.app.service.RightsService;
import ru.hse.restaurant.app.service.UserService;

import java.sql.Timestamp;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController implements OrderApi {
    private final OrderService orderService;
    private final UserService userService;
    private final RightsService rightsService;
    private final OrderMapper orderMapper;
    private final OrderContentMapper orderContentMapper;
    private final DetailedOrderMapper detailedOrderMapper;
    @Override
    @PostMapping
    public Order orderCreate(@RequestBody RequestOrderContent request) {
        Order order = new Order();
        order.setUser_id(userService.getUserId(new Token(request.getToken())));
        order = orderMapper.appDto2ApiDto(orderService.createOrder(orderMapper.apiDto2AppDto(order), orderContentMapper.apiDto2AppDto(new OrderContent(request.getOrder_id(), request.getProduct_ids())), new Timestamp(System.currentTimeMillis())));
        return order;
    }

    @Override
    @PostMapping("/add")
    public void orderAddProduct(@RequestBody RequestOrderContent request) {
        if (!rightsService.isUser(request.getToken())) {
            throw new Unauthorized();
        } else {
            if(!rightsService.isAdmin(request.getToken())) {
                UUID owner_id = orderService.getOrderById(request.getOrder_id()).getUser_id();
                if (userService.getUserId(new Token(request.getToken())) != owner_id) {
                    throw new Forbidden(request.getToken());
                }
            }
        }
        orderService.addProductToOrder(orderContentMapper.apiDto2AppDto(new OrderContent(request.getOrder_id(), request.getProduct_ids())), new Timestamp(System.currentTimeMillis()));
    }

    @Override
    @PostMapping("/delete")
    public void orderDeleteProduct(@RequestBody RequestOrderContent request) {
        if (!rightsService.isUser(request.getToken())) {
            throw new Unauthorized();
        } else {
            if(!rightsService.isAdmin(request.getToken())) {
                UUID owner_id = orderService.getOrderById(request.getOrder_id()).getUser_id();
                if (userService.getUserId(new Token(request.getToken())) != owner_id) {
                    throw new Forbidden(request.getToken());
                }
            }
        }
        orderService.removeProductFromOrder(orderContentMapper.apiDto2AppDto(new OrderContent(request.getOrder_id(), request.getProduct_ids())));
    }

    @Override
    @GetMapping()
    public DetailedOrder orderGetProducts(RequestUUID request) {
        if (!rightsService.isUser(request.getToken())) {
            throw new Unauthorized();
        } else {
            if(!rightsService.isAdmin(request.getToken())) {
                UUID owner_id = orderService.getOrderById(request.getData()).getUser_id();
                if (userService.getUserId(new Token(request.getToken())) != owner_id) {
                    throw new Forbidden(request.getToken());
                }
            }
        }
        return detailedOrderMapper.appDto2ApiDto(orderService.getOrderById(request.getData()));
    }

    @Override
    @PostMapping("/deleteOrder")
    public void orderDelete(@RequestBody RequestUUID request) {
        if (!rightsService.isUser(request.getToken())) {
            throw new Unauthorized();
        } else {
            if(!rightsService.isAdmin(request.getToken())) {
                UUID owner_id = orderService.getOrderById(request.getData()).getUser_id();
                if (userService.getUserId(new Token(request.getToken())) != owner_id) {
                    throw new Forbidden(request.getToken());
                }
            }
        }
        orderService.deleteOrder(request.getData());
    }
}
