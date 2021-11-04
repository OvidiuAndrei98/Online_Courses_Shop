package com.codecool.shop.dao;

import com.codecool.shop.dao.jdbcImplementation.*;
import com.codecool.shop.model.*;
import io.github.cdimascio.dotenv.Dotenv;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class DbManager {
    Dotenv dotenv = Dotenv.load();
    private static DbManager instance = null;
    private ProductDao productDao;
    private SupplierDao supplierDao;
    private ProductCategoryDao productCategoryDao;
    private CustomerDao customerDao;
    private OrderDao orderDao;
    private  OrderDetailsDao orderDetailsDao;
    private CartProductsDaoJdbc cartProductsDaoJdbc;

    public static DbManager getInstance() {
        if (instance == null) {
            instance = new DbManager();
        }
        return instance;
    }

    public void addProduct(Product product) {
        productDao.add(product);
    }

    public void addSupplier(Supplier supplier) {
        supplierDao.add(supplier);
    }

    public void addCategory(ProductCategory productCategory) {
        productCategoryDao.add(productCategory);
    }

    public void addCustomer(Customer customer) {
        System.out.println("AM INTRAT ICISA");
        customerDao.add(customer);

    }

    public void addOrder(Order order) {
        orderDao.add(order);
    }

    public void addOrderDetails(OrderDetails orderDetails) {
        orderDetailsDao.add(orderDetails);
    }

    public void addCartProducts(LineItem lineItem) {
        cartProductsDaoJdbc.add(lineItem);
    }







    public Customer findCustomer(String email) {
        return customerDao.getByEmail(email);
    }

    public Product findProduct(int id) {
       return productDao.find(id);
    }

    public Customer findCustomerById (int id) {
        return customerDao.find(id);
    }

    public ProductCategory findCategory(int id) {
        return productCategoryDao.find(id);
    }

    public Supplier findSupplier(int id) {
        return supplierDao.find(id);
    }

    public List<Product> getByCategory(ProductCategory productCategory) {
        return productDao.getByCategory(productCategory);
    }

    public List<LineItem> getCartItemsByCartId (int id) {
       return cartProductsDaoJdbc.getBy(id);
    }

    public List<Product> getBySupplier(Supplier supplier) {
        return productDao.getBySupplier(supplier);
    }

    public List<Supplier> getAllSupplier () {
        return supplierDao.getAll();
    }

    public List<ProductCategory> getAllCategories () {
        return productCategoryDao.getAll();
    }


    private DataSource connect() throws SQLException {

        PGSimpleDataSource dataSource = new PGSimpleDataSource();

        String dbName = dotenv.get("PSQL_DB_NAME");
        String user = dotenv.get("PSQL_USER_NAME");
        String password = dotenv.get("PSQL_PASSWORD");


        dataSource.setDatabaseName(dbName);
        dataSource.setUser(user);
        dataSource.setPassword(password);

        System.out.println("Trying to connect");
        dataSource.getConnection().close();
        System.out.println("Connection ok.");

        return dataSource;
    }

    public void setup() throws SQLException {
        if(Objects.equals(dotenv.get("DAO"), "jdbc")) {
            DataSource dataSource = connect();
            System.out.println("Using PostgreSQL");
            productDao = new ProductDaoJdbc(dataSource);;
            supplierDao = new SupplierDaoJdbc(dataSource);
            productCategoryDao = new ProductsCategoryJdbc(dataSource);
            customerDao = new CustomerDaoJdbc(dataSource);
            orderDao = new OrderDaoJdbc(dataSource);
            orderDetailsDao = new OrderDetailsDaoJdbc(dataSource);
            cartProductsDaoJdbc = new CartProductsDaoJdbc(dataSource);
        }else {
            System.out.println("USING IN MEMORY DB");
        }
    }

    public Dotenv getDotenv() {
        return dotenv;
    }
}
