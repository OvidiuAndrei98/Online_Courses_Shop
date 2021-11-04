package com.codecool.shop.controller;

import com.codecool.shop.dao.*;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.model.Cart;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.model.LineItem;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONObject;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


@WebServlet(urlPatterns = {"/cart"})
public class CartController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        CartDao cartDaoDataStore = CartDaoMem.getInstance();
        LineItemDao lineItemDaoDataStore = LineItemDaoMem.getInstance();

        DbManager dbManager = DbManager.getInstance();
        try {
            dbManager.setup();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        HttpSession session = req.getSession(false);


        if (session.getAttribute("cardId") == null) {
            System.out.println("NEW");
            Cart cart = new Cart();
            cartDaoDataStore.add(cart);
            session.setAttribute("cardId",cart.getId());
            System.out.println((Integer) session.getAttribute("cardId"));
        }

        int id = (Integer) session.getAttribute("cardId");
        Cart cart = cartDaoDataStore.find(id);
        System.out.println(cart);



        int productId = Integer.parseInt(req.getParameter("productId"));


        if (lineItemDaoDataStore.find(dbManager.findProduct(productId).getId()) == null || lineItemDaoDataStore.find(dbManager.findProduct(productId).getId()).getOrderId() != cart.getId()){
            lineItemDaoDataStore.add(new LineItem(dbManager.findProduct(productId).getId(), dbManager.findProduct(productId)));
            lineItemDaoDataStore.find(dbManager.findProduct(productId).getId()).setOrderId(cart.getId());
            lineItemDaoDataStore.find(dbManager.findProduct(productId).getId()).setQuantity(1);
            cart.addToCart(lineItemDaoDataStore.find(dbManager.findProduct(productId).getId()));
            cart.increaseQuantity();


        } else {
            lineItemDaoDataStore.find(dbManager.findProduct(productId).getId()).setQuantity(lineItemDaoDataStore.find(dbManager.findProduct(productId).getId()).getQuantity() + 1);
            cart.increaseQuantity();


        }

        if(session.getAttribute("logged_in") == "true") {
           LineItem lineItem =  lineItemDaoDataStore.find(dbManager.findProduct(productId).getId());
           String user_id = String.valueOf(session.getAttribute("user_id"));
           lineItem.setOrderId(Integer.parseInt(user_id));
            dbManager.addCartProducts(lineItemDaoDataStore.find(dbManager.findProduct(productId).getId()));


            cart.setProducts(dbManager.getCartItemsByCartId(id));
        }

        cart.calculateTotal();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(cart.getProducts());


        JSONObject obj = new JSONObject();
        obj.put("id", cart.getId());
        obj.put("total", cart.getTotal());
        obj.put("products", json);
        obj.put("quantity", cart.getQuantity());


        try (PrintWriter writer = resp.getWriter()) {
            writer.append(obj.toString());
        }


        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        engine.process("product/index.html", context, resp.getWriter());

    }


}
