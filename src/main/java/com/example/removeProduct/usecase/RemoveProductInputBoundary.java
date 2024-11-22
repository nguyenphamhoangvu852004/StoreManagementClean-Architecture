package com.example.removeProduct.usecase;

import com.example.removeProduct.dto.RemoveProductDTO;

public interface RemoveProductInputBoundary {
    void execute(RemoveProductDTO dto);
}
