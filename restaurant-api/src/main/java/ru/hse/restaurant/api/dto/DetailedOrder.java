package ru.hse.restaurant.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailedOrder {
    private UUID id;
    private UUID user_id;
    private String status;
    List<OrderProduct> products;
}
