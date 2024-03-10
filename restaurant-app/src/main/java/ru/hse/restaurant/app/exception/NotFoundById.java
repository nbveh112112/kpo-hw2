package ru.hse.restaurant.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundById extends RuntimeException{

  public NotFoundById(UUID id) {
    super("Item with id '" + id + "' not found");
  }
}
