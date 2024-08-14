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
    
    //declaring UI components
    JTextField tfFlightID, tfFlightCode, tfFlightName, tfSource, tfDestination;
    JButton addFlight, updateFlight, deleteFlight, fetchFlight;
    JTable flightTable;
    DefaultTableModel tableModel;
    
    //Constructor to set up the frame and its components
    public AdminFlightDetails() {
        getContentPane().setBackground(Color.WHITE); // Set background color of the frame
        setLayout(null); // Disable the layout manager
        
        setTitle("Manage Flights"); // Set the title of the window
        setSize(600, 600);  // Set the size of the window
        setLocation(100, 100); // Set the window location on the screen
        
        //adding a heading label
        JLabel heading = new JLabel("Manage Flights");
        heading.setBounds(200, 20, 200, 30);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 24)); // Set font for the heading
        add(heading);
        
        //Label and text field for Flight ID
        JLabel lblFlightID = new JLabel("Flight ID");
        lblFlightID.setBounds(50, 80, 100, 25);
        add(lblFlightID);
        
        tfFlightID = new JTextField();
        tfFlightID.setBounds(150, 80, 150, 25);
        add(tfFlightID);
        
        //Label and text field for Flight Code
        JLabel lblFlightCode = new JLabel("Flight Code");
        lblFlightCode.setBounds(50, 120, 100, 25);
        add(lblFlightCode);
        
        tfFlightCode = new JTextField();
        tfFlightCode.setBounds(150, 120, 150, 25);
        add(tfFlightCode);
        
        //Label and text field for Flight Name
        JLabel lblFlightName = new JLabel("Flight Name");
        lblFlightName.setBounds(50, 160, 100, 25);
        add(lblFlightName);
        
        tfFlightName = new JTextField();
        tfFlightName.setBounds(150, 160, 150, 25);
        add(tfFlightName);
        
        //Label and text field for Source
        JLabel lblSource = new JLabel("Source");
        lblSource.setBounds(50, 200, 100, 25);
        add(lblSource);
        
        tfSource = new JTextField();
        tfSource.setBounds(150, 200, 150, 25);
        add(tfSource);
        
        //Label and text field for Destination
        JLabel lblDestination = new JLabel("Destination");
        lblDestination.setBounds(50, 240, 100, 25);
        add(lblDestination);
        
        tfDestination = new JTextField();
        tfDestination.setBounds(150, 240, 150, 25);
        add(tfDestination);
        
        //Button to add a new flight
        addFlight = new JButton("Add Flight");
        addFlight.setBounds(50, 280, 100, 30);
        addFlight.setBackground(Color.BLACK);
        addFlight.setForeground(Color.WHITE);
        addFlight.addActionListener(this);
        add(addFlight);
        
        //Button to update an existing flight
        updateFlight = new JButton("Update Flight");
        updateFlight.setBounds(160, 280, 120, 30);
        updateFlight.setBackground(Color.BLACK);
        updateFlight.setForeground(Color.WHITE);
        updateFlight.addActionListener(this);
        add(updateFlight);
        
        //Button to delete a flight
        deleteFlight = new JButton("Delete Flight");
        deleteFlight.setBounds(290, 280, 120, 30);
        deleteFlight.setBackground(Color.BLACK);
        deleteFlight.setForeground(Color.WHITE);
        deleteFlight.addActionListener(this);
        add(deleteFlight);
        
        //Button to fetch flight details based on Flight ID
        fetchFlight = new JButton("Fetch Flight");
        fetchFlight.setBounds(420, 280, 120, 30);
        fetchFlight.setBackground(Color.BLACK);
        fetchFlight.setForeground(Color.WHITE);
        fetchFlight.addActionListener(this);
        add(fetchFlight);
        
        //Setup table for displaying flight data
        String[] columnNames = {"Flight ID", "Flight Code", "Flight Name", "Source", "Destination"};
        tableModel = new DefaultTableModel(columnNames, 0);
        flightTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(flightTable); // adding table to scroll pane
        scrollPane.setBounds(50, 320, 500, 200);  // Set size and position of scroll pane
        add(scrollPane);
        
        setVisible(true); // Make the frame visible
        
        //Fetch and display all flights in the table
        fetchAllFlights();
    }
    
    //method to fetch and display all flights in the table
    private void fetchAllFlights() {
        try {
            MyConnection conn = new MyConnection(); // Create a connection object
            String query = "SELECT * FROM flight"; // SQL query to fetch all flights
            ResultSet rs = conn.stm.executeQuery(query); // Execute the query
            
            //clear existing rows in the table model
            tableModel.setRowCount(0);
            
            //adding each flight to the table model
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
    
    //event handler for button clicks
    public void actionPerformed(ActionEvent ae) {
        String Flight_ID = tfFlightID.getText();
        String Flight_code = tfFlightCode.getText();
        String flightname = tfFlightName.getText();
        String source = tfSource.getText();
        String destination = tfDestination.getText();
        
        //handling the fetch flight operation
        if (ae.getSource() == fetchFlight) {
            if (Flight_ID.isEmpty()) { // Check if Flight ID is empty
                JOptionPane.showMessageDialog(null, "Please enter a flight ID to fetch details.");
                return;
            }
            
            try {
                MyConnection conn = new MyConnection();
                String query = "SELECT * FROM flight where Flight_ID = '" + Flight_ID + "'"; // SQL query to fetch flight by ID
                ResultSet rs = conn.stm.executeQuery(query);
                
                if (rs.next()) {
                    tfFlightCode.setText(rs.getString("Flight_code"));
                    tfFlightName.setText(rs.getString("flightname"));
                    tfSource.setText(rs.getString("Source"));
                    tfDestination.setText(rs.getString("Destination"));
                    
                    //updating table with fetched data
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
            //validating that all fields are filled
            if (Flight_ID.isEmpty() || Flight_code.isEmpty() || flightname.isEmpty() || source.isEmpty() || destination.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in all fields.");
                return;
            }
            
            try {
                MyConnection conn = new MyConnection();
                
                //handling add flight operation
                if (ae.getSource() == addFlight) {
                    String query = "INSERT INTO flight (Flight_ID, Flight_Code, flightname, Source, Destination) VALUES('" + Flight_ID + "', '" + Flight_code + "', '" + flightname + "', '" + source + "', '" + destination + "')";
                    conn.stm.executeUpdate(query); // Execute the insert query
                    JOptionPane.showMessageDialog(null, "Flight Added Successfully");
                    
                //handling update flight operation
                } else if (ae.getSource() == updateFlight) {
                    String query = "UPDATE flight SET Flight_code = '" + Flight_code + "', flightname = '" + flightname + "', Source = '" + source + "', Destination = '" + destination + "' WHERE Flight_ID = '" + Flight_ID + "'";
                    int rowsAffected = conn.stm.executeUpdate(query); // Execute the update query
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Flight Updated Successfully");
                    } else {
                        JOptionPane.showMessageDialog(null, "Flight ID Not Found");
                    }

                //handling delete flight operation
                } else if (ae.getSource() == deleteFlight) {
                    String query = "DELETE FROM flight WHERE Flight_ID = '" + Flight_ID + "'";
                    int rowsAffected = conn.stm.executeUpdate(query); // Execute the delete query
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Flight Deleted Successfully");
                    } else {
                        JOptionPane.showMessageDialog(null, "Flight ID Not Found");
                    }
                }
                
                //refreshing the table after any operation to reflect changes
                fetchAllFlights();
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    //main method to run the program
    public static void main(String[] args) {
        new AdminFlightDetails();
    }
}