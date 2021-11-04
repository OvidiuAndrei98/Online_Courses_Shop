package com.codecool.shop.dao.jdbcImplementation;

import com.codecool.shop.dao.OrderDetailsDao;
import com.codecool.shop.model.OrderDetails;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class OrderDetailsDaoJdbc implements OrderDetailsDao {
    private DataSource dataSource;

    public  OrderDetailsDaoJdbc(DataSource dataSource) {
        this.dataSource =dataSource;
    }
    @Override
    public void add(OrderDetails orderDetails) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO order_details (order_id, product_id, price, quantity) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1,orderDetails.getOrderId());
            statement.setInt(2,orderDetails.getProductId());
            statement.setFloat(3,orderDetails.getPrice());
            statement.setInt(4,orderDetails.getQuantity());



            statement.execute();

            ResultSet resultSet = statement.getGeneratedKeys();

            resultSet.next();


        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public OrderDetails find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<OrderDetails> getAll() {
        return null;
    }
}
