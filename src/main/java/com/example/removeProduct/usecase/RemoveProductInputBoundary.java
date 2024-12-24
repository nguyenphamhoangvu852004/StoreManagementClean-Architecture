package com.example.removeProduct.usecase;

import com.example.removeProduct.dto.RemoveProductDTO;

import java.sql.SQLException;

public interface RemoveProductInputBoundary {
    void execute(RemoveProductDTO dto) throws SQLException;
}
