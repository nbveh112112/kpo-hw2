package ru.hse.restaurant.data.api.model;

import lombok.Data;

import java.util.UUID;


@Data
public class User {
    private UUID id;
    private String user_name;
    private String password;
    private String type;
    private String token;
}
