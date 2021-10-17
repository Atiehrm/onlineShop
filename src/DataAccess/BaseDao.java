package DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDao {
    private static Connection connection;

    public BaseDao() throws SQLException, ClassNotFoundException {
        connection = getConnection();
    }

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        if (connection == null) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db-8"
                    , "root", "root");
        }
        return connection;
    }
}
