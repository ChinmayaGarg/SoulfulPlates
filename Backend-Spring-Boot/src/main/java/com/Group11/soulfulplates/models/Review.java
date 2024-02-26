package com.Group11.soulfulplates.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "menu_item_id", nullable = false)
    private MenuItem menuItem;

    @Column(name = "rating", nullable = false)
    private int rating; // Use int for integer rating (1-5?)

    @Column(name = "comment")
    private String comment;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

}

