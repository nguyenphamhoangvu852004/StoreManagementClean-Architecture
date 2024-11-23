package com.example.dtos.updateProductDTOS;

import com.example.interfaces.RequestData;

public class UpdateDienMayDTO extends UpdateHangHoaDTO implements RequestData {
    private int thoiGianBaoHanh; // Số tháng
    private double congSuat; // KW

    public UpdateDienMayDTO(String maHang, String tenHang, String tenLoai, int soLuongTon, double donGia, int thoiGianBaoHanh, double congSuat) {
        super(maHang, tenHang, tenLoai, soLuongTon, donGia);
        this.thoiGianBaoHanh = thoiGianBaoHanh;
        this.congSuat = congSuat;
    }

    public int getThoiGianBaoHanh() {
        return thoiGianBaoHanh;
    }

    public void setThoiGianBaoHanh(int thoiGianBaoHanh) {
        this.thoiGianBaoHanh = thoiGianBaoHanh;
    }

    public double getCongSuat() {
        return congSuat;
    }

    public void setCongSuat(double congSuat) {
        this.congSuat = congSuat;
    }
}