import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {

     // Database connection details
    private static final String URL = "jdbc:postgresql://localhost:5432/DB";
    private static final String USER = "postgres";
    private static final String PASSWORD = "ravi098";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Connection established.");

            // Create a user
            createUser(conn, "rojan", 17);
            // Read users
            readUsers(conn);
           //  Update user's age
            updateUserAge(conn, "Alice", 31);
            // Delete a user
            deleteUser(conn, "Alice");

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    // Create (Insert)
    public static void createUser(Connection conn, String name, int age) {
        String sql = "INSERT INTO users (name, age) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.executeUpdate();
            System.out.println("User created: " + name);
        } catch (SQLException e) {
            System.out.println("Insert failed: " + e.getMessage());
        }
    }

     // Read (Select)
    public static void readUsers(Connection conn) {
        String sql = "SELECT * FROM users";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println("User: " + rs.getString("name") + ", Age: " + rs.getInt("age"));
            }
        } catch (SQLException e) {
            System.out.println("Select failed: " + e.getMessage());
        }
    }

    // Update
    public static void updateUserAge(Connection conn, String name, int newAge) {
        String sql = "UPDATE users SET age = ? WHERE name = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, newAge);
            pstmt.setString(2, name);
            pstmt.executeUpdate();
            System.out.println("User updated: " + name);
        } catch (SQLException e) {
            System.out.println("Update failed: " + e.getMessage());
        }
    }

   //  Delete
    public static void deleteUser(Connection conn, String name) {
        String sql = "DELETE FROM users WHERE name = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
            System.out.println("User deleted: " + name);
        } catch (SQLException e) {
            System.out.println("Delete failed: " + e.getMessage());
        }
    }
}
