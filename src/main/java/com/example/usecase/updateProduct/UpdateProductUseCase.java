package com.example.usecase.updateProduct;


import com.example.database.UpdateProduct.UpdateProductSqlDAO;
import com.example.dtos.updateProductDTOS.UpdateDienMayDTO;
import com.example.dtos.updateProductDTOS.UpdateHangHoaResponseDTO;
import com.example.dtos.updateProductDTOS.UpdateSanhSuDTO;
import com.example.dtos.updateProductDTOS.UpdateThucPhamDTO;
import com.example.entity.HangDienMay;
import com.example.entity.HangHoa;
import com.example.entity.HangSanhSu;
import com.example.entity.HangThucPham;
import com.example.interfaces.*;
import com.example.ui.getAllProductListMVVP.GetProductListSubject;

import java.sql.SQLException;

public class UpdateProductUseCase implements InputBoundary {

    private DatabaseBoundary databaseUpdateBoundary = null;
    private OutputBoundary outputBoundary = null;

    public UpdateProductUseCase(DatabaseBoundary databaseUpdateBoundary, OutputBoundary outputBoundary) {
        this.databaseUpdateBoundary = databaseUpdateBoundary;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute(RequestData requestData) throws SQLException {
        if (requestData instanceof UpdateDienMayDTO) {
            UpdateDienMayDTO updateDienMayDTO = (UpdateDienMayDTO) requestData;

            if (updateDienMayDTO.getMaHang().isEmpty()) {
                ResponseData responseData = new UpdateHangHoaResponseDTO("Mã hàng không được để trống");
                outputBoundary.exportResult(responseData);
                return;
            }

            if (!updateDienMayDTO.getMaHang().contains("HDM")) {
                ResponseData responseData = new UpdateHangHoaResponseDTO("Mã hàng điện máy phải chứa kí tự HDM");
                outputBoundary.exportResult(responseData);
                return;
            }

            if (updateDienMayDTO.getTenHang().isEmpty()) {
                ResponseData responseData = new UpdateHangHoaResponseDTO("Tên hàng không được để trống");
                outputBoundary.exportResult(responseData);
                return;
            }

            if (updateDienMayDTO.getSoLuongTon() < 0) {
                ResponseData responseData = new UpdateHangHoaResponseDTO("Số lượng tồn không được bé hơn 0");
                outputBoundary.exportResult(responseData);
                return;
            }

            if (updateDienMayDTO.getDonGia() <= 0) {
                ResponseData responseData = new UpdateHangHoaResponseDTO("Đơn giá không được bé hơn 0");
                outputBoundary.exportResult(responseData);
                return;
            }

            if (updateDienMayDTO.getCongSuat() <= 0) {
                ResponseData responseData = new UpdateHangHoaResponseDTO("Công suất không được bé hơn 0");
                outputBoundary.exportResult(responseData);
                return;
            }

            if (updateDienMayDTO.getThoiGianBaoHanh() < 0) {
                ResponseData responseData = new UpdateHangHoaResponseDTO("Thời gian bảo hành không được bé hơn 0");
                outputBoundary.exportResult(responseData);
                return;
            }

            HangHoa hangDienMay = new HangDienMay(updateDienMayDTO.getMaHang(), updateDienMayDTO.getTenHang(), updateDienMayDTO.getSoLuongTon(), updateDienMayDTO.getDonGia(), updateDienMayDTO.getTenLoai(), updateDienMayDTO.getThoiGianBaoHanh(), updateDienMayDTO.getCongSuat());
            String message = ((UpdateProductSqlDAO) databaseUpdateBoundary).updateProduct(hangDienMay);

            ResponseData responseData = new UpdateHangHoaResponseDTO(message);
            outputBoundary.exportResult(responseData);
        } else if (requestData instanceof UpdateSanhSuDTO) {
            UpdateSanhSuDTO sanhSuDTO = (UpdateSanhSuDTO) requestData;

            if (sanhSuDTO.getMaHang().isEmpty()) {
                ResponseData responseData = new UpdateHangHoaResponseDTO("Mã hàng không được để trống");
                outputBoundary.exportResult(responseData);
                return;
            }


            if (!sanhSuDTO.getMaHang().contains("HSS")) {
                ResponseData responseData = new UpdateHangHoaResponseDTO("Mã hàng sành sứ phải chứa kí tự HSS");
                outputBoundary.exportResult(responseData);
                return;
            }

            if (sanhSuDTO.getTenHang().isEmpty()) {
                ResponseData responseData = new UpdateHangHoaResponseDTO("Tên hàng không được để trống");
                outputBoundary.exportResult(responseData);
                return;
            }

            if (sanhSuDTO.getSoLuongTon() < 0) {
                ResponseData responseData = new UpdateHangHoaResponseDTO("Số lượng không được bé hơn 0");
                outputBoundary.exportResult(responseData);
                return;
            }

            if (sanhSuDTO.getDonGia() <= 0) {
                ResponseData responseData = new UpdateHangHoaResponseDTO("Đơn giá không được bé hơn 0");
                outputBoundary.exportResult(responseData);
                return;
            }

            if (sanhSuDTO.getNgayNhapKho() == null) {
                ResponseData responseData = new UpdateHangHoaResponseDTO("Ngày nhập kho không được để trống");
                outputBoundary.exportResult(responseData);
                return;
            }

            if (sanhSuDTO.getNhaSanXuat().isEmpty()) {
                ResponseData responseData = new UpdateHangHoaResponseDTO("Nhà sản xuất không được để trống");
                outputBoundary.exportResult(responseData);
                return;
            }


            HangHoa hangSanhSu = new HangSanhSu(sanhSuDTO.getMaHang(), sanhSuDTO.getTenHang(), sanhSuDTO.getSoLuongTon(), sanhSuDTO.getDonGia(), sanhSuDTO.getTenLoai(), sanhSuDTO.getNhaSanXuat(), sanhSuDTO.getNgayNhapKho());
            String message = ((UpdateProductSqlDAO) databaseUpdateBoundary).updateProduct(hangSanhSu);
            ResponseData responseData = new UpdateHangHoaResponseDTO(message);
            outputBoundary.exportResult(responseData);
        } else if (requestData instanceof UpdateThucPhamDTO) {
            UpdateThucPhamDTO thucPhamDTO = (UpdateThucPhamDTO) requestData;

            if (thucPhamDTO.getMaHang().isEmpty()) {
                ResponseData responseData = new UpdateHangHoaResponseDTO("Mã hàng không được để trống");
                outputBoundary.exportResult(responseData);
                return;
            }


            if (!thucPhamDTO.getMaHang().contains("HTP")) {
                ResponseData responseData = new UpdateHangHoaResponseDTO("Mã hàng thực phẩm phải chứa kí tự HT");
                outputBoundary.exportResult(responseData);
                return;
            }

            if (thucPhamDTO.getTenHang().isEmpty()) {
                ResponseData responseData = new UpdateHangHoaResponseDTO("Tên hàng không được để trống");
                outputBoundary.exportResult(responseData);
                return;
            }

            if (thucPhamDTO.getSoLuongTon() < 0) {
                ResponseData responseData = new UpdateHangHoaResponseDTO("Số lượng không được bé hơn 0");
                outputBoundary.exportResult(responseData);
                return;
            }

            if (thucPhamDTO.getDonGia() <= 0) {
                ResponseData responseData = new UpdateHangHoaResponseDTO("Đơn giá phải lớn hơn 0");
                outputBoundary.exportResult(responseData);
                return;
            }

            if (thucPhamDTO.getNgaySanXuat() == null) {
                ResponseData responseData = new UpdateHangHoaResponseDTO("Ngày sản xuất không được để trống");
                outputBoundary.exportResult(responseData);
                return;
            }

            if (thucPhamDTO.getNgayHetHan() == null) {
                ResponseData responseData = new UpdateHangHoaResponseDTO("Ngày hết hợp không là ngày không sử dụng");
                outputBoundary.exportResult(responseData);
                return;
            }

            if (thucPhamDTO.getNhaCungCap().isEmpty()) {
                ResponseData responseData = new UpdateHangHoaResponseDTO("Nhà cung cấp không được để trống");
                outputBoundary.exportResult(responseData);
                return;
            }

            if (thucPhamDTO.getNgayHetHan().isBefore(thucPhamDTO.getNgaySanXuat())) {
                ResponseData responseData = new UpdateHangHoaResponseDTO("Ngày hết hạn không được bé hơn ngày sản xuất");
                outputBoundary.exportResult(responseData);
                return;
            }

            HangHoa hangThucPham = new HangThucPham(thucPhamDTO.getMaHang(), thucPhamDTO.getTenHang(), thucPhamDTO.getSoLuongTon(), thucPhamDTO.getDonGia(), thucPhamDTO.getTenLoai(), thucPhamDTO.getNgaySanXuat(), thucPhamDTO.getNgayHetHan(), thucPhamDTO.getNhaCungCap());
            String message = ((UpdateProductSqlDAO) databaseUpdateBoundary).updateProduct(hangThucPham);
            ResponseData responseData = new UpdateHangHoaResponseDTO(message);
            outputBoundary.exportResult(responseData);
        }
    }
}
