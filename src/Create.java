import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class Create {

    public static void main(String[] args) {

        // Database connection details
        String URL = "jdbc:postgresql://localhost:5432/DB";
        String USER = "postgres";
        String PASSWORD = "ravi098";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Connection established.");

            // Step 1: Create the 'users' table with columns
            String createTableSQL = "CREATE TABLE IF NOT EXISTS StudentInfo ("
                    + "id SERIAL PRIMARY KEY, "
                    + "name VARCHAR(100), "
                    + "age INT, "
                    + "address VARCHAR(255), "
                    + "phone VARCHAR(15))";

            Statement stmt = conn.createStatement();
            stmt.execute(createTableSQL);
            System.out.println("Table 'users' created successfully.");

            // Step 2: Insert data into the 'users' table
            String insertSQL = "INSERT INTO users (name, age, address, phone) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertSQL);

            // Set the values for the placeholders
            pstmt.setString(1, "John");
            pstmt.setInt(2, 30);
            pstmt.setString(3, "123 Main St");
            pstmt.setString(4, "555-1234");

            // Execute the update
            pstmt.executeUpdate();
            System.out.println("Record inserted successfully.");

        } catch (Exception e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
