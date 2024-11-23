package com.example.ui.getAllProductListSevenDaysExpiryMVVP;

public class GetProductListSevenDaysExpiryViewModel {
    public String maHang;
    public String tenHang;
    public String soLuongTon;
    public String donGia;
    public String VAT;
    public String ngaySanXuat;
    public String ngayHetHan;
    public String nhaCungCap;

    public GetProductListSevenDaysExpiryViewModel(String maHang, String tenHang, String soLuongTon, String donGia, String VAT, String ngaySanXuat, String ngayHetHan, String nhaCungCap) {
        this.maHang = maHang;
        this.tenHang = tenHang;
        this.soLuongTon = soLuongTon;
        this.donGia = donGia;
        this.VAT = VAT;
        this.ngaySanXuat = ngaySanXuat;
        this.ngayHetHan = ngayHetHan;
        this.nhaCungCap = nhaCungCap;
    }

    public String getMaHang() {
        return maHang;
    }

    public String getTenHang() {
        return tenHang;
    }

    public String getSoLuongTon() {
        return soLuongTon;
    }

    public String getDonGia() {
        return donGia;
    }

    public String getVAT() {
        return VAT;
    }

    public String getNgaySanXuat() {
        return ngaySanXuat;
    }

    public String getNgayHetHan() {
        return ngayHetHan;
    }

    public String getNhaCungCap() {
        return nhaCungCap;
    }
}
