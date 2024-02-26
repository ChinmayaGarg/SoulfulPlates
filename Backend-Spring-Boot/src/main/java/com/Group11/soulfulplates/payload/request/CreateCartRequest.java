package com.Group11.soulfulplates.payload.request;

public class CreateCartRequest {

    private Long userId;
    private Long serviceProviderId;

    // Constructors, Getters and Setters

    public CreateCartRequest() {
    }

    public CreateCartRequest(Long userId, Long serviceProviderId) {
        this.userId = userId;
        this.serviceProviderId = serviceProviderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getServiceProviderId() {
        return serviceProviderId;
    }

    public void setServiceProviderId(Long serviceProviderId) {
        this.serviceProviderId = serviceProviderId;
    }
}
