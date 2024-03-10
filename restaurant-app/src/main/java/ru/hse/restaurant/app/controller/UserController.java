package ru.hse.restaurant.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.hse.restaurant.api.UserApi;
import ru.hse.restaurant.api.dto.*;
import ru.hse.restaurant.app.exception.Forbidden;
import ru.hse.restaurant.app.exception.Unauthorized;
import ru.hse.restaurant.app.mapper.UserMapper;
import ru.hse.restaurant.app.service.RightsService;
import ru.hse.restaurant.app.service.UserService;


import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class UserController implements UserApi {
    private final UserService userService;
    private final UserMapper userMapper;
    private final RightsService rightsService;

    @Override
    @GetMapping("/auth")
    public Token auth(UserAuth userAuth) throws NoSuchAlgorithmException, InvalidKeySpecException {
        Token token = userMapper.appDto2ApiDto(userService.login(userMapper.apiDto2AppDto(userAuth)));
        if (token == null) {
            throw new Unauthorized();
        }
        return token;
    }

    @Override
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void register(@RequestBody UserAuth userAuth) throws NoSuchAlgorithmException, InvalidKeySpecException {
        userService.register(userMapper.apiDto2AppDto(userAuth));
    }

    @Override
    @PutMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(@RequestBody Token token) {
        userService.logout(userMapper.apiDto2AppDto(token));
    }

    @Override
    @PutMapping("/changeStatus")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changeStatus(@RequestBody RequestUserStatus request) {
        if (!rightsService.isUser(request.getToken())) {
            throw new Unauthorized();
        }
        if (!rightsService.isAdmin(request.getToken())) {
            throw new Forbidden(request.getToken());
        }
        userService.changeStatus(userMapper.apiDto2AppDto(new UserStatus(request.getId(), request.getStatus())));
    }

    @Override
    @GetMapping("/id")
    public @ResponseBody UUID getId(RequestToken token) {
        if (!rightsService.isUser(token.getToken())) {
            throw new Unauthorized();
        }
        if (!rightsService.isAdmin(token.getToken())) {
            throw new Forbidden(token.getToken());
        }
        return userService.getUserId(new ru.hse.restaurant.app.dto.Token(token.getRequesttoken()));
    }
}
