package ru.hse.product.storage.api.dto;

import lombok.Data;

import java.net.URL;
import java.util.UUID;

@Data
public class Product {

    private UUID id;

    private String name;

    private String description;

    private URL imageUrl;

    private int price;
}
