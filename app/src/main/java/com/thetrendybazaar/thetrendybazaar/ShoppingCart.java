package com.thetrendybazaar.thetrendybazaar;

public class ShoppingCart {
    Integer cartId;
    Double totalPrice;
    Integer itemQuantity;
    Integer customerId;
    static int currentShoppingCardId = -1;

    public ShoppingCart(Integer cartId, Double totalPrice, Integer itemQuantity, Integer customerId) {
        this.cartId = cartId;
        this.totalPrice = totalPrice;
        this.itemQuantity = itemQuantity;
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "cartId=" + cartId +
                ", totalPrice=" + totalPrice +
                ", itemQuantity=" + itemQuantity +
                ", customerId=" + customerId +
                '}';
    }

    public void updatePrice(double priceChange){
        totalPrice += priceChange;
    }

    public void updateQuantity(int quantityToAdd){
        itemQuantity += quantityToAdd;
    }
}
