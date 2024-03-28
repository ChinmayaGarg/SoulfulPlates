package com.Group11.soulfulplates.controllers;

import com.Group11.soulfulplates.payload.request.CreatePaymentRequest;
import com.Group11.soulfulplates.payload.request.PaymentFilterRequestBuyer;
import com.Group11.soulfulplates.payload.request.PaymentFilterRequestSeller;
import com.Group11.soulfulplates.payload.request.UpdatePaymentStatusRequest;
import com.Group11.soulfulplates.payload.response.PaymentFilterResponse;
import com.Group11.soulfulplates.payload.response.MessageResponse;
import com.Group11.soulfulplates.services.PaymentService;
import com.Group11.soulfulplates.utils.FormatValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/createPaymentAndTransaction")
    @PreAuthorize("hasRole('ROLE_BUYER')")
    public ResponseEntity<?> createPaymentAndTransaction(@RequestBody(required = false) CreatePaymentRequest request) {
        try {
            boolean validate = true;
            validate  = validate && FormatValidations.verifyCardExpiry(request.getCardExpiry());
            validate  = validate && FormatValidations.verifyCvv(request.getCvv());
            validate  = validate && FormatValidations.verifyCardNumber(request.getCardNumber());
            if (!validate){
                throw new Exception("Invalid details");
            }
            Map<String, Object> response = paymentService.createPaymentAndTransaction(request);
            return ResponseEntity.ok().body(Map.of("code", 1, "data", response, "description", "Transaction created."));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("code", -1, "description", "An error occurred: " + e.getMessage()));
        }
    }

    @PostMapping("/updateStatus")
    @PreAuthorize("hasRole('ROLE_BUYER')")
    public ResponseEntity<?> updatePaymentStatus(@RequestBody UpdatePaymentStatusRequest request) {
        try {
            paymentService.updatePaymentStatus(request.getPaymentId(), request.getTransactionId(), request.getStatus());
            return ResponseEntity.ok(new MessageResponse(1, "Payment status updated.", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse(0, "Error updating payment status: " + e.getMessage(), null));
        }
    }

    @GetMapping("/buyerPaymentHistory")
    @PreAuthorize("hasRole('ROLE_BUYER')")
    public ResponseEntity<?> filterPayments(@RequestBody PaymentFilterRequestBuyer request) {
        try {
            // Extract parameters from the request for clarity
            Long userId = request.getUserId();
            String status = request.getStatus();
            int limit = request.getLimit();
            int offset = request.getOffset();

            // Call the service method with the extracted parameters
            List<PaymentFilterResponse> response = paymentService.filterPayments(userId, status, limit, offset);

            return ResponseEntity.ok(new MessageResponse(1, "Success", response));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MessageResponse(-1, "Failure", null));
        }
    }

    @GetMapping("/sellerPaymentHistory")
    @PreAuthorize("hasRole('ROLE_SELLER')")
    public ResponseEntity<?> filterSellerPayments(@RequestBody PaymentFilterRequestSeller request) {
        try {
            // Extract parameters from the request
            Long storeId = request.getStoreId();
            String status = request.getStatus();
            int limit = request.getLimit();
            int offset = request.getOffset();

            // Use the extracted parameters to call the service method
            List<PaymentFilterResponse> response = paymentService.filterPayments(storeId, status, limit, offset);

            return ResponseEntity.ok(new MessageResponse(1, "Success", response));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MessageResponse(-1, "Failure", null));
        }
    }
}
