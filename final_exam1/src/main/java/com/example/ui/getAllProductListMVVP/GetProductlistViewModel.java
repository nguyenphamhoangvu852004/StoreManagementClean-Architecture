package com.example.ui.getAllProductListMVVP;

public class GetProductlistViewModel {
    public String maHang;
    public String tenHang;
    public String soLuongTon;
    public String donGia;
    public String VAT;

    public GetProductlistViewModel(String maHang, String tenHang, String soLuongTon, String donGia, String VAT) {
        this.maHang = maHang;
        this.tenHang = tenHang;
        this.soLuongTon = soLuongTon;
        this.donGia = donGia;
        this.VAT = VAT;
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
}