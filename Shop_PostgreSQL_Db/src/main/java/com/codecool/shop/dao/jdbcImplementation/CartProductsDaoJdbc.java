package com.codecool.shop.dao.jdbcImplementation;

import com.codecool.shop.dao.CartItemsDao;
import com.codecool.shop.model.*;
import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartProductsDaoJdbc implements CartItemsDao {
    private DataSource dataSource;
    Gson gson = new Gson();

    public  CartProductsDaoJdbc(DataSource dataSource) {
        this.dataSource =dataSource;
    }
    @Override
    public void add(LineItem lineItem) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO cart_items (cartid, lineitem) VALUES (?, ?::json)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1,lineItem.getOrderId());
            statement.setString(2,gson.toJson(lineItem.getProduct()));



            statement.execute();

            ResultSet resultSet = statement.getGeneratedKeys();

            resultSet.next();


        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public LineItem find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<LineItem> getBy(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM cart_items WHERE cartid = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,id);



            ResultSet resultSet = statement.executeQuery();

            List<LineItem> result = new ArrayList<>();


            while (resultSet.next()) {
                JSONParser parser = new JSONParser();
                JSONObject json = (JSONObject) parser.parse(resultSet.getString(3));

                 Product product = gson.fromJson(String.valueOf(json), Product.class);


                LineItem lineItem;

                boolean found = false;
//
                if (result.isEmpty()) {
                    result.add(new LineItem(product.getId(), product));
                } else {
                    for (int i = 0; i < result.size(); i++) {
                        if (result.get(i).getItemId() == product.getId()) {
                            found = true;
                            result.get(i).setQuantity(result.get(i).getQuantity() + 1);
                        }

                    }
                    if (!found) {
                        lineItem = new LineItem(product.getId(), product);
                        result.add(lineItem);
                    }
                }
            }

            return result;

        } catch (
                SQLException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<LineItem> getAll() {
        return null;
    }
}
