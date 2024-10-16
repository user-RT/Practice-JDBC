import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class connection {
    public static void main(String[] args) {


        // Connection Establishing ----------------

String URL="jdbc:postgresql://localhost:5432/DB";
String USER="postgres";
String PASSWORD="ravi098";
try{

    Connection conn = DriverManager.getConnection(URL,USER,PASSWORD);
System.out.println("Connection Established");

      // Creating a Table-----
    String sql = "CREATE TABLE IF NOT EXISTS Student("+"id INT,"+ "name VARCHAR(50))";
    Statement s = conn.createStatement();
    s.execute(sql);
    System.out.println("Table and Columns Created Successfully");

    // Inserting Data Into Table
    String insert = "INSERT INTO Student(id ,name )Values(?,?)";
    PreparedStatement s1 = conn.prepareStatement(insert);
    s1.setInt(1,1);
    s1.setString(2,"ravi");

    s1.executeUpdate();
    System.out.println("Data Inserted Successfully");



}catch (Exception e){
    System.out.println("Connection Failed");

}
//
    }
    }
