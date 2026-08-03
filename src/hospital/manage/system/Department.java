package hospital.manage.system;
import hospital.manage.system.conn;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Department extends JFrame {

    // Define Professional Theme Colors
    private static final Color PRIMARY_BLUE = new Color(0, 102, 204);
    private static final Color BACKGROUND_LIGHT = Color.WHITE;
    private static final Color TEXT_DARK = new Color(51, 51, 51);
    private static final Color PANEL_BACKGROUND = new Color(245, 245, 245);
    private static final Font HEADER_FONT = new Font("Tahoma", Font.BOLD, 14);

    public Department (){
        setTitle("Hospital Department Information");

        // --- Main Panel Setup ---
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 690, 470);
        panel.setLayout(null);
        panel.setBackground(PANEL_BACKGROUND);
        add(panel);

        // --- Header Title ---
        JLabel mainHeader = new JLabel("HOSPITAL DEPARTMENTS");
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

        table.setTableHeader(null);
        table.setShowGrid(true);
        table.setGridColor(new Color(200, 200, 200));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 80, 670, 300);
        scrollPane.setBackground(BACKGROUND_LIGHT);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        panel.add(scrollPane);


        // --- Database Fetch Logic: Reverted to SELECT * ---
        try{
            conn c = new conn();
            // FIX: Using SELECT * to ensure all columns are retrieved, preventing column mismatch errors.
            String q = "select * from department";
            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));

            // Adjust column widths based on the assumption of two main columns,
            // but the table model uses ALL columns from the DB.
            table.getColumnModel().getColumn(0).setPreferredWidth(200);
            table.getColumnModel().getColumn(1).setPreferredWidth(100);

        }catch (Exception e){
            e.printStackTrace();
            // Changed to a more general error message
            JOptionPane.showMessageDialog(this, "Failed to load department data. Check database connection or the 'department' table name.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }

        // --- Custom Column Headers (Aligned for the first two columns) ---
        int headerY = 55;

        JLabel label1 = new JLabel("Department");
        // Alignment for Column 1
        label1.setBounds(10, headerY, 150, 20);
        label1.setFont(HEADER_FONT);
        label1.setForeground(TEXT_DARK);
        panel.add(label1);

        JLabel label2 = new JLabel("Phone Number");
        // Alignment for Column 2 (Approx 250 units from left edge, assuming column 1 is wide)
        label2.setBounds(300, headerY, 150, 20);
        label2.setFont(HEADER_FONT);
        label2.setForeground(TEXT_DARK);
        panel.add(label2);

        // --- Back Button: Styled with dark color ---
        JButton b1 = new JButton("BACK");
        b1.setBounds(280, 420, 130, 35);
        b1.setBackground(TEXT_DARK);
        b1.setForeground(Color.white);
        b1.setFont(HEADER_FONT);
        panel.add(b1);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });

        setUndecorated(true);

        setSize(700,480);
        setLayout(null);
        setLocation(350,225);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Department();
    }
}