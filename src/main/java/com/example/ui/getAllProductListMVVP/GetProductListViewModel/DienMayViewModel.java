package com.example.ui.getAllProductListMVVP.GetProductListViewModel;

public class DienMayViewModel extends GetProductlistViewModel {
    public String thoiGianBaoHanh;
    public String congSuat;

    public DienMayViewModel(String maHang, String tenHang, String soLuongTon, String donGia, String VAT, String tenLoai,
                            String thoiGianBaoHanh, String congSuat) {
        super(maHang, tenHang, soLuongTon, donGia, VAT, tenLoai);
        this.thoiGianBaoHanh = thoiGianBaoHanh;
        this.congSuat = congSuat;
    }

    public String getThoiGianBaoHanh() {
        return thoiGianBaoHanh;
    }

    public String getCongSuat() {
        return congSuat;
    }
}
