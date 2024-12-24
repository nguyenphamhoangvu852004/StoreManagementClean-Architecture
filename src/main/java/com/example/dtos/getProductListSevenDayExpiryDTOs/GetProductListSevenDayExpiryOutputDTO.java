package com.example.dtos.getProductListSevenDayExpiryDTOs;

import com.example.interfaces.ResponseData;

import java.time.LocalDate;

public class GetProductListSevenDayExpiryOutputDTO implements ResponseData {
    protected String maHang;
    protected String tenHang;
    protected int soLuongTon;
    protected double donGia;
    protected double VAT;
    private LocalDate ngaySanXuat;
    private LocalDate ngayHetHan;
    private String nhaCungCap;

    public GetProductListSevenDayExpiryOutputDTO(String maHang, String tenHang, int soLuongTon, double donGia, double VAT, LocalDate ngaySanXuat, LocalDate ngayHetHan, String nhaCungCap) {
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

    public int getSoLuongTon() {
        return soLuongTon;
    }

    public double getDonGia() {
        return donGia;
    }

    public double getVAT() {
        return VAT;
    }

    public LocalDate getNgaySanXuat() {
        return ngaySanXuat;
    }

    public LocalDate getNgayHetHan() {
        return ngayHetHan;
    }

    public String getNhaCungCap() {
        return nhaCungCap;
    }
}
