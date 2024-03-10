package ru.hse.restaurant.app.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hse.restaurant.app.dto.Token;
import ru.hse.restaurant.app.dto.UserAuth;
import ru.hse.restaurant.app.dto.UserStatus;
import ru.hse.restaurant.app.mapper.UserMapper;
import ru.hse.restaurant.data.api.model.User;
import ru.hse.restaurant.data.api.repository.UserRepository;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Objects;
import java.util.UUID;

import static ch.qos.logback.core.util.StatusPrinter.print;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();
    public static String generateNewToken() {
        String CharSet = "abcdefghijkmnopqrstuvwxyzABCDEFGHJKLMNOPQRSTUVWXYZ234567890";
        String Token = "";
        for (int a = 1; a <= 255; a++) {
            Token += CharSet.charAt(new SecureRandom().nextInt(CharSet.length()));
        }
        return Token;
    }

    String hash(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        try {
            byte[] salt = new byte[]{1, 3, 5};
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 192);
            SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = f.generateSecret(spec).getEncoded();
            Base64.Encoder enc = Base64.getEncoder();
            return enc.encodeToString(hash);
        }
        catch (Exception ignored) {
            throw ignored;
        }
    }

    @Override
    public void register(UserAuth userAuth) throws NoSuchAlgorithmException, InvalidKeySpecException {
        User user = userMapper.appDto2DataModel(userAuth);
        user.setPassword(hash(userAuth.getPassword()));
        user.setUser_name(userAuth.getUsername());
        user.setType("user");
        userRepository.save(user);
    }

    @Override
    public Token login(UserAuth userAuth) throws NoSuchAlgorithmException, InvalidKeySpecException {
        User user = userRepository.findByName(userAuth.getUsername());
        if (Objects.equals(hash(userAuth.getPassword()), user.getPassword())) {
            String token = generateNewToken();
            user.setToken(token);
            userRepository.updateById(user);
            return new Token(user.getToken());
        } else {
            return null;
        }
    }

    @Override
    public void logout(Token token) {
        User user = userRepository.findByToken(token.getToken());
        user.setToken(null);
        userRepository.save(user);
    }

    @Override
    public String getUserType(Token token) {
        User user = userRepository.findByToken(token.getToken());
        if (user == null) {
            return "unauthorized";
        }
        return user.getType();
    }

    @Override
    public UUID getUserId(Token token) {
        User user = userRepository.findByToken(token.getToken());
        return user.getId();
    }

    @Override
    public void changeStatus(UserStatus userStatus) {
        User user = userRepository.findById(userStatus.getId());
        user.setType(userStatus.getStatus());
        userRepository.updateById(user);
    }
}
