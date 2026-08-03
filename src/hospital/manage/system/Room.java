package hospital.manage.system;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;


public class Room extends JFrame {

    // Define Professional Theme Colors
    private static final Color PRIMARY_BLUE = new Color(0, 102, 204);     // Accent Blue
    private static final Color BACKGROUND_LIGHT = Color.WHITE;             // Main Background
    private static final Color TEXT_DARK = new Color(51, 51, 51);         // Dark Text
    private static final Color PANEL_BACKGROUND = new Color(245, 245, 245); // Light panel for contrast
    private static final Font HEADER_FONT = new Font("Tahoma", Font.BOLD, 14);

    JTable table;

    Room(){
        setTitle("Hospital Room Information");

        // --- Main Panel Setup ---
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 890, 480);
        panel.setBackground(PANEL_BACKGROUND);
        panel.setLayout(null);
        add(panel);

        // --- Header Title ---
        JLabel mainHeader = new JLabel("HOSPITAL ROOM STATUS");
        mainHeader.setBounds(10, 10, 300, 25);
        mainHeader.setFont(new Font("Tahoma", Font.BOLD, 20));
        mainHeader.setForeground(PRIMARY_BLUE);
        panel.add(mainHeader);


        // --- Image: Styled and Repositioned ---
        try {
            ImageIcon ImageIcon = new ImageIcon(ClassLoader.getSystemResource("icons/roomm.png"));
            Image image = ImageIcon.getImage().getScaledInstance( 250, 250,Image.SCALE_DEFAULT);
            ImageIcon ImageIcon1 =new ImageIcon(image);
            JLabel label = new JLabel(ImageIcon1);
            label.setBounds(580, 150, 250, 250);
            panel.add(label);
        } catch (Exception e) {
            System.err.println("Room image not found.");
        }


        // --- Table Setup ---
        table = new JTable();
        table.setFont(new Font("Tahoma", Font.PLAIN, 12));
        table.setForeground(TEXT_DARK);
        table.setBackground(BACKGROUND_LIGHT);

        // *** FINAL FIX: Set the table header to null to remove padding/vacant space ***
        table.setTableHeader(null);
        // ***************************************************************



        JScrollPane scrollPane = new JScrollPane(table);
        // Adjusted X position to avoid cutoff and set Y to 80 (below custom headers)
        scrollPane.setBounds( 30, 80, 500, 320);
        scrollPane.setBackground(BACKGROUND_LIGHT);
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Remove ScrollPane border

        panel.add(scrollPane);

        // --- Column Headers (Aligned to match the new table position) ---
        int headerY = 55;

        // Labels shifted right by 20 (12 -> 32)
        JLabel label1 = new JLabel( "Room No");
        label1.setBounds( 32, headerY, 80, 15);
        label1.setFont(HEADER_FONT);
        label1.setForeground(TEXT_DARK);
        panel.add(label1);

        JLabel label2 = new JLabel("Availability");
        label2.setBounds( 140, headerY, 80, 15);
        label2.setFont(HEADER_FONT);
        label2.setForeground(TEXT_DARK);
        panel.add(label2);

        JLabel label3 = new JLabel("Price");
        label3.setBounds( 290, headerY, 80, 15);
        label3.setFont(HEADER_FONT);
        label3.setForeground(TEXT_DARK);
        panel.add(label3);

        JLabel label4 = new JLabel("Bed Type");
        label4.setBounds( 400, headerY, 80, 15);
        label4.setFont(HEADER_FONT);
        label4.setForeground(TEXT_DARK);
        panel.add(label4);



        // --- Database Fetch Logic ---
        try{
            conn c = new conn();
            String q = "select room_no, availability, price, Room_Type from room";
            ResultSet resultset = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultset));

            // Auto-set preferred column widths for alignment
            table.getColumnModel().getColumn(0).setPreferredWidth(50); // Room No
            table.getColumnModel().getColumn(1).setPreferredWidth(80); // Availability
            table.getColumnModel().getColumn(2).setPreferredWidth(50); // Price
            table.getColumnModel().getColumn(3).setPreferredWidth(120); // Bed Type

        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load room data from database. Check connection or DbUtils library.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }


        // --- Back Button: Styled with dark color ---
        JButton back = new JButton("Back");
        back.setBounds( 220, 430, 120, 35);
        back.setBackground(TEXT_DARK);
        back.setForeground(Color.white);
        back.setFont(HEADER_FONT);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });
        panel.add(back);

        setUndecorated(true);
        setSize( 900, 490);
        setLayout(null);
        setLocation(250, 220);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Room();
    }
}