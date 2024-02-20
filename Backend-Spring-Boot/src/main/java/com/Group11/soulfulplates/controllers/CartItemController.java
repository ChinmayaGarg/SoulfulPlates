package com.Group11.soulfulplates.controllers;

import com.Group11.soulfulplates.models.CartItem;
import com.Group11.soulfulplates.services.CartItemService;
import com.Group11.soulfulplates.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cart-items")
public class CartItemController {

    private final CartItemService cartItemService;
    private final CartService cartService;

// MenuItemService menuItemService
    @Autowired
    public CartItemController(CartItemService cartItemService, CartService cartService) {
        this.cartItemService = cartItemService;
        this.cartService = cartService;
//        this.menuItemService = menuItemService;
    }

    @PreAuthorize("hasRole('ROLE_BUYER')")
    @PostMapping("/add")
    public ResponseEntity<?> addCartItem(@RequestBody(required = false) CartItem cartItem) {
        if (cartItem == null || cartItem.getCartId() == null || cartItem.getMenuItemId() == null || cartItem.getQuantity() == null) {
            return ResponseEntity.badRequest().body(new ResponseObject(-1, "Invalid Request Body.", null));
        }

        if (!cartService.existsByCartId(cartItem.getCartId())) {
            return ResponseEntity.badRequest().body(new ResponseObject(-1, "Invalid Cart.", null));
        }

        // Menu Item Service needs to be implemented
//        if (!menuItemService.existsById(cartItem.getMenuItemId())) {
//            return ResponseEntity.badRequest().body(new ResponseObject(-1, "Invalid Menu Item.", null));
//        }

        // Proceed to add the cart item if all checks pass
        CartItem newCartItem = cartItemService.addOrUpdateCartItem(cartItem.getCartId(), cartItem.getMenuItemId(), cartItem.getQuantity(), cartItem.getNotes());
        return ResponseEntity.ok(newCartItem);
    }
        }
    }
    static class ResponseObject {
        private int code;
        private String description;
        private Object data;

        public ResponseObject(int code, String description, Object data) {
            this.code = code;
            this.description = description;
            this.data = data;
        }

        // Getters and Setters
        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }
    }
}
