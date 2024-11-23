package com.example.ui.updateProductMVVP;

import com.example.dtos.productInfoDetail.Response.DienMayDetailInfoResponseDTO;
import com.example.dtos.productInfoDetail.Response.SanhSuDetailInfoResponseDTO;
import com.example.dtos.productInfoDetail.Response.ThucPhamDetailInfoResponseDTO;
import com.example.dtos.updateProductDTOS.UpdateDienMayDTO;
import com.example.dtos.updateProductDTOS.UpdateHangHoaDTO;
import com.example.dtos.updateProductDTOS.UpdateSanhSuDTO;
import com.example.dtos.updateProductDTOS.UpdateThucPhamDTO;
import com.example.interfaces.RequestData;
import com.example.interfaces.ResponseData;
import com.example.ui.GetProductDetailInfoMVVP.DienMayDetailInfoModelView;
import com.example.ui.GetProductDetailInfoMVVP.SanhSuDetailInfoModelView;
import com.example.ui.GetProductDetailInfoMVVP.ThucPhamDetailInfoModelView;
import com.example.ui.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class UpdateProductView {

    private JFrame frame;
    private JPanel formPanel;
    private JLabel typeLabel, maHangLabel, tenHangLabel, soLuongTonLabel, donGiaLabel;
    private JTextField maHangField, tenHangField, soLuongTonField, donGiaField;
    private JButton submitButton;

    private JLabel thoiGianBaoHanhLabel;
    private JTextField thoiGianBaoHanhField;
    private JLabel congSuatLabel;
    private JTextField congSuatField;
    private JLabel ngaySanXuatLabel;

    private JTextField ngaySanXuatField;
    private JLabel ngayHetHanLabel;
    private JTextField ngayHetHanField;
    private JLabel nhaCungCapLabel;
    private JTextField nhaCungCapField;

    private MainController mainController;

    private JLabel nhaSanXuatLabel;
    private JTextField nhaSanXuatField;

    private JLabel ngayNhapKhoLabel;
    private JTextField ngayNhapKhoField;

    private String selectedType;


    public void getUpdateProductView(String productType, MainController mainController, String selectedType) {

        this.mainController = mainController;
        this.selectedType = selectedType;

        frame = new JFrame("Update Product - " + productType);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 450);
        frame.setLayout(new BorderLayout());

        // Form Panel
        formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(0, 2, 10, 10));

        // Common Fields
        maHangLabel = new JLabel("Mã Hàng:");
        maHangField = new JTextField();

        tenHangLabel = new JLabel("Tên Hàng:");
        tenHangField = new JTextField();

        soLuongTonLabel = new JLabel("Số Lượng Tồn:");
        soLuongTonField = new JTextField();

        donGiaLabel = new JLabel("Đơn Giá:");
        donGiaField = new JTextField();

        formPanel.add(maHangLabel);
        formPanel.add(maHangField);

        formPanel.add(tenHangLabel);
        formPanel.add(tenHangField);

        formPanel.add(soLuongTonLabel);
        formPanel.add(soLuongTonField);

        formPanel.add(donGiaLabel);
        formPanel.add(donGiaField);

        // Product-Specific Fields
        switch (productType) {
            case "HangDienMay":
                addHangDienMayFields(formPanel);
                break;

            case "HangThucPham":
                addHangThucPhamFields(formPanel);
                break;

            case "HangSanhSu":
                addHangSanhSuFields(formPanel);
                break;

            default:
                JOptionPane.showMessageDialog(frame, "Hãy chọn sản phẩm", "Error", JOptionPane.ERROR_MESSAGE);
                return;
        }

        submitButton = new JButton("Update Product");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSubmit(productType);
            }
        });

        frame.add(formPanel, BorderLayout.CENTER);
        frame.add(submitButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }


    private void addHangDienMayFields(JPanel panel) {
        thoiGianBaoHanhLabel = new JLabel("Thời Gian Bảo Hành (Tháng):");
        thoiGianBaoHanhField = new JTextField();

        congSuatLabel = new JLabel("Công Suất (W):");
        congSuatField = new JTextField();

        panel.add(thoiGianBaoHanhLabel);
        panel.add(thoiGianBaoHanhField);

        panel.add(congSuatLabel);
        panel.add(congSuatField);
    }

    private void addHangThucPhamFields(JPanel panel) {
        ngaySanXuatLabel = new JLabel("Ngày Sản Xuất:");
        ngaySanXuatField = new JTextField();

        ngayHetHanLabel = new JLabel("Ngày Hết Hạn:");
        ngayHetHanField = new JTextField();

        nhaCungCapLabel = new JLabel("Nhà Cung Cấp:");
        nhaCungCapField = new JTextField();

        panel.add(ngaySanXuatLabel);
        panel.add(ngaySanXuatField);

        panel.add(ngayHetHanLabel);
        panel.add(ngayHetHanField);

        panel.add(nhaCungCapLabel);
        panel.add(nhaCungCapField);
    }

    private void addHangSanhSuFields(JPanel panel) {
        nhaSanXuatLabel = new JLabel("Nhà Sản Xuất:");
        nhaSanXuatField = new JTextField();

        ngayNhapKhoLabel = new JLabel("Ngày Nhập Kho:");
        ngayNhapKhoField = new JTextField();

        panel.add(nhaSanXuatLabel);
        panel.add(nhaSanXuatField);

        panel.add(ngayNhapKhoLabel);
        panel.add(ngayNhapKhoField);
    }

    private void handleSubmit(String productType) {
        try {
            // Common Fields
            String maHang = maHangField.getText().trim();
            String tenHang = tenHangField.getText().trim();
            int soLuongTon = Integer.parseInt(soLuongTonField.getText().trim());
            double donGia = Double.parseDouble(donGiaField.getText().trim());

            // Specific Fields
            switch (productType) {
                case "HangDienMay":
                    int thoiGianBaoHanh = Integer.parseInt(thoiGianBaoHanhField.getText().trim());
                    double congSuat = Double.parseDouble(congSuatField.getText().trim());
                    submitHangDienMay(maHang, tenHang, "HDM", soLuongTon, donGia, thoiGianBaoHanh, congSuat);
                    break;

                case "HangThucPham":
                    String ngaySanXuat = ngaySanXuatField.getText().trim();
                    String ngayHetHan = ngayHetHanField.getText().trim();
                    String nhaCungCap = nhaCungCapField.getText().trim();
                    submitHangThucPham(maHang, tenHang, soLuongTon, "HTP", donGia, parseDate(ngaySanXuat), parseDate(ngayHetHan), nhaCungCap);
                    break;

                case "HangSanhSu":
                    String nhaSanXuat = nhaSanXuatField.getText().trim();
                    String ngayNhapKho = ngayNhapKhoField.getText().trim();
                    submitHangSanhSu(maHang, tenHang, soLuongTon, "HSS", donGia, nhaSanXuat, parseDate(ngayNhapKho));
                    break;

                default:
                    JOptionPane.showMessageDialog(frame, "Invalid product type", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Invalid number format", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private LocalDate parseDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            return LocalDate.parse(dateString, formatter); // Parse the date string to LocalDate
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public void showUpdateSuccessMessage(UpdateProductViewModel updateProductViewModel) {
        JOptionPane.showMessageDialog(frame, updateProductViewModel.getMessage(), "Success", JOptionPane.INFORMATION_MESSAGE);

    }

    public void showUpdateFailedMessage(UpdateProductViewModel updateProductViewModel) {
        JOptionPane.showMessageDialog(frame, updateProductViewModel, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void submitHangDienMay(String maHang, String tenHang, String tenLoai, int soLuongTon, double donGia, int thoiGianBaoHanh, double congSuat) throws SQLException {

        RequestData requestData = new UpdateDienMayDTO(maHang, tenHang, tenLoai, soLuongTon, donGia, thoiGianBaoHanh, congSuat);

        mainController.executeUpdateProduct(requestData);

        mainController.executeGetProductList(selectedType);

    }


    private void submitHangThucPham(String maHang, String tenHang, int soLuongTon, String tenLoai, double donGia, LocalDate ngaySanXuat, LocalDate ngayHetHan, String nhaCungCap) throws SQLException {

        RequestData requestData = new UpdateThucPhamDTO(maHang, tenHang, tenLoai, soLuongTon, donGia, ngaySanXuat, ngayHetHan, nhaCungCap);

        mainController.executeUpdateProduct(requestData);

        mainController.executeGetProductList(selectedType);
    }


    private void submitHangSanhSu(String maHang, String tenHang, int soLuongTon, String tenLoai, double donGia, String nhaSanXuat, LocalDate ngayNhapKho) throws SQLException {

        RequestData requestData = new UpdateSanhSuDTO(maHang, tenHang, tenLoai, soLuongTon, donGia, nhaSanXuat, ngayNhapKho);

        mainController.executeUpdateProduct(requestData);

        mainController.executeGetProductList(selectedType);

    }


    public void setDienMayDetail(
            DienMayDetailInfoModelView dienMayDetailInfoModelView
    ) {

        maHangField.setText(dienMayDetailInfoModelView.getMaHang());
        tenHangField.setText(dienMayDetailInfoModelView.getTenHang());
        soLuongTonField.setText(String.valueOf(dienMayDetailInfoModelView.getSoLuongTon()));
        donGiaField.setText(String.valueOf(dienMayDetailInfoModelView.getDonGia()));
        thoiGianBaoHanhField.setText(String.valueOf(dienMayDetailInfoModelView.getThoiGianBaoHanh()));
        congSuatField.setText(String.valueOf(dienMayDetailInfoModelView.getCongSuat()));

    }


    public void setThucPhamDetail(
            ThucPhamDetailInfoModelView thucPhamDetailInfoModelView
    ) {

        maHangField.setText(thucPhamDetailInfoModelView.getMaHang());
        tenHangField.setText(thucPhamDetailInfoModelView.getTenHang());
        soLuongTonField.setText(String.valueOf(thucPhamDetailInfoModelView.getSoLuongTon()));
        donGiaField.setText(String.valueOf(thucPhamDetailInfoModelView.getDonGia()));
        ngaySanXuatField.setText(formatDate(thucPhamDetailInfoModelView.getNgaySanXuat()));
        ngayHetHanField.setText(formatDate(thucPhamDetailInfoModelView.getNgayHetHan()));
        nhaCungCapField.setText(thucPhamDetailInfoModelView.getNhaCungCap());

    }

    public void setSanhSuDetail(
            SanhSuDetailInfoModelView sanhSuDetailInfoModelView
    ) {

        maHangField.setText(sanhSuDetailInfoModelView.getMaHang());
        tenHangField.setText(sanhSuDetailInfoModelView.getTenHang());
        soLuongTonField.setText(String.valueOf(sanhSuDetailInfoModelView.getSoLuongTon()));
        donGiaField.setText(String.valueOf(sanhSuDetailInfoModelView.getDonGia()));
        nhaSanXuatField.setText(sanhSuDetailInfoModelView.getNhaSanXuat());
        ngayNhapKhoField.setText(formatDate(sanhSuDetailInfoModelView.getNgayNhapKho()));

    }


    private String formatDate(LocalDate date) {
        if (date == null) return "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(formatter);
    }
}