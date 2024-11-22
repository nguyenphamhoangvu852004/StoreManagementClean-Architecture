package com.example.dtos.getProductListDTOs.GetProductListOutputDTOs;

import java.time.LocalDate;

// DTO cho hàng sành sứ
public class HangSanhSuDTO extends GetProductListOutputDTO {
    private String nhaSanXuat;
    private LocalDate ngayNhapKho;

    public HangSanhSuDTO(String maHang, String tenHang, int soLuongTon, double donGia, double VAT,String tenLoai,
                         String nhaSanXuat, LocalDate ngayNhapKho) {
        super(maHang, tenHang, soLuongTon, donGia, VAT,tenLoai);
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
