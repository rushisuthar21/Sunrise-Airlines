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
    JTextField tfusername;
    JPasswordField tfpassword;
    JComboBox<String> roleComboBox;
    JButton submit, back;

    public SignUp() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        setTitle("Register");
        
        JLabel lblusername = new JLabel("Username");
        lblusername.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblusername.setBounds(20, 20, 100, 20);
        add(lblusername);

        tfusername = new JTextField();
        tfusername.setBounds(130, 20, 200, 20);
        add(tfusername);

        JLabel lblpassword = new JLabel("Password");
        lblpassword.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblpassword.setBounds(20, 60, 100, 20);
        add(lblpassword);

        tfpassword = new JPasswordField();
        tfpassword.setBounds(130, 60, 200, 20);
        add(tfpassword);

        JLabel lblRole = new JLabel("Role");
        lblRole.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblRole.setBounds(20, 100, 100, 20);
        add(lblRole);

        String[] roles = { "User", "Admin" };
        roleComboBox = new JComboBox<>(roles);
        roleComboBox.setBounds(130, 100, 200, 20);
        add(roleComboBox);

        submit = new JButton("Submit");
        submit.setBounds(40, 160, 120, 20);
        submit.addActionListener(this);
        add(submit);

        back = new JButton("Back");
        back.setBounds(190, 160, 120, 20);
        back.addActionListener(this);
        add(back);

        setSize(400, 250);
        setLocation(600, 250);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            String username = tfusername.getText();
            String password = new String(tfpassword.getPassword());
            String role = (String) roleComboBox.getSelectedItem();
            
            if (username.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Username cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (!password.matches("^(?=.*[0-9])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?])[a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]{5,}$")) {
                JOptionPane.showMessageDialog(this, "Password must be at least 5 characters long, and include at least one special character and one number.", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Exit the method if validation fails
            }

            try {
                MyConnection c = new MyConnection();
                String query = "insert into login (username, password, role) values ('" + username + "', '" + password + "', '" + role + "')";
                c.stm.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Account Created Successfully");
                new Login();
                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == back) {
            new Login();
            setVisible(false);
        }
    }
    
    public static void main(String[] args) {
        new SignUp();
    }
}
