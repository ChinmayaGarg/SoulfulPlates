package com.Group11.soulfulplates.repository;

import com.Group11.soulfulplates.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByOrderOrderId(Long orderId);
}
