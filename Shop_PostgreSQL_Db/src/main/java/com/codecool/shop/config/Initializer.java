package com.codecool.shop.config;

import com.codecool.shop.dao.*;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.model.*;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Objects;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {


        //setting up a new supplier
        Supplier udemy = new Supplier("Udemy", "Learn for your future");
        Supplier coursera = new Supplier("Coursera", "Build Skills with Online Courses from Top Institutions");
        Supplier codeWithMosh = new Supplier("CodeWithMosh", "Master the Coding Skills to Become an Engineer Companies LOVE to Hire");

        //setting up a new product category
        ProductCategory javaScript = new ProductCategory("JavaScript", "JS Course", "Javascript courses");
        ProductCategory html = new ProductCategory("HTML", "HTML Course", "HTML courses");
        ProductCategory css = new ProductCategory("CSS", "CSS Course", "CSS courses");
        ProductCategory sql = new ProductCategory("SQL", "SQL Course", "SQL courses");


//        DB connection
        DbManager dbManager = new DbManager();
        if ( Objects.equals(dbManager.getDotenv().get("DAO"), "jdbc")){
//            try {
//                dbManager.setup();
//                dbManager.addSupplier(udemy);
//                dbManager.addCategory(javaScript);
//                dbManager.addProduct(new Product("The Complete JavaScript Course 2021: From Zero to Expert!", 18.99f, "USD", "The modern JavaScript course for everyone! Master JavaScript with projects, challenges and theory.", javaScript, udemy));
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }


        } else {
            ProductDao productDataStore = ProductDaoMem.getInstance();
            ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
            SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

            supplierDataStore.add(udemy);
            supplierDataStore.add(coursera);
            supplierDataStore.add(codeWithMosh);

            productCategoryDataStore.add(javaScript);
            productCategoryDataStore.add(html);
            productCategoryDataStore.add(css);
            productCategoryDataStore.add(sql);



            //setting up products and printing it
            productDataStore.add(new Product("The Complete JavaScript Course 2021: From Zero to Expert!", 18.99f, "USD", "The modern JavaScript course for everyone! Master JavaScript with projects, challenges and theory.", javaScript, udemy));
            productDataStore.add(new Product("The Ultimate JavaScript Mastery Series", 15, "USD", "Master the Fundamentals of JavaScript - The Language Behind Millions of Websites & Apps.", javaScript, codeWithMosh));
            productDataStore.add(new Product("HTML, CSS, and Javascript for Web Developers", 50, "USD", "Learn the basic tools that every web page coder needs to know.", javaScript, coursera));
            productDataStore.add(new Product("HTML, CSS, and Javascript for Web Developers", 50, "USD", "Learn the basic tools that every web page coder needs to know.", html, coursera));
            productDataStore.add(new Product("The Ultimate HTML/CSS Mastery Series", 49, "USD", "Everything you need to build fast and beautiful websites with HTML5 and CSS3 in one bundle.", html, codeWithMosh));
            productDataStore.add(new Product("Build Responsive Real World Websites with HTML5 and CSS3", 18.99f, "USD", "The easiest way to learn modern web design, HTML5 and CSS3 step-by-step from scratch. Design AND code a huge project.", html, udemy));
            productDataStore.add(new Product("HTML, CSS, and Javascript for Web Developers", 50, "USD", "Learn the basic tools that every web page coder needs to know.", css, coursera));
            productDataStore.add(new Product("The Ultimate HTML/CSS Mastery Series", 49, "USD", "Everything you need to build fast and beautiful websites with HTML5 and CSS3 in one bundle.", css, codeWithMosh));
            productDataStore.add(new Product("Build Responsive Real World Websites with HTML5 and CSS3", 18.99f, "USD", "The easiest way to learn modern web design, HTML5 and CSS3 step-by-step from scratch. Design AND code a huge project.", css, udemy));
            productDataStore.add(new Product("Learn SQL Basics for Data Science Specialization", 50, "USD", "Apply SQL creatively to analyze and explore data; demonstrate efficiency in writing queries or create data analysis datasets.", sql, coursera));
            productDataStore.add(new Product("SQL and PostgreSQL: The Complete Developer's Guide", 14.99f, "USD", "Become an expert with SQL and PostgreSQL! Store and fetch data, tune queries, and design efficient database structures!", sql, udemy));
            productDataStore.add(new Product("Complete SQL Mastery", 29, "USD", "Everything You Need to Design and Query Databases in One Course.", sql, codeWithMosh));
        }
    }
}
