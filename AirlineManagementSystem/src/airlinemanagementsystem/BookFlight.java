package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import javax.swing.ImageIcon;

public class BookFlight extends JFrame implements ActionListener {
    
    // Declare the GUI components
    JTextField tfpassport;
    JLabel tfname, tfnationality, tfaddress, labelgender, labelfname, labelfcode;
    JButton bookflight, fetchButton, flight;
    Choice source, destination;
    JDateChooser dcdate;
    
    // Constructor for the BookFlight class
    public BookFlight() {
        // Set background color and layout
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        setTitle("Book Your Flight");  // Set the window title
        
        // Add a heading label
        JLabel heading = new JLabel("Book Flight");
        heading.setBounds(420, 20, 500, 40);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 32));
        heading.setForeground(Color.BLUE);
        add(heading);
        
        // Passport number input
        JLabel lblpassport = new JLabel("Passport Number");
        lblpassport.setBounds(60, 80, 150, 25);
        lblpassport.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblpassport);
        
        tfpassport = new JTextField();
        tfpassport.setBounds(220, 80, 150, 25);
        add(tfpassport);
        
        // Button to fetch user details based on passport number
        fetchButton = new JButton("Fetch User");
        fetchButton.setBackground(Color.BLACK);
        fetchButton.setForeground(Color.WHITE);
        fetchButton.setBounds(380, 80, 120, 25);
        fetchButton.addActionListener(this);
        add(fetchButton);
        
        // Label to display user name
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(60, 130, 150, 25);
        lblname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblname);
        
        tfname = new JLabel();
        tfname.setBounds(220, 125, 150, 35);
        add(tfname);
        
        // Label to display nationality
        JLabel lblnationality = new JLabel("Nationality");
        lblnationality.setBounds(60, 180, 150, 25);
        lblnationality.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblnationality);
        
        tfnationality = new JLabel();
        tfnationality.setBounds(220, 180, 150, 25);
        add(tfnationality);
        
        // Label to display address
        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(60, 230, 150, 25);
        lbladdress.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lbladdress);
        
        tfaddress = new JLabel();
        tfaddress.setBounds(220, 230, 150, 25);
        add(tfaddress);
        
        // Label to display gender
        JLabel lblgender = new JLabel("Gender");
        lblgender.setBounds(60, 280, 150, 25);
        lblgender.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblgender);
        
        labelgender = new JLabel();
        labelgender.setBounds(220, 280, 150, 25);
        add(labelgender);
        
        // Dropdown for selecting the source location
        JLabel lblsource = new JLabel("Source");
        lblsource.setBounds(60, 330, 150, 25);
        lblsource.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblsource);
        
        source = new Choice();
        source.setBounds(220, 330, 150, 25);       
        add(source);
        
        // Dropdown for selecting the destination location
        JLabel lbldest = new JLabel("Destination");
        lbldest.setBounds(60, 380, 150, 25);
        lbldest.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lbldest);
        
        destination = new Choice();
        destination.setBounds(220, 380, 150, 25);       
        add(destination);
        
        // Populate source and destination dropdowns from the flight database
        try {
            MyConnection c = new MyConnection();
            String query = "select * from flight";
            ResultSet rs = c.stm.executeQuery(query);
            
            while(rs.next()) {
                source.add(rs.getString("source"));
                destination.add(rs.getString("destination"));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Button to fetch available flights based on source and destination
        flight = new JButton("Fetch Flights");
        flight.setBackground(Color.BLACK);
        flight.setForeground(Color.WHITE);
        flight.setBounds(380, 380, 120, 25);
        flight.addActionListener(this);
        add(flight);
        
        // Label to display flight name
        JLabel lblfname = new JLabel("Flight Name");
        lblfname.setBounds(60, 430, 150, 25);
        lblfname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblfname);
        
        labelfname = new JLabel();
        labelfname.setBounds(220, 430, 150, 25);
        add(labelfname);
        
        // Label to display flight code
        JLabel lblfcode = new JLabel("Flight Code");
        lblfcode.setBounds(60, 480, 150, 25);
        lblfcode.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblfcode);
        
        labelfcode = new JLabel();
        labelfcode.setBounds(220, 480, 150, 25);
        add(labelfcode);
        
        // Date picker for selecting travel date
        JLabel lbldate = new JLabel("Date of Travel");
        lbldate.setBounds(60, 530, 150, 25);
        lbldate.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lbldate);
        
        dcdate = new JDateChooser();
        dcdate.setBounds(220, 530, 150, 25);
        add(dcdate);
        
        // Add an image to the GUI
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("airlinemanagementsystem/icons/details.jpg"));
        Image i2 = i1.getImage().getScaledInstance(350, 220, Image.SCALE_DEFAULT);
        ImageIcon image = new ImageIcon(i2);
        JLabel lblimage = new JLabel(image);
        lblimage.setBounds(485, 80, 420, 390);
        add(lblimage);
        
        // Button to book the flight
        bookflight = new JButton("Book Flight");
        bookflight.setBackground(Color.BLACK);
        bookflight.setForeground(Color.WHITE);
        bookflight.setBounds(220, 580, 150, 25);
        bookflight.addActionListener(this);
        add(bookflight);
        
        // Set the size and location of the frame and make it visible
        setSize(990, 660);
        setLocation(100, 6);
        setVisible(true);
    }
    
    // Handle actions performed on the buttons
    public void actionPerformed(ActionEvent ae) {
        // Fetch user details when "Fetch User" button is clicked
        if (ae.getSource() == fetchButton) {
            String passport = tfpassport.getText();
            
            if (passport.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter a passport number.");
                return;
            }
            
            if (source.equals(destination)) {
                JOptionPane.showMessageDialog(null, "Source and destination cannot be the same.");
                return;
            }
            
            try {
                MyConnection conn = new MyConnection();
                String query = "select * from passenger where passport = '"+passport+"'";
                ResultSet rs = conn.stm.executeQuery(query);
                
                if (rs.next()) {
                    tfname.setText(rs.getString("name")); 
                    tfnationality.setText(rs.getString("nationality")); 
                    tfaddress.setText(rs.getString("address"));
                    labelgender.setText(rs.getString("gender"));
                } else {
                    JOptionPane.showMessageDialog(null, "Please Enter Correct Passport Number !!!");                
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        // Fetch available flights when "Fetch Flights" button is clicked
        } else if (ae.getSource() == flight) {
            String Source = source.getSelectedItem();
            String Destination = destination.getSelectedItem();
            try {
                MyConnection conn = new MyConnection();
                String query = "select * from flight where source = '"+Source+"' and destination = '"+Destination+"'";
                ResultSet rs = conn.stm.executeQuery(query);
                
                if (rs.next()) {
                    labelfname.setText(rs.getString("flightname")); 
                    labelfcode.setText(rs.getString("Flight_code")); 
                } else {
                    JOptionPane.showMessageDialog(null, "No Flights Found");                
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        // Book the flight when "Book Flight" button is clicked
        } else if (ae.getSource() == bookflight) {
            Random random = new Random();
            
            String passport = tfpassport.getText();
            String name = tfname.getText(); 
            String nationality = tfnationality.getText();
            String flightname = labelfname.getText(); 
            String flightcode = labelfcode.getText();
            String departure = source.getSelectedItem(); 
            String destination1 = destination.getSelectedItem();
            Date date = dcdate.getDate();
            
            // Format the date to 'YYYY-MM-DD'
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String book_date = sdf.format(date);
            
            try {
                MyConnection conn = new MyConnection();
                String query = "insert into reservation values('PNR-"+random.nextInt(1000000)+"', 'TICKET-"+random.nextInt(10000)+"', '"+passport+"', '"+name+"', '"+nationality+"', '"+flightname+"', '"+flightcode+"', '"+departure+"', '"+destination1+"', '"+book_date+"')";
                conn.stm.executeUpdate(query);
                
                JOptionPane.showMessageDialog(null, "Ticket Booked Successfully");
                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //main method to launch the application
    public static void main(String[] args) {
        new BookFlight();
    }
}
