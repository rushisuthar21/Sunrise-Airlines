// Login.java
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

public class Login extends JFrame implements ActionListener {
    JButton submit, close, signup;
    JTextField tfusername;
    JPasswordField tfpassword;
    JComboBox<String> roleComboBox;

    public Login() {
        setTitle("Sunrise Airlines");
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel header = new JLabel("Welcome to Sunrise Airlines...", JLabel.CENTER);
        header.setFont(new Font("Tahoma", Font.BOLD, 30));
        header.setBounds(0, 10, 950, 90);
        add(header);
        
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("airlinemanagementsystem/icons/plane.png"));
        JLabel imageLabel = new JLabel(icon);
        imageLabel.setBounds(450, 80, 620, 260);
        add(imageLabel);


        JLabel lblusername = new JLabel("Username");
        lblusername.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblusername.setBounds(20, 180, 100, 20);
        add(lblusername);

        tfusername = new JTextField();
        tfusername.setBounds(130, 180, 200, 20);
        add(tfusername);

        JLabel lblpassword = new JLabel("Password");
        lblpassword.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblpassword.setBounds(20, 220, 100, 20);
        add(lblpassword);

        tfpassword = new JPasswordField();
        tfpassword.setBounds(130, 220, 200, 20);
        add(tfpassword);
        
        JLabel lblrole = new JLabel("Role");
        lblrole.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblrole.setBounds(20, 260, 100, 20);
        add(lblrole);

        String[] roles = {"User", "Admin"};
        roleComboBox = new JComboBox<>(roles);
        roleComboBox.setBounds(130, 260, 200, 20);
        add(roleComboBox);

        submit = new JButton("Login");
        submit.setBounds(370, 260, 120, 20);
        submit.addActionListener(this);
        add(submit);

        signup = new JButton("Signup");
        signup.setBounds(40, 300, 120, 20);
        signup.addActionListener(this);
        add(signup);

        close = new JButton("Close");
        close.setBounds(190, 300, 120, 20); 
        close.addActionListener(this);
        add(close);

        setSize(1100, 400);
        setLocation(100, 100);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            String username = tfusername.getText();
            String password = new String(tfpassword.getPassword());
            String role = (String) roleComboBox.getSelectedItem();

            try {
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
                
                MyConnection c = new MyConnection();
                String query = "select * from login where username = '"+username+"' and password = '"+password+"' and role = '"+role+"'";
                ResultSet rs = c.stm.executeQuery(query);
                if (rs.next()) {
                    if (role.equals("Admin")) {
                        new AdminHome(); // Open admin home screen
                    } else {
                        new Home(); // Open regular user home screen
                    }
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Username, Password, or Role");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == close) {
            setVisible(false);
        } else if (ae.getSource() == signup) {
            new SignUp();
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
