package com.codecool.shop.dao;

import com.codecool.shop.model.LineItem;

import java.util.List;

public interface LineItemDao {
    void add(LineItem lineItem);
    LineItem find(int id);
    void remove(int id);

    List<LineItem> getAll();
}
