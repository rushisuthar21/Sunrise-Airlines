package airlinemanagementsystem;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Font;
import java.sql.ResultSet;

public class ViewCustomer extends JFrame {
    
    JTable table;  // Table to display customer details

    public ViewCustomer() {
        // Set background color to white and layout manager to null for absolute positioning
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        // Set the title of the window
        setTitle("VIEW CUSTOMER DETAILS");
        
        // Create a heading label with specified font and color
        JLabel heading = new JLabel("VIEW CUSTOMER DETAILS");
        heading.setBounds(180, 20, 500, 35);  // Set position and size of the label
        heading.setFont(new Font("Tahoma", Font.PLAIN, 32));  // Set font of the label
        heading.setForeground(Color.BLUE);  // Set color of the label text
        add(heading);  // Add the label to the frame
        
        // Column names for the table
        String[] columns = {"Name", "Nationality", "Phone", "Address", "Passport", "Gender"};
        
        // Initialize data array to hold customer details; assuming a maximum of 20 rows
        String[][] data = new String[20][6];
        
        // Fetch customer details from the database and populate the data array
        try {
            MyConnection conn = new MyConnection();  // Establish database connection
            
            String query = "select * from passenger";  // Query to fetch customer details
            ResultSet rs = conn.stm.executeQuery(query);  // Execute the query and store the result
            
            int row = 0;  // Row index for the data array
            // Iterate through the result set and populate the data array
            while (rs.next()) {
                data[row][0] = rs.getString("name");  // Get name from the result set
                data[row][1] = rs.getString("nationality");  // Get nationality
                data[row][2] = rs.getString("phone");  // Get phone number
                data[row][3] = rs.getString("address");  // Get address
                data[row][4] = rs.getString("passport");  // Get passport number
                data[row][5] = rs.getString("gender");  // Get gender
                row++;
            }
        } catch (Exception e) {
            e.printStackTrace();  // Print stack trace if an exception occurs
        }
        
        // Create a table with the data and column names
        table = new JTable(data, columns);
        JScrollPane sp = new JScrollPane(table);  // Add the table to a scroll pane
        sp.setBounds(40, 100, 600, 300);  // Set position and size of the scroll pane
        add(sp);  // Add the scroll pane to the frame
        
        // Set size, location, and visibility of the frame
        setSize(700, 500);
        setLocation(300, 100);
        setVisible(true);
    }

    public static void main(String[] args) {
        new ViewCustomer();  // Create an instance of ViewCustomer to display the GUI
    }
}
