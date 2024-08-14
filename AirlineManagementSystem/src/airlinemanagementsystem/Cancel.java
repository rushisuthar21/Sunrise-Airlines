package airlinemanagementsystem;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.util.Random;

// Cancel class extends JFrame and implements ActionListener for handling GUI and user actions
public class Cancel extends JFrame implements ActionListener {
    
    // Declare UI components
    JTextField tfpnr;
    JLabel tfname, cancellationno, lblfcode, lbldateoftravel;
    JButton fetchButton, flight, cancelButton;
    
    // Constructor to initialize the Cancel window and its components
    public Cancel() {
        // Set background color and layout of the window
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        // Set title of the window
        setTitle("Ticket Cancellation");
        
        // Create a Random object to generate a random cancellation number
        Random random = new Random();
        
        // Heading label for ticket cancellation
        JLabel heading = new JLabel("XXX-CANCEL TICKET-XXX");
        heading.setBounds(180, 20, 250, 35);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 32));
        add(heading);
        
        // Add an image to the window (e.g., cancellation icon)
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("airlinemanagementsystem/icons/cancel_1.jpg"));
        Image i2 = i1.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(470, 120, 250, 250);
        add(image);
        
        // Label and text field for PNR input
        JLabel lblaadhar = new JLabel("PNR Number");
        lblaadhar.setBounds(60, 80, 150, 25);
        lblaadhar.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblaadhar);
        
        tfpnr = new JTextField();
        tfpnr.setBounds(220, 80, 150, 25);
        add(tfpnr);
        
        // Button to fetch and display ticket details based on PNR
        fetchButton = new JButton("Show Details");
        fetchButton.setBackground(Color.BLACK);
        fetchButton.setForeground(Color.WHITE);
        fetchButton.setBounds(380, 80, 120, 25);
        fetchButton.addActionListener(this);
        add(fetchButton);
        
        // Labels to display the passenger's name, cancellation number, flight code, and date of travel
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(60, 130, 150, 25);
        lblname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblname);
        
        tfname = new JLabel();
        tfname.setBounds(220, 130, 150, 25);
        add(tfname);
        
        JLabel lblnationality = new JLabel("Cancellation No");
        lblnationality.setBounds(60, 180, 150, 25);
        lblnationality.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblnationality);
        
        cancellationno = new JLabel("" + random.nextInt(1000000));
        cancellationno.setBounds(220, 180, 150, 25);
        add(cancellationno);
        
        JLabel lbladdress = new JLabel("Flight Code");
        lbladdress.setBounds(60, 230, 150, 25);
        lbladdress.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lbladdress);
        
        lblfcode = new JLabel();
        lblfcode.setBounds(220, 230, 150, 25);
        add(lblfcode);
        
        JLabel lblgender = new JLabel("Date");
        lblgender.setBounds(60, 280, 150, 25);
        lblgender.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblgender);
        
        lbldateoftravel = new JLabel();
        lbldateoftravel.setBounds(220, 280, 150, 25);
        add(lbldateoftravel);
        
        // Button to cancel the ticket
        flight = new JButton("Cancel Ticket");
        flight.setBackground(Color.BLACK);
        flight.setForeground(Color.WHITE);
        flight.setBounds(120, 330, 120, 25);
        flight.addActionListener(this);
        add(flight);
        
        // Button to close the window
        cancelButton = new JButton("CLOSE");
        cancelButton.setBackground(Color.RED);
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setBounds(320, 330, 120, 25);
        cancelButton.addActionListener(this);
        add(cancelButton);
        
        // Set size and location of the window, and make it visible
        setSize(800, 450);
        setLocation(350, 150);
        setVisible(true);
    }
    
    // Action listener method to handle button click events
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == fetchButton) {
            // Fetch and display ticket details based on the entered PNR number
            String pnr = tfpnr.getText();
            
            try {
                // Establish a connection to the database
                MyConnection conn = new MyConnection();

                // Query to retrieve reservation details based on the PNR number
                String query = "select * from reservation where PNR = '"+pnr+"'";

                // Execute the query and process the result
                ResultSet rs = conn.stm.executeQuery(query);
                
                // If a matching record is found, populate the labels with the passenger's details
                if (rs.next()) {
                    tfname.setText(rs.getString("name")); 
                    lblfcode.setText(rs.getString("flightcode")); 
                    lbldateoftravel.setText(rs.getString("book_date"));
                } else {
                    //showing a message if the PNR is not found
                    JOptionPane.showMessageDialog(null, "Please enter correct PNR");                
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == flight) {
            //canceling the ticket and remove the reservation record from the database
            String name = tfname.getText();
            String pnr = tfpnr.getText();
            String cancelno = cancellationno.getText();
            String fcode = lblfcode.getText();
            String book_date = lbldateoftravel.getText();
            
            try {
                //establishing a connection to the database
                MyConnection conn = new MyConnection();

                //inserting cancellation details into the cancel table and delete the reservation record
                String query = "insert into cancel values('"+pnr+"', '"+name+"', '"+cancelno+"', '"+fcode+"', '"+book_date+"')";

                conn.stm.executeUpdate(query);
                conn.stm.executeUpdate("delete from reservation where PNR = '"+pnr+"'");
                
                //howing a confirmation message and close the window
                JOptionPane.showMessageDialog(null, "Ticket Cancelled");
                setVisible(false);
            
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == cancelButton) {
            //closing the window
            setVisible(false);
        }
    }

    //main method to create an instance of the Cancel class and display the window
    public static void main(String[] args) {
        new Cancel();
    }
}