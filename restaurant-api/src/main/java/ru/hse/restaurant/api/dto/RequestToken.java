package ru.hse.restaurant.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestToken {
    private String token;

    private String requesttoken;
}
