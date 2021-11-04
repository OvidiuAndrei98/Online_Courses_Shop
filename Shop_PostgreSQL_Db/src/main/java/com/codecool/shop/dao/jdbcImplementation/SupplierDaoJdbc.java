package com.codecool.shop.dao.jdbcImplementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoJdbc implements SupplierDao {
    private DataSource dataSource;

    public  SupplierDaoJdbc(DataSource dataSource) {
        this.dataSource =dataSource;
    }

    @Override
    public void add(Supplier supplier) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO suppliers (name, description) VALUES (?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,supplier.getName());
            statement.setString(2,supplier.getDescription());

            statement.execute();

            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                supplier.setId(resultSet.getInt(1));
            }

            statement.close();

        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Supplier find(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM suppliers WHERE id = ? ";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,id);



            ResultSet resultSet = statement.executeQuery();

            if(!resultSet.next()) {
                return null;
            }

            Supplier supplier = new Supplier(resultSet.getString(2),resultSet.getString(3));
            supplier.setId(resultSet.getInt(1));
            return supplier;

        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Supplier> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM suppliers";


            ResultSet resultSet = conn.createStatement().executeQuery(sql);




            List<Supplier> result = new ArrayList<>();

            while (resultSet.next()) {
                Supplier supplier = new Supplier(resultSet.getString(2),resultSet.getString(3));
                supplier.setId(resultSet.getInt(1));
                result.add(supplier);
            }

            return result;

        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
