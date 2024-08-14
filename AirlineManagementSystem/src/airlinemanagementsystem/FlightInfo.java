package airlinemanagementsystem;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import java.sql.ResultSet;
import net.proteanit.sql.DbUtils;

// FlightInfo class extends JFrame to create a window displaying flight information in a table
public class FlightInfo extends JFrame {
    
    // Constructor to initialize the FlightInfo window and its components
    public FlightInfo() {
        
        // Set background color of the window to white and use absolute layout
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        // Set the title of the window
        setTitle("Flight Information");
        
        // Create a JTable to display flight information
        JTable table = new JTable();
        
        try {
            // Establish a connection to the database
            MyConnection conn = new MyConnection();
            
            // Execute a query to retrieve all records from the 'flight' table
            ResultSet rs = conn.stm.executeQuery("select * from flight");
            
            // Populate the JTable with the result set using DbUtils to convert the ResultSet to a table model
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch(Exception e) {
            // Print stack trace in case of an exception
            e.printStackTrace();
        }
        
        // Add the JTable to a JScrollPane to enable scrolling if the content exceeds the visible area
        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 0, 800, 500); // Set bounds for the scroll pane
        add(jsp);
        
        // Set the size, location, and visibility of the window
        setSize(800, 350);
        setLocation(100, 110);
        setVisible(true);
    }

    // Main method to create an instance of FlightInfo and display the window
    public static void main(String[] args) {
        new FlightInfo();
    }
}
