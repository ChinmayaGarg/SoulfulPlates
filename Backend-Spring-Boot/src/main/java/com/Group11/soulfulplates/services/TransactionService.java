package com.Group11.soulfulplates.services;

import com.Group11.soulfulplates.models.Transaction;

import java.util.Map;

public interface TransactionService {
    Transaction createTransaction(Map<String, Object> request) throws Exception;
    void updateTransactionStatus(Long transactionId, String status) throws Exception;
}
