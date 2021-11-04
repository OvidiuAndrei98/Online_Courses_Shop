package com.codecool.shop.service;

import com.codecool.shop.dao.DbManager;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.util.List;

public class ProductService{
    private ProductDao productDao;
    private ProductCategoryDao productCategoryDao;
    private SupplierDao supplierDao;

    public ProductService(ProductDao productDao, ProductCategoryDao productCategoryDao, SupplierDao supplierDao) {
        this.productDao = productDao;
        this.productCategoryDao = productCategoryDao;
        this.supplierDao = supplierDao;
    }

    public ProductService() {

    }

    public ProductCategory getProductCategory(int categoryId){
        return productCategoryDao.find(categoryId);
    }

    public List<Product> getProductsForCategory(int categoryId){
        ProductCategory category = productCategoryDao.find(categoryId);
        return productDao.getByCategory(category);
    }

    public List<Product> getProductsForCategoryJdbc(int categoryId){
        return DbManager.getInstance().getByCategory(DbManager.getInstance().findCategory(categoryId));
    }

    public List<Product> getProductsForSupplierJdbc(int supplierId){
        return DbManager.getInstance().getBySupplier(DbManager.getInstance().findSupplier(supplierId));
    }

    public Supplier getProductSupplier(int supplierId) {
        return supplierDao.find(supplierId);
    }

    public List<Product> getProductsForSupplier(int supplierId) {
        Supplier supplier = supplierDao.find(supplierId);
        return productDao.getBySupplier(supplier);
    }
}
