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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/cart/content"})
public class CartShow extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }


}