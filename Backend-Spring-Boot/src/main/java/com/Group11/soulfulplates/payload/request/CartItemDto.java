package com.Group11.soulfulplates.payload.request;

public class CartItemDto {
    private Long menuItemId;
    private Integer quantity;
    private String notes;

    // Constructors
    public CartItemDto() {}

    public CartItemDto(Long menuItemId, Integer quantity, String notes) {
        this.menuItemId = menuItemId;
        this.quantity = quantity;
        this.notes = notes;
    }

    // Getters and setters
    public Long getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(Long menuItemId) {
        this.menuItemId = menuItemId;
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
}
