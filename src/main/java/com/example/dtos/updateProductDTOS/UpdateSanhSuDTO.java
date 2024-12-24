package com.example.dtos.updateProductDTOS;

import com.example.interfaces.RequestData;

import java.time.LocalDate;

public class UpdateSanhSuDTO extends UpdateHangHoaDTO implements RequestData {
    private String nhaSanXuat;
    private LocalDate ngayNhapKho;

    public UpdateSanhSuDTO(String maHang, String tenHang, String tenLoai, int soLuongTon, double donGia, String nhaSanXuat, LocalDate ngayNhapKho) {
        super(maHang, tenHang, tenLoai, soLuongTon, donGia);
        this.nhaSanXuat = nhaSanXuat;
        this.ngayNhapKho = ngayNhapKho;
    }

    public String getNhaSanXuat() {
        return nhaSanXuat;
    }

    public void setNhaSanXuat(String nhaSanXuat) {
        this.nhaSanXuat = nhaSanXuat;
    }

    public LocalDate getNgayNhapKho() {
        return ngayNhapKho;
    }

    public void setNgayNhapKho(LocalDate ngayNhapKho) {
        this.ngayNhapKho = ngayNhapKho;
    }
}
