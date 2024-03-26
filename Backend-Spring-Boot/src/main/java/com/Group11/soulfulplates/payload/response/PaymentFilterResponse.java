package com.Group11.soulfulplates.payload.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
public class PaymentFilterResponse {
    private Long userId;
    private Long storeId;
    private BigDecimal amount;
    private Long orderId;
    private String cardNumber;
    private String cardExpiry;
    private String cvv;
    private String paymentStatus;
    private Long paymentId;
    private Long transactionId;
    private String status;
    private Date createdDate;
    private Date updatedDate;

    public PaymentFilterResponse(){}
    public PaymentFilterResponse(Long userId, Long storeId, BigDecimal amount, Long orderId, String cardNumber,
                                 String cardExpiry, String cvv, String paymentStatus, Long paymentId,
                                 Long transactionId, String status, Date createdDate, Date updatedDate) {
        this.userId = userId;
        this.storeId = storeId;
        this.amount = amount;
        this.orderId = orderId;
        this.cardNumber = cardNumber;
        this.cardExpiry = cardExpiry;
        this.cvv = cvv;
        this.paymentStatus = paymentStatus;
        this.paymentId = paymentId;
        this.transactionId = transactionId;
        this.status = status;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

}
