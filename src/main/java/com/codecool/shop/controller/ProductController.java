package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;
import com.codecool.shop.service.ProductService;
import com.codecool.shop.config.TemplateEngineUtil;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
        ProductService productService = new ProductService(productDataStore,productCategoryDataStore, supplierDataStore);

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        String category = req.getParameter("category");
        String supplier = req.getParameter("supplier");
        int categoryId = (category != null) ? Integer.parseInt(category) : 1;
        int supplierId = (supplier != null) ? Integer.parseInt(supplier) : 1;

        if (supplier != null) {
            context.setVariable("supplier", productService.getProductSupplier(supplierId));
            context.setVariable("products", productService.getProductsForSupplier(supplierId));
        } else {
            context.setVariable("category", productService.getProductCategory(categoryId));
            context.setVariable("products", productService.getProductsForCategory(categoryId));
        }

        context.setVariable("categories", productCategoryDataStore.getAll());
        context.setVariable("suppliers", supplierDataStore.getAll());
        // Alternative setting of the template context
//         Map<String, Object> params = new HashMap<>();
//         params.put("category", productCategoryDataStore.find(1));
//         params.put("products", productDataStore.getBy(productCategoryDataStore.find(1)));
//         context.setVariables(params);
        engine.process("product/index.html", context, resp.getWriter());
    }


}
