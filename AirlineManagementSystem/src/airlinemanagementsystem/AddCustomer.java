package airlinemanagementsystem;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//adding Customer class that extends JFrame and implements ActionListener for event handling
public class AddCustomer extends JFrame implements ActionListener {
    
    //declaring text fields and radio buttons for capturing customer details
    JTextField tfname, tfphone, tfpassport, tfnationality, tfaddress;
    JRadioButton rbmale, rbfemale;
    
    //constructor for setting up the user interface components
    public AddCustomer() {
        //setting background color and layout of the frame
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        //setting the title of the window
        setTitle("ADD YOUR DETAILS");
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        //adding a heading label
        JLabel heading = new JLabel("ADD CUSTOMER DETAILS");
        heading.setBounds(220, 20, 500, 35);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 32));
        heading.setForeground(Color.BLUE);
        add(heading);
        
        //adding a label and text field for customer name
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(60, 80, 150, 25);
        lblname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblname);
        
        tfname = new JTextField();
        tfname.setBounds(220, 80, 150, 25);
        add(tfname);
        
        //adding a label and text field for customer nationality
        JLabel lblnationality = new JLabel("Nationality");
        lblnationality.setBounds(60, 130, 150, 25);
        lblnationality.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblnationality);
        
        tfnationality = new JTextField();
        tfnationality.setBounds(220, 130, 150, 25);
        add(tfnationality);
        
        //adding a label and text field for customer passport number
        JLabel lblpassport = new JLabel("Passport Number");
        lblpassport.setBounds(60, 180, 150, 25);
        lblpassport.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblpassport);
        
        tfpassport = new JTextField();
        tfpassport.setBounds(220, 180, 150, 25);
        add(tfpassport);
        
        //adding a label and text field for customer address
        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(60, 230, 150, 25);
        lbladdress.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lbladdress);
        
        tfaddress = new JTextField();
        tfaddress.setBounds(220, 230, 150, 25);
        add(tfaddress);
        
        //adding a label and radio buttons for customer gender selection
        JLabel lblgender = new JLabel("Gender");
        lblgender.setBounds(60, 280, 150, 25);
        lblgender.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblgender);
        
        ButtonGroup gendergroup = new ButtonGroup();
        
        rbmale = new JRadioButton("Male");
        rbmale.setBounds(220, 280, 70, 25);
        rbmale.setBackground(Color.WHITE);
        add(rbmale);
        
        rbfemale = new JRadioButton("Female");
        rbfemale.setBounds(300, 280, 70, 25);
        rbfemale.setBackground(Color.WHITE);
        add(rbfemale);
        
        gendergroup.add(rbmale);
        gendergroup.add(rbfemale);
        
        //adding a label and text field for customer contact number
        JLabel lblphone = new JLabel("Contact Number");
        lblphone.setBounds(60, 330, 150, 25);
        lblphone.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblphone);
        
        tfphone = new JTextField();
        tfphone.setBounds(220, 330, 150, 25);
        add(tfphone);
        
        //adding a save button to save customer details
        JButton save = new JButton("SAVE");
        save.setBackground(Color.BLACK);
        save.setForeground(Color.WHITE);
        save.setBounds(220, 380, 150, 30);
        save.addActionListener(this);
        add(save);
        
        //adding an image icon to the window
        ImageIcon image = new ImageIcon(ClassLoader.getSystemResource("airlinemanagementsystem/icons/emp.png"));
        JLabel lblimage = new JLabel(image);
        lblimage.setBounds(450, 80, 280, 400);
        add(lblimage);
        
        //setting the size, location, and visibility of the frame
        setSize(750, 500);
        setLocation(300, 110);
        setVisible(true);
    }
    
    // Action performed when the save button is clicked
    public void actionPerformed(ActionEvent ae) {
        // Retrieve user input from text fields and radio buttons
        String name = tfname.getText();
        String nationality = tfnationality.getText();
        String phone = tfphone.getText();
        String address = tfaddress.getText();
        String passport = tfpassport.getText();
        String gender = null;
        if (rbmale.isSelected()) {
            gender = "Male";
        } else {
            gender = "Female";
        }
        
        //validating user input and show error messages if any field is empty
        if (name.isEmpty() || nationality.isEmpty() || phone.isEmpty() || address.isEmpty() || passport.isEmpty() || gender == null) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        //validating the phone number (must be 10 digits)
        if (!phone.matches("\\d{10}")) {
            JOptionPane.showMessageDialog(null, "Enter a valid 10-digit Contact Number.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        //validating the passport number (letters and numbers only)
        if (!passport.matches("[A-Za-z0-9]+")) {
            JOptionPane.showMessageDialog(null, "Enter a Valid Passport Number (letters and numbers only).", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            //establishing a database connection
            MyConnection conn = new MyConnection();
            
            //inserting customer details into the database
            String query = "insert into passenger values('"+name+"', '"+nationality+"', '"+phone+"', '"+address+"', '"+passport+"', '"+gender+"')";
            conn.stm.executeUpdate(query);
            
            //shows success message
            JOptionPane.showMessageDialog(null, "Customer Details Added Successfully");
        
            //closing the form after saving
            setVisible(false);
        } catch (Exception e) {
            // Handle any errors that occur during database interaction
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error Saving Customer Details. Please Try Again.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //main method to run the AddCustomer form
    public static void main(String[] args) {
        new AddCustomer();
    }
}