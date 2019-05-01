package com.thetrendybazaar.thetrendybazaar;

public class Shipment {
    String shippingService;
    String shipmentType;
    Double shipmentPrice;
    Integer orderNumber;
    Integer shipmentId;

    public Shipment(Integer shipmentId, String shipmentType, Double shipmentPrice, Integer orderNumber, String shippingService) {
        this.shippingService = shippingService;
        this.shipmentType = shipmentType;
        this.shipmentPrice = shipmentPrice;
        this.orderNumber = orderNumber;
        this.shipmentId = shipmentId;
    }

    @Override
    public String toString() {
        return "Shipment{" +
                "shippingService='" + shippingService + '\'' +
                ", shipmentType='" + shipmentType + '\'' +
                ", shipmentPrice=" + shipmentPrice +
                ", orderNumber=" + orderNumber +
                ", shipmentId=" + shipmentId +
                '}';
    }
}
