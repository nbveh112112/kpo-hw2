package ru.hse.product.storage.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderProduct {
    private UUID product_id;
    private Timestamp start_time;
}
