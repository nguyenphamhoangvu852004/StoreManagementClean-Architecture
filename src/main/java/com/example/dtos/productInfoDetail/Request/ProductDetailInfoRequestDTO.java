package com.example.dtos.productInfoDetail.Request;

import com.example.interfaces.RequestData;

public abstract class ProductDetailInfoRequestDTO implements RequestData {
    private String maHang;

    public ProductDetailInfoRequestDTO(String maHang) {
        this.maHang = maHang;
    }

    public String getMaHang() {
        return maHang;
    }
}
