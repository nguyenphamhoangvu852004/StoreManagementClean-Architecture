package com.example.addProduct;

import com.example.addProduct.observe.Subscriber;
import com.example.tool.NumericFilter;
import com.example.ui.MainController;
import com.example.ui.getTypeListMVVP.GetTypeListObserver;
import com.example.ui.getTypeListMVVP.GetTypeListViewModel;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class AddProductView extends JFrame implements Subscriber, GetTypeListObserver {
    private JComboBox<String> productTypeComboBox;
    private JTextField productNameField, quantityField, priceField;
    private JTextField warrantyTimeField, powerField;
    private JDateChooser manufactureDateChooser, expiryDateChooser, importDateChooser;
    private JTextField supplierField, producerField;
    private MainController controller;
    private AddProductViewModel viewModel;
    private JButton addButton;


    public AddProductView(MainController controller, AddProductViewModel viewModel) {
        this.controller = controller;
        this.viewModel = viewModel;
        viewModel.subscribe(this);
        setTitle("Thêm sản phẩm");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(450, 300);


        JLabel productNameLabel = new JLabel("Tên sản phẩm:");
        productNameField = new JTextField(15);

        JLabel quantityLabel = new JLabel("Số lượng:");
        quantityField = new JTextField(10);
        ((AbstractDocument) quantityField.getDocument()).setDocumentFilter(new NumericFilter());

        JLabel priceLabel = new JLabel("Đơn giá:");
        priceField = new JTextField(10);
        ((AbstractDocument) priceField.getDocument()).setDocumentFilter(new NumericFilter());

        JLabel productTypeLabel = new JLabel("Loại hàng:");
        productTypeComboBox = new JComboBox<>();

        JLabel warrantyTimeLabel = new JLabel("Thời gian bảo hành:");
        warrantyTimeField = new JTextField(10);
        ((AbstractDocument) warrantyTimeField.getDocument()).setDocumentFilter(new NumericFilter());

        JLabel powerLabel = new JLabel("Công suất:");
        powerField = new JTextField(10);
        ((AbstractDocument) powerField.getDocument()).setDocumentFilter(new NumericFilter());

        JLabel manufactureDateLabel = new JLabel("Ngày sản xuất:");
        manufactureDateChooser = new JDateChooser();
        manufactureDateChooser.getDateEditor().setEnabled(false);

        JLabel expiryDateLabel = new JLabel("Ngày hết hạn:");
        expiryDateChooser = new JDateChooser();
        expiryDateChooser.getDateEditor().setEnabled(false);

        JLabel supplierLabel = new JLabel("Nhà cung cấp:");
        supplierField = new JTextField(15);

        JLabel producerLabel = new JLabel("Nhà sản xuất:");
        producerField = new JTextField(15);

        JLabel importDateLabel = new JLabel("Ngày nhập kho:");
        importDateChooser = new JDateChooser();
        importDateChooser.getDateEditor().setEnabled(false);

        addButton = new JButton("Thêm");
        JButton cancelButton = new JButton("Cancel");
        JButton resetButton = new JButton("Reset");

        // Ẩn các trường đặc thù khi chưa chọn loại sản phẩm
        warrantyTimeLabel.setVisible(false);
        warrantyTimeField.setVisible(false);
        powerLabel.setVisible(false);
        powerField.setVisible(false);

        manufactureDateLabel.setVisible(false);
        manufactureDateChooser.setVisible(false);
        expiryDateLabel.setVisible(false);
        expiryDateChooser.setVisible(false);
        supplierLabel.setVisible(false);
        supplierField.setVisible(false);

        producerLabel.setVisible(false);
        producerField.setVisible(false);
        importDateLabel.setVisible(false);
        importDateChooser.setVisible(false);

        productTypeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedType = (String) productTypeComboBox.getSelectedItem();

                // Reset visibility
                warrantyTimeLabel.setVisible(false);
                warrantyTimeField.setVisible(false);
                powerLabel.setVisible(false);
                powerField.setVisible(false);

                manufactureDateLabel.setVisible(false);
                manufactureDateChooser.setVisible(false);
                expiryDateLabel.setVisible(false);
                expiryDateChooser.setVisible(false);
                supplierLabel.setVisible(false);
                supplierField.setVisible(false);

                producerLabel.setVisible(false);
                producerField.setVisible(false);
                importDateLabel.setVisible(false);
                importDateChooser.setVisible(false);

                // Show fields based on product type
                if ("Hàng Điện Máy".equals(selectedType)) {
                    warrantyTimeLabel.setVisible(true);
                    warrantyTimeField.setVisible(true);
                    powerLabel.setVisible(true);
                    powerField.setVisible(true);
                } else if ("Hàng Thực Phẩm".equals(selectedType)) {
                    manufactureDateLabel.setVisible(true);
                    manufactureDateChooser.setVisible(true);
                    expiryDateLabel.setVisible(true);
                    expiryDateChooser.setVisible(true);
                    supplierLabel.setVisible(true);
                    supplierField.setVisible(true);
                } else if ("Hàng Sành Sứ".equals(selectedType)) {
                    producerLabel.setVisible(true);
                    producerField.setVisible(true);
                    importDateLabel.setVisible(true);
                    importDateChooser.setVisible(true);
                }
                revalidate();
                repaint();
            }
        });

        addButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String productName = (productNameField != null && !productNameField.getText().trim().isEmpty())
                        ? productNameField.getText().trim()
                        : "";

                int quantity = 0;
                if (quantityField != null && !quantityField.getText().trim().isEmpty()) {
                    try {
                        quantity = Integer.parseInt(quantityField.getText().trim());
                    } catch (NumberFormatException ignored) {

                    }
                }

                double price = 0.0;
                if (priceField != null && !priceField.getText().trim().isEmpty()) {
                    try {
                        price = Double.parseDouble(priceField.getText().trim());
                    } catch (NumberFormatException ignored) {

                    }
                }

                String selectedType = (String) productTypeComboBox.getSelectedItem();

                if ("Hàng Thực Phẩm".equals(selectedType)) {
                    LocalDate manufactureDate = (manufactureDateChooser != null && manufactureDateChooser.getDate() != null)
                            ? convertToLocalDate(manufactureDateChooser.getDate())
                            : null;
                    LocalDate expiryDate = (expiryDateChooser != null && expiryDateChooser.getDate() != null)
                            ? convertToLocalDate(expiryDateChooser.getDate())
                            : null;
                    String supplier = (supplierField != null && !supplierField.getText().trim().isEmpty())
                            ? supplierField.getText().trim()
                            : "";

                    AddProductDTO addProductDTO = new AddProductDTO(
                            productName, quantity, price, selectedType, manufactureDate, expiryDate, supplier
                    );

                    try {
                        controller.executeAddProduct(addProductDTO);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                } else if ("Hàng Điện Máy".equals(selectedType)) {
                    int warrantyTime = 0;
                    if (warrantyTimeField != null && !warrantyTimeField.getText().trim().isEmpty()) {
                        try {
                            warrantyTime = Integer.parseInt(warrantyTimeField.getText().trim());
                        } catch (NumberFormatException ignored) {}
                    }

                    double power = 0.0;
                    if (powerField != null && !powerField.getText().trim().isEmpty()) {
                        try {
                            power = Double.parseDouble(powerField.getText().trim());
                        } catch (NumberFormatException ignored) {}
                    }

                    AddProductDTO addProductDTO = new AddProductDTO(
                            productName, quantity, price, selectedType,
                            warrantyTime, power
                    );
                    try {
                        controller.executeAddProduct(addProductDTO);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                } else if ("Hàng Sành Sứ".equals(selectedType)) {
                    LocalDate importDate = (importDateChooser != null && importDateChooser.getDate() != null)
                            ? convertToLocalDate(importDateChooser.getDate())
                            : null;
                    String producer = (producerField != null && !producerField.getText().trim().isEmpty())
                            ? producerField.getText().trim()
                            : "";

                    AddProductDTO addProductDTO = new AddProductDTO(
                            productName, quantity, price, selectedType, producer, importDate
                    );
                    try {
                        controller.executeAddProduct(addProductDTO);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                try {
                    controller.executeGetProductList("Tất Cả");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Reset tất cả các trường nhập liệu
                productNameField.setText("");
                quantityField.setText(String.valueOf(0));
                priceField.setText(String.valueOf(0));
                warrantyTimeField.setText(String.valueOf(0));
                powerField.setText(String.valueOf(0));
                manufactureDateChooser.setDate(null);
                expiryDateChooser.setDate(null);
                supplierField.setText("");
                producerField.setText("");
                importDateChooser.setDate(null);

            }
        });

        // Sử dụng GroupLayout để căn chỉnh
        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        // set kich thuoc mac dinh
        manufactureDateChooser.setMaximumSize(new Dimension(120, 25));
        expiryDateChooser.setMaximumSize(new Dimension(120, 25));
        importDateChooser.setMaximumSize(new Dimension(120, 25));

// Cập nhật layout.setHorizontalGroup
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(productNameLabel)
                                .addComponent(quantityLabel)
                                .addComponent(priceLabel)
                                .addComponent(productTypeLabel)
                                .addComponent(warrantyTimeLabel)
                                .addComponent(powerLabel)
                                .addComponent(manufactureDateLabel)
                                .addComponent(expiryDateLabel)
                                .addComponent(supplierLabel)
                                .addComponent(producerLabel)
                                .addComponent(importDateLabel)
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(productNameField)
                                .addComponent(quantityField)
                                .addComponent(priceField)
                                .addComponent(productTypeComboBox)
                                .addComponent(warrantyTimeField)
                                .addComponent(powerField)
                                .addComponent(manufactureDateChooser)
                                .addComponent(expiryDateChooser)
                                .addComponent(supplierField)
                                .addComponent(producerField)
                                .addComponent(importDateChooser)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(30) // Thêm khoảng cách bên trái
                                        .addComponent(cancelButton)
                                        .addGap(10) // Thêm khoảng cách giữa các nút
                                        .addComponent(resetButton)
                                        .addGap(10) // Thêm khoảng cách giữa các nút
                                        .addComponent(addButton)
                                        .addGap(30) // Thêm khoảng cách bên phải
                                )
                        )
        );

// Cập nhật layout.setVerticalGroup
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(productNameLabel)
                                .addComponent(productNameField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(quantityLabel)
                                .addComponent(quantityField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(priceLabel)
                                .addComponent(priceField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(productTypeLabel)
                                .addComponent(productTypeComboBox))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(warrantyTimeLabel)
                                .addComponent(warrantyTimeField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(powerLabel)
                                .addComponent(powerField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(manufactureDateLabel)
                                .addComponent(manufactureDateChooser))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(expiryDateLabel)
                                .addComponent(expiryDateChooser))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(supplierLabel)
                                .addComponent(supplierField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(producerLabel)
                                .addComponent(producerField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(importDateLabel)
                                .addComponent(importDateChooser))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(cancelButton)
                                .addComponent(resetButton)
                                .addComponent(addButton)
                        )
        );

        add(panel);
        setLocationRelativeTo(null);
        setVisible(false);
    }


    private LocalDate convertToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public JButton getAddButton() {
        return addButton;
    }

    @Override
    public void update() {

        JOptionPane.showMessageDialog(null, viewModel.getMessage());
        viewModel.unSubscriber(this);
    }

    @Override
    public void updateGetTypeList(List<GetTypeListViewModel> data) {
        productTypeComboBox.removeAllItems(); // Xóa các mục cũ
        productTypeComboBox.addItem("Chọn loại");
        for (GetTypeListViewModel type : data) {
            productTypeComboBox.addItem(type.getType());
        }
    }
}

