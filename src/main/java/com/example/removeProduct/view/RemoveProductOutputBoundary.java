package com.example.removeProduct.view;

import com.example.removeProduct.dto.RemoveProductOutputDTO;

public interface RemoveProductOutputBoundary {
    void presenter(RemoveProductOutputDTO productOutputDTO);
    void exportResult(RemoveProductOutputDTO productOutputDTO);
}
