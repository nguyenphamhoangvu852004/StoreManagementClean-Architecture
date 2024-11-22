package com.example.dtos.getProductListDTOs.GetProductListOutputDTOs;

public class GetProductListOutputDTO {
    protected String maHang;
    protected String tenHang;
    protected int soLuongTon;
    protected double donGia;
    protected double VAT;
    protected String tenLoai;
    public GetProductListOutputDTO(String maHang, String tenHang, int soLuongTon, double donGia, double VAT,String tenLoai) {
        this.maHang = maHang;
        this.tenHang = tenHang;
        this.soLuongTon = soLuongTon;
        this.donGia = donGia;
        this.VAT = VAT;
        this.tenLoai = tenLoai;
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

    public double getVAT() {
        return VAT;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }
}

