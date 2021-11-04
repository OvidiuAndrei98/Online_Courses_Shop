package com.codecool.shop.dao.jdbcImplementation;

import com.codecool.shop.dao.CustomerDao;
import com.codecool.shop.model.Customer;
import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class CustomerDaoJdbc implements CustomerDao {

    private DataSource dataSource;

    public  CustomerDaoJdbc(DataSource dataSource) {
        this.dataSource =dataSource;
    }

    @Override
    public void add(Customer customer) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO customers (first_name, last_name, email, password) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,customer.getFirstName());
            statement.setString(2,customer.getLastName());
            statement.setString(3,customer.getEmail());
            statement.setString(4,customer.getPassword());

            statement.execute();

            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                customer.setId(resultSet.getInt(1));
            }

            statement.close();

        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Customer find(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM customers WHERE id = ? ";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,id);



            ResultSet resultSet = statement.executeQuery();

            if(!resultSet.next()) {
                return null;
            }
            Customer customer = new Customer(resultSet.getString(2),resultSet.getString(3),resultSet.getString(5),resultSet.getString(6));
            customer.setId(resultSet.getInt(1));

            return  customer;

        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public Customer getByEmail(String email) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM customers WHERE email = ? ";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1,email);



            ResultSet resultSet = statement.executeQuery();

            if(!resultSet.next()) {
                return null;
            }

            Customer customer = new Customer(resultSet.getString(2),resultSet.getString(3),resultSet.getString(5),resultSet.getString(6));
            customer.setId(resultSet.getInt(1));

            return  customer;

        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
    }

        @Override
    public List<Customer> getAll() {
        return null;
    }
}
