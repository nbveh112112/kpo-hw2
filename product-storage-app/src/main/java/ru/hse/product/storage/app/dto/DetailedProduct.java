package ru.hse.product.storage.app.dto;

import java.net.URL;
import lombok.Data;

@Data
public class DetailedProduct {

  private String name;

  private String description;

  private String imageUrl;

  private int price;

  private int protein;

  private int fat;

  private int carbohydrate;
}
