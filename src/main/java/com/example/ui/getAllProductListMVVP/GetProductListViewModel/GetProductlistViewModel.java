package com.example.ui.getAllProductListMVVP.GetProductListViewModel;



public class GetProductlistViewModel {
    public String maHang;
    public String tenHang;
    public String soLuongTon;
    public String donGia;
    public String VAT;
    public String tenLoai;
    public GetProductlistViewModel(String maHang, String tenHang, String soLuongTon, String donGia, String VAT,String tenLoai) {
        this.maHang = maHang;
        this.tenHang = tenHang;
        this.soLuongTon = soLuongTon;
        this.donGia = donGia;
        this.VAT = VAT;
        this.tenLoai = tenLoai;
    }


}

