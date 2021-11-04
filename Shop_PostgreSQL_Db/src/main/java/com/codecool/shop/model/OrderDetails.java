package com.codecool.shop.model;

public class OrderDetails {
    private int orderId;
    private int productId;
    private float price;
    private int quantity;

    public OrderDetails(int orderId, int productId, float price, int quantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getProductId() {
        return productId;
    }

    public float getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
