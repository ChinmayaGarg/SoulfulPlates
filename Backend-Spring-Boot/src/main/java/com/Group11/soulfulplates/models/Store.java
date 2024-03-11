package com.Group11.soulfulplates.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "stores")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long storeId;

    @Column(name = "storeName", unique = true)
    private String storeName;

    @Column(name = "storeEmail", unique = true)
    private String storeEmail;

    @Column(name = "contact_number")
    private String contactNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "store_image_url")
    private String storeImageUrl;

    public String getName() {
        return storeName;
    }

}
