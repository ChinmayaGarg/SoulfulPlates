package com.Group11.soulfulplates.services;

import com.Group11.soulfulplates.models.Cart;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface CartService {
    @Transactional
    Cart createOrUpdateCart(Long userId, Long sellerId);

    Optional<Cart> getCartsByUserId(Long userId);

    boolean existsByCartId(Long id);

    void deleteCartAndItems(Long cartId);
}

