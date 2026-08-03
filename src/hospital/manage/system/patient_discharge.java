package hospital.manage.system;

import hospital.manage.system.conn;

import javax.naming.Name;
import javax.swing.*;
import java .awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class patient_discharge extends JFrame implements ActionListener {

    // Define Professional Theme Colors
    private static final Color PRIMARY_BLUE = new Color(0, 102, 204);     // Accent Blue
    private static final Color BACKGROUND_LIGHT = Color.WHITE;             // Main Background
    private static final Color TEXT_DARK = new Color(51, 51, 51);         // Dark Text
    private static final Color PANEL_BACKGROUND = new Color(245, 245, 245); // Light panel for contrast
    private static final Font LABEL_FONT = new Font("Tahoma", Font.BOLD, 14);
    private static final Font DATA_FONT = new Font("Tahoma", Font.PLAIN, 14);

    Choice choice;
    JLabel CName, RNo, INTime, OutTime;
    JButton discharge, Check, Back;

    // We move the discharge logic inside actionPerformed, so we need the constructor logic outside.

    patient_discharge(){
        setTitle("Patient Check-out / Discharge");

        // --- Main Panel Setup: Light, professional background ---
        JPanel panel = new JPanel();
        panel.setBounds( 5, 5, 790, 420);
        panel.setBackground(PANEL_BACKGROUND); // Light background
        panel.setLayout(null);
        add(panel);

        // --- Header Title ---
        JLabel label = new JLabel( "PATIENT CHECK-OUT");
        label.setBounds ( 30, 20, 300, 25);
        label.setFont(new Font( "Tahoma", Font.BOLD, 22));
        label.setForeground(PRIMARY_BLUE); // Professional Blue
        panel.add(label);

        // --- Image ---
        try {
            ImageIcon ImageIcon = new ImageIcon(ClassLoader.getSystemResource("icons/discharge.jpg")); // Assuming you have a discharge/checkout image
            Image image = ImageIcon.getImage().getScaledInstance( 250, 250,Image.SCALE_SMOOTH);
            ImageIcon ImageIcon1 =new ImageIcon(image);
            JLabel imageLabel = new JLabel(ImageIcon1);
            imageLabel.setBounds(450, 50, 300, 300);
            panel.add(imageLabel);
        } catch (Exception e) {
            // Placeholder text if image is missing
            JLabel placeholder = new JLabel("[Discharge Image]");
            placeholder.setBounds(550, 150, 150, 20);
            panel.add(placeholder);
        }


        int y_start = 80;
        int y_spacing = 50;
        int label_x = 30;
        int field_x = 200;

        // 1. Customer ID (Choice/Dropdown)
        JLabel label2 = new JLabel( "Patient ID Number:");
        label2.setBounds ( label_x,  y_start, 150, 20);
        label2.setFont(LABEL_FONT);
        label2.setForeground(TEXT_DARK);
        panel.add(label2);

        choice = new Choice();
        choice.setBounds( field_x, y_start, 220, 25);
        choice.setBackground(BACKGROUND_LIGHT);
        choice.setForeground(TEXT_DARK);
        panel.add(choice);

        // Populate Choice Box
        try{
            conn c = new conn();
            // Assuming 'number' is the ID field in Patient_Info
            ResultSet resultSet = c.statement.executeQuery( "select number from Patient_Info where Room_Number IS NOT NULL"); // Only patients currently assigned a room
            while (resultSet.next()){
                choice.add(resultSet.getString( "number"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        // 2. Customer Name (Display)
        JLabel label6 = new JLabel( "Patient Name:");
        label6.setBounds ( label_x,  y_start + y_spacing, 150, 20);
        label6.setFont(LABEL_FONT);
        label6.setForeground(TEXT_DARK);
        panel.add(label6);

        CName = new JLabel("---");
        CName .setBounds ( field_x, y_start + y_spacing, 250, 20);
        CName .setFont(DATA_FONT);
        CName .setForeground(TEXT_DARK);
        panel.add(CName);

        // 3. Room Number (Display)
        JLabel label3 = new JLabel( "Room Number:");
        label3.setBounds ( label_x,  y_start + 2 * y_spacing, 150, 20);
        label3.setFont(LABEL_FONT);
        label3.setForeground(TEXT_DARK);
        panel.add(label3);

        RNo = new JLabel("---");
        RNo .setBounds ( field_x, y_start + 2 * y_spacing, 150, 20);
        RNo .setFont(DATA_FONT);
        RNo .setForeground(TEXT_DARK);
        panel.add(RNo);

        // 4. IN Time (Display)
        JLabel label4 = new JLabel( "Admission Time:");
        label4.setBounds ( label_x, y_start + 3 * y_spacing, 150, 20);
        label4.setFont(LABEL_FONT);
        label4.setForeground(TEXT_DARK);
        panel.add(label4);

        INTime = new JLabel("---");
        INTime .setBounds ( field_x, y_start + 3 * y_spacing, 250, 20);
        INTime .setFont(DATA_FONT);
        INTime .setForeground(TEXT_DARK);
        panel.add(INTime);

        // 5. OUT Time (Display)
        JLabel label5 = new JLabel( "Check-out Time:");
        label5.setBounds ( label_x, y_start + 4 * y_spacing, 150, 20);
        label5.setFont(LABEL_FONT);
        label5.setForeground(TEXT_DARK);
        panel.add(label5);

        Date date = new Date();
        OutTime = new JLabel(date.toString()); // Removed unnecessary quotes
        OutTime .setBounds ( field_x, y_start + 4 * y_spacing,  250, 20);
        OutTime .setFont(DATA_FONT);
        OutTime .setForeground(TEXT_DARK);
        panel.add(OutTime);

        // --- Buttons ---
        int button_y = 350;

        // Discharge Button (Primary Action: Accent Blue)
        discharge = new JButton( "DISCHARGE");
        discharge.setBounds( 30, button_y, 120, 35);
        discharge.setBackground(PRIMARY_BLUE);
        discharge.setForeground(Color.white);
        discharge.setFont(LABEL_FONT);
        discharge.addActionListener(this);
        panel.add(discharge);

        // Check Button (Secondary Action: Dark Text)
        Check = new JButton( "CHECK");
        Check.setBounds(170, button_y,  140, 35);
        Check.setBackground(TEXT_DARK);
        Check.setForeground(Color.white);
        Check.setFont(LABEL_FONT);
        Check.addActionListener(this);
        panel.add(Check);

        // Back Button (Tertiary Action: Dark Text)
        Back = new JButton( "BACK");
        Back.setBounds(330, button_y, 100,  35);
        Back.setBackground(TEXT_DARK);
        Back.setForeground(Color.white);
        Back.setFont(LABEL_FONT);
        Back.addActionListener(this);
        panel.add(Back);

        setUndecorated(true);

        setSize( 800,  430);
        setLayout(null);
        setLocation( 300, 250);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // --- Button Logic (Kept as originally defined) ---

        if (e.getSource() == Check) {
            conn c = new conn();
            try {
                ResultSet resultSet = c.statement.executeQuery("select Name, Room_Number, Time from Patient_Info where number = '" + choice.getSelectedItem() + "'");
                while (resultSet.next()) {
                    RNo.setText(resultSet.getString("Room_Number"));
                    INTime.setText(resultSet.getString("Time"));
                    CName.setText(resultSet.getString("Name"));
                }
            } catch (Exception E) {
                E.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error fetching patient details.", "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (e.getSource() == discharge) {
            conn c = new conn();
            try {
                // 1. Fetch the Room number based on selected patient (Needed for room update)
                ResultSet rs = c.statement.executeQuery("select Room_Number from Patient_Info where number = '" + choice.getSelectedItem() + "'");
                String roomNumber = "";
                if (rs.next()) {
                    roomNumber = rs.getString("Room_Number");
                }

                // 2. Delete the patient record
                c.statement.executeUpdate("delete from Patient_Info where number = '"+choice.getSelectedItem()+"'");

                // 3. Update room availability (Uses roomNumber fetched in step 1)
                // NOTE: Original code was "Room_Number" but DB is "room_no", using "room_no" here for compatibility.
                // If your DB uses Room_Number, adjust q1 accordingly.
                c.statement.executeUpdate("update room set Availability = 'Available' where room_no = '"+roomNumber+"'");

                JOptionPane.showMessageDialog(null, "Patient Discharged Successfully");
                setVisible(false);
                dispose();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Discharge failed. Check room number column name.", "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (e.getSource() == Back) {
            setVisible(false);
            dispose();
        }

    }

    public static void main(String[] args) {
        new patient_discharge();
    }
}