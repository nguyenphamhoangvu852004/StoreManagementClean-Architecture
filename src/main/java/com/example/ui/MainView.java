package com.example.ui;

import com.example.ui.getAllProductListMVVP.*;
import com.example.ui.getAllProductListMVVP.GetProductListViewModel.DienMayViewModel;
import com.example.ui.getAllProductListMVVP.GetProductListViewModel.GetProductlistViewModel;
import com.example.ui.getAllProductListMVVP.GetProductListViewModel.SanhSuViewModel;
import com.example.ui.getAllProductListMVVP.GetProductListViewModel.ThucPhamViewModel;
import com.example.ui.getTypeListMVVP.GetTypeListObserver;
import com.example.ui.getTypeListMVVP.GetTypeListViewModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class MainView implements GetProductListObserver, GetTypeListObserver {
    private JFrame frame;
    private JPanel controlPanel;
    private JScrollPane scrollPane;
    private JButton addButton, editButton, deleteButton, expiryButton, findProductButton; // Thêm expiryButton
    private JComboBox<String> typeComboBox;
    private MainController mainController;
    private TextField findProductTextField;

    public String maHang;

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
        findProductButton = new JButton("Tìm Kiếm");
        controlPanel.add(addButton);
        controlPanel.add(editButton);
        controlPanel.add(deleteButton);
        controlPanel.add(expiryButton); // Thêm vào controlPanel

        // Tạo trường tìm kiếm và thay đổi kích thước
        findProductTextField = new TextField();
        findProductTextField.setPreferredSize(new Dimension(200, 25)); // Thay đổi kích thước
        controlPanel.add(findProductTextField);
        controlPanel.add(findProductButton);

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

    public JButton getFindProductButton() {
        return findProductButton;
    }

    public TextField getFindProductTextField() {
        return this.findProductTextField;
    }

    private void handleTypeChange() throws SQLException {
        String selectedType = (String) typeComboBox.getSelectedItem();
        mainController.executeGetProductList(selectedType);
    }

    @Override
    public void updateGetProductList(List<GetProductlistViewModel> data) {

        String type = (String) typeComboBox.getSelectedItem();

        String[] commonColumns = {"Mã Hàng", "Tên Hàng", "Số Lượng Tồn", "Đơn giá", "VAT"};
        String[] additionalColumns;

        switch (type) {
            case "Hàng Thực Phẩm":
                additionalColumns = new String[]{"Ngày Sản Xuất", "Ngày Hết Hạn", "Nhà Cung Cấp"};
                break;
            case "Hàng Sành Sứ":
                additionalColumns = new String[]{"Nhà Sản Xuất", "Ngày Nhập Kho"};
                break;
            case "Hàng Điện Máy":
                additionalColumns = new String[]{"Thời Gian Bảo Hành", "Công Suất"};
                break;
            default:
                additionalColumns = new String[]{};
        }

        // Gộp cột chung và cột bổ sung
        String[] allColumns = Stream.concat(Arrays.stream(commonColumns), Arrays.stream(additionalColumns))
                .toArray(String[]::new);

        // Tạo bảng với cấu trúc cột
        DefaultTableModel tableModel = new DefaultTableModel(allColumns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };


        for (GetProductlistViewModel product : data) {
            List<String> row = new ArrayList<>();
            row.add(product.maHang);
            row.add(product.tenHang);
            row.add(product.soLuongTon);
            row.add(product.donGia);
            row.add(product.VAT);

            // Kiểm tra loại sản phẩm trước khi ép kiểu
            if (product instanceof ThucPhamViewModel thucPham) {
                row.add(thucPham.getNgaySanXuat());
                row.add(thucPham.getNgayHetHan());
                row.add(thucPham.getNhaCungCap());
            } else if (product instanceof SanhSuViewModel sanhSu) {
                row.add(sanhSu.getNhaSanXuat());
                row.add(sanhSu.getNgayNhapKho());
            } else if (product instanceof DienMayViewModel dienMay) {
                row.add(dienMay.getThoiGianBaoHanh());
                row.add(dienMay.getCongSuat());
            }

            tableModel.addRow(row.toArray());

        }

        JTable table = new JTable(tableModel);
        scrollPane.setViewportView(table);

        // Lắng nghe sự kiện chọn dòng
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    // Lấy mã sản phẩm từ cột "Mã Hàng" (giả sử là cột 0)
                    maHang = table.getValueAt(selectedRow, 0).toString();
                }
            }
        });
    }


    @Override
    public void updateGetTypeList(List<GetTypeListViewModel> data) {
        typeComboBox.removeAllItems(); // Xóa các mục cũ
        typeComboBox.addItem("Tất Cả"); // Giá trị mặc định
        for (GetTypeListViewModel type : data) {
            typeComboBox.addItem(type.getType());
        }
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public String getMaHang() {
        return maHang;
    }
}
