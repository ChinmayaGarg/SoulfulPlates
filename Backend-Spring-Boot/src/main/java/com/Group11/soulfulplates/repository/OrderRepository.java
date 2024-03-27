package com.Group11.soulfulplates.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.Group11.soulfulplates.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByOrderIdAndUserId(Long id, Long userId);
    Page<Order> findByUserIdAndStatusOrderByCreatedAtDesc(Long userId, String status, Pageable pageable);
    Page<Order> findByUserIdOrderByCreatedAtDesc(Long userId, String status, Pageable pageable);
    Page<Order> findByStoreStoreIdOrderByCreatedAtDesc(Long userId, String status, Pageable pageable);
    Page<Order> findByStoreStoreIdAndStatusOrderByCreatedAtDesc(Long userId, String status, Pageable pageable);
}
