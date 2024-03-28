package com.Group11.soulfulplates.repository;

import com.Group11.soulfulplates.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
