package airlinemanagementsystem;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;

public class AdminFlightDetails extends JFrame implements ActionListener {
    
    JTextField tfFlightID, tfFlightCode, tfFlightName, tfSource, tfDestination;
    JButton addFlight, updateFlight, deleteFlight, fetchFlight;
    JTable flightTable;
    DefaultTableModel tableModel;
    
    public AdminFlightDetails() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        setTitle("Manage Flights");
        setSize(600, 600);  // Increased height to accommodate table
        setLocation(100, 100);
        
        JLabel heading = new JLabel("Manage Flights");
        heading.setBounds(200, 20, 200, 30);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 24));
        add(heading);
        
        JLabel lblFlightID = new JLabel("Flight ID");
        lblFlightID.setBounds(50, 80, 100, 25);
        add(lblFlightID);
        
        tfFlightID = new JTextField();
        tfFlightID.setBounds(150, 80, 150, 25);
        add(tfFlightID);
        
        JLabel lblFlightCode = new JLabel("Flight Code");
        lblFlightCode.setBounds(50, 120, 100, 25);
        add(lblFlightCode);
        
        tfFlightCode = new JTextField();
        tfFlightCode.setBounds(150, 120, 150, 25);
        add(tfFlightCode);
        
        JLabel lblFlightName = new JLabel("Flight Name");
        lblFlightName.setBounds(50, 160, 100, 25);
        add(lblFlightName);
        
        tfFlightName = new JTextField();
        tfFlightName.setBounds(150, 160, 150, 25);
        add(tfFlightName);
        
        JLabel lblSource = new JLabel("Source");
        lblSource.setBounds(50, 200, 100, 25);
        add(lblSource);
        
        tfSource = new JTextField();
        tfSource.setBounds(150, 200, 150, 25);
        add(tfSource);
        
        JLabel lblDestination = new JLabel("Destination");
        lblDestination.setBounds(50, 240, 100, 25);
        add(lblDestination);
        
        tfDestination = new JTextField();
        tfDestination.setBounds(150, 240, 150, 25);
        add(tfDestination);
        
        addFlight = new JButton("Add Flight");
        addFlight.setBounds(50, 280, 100, 30);
        addFlight.setBackground(Color.BLACK);
        addFlight.setForeground(Color.WHITE);
        addFlight.addActionListener(this);
        add(addFlight);
        
        updateFlight = new JButton("Update Flight");
        updateFlight.setBounds(160, 280, 120, 30);
        updateFlight.setBackground(Color.BLACK);
        updateFlight.setForeground(Color.WHITE);
        updateFlight.addActionListener(this);
        add(updateFlight);
        
        deleteFlight = new JButton("Delete Flight");
        deleteFlight.setBounds(290, 280, 120, 30);
        deleteFlight.setBackground(Color.BLACK);
        deleteFlight.setForeground(Color.WHITE);
        deleteFlight.addActionListener(this);
        add(deleteFlight);
        
        fetchFlight = new JButton("Fetch Flight");
        fetchFlight.setBounds(420, 280, 120, 30);
        fetchFlight.setBackground(Color.BLACK);
        fetchFlight.setForeground(Color.WHITE);
        fetchFlight.addActionListener(this);
        add(fetchFlight);
        
        // Table setup
        String[] columnNames = {"Flight ID", "Flight Code", "Flight Name", "Source", "Destination"};
        tableModel = new DefaultTableModel(columnNames, 0);
        flightTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(flightTable);
        scrollPane.setBounds(50, 320, 500, 200);  // Adjust size and position as needed
        add(scrollPane);
        
        setVisible(true);
        
        // Initially populate table with all flights
        fetchAllFlights();
    }
    
    private void fetchAllFlights() {
        try {
            MyConnection conn = new MyConnection();
            String query = "SELECT * FROM flight";
            ResultSet rs = conn.stm.executeQuery(query);
            
            // Clear existing rows
            tableModel.setRowCount(0);
            
            while (rs.next()) {
                tableModel.addRow(new Object[]{
                    rs.getString("Flight_ID"),
                    rs.getString("Flight_code"),
                    rs.getString("flightname"),
                    rs.getString("Source"),
                    rs.getString("Destination")
                });
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void actionPerformed(ActionEvent ae) {
        String Flight_ID = tfFlightID.getText();
        String Flight_code = tfFlightCode.getText();
        String flightname = tfFlightName.getText();
        String source = tfSource.getText();
        String destination = tfDestination.getText();
        
        if (ae.getSource() == fetchFlight) {
            if (Flight_ID.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter a flight ID to fetch details.");
                return;
            }
            
            try {
                MyConnection conn = new MyConnection();
                String query = "SELECT * FROM flight where Flight_ID = '" + Flight_ID + "'";
                ResultSet rs = conn.stm.executeQuery(query);
                
                if (rs.next()) {
                    tfFlightCode.setText(rs.getString("Flight_code"));
                    tfFlightName.setText(rs.getString("flightname"));
                    tfSource.setText(rs.getString("Source"));
                    tfDestination.setText(rs.getString("Destination"));
                    
                    // Update table with fetched data
                    tableModel.setRowCount(0);  // Clear existing rows
                    tableModel.addRow(new Object[]{
                        rs.getString("Flight_ID"),
                        rs.getString("Flight_code"),
                        rs.getString("flightname"),
                        rs.getString("Source"),
                        rs.getString("Destination")
                    });
                } else {
                    JOptionPane.showMessageDialog(null, "Flight ID Not Found");
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            if (Flight_ID.isEmpty() || Flight_code.isEmpty() || flightname.isEmpty() || source.isEmpty() || destination.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in all fields.");
                return;
            }
            
            try {
                MyConnection conn = new MyConnection();
                
                if (ae.getSource() == addFlight) {
                    String query = "INSERT INTO flight (Flight_ID, Flight_Code, flightname, Source, Destination) VALUES('" + Flight_ID + "', '" + Flight_code + "', '" + flightname + "', '" + source + "', '" + destination + "')";
                    conn.stm.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "Flight Added Successfully");
                    
                } else if (ae.getSource() == updateFlight) {
                    String query = "UPDATE flight SET Flight_code = '" + Flight_code + "', flightname = '" + flightname + "', Source = '" + source + "', Destination = '" + destination + "' WHERE Flight_ID = '" + Flight_ID + "'";
                    int rowsAffected = conn.stm.executeUpdate(query);
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Flight Updated Successfully");
                    } else {
                        JOptionPane.showMessageDialog(null, "Flight ID Not Found");
                    }

                } else if (ae.getSource() == deleteFlight) {
                    String query = "DELETE FROM flight WHERE Flight_ID = '" + Flight_ID + "'";
                    int rowsAffected = conn.stm.executeUpdate(query);
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Flight Deleted Successfully");
                    } else {
                        JOptionPane.showMessageDialog(null, "Flight ID Not Found");
                    }
                }
                
                // Refresh the table after any operation
                fetchAllFlights();
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) {
        new AdminFlightDetails();
    }
}
