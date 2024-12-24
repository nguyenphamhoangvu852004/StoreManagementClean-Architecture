package com.example.dtos.productInfoDetail.Response;

public class DienMayDetailInfoResponseDTO extends ProductHangHoaDetailInfoResponseDTO {

    private int thoiGianBaoHanh; // Số tháng
    private double congSuat; //

    public DienMayDetailInfoResponseDTO(String maHang, String tenHang, int soLuongTon, double donGia, String tenLoai, int thoiGianBaoHanh, double congSuat) {
        super(maHang, tenHang, soLuongTon, donGia, tenLoai);
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
