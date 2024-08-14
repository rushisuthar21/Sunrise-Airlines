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
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

// The Login class extends JFrame and implements ActionListener to handle login functionality
public class Login extends JFrame implements ActionListener {
    JButton submit, close, signup;  // Buttons for login, closing, and signup actions
    JTextField tfusername;  // Text field for entering the username
    JPasswordField tfpassword;  // Password field for entering the password
    JComboBox<String> roleComboBox;  // Dropdown for selecting the role (User or Admin)

    // Constructor to initialize the login window and its components
    public Login() {
        setTitle("Sunrise Airlines");  // Set the title of the window
        getContentPane().setBackground(Color.WHITE);  // Set the background color of the window
        setLayout(null);  // Use absolute layout

        // Header label
        JLabel header = new JLabel("Welcome to Sunrise Airlines...", JLabel.CENTER);
        header.setFont(new Font("Tahoma", Font.BOLD, 30));  // Set font for the header
        header.setBounds(0, 10, 950, 90);  // Set position and size of the header
        add(header);

        // Airline logo image
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("airlinemanagementsystem/icons/plane.png"));
        JLabel imageLabel = new JLabel(icon);
        imageLabel.setBounds(450, 80, 620, 260);  // Set position and size of the image
        add(imageLabel);

        // Username label and text field
        JLabel lblusername = new JLabel("Username");
        lblusername.setFont(new Font("Tahoma", Font.BOLD, 16));  // Set font for the username label
        lblusername.setBounds(20, 180, 100, 20);  // Set position and size of the username label
        add(lblusername);

        tfusername = new JTextField();
        tfusername.setBounds(130, 180, 200, 20);  // Set position and size of the username text field
        add(tfusername);

        // Password label and password field
        JLabel lblpassword = new JLabel("Password");
        lblpassword.setFont(new Font("Tahoma", Font.BOLD, 16));  // Set font for the password label
        lblpassword.setBounds(20, 220, 100, 20);  // Set position and size of the password label
        add(lblpassword);

        tfpassword = new JPasswordField();
        tfpassword.setBounds(130, 220, 200, 20);  // Set position and size of the password field
        add(tfpassword);

        // Role label and combo box
        JLabel lblrole = new JLabel("Role");
        lblrole.setFont(new Font("Tahoma", Font.BOLD, 16));  // Set font for the role label
        lblrole.setBounds(20, 260, 100, 20);  // Set position and size of the role label
        add(lblrole);

        String[] roles = {"User", "Admin"};
        roleComboBox = new JComboBox<>(roles);  // Create combo box with role options
        roleComboBox.setBounds(130, 260, 200, 20);  // Set position and size of the role combo box
        add(roleComboBox);

        // Login button
        submit = new JButton("Login");
        submit.setBounds(370, 260, 120, 20);  // Set position and size of the login button
        submit.addActionListener(this);  // Add action listener to handle button click
        add(submit);

        // Signup button
        signup = new JButton("Signup");
        signup.setBounds(40, 300, 120, 20);  // Set position and size of the signup button
        signup.addActionListener(this);  // Add action listener to handle button click
        add(signup);

        // Close button
        close = new JButton("Close");
        close.setBounds(190, 300, 120, 20);  // Set position and size of the close button
        close.addActionListener(this);  // Add action listener to handle button click
        add(close);

        // Set size, location, and visibility of the window
        setSize(1100, 400);
        setLocation(100, 100);
        setVisible(true);
    }

    // Handle button click events
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {  // If the login button is clicked
            String username = tfusername.getText();  // Get the entered username
            String password = new String(tfpassword.getPassword());  // Get the entered password
            String role = (String) roleComboBox.getSelectedItem();  // Get the selected role

            try {
                // Validate inputs
                if (username.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Username cannot be empty.");
                    return;
                }
                if (password.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Password cannot be empty.");
                    return;
                }
                if (role == null) {
                    JOptionPane.showMessageDialog(null, "Please select a role.");
                    return;
                }
                
                // Establish a connection to the database and check credentials
                MyConnection c = new MyConnection();
                String query = "select * from login where username = '"+username+"' and password = '"+password+"' and role = '"+role+"'";
                ResultSet rs = c.stm.executeQuery(query);
                
                // If credentials are valid, open the appropriate home screen
                if (rs.next()) {
                    if (role.equals("Admin")) {
                        new AdminHome();  // Open admin home screen
                    } else {
                        new Home();  // Open regular user home screen
                    }
                    setVisible(false);  // Close the login window
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Username, Password, or Role");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == close) {  // If the close button is clicked
            setVisible(false);  // Close the login window
        } else if (ae.getSource() == signup) {  // If the signup button is clicked
            new SignUp();  // Open the signup window
            setVisible(false);  // Close the login window
        }
    }

    // Main method to create an instance of Login and display the window
    public static void main(String[] args) {
        new Login();
    }
}
