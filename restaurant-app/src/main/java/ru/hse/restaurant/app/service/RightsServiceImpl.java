package ru.hse.restaurant.app.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hse.restaurant.data.api.model.User;
import ru.hse.restaurant.data.api.repository.UserRepository;

import java.util.Objects;

@Service
@AllArgsConstructor
public class RightsServiceImpl implements RightsService{

    private final UserRepository userRepository;
    public boolean isAdmin(String token) {
        User user = userRepository.findByToken(token);
        return Objects.equals(user.getType(), "admin");
    }

    public boolean isUser(String token) {
        User user = userRepository.findByToken(token);
        return Objects.equals(user.getType(), "user") || Objects.equals(user.getType(), "admin");
    }
}
