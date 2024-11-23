package com.example.dtos.productInfoDetail.Response;

import com.example.interfaces.ResponseData;

public abstract class ProductHangHoaDetailInfoResponseDTO implements ResponseData {
    protected String maHang;
    protected String tenHang;
    protected int soLuongTon;
    protected double donGia;
    protected String tenLoai;

    public ProductHangHoaDetailInfoResponseDTO(String maHang, String tenHang, int soLuongTon, double donGia, String tenLoai) {
        this.maHang = maHang;
        this.tenHang = tenHang;
        this.soLuongTon = soLuongTon;
        this.donGia = donGia;
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

    public String getTenLoai() {
        return tenLoai;
    }
}