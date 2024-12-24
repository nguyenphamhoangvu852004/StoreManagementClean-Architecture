package com.example.removeProduct.dto;


import com.example.interfaces.RequestData;

public class RemoveProductDTO implements RequestData {
    private String maHang;

    public RemoveProductDTO(String maHang) {
        this.maHang = maHang;
    }

    public RemoveProductDTO() {}

    public void setMaHang(String maHang) {
        this.maHang = maHang;
    }

    public String getMaHang() {
        return maHang;
    }
}
