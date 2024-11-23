package com.example.usecase.totalQuantityUseCase;

import com.example.database.QuantityProduct.QuantityProduct;
import com.example.dtos.totalQuantityDTOs.*;
import com.example.interfaces.*;

import java.sql.SQLException;


public class TotalQuantityUseCase implements InputBoundary {

    private DatabaseBoundary databaseBoundary = null;

    private OutputBoundary outputBoundary = null;

    public TotalQuantityUseCase(DatabaseBoundary databaseBoundary, OutputBoundary outputBoundary) {
        this.databaseBoundary = databaseBoundary;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute(RequestData requestData) throws SQLException {
        if (requestData instanceof TotalQuantityDienMayDTO) {
            int totalQuantity = ((QuantityProduct) databaseBoundary).getTotalQuantityByType("HDM");
            ResponseData responseData = new TotalQuantityDienMayResponseDTO(totalQuantity);
            outputBoundary.exportResult(responseData);
        } else if (requestData instanceof TotalQuantitySanhSuDTO) {
            int totalQuantity = ((QuantityProduct) databaseBoundary).getTotalQuantityByType("HSS");
            ResponseData responseData = new TotalQuantitySanhSuResponseDTO(totalQuantity);
            outputBoundary.exportResult(responseData);
        } else if (requestData instanceof TotalQuantityThucPhamDTO) {
            int totalQuantity = ((QuantityProduct) databaseBoundary).getTotalQuantityByType("HTP");
            ResponseData responseData = new TotalQuantityThucPhamResponseDTO(totalQuantity);
            outputBoundary.exportResult(responseData);
        }
    }
}
