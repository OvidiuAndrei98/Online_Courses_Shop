package com.codecool.shop.controller;

import com.codecool.shop.dao.*;

import com.codecool.shop.model.Customer;

import com.google.gson.Gson;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;


@WebServlet(urlPatterns = {"/register"})
public class RegisterController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        DbManager dbManager = DbManager.getInstance();

        String jsonString = new String();
        try {
            String line = "";
            BufferedReader reader = req.getReader();
            while ((line = reader.readLine()) != null)
                jsonString += line;
        } catch (Exception e) {
            e.printStackTrace();
        }



        Gson gson = new Gson();
        Customer customer = gson.fromJson(jsonString, Customer.class);
        dbManager.addCustomer(customer );


    }
}



