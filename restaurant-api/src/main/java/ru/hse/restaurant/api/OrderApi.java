package ru.hse.restaurant.api;

import ru.hse.product.storage.api.dto.*;
import ru.hse.restaurant.api.dto.DetailedOrder;
import ru.hse.restaurant.api.dto.Order;
import ru.hse.restaurant.api.dto.RequestOrderContent;
import ru.hse.restaurant.api.dto.RequestUUID;

public interface OrderApi {
    Order orderCreate(RequestOrderContent request);
    void orderAddProduct(RequestOrderContent request);
    void orderDeleteProduct(RequestOrderContent request);
    DetailedOrder orderGetProducts(RequestUUID request);
    void orderDelete(RequestUUID request);
}
