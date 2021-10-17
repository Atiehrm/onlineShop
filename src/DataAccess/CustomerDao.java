package DataAccess;

import models.Customer;

import java.sql.*;

public class CustomerDao extends BaseDao {
    private final Connection connection;
    public CustomerDao() throws SQLException, NullPointerException, ClassNotFoundException {
        this.connection = BaseDao.getConnection();
    }

    public int findEmail(String email) throws SQLException, NullPointerException, ClassNotFoundException {
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("select * from customer where email = '%s'", email);
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            if (resultSet.next()) {
                return 1;
            }
        }
        return -1;
    }

    public int findNationalCode(String nationalCode) throws SQLException, NullPointerException, ClassNotFoundException {
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("select * from customer where national_code = '%s'"
                    , nationalCode);
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            if (resultSet.next()) {
                return 1;
            }
        }
        return -1;
    }

    public void save(Customer customer) throws SQLException, NullPointerException, ClassNotFoundException {
        if (getConnection() != null) {
            String sqlQuery = String.format("insert into customer" +
                            " (name,national_code,email) values ('%s','%s','%s')"
                    , customer.getFullName(), customer.getNationalCode(), customer.getEmail());
            PreparedStatement preparedStatement = getConnection().prepareStatement(sqlQuery,
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            int generatedKey = 0;
            if (rs.next()) {
                generatedKey = rs.getInt(1);
                customer.setId(generatedKey);
            }
        }
    }
}

