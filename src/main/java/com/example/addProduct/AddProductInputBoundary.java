package com.example.addProduct;

import java.sql.SQLException;

public interface AddProductInputBoundary {
    void execute(AddProductDTO addProductDTO) throws SQLException;
}
