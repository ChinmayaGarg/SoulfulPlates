package com.Group11.soulfulplates.models;

import jakarta.persistence.*;

@Entity
@Table(name = "user_food_preference")
public class UserFoodPreference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long preferenceId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "preference_type", nullable = false)
    private EUserFoodPreference preferenceType;

    @Column(name = "description")
    private String description;

    // Getters and setters for each field

    // ...
}


