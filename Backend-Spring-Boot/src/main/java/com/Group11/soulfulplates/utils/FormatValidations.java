package com.Group11.soulfulplates.utils;

public class FormatValidations {
    // Verifies the card number format: 1234-5678-9101
    public static boolean verifyCardNumber(String cardNumber) {
        return cardNumber.matches("\\d{4}-\\d{4}-\\d{4}-\\d{4}");
    }

    // Verifies the card expiry format: MM/YY
    public static boolean verifyCardExpiry(String cardExpiry) {
        return cardExpiry.matches("(0[1-9]|1[0-2])\\/\\d{2}");
    }

    // Verifies the CVV format: 123
    public static boolean verifyCvv(String cvv) {
        return cvv.matches("\\d{3}");
    }
}
