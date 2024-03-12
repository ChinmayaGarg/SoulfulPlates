package com.Group11.soulfulplates.controllers;

import com.Group11.soulfulplates.payload.request.UpdateTransactionStatusRequest;
import com.Group11.soulfulplates.payload.response.MessageResponse;
import com.Group11.soulfulplates.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    // Other endpoints...
    @PostMapping("/updateStatus")
    @PreAuthorize("hasRole('ROLE_BUYER')")
    public ResponseEntity<?> updateTransactionStatus(@RequestBody UpdateTransactionStatusRequest request) {
        try {
            transactionService.updateTransactionStatus(request.getTransactionId(), request.getStatus());
            return ResponseEntity.ok(new MessageResponse(1, "Transaction status updated.", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(0, "Error updating transaction: " + e.getMessage(), null));
        }
    }
}
