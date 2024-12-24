package com.example.removeProduct.usecase;

import com.example.removeProduct.database.RemoveProductDAO;
import com.example.removeProduct.database.RemoveProductDatabaseBoundary;
import com.example.removeProduct.dto.RemoveProductDTO;
import com.example.removeProduct.dto.RemoveProductOutputDTO;
import com.example.removeProduct.view.RemoveProductOutputBoundary;

import java.sql.SQLException;

public class RemoveProductUseCase implements RemoveProductInputBoundary {
    private RemoveProductDatabaseBoundary removeDatabaseBoundary = null;
    private RemoveProductOutputBoundary removeOutputBoundary = null;

    public RemoveProductUseCase(RemoveProductDatabaseBoundary removeDatabaseBoundary, RemoveProductOutputBoundary removeOutputBoundary) {
        this.removeDatabaseBoundary = removeDatabaseBoundary;
        this.removeOutputBoundary = removeOutputBoundary;
    }

    @Override
    public void execute(RemoveProductDTO dto ) throws SQLException {
        RemoveProductOutputDTO removeProductOutputDTO = new RemoveProductOutputDTO();
        if (dto.getMaHang() == null || dto.getMaHang().isEmpty()) {
            removeProductOutputDTO.setMessage("Mã hàng không được để trống");
            removeOutputBoundary.presenter(removeProductOutputDTO);
            removeOutputBoundary.exportResult(removeProductOutputDTO);
            return;
        } else if (!validate(dto.getMaHang())) {
            removeProductOutputDTO.setMessage("Mã hàng phải bắt đầu bằng HTP, HDM, HSS");
            removeOutputBoundary.presenter(removeProductOutputDTO);
            removeOutputBoundary.exportResult(removeProductOutputDTO);
            return;
        }


        RemoveProductDAO database = (RemoveProductDAO) removeDatabaseBoundary;
        boolean isRemove = database.removeProduct(dto.getMaHang());
        if (isRemove) {
            removeProductOutputDTO.setMessage("Xóa sản phẩm thành công");
        } else {
            removeProductOutputDTO.setMessage("Mã sản phẩm không tồn tại");
        }
        removeOutputBoundary.presenter(removeProductOutputDTO);
        removeOutputBoundary.exportResult(removeProductOutputDTO);
    }


    public boolean validate(String maHang) {
        return maHang.startsWith("HTP") || maHang.startsWith("HSS") || maHang.startsWith("HDM");

    }



}
