package com.codecool.shop.dao.jdbcImplementation;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Order;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class OrderDaoJdbc implements OrderDao {

    private DataSource dataSource;

    public  OrderDaoJdbc(DataSource dataSource) {
        this.dataSource =dataSource;
    }
    @Override
    public void add(Order order) {

        Date date = Date.valueOf(order.getDate());

        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO orders (customer_id, order_date, shipment_date, order_status, shipment_status) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1,order.getCustomerId());
            statement.setDate(2, date);
            statement.setDate(3,Date.valueOf(LocalDate.of(2021, 7, 25)));
            statement.setString(4,"Paid");
            statement.setString(5,"Shipping");


            statement.execute();

            ResultSet resultSet = statement.getGeneratedKeys();

            resultSet.next();


        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Order find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Order> getAll() {
        return null;
    }
}
