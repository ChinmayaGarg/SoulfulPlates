// Order.java
package com.Group11.soulfulplates.models;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Other fields and relationships

    @OneToOne
    @JoinColumn(name = "delivery_id", referencedColumnName = "id")
    private Delivery delivery;

    // Constructors, getters, and setters
}
