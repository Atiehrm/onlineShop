package DataAccess;

import models.Customer;
import models.Product;
import models.enums.ProductCategory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDao extends BaseDao {
    private final Connection connection;

    public ProductDao() throws SQLException, ClassNotFoundException {
        this.connection = BaseDao.getConnection();
    }

    public List<Product> getProducts() throws SQLException, ClassNotFoundException {
        List<Product> productList = new ArrayList<>();
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("select * from product");
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setProductCategory(ProductCategory.valueOf(resultSet.getString("category").toUpperCase()));
                product.setStock(resultSet.getInt("stock"));
                product.setPrice(resultSet.getDouble("price"));
                productList.add(product);
            }
        }
        return productList;
    }

    public void updateStock(int productId, int newStock) throws SQLException, ClassNotFoundException {
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("update product set stock= '%d' where id= '%d'"
                    , newStock,productId);
            statement.executeUpdate(sqlQuery);
        }
    }

    public Product findProduct(int productId) throws SQLException, ClassNotFoundException {
        Product product = null;
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("select * from product where id = '%d'", productId);
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setProductCategory(ProductCategory.valueOf(resultSet.getString("category").toUpperCase()));
                product.setStock(resultSet.getInt("stock"));
                product.setPrice(resultSet.getDouble("price"));
            }
        }
        return product;

    }
}