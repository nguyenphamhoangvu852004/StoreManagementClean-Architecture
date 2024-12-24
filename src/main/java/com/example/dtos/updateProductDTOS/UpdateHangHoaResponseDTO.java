package com.example.dtos.updateProductDTOS;

import com.example.interfaces.ResponseData;

public class UpdateHangHoaResponseDTO implements ResponseData {
    private String message;

    public UpdateHangHoaResponseDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
