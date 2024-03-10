package ru.hse.restaurant.app.service;

public interface RightsService {

    boolean isAdmin(String token);
    boolean isUser(String token);
}
