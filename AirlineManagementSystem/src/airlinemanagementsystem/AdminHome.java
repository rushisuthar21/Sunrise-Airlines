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
    
    public AdminHome() {
        setLayout(null);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("airlinemanagementsystem/icons/front.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(0, 0, 1310, 680);
        add(image);
        
        JLabel heading = new JLabel("ADMIN PAGE - SUNRISE AIRLINES");
        heading.setBounds(370, 40, 1000, 40);
        heading.setForeground(Color.BLACK);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 36));
        image.add(heading);
        
        JMenuBar menubar = new JMenuBar();
        setJMenuBar(menubar);
        
        JMenu details = new JMenu("Details");
        menubar.add(details);
        
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
        
        JMenu ticket = new JMenu("Ticket");
        menubar.add(ticket);
        
        JMenuItem ticketCancellation = new JMenuItem("Cancel Ticket");
        ticketCancellation.addActionListener(this);
        ticket.add(ticketCancellation);
        
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        String text = ae.getActionCommand();
        
        if (text.equals("Add Customer")) {
            new AddCustomer();
        } else if (text.equals("Add, Update, Delete Flights")) {
            new AdminFlightDetails();
        } else if (text.equals("Remove Customer")) {
            new RemoveCustomer();
        } else if (text.equals("View Customer Details")) {
            new ViewCustomer();
        } else if (text.equals("Cancel Ticket")) {
            new Cancel();
        }
    }
    
    public static void main(String[] args) {
        new AdminHome();
    }
}