package com.example.ui;

import com.example.ui.getAllProductListMVVP.GetProductListObserver;
import com.example.ui.getAllProductListMVVP.GetProductlistViewModel;
import com.example.ui.getTypeListMVVP.GetTypeListObserver;
import com.example.ui.getTypeListMVVP.GetTypeListViewModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class MainView implements GetProductListObserver, GetTypeListObserver {
    private JFrame frame;
    private JPanel controlPanel;
    private JScrollPane scrollPane;
    private JButton addButton, editButton, deleteButton, expiryButton; // Thêm expiryButton
    private JComboBox<String> typeComboBox;
    private MainController mainController;

    public MainView(MainController mainControllerr) {
        this.mainController = mainControllerr;
        frame = new JFrame("Product Management");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(900, 600);


        // Tạo tiêu đề
        JLabel titleLabel = new JLabel("DANH SÁCH SẢN PHẨM", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        frame.add(titleLabel, BorderLayout.NORTH);

        // Tạo bảng sản phẩm
        scrollPane = new JScrollPane();
        frame.add(scrollPane, BorderLayout.CENTER);

        // Tạo panel điều khiển
        controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        frame.add(controlPanel, BorderLayout.SOUTH);

        // ComboBox để hiển thị loại sản phẩm
        typeComboBox = new JComboBox<>();
        controlPanel.add(typeComboBox);

        // Nút chức năng
        addButton = new JButton("Thêm");
        editButton = new JButton("Sửa");
        deleteButton = new JButton("Xóa");
        expiryButton = new JButton("Sản phẩm gần hết hạn"); // Thêm nút mới

        controlPanel.add(addButton);
        controlPanel.add(editButton);
        controlPanel.add(deleteButton);
        controlPanel.add(expiryButton); // Thêm vào controlPanel

        // Hiển thị frame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        typeComboBox.addActionListener(v -> {
            try {
                handleTypeChange();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public JButton getExpiryButton() {
        return expiryButton;
    }

    private void handleTypeChange() throws SQLException {
        String selectedType = (String) typeComboBox.getSelectedItem();
        mainController.executeGetProductList(selectedType);
    }


    @Override
    public void updateGetProductList(List<GetProductlistViewModel> data) {
          String[] columns = {"Mã Hàng", "Tên Hàng", "Số Lượng Tồn", "Đơn giá", "VAT"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (GetProductlistViewModel product : data) {
            Object[] row = {
                    product.getMaHang(),
                    product.getTenHang(),
                    product.getSoLuongTon(),
                    product.getDonGia(),
                    product.getVAT(),
            };
            tableModel.addRow(row);
        }

        JTable table = new JTable(tableModel);
        scrollPane.setViewportView(table);
    }

    @Override
    public void updateGetTypeList(List<GetTypeListViewModel> data) {
        typeComboBox.removeAllItems(); // Xóa các mục cũ
        typeComboBox.addItem("Tất Cả"); // Giá trị mặc định
        for (GetTypeListViewModel type : data) {

            typeComboBox.addItem(type.getType());
        }
    }
}

