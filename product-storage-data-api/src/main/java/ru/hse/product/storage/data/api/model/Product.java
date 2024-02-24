package ru.hse.product.storage.data.api.model;

import lombok.Data;

import java.util.UUID;

@Data
public class Product {

  private UUID id;

  private String name;

  private String description;

  private String imageUrl;

  private int price;

  private int protein;

  private int fat;

  private int carbohydrate;
}
