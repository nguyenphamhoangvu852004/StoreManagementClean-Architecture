package com.example.dtos.FindProductByIdDTOs;

import com.example.interfaces.RequestData;

public class FindProductDTO implements RequestData {
    private String maHang;

    public FindProductDTO(String maHang) {
        this.maHang = maHang;
    }

    public String getMaHang() {
        return maHang;
    }

    public void setMaHang(String maHang) {
        this.maHang = maHang;
    }
}
