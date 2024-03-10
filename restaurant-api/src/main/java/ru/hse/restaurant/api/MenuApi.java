package ru.hse.restaurant.api;

import ru.hse.restaurant.api.dto.Menu;
import ru.hse.restaurant.api.dto.RequestUUID;

public interface MenuApi {
    void menuAddProduct(RequestUUID request);
    void menuDeleteProduct(RequestUUID request);
    Menu getMenu();
}
