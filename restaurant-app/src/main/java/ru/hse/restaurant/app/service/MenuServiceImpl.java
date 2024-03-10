package ru.hse.restaurant.app.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hse.restaurant.app.dto.Menu;
import ru.hse.restaurant.app.dto.Product;
import ru.hse.restaurant.app.mapper.ProductMapper;
import ru.hse.restaurant.data.api.repository.MenuRepository;
import ru.hse.restaurant.data.api.repository.ProductRepository;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MenuServiceImpl implements MenuService{
    private final MenuRepository menuRepository;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    @Override
    public Menu getMenu() {
        List<Product> products = menuRepository.findAll().stream().map(id -> productMapper.dataModel2AppDto(productRepository.findById(id))).toList();
        return new Menu(products);
    }


    @Override
    public void addMenuItem(UUID productId) {
        menuRepository.save(productId);
    }

    @Override
    public void removeMenuItem(UUID productId) {
        menuRepository.delete(productId);
    }

    @Override
    public boolean inMenu(UUID productId) {
        return menuRepository.exist(productId);
    }
}
