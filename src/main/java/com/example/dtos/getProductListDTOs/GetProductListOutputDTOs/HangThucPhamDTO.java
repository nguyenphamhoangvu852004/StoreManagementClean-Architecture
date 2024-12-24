package com.example.dtos.getProductListDTOs.GetProductListOutputDTOs;

import java.time.LocalDate;

// DTO cho hàng thực phẩm
public class HangThucPhamDTO extends GetProductListOutputDTO {
    private LocalDate ngaySanXuat;
    private LocalDate ngayHetHan;
    private String nhaCungCap;

    public HangThucPhamDTO(String maHang, String tenHang, int soLuongTon, double donGia, double VAT, String tenLoai,
                           LocalDate ngaySanXuat, LocalDate ngayHetHan, String nhaCungCap) {
        super(maHang, tenHang, soLuongTon, donGia, VAT,tenLoai);
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
