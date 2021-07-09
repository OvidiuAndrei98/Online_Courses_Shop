package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.*;
import com.codecool.shop.dao.implementation.*;
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
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/checkout"})
public class CheckoutController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

//        System.out.println("AM INTRAT PE RUTA");
//        System.out.println("AM INTRAT PE GET");

        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
        OrderDao orderDaoDataStore = OrderDaoMem.getInstance();
        CartDao cartDaoDataStore = CartDaoMem.getInstance();


        String firstName = req.getParameter("firstname");
        String lastName = req.getParameter("lastname");
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String address = req.getParameter("address");
        String address2 = req.getParameter("address2");
        String country = req.getParameter("country");
        String state = req.getParameter("state");
        String zipCode = req.getParameter("zip");
        String sameAddress = req.getParameter("sameaddress");
        String save = req.getParameter("saveinfo");
        String cardName = req.getParameter("cname");
        String cardNumber = req.getParameter("cnum");
        String expiration = req.getParameter("exp");
        String cvv = req.getParameter("cvv");

//        System.out.println(firstName);
//        System.out.println(save);
//        System.out.println(email);

        Order order1 = new Order(1, firstName, lastName, username, email, address, address2, country, state, zipCode, sameAddress, save, cardName, cardNumber, expiration, cvv);

        Cart cart = cartDaoDataStore.find(1);

        orderDaoDataStore.add(new Order(1, firstName, lastName, username, email, address, address2, country, state, zipCode, sameAddress, save, cardName, cardNumber, expiration, cvv)); // to be fixed


        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        LocalDate date = LocalDate.now();

        context.setVariable("categories", productCategoryDataStore.getAll());
        context.setVariable("suppliers", supplierDataStore.getAll());
        context.setVariable("order", orderDaoDataStore.find(1));
        context.setVariable("cart", cart);
        context.setVariable("lineitems", cart.getProducts());
        context.setVariable("date", date);



        engine.process("product/invoice.html", context, resp.getWriter());

    }

}