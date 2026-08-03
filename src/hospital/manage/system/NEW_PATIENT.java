package hospital.manage.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class NEW_PATIENT extends JFrame implements ActionListener {

    // Define Professional Theme Colors (Light/Professional Theme)
    private static final Color PRIMARY_BLUE = new Color(0, 102, 204);     // Accent Blue
    private static final Color BACKGROUND_LIGHT = Color.WHITE;             // Main Background
    private static final Color TEXT_DARK = new Color(51, 51, 51);         // Dark Text
    private static final Font LABEL_FONT = new Font("Tahoma", Font.BOLD, 14);
    private static final Font FIELD_FONT = new Font("Tahoma", Font.PLAIN, 14);

    JComboBox comboBox;
    JTextField textFieldNumber, textName, textFieldDisease, textFieldDeposite;
    JRadioButton r1, r2;
    Choice c1;
    JLabel date;
    JButton b1, b2;

    NEW_PATIENT() {
        setTitle("New Patient Admission Form");

        // --- Main Panel Setup: Replaced original teal with clean WHITE ---
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 840, 450);
        panel.setBackground(BACKGROUND_LIGHT); // Clean White Background
        panel.setLayout(null);
        add(panel);

        // --- Header Title ---
        JLabel labelName = new JLabel("NEW PATIENT ADMISSION");
        labelName.setBounds(100, 15, 350, 25);
        labelName.setFont(new Font("Tahoma", Font.BOLD, 22));
        labelName.setForeground(PRIMARY_BLUE); // Professional Blue
        panel.add(labelName);

        // --- Image ---
        ImageIcon ImageIcon = new ImageIcon(ClassLoader.getSystemResource("icons/patient.png"));
        Image image = ImageIcon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        ImageIcon ImageIcon1 = new ImageIcon(image);
        JLabel label = new JLabel(ImageIcon1);
        label.setBounds(550, 100, 250, 250);
        panel.add(label);

        // --- Form Fields Layout ---
        int y_start = 70;
        int y_spacing = 40;
        int label_x = 35;
        int field_x = 271;
        int width = 180;

        // 1. ID Type
        JLabel labelID = new JLabel("ID Type:");
        labelID.setBounds(label_x, y_start, 200, 20);
        labelID.setFont(LABEL_FONT);
        labelID.setForeground(TEXT_DARK); // Dark Text
        panel.add(labelID);

        comboBox = new JComboBox(new String[]{"Aadhar Card", "Voter Id", "Driving License"});
        comboBox.setBounds(field_x, y_start, width, 25);
        comboBox.setBackground(Color.WHITE);
        comboBox.setForeground(TEXT_DARK);
        comboBox.setFont(FIELD_FONT);
        panel.add(comboBox);

        // 2. ID Number
        JLabel labelNumber = new JLabel("ID Number:");
        labelNumber.setBounds(label_x, y_start + y_spacing, 200, 20);
        labelNumber.setFont(LABEL_FONT);
        labelNumber.setForeground(TEXT_DARK);
        panel.add(labelNumber);

        textFieldNumber = new JTextField();
        textFieldNumber.setBounds(field_x, y_start + y_spacing, width, 25);
        textFieldNumber.setFont(FIELD_FONT);
        textFieldNumber.setBorder(BorderFactory.createLineBorder(TEXT_DARK.brighter()));
        panel.add(textFieldNumber);

        // 3. Name
        JLabel labelName1 = new JLabel("Name:");
        labelName1.setBounds(label_x, y_start + 2 * y_spacing, 200, 20);
        labelName1.setFont(LABEL_FONT);
        labelName1.setForeground(TEXT_DARK);
        panel.add(labelName1);

        textName = new JTextField();
        textName.setBounds(field_x, y_start + 2 * y_spacing, width, 25);
        textName.setFont(FIELD_FONT);
        textName.setBorder(BorderFactory.createLineBorder(TEXT_DARK.brighter()));
        panel.add(textName);

        // 4. Gender
        JLabel labelGender = new JLabel("Gender:");
        labelGender.setBounds(label_x, y_start + 3 * y_spacing, 200, 20);
        labelGender.setFont(LABEL_FONT);
        labelGender.setForeground(TEXT_DARK);
        panel.add(labelGender);

        // Radio buttons updated for white background
        r1 = new JRadioButton("Male");
        r1.setFont(FIELD_FONT);
        r1.setForeground(TEXT_DARK);
        r1.setBackground(BACKGROUND_LIGHT);
        r1.setBounds(field_x, y_start + 3 * y_spacing, 80, 20);
        panel.add(r1);

        r2 = new JRadioButton("Female");
        r2.setFont(FIELD_FONT);
        r2.setForeground(TEXT_DARK);
        r2.setBackground(BACKGROUND_LIGHT);
        r2.setBounds(field_x + 90, y_start + 3 * y_spacing, 100, 20);
        panel.add(r2);

        ButtonGroup bg = new ButtonGroup();
        bg.add(r1);
        bg.add(r2);


        // 5. Disease
        JLabel labelDisease = new JLabel("Disease:");
        labelDisease.setBounds(label_x, y_start + 4 * y_spacing, 200, 20);
        labelDisease.setFont(LABEL_FONT);
        labelDisease.setForeground(TEXT_DARK);
        panel.add(labelDisease);

        textFieldDisease = new JTextField();
        textFieldDisease.setBounds(field_x, y_start + 4 * y_spacing, width, 25);
        textFieldDisease.setFont(FIELD_FONT);
        textFieldDisease.setBorder(BorderFactory.createLineBorder(TEXT_DARK.brighter()));
        panel.add(textFieldDisease);

        // 6. Room
        JLabel labelRoom = new JLabel("Room No. (Available):");
        labelRoom.setBounds(label_x, y_start + 5 * y_spacing, 200, 20);
        labelRoom.setFont(LABEL_FONT);
        labelRoom.setForeground(TEXT_DARK);
        panel.add(labelRoom);

        c1 = new Choice();
        try {
            conn c = new conn();
            // Original logic: select * from Room (fetches all rooms, available or not)
            ResultSet resultSet = c.statement.executeQuery("select * from Room where Availability = 'Available'");
            while (resultSet.next()) {
                c1.add(resultSet.getString("room_no"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        c1.setBounds(field_x, y_start + 5 * y_spacing, width, 25);
        c1.setFont(FIELD_FONT);
        c1.setForeground(TEXT_DARK);
        c1.setBackground(Color.LIGHT_GRAY); // Light contrast for the Choice box
        panel.add(c1);


        // 7. Time
        JLabel labelDate = new JLabel("Admission Time:");
        labelDate.setBounds(label_x, y_start + 6 * y_spacing, 200, 20);
        labelDate.setFont(LABEL_FONT);
        labelDate.setForeground(TEXT_DARK);
        panel.add(labelDate);

        Date date1 = new Date();
        date = new JLabel("" + date1);
        date.setBounds(field_x, y_start + 6 * y_spacing, 250, 20);
        date.setFont(FIELD_FONT);
        date.setForeground(TEXT_DARK);
        panel.add(date);

        // 8. Deposit
        JLabel labelDeposite = new JLabel("Deposit (₹):");
        labelDeposite.setBounds(label_x, y_start + 7 * y_spacing, 200, 20);
        labelDeposite.setFont(LABEL_FONT);
        labelDeposite.setForeground(TEXT_DARK);
        panel.add(labelDeposite);

        textFieldDeposite = new JTextField();
        textFieldDeposite.setBounds(field_x, y_start + 7 * y_spacing, width, 25);
        textFieldDeposite.setFont(FIELD_FONT);
        textFieldDeposite.setBorder(BorderFactory.createLineBorder(TEXT_DARK.brighter()));
        panel.add(textFieldDeposite);

        // --- Buttons: Styled with Accent Blue/Dark Gray ---
        b1 = new JButton("ADD PATIENT");
        b1.setBounds(100, 405, 150, 35);
        b1.setForeground(Color.WHITE);
        b1.setBackground(PRIMARY_BLUE);
        b1.setFont(LABEL_FONT);
        b1.setBorder(BorderFactory.createEmptyBorder());
        b1.addActionListener(this);
        panel.add(b1);

        b2 = new JButton("BACK");
        b2.setBounds(280, 405, 150, 35);
        b2.setForeground(Color.WHITE);
        b2.setBackground(TEXT_DARK);
        b2.setFont(LABEL_FONT);
        b2.setBorder(BorderFactory.createEmptyBorder());
        b2.addActionListener(this);
        panel.add(b2);

        // --- Frame Settings ---
        setUndecorated(true);
        setSize(850, 460);
        setLayout(null);
        setLocation(275, 235);
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {

            // --- CORE FUNCTIONAL LOGIC RETAINED AS IS ---
            conn c = new conn();
            String radioBTN = null;
            if (r1.isSelected()){
                radioBTN = "Male";
            } else if (r2.isSelected()) {
                radioBTN = "Female";
            }
            String s1 = (String)comboBox.getSelectedItem();
            String s2 = textFieldNumber.getText();
            String s3 = textName.getText();
            String s4 = radioBTN;
            String s5 = textFieldDisease.getText();
            String s6 = c1.getSelectedItem();
            String s7 = date.getText();
            String s8 = textFieldDeposite.getText();


            try {
                // Original Insert Query
                String q = "insert into Patient_Info values ('"+s1+"','"+s2+"','"+s3+"','"+s4+"','"+s5+"','"+s6+"','"+s7+"','"+s8+"')";

                // Original Update Query (Note: Vulnerable to SQL Injection; use PreparedStatement in real apps)
                String q1 = "update room set Availability = 'Occupied' where room_no = "+s6;

                c.statement.executeUpdate(q);
                c.statement.executeUpdate(q1);

                JOptionPane.showMessageDialog(null, "Added Successfully");
                setVisible(false);

            }catch (Exception E){
                // Error Handler: Provides detailed info to console for debugging
                System.out.println("Database Execution Failed: " + E.getMessage());
                JOptionPane.showMessageDialog(null, "Database Error: Cannot add patient. Check if the ID Number is a duplicate or if the Room is already occupied.", "Database Constraint Error", JOptionPane.ERROR_MESSAGE);
                E.printStackTrace();
            }


        }else if (e.getSource() == b2) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new NEW_PATIENT();
    }
}