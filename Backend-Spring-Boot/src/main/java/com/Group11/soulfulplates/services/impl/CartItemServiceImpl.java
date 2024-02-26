package com.Group11.soulfulplates.services.impl;

import com.Group11.soulfulplates.models.CartItem;
import com.Group11.soulfulplates.repository.CartItemRepository;
import com.Group11.soulfulplates.services.CartItemService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;

    @Autowired
    public CartItemServiceImpl(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public CartItem addCartItem(Long cartId, Long menuItemId, Integer quantity, String notes) {
        CartItem cartItem = new CartItem();
        cartItem.setCartId(cartId);
        cartItem.setMenuItemId(menuItemId);
        cartItem.setQuantity(quantity);
        cartItem.setAddedDate(LocalDateTime.now());
        cartItem.setNotes(notes);
        return cartItemRepository.save(cartItem);
    }

    public CartItem addOrUpdateCartItem(Long cartId, Long menuItemId, Integer quantity, String notes) {
        Optional<CartItem> existingCartItem = cartItemRepository.findByCartIdAndMenuItemId(cartId, menuItemId);

        if (existingCartItem.isPresent()) {
            CartItem cartItemToUpdate = existingCartItem.get();
            cartItemToUpdate.setQuantity(quantity); // Update quantity
            return cartItemRepository.save(cartItemToUpdate); // Save updated cart item
        } else {
            CartItem newCartItem = new CartItem();
            newCartItem.setCartId(cartId);
            newCartItem.setMenuItemId(menuItemId);
            newCartItem.setQuantity(quantity);
            newCartItem.setNotes(notes);
            newCartItem.setAddedDate(LocalDateTime.now());
            return cartItemRepository.save(newCartItem); // Save new cart item
        }
    }

    @Override
    public Optional<CartItem> findById(Long cartItemId) {
        return cartItemRepository.findById(cartItemId);
    }

    @Override
    public CartItem updateCartItem(Long cartItemId, Integer quantity, String notes) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found")); // Or handle this scenario appropriately
        cartItem.setQuantity(quantity);
        cartItem.setNotes(notes);
        return cartItemRepository.save(cartItem);
    }

    @Override
    @Transactional
    public void removeCartItem(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }
    @Override
    public List<CartItem> getCartItemsByCartId(Long cartId) {
        return cartItemRepository.findByCartId(cartId);
    }

    @Override
    @Transactional
    public void deleteByCartId(Long cartId) {
//        List<CartItem> cartItems = cartItemRepository.findByCartId(cartId);
        cartItemRepository.deleteByCartId(cartId); // Delete all cart items associated with the cartId
    }
}
