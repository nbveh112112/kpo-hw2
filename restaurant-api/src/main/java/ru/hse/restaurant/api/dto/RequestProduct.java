package ru.hse.restaurant.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestProduct {
    private String token;

    private UUID id;

    private String name;

    private String description;

    private int price;

    private int time;
}
