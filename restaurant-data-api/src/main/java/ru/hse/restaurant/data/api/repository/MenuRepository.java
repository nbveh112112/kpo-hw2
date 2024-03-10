package ru.hse.restaurant.data.api.repository;

import java.util.List;
import java.util.UUID;

public interface MenuRepository {
    void save(UUID productId);

    void delete(UUID productId);

    List<UUID> findAll();

    boolean exist(UUID productiId);
}
