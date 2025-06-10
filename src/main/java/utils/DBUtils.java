package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtils {

    /**
     * Establishes a connection to the database using properties for configuration.
     * The method fetches the database URL, user, and password from a `Properties` object 
     * and uses them to create a connection via `DriverManager`.
     *
     * @return Connection - A `Connection` object linked to the database.
     * @throws SQLException - If there is a failure in establishing the connection.
     */
    public static Connection checkDBConnection() throws SQLException {
        // Create a Properties object to hold database connection information.
        Properties props = new Properties();
        
        // Retrieve database URL, username, and password from the properties.
        String url = props.getProperty("db.url");       // Database URL (e.g., jdbc:mysql://localhost:3306/db_name)
        String user = props.getProperty("db.user");     // Database username
        String password = props.getProperty("db.password"); // Database password
        
        // Establish and return the database connection.
        return DriverManager.getConnection(url, user, password);
    }

    // Insert mobile details
    public static void insertMobileDetails(String brand, String description, String model,double price) {

        try {
            Connection conn = checkDBConnection();
            String sql = "INSERT INTO mobiles(brand, description, model, price) VALUES(?,?,?,?)";
            java.sql.PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, brand);
            pstmt.setString(2, description);
            pstmt.setString(3, model);
            pstmt.setDouble(4, price);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}