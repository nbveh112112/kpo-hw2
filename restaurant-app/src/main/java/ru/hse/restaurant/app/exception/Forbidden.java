package ru.hse.restaurant.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class Forbidden extends RuntimeException{
    public Forbidden(String token) {
        super("Request with account associated with token '" + token + "' is forbidden");
    }
}

