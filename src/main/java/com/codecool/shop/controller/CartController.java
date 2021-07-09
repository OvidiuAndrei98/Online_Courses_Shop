package com.codecool.shop.controller;

import com.codecool.shop.dao.*;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.model.Cart;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Product;
import com.codecool.shop.service.ProductService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet(urlPatterns = {"/cart"})
public class CartController extends HttpServlet {
        //api = /cart
    // await fetch api
    // do post

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        ProductDao productDataStore = ProductDaoMem.getInstance();
        CartDao cartDaoDataStore = CartDaoMem.getInstance();
        LineItemDao lineItemDaoDataStore = LineItemDaoMem.getInstance();


        HttpSession session = req.getSession(true);


        if (session.isNew()) {
            System.out.println("NEW");
            Cart cart = new Cart();
            cartDaoDataStore.add(cart);
            session.setAttribute("cardId",cart.getId());
            System.out.println((Integer) session.getAttribute("cardId"));
        }

        int id = (Integer) session.getAttribute("cardId");
        System.out.println(" old sesion:" + id);
        Cart cart = cartDaoDataStore.find(id);



//        Cart cart = cartDaoDataStore.find(1);

        int productId = Integer.parseInt(req.getParameter("productId"));
//        String modifier = (req.getParameter("modifier")) != null ? req.getParameter("modifier") : "default";


        if (lineItemDaoDataStore.find(productDataStore.find(productId).getId()) == null) {
            lineItemDaoDataStore.add(new LineItem(productDataStore.find(productId).getId(), productDataStore.find(productId)));
//            System.out.println(lineItemDaoDataStore.find(productDataStore.find(productId).getId()));
//            System.out.println(lineItemDaoDataStore.find(productDataStore.find(productId).getId()).getLineNumber());
//            System.out.println(productDataStore.find(productId).getId());
            lineItemDaoDataStore.find(productDataStore.find(productId).getId()).setOrderId(cart.getId());
            lineItemDaoDataStore.find(productDataStore.find(productId).getId()).setQuantity(1);
            cart.addToCart(lineItemDaoDataStore.find(productDataStore.find(productId).getId()));
            cart.increaseQuantity();

        } else {
            lineItemDaoDataStore.find(productDataStore.find(productId).getId()).setQuantity(lineItemDaoDataStore.find(productDataStore.find(productId).getId()).getQuantity() + 1);
            cart.increaseQuantity();


        }

//        if (lineItemDaoDataStore.find(productId).getQuantity() > 0) {
//            if (modifier.equals("increase")) {
//                lineItemDaoDataStore.find(productDataStore.find(productId).getId()).setQuantity(lineItemDaoDataStore.find(productDataStore.find(productId).getId()).getQuantity() + 1);
//            } else {
//                lineItemDaoDataStore.find(productDataStore.find(productId).getId()).setQuantity(lineItemDaoDataStore.find(productDataStore.find(productId).getId()).getQuantity() - 1);
//            }
//        } else {
//            cart.getProducts().remove(lineItemDaoDataStore.find(productId));
//        }

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

//        context.setVariable("cart", cart);

//        context.setVariable("lineItems", lineItemDaoDataStore.getAll());
        engine.process("product/index.html", context, resp.getWriter());

    }


}
