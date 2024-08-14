package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

public class ViewCustomerDetails extends JFrame implements ActionListener {
    
    JTextField tfpassport;
    JButton btnView;
    JTable table;
    DefaultTableModel tableModel;

    public ViewCustomerDetails() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        setTitle("VIEW CUSTOMER DETAILS");
        
        JLabel heading = new JLabel("VIEW CUSTOMER DETAILS");
        heading.setBounds(180, 20, 400, 35);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 32));
        heading.setForeground(Color.BLUE);
        add(heading);
        
        JLabel lblpassport = new JLabel("Passport Number");
        lblpassport.setBounds(60, 80, 150, 25);
        lblpassport.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblpassport);
        
        tfpassport = new JTextField();
        tfpassport.setBounds(220, 80, 150, 25);
        add(tfpassport);
        
        btnView = new JButton("VIEW DETAILS");
        btnView.setBackground(Color.BLACK);
        btnView.setForeground(Color.WHITE);
        btnView.setBounds(220, 120, 150, 30);
        btnView.addActionListener(this);
        add(btnView);
        
        // Table to display customer details
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new Object[] {"Field", "Value"});
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(60, 180, 600, 250);
        add(scrollPane);
        
        setSize(750, 500);
        setLocation(300, 110);
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
            
            String query = "SELECT * FROM passenger WHERE passport = ?";
            PreparedStatement pst = conn.con.prepareStatement(query);
            pst.setString(1, passport);
            ResultSet rs = pst.executeQuery();
            
            // Clear the table model
            tableModel.setRowCount(0);
            
            if (rs.next()) {
                String name = rs.getString("name");
                String nationality = rs.getString("nationality");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                String gender = rs.getString("gender");
                
                // Add data to the table model
                tableModel.addRow(new Object[] {"Name", name});
                tableModel.addRow(new Object[] {"Nationality", nationality});
                tableModel.addRow(new Object[] {"Contact Number", phone});
                tableModel.addRow(new Object[] {"Address", address});
                tableModel.addRow(new Object[] {"Passport Number", passport});
                tableModel.addRow(new Object[] {"Gender", gender});
            } else {
                JOptionPane.showMessageDialog(null, "No records found for the given Passport Number.", "No Record", JOptionPane.INFORMATION_MESSAGE);
            }
            
            pst.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error retrieving customer details. Please try again.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void main(String[] args) {
        new ViewCustomerDetails();
    }
}
