package ru.hse.restaurant.app.mapper;

import org.mapstruct.Mapper;
import ru.hse.restaurant.app.dto.Token;
import ru.hse.restaurant.app.dto.User;
import ru.hse.restaurant.app.dto.UserAuth;
import ru.hse.restaurant.app.dto.UserStatus;

@Mapper(componentModel = "spring")
public interface UserMapper {
    ru.hse.restaurant.api.dto.UserAuth appDto2ApiDto(
            UserAuth user);

    UserAuth apiDto2AppDto(
            ru.hse.restaurant.api.dto.UserAuth user);

    User dataModel2AppDto(
            ru.hse.restaurant.data.api.model.User user);

    ru.hse.restaurant.data.api.model.User appDto2DataModel(
            User user);

    ru.hse.restaurant.data.api.model.User appDto2DataModel(
            UserAuth user);

    ru.hse.restaurant.api.dto.Token appDto2ApiDto(
            Token token);

    Token apiDto2AppDto(
            ru.hse.restaurant.api.dto.Token token);

    UserStatus apiDto2AppDto(
            ru.hse.restaurant.api.dto.UserStatus userStatus);

    ru.hse.restaurant.api.dto.UserStatus appDto2ApiDto(
            UserStatus userStatus);

}
