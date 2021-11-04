package com.codecool.shop.dao.jdbcImplementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductsCategoryJdbc implements ProductCategoryDao {
    private DataSource dataSource;

    public  ProductsCategoryJdbc(DataSource dataSource) {
        this.dataSource =dataSource;
    }

    @Override
    public void add(ProductCategory productCategory) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO products_category (category) VALUES (?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,productCategory.getName());

            statement.execute();

            ResultSet resultSet = statement.getGeneratedKeys();

            resultSet.next();
            productCategory.setId(resultSet.getInt(1));
        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ProductCategory find(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM products_category WHERE id = ? ";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,id);



            ResultSet resultSet = statement.executeQuery();

            if(!resultSet.next()) {
                return null;
            }
            ProductCategory category = new ProductCategory(resultSet.getString(2),resultSet.getString(3),resultSet.getString(4));
            category.setId(resultSet.getInt(1));
            return category;

        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<ProductCategory> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM products_category";


            ResultSet resultSet = conn.createStatement().executeQuery(sql);




            List<ProductCategory> result = new ArrayList<>();

            while (resultSet.next()) {
                ProductCategory category = new ProductCategory(resultSet.getString(2),resultSet.getString(3),resultSet.getString(4));
                category.setId(resultSet.getInt(1));
                result.add(category);
            }

            return result;

        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
