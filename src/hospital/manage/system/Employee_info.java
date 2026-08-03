package hospital.manage.system;

import hospital.manage.system.conn;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Employee_info extends JFrame {

    // Define Professional Theme Colors
    private static final Color PRIMARY_BLUE = new Color(0, 102, 204);     // Accent Blue
    private static final Color BACKGROUND_LIGHT = Color.WHITE;             // Main Background
    private static final Color TEXT_DARK = new Color(51, 51, 51);         // Dark Text
    private static final Color PANEL_BACKGROUND = new Color(245, 245, 245); // Light panel for contrast
    private static final Font HEADER_FONT = new Font("Tahoma", Font.BOLD, 14);

    public Employee_info(){
        setTitle("Employee Information");

        // --- Main Panel Setup: Light, professional background ---
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 990, 470);
        panel.setLayout(null);
        panel.setBackground(PANEL_BACKGROUND);
        add(panel);

        // --- Header Title ---
        JLabel mainHeader = new JLabel("HOSPITAL EMPLOYEE DIRECTORY");
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
        scrollPane.setBounds(10, 70, 980, 50); // Positioned right below custom headers
        scrollPane.setBackground(BACKGROUND_LIGHT);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        panel.add(scrollPane);

        try{
            conn c = new conn();
            // Select columns explicitly to ensure order matches custom labels
            String q = "select Name, Age, Phone_Number, Salary, Gmail, Aadhar_Number from EMP_INFO";
            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));

        }catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load employee data. Check database connection or 'EMP_INFO' table name.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }

        // --- Custom Column Headers (Aligned to match the table data) ---
        int headerY = 45; // Closer to the table

        // --- NOTE: Column alignments are adjusted to match typical JTable column rendering ---

        JLabel label1 = new JLabel("Name");
        label1.setBounds(25, headerY, 70, 20); // Aligned X
        label1.setFont(HEADER_FONT);
        label1.setForeground(TEXT_DARK);
        panel.add(label1);

        JLabel label2 = new JLabel("Age");
        label2.setBounds(175, headerY, 70, 20); // Aligned X
        label2.setFont(HEADER_FONT);
        label2.setForeground(TEXT_DARK);
        panel.add(label2);


        JLabel label3 = new JLabel("Phone Number");
        label3.setBounds(339, headerY, 150, 20); // Aligned X
        label3.setFont(HEADER_FONT);
        label3.setForeground(TEXT_DARK);
        panel.add(label3);


        JLabel label4 = new JLabel("Salary");
        label4.setBounds(505, headerY, 150, 20); // Aligned X
        label4.setFont(HEADER_FONT);
        label4.setForeground(TEXT_DARK);
        panel.add(label4);


        JLabel label5 = new JLabel("Gmail");
        label5.setBounds(670, headerY, 150, 20); // Aligned X
        label5.setFont(HEADER_FONT);
        label5.setForeground(TEXT_DARK);
        panel.add(label5);


        JLabel label6 = new JLabel( "Aadhar Number");
        label6.setBounds( 830, headerY, 150, 20); // Aligned X
        label6.setFont(HEADER_FONT);
        label6.setForeground(TEXT_DARK);
        panel.add(label6);


        // --- BACK Button: Styled with dark color ---
        JButton button = new JButton( "BACK");
        button.setBounds(440, 420, 120, 35); // Lower and slightly larger
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

        setSize( 1000, 480);
        setLayout(null);
        setLocation(175,220);
        setVisible(true);

    }

    public static void main(String[] args) {
        new Employee_info();
    }

}