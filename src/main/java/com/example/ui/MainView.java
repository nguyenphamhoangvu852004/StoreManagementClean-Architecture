package com.example.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.TextField;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.example.ui.getAllProductListMVVP.GetProductListObserver;
import com.example.ui.getAllProductListMVVP.GetProductListViewModel.DienMayViewModel;
import com.example.ui.getAllProductListMVVP.GetProductListViewModel.GetProductlistViewModel;
import com.example.ui.getAllProductListMVVP.GetProductListViewModel.SanhSuViewModel;
import com.example.ui.getAllProductListMVVP.GetProductListViewModel.ThucPhamViewModel;
import com.example.ui.getTypeListMVVP.GetTypeListObserver;
import com.example.ui.getTypeListMVVP.GetTypeListViewModel;

public class MainView implements GetProductListObserver, GetTypeListObserver {
    private JFrame frame;
    private JPanel controlPanel, paginationPanel; // Thêm paginationPanel
    private JScrollPane scrollPane;
    private JButton addButton, editButton, deleteButton, expiryButton, findProductButton, quantityButton;
    private JButton btnNext, btnPrev; // Thêm nút phân trang
    private JComboBox<String> typeComboBox;
    private MainController mainController;
    private TextField findProductTextField;
    private JTable table;
    public String maHang;

    private int currentPage = 1;
    private int pageSize = 10; // Kích thước trang mặc định
    private int totalItems = 0; // Tổng số sản phẩm
    private int totalPages = 0; // Tổng số trang

    public MainView(MainController mainController) {
        this.mainController = mainController;
        frame = new JFrame("Product Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        expiryButton = new JButton("Sản phẩm gần hết hạn");
        findProductButton = new JButton("Tìm Kiếm");
        quantityButton = new JButton("Tổng số lượng");

        controlPanel.add(addButton);
        controlPanel.add(editButton);
        controlPanel.add(deleteButton);
        controlPanel.add(expiryButton);
        controlPanel.add(quantityButton);

        // Tạo trường tìm kiếm
        findProductTextField = new TextField();
        findProductTextField.setPreferredSize(new Dimension(200, 25));
        controlPanel.add(findProductTextField);
        controlPanel.add(findProductButton);

        // Tạo panel phân trang
        paginationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        frame.add(paginationPanel, BorderLayout.PAGE_END);

        // Nút phân trang
        btnPrev = new JButton("<< Trước");
        btnNext = new JButton("Tiếp >>");

        paginationPanel.add(btnPrev);
        paginationPanel.add(btnNext);

        // Hiển thị frame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Xử lý sự kiện ComboBox
        typeComboBox.addActionListener(v -> {
            try {
                currentPage = 1; // Reset về trang đầu khi thay đổi loại
                handleTypeChange();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        // Xử lý sự kiện nút phân trang
        btnPrev.addActionListener(v -> {
            if (currentPage > 1) {
                currentPage--;
                try {
                    handleTypeChange();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        btnNext.addActionListener(v -> {
            if (currentPage < totalPages) {
                currentPage++;
                try {
                    handleTypeChange();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public JButton getExpiryButton() {
        return expiryButton;
    }

    public JButton getFindProductButton() {
        return findProductButton;
    }

    public String getSelectedType() {
        return (String) typeComboBox.getSelectedItem();
    }

    public TextField getFindProductTextField() {
        return this.findProductTextField;
    }

    private void handleTypeChange() throws SQLException {
        String selectedType = (String) typeComboBox.getSelectedItem();
        mainController.executeGetProductList(selectedType, currentPage, pageSize); // Truyền thêm currentPage, pageSize
    }

    @Override
    public void updateGetProductList(List<GetProductlistViewModel> data, int totalItems) {
        this.totalItems = totalItems;
        this.totalPages = (int) Math.ceil((double) totalItems / pageSize);

        // Cập nhật bảng
        String type = (String) typeComboBox.getSelectedItem();

        String[] commonColumns = { "Mã Hàng", "Tên Hàng", "Số Lượng Tồn", "Đơn giá", "VAT" };
        String[] additionalColumns = switch (type) {
            case "Hàng Thực Phẩm" -> new String[] { "Ngày Sản Xuất", "Ngày Hết Hạn", "Nhà Cung Cấp" };
            case "Hàng Sành Sứ" -> new String[] { "Nhà Sản Xuất", "Ngày Nhập Kho" };
            case "Hàng Điện Máy" -> new String[] { "Thời Gian Bảo Hành", "Công Suất" };
            default -> new String[] {};
        };

        String[] allColumns = Stream.concat(Arrays.stream(commonColumns), Arrays.stream(additionalColumns))
                .toArray(String[]::new);

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

        table = new JTable(tableModel);
        scrollPane.setViewportView(table);

        // Lắng nghe sự kiện chọn dòng
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    maHang = table.getValueAt(selectedRow, 0).toString();
                }
            }
        });

        // Cập nhật trạng thái nút phân trang
        btnPrev.setEnabled(currentPage > 1);
        btnNext.setEnabled(currentPage < totalPages);
    }

    @Override
    public void updateGetTypeList(List<GetTypeListViewModel> data) {
        typeComboBox.removeAllItems();
        typeComboBox.addItem("Tất Cả");
        for (GetTypeListViewModel type : data) {
            typeComboBox.addItem(type.getType());
        }
    }

    public JButton getQuantityButton() {
        return quantityButton;
    }

    public JTable getTable() {
        return table;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JButton getEditButton() {
        return editButton;
    }

    public String getMaHang() {
        return maHang;
    }
}
