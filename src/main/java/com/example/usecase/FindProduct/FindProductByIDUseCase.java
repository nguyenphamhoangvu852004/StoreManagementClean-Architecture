package com.example.usecase.FindProduct;

import com.example.database.FindProductDAO;
import com.example.dtos.FindProductByIdDTOs.FindProductDTO;
import com.example.dtos.FindProductByIdDTOs.ResponseDTO;
import com.example.entity.HangHoa;
import com.example.interfaces.DatabaseBoundary;
import com.example.interfaces.InputBoundary;
import com.example.interfaces.OutputBoundary;
import com.example.interfaces.RequestData;

import java.sql.SQLException;

public class FindProductByIDUseCase implements InputBoundary {
    private final DatabaseBoundary databaseBoundary;
    private final OutputBoundary outputBoundary;

    public FindProductByIDUseCase(DatabaseBoundary databaseBoundary, OutputBoundary outputBoundary) {
        this.databaseBoundary = databaseBoundary;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute(RequestData requestData) throws SQLException {
        FindProductDTO request = (FindProductDTO) requestData;

        // Kiểm tra mã hàng rỗng
        if (request.getMaHang() == null || request.getMaHang().trim().isEmpty()) {
            ResponseDTO errorResponse = new ResponseDTO(
                    "Mã hàng không được để trống.", null, null, null, 0, 0, 0);
            outputBoundary.exportResult(errorResponse);
            return;
        }

        // Kiểm tra mã hàng có hợp lệ
        if (!isValidMaHang(request.getMaHang())) {
            ResponseDTO errorResponse = new ResponseDTO(
                    "Mã hàng phải bắt đầu bằng HTP, HDM, HSS.", null, null, null, 0, 0, 0);
            outputBoundary.exportResult(errorResponse);
            return;
        }

        // Tìm kiếm sản phẩm
        HangHoa result = ((FindProductDAO) databaseBoundary).findProductById(request.getMaHang());

        // Kiểm tra kết quả tìm kiếm
        if (result == null) {
            ResponseDTO errorResponse = new ResponseDTO(
                    "Tìm kiếm sản phẩm thất bại: Sản phẩm không tồn tại trong hệ thống.",
                    null, null, null, 0, 0, 0);
            outputBoundary.exportResult(errorResponse);
        } else {
            ResponseDTO successResponse = new ResponseDTO(
                    "Thành công",
                    result.getMaHang(),
                    result.getTenHang(),
                    result.getTenLoai(),
                    result.getSoLuongTon(),
                    result.getDonGia(),
                    result.tinhVat());
            outputBoundary.exportResult(successResponse);
        }
    }

    private boolean isValidMaHang(String maHang) {
        return maHang.startsWith("HTP") || maHang.startsWith("HDM") || maHang.startsWith("HSS");
    }
}
