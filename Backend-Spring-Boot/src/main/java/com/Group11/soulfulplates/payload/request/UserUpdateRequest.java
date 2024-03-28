package com.Group11.soulfulplates.payload.request;

import lombok.Data;

@Data
public class UserUpdateRequest {
    private String username;
    private String email;
    private String contactNumber;
}
