package ru.hse.restaurant.data.api.model;

import lombok.Data;

import java.util.UUID;

@Data
public class Order {
    private UUID id;
    private UUID user_id;
    private String status;
}
