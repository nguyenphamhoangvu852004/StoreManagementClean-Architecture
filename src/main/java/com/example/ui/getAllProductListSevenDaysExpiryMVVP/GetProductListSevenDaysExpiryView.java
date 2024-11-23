package com.example.ui.getAllProductListSevenDaysExpiryMVVP;


import com.example.ui.getAllProductListMVVP.GetProductListObserver;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;


public class GetProductListSevenDaysExpiryView implements GetProductListSevenDaysExpiryObserver {
    private JFrame frame;
    private DefaultTableModel tableModel;

    public GetProductListSevenDaysExpiryView() {
        frame = new JFrame("FOOD SEVEN DAY EXPIRY LIST");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 450);

        // Create title label
        JLabel titleLabel = new JLabel("DANH SÁCH HÀNG THỰC PHẨM SẮP HẾT HẠN TRONG BẢY NGÀY", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // Padding around the title

        // Column headers for the JTable
        String[] columns = {
                "Mã Hàng", "Tên Hàng", "Số Lượng Tồn", "Đơn giá", "VAT","Ngày sản xuất", "Ngày hết hạn","Nhà Cung Cấp"
        };

        // Create table model
        tableModel = new DefaultTableModel(columns, 0);
        JTable table = new JTable(tableModel);

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);

        // Set up layout for the frame
        frame.setLayout(new BorderLayout());
        frame.add(titleLabel, BorderLayout.NORTH); // Add title label at the top
        frame.add(scrollPane, BorderLayout.CENTER); // Add scroll pane in the center

        // Make the frame visible
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void updateGetProductListSevenDaysExpiry(List<GetProductListSevenDaysExpiryViewModel> data) {
        // Clear existing rows in the table
        tableModel.setRowCount(0);

        // Add new product data to the table
        for (GetProductListSevenDaysExpiryViewModel product : data) {
            Object[] row = {
                    product.getMaHang(),
                    product.getTenHang(),
                    product.getSoLuongTon(),
                    product.getDonGia(),
                    product.getVAT(),
                    product.getNgaySanXuat(),
                    product.getNgayHetHan(),
                    product.getNhaCungCap()
            };
            tableModel.addRow(row);
        }
    }
}
