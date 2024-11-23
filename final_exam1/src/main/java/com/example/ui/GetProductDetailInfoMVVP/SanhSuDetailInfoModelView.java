package com.example.ui.GetProductDetailInfoMVVP;

import java.time.LocalDate;

public class SanhSuDetailInfoModelView {
    private String maHang;
    private String tenHang;
    private int soLuongTon;
    private double donGia;
    private String nhaSanXuat;
    private LocalDate ngayNhapKho;

    public SanhSuDetailInfoModelView(String maHang, String tenHang, int soLuongTon, double donGia, String nhaSanXuat, LocalDate ngayNhapKho) {
        this.maHang = maHang;
        this.tenHang = tenHang;
        this.soLuongTon = soLuongTon;
        this.donGia = donGia;
        this.nhaSanXuat = nhaSanXuat;
        this.ngayNhapKho = ngayNhapKho;
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

    public String getNhaSanXuat() {
        return nhaSanXuat;
    }

    public LocalDate getNgayNhapKho() {
        return ngayNhapKho;
    }
}

