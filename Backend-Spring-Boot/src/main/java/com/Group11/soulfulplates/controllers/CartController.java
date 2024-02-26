package com.Group11.soulfulplates.controllers;

import com.Group11.soulfulplates.models.Cart;
import com.Group11.soulfulplates.models.CartItem;
import com.Group11.soulfulplates.payload.request.CartItemDto;
import com.Group11.soulfulplates.payload.response.MessageResponse;
import com.Group11.soulfulplates.repository.CartRepository;
import com.Group11.soulfulplates.repository.UserRepository;
import com.Group11.soulfulplates.services.CartItemService;
import com.Group11.soulfulplates.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.Group11.soulfulplates.services.SellerService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;
    private final CartItemService cartItemService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    private final SellerService sellerService;

    @Autowired
    public CartController(CartService cartService, SellerService sellerService, CartItemService cartItemService) {
        this.cartService = cartService;
        this.sellerService = sellerService; // Initialize via constructor
        this.cartItemService = cartItemService;
    }

    @GetMapping("/getCartsByUserId")
    @PreAuthorize("hasRole('ROLE_BUYER')")
    public ResponseEntity<?> getCartsByUserId(@RequestParam Long userId) {
        // Validate userId parameter
        if (userId == null || !userRepository.existsById(userId)) {
            return ResponseEntity.badRequest().body(new ResponseObject(-1, "Invalid userId.", Collections.emptyList()));
        }

        // Attempt to fetch the cart for the given userId
        return cartService.getCartsByUserId(userId)
                .map(cart -> ResponseEntity.ok(new MessageResponse(1, "Carts found", cart))) // If cart is found, return the cart
                .orElseGet(() -> ResponseEntity.ok(new MessageResponse(1, "No carts found", null))); // No cart found case
    }

    @PostMapping("/createCart")
    @PreAuthorize("hasRole('ROLE_BUYER')")
    public ResponseEntity<?> create(@RequestParam(required = false) Long userId, @RequestParam(required = false) Long sellerId) {
        // Check if userId is provided and valid
        if (userId == null || !userRepository.existsById(userId)) {
            return ResponseEntity.badRequest().body(new ResponseObject(-1, "Invalid userId.", null));
        }

        // Check if sellerId is provided and valid
        if (sellerId == null || !sellerService.existsById(sellerId)) {
            return ResponseEntity.badRequest().body(new ResponseObject(-1, "Invalid sellerId.", null));
        }

        // Check if cart is present
        if (cartRepository.findByUserIdAndSellerId(userId, sellerId).isPresent()) {
            return ResponseEntity.badRequest().body(new ResponseObject(1, "Cart is already present", cartRepository.findByUserIdAndSellerId(userId, sellerId)));
        }

        Cart cart = cartService.createCart(userId, sellerId);

        return ResponseEntity.ok(new MessageResponse(1, "Cart Created successfully!", cart));
    }

    @PostMapping("/createOrUpdate")
    @PreAuthorize("hasRole('ROLE_BUYER')")
    public ResponseEntity<?> createOrUpdateCart(@RequestParam(required = false) Long userId, @RequestParam(required = false) Long sellerId) {
        // Check if userId is provided and valid
        if (userId == null || !userRepository.existsById(userId)) {
            return ResponseEntity.badRequest().body(new ResponseObject(-1, "Invalid userId.", null));
        }

        // Check if sellerId is provided and valid
        if (sellerId == null || !sellerService.existsById(sellerId)) {
            return ResponseEntity.badRequest().body(new ResponseObject(-1, "Invalid sellerId.", null));
        }

        // Check if cart is present
        if (cartRepository.findByUserIdAndSellerId(userId, sellerId).isPresent()) {
            cartService.updateCart(userId, sellerId);
            return ResponseEntity.badRequest().body(new ResponseObject(1, "Cart Updated Successfully", userId));
        }
        Cart cart = cartService.createCart(userId, sellerId);

        return ResponseEntity.ok(new MessageResponse(1, "Cart Created successfully!", cart));
    }

    @DeleteMapping("/{cartId}")
    @PreAuthorize("hasRole('ROLE_BUYER')")
    public ResponseEntity<?> deleteCart(@PathVariable Long cartId) {
        if (!cartService.existsByCartId(cartId)) {
            return ResponseEntity.badRequest().body(new ResponseObject(1, "Cart Not Available.", null));
        }
        cartService.deleteCartAndItems(cartId);
        return ResponseEntity.ok(new MessageResponse(1, "Cart Deleted successfully!", cartId));
    }

    @PutMapping("/updateCartItem")
    @PreAuthorize("hasRole('ROLE_BUYER')")
    public ResponseEntity<?> updateCartItem(@RequestParam Long userId, @RequestParam Long sellerId, @RequestBody CartItemDto cartItemDto) {
        // Check if userId is provided and valid
        if (userId == null || !userRepository.existsById(userId)) {
            return ResponseEntity.badRequest().body(new ResponseObject(-1, "Invalid userId.", null));
        }

        // Check if sellerId is provided and valid
        if (sellerId == null || !sellerService.existsById(sellerId)) {
            return ResponseEntity.badRequest().body(new ResponseObject(-1, "Invalid sellerId.", null));
        }

        Cart cart = cartService.getOrCreateCart(userId, sellerId);
        CartItem updatedCartItem = cartItemService.addOrUpdateCartItem(cart.getCartId(), cartItemDto.getMenuItemId(), cartItemDto.getQuantity(), cartItemDto.getNotes());
        return ResponseEntity.ok(new MessageResponse(1, "Cart Updated successfully!", updatedCartItem));
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
