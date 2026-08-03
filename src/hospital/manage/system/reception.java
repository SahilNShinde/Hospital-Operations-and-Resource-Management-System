package hospital.manage.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class reception extends JFrame implements ActionListener {

    // Define Professional Theme Colors
    private static final Color PRIMARY_BLUE = new Color(0, 102, 204);     // Deep professional blue (Accent)
    private static final Color BACKGROUND_LIGHT = Color.WHITE;             // Clean white background
    private static final Color PANEL_BACKGROUND = new Color(240, 240, 240); // Light gray for panel contrast
    private static final Color TEXT_DARK = new Color(51, 51, 51);         // Dark gray for text/secondary buttons
    private static final Font BUTTON_FONT = new Font("Arial", Font.BOLD, 14);

    // Array of buttons for easier styling
    JButton btnNewPatient, btnRoom, btnDepartment, btnEmployeeInfo, btnPatientInfo,
            btnDischarge, btnUpdateDetails, btnAmbulance, btnSearchRoom, btnLogout;

    reception(){
        setTitle("Hospital Management System - Reception Dashboard");

        // --- Panel 1: Top Navigation and Header (Lighter Contrast) ---
        JPanel panel1 = new JPanel();
        panel1.setLayout(null);
        panel1.setBounds(5, 5, 1355, 175);
        panel1.setBackground(PANEL_BACKGROUND); // Light gray for subtle contrast
        add(panel1);

        // --- Header Title ---
        JLabel headerLabel = new JLabel("Reception Portal - Quick Access");
        headerLabel.setBounds(30, 0, 400, 40);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 22));
        headerLabel.setForeground(PRIMARY_BLUE);
        panel1.add(headerLabel);

        // --- Panel 2: Main Display/Data Area (White Background) ---
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(5, 160 ,1350, 533);
        panel.setBackground(BACKGROUND_LIGHT);
        add(panel);


        // --- Image/Logo Enhancements (Doctor/Ambulance) ---

        // Doctor Image (Adjusted position)
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/dr.jpg"));
        Image image = i1.getImage().getScaledInstance(170, 170, Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(image);
        JLabel label = new JLabel(i2);
        label.setBounds(1060 , 5, 380, 165); // Adjusted size and position for panel1
        panel1.add(label);




        // --- Button Creation and Styling (4 Columns Layout) ---
        int startX = 30;
        int buttonWidth = 220; // Slightly wider buttons
        int buttonHeight = 35; // Slightly taller buttons
        int xSpacing = 250;
        int ySpacing = 45;
        int startY = 40;

        // Column 1
        btnNewPatient = createStyledButton("Add New Patient", PRIMARY_BLUE);
        btnNewPatient.setBounds(startX, startY, buttonWidth, buttonHeight);

        btnRoom = createStyledButton("Room Info", PRIMARY_BLUE);
        btnRoom.setBounds(startX, startY + ySpacing, buttonWidth, buttonHeight);

        btnDepartment = createStyledButton("Department Info", PRIMARY_BLUE);
        btnDepartment.setBounds(startX, startY + 2 * ySpacing, buttonWidth, buttonHeight);

        // Column 2
        btnEmployeeInfo = createStyledButton("All Employee Info", PRIMARY_BLUE);
        btnEmployeeInfo.setBounds(startX + xSpacing, startY, buttonWidth, buttonHeight);

        btnPatientInfo = createStyledButton("All Patient Info", PRIMARY_BLUE);
        btnPatientInfo.setBounds(startX + xSpacing, startY + ySpacing, buttonWidth, buttonHeight);

        btnDischarge = createStyledButton("Patient Discharge", PRIMARY_BLUE);
        btnDischarge.setBounds(startX + xSpacing, startY + 2 * ySpacing, buttonWidth, buttonHeight);

        // Column 3
        btnUpdateDetails = createStyledButton("Update Patient Details", PRIMARY_BLUE);
        btnUpdateDetails.setBounds(startX + 2 * xSpacing, startY, buttonWidth, buttonHeight);

        btnAmbulance = createStyledButton("Hospital Ambulance", PRIMARY_BLUE);
        btnAmbulance.setBounds(startX + 2 * xSpacing, startY + ySpacing, buttonWidth, buttonHeight);

        btnSearchRoom = createStyledButton("Search Room", PRIMARY_BLUE);
        btnSearchRoom.setBounds(startX + 2 * xSpacing, startY + 2 * ySpacing, buttonWidth, buttonHeight);

        // Column 4 (Logout)
        btnLogout = createStyledButton("Logout", TEXT_DARK);
        btnLogout.setBounds(startX + 3 * xSpacing, startY, buttonWidth, buttonHeight);

        // Add all buttons to panel1 (Navigation Panel)
        panel1.add(btnNewPatient);
        panel1.add(btnRoom);
        panel1.add(btnDepartment);
        panel1.add(btnEmployeeInfo);
        panel1.add(btnPatientInfo);
        panel1.add(btnDischarge);
        panel1.add(btnUpdateDetails);
        panel1.add(btnAmbulance);
        panel1.add(btnSearchRoom);
        panel1.add(btnLogout);

        // Add Action Listeners
        btnNewPatient.addActionListener(this);
        btnRoom.addActionListener(this);
        btnDepartment.addActionListener(this);
        btnEmployeeInfo.addActionListener(this);
        btnPatientInfo.addActionListener(this);
        btnDischarge.addActionListener(this);
        btnUpdateDetails.addActionListener(this);
        btnAmbulance.addActionListener(this);
        btnSearchRoom.addActionListener(this);
        btnLogout.addActionListener(this);


        // --- Frame Settings ---
        setSize(1380, 730); // Adjusted size to fit a standard screen well
        getContentPane().setBackground(BACKGROUND_LIGHT);
        setLayout(null);
        setLocationRelativeTo(null); // Center the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Helper method to create consistently styled buttons
    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(BUTTON_FONT);
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Added internal padding
        return button;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle all button clicks here using the source object
        if (e.getSource() == btnNewPatient) {
             new NEW_PATIENT();
        } else if (e.getSource() == btnRoom) {
             new Room();
        } else if (e.getSource() == btnDepartment) {
             new Department();
        } else if (e.getSource() == btnEmployeeInfo) {
             new Employee_info();
        } else if (e.getSource() == btnPatientInfo) {
             new ALL_Patient_Info();
        } else if (e.getSource() == btnDischarge) {
             new patient_discharge();
        } else if (e.getSource() == btnUpdateDetails) {
             new update_patient_details();
        } else if (e.getSource() == btnAmbulance) {
             new Ambulance();
        } else if (e.getSource() == btnSearchRoom) {
             new SearchRoom();
        } else if (e.getSource() == btnLogout) {
            setVisible(false);
            new Login();
            dispose();
        }

        // Note: For other windows, you might want to hide the current frame
        // using setVisible(false) before opening the new one, but for a
        // dashboard, keeping the reception window open might be desired.
    }


    public static void main(String[] args) {
        new reception();
    }
}