
//Database connection 
import java.sql.*;

// Class for establishing a database connection
public class DBConnection {

    // Main method for testing database connection
    public static void main(String args[]) {
        createConnection();
    }

    // Method to create and return a database connection
    public static Connection createConnection() {
        Connection connection = null;
        try {
            // Connecting to the database using JDBC
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/companymanagementsystem", "root",
                    "Admin@27");
            System.out.println("Database Connected!");
        } catch (Exception e) {
            // Handling any exceptions that may occur during connection
            System.out.println(e);
        }
        return connection;
    }
}
