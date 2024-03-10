package ru.hse.restaurant.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private UUID id;

    private String name;

    private String description;

    private int price;

    private int time;
}
