package com.Group11.soulfulplates.payload.response;

import lombok.Data;
import java.util.Random;

@Data
public class OtpResponse {
    private String OtpCode;

    public static String OtpCode() {
        Random random = new Random();
        int otpCode = 1000 + random.nextInt(9000);
        return String.valueOf(otpCode);
    }

}
