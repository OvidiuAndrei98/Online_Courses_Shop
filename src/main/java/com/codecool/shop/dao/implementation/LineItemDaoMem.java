package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.LineItemDao;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LineItemDaoMem implements LineItemDao {
    private List<LineItem> data = new ArrayList<>();
    private static LineItemDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private LineItemDaoMem() {
    }

    public static LineItemDaoMem getInstance() {
        if (instance == null) {
            instance = new LineItemDaoMem();
        }
        return instance;
    }



    @Override
    public void add(LineItem lineItem) {
        data.add(lineItem);
    }

    @Override
    public LineItem find(int id) {
        return data.stream().filter(t -> t.getLineNumber() == id).findFirst().orElse(null);
    }

    @Override
    public void remove(int id) {
        data.remove(find(id));

    }

    @Override
    public List<Product> getBy(int id) {
        return data.stream().filter(t -> t.getOrderId() == id).map(LineItem::getItem).collect(Collectors.toList());
    }

    @Override
    public List<LineItem> getAll() {
        return data;
    }
}
