package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderDaoMem implements OrderDao {

    private List<Order> data = new ArrayList<>();
    private static OrderDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private OrderDaoMem() {
    }

    public static OrderDaoMem getInstance() {
        if (instance == null) {
            instance = new OrderDaoMem();
        }
        return instance;
    }


    @Override
    public void add(Order order) {
        System.out.println(order.getAddress());
        data.add(order);

    }

    @Override
    public Order find(int id) {

        if (data.size() > 1) {
            return data.stream().filter(t -> {
//            System.out.println(t.getAddress());
//            System.out.println(t.getOrderId());
                return t.getOrderId() == id;
            }).collect(Collectors.toList()).get(1);
        }
        else
            return data.stream().filter(t -> {
//            System.out.println(t.getAddress());
//            System.out.println(t.getOrderId());
                return t.getOrderId() == id;
            }).findFirst().orElse(null);
    }

    @Override
    public void remove(int id) {
        data.remove(find(id));

    }

    @Override
    public List<Order> getAll() {
        return data;
    }
}
