package com.example.dtos.productInfoDetail.Response;

import com.example.dtos.productInfoDetail.Response.ProductHangHoaDetailInfoResponseDTO;

import java.time.LocalDate;

public class SanhSuDetailInfoResponseDTO extends ProductHangHoaDetailInfoResponseDTO {

    private String nhaSanXuat;
    private LocalDate ngayNhapKho;

    public SanhSuDetailInfoResponseDTO(String maHang, String tenHang, int soLuongTon, double donGia, String tenLoai, String nhaSanXuat, LocalDate ngayNhapKho) {
        super(maHang, tenHang, soLuongTon, donGia, tenLoai);
        this.nhaSanXuat = nhaSanXuat;
        this.ngayNhapKho = ngayNhapKho;
    }

    public String getNhaSanXuat() {
        return nhaSanXuat;
    }

    public LocalDate getNgayNhapKho() {
        return ngayNhapKho;
    }
}
