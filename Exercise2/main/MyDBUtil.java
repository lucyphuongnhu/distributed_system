package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDBUtil {
    public static Connection getConnection() throws SQLException {
        Connection conn = null;
        try {
            // db parameters
            String url       = "jdbc:mysql://localhost:3306/distributed_system";
            String user      = "root";
            String password  = "Conchimnon123@@1";

            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }

}
