package DataAccess;

import models.Product;
import models.ShoppingBasket;

import java.sql.*;
import java.util.Set;

public class ShoppingBasketDao extends BaseDao {
    private final Connection connection;

    public ShoppingBasketDao() throws SQLException, ClassNotFoundException {
        this.connection = BaseDao.getConnection();
    }

    public void save(ShoppingBasket shoppingBasket) throws SQLException, ClassNotFoundException {
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("inset into shoppingcard (product_id,customer_national_code) " +
                            "values ('%s','%s')"
                    , shoppingBasket.getProduct().getId(), shoppingBasket.getCustomer().getNationalCode());
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

}
