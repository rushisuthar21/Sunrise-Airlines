package airlinemanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class MyConnection {
    Connection con;
    Statement stm;

    // Constructor
    public MyConnection() {
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establish the connection
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ams", "root", "admin123");

            // Create the statement
            stm = con.createStatement();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Instantiate the class to initialize the connection
        new MyConnection();
    }
}
