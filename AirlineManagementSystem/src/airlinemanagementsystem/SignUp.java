package airlinemanagementsystem;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUp extends JFrame implements ActionListener {
    // Declare GUI components as class variables
    JTextField tfusername;
    JPasswordField tfpassword;
    JComboBox<String> roleComboBox;
    JButton submit, back;

    // Constructor to set up the GUI components and layout
    public SignUp() {
        // Set background color of the content pane
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        // Set title of the window
        setTitle("Register");
        
        // Username label
        JLabel lblusername = new JLabel("Username");
        lblusername.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblusername.setBounds(20, 20, 100, 20);
        add(lblusername);

        // Username text field
        tfusername = new JTextField();
        tfusername.setBounds(130, 20, 200, 20);
        add(tfusername);

        // Password label
        JLabel lblpassword = new JLabel("Password");
        lblpassword.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblpassword.setBounds(20, 60, 100, 20);
        add(lblpassword);

        // Password field
        tfpassword = new JPasswordField();
        tfpassword.setBounds(130, 60, 200, 20);
        add(tfpassword);

        // Role label
        JLabel lblRole = new JLabel("Role");
        lblRole.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblRole.setBounds(20, 100, 100, 20);
        add(lblRole);

        // Role combo box with options "User" and "Admin"
        String[] roles = { "User", "Admin" };
        roleComboBox = new JComboBox<>(roles);
        roleComboBox.setBounds(130, 100, 200, 20);
        add(roleComboBox);

        // Submit button
        submit = new JButton("Submit");
        submit.setBounds(40, 160, 120, 20);
        submit.addActionListener(this); // Register action listener
        add(submit);

        // Back button
        back = new JButton("Back");
        back.setBounds(190, 160, 120, 20);
        back.addActionListener(this); // Register action listener
        add(back);

        // Set the size and position of the window, and make it visible
        setSize(400, 250);
        setLocation(600, 250);
        setVisible(true);
    }

    // Handle button clicks
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) { // If Submit button is clicked
            // Get values from the text fields and combo box
            String username = tfusername.getText();
            String password = new String(tfpassword.getPassword());
            String role = (String) roleComboBox.getSelectedItem();
            
            // Validate the username field
            if (username.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Username cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Validate the password using a regex pattern
            if (!password.matches("^(?=.*[0-9])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?])[a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]{5,}$")) {
                JOptionPane.showMessageDialog(this, "Password must be at least 5 characters long, and include at least one special character and one number.", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Exit the method if validation fails
            }

            try {
                // Create a connection to the database and execute an SQL query
                MyConnection c = new MyConnection();
                String query = "insert into login (username, password, role) values ('" + username + "', '" + password + "', '" + role + "')";
                c.stm.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Account Created Successfully");
                
                // Open the login window and close the signup window
                new Login();
                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace(); // Print the stack trace in case of an exception
            }
        } else if (ae.getSource() == back) { // If Back button is clicked
            // Open the login window and close the signup window
            new Login();
            setVisible(false);
        }
    }
    
    // Main method to run the application
    public static void main(String[] args) {
        new SignUp();
    }
}
