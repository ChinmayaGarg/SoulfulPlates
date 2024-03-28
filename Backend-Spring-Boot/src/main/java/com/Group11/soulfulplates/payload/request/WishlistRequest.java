package com.Group11.soulfulplates.payload.request;

import lombok.Data;

@Data
public class WishlistRequest {

    private Long userId;
    private Long storeId;
    private Long menuItemId;
    private String itemName;
    private double itemPrice;

    public WishlistRequest() {
    }

    public WishlistRequest(Long userId, Long storeId, Long menuItemId, String itemName, double itemPrice) {
        this.userId = userId;
        this.storeId = storeId;
        this.menuItemId = menuItemId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }

}
