package com.thetrendybazaar.thetrendybazaar;

public class Return {
    Integer returnId;
    Integer itemQuantity;
    Integer orderNumber;
    String returnReason;

    public Return(Integer returnId, Integer itemQuantity, Integer orderNumber, String returnReason) {
        this.returnId = returnId;
        this.itemQuantity = itemQuantity;
        this.orderNumber = orderNumber;
        this.returnReason = returnReason;
    }

    @Override
    public String toString() {
        return "Return{" +
                "returnId=" + returnId +
                ", itemQuantity=" + itemQuantity +
                ", orderNumber=" + orderNumber +
                ", returnReason='" + returnReason + '\'' +
                '}';
    }
}
