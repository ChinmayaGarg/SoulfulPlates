package com.Group11.soulfulplates.repository;

import com.Group11.soulfulplates.models.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByOrderOrderId(Long orderId);
    Optional<Payment> findFirstByOrderOrderIdOrderByPaymentIdDesc(Long orderId);
    Page<Payment> findByTransactionUserIdAndStatusOrderByCreatedAtDesc(Long userId, String status, Pageable pageable);
    Page<Payment> findByStoreStoreIdAndStatusOrderByCreatedAtDesc(Long userId, String status, Pageable pageable);
    // Method to calculate the sum of payments for a store in a specific month
    @Query("SELECT SUM(p.amount) FROM Payment p WHERE MONTH(p.createdAt) = :month AND p.store.storeId = :storeId")
    BigDecimal sumByStoreIdAndMonth(@Param("storeId") int storeId, @Param("month") int month);
}
