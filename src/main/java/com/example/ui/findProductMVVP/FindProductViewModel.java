package com.example.ui.findProductMVVP;

public class FindProductViewModel {
    private final String maHang;
    private final String tenHang;
    private final String tenLoai;
    private final String soLuongTon;
    private final String donGia;
    private final String vat;

    public FindProductViewModel(String maHang, String tenHang, String tenLoai, String soLuongTon, String donGia, String vat) {
        this.maHang = maHang;
        this.tenHang = tenHang;
        this.tenLoai = tenLoai;
        this.soLuongTon = soLuongTon;
        this.donGia = donGia;
        this.vat = vat;
    }

    public String getMaHang() {
        return maHang;
    }

    public String getTenHang() {
        return tenHang;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public String getSoLuongTon() {
        return soLuongTon;
    }

    public String getDonGia() {
        return donGia;
    }

    public String getVat() {
        return vat;
    }
}
