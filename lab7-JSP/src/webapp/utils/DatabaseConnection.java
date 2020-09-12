package webapp.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName(Utils.DB_DRIVER);
            connection =  DriverManager.getConnection(Utils.DB_URL, Utils.DB_USER, Utils.DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
