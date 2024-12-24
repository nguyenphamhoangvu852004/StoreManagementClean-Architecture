package com.example.ui.GetProductDetailInfoMVVP;

import java.time.LocalDate;

public class DienMayDetailInfoModelView {
    private String maHang;
    private String tenHang;
    private int soLuongTon;
    private double donGia;
    private int thoiGianBaoHanh;
    private double congSuat;

    public DienMayDetailInfoModelView(String maHang, String tenHang, int soLuongTon, double donGia, int thoiGianBaoHanh, double congSuat) {
        this.maHang = maHang;
        this.tenHang = tenHang;
        this.soLuongTon = soLuongTon;
        this.donGia = donGia;
        this.thoiGianBaoHanh = thoiGianBaoHanh;
        this.congSuat = congSuat;
    }

    public String getMaHang() {
        return maHang;
    }

    public String getTenHang() {
        return tenHang;
    }

    public int getSoLuongTon() {
        return soLuongTon;
    }

    public double getDonGia() {
        return donGia;
    }

    public int getThoiGianBaoHanh() {
        return thoiGianBaoHanh;
    }

    public double getCongSuat() {
        return congSuat;
    }
}
