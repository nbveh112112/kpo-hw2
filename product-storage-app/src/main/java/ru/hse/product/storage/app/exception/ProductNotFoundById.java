package ru.hse.product.storage.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundById extends RuntimeException{

  public ProductNotFoundById(UUID id) {
    super("Product with id '" + id + "' not found");
  }
}
