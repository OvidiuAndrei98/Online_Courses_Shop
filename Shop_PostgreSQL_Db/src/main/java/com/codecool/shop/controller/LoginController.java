package com.codecool.shop.controller;
import com.codecool.shop.dao.*;
import com.codecool.shop.model.Customer;
import com.codecool.shop.model.Login;
import com.google.gson.Gson;
import org.json.simple.JSONObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        DbManager dbManager = DbManager.getInstance();
        try {
            dbManager.setup();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        String jsonString = new String();
        try {
            String line = "";
            BufferedReader reader = req.getReader();
            while ((line = reader.readLine()) != null)
                jsonString += line;
        } catch (Exception e) {
            e.printStackTrace();
        }


        HttpSession session = req.getSession( false );

        Gson gson = new Gson();
        Login login = gson.fromJson(jsonString, Login.class);

        Customer customer =  dbManager.findCustomer(login.getEmail());


        if (customer != null) {
            if ((login.getEmail().equals(customer.getEmail()) && login.getPassword().equals(customer.getPassword())) && (login.getEmail() != null && login.getPassword() != null)) {
                try (PrintWriter writer = resp.getWriter()) {
                   JSONObject jsonObject = new JSONObject();
                    jsonObject.put("login","true");
                    writer.print(jsonObject.toString());
                    session.setAttribute("logged_in","true");
                    session.setAttribute("username",customer.getFirstName());
                    session.setAttribute("user_id",customer.getId());

                    String user= (String) session.getAttribute("logged_in");
                   System.out.println(user);



                }
            }
            try (PrintWriter writer = resp.getWriter()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("login","false");
                writer.print(jsonObject.toString());
            }
        }

    }

   }
