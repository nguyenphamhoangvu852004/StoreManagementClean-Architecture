package com.example.ui.findProductMVVP;

import com.example.dtos.FindProductByIdDTOs.ResponseDTO;
import com.example.interfaces.OutputBoundary;
import com.example.interfaces.ResponseData;

import javax.swing.*;
import java.text.DecimalFormat;

public class FindProductPresenter implements OutputBoundary {
    private FindProductViewModel findProductViewModel;

    @Override
    public void exportResult(ResponseData responseData) {
        ResponseDTO responseDTO = (ResponseDTO) responseData;

        // Kiểm tra thông báo lỗi từ `contentMessage`
        if (responseDTO.getContentMessage() != null && !responseDTO.getContentMessage().equals("Thành công")) {
            showError(responseDTO.getContentMessage());
            return;
        }

        // Định dạng số thập phân cho đơn giá và VAT
        String formattedDonGia = formatNumber(responseDTO.getDonGia());
        String formattedVat = formatNumber(responseDTO.getVAT());

        // Tạo ViewModel từ ResponseDTO
        this.findProductViewModel = new FindProductViewModel(
                responseDTO.getMaHang(),
                responseDTO.getTenHang(),
                responseDTO.getTenLoai(),
                String.valueOf(responseDTO.getSoLuongTon()),
                formattedDonGia,
                formattedVat
        );

        // Khởi tạo giao diện và truyền ViewModel
        FindProductView findProductView = new FindProductView();
        findProductView.buildComponent(this.findProductViewModel);
    }

    // Phương thức tiện ích để hiển thị thông báo lỗi
    private void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Lỗi", JOptionPane.ERROR_MESSAGE);
    }

    // Phương thức tiện ích để định dạng số thập phân
    private String formatNumber(double number) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
        return decimalFormat.format(number);
    }
}
