package com.example.dtos.getProductListDTOs.GetProductListOutputDTOs;

// DTO cho hàng điện máy
public class HangDienMayDTO extends GetProductListOutputDTO {
    private int thoiGianBaoHanh;
    private double congSuat;

    public HangDienMayDTO(String maHang, String tenHang, int soLuongTon, double donGia, double VAT, String tenLoai,
                          int thoiGianBaoHanh, double congSuat) {
        super(maHang, tenHang, soLuongTon, donGia, VAT, tenLoai);
        this.thoiGianBaoHanh = thoiGianBaoHanh;
        this.congSuat = congSuat;
    }

    public int getThoiGianBaoHanh() {
        return thoiGianBaoHanh;
    }

    public double getCongSuat() {
        return congSuat;
    }
}
