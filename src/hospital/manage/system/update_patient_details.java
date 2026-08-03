package hospital.manage.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class update_patient_details extends JFrame implements ActionListener {

    // Define Professional Theme Colors
    private static final Color PRIMARY_BLUE = new Color(0, 102, 204);     // Accent Blue
    private static final Color BACKGROUND_LIGHT = Color.WHITE;             // Main Background
    private static final Color TEXT_DARK = new Color(51, 51, 51);         // Dark Text
    private static final Color PANEL_BACKGROUND = new Color(245, 245, 245); // Light panel for contrast
    private static final Font LABEL_FONT = new Font("Tahoma", Font.BOLD, 14);
    private static final Font FIELD_FONT = new Font("Tahoma", Font.PLAIN, 14);

    // Declare components outside the constructor to access them in actionPerformed
    Choice choice;
    JTextField textFieldR, textFieldInTime, textFieldAmount, textFieldPending;
    JButton check, update, back;


    update_patient_details() {
        setTitle("Update Patient Details");

        // --- Main Panel Setup: Light, professional background ---
        JPanel panel = new JPanel();
        panel.setBounds ( 5, 5, 940, 460);
        panel.setBackground(PANEL_BACKGROUND);
        panel.setLayout(null);
        add(panel);

        // --- Header Title ---
        JLabel label1 = new JLabel("Update Patient Details");
        label1.setBounds(25, 15, 300, 25);
        label1.setFont(new Font( "Tahoma", Font. BOLD, 22));
        label1.setForeground(PRIMARY_BLUE);
        panel.add(label1);

        // --- Image ---
        try {
            ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icons/updated.png"));
            Image image = imageIcon.getImage().getScaledInstance( 300, 300, Image.SCALE_SMOOTH);
            ImageIcon imageIcon1 = new ImageIcon(image);
            JLabel imageLabel = new JLabel(imageIcon1);
            imageLabel.setBounds ( 550, 60, 350, 350); // Adjusted position for size
            panel.add(imageLabel);
        } catch (Exception e) {
            System.err.println("Image not found: icons/updated.png");
        }


        // --- Form Fields Layout ---
        int y_start = 80;
        int y_spacing = 45;
        int label_x = 25;
        int field_x = 248;
        int width = 180;


        // 1. Patient Name (Choice)
        JLabel label2 = new JLabel("Patient Name:");
        label2.setBounds(label_x, y_start, 150, 20);
        label2.setFont(LABEL_FONT);
        label2.setForeground(TEXT_DARK);
        panel.add(label2);

        choice = new Choice();
        choice.setBounds( field_x, y_start, width, 25);
        choice.setBackground(BACKGROUND_LIGHT);
        choice.setForeground(TEXT_DARK);
        panel.add(choice);

        try{
            conn c = new conn();
            ResultSet resultSet = c.statement.executeQuery("select Name from Patient_Info");
            while (resultSet.next()){
                choice.add(resultSet.getString("Name"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        // 2. Room Number
        JLabel label3 = new JLabel("Room Number:");
        label3.setBounds(label_x, y_start + y_spacing, 150, 20);
        label3.setFont(LABEL_FONT);
        label3.setForeground(TEXT_DARK);
        panel.add(label3);

        textFieldR = new JTextField();
        textFieldR.setBounds(field_x, y_start + y_spacing, width, 25);
        textFieldR.setFont(FIELD_FONT);
        textFieldR.setBorder(BorderFactory.createLineBorder(TEXT_DARK.brighter()));
        panel.add(textFieldR);

        // 3. In-Time
        JLabel label4 = new JLabel( "Admission Time:");
        label4.setBounds( label_x, y_start + 2 * y_spacing, 150, 20);
        label4.setFont(LABEL_FONT);
        label4.setForeground(TEXT_DARK);
        panel.add(label4);

        textFieldInTime = new JTextField();
        textFieldInTime.setBounds( field_x, y_start + 2 * y_spacing, width, 25);
        textFieldInTime.setFont(FIELD_FONT);
        textFieldInTime.setBorder(BorderFactory.createLineBorder(TEXT_DARK.brighter()));
        panel.add(textFieldInTime);

        // 4. Deposit/Amount Paid
        JLabel label5 = new JLabel("Deposit Amount (₹):");
        label5.setBounds( label_x, y_start + 3 * y_spacing, 150, 20);
        label5.setFont(LABEL_FONT);
        label5.setForeground(TEXT_DARK);
        panel.add(label5);

        textFieldAmount = new JTextField();
        textFieldAmount.setBounds(field_x, y_start + 3 * y_spacing, width, 25);
        textFieldAmount.setFont(FIELD_FONT);
        textFieldAmount.setBorder(BorderFactory.createLineBorder(TEXT_DARK.brighter()));
        panel.add(textFieldAmount );

        // 5. Pending Amount
        JLabel label6 = new JLabel("Pending Bill (₹):");
        label6.setBounds(label_x, y_start + 4 * y_spacing, 150, 20);
        label6.setFont(LABEL_FONT);
        label6.setForeground(TEXT_DARK);
        panel.add(label6);

        textFieldPending = new JTextField();
        textFieldPending.setBounds(field_x, y_start + 4 * y_spacing, width, 25);
        textFieldPending.setFont(FIELD_FONT);
        textFieldPending.setForeground(Color.RED); // Highlight pending amount
        textFieldPending.setBorder(BorderFactory.createLineBorder(TEXT_DARK.brighter()));
        textFieldPending.setEditable(false); // Should only be calculated, not edited
        panel.add(textFieldPending);

        // --- Buttons ---
        int button_y = 380;
        int button_w = 110;
        int button_h = 35;

        // UPDATE Button (Primary Action: Accent Blue)
        update = new JButton("UPDATE");
        update.setBounds(56, button_y, button_w, button_h);
        update.setBackground (PRIMARY_BLUE);
        update.setForeground (Color.white);
        update.setFont(LABEL_FONT);
        update.addActionListener(this);
        panel.add(update);

        // CHECK Button (Secondary Action: Dark Text)
        check = new JButton("CHECK");
        check.setBounds(281, button_y, button_w, button_h);
        check.setBackground (TEXT_DARK);
        check.setForeground (Color.white);
        check.setFont(LABEL_FONT);
        check.addActionListener(this);
        panel.add(check);

        // BACK Button (Tertiary Action: Dark Text)
        back = new JButton("BACK");
        back.setBounds(168, button_y, button_w, button_h);
        back.setBackground (TEXT_DARK);
        back.setForeground (Color.white);
        back.setFont(LABEL_FONT);
        back.addActionListener(this);
        panel.add(back);

        // --- Frame Settings ---
        setUndecorated(true);
        setSize(950, 470);
        setLayout(null);
        setLocation(225, 230);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == check) {
            String id = choice.getSelectedItem();
            String q = "select * from Patient_Info where Name = '" + id + "'";
            try {
                conn c = new conn();
                ResultSet resultSet = c.statement.executeQuery(q);

                // 1. Fetch Patient Info
                while (resultSet.next()) {
                    textFieldR.setText(resultSet.getString("Room_Number"));
                    textFieldInTime.setText(resultSet.getString("Time"));
                    textFieldAmount.setText(resultSet.getString("Deposite"));
                }

                // 2. Calculate Pending Amount
                String roomNo = textFieldR.getText();
                ResultSet resultSet1 = c.statement.executeQuery("select Price from room where room_no = '" + roomNo + "'");

                if (resultSet1.next()) {
                    // Assuming Room.Price is the total cost/rate
                    String priceStr = resultSet1.getString("Price");

                    // Safety check before parsing
                    if (priceStr != null && textFieldAmount.getText() != null) {
                        try {
                            int totalBill = Integer.parseInt(priceStr);
                            int amountPaid = Integer.parseInt(textFieldAmount.getText());
                            int amountPending = totalBill - amountPaid;
                            textFieldPending.setText("₹ " + amountPending);
                        } catch (NumberFormatException ex) {
                            textFieldPending.setText("Error Calc.");
                            JOptionPane.showMessageDialog(this, "Price or Deposit contains non-numeric data.", "Data Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            } catch (Exception E) {
                E.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error fetching data. Check Patient ID or Room setup.", "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (e.getSource() == update) {
            try {
                conn c = new conn();
                String q = choice.getSelectedItem();
                String room = textFieldR.getText();
                String time = textFieldInTime.getText();
                String amount = textFieldAmount.getText();

                // The original update query:
                String updateQuery = "update Patient_Info set Room_Number = '"+room+"', Time = '"+time+"', Deposite = '"+amount+"' where name = '"+q+"'";

                c.statement.executeUpdate(updateQuery);
                JOptionPane.showMessageDialog(null, "Updated Successfully");
                setVisible(false);
                dispose();
            }catch (Exception E){
                E.printStackTrace();
                JOptionPane.showMessageDialog(null, "Update failed. Check your input values.", "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (e.getSource() == back) {
            setVisible(false);
            dispose();
        }
    }

    public static void main(String[] args) {
        new update_patient_details();
    }
}