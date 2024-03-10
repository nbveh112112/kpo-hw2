package ru.hse.restaurant.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.GONE)
public class Gone extends RuntimeException{
    public Gone(UUID id) {
        super("Order with id '" + id + "' is ready, so you cant edit it");
    }
}
