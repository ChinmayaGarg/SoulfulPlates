package com.Group11.soulfulplates.payload.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WishlistRequest {

    private Long id; // If updating, this field will be used to identify the wishlist
    private LocalDateTime createdDate;
    private Long userId; // Added user_id field

    public WishlistRequest() {
    }

    public WishlistRequest(Long id, LocalDateTime createdDate, Long userId) {
        this.id = id;
        this.createdDate = createdDate;
        this.userId = userId;
    }

}
