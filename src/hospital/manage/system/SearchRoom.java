package hospital.manage.system;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class SearchRoom extends JFrame {

    // Define Professional Theme Colors
    private static final Color PRIMARY_BLUE = new Color(0, 102, 204);     // Accent Blue
    private static final Color BACKGROUND_LIGHT = Color.WHITE;             // Main Background
    private static final Color TEXT_DARK = new Color(51, 51, 51);         // Dark Text
    private static final Color PANEL_BACKGROUND = new Color(245, 245, 245); // Light panel for contrast
    private static final Font LABEL_FONT = new Font("Tahoma", Font.BOLD, 14);
    private static final Font BUTTON_FONT = new Font("Tahoma", Font.BOLD, 14);

    Choice choice;
    JTable table;
    JButton Search, Back;

    SearchRoom() {
        setTitle("Search Room Availability");

        // --- Main Panel Setup: Light, professional background ---
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 690, 470);
        panel.setBackground(PANEL_BACKGROUND);
        panel.setLayout(null);
        add(panel);

        // --- Header Title ---
        JLabel For = new JLabel("SEARCH ROOM AVAILABILITY");
        For.setBounds(25, 20, 350, 25);
        For.setForeground(PRIMARY_BLUE);
        For.setFont(new Font("Tahoma", Font.BOLD, 22));
        panel.add(For);

        // --- Search Status Label and Choice ---
        JLabel status = new JLabel("Filter by Status:");
        status.setBounds(70, 70, 120, 20);
        status.setForeground(TEXT_DARK);
        status.setFont(LABEL_FONT);
        panel.add(status);

        choice = new Choice();
        choice.setBounds(200, 70, 150, 20); // Adjusted position and width
        choice.add("Available");
        choice.add("Occupied");
        choice.setBackground(BACKGROUND_LIGHT);
        choice.setForeground(TEXT_DARK);
        panel.add(choice);

        // --- Table Setup ---
        table = new JTable();
        table.setFont(new Font("Tahoma", Font.PLAIN, 12));
        table.setForeground(TEXT_DARK);
        table.setBackground(BACKGROUND_LIGHT);
        table.setRowHeight(25);

        // Hide default header and remove padding
        table.setTableHeader(null);
        table.setShowGrid(true);
        table.setGridColor(new Color(200, 200, 200));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 180, 670, 225); // Use JScrollPane and adjust bounds
        scrollPane.setBackground(BACKGROUND_LIGHT);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        panel.add(scrollPane);

        try {
            conn c = new conn();
            // Select columns explicitly to ensure order matches custom labels
            String q = "select room_no, availability, Price, Room_Type from room";
            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));

            // Adjust column widths for better visual balance
            table.getColumnModel().getColumn(0).setPreferredWidth(50); // Room No
            table.getColumnModel().getColumn(1).setPreferredWidth(100); // Availability
            table.getColumnModel().getColumn(2).setPreferredWidth(50);  // Price
            table.getColumnModel().getColumn(3).setPreferredWidth(100); // Bed Type

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load initial room data.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }

        // --- Custom Column Headers (Aligned to match the table data) ---
        int headerY = 155;

        JLabel Rooomno = new JLabel("Room No."); // Renamed for professional look
        Rooomno.setBounds(17, headerY, 150, 20);
        Rooomno.setForeground(TEXT_DARK);
        Rooomno.setFont(LABEL_FONT);
        panel.add(Rooomno);

        JLabel available = new JLabel("Availability");
        available.setBounds(185, headerY, 150, 20); // Adjusted X
        available.setForeground(TEXT_DARK);
        available.setFont(LABEL_FONT);
        panel.add(available);

        JLabel price = new JLabel("Price");
        price.setBounds(347, headerY, 150, 20); // Adjusted X
        price.setForeground(TEXT_DARK);
        price.setFont(LABEL_FONT);
        panel.add(price);

        JLabel Bed = new JLabel("Bed Type");
        Bed.setBounds(515, headerY, 150, 20); // Adjusted X
        Bed.setForeground(TEXT_DARK);
        Bed.setFont(LABEL_FONT);
        panel.add(Bed);

        // --- Search Button (Primary Action: Accent Blue) ---
        Search = new JButton("Search");
        Search.setBounds(200, 420, 120, 35); // Adjusted Y and H
        Search.setBackground(PRIMARY_BLUE);
        Search.setForeground(Color.white);
        Search.setFont(BUTTON_FONT);
        panel.add(Search);
        Search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ensure SQL injection safety (though minimal here, good practice)
                String status = choice.getSelectedItem();
                String q = "select room_no, availability, Price, room_type from room where Availability = '" + status + "'";
                try {
                    conn c = new conn();
                    ResultSet resultSet = c.statement.executeQuery(q);
                    table.setModel(DbUtils.resultSetToTableModel(resultSet));
                } catch (Exception E) {
                    E.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Search failed. Check database connection.", "Database Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        // --- Back Button (Secondary Action: Dark Text) ---
        Back = new JButton("Back");
        Back.setBounds(380, 420, 120, 35); // Adjusted Y and H
        Back.setBackground(TEXT_DARK);
        Back.setForeground(Color.white);
        Back.setFont(BUTTON_FONT);
        panel.add(Back);
        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });

        setUndecorated(true);
        setSize(700, 480);
        setLayout(null);
        setLocation(350, 225);
        setVisible(true);
    }

    public static void main(String[] args) {
        new SearchRoom();
    }
}