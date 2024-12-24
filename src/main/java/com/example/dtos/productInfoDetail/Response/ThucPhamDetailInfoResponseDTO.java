package com.example.dtos.productInfoDetail.Response;

import com.example.dtos.productInfoDetail.Response.ProductHangHoaDetailInfoResponseDTO;

import java.time.LocalDate;

public class ThucPhamDetailInfoResponseDTO extends ProductHangHoaDetailInfoResponseDTO {

    private LocalDate ngaySanXuat;
    private LocalDate ngayHetHan;
    private String nhaCungCap;

    public ThucPhamDetailInfoResponseDTO(String maHang, String tenHang, int soLuongTon, double donGia, String tenLoai, LocalDate ngaySanXuat, LocalDate ngayHetHan, String nhaCungCap) {
        super(maHang, tenHang, soLuongTon, donGia, tenLoai);
        this.ngaySanXuat = ngaySanXuat;
        this.ngayHetHan = ngayHetHan;
        this.nhaCungCap = nhaCungCap;
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
