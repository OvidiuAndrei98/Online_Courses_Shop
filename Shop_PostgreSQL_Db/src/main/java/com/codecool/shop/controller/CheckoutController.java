package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.*;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.model.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

@WebServlet(urlPatterns = {"/checkout"})
public class CheckoutController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        DbManager dbManager = DbManager.getInstance();
        try {
            dbManager.setup();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


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


        HttpSession session = req.getSession( false );

        int id = (Integer) session.getAttribute("cardId");
        Cart cart = cartDaoDataStore.find(id);

        String user_id = "0";

        Order order1 = new Order(id, firstName, lastName, username, email, address, address2, country, state, zipCode, sameAddress, save, cardName, cardNumber, expiration, cvv, Integer.parseInt(user_id));


        orderDaoDataStore.add(new Order(id, firstName, lastName, username, email, address, address2, country, state, zipCode, sameAddress, save, cardName, cardNumber, expiration, cvv,Integer.parseInt(user_id))); // to be fixed


        if(session.getAttribute("logged_in") == "true") {
             user_id = String.valueOf(session.getAttribute("user_id"));

            System.out.println(user_id);
            dbManager.addOrder(order1);

            for (LineItem lineItem : cart.getProducts()) {
                OrderDetails orderDetails = new OrderDetails(order1.getOrderId(),lineItem.getItemId(),lineItem.getUnitPrice(), lineItem.getQuantity());
                dbManager.addOrderDetails(orderDetails);
            }
        }




        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        LocalDate date = LocalDate.now();

        context.setVariable("categories", productCategoryDataStore.getAll());
        context.setVariable("suppliers", supplierDataStore.getAll());
        context.setVariable("order", order1);
        context.setVariable("cart", cart);
        context.setVariable("lineitems", cart.getProducts());
        context.setVariable("date", date);



        engine.process("product/invoice.html", context, resp.getWriter());

    }

}