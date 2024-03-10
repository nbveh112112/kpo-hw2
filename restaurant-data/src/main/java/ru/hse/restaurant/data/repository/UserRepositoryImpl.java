package ru.hse.restaurant.data.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.hse.restaurant.data.api.model.User;
import ru.hse.restaurant.data.api.repository.UserRepository;

import java.util.UUID;

@Repository
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    public static final RowMapper<User> USER_ROW_MAPPER =
            (rs, i) -> {
                User user = new User();

                user.setId(UUID.fromString(rs.getString("id")));
                user.setUser_name(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setType(rs.getString("type"));
                user.setToken(rs.getString("token"));
                return user;
            };

    private final JdbcTemplate jdbcTemplate;
    @Override
    public User save(User user) {
        return jdbcTemplate.queryForObject(
                "insert into users(id, username, password, type, token) "
                        + "values (uuid_generate_v4(), ?, ?, ?, ?) "
                        + "returning id, username, password, type, token",
                USER_ROW_MAPPER,
                user.getUser_name(),
                user.getPassword(),
                user.getType(),
                user.getToken());
    }

    @Override
    public void updateById(User user) {
        jdbcTemplate.update(
                "update users set "
                        + "username = ?, "
                        + "password = ?, "
                        + "type = ?,"
                        + "token = ? "
                        + "where id = ?",
                user.getUser_name(),
                user.getPassword(),
                user.getType(),
                user.getToken(),
                user.getId());
    }

    @Override
    public User findById(UUID id) {
        try{
        return jdbcTemplate.queryForObject("select * from users where id=?", USER_ROW_MAPPER, id);
        }
        catch (Exception e) {
            return new User();
        }
    }

    @Override
    public void deleteById(UUID id) {
        jdbcTemplate.update("delete from users where id=?", id);
    }

    @Override
    public User findByToken(String token) {
        try {
            return jdbcTemplate.queryForObject("select * from users where token=?", USER_ROW_MAPPER, token);
        }
        catch (Exception e) {
            return new User();
        }
    }

    @Override
    public User findByName(String username) {
        try {
            return jdbcTemplate.queryForObject("select * from users where username=?", USER_ROW_MAPPER, username);
        }
        catch (Exception e) {
            return new User();
        }
    }
}
