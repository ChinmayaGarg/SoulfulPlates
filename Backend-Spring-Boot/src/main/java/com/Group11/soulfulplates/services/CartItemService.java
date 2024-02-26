package com.Group11.soulfulplates.services;

import com.Group11.soulfulplates.models.CartItem;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CartItemService {
    @Transactional
    CartItem addCartItem(Long cartId, Long menuItemId, Integer quantity, String notes);

    @Transactional
    CartItem addOrUpdateCartItem(Long cartId, Long menuItemId, Integer quantity, String notes);

    Optional<CartItem> findById(Long cartItemId);

    @Transactional
    CartItem updateCartItem(Long cartItemId, Integer quantity, String notes);


    void removeCartItem(Long cartItemId);

    List<CartItem> getCartItemsByCartId(Long cartId);

    void deleteByCartId(Long cartId);
}
