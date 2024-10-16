import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

    public class p {
        public static void main(String[] args) {
            String URL = "jdbc:postgresql://localhost:5432/DB"; // Database URL
            String USER = "postgres"; // Database user
            String PASSWORD = "ravi098"; // Database password

            try {
                // Establishing the connection
                Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Connection Established");



                // Create a statement to execute the query
                Statement stmt = conn.createStatement();

                // Execute the SELECT query
                ResultSet rs = stmt.executeQuery("SELECT * FROM Student");


                System.out.println("Retrieving Data from Student Table:");
                while (rs.next()) {
                    int id = rs.getInt("id"); // Get ID
                    String name = rs.getString("name"); // Get Name
                    System.out.println("ID: " + id + ", Name: " + name);
                }


                // Close the ResultSet and Statement
                rs.close();
                stmt.close();
                conn.close(); // Close the connection

            } catch (Exception e) {
                System.out.println("Connection Failed");
                e.printStackTrace(); // Print the exception stack trace
            }
        }
    }


