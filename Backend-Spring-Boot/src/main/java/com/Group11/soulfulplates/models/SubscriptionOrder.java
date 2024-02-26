package com.Group11.soulfulplates.models;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "subscription_order")
public class SubscriptionOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subscriptionOrderId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "subscription_id", nullable = false)
    private SubscriptionOption subscriptionOption;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private SubscriptionOrderStatus status;



}
