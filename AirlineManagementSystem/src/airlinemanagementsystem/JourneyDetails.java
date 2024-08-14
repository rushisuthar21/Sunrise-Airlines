package airlinemanagementsystem;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Font;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import net.proteanit.sql.DbUtils;

public class JourneyDetails extends JFrame implements ActionListener {
    JTable table;
    JTextField passport;
    JButton show;

    // Constructor for the JourneyDetails class
    public JourneyDetails() {
        
        // Set the background color of the content pane to white
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        // Set the title of the window
        setTitle("View Your Journey Details");
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        // Label for Passport Number input
        JLabel lblPassport = new JLabel("Passport Number");
        lblPassport.setFont(new Font("Tahoma", Font.PLAIN, 16)); // Set font style and size
        lblPassport.setBounds(50, 50, 150, 25); // Position the label
        add(lblPassport); // Add the label to the frame
        
        // Text field for entering the passport number
        passport = new JTextField();
        passport.setBounds(210, 50, 120, 25); // Set the position and size of the text field
        add(passport); // Add the text field to the frame
        
        // Button to show journey details
        show = new JButton("Show Details");
        show.setBackground(Color.BLACK); // Set button background color
        show.setForeground(Color.WHITE); // Set button text color
        show.setBounds(340, 50, 120, 25); // Position the button
        show.addActionListener(this); // Add action listener to handle button click
        add(show); // Add the button to the frame
        
        // Table to display journey details
        table = new JTable();
        
        // Scroll pane for the table to handle large data sets
        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 100, 800, 150); // Set the size and position of the scroll pane
        jsp.setBackground(Color.WHITE); // Set the background color of the scroll pane
        add(jsp); // Add the scroll pane (containing the table) to the frame
        
        // Set the size of the window and its initial position
        setSize(815, 350);
        setLocation(100, 30);
        setVisible(true); // Make the window visible
    }

    // Action performed when the "Show Details" button is clicked
    public void actionPerformed(ActionEvent ae) {
        try {
            // Create a new database connection
            MyConnection conn = new MyConnection();
            
            // Execute SQL query to fetch journey details based on the entered passport number
            ResultSet rs = conn.stm.executeQuery("select * from reservation where passport = '"+passport.getText()+"'");
            
            // If no records are found, display a message dialog
            if (!rs.isBeforeFirst()) {
                JOptionPane.showMessageDialog(null, "No Information Found");
                return;
            }
            
            // If records are found, display them in the table
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch(Exception e) {
            e.printStackTrace(); // Print any exception details to the console
        }
    }

    // Main method to launch the application
    public static void main(String[] args) {
        new JourneyDetails();
    }
}
