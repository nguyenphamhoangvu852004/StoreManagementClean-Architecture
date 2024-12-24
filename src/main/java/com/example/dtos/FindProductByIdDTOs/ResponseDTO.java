package com.example.dtos.FindProductByIdDTOs;

import com.example.interfaces.ResponseData;

public class ResponseDTO implements ResponseData {
    private String contentMessage;
    private String maHang;
    private String tenHang;
    private String tenLoai;
    private int soLuongTon;
    private double donGia;
    private double VAT;

    public ResponseDTO(String contentMessage, String maHang, String tenHang, String tenLoai, int soLuongTon, double donGia, double VAT) {
        this.contentMessage = contentMessage;
        this.maHang = maHang;
        this.tenHang = tenHang;
        this.tenLoai = tenLoai;
        this.soLuongTon = soLuongTon;
        this.donGia = donGia;
        this.VAT = VAT;
    }

    public String getContentMessage() {
        return contentMessage;
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

    public int getSoLuongTon() {
        return soLuongTon;
    }

    public double getDonGia() {
        return donGia;
    }

    public double getVAT() {
        return VAT;
    }
}
