package com.Group11.soulfulplates.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ForgetPasswordRequest {
    @NotBlank
    private String email;
}
