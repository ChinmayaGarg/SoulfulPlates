package com.Group11.soulfulplates.models;

import jakarta.persistence.*;

@Entity
@Table(name = "subscription_options")
public class SubscriptionOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subscription_id")
    private Long subscriptionId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "menu_item_id", nullable = false)
    private MenuItem menuItem;

    @Column(name = "number_of_menu_item", nullable = false)
    private int numberOfMenuItem;

    @Enumerated(EnumType.STRING)
    @Column(name = "duration", nullable = false)
    private ESubscriptionOption duration;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    // Getters and setters omitted for brevity

}

