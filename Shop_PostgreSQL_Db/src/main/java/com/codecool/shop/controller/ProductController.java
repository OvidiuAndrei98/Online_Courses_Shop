package com.codecool.shop.controller;

import com.codecool.shop.dao.*;
import com.codecool.shop.service.ProductService;
import com.codecool.shop.config.TemplateEngineUtil;
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

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DbManager dbManager = DbManager.getInstance();
        try {
            dbManager.setup();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



        HttpSession session = req.getSession(true); //de refacut
        if(session.getAttribute("logged_in") == "true") {
            session.setAttribute("logged_in","true");
        } else {
            session.setAttribute("logged_in","false");
        }


        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        String category = req.getParameter("category");
        int categoryId = (category != null) ? Integer.parseInt(category) : 1;
        String supplier = req.getParameter("supplier");
        int supplierId = (supplier != null) ? Integer.parseInt(supplier) : 0;


        if (supplier == null) {
            context.setVariable("category", dbManager.findCategory(categoryId));
            context.setVariable("products", dbManager.getByCategory(DbManager.getInstance().findCategory(categoryId)));
        } else {
            context.setVariable("supplier", dbManager.findSupplier(supplierId));
            context.setVariable("products", dbManager.getBySupplier(DbManager.getInstance().findSupplier(supplierId)));
        }

        context.setVariable("categories", dbManager.getAllCategories());
        context.setVariable("suppliers", dbManager.getAllSupplier());
        // Alternative setting of the template context
//         Map<String, Object> params = new HashMap<>();
//         params.put("category", productCategoryDataStore.find(1));
//         params.put("products", productDataStore.getBy(productCategoryDataStore.find(1)));
//         context.setVariables(params);
        engine.process("product/index.html", context, resp.getWriter());
    }


}
