package ru.hse.restaurant.data.api.repository;

import ru.hse.restaurant.data.api.model.User;

import java.util.UUID;

public interface UserRepository {
    User save(User user);

    void updateById(User user);

    User findById(UUID id);

    void deleteById(UUID id);

    User findByToken(String token);

    User findByName(String username);
}
