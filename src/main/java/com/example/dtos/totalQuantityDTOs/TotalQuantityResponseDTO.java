package com.example.dtos.totalQuantityDTOs;

import com.example.interfaces.ResponseData;

public abstract class TotalQuantityResponseDTO implements ResponseData {
    private int totalQuantity;

    public TotalQuantityResponseDTO(int quantity) {
        this.totalQuantity = quantity;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }
}
