package ru.hse.restaurant.data.api.model;

import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
public class OrderProduct {
    private UUID product_id;
    private Timestamp start_time;
}
