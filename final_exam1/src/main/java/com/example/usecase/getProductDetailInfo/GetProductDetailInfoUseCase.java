package com.example.usecase.getProductDetailInfo;

import com.example.database.MysqlGetProductInfoDetail.MysqlGetProductInfoDetail;
import com.example.dtos.productInfoDetail.Request.DienMayDetailInfoRequestDTO;
import com.example.dtos.productInfoDetail.Request.SanhSuDetailInfoRequestDTO;
import com.example.dtos.productInfoDetail.Request.ThucPhamDetailInfoRequestDTO;
import com.example.dtos.productInfoDetail.Response.DienMayDetailInfoResponseDTO;
import com.example.dtos.productInfoDetail.Response.SanhSuDetailInfoResponseDTO;
import com.example.dtos.productInfoDetail.Response.ThucPhamDetailInfoResponseDTO;
import com.example.entity.HangDienMay;
import com.example.entity.HangHoa;
import com.example.entity.HangSanhSu;
import com.example.entity.HangThucPham;
import com.example.interfaces.*;

import java.sql.SQLException;

public class GetProductDetailInfoUseCase implements InputBoundary {


    public GetProductDetailInfoUseCase(DatabaseBoundary databaseUpdateBoundary, OutputBoundary outputBoundary) {
        this.databaseUpdateBoundary = databaseUpdateBoundary;
        this.outputBoundary = outputBoundary;
    }

    private DatabaseBoundary databaseUpdateBoundary = null;
    private OutputBoundary outputBoundary = null;

    @Override
    public void execute(RequestData requestData) throws SQLException {
        if (requestData instanceof DienMayDetailInfoRequestDTO) {
            DienMayDetailInfoRequestDTO request = (DienMayDetailInfoRequestDTO) requestData;
            HangHoa hangHoa = ((MysqlGetProductInfoDetail) databaseUpdateBoundary).getProductInfoDetail("HangDienMay", request.getMaHang());
            ResponseData responseData = new DienMayDetailInfoResponseDTO(
                    hangHoa.getMaHang(),
                    hangHoa.getTenHang(),
                    hangHoa.getSoLuongTon(),
                    hangHoa.getDonGia(),
                    hangHoa.getTenLoai(),
                    ((HangDienMay) hangHoa).getThoiGianBaoHanh(),
                    ((HangDienMay) hangHoa).getCongSuat());
            outputBoundary.exportResult(responseData);
        } else if (requestData instanceof ThucPhamDetailInfoRequestDTO) {
            ThucPhamDetailInfoRequestDTO request = (ThucPhamDetailInfoRequestDTO) requestData;
            HangHoa hangHoa = ((MysqlGetProductInfoDetail) databaseUpdateBoundary).getProductInfoDetail("HangThucPham", request.getMaHang());
            ResponseData responseData = new ThucPhamDetailInfoResponseDTO(
                    hangHoa.getMaHang(),
                    hangHoa.getTenHang(),
                    hangHoa.getSoLuongTon(),
                    hangHoa.getDonGia(),
                    hangHoa.getTenLoai(),
                    ((HangThucPham) hangHoa).getNgaySanXuat(),
                    ((HangThucPham) hangHoa).getNgayHetHan(),
                    ((HangThucPham) hangHoa).getNhaCungCap()
            );

            outputBoundary.exportResult(responseData);
        } else if (requestData instanceof SanhSuDetailInfoRequestDTO) {
            SanhSuDetailInfoRequestDTO request = (SanhSuDetailInfoRequestDTO) requestData;
            HangHoa hangHoa = ((MysqlGetProductInfoDetail) databaseUpdateBoundary).getProductInfoDetail("HangSanhSu", request.getMaHang());
            ResponseData responseData = new SanhSuDetailInfoResponseDTO(
                    hangHoa.getMaHang(),
                    hangHoa.getTenHang(),
                    hangHoa.getSoLuongTon(),
                    hangHoa.getDonGia(),
                    hangHoa.getTenLoai(),
                    ((HangSanhSu) hangHoa).getNhaSanXuat(),
                    ((HangSanhSu) hangHoa).getNgayNhapKho()
            );
            outputBoundary.exportResult(responseData);
        }
    }
}
