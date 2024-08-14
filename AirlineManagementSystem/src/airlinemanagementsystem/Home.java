package airlinemanagementsystem;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame implements ActionListener {

    // Constructor for the Home class
    public Home() {
        setLayout(null); // Set the layout manager to null for absolute positioning
        
        // Load and add the background image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("airlinemanagementsystem/icons/front.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(0, 0, 1310, 680); // Set the bounds for the image
        add(image);
        
        // Add a heading label on the image
        JLabel heading = new JLabel("SUNRISE AIRLINE WELCOMES YOU");
        heading.setBounds(370, 40, 1000, 40); // Position the heading label
        heading.setForeground(Color.BLACK); // Set text color
        heading.setFont(new Font("Tahoma", Font.PLAIN, 36)); // Set font and size
        image.add(heading); // Add heading to the image
        
        // Create a menu bar and add it to the frame
        JMenuBar menubar = new JMenuBar();
        setJMenuBar(menubar);
        
        // Create the "Details" menu
        JMenu details = new JMenu("Details");
        menubar.add(details);
        
        // Add "Flight Details" menu item and set action listener
        JMenuItem flightDetails = new JMenuItem("Flight Details");
        flightDetails.addActionListener(this);
        details.add(flightDetails);
        
        // Add "Add Customer Details" menu item and set action listener
        JMenuItem customerDetails = new JMenuItem("Add Customer Details");
        customerDetails.addActionListener(this);
        details.add(customerDetails);
        
        // Add "View Your Details" menu item and set action listener
        JMenuItem viewYourDetails = new JMenuItem("View Your Details");
        viewYourDetails.addActionListener(this);
        details.add(viewYourDetails);
        
        // Add "Book Flight" menu item and set action listener
        JMenuItem bookFlight = new JMenuItem("Book Flight");
        bookFlight.addActionListener(this);
        details.add(bookFlight);
        
        // Add "Journey Details" menu item and set action listener
        JMenuItem journeyDetails = new JMenuItem("Journey Details");
        journeyDetails.addActionListener(this);
        details.add(journeyDetails);
        
        // Add "Cancel Ticket" menu item and set action listener
        JMenuItem ticketCancellation = new JMenuItem("Cancel Ticket");
        ticketCancellation.addActionListener(this);
        details.add(ticketCancellation);
        
        // Create the "Ticket" menu
        JMenu ticket = new JMenu("Ticket");
        menubar.add(ticket);
        
        // Add "Boarding Pass" menu item and set action listener
        JMenuItem boardingPass = new JMenuItem("Boarding Pass");
        boardingPass.addActionListener(this);
        ticket.add(boardingPass);
        
        // Create the "Logout" menu
        JMenu logout = new JMenu("Logout");
        menubar.add(logout);
        
        // Add "Logout" menu item and set action listener
        JMenuItem logoutItem = new JMenuItem("Logout");
        logoutItem.addActionListener(this);
        logout.add(logoutItem);
        
        // Set the window to full screen and make it visible
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }
    
    // Handle actions performed on the menu items
    public void actionPerformed(ActionEvent ae) {
        String text = ae.getActionCommand(); // Get the action command text
        
        // Open the appropriate window based on the menu item clicked
        if (text.equals("Add Customer Details")) {
            new AddCustomer();
        } else if (text.equals("View Your Details")) {
            new ViewCustomerDetails(); 
        } else if (text.equals("Flight Details")) {
            new FlightInfo();
        } else if (text.equals("Book Flight")) {
            new BookFlight();
        } else if (text.equals("Journey Details")) {
            new JourneyDetails();
        } else if (text.equals("Cancel Ticket")) {
            new Cancel();
        } else if (text.equals("Boarding Pass")) {
            new BoardingPass();
        } else if (text.equals("Logout")) {
            System.exit(0); // Exit the application
        }
    }
    
    // Main method to launch the application
    public static void main(String[] args) {
        new Home();
    }
}
