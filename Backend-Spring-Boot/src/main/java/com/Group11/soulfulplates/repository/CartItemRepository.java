package com.Group11.soulfulplates.repository;

import com.Group11.soulfulplates.models.CartItem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class CartItemRepository {

    @PersistenceContext
    private EntityManager entityManager;

    // Find cart items by cart ID
    public List<CartItem> findByCartId(Long cartId) {
        return entityManager.createQuery("SELECT ci FROM CartItem ci WHERE ci.cart.cartId = :cartId", CartItem.class)
                .setParameter("cartId", cartId)
                .getResultList();
    }

    // Find cart items by cart and menu item ID
    public Optional<CartItem> findByCartIdAndMenuItemId(Long cartId, Long menuItemId) {
        try {
            System.out.println(21);
            CartItem cartItem = entityManager.createQuery("SELECT ci FROM CartItem ci WHERE ci.cart.cartId = :cartId AND ci.menuItem.menuItemId = :menuItemId", CartItem.class)
                    .setParameter("cartId", cartId)
                    .setParameter("menuItemId", menuItemId)
                    .getSingleResult();
            System.out.println(22);
            return Optional.of(cartItem);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }


    // Method to find a cart item by ID
    public Optional<CartItem> findById(Long cartItemId) {
        CartItem cartItem = entityManager.find(CartItem.class, cartItemId);
        return Optional.ofNullable(cartItem);
    }

    // Method to delete a cart item by ID
    @Transactional
    public void deleteById(Long cartItemId) {
        CartItem cartItem = findById(cartItemId).orElseThrow(
                () -> new RuntimeException("Cart item not found with id: " + cartItemId)
        );
        entityManager.remove(cartItem);
    }

    // Method to delete cart items by cart id
    @Transactional
    public void deleteByCartId(Long cartId) {
        entityManager.createQuery("DELETE FROM CartItem ci WHERE ci.cart.cartId = :cartId")
                .setParameter("cartId", cartId)
                .executeUpdate();
    }

    // Save a cart item
    public CartItem save(CartItem cartItem) {
        if (cartItem.getCartItemId() == null) {
            entityManager.persist(cartItem);
            return cartItem;
        } else {
            return entityManager.merge(cartItem);
        }
    }
}
