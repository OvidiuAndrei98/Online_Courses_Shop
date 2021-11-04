package com.codecool.shop.controller;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.model.Order;
import org.json.simple.JSONObject;
import javax.servlet.ServletException;
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
        obj.put("firstname", order.getFirstName());
        obj.put("lastname", order.getLastName());
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
    }


}





