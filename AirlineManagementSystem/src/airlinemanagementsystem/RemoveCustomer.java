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


public class RemoveCustomer extends JFrame implements ActionListener {
    
    JTextField tfpassport;
    
    public RemoveCustomer() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        setTitle("REMOVE CUSTOMER DETAILS");
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel heading = new JLabel("REMOVE CUSTOMER DETAILS");
        heading.setBounds(180, 20, 500, 35);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 32));
        heading.setForeground(Color.RED);
        add(heading);
        
        JLabel lblpassport = new JLabel("Passport Number");
        lblpassport.setBounds(60, 130, 150, 25);
        lblpassport.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblpassport);
        
        tfpassport = new JTextField();
        tfpassport.setBounds(220, 130, 150, 25);
        add(tfpassport);
        
        JButton remove = new JButton("REMOVE");
        remove.setBackground(Color.BLACK);
        remove.setForeground(Color.WHITE);
        remove.setBounds(220, 180, 150, 30);
        remove.addActionListener(this);
        add(remove);
        
        setSize(700, 300);
        setLocation(300, 150);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        String passport = tfpassport.getText();
        
        if (passport.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter the Passport Number.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (!passport.matches("[A-Za-z0-9]+")) {
            JOptionPane.showMessageDialog(null, "Enter a Valid Passport Number (letters and numbers only).", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            MyConnection conn = new MyConnection();
            
            String query = "delete from passenger where passport = '"+passport+"'";
            
            int result = conn.stm.executeUpdate(query);
            
            if (result > 0) {
                JOptionPane.showMessageDialog(null, "Customer Details Removed Successfully");
            } else {
                JOptionPane.showMessageDialog(null, "No Customer Found with the Given Passport Number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error Removing Customer Details. Please Try Again.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new RemoveCustomer();
    }
}
