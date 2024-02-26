// Delivery.java
package com.Group11.soulfulplates.models;

import jakarta.persistence.*;

@Entity
@Table(name = "deliveries")
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Other fields and relationships

    @OneToOne(mappedBy = "delivery")
    private Order order;

    // Constructors, getters, and setters
}
