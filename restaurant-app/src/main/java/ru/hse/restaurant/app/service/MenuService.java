package ru.hse.restaurant.app.service;

import ru.hse.restaurant.app.dto.Menu;

import java.util.UUID;

public interface MenuService {
    Menu getMenu();
    void addMenuItem(UUID productId);
    void removeMenuItem(UUID productId);

    boolean inMenu(UUID productId);
}
