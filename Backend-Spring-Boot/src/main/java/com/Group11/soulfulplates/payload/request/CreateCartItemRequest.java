package com.Group11.soulfulplates.payload.request;

public class CreateCartItemRequest {

    private Long cartId;
    private Long menuItemId;
    private Integer quantity;
    private String notes;

    // Constructors, Getters and Setters

    public CreateCartItemRequest() {
    }

    public CreateCartItemRequest(Long cartId, Long menuItemId, Integer quantity, String notes) {
        this.cartId = cartId;
        this.menuItemId = menuItemId;
        this.quantity = quantity;
        this.notes = notes;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

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
