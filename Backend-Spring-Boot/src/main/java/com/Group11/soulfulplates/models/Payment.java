package com.Group11.soulfulplates.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(name = "amount", nullable = false)
    private int amount; // Use BigDecimal for precise monetary values

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false)
    private EPayment paymentMethod;

    @Column(name = "payment_date", nullable = false)
    private LocalDateTime paymentDate;

    @Column(name = "is_payment_successful", nullable = false)
    private Boolean isPaymentSuccessful;

    // Getters and setters for each field

    // ...


}
