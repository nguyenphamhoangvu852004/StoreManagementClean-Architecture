package com.example.ui.GetProductDetailInfoMVVP;

import com.example.dtos.productInfoDetail.Response.DienMayDetailInfoResponseDTO;
import com.example.dtos.productInfoDetail.Response.SanhSuDetailInfoResponseDTO;
import com.example.dtos.productInfoDetail.Response.ThucPhamDetailInfoResponseDTO;
import com.example.interfaces.OutputBoundary;
import com.example.interfaces.ResponseData;
import com.example.ui.updateProductMVVP.UpdateProductView;

import java.sql.SQLException;

public class GetProductDetailInfoPresenter implements OutputBoundary {

    private UpdateProductView updateProductView;

    public GetProductDetailInfoPresenter(UpdateProductView updateProductView) {

        this.updateProductView = updateProductView;
    }

    @Override
    public void exportResult(ResponseData responseData) throws SQLException {
        if (responseData instanceof DienMayDetailInfoResponseDTO) {

            DienMayDetailInfoResponseDTO dienMayResponse = (DienMayDetailInfoResponseDTO) responseData;

            DienMayDetailInfoModelView dienMayDetailInfoModelView = new DienMayDetailInfoModelView(
                    dienMayResponse.getMaHang(),
                    dienMayResponse.getTenHang(),
                    dienMayResponse.getSoLuongTon(),
                    dienMayResponse.getDonGia(),
                    dienMayResponse.getThoiGianBaoHanh(),
                    dienMayResponse.getCongSuat()
            );

            updateProductView.setDienMayDetail(dienMayDetailInfoModelView);
        } else if (responseData instanceof ThucPhamDetailInfoResponseDTO) {

            ThucPhamDetailInfoResponseDTO thucPhamResponse = (ThucPhamDetailInfoResponseDTO) responseData;

            ThucPhamDetailInfoModelView thucPhamDetailInfoModelView = new ThucPhamDetailInfoModelView(
                    thucPhamResponse.getMaHang(),
                    thucPhamResponse.getTenHang(),
                    thucPhamResponse.getSoLuongTon(),
                    thucPhamResponse.getDonGia(),
                    thucPhamResponse.getNgaySanXuat(),
                    thucPhamResponse.getNgayHetHan(),
                    thucPhamResponse.getNhaCungCap()
            );

            updateProductView.setThucPhamDetail(thucPhamDetailInfoModelView);
        } else if (responseData instanceof SanhSuDetailInfoResponseDTO) {

            SanhSuDetailInfoResponseDTO sanhSuResponse = (SanhSuDetailInfoResponseDTO) responseData;

            SanhSuDetailInfoModelView sanhSuDetailInfoModelView = new SanhSuDetailInfoModelView(
                    sanhSuResponse.getMaHang(),
                    sanhSuResponse.getTenHang(),
                    sanhSuResponse.getSoLuongTon(),
                    sanhSuResponse.getDonGia(),
                    sanhSuResponse.getNhaSanXuat(),
                    sanhSuResponse.getNgayNhapKho()
            );

            updateProductView.setSanhSuDetail(sanhSuDetailInfoModelView);
        }
    }
}
