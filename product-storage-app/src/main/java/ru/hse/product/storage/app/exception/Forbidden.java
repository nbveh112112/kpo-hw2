package ru.hse.product.storage.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.hse.product.storage.api.dto.Token;

import java.util.UUID;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class Forbidden extends RuntimeException{
    public Forbidden(Token token) {
        super("Request with account associated with token '" + token + "' is forbidden");
}
}

