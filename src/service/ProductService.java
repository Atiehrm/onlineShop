package service;

import DataAccess.ProductDao;
import models.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private final ProductDao productDao;

    public ProductService() throws SQLException, ClassNotFoundException {
        this.productDao = new ProductDao();
    }

    public List<Product> getAllProducts() throws SQLException, ClassNotFoundException {
        List<Product> productList = new ArrayList<>();
        productList = productDao.getProducts();
        return productList;
    }

    public Product findById(int id) throws SQLException, ClassNotFoundException {
        Product product = productDao.findById(id);
        return product;
    }
}
