package com.Group11.soulfulplates.repository;

import com.Group11.soulfulplates.models.Cart;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class CartRepository {

    @PersistenceContext
    private EntityManager entityManager;

    // Find a cart by user ID and serviceProviderId
    public Optional<Cart> findByUserIdAndServiceProviderId(Long userId, Long serviceProviderId) {
        Cart cart = entityManager.createQuery("SELECT c FROM Cart c WHERE c.userId = :userId AND c.serviceProviderId = :serviceProviderId", Cart.class)
                .setParameter("userId", userId)
                .setParameter("serviceProviderId", serviceProviderId)
                .getResultList()
                .stream()
                .findFirst()
                .orElse(null);
        return Optional.ofNullable(cart);
    }

    // Method to find a cart by user ID
    public Optional<Cart> findByUserId(Long userId) {
        try {
            Cart cart = entityManager.createQuery("SELECT c FROM Cart c WHERE c.userId = :userId", Cart.class)
                    .setParameter("userId", userId)
                    .getSingleResult();
            return Optional.of(cart);
        } catch (Exception e) {
            // Handle no result or any other exceptions appropriately
            return Optional.empty();
        }
    }

    public boolean existsByCartId(Long id) {
        Long count = entityManager.createQuery("SELECT COUNT(c) FROM Cart c WHERE c.cartId = :id", Long.class)
                .setParameter("id", id)
                .getSingleResult();
        return count > 0;
    }

    @Transactional
    public void deleteById(Long id) {
        Cart cart = entityManager.find(Cart.class, id);
        if (cart != null) {
            entityManager.remove(cart);
        }
    }

    // Save a cart
    public Cart save(Cart cart) {
        if (cart.getCartId() == null) {
            entityManager.persist(cart);
            return cart;
        } else {
            return entityManager.merge(cart);
        }
    }
}