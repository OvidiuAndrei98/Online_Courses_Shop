package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.*;

import com.codecool.shop.model.Customer;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import java.sql.SQLException;
import java.time.LocalDate;

@WebServlet(urlPatterns = {"/profile"})
public class ProfileController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        DbManager dbManager = DbManager.getInstance();
        try {
            dbManager.setup();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        String user_id = req.getParameter("user");

        Customer customer = dbManager.findCustomerById(Integer.parseInt(user_id));




        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        LocalDate date = LocalDate.now();

        context.setVariable("firstName", customer.getFirstName());
        context.setVariable("lastName", customer.getLastName());
        context.setVariable("address", customer.getAddress());
        context.setVariable("email", customer.getEmail());




        engine.process("product/profile.html", context, resp.getWriter());
    }



}
