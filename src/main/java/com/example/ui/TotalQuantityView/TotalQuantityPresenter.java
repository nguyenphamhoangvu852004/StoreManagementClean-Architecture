package com.example.ui.TotalQuantityView;

import com.example.dtos.totalQuantityDTOs.TotalQuantityDienMayResponseDTO;
import com.example.dtos.totalQuantityDTOs.TotalQuantitySanhSuResponseDTO;
import com.example.dtos.totalQuantityDTOs.TotalQuantityThucPhamResponseDTO;
import com.example.interfaces.OutputBoundary;
import com.example.interfaces.ResponseData;

import java.sql.SQLException;

public class TotalQuantityPresenter implements OutputBoundary {
    private TotalQuantityView totalQuantityView;

    public TotalQuantityPresenter(TotalQuantityView totalQuantityView) {
        this.totalQuantityView = totalQuantityView;
    }

    @Override
    public void exportResult(ResponseData responseData) throws SQLException {
        if (responseData instanceof TotalQuantityDienMayResponseDTO) {
            TotalQuantityViewModel totalQuantityViewModel = new TotalQuantityViewModel(((TotalQuantityDienMayResponseDTO) responseData).getTotalQuantity());
            totalQuantityView.setQuantityDienMayLabel(totalQuantityViewModel.getQuantity());
        } else if (responseData instanceof TotalQuantitySanhSuResponseDTO) {
            TotalQuantityViewModel totalQuantityViewModel = new TotalQuantityViewModel(((TotalQuantitySanhSuResponseDTO) responseData).getTotalQuantity());
            totalQuantityView.setQuantitySanhSuLabel(totalQuantityViewModel.getQuantity());
        } else if (responseData instanceof TotalQuantityThucPhamResponseDTO) {
            TotalQuantityViewModel totalQuantityViewModel = new TotalQuantityViewModel(((TotalQuantityThucPhamResponseDTO) responseData).getTotalQuantity());
            totalQuantityView.setQuantityThucPhamLabel(totalQuantityViewModel.getQuantity());
        }
    }
}