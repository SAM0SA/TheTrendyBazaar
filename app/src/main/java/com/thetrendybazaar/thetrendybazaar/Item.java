package com.thetrendybazaar.thetrendybazaar;

public class Item {
    Integer articleId;
    Integer manufacturerId;
    Integer quantity;
    Double price;
    String description;
    String category;
    String name;

    public Item(Integer articleId, Integer manufacturerId, Integer quantity, Double price, String description, String category, String name) {
        this.articleId = articleId;
        this.manufacturerId = manufacturerId;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
        this.category = category;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Item{" +
                "articleId=" + articleId +
                ", manufacturerId=" + manufacturerId +
                ", quantity=" + quantity +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
