package ru.hse.restaurant.data.api.model;

import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;


@Data
public class Product {

  private UUID id;

  private String name;

  private String description;

  private int price;

  private int time;
}
