package com.codecool.shop.controller;

import com.codecool.shop.dao.*;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.model.Cart;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.service.ProductService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.json.simple.JSONObject;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/cart/content"})
public class CartShow extends HttpServlet {
    //api = /cart
    // await fetch api

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        ProductDao productDataStore = ProductDaoMem.getInstance();
        CartDao cartDaoDataStore = CartDaoMem.getInstance();
        LineItemDao lineItemDaoDataStore = LineItemDaoMem.getInstance();


        Cart cart = cartDaoDataStore.find(1);


        int productId =1; // Integer.parseInt(req.getParameter("productId"));
        LineItem lineItem1  = new LineItem(lineItemDaoDataStore.getAll().size()+1, productDataStore.find(productId));
        lineItem1.setOrderId(cart.getId());
        lineItemDaoDataStore.add(lineItem1);
        cart.addToCart(lineItem1);
        cart.setQuantity(cart.getProducts().size());


        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        context.setVariable("cart", cart);
        context.setVariable("lineItems", lineItemDaoDataStore.getAll());

        engine.process("product/index.html", context, resp.getWriter());

    }


}