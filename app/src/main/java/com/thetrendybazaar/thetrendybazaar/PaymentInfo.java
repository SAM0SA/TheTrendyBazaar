package com.thetrendybazaar.thetrendybazaar;

import java.sql.Date;

public class PaymentInfo {
    Integer cardNumber;
    String type;
    Integer securityCode;
    Date expiryDate;

    public PaymentInfo(Integer cardNumber, String type, Integer securityCode, Date expiryDate) {
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
