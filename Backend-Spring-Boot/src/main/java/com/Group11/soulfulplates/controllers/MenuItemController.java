package com.Group11.soulfulplates.controllers;


import com.Group11.soulfulplates.models.MenuItem;
import com.Group11.soulfulplates.payload.response.MessageResponse;
import com.Group11.soulfulplates.payload.response.OrderDetailsResponse;
import com.Group11.soulfulplates.services.impl.MenuItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MenuItemController {

    @Autowired
    private MenuItemServiceImpl menuItemService;
    // Insert MenuItem
    @PostMapping("/menu-items")
    public ResponseEntity createMenuItem(@RequestBody MenuItem menuItem) {
        try {
            MenuItem createdMenuItem = menuItemService.createMenuItem(menuItem);
            return ResponseEntity.ok(new MessageResponse(1, "Menu item created.", createdMenuItem));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new OrderDetailsResponse(-1,
                    "Error creating menu item: " + e.getMessage(), null));
        }

    }

    // Update MenuItem
    @PutMapping("/menu-items/{menuItemId}")
    public ResponseEntity updateMenuItem(@PathVariable Long menuItemId, @RequestBody MenuItem updatedMenuItem) {
        try {
            MenuItem menuItem = menuItemService.updateMenuItem(menuItemId, updatedMenuItem);
            return ResponseEntity.ok(new MessageResponse(1, "Menu item updated.", menuItem));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new OrderDetailsResponse(-1,
                    "Error updating menu item: " + e.getMessage(), null));
        }
    }

    // Delete MenuItem
    @DeleteMapping("/menu-items/{menuItemId}")
    public ResponseEntity deleteMenuItem(@PathVariable Long menuItemId) {
        try {
            menuItemService.deleteMenuItem(menuItemId);
            return ResponseEntity.ok(new MessageResponse(1, "Menu item deleted.", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(-1,
                    "Error deleting menu item: " + e.getMessage(), null));
        }

    }

    @GetMapping("/getMenuItems")
    public ResponseEntity getAllMenuItemsWithDetails() {
        try {
            var menuItems =  menuItemService.getAllMenuItemsWithDetails();
            return ResponseEntity.ok(new MessageResponse(1, "Menu item fetched.", menuItems));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(-1,
                    "Error fetching menu item: " + e.getMessage(), null));
        }

    }


    @GetMapping("/getMenuItemsByStore/{storeId}")
    public ResponseEntity getAllMenuItemsWithDetails(@PathVariable Long storeId) {
        try {
            var menuItems =  menuItemService.getMenuItemByStore(storeId);
            return ResponseEntity.ok(new MessageResponse(1, "Menu item fetched.", menuItems));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(-1,
                    "Error fetching menu item: " + e.getMessage(), null));
        }
    }


}
