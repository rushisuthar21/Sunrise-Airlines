package airlinemanagementsystem;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// The RemoveCustomer class extends JFrame and implements ActionListener to handle the removal of customer details
public class RemoveCustomer extends JFrame implements ActionListener {
    
    JTextField tfpassport;  // Text field for entering the passport number

    // Constructor to set up the RemoveCustomer window
    public RemoveCustomer() {
        getContentPane().setBackground(Color.WHITE);  // Set the background color of the window
        setLayout(null);  // Use absolute layout
        
        setTitle("REMOVE CUSTOMER DETAILS");  // Set the title of the window

        // Heading label
        JLabel heading = new JLabel("REMOVE CUSTOMER DETAILS");
        heading.setBounds(180, 20, 500, 35);  // Set position and size of the heading
        heading.setFont(new Font("Tahoma", Font.PLAIN, 32));  // Set font for the heading
        heading.setForeground(Color.RED);  // Set text color of the heading
        add(heading);

        // Passport number label and text field
        JLabel lblpassport = new JLabel("Passport Number");
        lblpassport.setBounds(60, 130, 150, 25);  // Set position and size of the passport number label
        lblpassport.setFont(new Font("Tahoma", Font.PLAIN, 16));  // Set font for the label
        add(lblpassport);

        tfpassport = new JTextField();
        tfpassport.setBounds(220, 130, 150, 25);  // Set position and size of the passport number text field
        add(tfpassport);

        // Remove button
        JButton remove = new JButton("REMOVE");
        remove.setBackground(Color.BLACK);  // Set background color of the button
        remove.setForeground(Color.WHITE);  // Set text color of the button
        remove.setBounds(220, 180, 150, 30);  // Set position and size of the button
        remove.addActionListener(this);  // Add action listener to handle button click
        add(remove);

        // Set size, location, and visibility of the window
        setSize(700, 300);
        setLocation(300, 150);
        setVisible(true);
    }
    
    // Handle button click events
    public void actionPerformed(ActionEvent ae) {
        String passport = tfpassport.getText();  // Get the entered passport number

        // Validate the passport number input
        if (passport.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter the Passport Number.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (!passport.matches("[A-Za-z0-9]+")) {
            JOptionPane.showMessageDialog(null, "Enter a Valid Passport Number (letters and numbers only).", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Establish a connection to the database and attempt to remove the customer details
            MyConnection conn = new MyConnection();
            String query = "delete from passenger where passport = '"+passport+"'";
            int result = conn.stm.executeUpdate(query);  // Execute the delete query

            // Check if the delete operation was successful
            if (result > 0) {
                JOptionPane.showMessageDialog(null, "Customer Details Removed Successfully");
            } else {
                JOptionPane.showMessageDialog(null, "No Customer Found with the Given Passport Number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            setVisible(false);  // Close the window after the operation
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error Removing Customer Details. Please Try Again.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Main method to create an instance of RemoveCustomer and display the window
    public static void main(String[] args) {
        new RemoveCustomer();
    }
}