package com.example.removeProduct.view;

import com.example.removeProduct.dto.RemoveProductOutputDTO;

import java.sql.SQLException;

public interface RemoveProductOutputBoundary {
    void presenter(RemoveProductOutputDTO productOutputDTO) throws SQLException;
    void exportResult(RemoveProductOutputDTO productOutputDTO);
}
