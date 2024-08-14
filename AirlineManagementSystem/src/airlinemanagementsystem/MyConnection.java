package airlinemanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class MyConnection {
    Connection con; // Represents the connection to the database
    Statement stm;  // Used to execute SQL queries

    // Constructor
    public MyConnection() {
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            //establishing the connection to the database
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ams", "root", "admin123");

            // Creating a Statement object to send SQL statements to the database
            stm = con.createStatement();
        } catch (Exception ex) {
            // Printing any exception details to the console
            ex.printStackTrace();
        }
    }

    // Main method for testing the connection
    public static void main(String[] args) {
        // Instantiate the MyConnection class to initialize the database connection
        new MyConnection();
    }
}