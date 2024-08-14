package airlinemanagementsystem;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminHome extends JFrame implements ActionListener{
    
    //Constructor for the AdminHome class
    public AdminHome() {
        //setting the layout to null for absolute positioning of components
        setLayout(null);
        
        //loading and adding the background image to the frame
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("airlinemanagementsystem/icons/front.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(0, 0, 1310, 680);  // Set the position and size of the image
        add(image);  // Add the image to the frame
        
        // creating and add a heading label on the background image
        JLabel heading = new JLabel("ADMIN PAGE - SUNRISE AIRLINES");
        heading.setBounds(370, 40, 1000, 40);  // Set position and size of the heading
        heading.setForeground(Color.BLACK);  // Set the text color
        heading.setFont(new Font("Tahoma", Font.PLAIN, 36));  // Set the font and size
        image.add(heading);  // Add the heading label to the image
        
        // creating a menu bar and add it to the frame
        JMenuBar menubar = new JMenuBar();
        setJMenuBar(menubar);
        
        // creating a "Details" menu and add it to the menu bar
        JMenu details = new JMenu("Details");
        menubar.add(details);
        
        // creating menu items for the "Details" menu and add action listeners to them
        JMenuItem AdminflightDetails = new JMenuItem("Add, Update, Delete Flights");
        AdminflightDetails.addActionListener(this);
        details.add(AdminflightDetails);
        
        JMenuItem customerDetails = new JMenuItem("Add Customer");
        customerDetails.addActionListener(this);
        details.add(customerDetails);
        
        JMenuItem RemoveCustomer = new JMenuItem("Remove Customer");
        RemoveCustomer.addActionListener(this);
        details.add(RemoveCustomer);
        
        JMenuItem CustomerDetails = new JMenuItem("View Customer Details");
        CustomerDetails.addActionListener(this);
        details.add(CustomerDetails);
        
        //creating a "Ticket" menu and add it to the menu bar
        JMenu ticket = new JMenu("Ticket");
        menubar.add(ticket);
        
        //creating a menu item for ticket cancellation and add an action listener to it
        JMenuItem ticketCancellation = new JMenuItem("Cancel Ticket");
        ticketCancellation.addActionListener(this);
        ticket.add(ticketCancellation);
        
        //creating a "Logout" menu and add it to the menu bar
        JMenu logout = new JMenu("Logout");
        menubar.add(logout);
        
        //creating a logout menu item and add an action listener to it
        JMenuItem logoutItem = new JMenuItem("Logout");
        logoutItem.addActionListener(this);
        logout.add(logoutItem);
        
        //setting the frame to be maximized on the screen and make it visible
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }
    
    //handling the actions performed when menu items are clicked
    public void actionPerformed(ActionEvent ae) {
        String text = ae.getActionCommand();  // Get the text of the clicked menu item
        
        //checking which menu item was clicked and open the corresponding window
        if (text.equals("Add Customer")) {
            new AddCustomer();  // Open the AddCustomer window
        } else if (text.equals("Add, Update, Delete Flights")) {
            new AdminFlightDetails();  // Open the AdminFlightDetails window
        } else if (text.equals("Remove Customer")) {
            new RemoveCustomer();  // Open the RemoveCustomer window
        } else if (text.equals("View Customer Details")) {
            new ViewCustomer();  // Open the ViewCustomer window
        } else if (text.equals("Cancel Ticket")) {
            new Cancel();  // Open the Cancel window
        } else if (text.equals("Logout")) {
            System.exit(0);  // Exit the application
        }
    }
    
    //main method to create an instance of AdminHome and display it
    public static void main(String[] args) {
        new AdminHome();  // creating and show the AdminHome window
    }
}