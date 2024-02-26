package com.Group11.soulfulplates.services.impl;

import com.Group11.soulfulplates.models.Cart;
import com.Group11.soulfulplates.repository.CartRepository;
import com.Group11.soulfulplates.services.CartItemService;
import com.Group11.soulfulplates.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemService cartItemService;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, CartItemService cartItemService) {
        this.cartItemService = cartItemService;
        this.cartRepository = cartRepository;
    }

    @Override
    public Optional<List<Cart>> getCartsByUserId(Long userId) {
        return cartRepository.findAllCartsOfUserId(userId);
    }

//    @Override
//    public Cart createOrUpdateCart(Long userId) {
//        Cart cart = cartRepository.findByUserId(userId)
//                .orElse(new Cart());
//
//        if (cart.getCartId() == null) {
//            cart.setUserId(userId);
//            cart.setCreatedDate(LocalDateTime.now());
//        }
//        cart.setLastUpdatedDate(LocalDateTime.now()); // Update the last updated date
//        return cartRepository.save(cart); // Save the cart and return it
//    }

    @Override
    public boolean existsByCartId(Long id) {
        return cartRepository.existsByCartId(id);
    }

    @Override
    public Cart createCart(Long userId, Long sellerId) {
        System.out.println(5);
        // If not, create a new cart
            Cart newCart = new Cart();
            newCart.setUserId(userId);
            newCart.setSellerId(sellerId);
            newCart.setCreatedDate(LocalDateTime.now());
            newCart.setLastUpdatedDate(LocalDateTime.now());
            return cartRepository.save(newCart);
    }

    @Override
    public void updateCart(Long userId, Long sellerId) {
        System.out.println(5);
        // If not, create a new cart
        Cart newCart = new Cart();
        newCart.setUserId(userId);
        newCart.setSellerId(sellerId);
        newCart.setCreatedDate(LocalDateTime.now());
        newCart.setLastUpdatedDate(LocalDateTime.now());
        cartRepository.updateCart(userId, sellerId, LocalDateTime.now());
    }

    @Override
    @Transactional
    public void deleteCartAndItems(Long cartId) {
        cartItemService.deleteByCartId(cartId); // Delete cart items first
        cartRepository.deleteById(cartId); // Then delete the cart
    }

    @Override
    public Cart getOrCreateCart(Long userId, Long sellerId) {
        // Try to find an existing cart
        Optional<Cart> existingCart = cartRepository.findByUserIdAndSellerId(userId, sellerId);
        if (existingCart.isPresent()) {
            return existingCart.get();
        } else {
            // Create a new cart if not found
            Cart newCart = new Cart();
            newCart.setUserId(userId);
            newCart.setSellerId(sellerId);
            return cartRepository.save(newCart);
        }
    }

    @Override
    public Cart getCart(Long cartId){
        Optional<Cart> existingCart = cartRepository.findByCartId(cartId);
        if (existingCart.isPresent()) {
            return existingCart.get();
        }
        return null;
    };

    @Override
    public Cart getCart(Long userId, Long sellerId){
        Optional<Cart> existingCart = cartRepository.findByUserIdAndSellerId(userId, sellerId);
        if (existingCart.isPresent()) {
            return existingCart.get();
        }
        return null;
    };

}
