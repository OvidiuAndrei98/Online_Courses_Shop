package com.codecool.shop.dao.jdbcImplementation;

import com.codecool.shop.dao.DbManager;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.*;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJdbc implements ProductDao {
    private DataSource dataSource;

    public  ProductDaoJdbc(DataSource dataSource) {
        this.dataSource =dataSource;
    }

    @Override
    public void add(Product product) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO products (name, price, quantity_in_stock, quantity_sold, supplier_id, category_id, currency, description) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,product.getName());
            statement.setDouble(2, Double.parseDouble(product.getPrice().split(" ")[0]));
            statement.setInt(3,10);
            statement.setInt(4,3);
            statement.setInt(5,1);
            statement.setInt(6,1);
            statement.setString(7, (String) product.getDefaultCurrency());
            statement.setString(8,product.getDescription());

            statement.execute();

            ResultSet resultSet = statement.getGeneratedKeys();

            resultSet.next();


            product.setId(resultSet.getInt(1));
        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product find(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM products WHERE id = ? ";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,id);



            ResultSet resultSet = statement.executeQuery();

            if(!resultSet.next()) {
                return null;
            }

            ProductCategory productCategory1 =  DbManager.getInstance().findCategory(resultSet.getInt(7));
            Supplier supplier1 =  DbManager.getInstance().findSupplier(resultSet.getInt(6));
            Product product = new Product(resultSet.getString(2),resultSet.getFloat(3),resultSet.getString(8),resultSet.getString(9),productCategory1,supplier1);
            product.setId(resultSet.getInt(1));

            return  product;

        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public List<Product> getBySupplier(Supplier supplier) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM products WHERE supplier_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,supplier.getId());



            ResultSet resultSet = statement.executeQuery();

            List<Product> result = new ArrayList<>();

            while (resultSet.next()) {
                ProductCategory productCategory1 =  DbManager.getInstance().findCategory(resultSet.getInt(7));
                Supplier supplier1 =  DbManager.getInstance().findSupplier(resultSet.getInt(6));
                Product product =new Product(resultSet.getString(2),resultSet.getFloat(3),resultSet.getString(8),resultSet.getString(9),productCategory1,supplier1);
                product.setId(resultSet.getInt(1));
                result.add(product);
            }

            return result;

        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> getByCategory(ProductCategory productCategory) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM products WHERE category_id = ? ";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,productCategory.getId());



            ResultSet resultSet = statement.executeQuery();

            List<Product> result = new ArrayList<>();

            while (resultSet.next()) {
                ProductCategory productCategory1 =  DbManager.getInstance().findCategory(resultSet.getInt(7));
                Supplier supplier =  DbManager.getInstance().findSupplier(resultSet.getInt(6));

                Product product =new Product(resultSet.getString(2),resultSet.getFloat(3),resultSet.getString(8),resultSet.getString(9),productCategory1,supplier);
                product.setId(resultSet.getInt(1));
                result.add(product);
            }

            return result;

        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
