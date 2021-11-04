package com.codecool.shop.dao;

import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.Supplier;

import java.util.List;

public interface CartItemsDao {
    void add(LineItem lineItem);
    LineItem find(int id);
    void remove(int id);
    List<LineItem> getBy(int id);
    List<LineItem> getAll();
}
