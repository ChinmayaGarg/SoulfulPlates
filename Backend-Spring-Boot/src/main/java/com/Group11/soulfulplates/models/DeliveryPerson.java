package com.Group11.soulfulplates.models;

import jakarta.persistence.*;

@Entity
@Table(name = "delivery_person")
public class DeliveryPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long personId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "contact_number", nullable = false, unique = true)
    private String contactNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "vehicle_type", nullable = false)
    private EDeliveryPerson vehicleType;

    @Column(name = "current_location")
    private String currentLocation;


}
