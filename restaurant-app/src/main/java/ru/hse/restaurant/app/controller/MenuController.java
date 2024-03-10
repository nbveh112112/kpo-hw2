package ru.hse.restaurant.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hse.restaurant.api.MenuApi;
import ru.hse.restaurant.api.dto.Menu;
import ru.hse.restaurant.api.dto.RequestUUID;
import ru.hse.restaurant.app.exception.Forbidden;
import ru.hse.restaurant.app.exception.Unauthorized;
import ru.hse.restaurant.app.mapper.MenuMapper;
import ru.hse.restaurant.app.service.MenuService;
import ru.hse.restaurant.app.service.RightsService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/menu")
public class MenuController implements MenuApi {
    private final MenuService menuService;
    private final RightsService rightsService;
    private final MenuMapper menuMapper;

    @Override
    @PostMapping("/add")
    public void menuAddProduct(RequestUUID request) {
        if (!rightsService.isUser(request.getToken())) {
            throw new Unauthorized();
        }
        if (!rightsService.isAdmin(request.getToken())) {
            throw new Forbidden(request.getToken());
        }
        menuService.addMenuItem(request.getData());
    }

    @Override
    @PostMapping("/delete")
    public void menuDeleteProduct(RequestUUID request) {
        if (!rightsService.isUser(request.getToken())) {
            throw new Unauthorized();
        }
        if (!rightsService.isAdmin(request.getToken())) {
            throw new Forbidden(request.getToken());
        }
        menuService.removeMenuItem(request.getData());
    }

    @Override
    @GetMapping
    public Menu getMenu() {
        return menuMapper.appDto2ApiDto(menuService.getMenu());
    }
}
