package com.thetrendybazaar.thetrendybazaar;

public class Order {
    Integer orderNumber;
    Integer cartId;
    Long cardNumber;
    String address;

    public Order(Integer orderNumber, Integer cartId, Long cardNumber, String address) {
        this.orderNumber = orderNumber;
        this.cartId = cartId;
        this.cardNumber = cardNumber;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderNumber=" + orderNumber +
                ", cartId=" + cartId +
                ", cardNumber=" + cardNumber +
                '}';
    }
}
