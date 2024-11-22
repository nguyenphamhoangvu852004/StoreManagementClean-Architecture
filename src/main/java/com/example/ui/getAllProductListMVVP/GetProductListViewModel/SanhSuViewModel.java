package com.example.ui.getAllProductListMVVP.GetProductListViewModel;

public class SanhSuViewModel extends GetProductlistViewModel {
    public String nhaSanXuat;
    public String ngayNhapKho;

    public SanhSuViewModel(String maHang, String tenHang, String soLuongTon, String donGia, String VAT, String tenLoai,
                           String nhaSanXuat, String ngayNhapKho) {
        super(maHang, tenHang, soLuongTon, donGia, VAT, tenLoai);
        this.nhaSanXuat = nhaSanXuat;
        this.ngayNhapKho = ngayNhapKho;
    }

    public String getNhaSanXuat() {
        return nhaSanXuat;
    }

    public String getNgayNhapKho() {
        return ngayNhapKho;
    }
}
