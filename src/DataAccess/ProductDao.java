package DataAccess;

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
                product.setProductCategory(ProductCategory.getVal(resultSet.getString("category")));
                productList.add(product);
            }
        }
        return productList;
    }
}
