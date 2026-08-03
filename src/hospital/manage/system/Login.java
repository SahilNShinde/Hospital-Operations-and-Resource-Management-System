package hospital.manage.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
// import java.util.concurrent.atomic.AtomicReference; // Not used, removed

public class Login extends JFrame implements ActionListener {

    // Define professional colors
    private static final Color PRIMARY_BLUE = new Color(0, 102, 204); // Deep professional blue
    private static final Color HOVER_BLUE = new Color(0, 128, 255);  // Slightly lighter blue for hover/focus
    private static final Color BACKGROUND_LIGHT = Color.WHITE; // Clean white background
    private static final Color TEXT_DARK = new Color(51, 51, 51); // Dark gray for text

    JTextField textField;
    JPasswordField jPasswordField;
    JButton b1, b2;

    Login() {
        setTitle("Hospital Management System - Login"); // Set a title

        // Use a more professional font
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font fieldFont = new Font("Arial", Font.PLAIN, 14);
        Font buttonFont = new Font("Arial", Font.BOLD, 14);

        // --- Username Label and Field ---
        JLabel namelabel = new JLabel("Username:");
        namelabel.setBounds(40, 50, 100, 30);
        namelabel.setFont(labelFont);
        namelabel.setForeground(TEXT_DARK);
        add(namelabel);

        textField = new JTextField();
        textField.setBounds(140, 50, 200, 30);
        textField.setFont(fieldFont);
        textField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY)); // Add a subtle border
        textField.setBackground(Color.WHITE);
        add(textField);

        // --- Password Label and Field ---
        JLabel password = new JLabel("Password:");
        password.setBounds(40, 100, 100, 30);
        password.setFont(labelFont);
        password.setForeground(TEXT_DARK);
        add(password);

        jPasswordField = new JPasswordField();
        jPasswordField.setBounds(140, 100, 200, 30);
        jPasswordField.setFont(fieldFont);
        jPasswordField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY)); // Add a subtle border
        jPasswordField.setBackground(Color.WHITE);
        add(jPasswordField);

        // --- Login Button ---
        b1 = new JButton("Login");
        b1.setBounds(40, 170, 140, 35);
        b1.setFont(buttonFont);
        b1.setBackground(PRIMARY_BLUE);
        b1.setForeground(Color.WHITE);
        b1.setFocusPainted(false); // Remove focus border
        b1.setBorder(BorderFactory.createEmptyBorder()); // Remove button border
        b1.addActionListener(this);
        add(b1);

        // --- Cancel Button ---
        b2 = new JButton("Cancel");
        b2.setBounds(200, 170, 140, 35);
        b2.setFont(buttonFont);
        b2.setBackground(TEXT_DARK); // Darker button for a secondary action
        b2.setForeground(Color.WHITE);
        b2.setFocusPainted(false);
        b2.setBorder(BorderFactory.createEmptyBorder());
        b2.addActionListener(this);
        add(b2);

        // --- Image/Logo Display (Adjusted for better fit) ---
        try {
            ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icons/CITY_HOSPITAL__2_-removebg-preview.png"));
            // Reduce image size for better integration, focusing on a logo
            Image i1 = imageIcon.getImage().getScaledInstance(280, 280, Image.SCALE_SMOOTH);
            ImageIcon imageIcon1 = new ImageIcon(i1);
            JLabel label = new JLabel(imageIcon1);
            // Positioned the image to the right of the login form
            label.setBounds(350, 0, 350, 280);
            add(label);
        } catch (Exception ex) {
            System.err.println("Image not found: icons/CITY_HOSPITAL__2_-removebg-preview.png");
            // Add a placeholder label if image fails to load
            JLabel placeholder = new JLabel("HMS Logo");
            placeholder.setBounds(450, 100, 200, 50);
            placeholder.setFont(new Font("Arial", Font.BOLD, 20));
            add(placeholder);
        }

        // --- Frame Settings ---
        getContentPane().setBackground(BACKGROUND_LIGHT); // Set the clean background
        setSize(750, 300);
        setLocationRelativeTo(null); // Center the frame on the screen
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Important for application exit
        setResizable(false); // Login screens are typically fixed size
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            try {
                // Ensure 'conn' class exists and is correctly setting up the connection/statement
                conn c = new conn();
                String user = textField.getText();
                // **SECURITY NOTE**: jPasswordField.getText() is deprecated for security reasons.
                // For a real application, you should use getPassword() and convert the char array to a String for comparison,
                // or preferably, use a secure hashing mechanism (like bcrypt) for password storage and verification.
                String Pass = new String(jPasswordField.getPassword());

                String q = "select * from login where ID = '"+user+"'  and PW = '"+Pass+"'";

                // **SECURITY NOTE**: This SQL query is vulnerable to SQL Injection.
                // In a real application, you must use a PreparedStatement instead of simple String concatenation.

                ResultSet resultSet = c.statement.executeQuery(q);

                if (resultSet.next()) {
                    new reception(); // Assumes 'reception' is your next main JFrame
                    setVisible(false);
                    dispose(); // Free up resources after closing
                } else {
                    // Use a more descriptive error message
                    JOptionPane.showMessageDialog(this, "Invalid Username or Password. Please try again.", "Login Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (Exception E) {
                // Better error handling for database/connection issues
                JOptionPane.showMessageDialog(this, "A system error occurred during login.", "System Error", JOptionPane.ERROR_MESSAGE);
                E.printStackTrace();
            }
        } else if (e.getSource() == b2) {
            // System.exit(10); // Standard exit code is 0 (success) or 1 (failure).
            System.exit(0);
        }
    }

    // Static main method remains the same
    public static void main(String[] args) {
        new Login();
    }
}