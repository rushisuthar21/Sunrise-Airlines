package airlinemanagementsystem;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Font;
import java.sql.ResultSet;

public class ViewCustomer extends JFrame {
    
    JTable table;

    public ViewCustomer() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        setTitle("VIEW CUSTOMER DETAILS");
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel heading = new JLabel("VIEW CUSTOMER DETAILS");
        heading.setBounds(180, 20, 500, 35);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 32));
        heading.setForeground(Color.BLUE);
        add(heading);
        
        String[] columns = {"Name", "Nationality", "Phone", "Address", "Passport", "Gender"};
        String[][] data = new String[20][6];
        
        try {
            MyConnection conn = new MyConnection();
            
            String query = "select * from passenger";
            ResultSet rs = conn.stm.executeQuery(query);
            
            int row = 0;
            while (rs.next()) {
                data[row][0] = rs.getString("name");
                data[row][1] = rs.getString("nationality");
                data[row][2] = rs.getString("phone");
                data[row][3] = rs.getString("address");
                data[row][4] = rs.getString("passport");
                data[row][5] = rs.getString("gender");
                row++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        table = new JTable(data, columns);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(40, 100, 600, 300);
        add(sp);
        
        setSize(700, 500);
        setLocation(300, 100);
        setVisible(true);
    }

    public static void main(String[] args) {
        new ViewCustomer();
    }
}