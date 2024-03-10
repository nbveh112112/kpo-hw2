package ru.hse.restaurant.api;

import ru.hse.product.storage.api.dto.*;
import ru.hse.restaurant.api.dto.RequestToken;
import ru.hse.restaurant.api.dto.RequestUserStatus;
import ru.hse.restaurant.api.dto.Token;
import ru.hse.restaurant.api.dto.UserAuth;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.UUID;

public interface UserApi {
    Token auth(UserAuth userAuth) throws NoSuchAlgorithmException, InvalidKeySpecException;
    void register(UserAuth userAuth) throws NoSuchAlgorithmException, InvalidKeySpecException;
    void logout(Token token);
    void changeStatus(RequestUserStatus request);


    UUID getId(RequestToken token);
}
