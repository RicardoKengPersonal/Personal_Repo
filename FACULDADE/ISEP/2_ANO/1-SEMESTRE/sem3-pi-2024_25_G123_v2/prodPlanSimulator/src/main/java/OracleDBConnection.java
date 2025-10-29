import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleDBConnection {
    public static void main(String[] args) {
        // Database connection details
        String url = "jdbc:oracle:thin:@localhost:1521/XE";
        String username = "SYS as SYSDBA";
        String password = "password";

        // Establish connection
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            if (connection != null) {
                System.out.println("Connected to the Oracle database!");
            }
        } catch (SQLException e) {
            System.out.println("Failed to connect to the Oracle database.");
            e.printStackTrace();
        }
    }
}



