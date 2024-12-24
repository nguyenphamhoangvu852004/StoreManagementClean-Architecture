package com.example.ui.findProductMVVP;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class FindProductView {

    private JFrame frame;
    private JTextField maHangTextField, tenHangTextField, soLuongTextField, donGiaTextField, VATTextField;
    private JTable productTable;
    private JScrollPane scrollPane;

    public FindProductView() {
        // Tạo JFrame
        frame = new JFrame("Product Detail");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 100);
        frame.setLayout(new BorderLayout());

        // Tạo Panel chính cho các JTextField
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Tạo các JTextField
        maHangTextField = new JTextField(20);
        maHangTextField.setEditable(false);

        tenHangTextField = new JTextField(20);
        tenHangTextField.setEditable(false);

        soLuongTextField = new JTextField(20);
        soLuongTextField.setEditable(false);

        donGiaTextField = new JTextField(20);
        donGiaTextField.setEditable(false);

        VATTextField = new JTextField(20);
        VATTextField.setEditable(false);

        // Tạo cột cho JTable
        String[] columns = {"Mã hàng", "Tên hàng", "Số lượng", "Đơn giá", "VAT (%)"};

        // Dữ liệu mặc định, có thể thay đổi sau
        Object[][] data = {{"", "", "", "", ""}};

        // Tạo DefaultTableModel với cột và dữ liệu
        DefaultTableModel model = new DefaultTableModel(data, columns);
        productTable = new JTable(model);

        // Tạo JScrollPane để cuộn bảng
        scrollPane = new JScrollPane(productTable);

        // Thêm các Panel vào Frame
        frame.add(mainPanel, BorderLayout.NORTH); // Các JTextField sẽ nằm ở trên
        frame.add(scrollPane, BorderLayout.CENTER); // JTable sẽ nằm ở dưới

        // Hiển thị JFrame
        frame.setVisible(true);
    }

    public void buildComponent(FindProductViewModel data) {
        // Cập nhật bảng nếu có thông tin từ FindProductViewModel
        DefaultTableModel model = (DefaultTableModel) productTable.getModel();
        model.setRowCount(0); // Xóa các hàng cũ
        model.addRow(new Object[]{data.getMaHang(), data.getTenHang(), data.getSoLuongTon(), data.getDonGia(), data.getVat()});
    }
}