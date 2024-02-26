package com.Group11.soulfulplates.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private Seller seller;

    @Column(name = "last_updated_date", nullable = false)
    private LocalDateTime lastUpdatedDate;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartItem> cartItems = new ArrayList<>();

    // Default constructor
//    public Cart() {
//    }

    public Cart () {
        seller = new Seller();
        user=new User();
    }

    // Getters and Setters
    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public void getSellerId(Long sellerId) {
        if(this.seller!=null) {
            this.seller.getSellerId();
        }
    }

    public void setSellerId(Long sellerId) {
        if(this.seller!=null) {
            this.seller.setSellerId(sellerId);
        }
    }

    public Long getUserId() {
        return user.getId();
    }

    public void setUserId(Long userId) {
        if(this.user!=null) {
            this.user.setId(userId);
        }
    }

    public Long getSellerId() {
        return seller.getSellerId();
    }

    public LocalDateTime getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(LocalDateTime lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartId=" + cartId +
                ", userId=" + user.getId() +
                ", sellerId=" + seller.getSellerId() +
                ", lastUpdatedDate=" + lastUpdatedDate +
                ", createdDate=" + createdDate +
                '}';
    }
}
