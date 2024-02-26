package com.Group11.soulfulplates.services;

import com.Group11.soulfulplates.models.Cart;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CartService {
    @Transactional
    Cart createCart(Long userId, Long sellerId);

    @Transactional
    void updateCart(Long userId, Long sellerId);

    Optional<List<Cart>> getCartsByUserId(Long userId);

    boolean existsByCartId(Long id);

    void deleteCartAndItems(Long cartId);

    Cart getOrCreateCart(Long userId, Long sellerId);

    Cart getCart(Long cartId);

    Cart getCart(Long userId, Long sellerId);
}

