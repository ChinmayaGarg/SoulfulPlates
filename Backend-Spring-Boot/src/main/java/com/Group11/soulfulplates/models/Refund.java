package com.Group11.soulfulplates.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "refund")
public class Refund {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long refundId;

    @ManyToOne
    @JoinColumn(name = "payment_id", nullable = false)
    private Payment payment;

    @Column(name = "amount", nullable = false)
    private int amount; // Use BigDecimal for precise monetary values

    @Column(name = "refund_date")
    private LocalDateTime refundDate;

    @Column(name = "reason")
    private String reason;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ERefund status;



}
