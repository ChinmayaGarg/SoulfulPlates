package com.Group11.soulfulplates.payload.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CartItemDTO {

    private Long cartItemId;
    private Long userId;
    private Long menuId;
    private Integer quantity;
    private String notes;
    private LocalDateTime created;
    private LocalDateTime updated;
    private Long orderId;
    private Boolean isActive;
    // Additional fields from Menu Table, if needed
    private String itemDescription;
    private String itemType;
    private String itemName;
    private String category;
    private BigDecimal price;
    private Boolean availability;

    // Getters and Setters
    public Long getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(Long cartItemId) {
        this.cartItemId = cartItemId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "CartItemDTO{" +
                "cartItemId=" + cartItemId +
                ", userId=" + userId +
                ", menuId=" + menuId +
                ", quantity=" + quantity +
                ", notes='" + notes + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                ", orderId=" + orderId +
                ", isActive=" + isActive +
                ", itemDescription='" + itemDescription + '\'' +
                ", itemType='" + itemType + '\'' +
                ", itemName='" + itemName + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", availability=" + availability +
                '}';
    }
}
