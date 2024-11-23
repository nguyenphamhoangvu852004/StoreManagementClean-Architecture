package com.example.ui.updateProductMVVP;

import com.example.dtos.updateProductDTOS.UpdateHangHoaResponseDTO;
import com.example.interfaces.OutputBoundary;
import com.example.interfaces.ResponseData;
import com.example.ui.MainView;

import java.sql.SQLException;

public class UpdateProductPresenter implements OutputBoundary {


    private UpdateProductView updateProductView;

    public UpdateProductPresenter(UpdateProductView updateProductView) {
        this.updateProductView = updateProductView;
    }

    @Override
    public void exportResult(ResponseData responseData) throws SQLException {
        UpdateHangHoaResponseDTO updateHangHoaResponseDTO = (UpdateHangHoaResponseDTO) responseData;
        String message = updateHangHoaResponseDTO.getMessage();

        if (message.contains("thành công")) {

            UpdateProductViewModel updateProductViewModel = new UpdateProductViewModel(message);
            updateProductView.showUpdateSuccessMessage(updateProductViewModel);
        } else {
            UpdateProductViewModel updateProductViewModel = new UpdateProductViewModel(message);
            updateProductView.showUpdateFailedMessage(updateProductViewModel);
        }
    }
}