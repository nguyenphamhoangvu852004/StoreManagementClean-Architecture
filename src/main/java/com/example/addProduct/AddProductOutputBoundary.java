package com.example.addProduct;

import java.sql.SQLException;

public interface AddProductOutputBoundary {
    void presenter(AddProductOutputDTO addProductOutputDTO) throws SQLException;
    void exportResult(AddProductOutputDTO addProductOutputDTO);
}
