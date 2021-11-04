package com.codecool.shop.dao;

import com.codecool.shop.model.Order;
import com.codecool.shop.model.OrderDetails;

import java.util.List;

public interface OrderDetailsDao {

    void add(OrderDetails orderDetails);
    OrderDetails find(int id);
    void remove(int id);

    List<OrderDetails> getAll();
}
