package DataAccess;

import models.Customer;
import models.Product;
import models.ShoppingBasket;
import models.enums.ProductCategory;

import java.sql.*;
import java.util.*;

public class ShoppingBasketDao extends BaseDao {
    private final Connection connection;

    public ShoppingBasketDao() throws SQLException, ClassNotFoundException {
        this.connection = BaseDao.getConnection();
    }

    public void save(ShoppingBasket shoppingBasket) throws SQLException, ClassNotFoundException {
        if (getConnection() != null) {
            String sqlQuery = String.format("insert into shoppingcard (product_id,customer_national_code,number_order) " +
                            "values ('%s','%s','%s')"
                    , shoppingBasket.getProduct().getId(), shoppingBasket.getCustomer().getNationalCode()
                    , shoppingBasket.getNumberOfOrder());
            PreparedStatement preparedStatement = getConnection().prepareStatement(sqlQuery,
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            int generatedKey = 0;
            if (rs.next()) {
                generatedKey = rs.getInt(1);
                shoppingBasket.setId(generatedKey);
            }
        }
    }

    public void delete(int id) throws SQLException, ClassNotFoundException {
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("delete from shoppingcard where id = '%d' "
                    , id);
            statement.executeUpdate(sqlQuery);
        }
    }

    public int getRows(String nationalCode) throws SQLException, ClassNotFoundException {
        int count = 0;
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("select count(1) from shoppingCard where customer_national_code = '%s'"
                    , nationalCode);
            ResultSet rs = statement.executeQuery(sqlQuery);
            rs.next();
            count = rs.getInt(1);
        }
        return count;
    }


    public List<ShoppingBasket> getBasketList(String nationalCode, Customer customer) throws SQLException, ClassNotFoundException {
        List<ShoppingBasket> basketList = new ArrayList<>();
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("SELECT shoppingcard.* ,product.name,product.category" +
                            ",product.stock,product.price" +
                            " FROM product " +
                            " INNER JOIN shoppingcard" +
                            " ON shoppingcard.product_id = product.id where customer_national_code = '%s';"
                    , nationalCode);
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                ShoppingBasket shoppingBasket = new ShoppingBasket();
                Product product = new Product();
                product.setId(resultSet.getInt("product_id"));
                product.setName(resultSet.getString("name"));
                product.setProductCategory(ProductCategory.valueOf(resultSet
                        .getString("category").toUpperCase()));
                product.setPrice(resultSet.getDouble("price"));
                product.setStock(resultSet.getInt("stock"));
                shoppingBasket.setId(resultSet.getInt("id"));
                shoppingBasket.setProduct(product);
                shoppingBasket.setCustomer(customer);
                shoppingBasket.setNumberOfOrder(resultSet.getInt("number_order"));
                basketList.add(shoppingBasket);
            }
        }
        return basketList;
    }

    public Map<Integer, Integer> findProductAndOrder(String nationalCode) throws SQLException, ClassNotFoundException {
        Map<Integer, Integer> productAndOrder = new HashMap<>();
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("select product_id,number_order from shoppingCard where customer_national_code = '%s'"
                    , nationalCode);
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                int product_id = resultSet.getInt("product_id");
                int order = resultSet.getInt("number_order");
                productAndOrder.put(product_id, order);
            }
        }
        return productAndOrder;
    }

    public void updateOrder(Customer customer, Product product, int newCountOrder) throws SQLException, ClassNotFoundException {
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("update shoppingCard set number_order='%s' where customer" +
                            "_national_code= '%s' && product_id = '%s'", newCountOrder
                    , customer.getNationalCode(), product.getId());
            statement.executeUpdate(sqlQuery);
        }
    }

    public int getOrderNumber(Customer customer, Product product) throws SQLException, ClassNotFoundException {
        int order = 0;
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("select number_order from shoppingcard where customer_national_code" +
                    "='%s' && product_id = '%s'", customer.getNationalCode(), product.getId());
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                order = resultSet.getInt("number_order");
            }

        }
        return order;
    }
}
