package com.thetrendybazaar.thetrendybazaar;

public class PaymentInfo {
    Long cardNumber;
    String type;
    Integer securityCode;
    String expiryDate;

    public PaymentInfo(Long cardNumber, String type, Integer securityCode, String expiryDate) {
        this.cardNumber = cardNumber;
        this.type = type;
        this.securityCode = securityCode;
        this.expiryDate = expiryDate;
    }

    @Override
    public String toString() {
        return "Card number = " + cardNumber;
    }
}
