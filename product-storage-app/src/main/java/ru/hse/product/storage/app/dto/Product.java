package ru.hse.product.storage.app.dto;

import java.net.URL;
import java.util.UUID;
import lombok.Data;

@Data
public class Product {

  private UUID id;

  private String name;

  private String imageUrl;

  private int price;
}
