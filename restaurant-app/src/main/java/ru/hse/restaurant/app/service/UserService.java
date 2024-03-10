package ru.hse.restaurant.app.service;

import ru.hse.restaurant.app.dto.Token;
import ru.hse.restaurant.app.dto.UserAuth;
import ru.hse.restaurant.app.dto.UserStatus;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.UUID;

public interface UserService {
    void register(UserAuth userAuth) throws NoSuchAlgorithmException, InvalidKeySpecException;
    Token login(UserAuth userAuth) throws NoSuchAlgorithmException, InvalidKeySpecException;
    void logout(Token token);
    String getUserType(Token token);

    UUID getUserId(Token token);
    void changeStatus(UserStatus userStatus);

}
