package com.thetrendybazaar.thetrendybazaar;

public class Shipment {
    String address;
    String shippingService;
    String shipmentType;
    Double shipmentPrice;
    Integer orderNumber;
    Integer shipmentId;

    public Shipment(Integer shipmentId, String shipmentType, Double shipmentPrice, Integer orderNumber, String shippingService, String address) {
        this.shippingService = shippingService;
        this.shipmentType = shipmentType;
        this.shipmentPrice = shipmentPrice;
        this.orderNumber = orderNumber;
        this.shipmentId = shipmentId;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Shipment{" +
                "shippingService='" + shippingService + '\'' +
                ", shipmentType='" + shipmentType + '\'' +
                ", shipmentPrice=" + shipmentPrice +
                ", orderNumber=" + orderNumber +
                ", shipmentId=" + shipmentId +
                ", address=" + address +
                '}';
    }
}
