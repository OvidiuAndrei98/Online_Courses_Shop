package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.LineItemDao;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.LineItemDaoMem;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Order;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONObject;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/get-from-form"})
public class OrderController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json");

        OrderDao orderDaoDataStore = OrderDaoMem.getInstance();

        Order order = orderDaoDataStore.find(1);


        JSONObject obj = new JSONObject();
        obj.put("orderId", order.getOrderId());
        System.out.println(orderDaoDataStore.find(1).getOrderId());
        obj.put("firstname", order.getFirstName());
        System.out.println(orderDaoDataStore.find(1).getFirstName());
        obj.put("lastname", order.getLastName());
        System.out.println(orderDaoDataStore.find(1).getLastName());
        obj.put("username", order.getUsername());
        obj.put("email", order.getEmail());
        obj.put("address", order.getAddress());
        obj.put("address2", order.getAddress2());
        obj.put("country", order.getCountry());
        obj.put("state", order.getState());
        obj.put("zip", order.getZipCode());
        obj.put("sameAddress", order.getSameAddress());
        obj.put("cname", order.getCardName());
        obj.put("cnum", order.getCardNumber());
        obj.put("exp", order.getExpiration());
        obj.put("cvv", order.getCvv());


        try (PrintWriter writer = resp.getWriter()) {
            writer.print(obj.toString());
        }


//        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
//        WebContext context = new WebContext(req, resp, req.getServletContext());
//        engine.process("product/index.html", context, resp.getWriter());

    }


}





