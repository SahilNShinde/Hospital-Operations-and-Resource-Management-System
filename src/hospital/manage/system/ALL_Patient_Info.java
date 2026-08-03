package hospital.manage.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class ALL_Patient_Info extends JFrame {

    // Define Professional Theme Colors
    private static final Color PRIMARY_BLUE = new Color(0, 102, 204);     // Accent Blue
    private static final Color BACKGROUND_LIGHT = Color.WHITE;             // Main Background
    private static final Color TEXT_DARK = new Color(51, 51, 51);         // Dark Text
    private static final Color PANEL_BACKGROUND = new Color(245, 245, 245); // Light panel for contrast
    private static final Font HEADER_FONT = new Font("Tahoma", Font.BOLD, 14);

    ALL_Patient_Info(){
        setTitle("All Patient Records");

        // --- Main Panel Setup: Light, professional background ---
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 890, 470);
        panel.setLayout(null);
        panel.setBackground(PANEL_BACKGROUND);
        add(panel);

        // --- Header Title ---
        JLabel mainHeader = new JLabel("ALL PATIENT RECORDS");
        mainHeader.setBounds(10, 10, 350, 25);
        mainHeader.setFont(new Font("Tahoma", Font.BOLD, 22));
        mainHeader.setForeground(PRIMARY_BLUE);
        panel.add(mainHeader);


        // --- Table Setup ---
        JTable table = new JTable();
        table.setFont(new Font("Tahoma", Font.PLAIN, 12));
        table.setForeground(TEXT_DARK);
        table.setBackground(BACKGROUND_LIGHT);
        table.setRowHeight(25);

        // Hide the default JTable header and remove padding
        table.setTableHeader(null);
        table.setShowGrid(true);
        table.setGridColor(new Color(200, 200, 200));

        // Use JScrollPane to display the table data
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 65, 870, 325); // Positioned below custom headers
        scrollPane.setBackground(BACKGROUND_LIGHT);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        panel.add(scrollPane);

        try{
            conn c = new conn();
            // Select columns explicitly to ensure order matches custom labels
            String q = "select ID, Number, Name, Gender, Patient_Disease, Room_Number, Time, Deposite from Patient_Info";
            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));

            // Adjust column widths for better visual balance
            table.getColumnModel().getColumn(0).setPreferredWidth(60);  // ID Type
            table.getColumnModel().getColumn(1).setPreferredWidth(100); // ID Number
            table.getColumnModel().getColumn(2).setPreferredWidth(100); // Name
            table.getColumnModel().getColumn(3).setPreferredWidth(60);  // Gender
            table.getColumnModel().getColumn(4).setPreferredWidth(100); // Disease
            table.getColumnModel().getColumn(5).setPreferredWidth(50);  // Room
            table.getColumnModel().getColumn(6).setPreferredWidth(180); // Time
            table.getColumnModel().getColumn(7).setPreferredWidth(60);  // Deposit


        }catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load patient data. Check database connection or table name.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }

        // --- Custom Column Headers (Aligned to match the table data) ---
        int headerY = 40;

        // Horizontal positions are estimated based on typical JTable column widths.

        JLabel label1 = new JLabel("ID Type"); // Changed from just "ID" for clarity
        label1.setBounds(15, headerY, 80,14);
        label1.setFont(HEADER_FONT);
        label1.setForeground(TEXT_DARK);
        panel.add(label1);

        JLabel label2 = new JLabel("Number");
        label2.setBounds(95,headerY,100,14);
        label2.setFont(HEADER_FONT);
        label2.setForeground(TEXT_DARK);
        panel.add(label2);

        JLabel label3 = new JLabel("Name");
        label3.setBounds(215,headerY,100,14);
        label3.setFont(HEADER_FONT);
        label3.setForeground(TEXT_DARK);
        panel.add(label3);

        JLabel label4 = new JLabel("Gender");
        label4.setBounds(335,headerY, 100, 14);
        label4.setFont(HEADER_FONT);
        label4.setForeground(TEXT_DARK);
        panel.add(label4);

        JLabel label5 = new JLabel("Disease");
        label5.setBounds(415, headerY,100,14);
        label5.setFont(HEADER_FONT);
        label5.setForeground(TEXT_DARK);
        panel.add(label5);

        JLabel label6 = new JLabel("Room");
        label6.setBounds(535,headerY, 100, 14);
        label6.setFont(HEADER_FONT);
        label6.setForeground(TEXT_DARK);
        panel.add(label6);

        JLabel label7 = new JLabel("Time");
        label7.setBounds(605,headerY,100,14);
        label7.setFont(HEADER_FONT);
        label7.setForeground(TEXT_DARK);
        panel.add(label7);

        JLabel label8 = new JLabel("Payment");
        label8.setBounds(805,headerY,100,12);
        label8.setFont(HEADER_FONT);
        label8.setForeground(TEXT_DARK);
        panel.add(label8);


        // --- Back Button: Styled with dark color ---
        JButton button = new JButton("BACK");
        button.setBounds(400,415,120,35); // Lower and centralized
        button.setBackground(TEXT_DARK);
        button.setForeground(Color.white);
        button.setFont(HEADER_FONT);
        panel.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }

        });

        setUndecorated(true);

        setSize(900,480);
        setLayout(null);
        setLocation(250,225);
        setVisible(true);

    }

    public static void main(String[] args) {
        new ALL_Patient_Info();
    }
}