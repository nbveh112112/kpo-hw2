package ru.hse.restaurant.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class Unauthorized extends RuntimeException{
    public Unauthorized() {
        super("Request is forbidden, for unauthorized users. Please, login or register.");
    }
}
