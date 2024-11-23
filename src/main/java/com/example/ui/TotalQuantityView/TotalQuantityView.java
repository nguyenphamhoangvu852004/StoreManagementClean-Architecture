package com.example.ui.TotalQuantityView;

import javax.swing.*;
import java.awt.*;

public class TotalQuantityView {

    private JFrame frame;
    private JPanel formPanel;
    private JLabel mainLabel;
    private JLabel thucPhamLabel, sanhSuLabel, dienMayLabel;
    private JLabel thucPhamValue, sanhSuValue, dienMayValue;

    public void getQuantityView() {
        // Initialize local variables
        int thucPhamQuantity = 10; // Example quantity, replace with actual data
        int sanhSuQuantity = 5;
        int dienMayQuantity = 8;

        // Set up the main frame
        frame = new JFrame("Product Quantities");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        // Center the window on screen
        frame.setLocationRelativeTo(null);

        // Main label
        mainLabel = new JLabel("QUANTITY", SwingConstants.CENTER);
        mainLabel.setFont(new Font("Arial", Font.BOLD, 24));
        mainLabel.setForeground(new Color(50, 50, 255));  // Blue color for the main label
        frame.add(mainLabel, BorderLayout.NORTH);

        // Form panel with GridLayout
        formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(3, 2, 20, 20)); // 3 rows, 2 columns with spacing
        formPanel.setBackground(new Color(240, 240, 240)); // Light gray background for the panel
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  // Padding for the panel

        // Initialize labels
        thucPhamLabel = new JLabel("Thực Phẩm:");
        thucPhamValue = new JLabel(String.valueOf(thucPhamQuantity));
        sanhSuLabel = new JLabel("Sành Sứ:");
        sanhSuValue = new JLabel(String.valueOf(sanhSuQuantity));
        dienMayLabel = new JLabel("Điện Máy:");
        dienMayValue = new JLabel(String.valueOf(dienMayQuantity));

        // Align text in value labels
        thucPhamValue.setHorizontalAlignment(SwingConstants.RIGHT);
        sanhSuValue.setHorizontalAlignment(SwingConstants.RIGHT);
        dienMayValue.setHorizontalAlignment(SwingConstants.RIGHT);

        // Add some padding for better spacing
        thucPhamLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        sanhSuLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        dienMayLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        thucPhamValue.setFont(new Font("Arial", Font.PLAIN, 16));
        sanhSuValue.setFont(new Font("Arial", Font.PLAIN, 16));
        dienMayValue.setFont(new Font("Arial", Font.PLAIN, 16));

        // Add labels to the panel
        formPanel.add(thucPhamLabel);
        formPanel.add(thucPhamValue);

        formPanel.add(sanhSuLabel);
        formPanel.add(sanhSuValue);

        formPanel.add(dienMayLabel);
        formPanel.add(dienMayValue);

        // Add the form panel to the frame
        frame.add(formPanel, BorderLayout.CENTER);

        // Make the frame visible
        frame.setVisible(true);
    }

    public void setQuantityDienMayLabel(int quantity) {
        dienMayValue.setText(String.valueOf(quantity));
    }

    public void setQuantitySanhSuLabel(int quantity) {
        sanhSuValue.setText(String.valueOf(quantity));
    }

    public void setQuantityThucPhamLabel(int quantity) {
        thucPhamValue.setText(String.valueOf(quantity));
    }
}
