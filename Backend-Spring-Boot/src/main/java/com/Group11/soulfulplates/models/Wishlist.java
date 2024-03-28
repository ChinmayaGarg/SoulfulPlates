package com.Group11.soulfulplates.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "wishlist")
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wishid")
    private Long wishId;

    @Column(name = "store_id")
    private Long storeId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "menuitem_id")
    private Long menuItemId;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "item_price")
    private Double itemPrice;
}
