package com.Group11.soulfulplates.repository;

import com.Group11.soulfulplates.models.Cart;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class CartRepository {

    @PersistenceContext
    private EntityManager entityManager;

    // Method to find a cart by user ID
    public Optional<Cart> findByUserId(Long userId) {
        try {
            Cart cart = entityManager.createQuery("SELECT c FROM Cart c WHERE c.user.id = :userId", Cart.class)
                    .setParameter("userId", userId)
                    .getSingleResult();
            return Optional.of(cart);
        } catch (Exception e) {
            // Handle no result or any other exceptions appropriately
            return Optional.empty();
        }
    }

    // Method to find all carts by user ID
    public Optional<List<Cart>> findAllCartsOfUserId(Long userId) {
        try {
            List<Cart> cart = entityManager.createQuery("SELECT c FROM Cart c WHERE c.user.id = :userId", Cart.class)
                    .setParameter("userId", userId)
                    .getResultList();
            return Optional.of(cart);
        } catch (Exception e) {
            // Handle no result or any other exceptions appropriately
            return Optional.empty();
        }
    }

    // Find a cart by user ID and serviceProviderId
    public Optional<Cart> findByUserIdAndSellerId(Long userId, Long sellerId) {
        try {
            Cart cart = entityManager.createQuery("SELECT c FROM Cart c WHERE c.user.id = :userId AND c.seller.sellerId = :sellerId", Cart.class)
                    .setParameter("userId", userId)
                    .setParameter("sellerId", sellerId)
                    .getSingleResult();
            return Optional.of(cart);
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }

    public Optional<Cart> findByCartId(Long cartId) {
        try {
            Cart cart = entityManager.createQuery("SELECT c FROM Cart c WHERE c.cartId = :cartId", Cart.class)
                    .setParameter("cartId", cartId)
                    .getSingleResult();
            return Optional.of(cart);
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }

    @Transactional
    public void updateCart(Long userId, Long sellerId, LocalDateTime lastUpdatedDate) {
        entityManager.createQuery("UPDATE Cart c SET c.lastUpdatedDate = :lastUpdatedDate WHERE c.user.id = :userId AND c.seller.sellerId = :sellerId")
                .setParameter("lastUpdatedDate", lastUpdatedDate)
                .setParameter("userId", userId)
                .setParameter("sellerId", sellerId)
                .executeUpdate();
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
