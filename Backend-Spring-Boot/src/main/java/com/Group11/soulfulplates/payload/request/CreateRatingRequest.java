package com.Group11.soulfulplates.payload.request;

public class CreateRatingRequest {

    private Long userId;
    private Long storeId;
    private Long orderId;
    private Integer rating;
    private String feedback;

    // Constructors, Getters, and Setters
    public CreateRatingRequest() {
    }

    // Additional constructor(s) can be added if needed

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
