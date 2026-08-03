package hospital.manage.system;

import hospital.manage.system.conn;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Ambulance extends JFrame {

    // Define Professional Theme Colors
    private static final Color PRIMARY_BLUE = new Color(0, 102, 204);     // Accent Blue
    private static final Color BACKGROUND_LIGHT = Color.WHITE;             // Main Background
    private static final Color TEXT_DARK = new Color(51, 51, 51);         // Dark Text
    private static final Color PANEL_BACKGROUND = new Color(245, 245, 245); // Light panel for contrast
    private static final Font HEADER_FONT = new Font("Tahoma", Font.BOLD, 14);

    Ambulance() {
        setTitle("Ambulance Status Information");

        // --- Main Panel Setup: Light, professional background ---
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 890, 470);
        panel.setLayout(null);
        panel.setBackground(PANEL_BACKGROUND);
        add(panel);

        // --- Header Title ---
        JLabel mainHeader = new JLabel("HOSPITAL AMBULANCE STATUS");
        mainHeader.setBounds(10, 10, 400, 25);
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
            // Assuming the DB columns match the labels: Name, Gender, Car_Name, Available, Location
            String q = "select Name, Gender, Car_Name, Available, Location from Ambulance";
            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));

            // Adjust column widths for better visual balance
            table.getColumnModel().getColumn(0).setPreferredWidth(100); // Name
            table.getColumnModel().getColumn(1).setPreferredWidth(60);  // Gender
            table.getColumnModel().getColumn(2).setPreferredWidth(100); // Car Name
            table.getColumnModel().getColumn(3).setPreferredWidth(70);  // Available
            table.getColumnModel().getColumn(4).setPreferredWidth(150); // Location


        }catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load ambulance data. Check database connection or table name.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }

        // --- Custom Column Headers (Aligned to match the table data) ---
        int headerY = 40;

        // Horizontal positions are adjusted to match column widths set above

        JLabel label1 = new JLabel("Name");
        label1.setBounds(20, headerY, 100,14);
        label1.setFont(HEADER_FONT);
        label1.setForeground(TEXT_DARK);
        panel.add(label1);

        JLabel label2 = new JLabel("Gender");
        label2.setBounds(200,headerY,100,14);
        label2.setFont(HEADER_FONT);
        label2.setForeground(TEXT_DARK);
        panel.add(label2);

        JLabel label3 = new JLabel("Car name");
        label3.setBounds(335,headerY,100,14);
        label3.setFont(HEADER_FONT);
        label3.setForeground(TEXT_DARK);
        panel.add(label3);

        JLabel label4 = new JLabel("Availability");
        label4.setBounds(515,headerY, 100, 14);
        label4.setFont(HEADER_FONT);
        label4.setForeground(TEXT_DARK);
        panel.add(label4);

        JLabel label5 = new JLabel("Location");
        label5.setBounds(660, headerY,100,14);
        label5.setFont(HEADER_FONT);
        label5.setForeground(TEXT_DARK);
        panel.add(label5);


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
    public static void main (String[]args){
        new Ambulance();
    }
}