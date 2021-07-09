package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Cart {
    private static final AtomicInteger count = new AtomicInteger(1);
    int id;
    private transient List<LineItem> products = new ArrayList<>();
    float total;
    int quantity;


    public Cart(int id) {
        this.id = id;

    }

    public Cart(List<LineItem> products) {
        this.id = 1; //count.incrementAndGet();
        this.products = products;
    }

    public void addToCart(LineItem lineItem) {
        products.add(lineItem);
    }

    public int getId() {
        return id;
    }

    public List<LineItem> getProducts() {
        return products;
    }

    public float getTotal() {
        return total;
    }

    public void calculateTotal() {
        total = 0;
        products.forEach(product -> {
            total += product.getQuantity() * product.getUnitPrice();
        });
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void increaseQuantity() {
        quantity += 1;
    }

}
