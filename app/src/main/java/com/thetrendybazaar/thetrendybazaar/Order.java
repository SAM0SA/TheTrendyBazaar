package com.thetrendybazaar.thetrendybazaar;

public class Order {
    Integer orderNumber;
    Integer cartId;
    Integer cardNumber;

    public Order(Integer orderNumber, Integer cartId, Integer cardNumber) {
        this.orderNumber = orderNumber;
        this.cartId = cartId;
        this.cardNumber = cardNumber;
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
