package com.example.ui.getAllProductListMVVP.GetProductListViewModel;

public class ThucPhamViewModel extends GetProductlistViewModel {
    public String ngaySanXuat;
    public String ngayHetHan;
    public String nhaCungCap;

    public ThucPhamViewModel(String maHang, String tenHang, String soLuongTon, String donGia, String VAT,String tenLoai,
                             String ngaySanXuat, String ngayHetHan, String nhaCungCap) {
        super(maHang, tenHang, soLuongTon, donGia, VAT,tenLoai);
        this.ngaySanXuat = ngaySanXuat;
        this.ngayHetHan = ngayHetHan;
        this.nhaCungCap = nhaCungCap;
    }

    public String getNgaySanXuat() {
        return ngaySanXuat;
    }

    public String getNgayHetHan() {
        return ngayHetHan;
    }

    public String getNhaCungCap() {
        return nhaCungCap;
    }
}
