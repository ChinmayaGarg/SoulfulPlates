package com.Group11.soulfulplates.models;

import jakarta.persistence.*;

@Entity
@Table(name = "menu_items")
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_item_id")
    private Long menuItemId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seller_id", nullable = false)
    private Seller seller;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "price", nullable = false)
    private int price;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private EMenuItem type;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @Column(name = "category")
    private String category;

    // Getters and setters
    public Long getMenuItemId() {
        return menuItemId;
    }

    public Long setMenuItemId(Long menuItemId) {
        this.menuItemId = menuItemId;
        return menuItemId;
    }

}
