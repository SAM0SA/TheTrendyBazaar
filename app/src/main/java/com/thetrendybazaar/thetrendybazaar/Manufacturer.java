package com.thetrendybazaar.thetrendybazaar;

public class Manufacturer {
    Integer manufacturerId;
    String name;
    String address;
    Integer phone;
    String email;

    public Manufacturer(Integer manufacturerId, String name, String address, Integer phone, String email) {
        this.manufacturerId = manufacturerId;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Manufacturer{" +
                "manufacturerId=" + manufacturerId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone=" + phone +
                ", email='" + email + '\'' +
                '}';
    }
}
