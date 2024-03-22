package com.Group11.soulfulplates.services.impl;

import com.Group11.soulfulplates.models.MenuItem;
import com.Group11.soulfulplates.repository.MenuItemRepository;
import com.Group11.soulfulplates.services.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MenuItemServiceImpl implements MenuItemService {

    @Autowired
    private MenuItemRepository menuItemRepository;

    public void createMenuItem(MenuItem menuItem) {
        menuItemRepository.save(menuItem);
    }

    public MenuItem findMenuById(Long categoryId) {
        return menuItemRepository.findById(categoryId).orElse(null);
    }


    public void editMenuItem(Long menuItemId, MenuItem menuItem) {
        menuItemRepository.save(menuItem);

    }

    public void deleteMenuItem(Long menuItemId) {
            menuItemRepository.deleteById(menuItemId);
    }

}