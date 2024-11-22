package com.example.usecase.getProductList;

import com.example.database.MysqlGetProductList;
import com.example.dtos.getProductListDTOs.*;
import com.example.dtos.getProductListDTOs.GetProductListOutputDTOs.GetProductListOutputDTO;
import com.example.dtos.getProductListDTOs.GetProductListOutputDTOs.HangDienMayDTO;
import com.example.dtos.getProductListDTOs.GetProductListOutputDTOs.HangSanhSuDTO;
import com.example.dtos.getProductListDTOs.GetProductListOutputDTOs.HangThucPhamDTO;
import com.example.entity.HangDienMay;
import com.example.entity.HangHoa;
import com.example.entity.HangSanhSu;
import com.example.entity.HangThucPham;
import com.example.interfaces.DatabaseBoundary;
import com.example.interfaces.InputBoundary;
import com.example.interfaces.OutputBoundary;
import com.example.interfaces.RequestData;
import com.example.ui.getAllProductListMVVP.GetProductListPresenter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetProductListUseCase implements InputBoundary {

    private OutputBoundary outputBoundary = null;
    private DatabaseBoundary databaseBoundary = null;

    public GetProductListUseCase(OutputBoundary outputBoundary, DatabaseBoundary databaseBoundary) {
        this.outputBoundary = outputBoundary;
        this.databaseBoundary = databaseBoundary;
    }

    @Override
    public void execute(RequestData requestData) throws SQLException {
        String type = ((GetProductListInputDTO) requestData).getType();

        List<HangHoa> listHangHoa = ((MysqlGetProductList) databaseBoundary).getProductList(type);
        List<GetProductListOutputDTO> listOutputDTOS = new ArrayList<>();

        if ("Tất Cả".equals(type)) {
            for (HangHoa hangHoa : listHangHoa) {
                GetProductListOutputDTO getProductOutputDTO = new GetProductListOutputDTO(
                        hangHoa.getMaHang(),
                        hangHoa.getTenHang(),
                        hangHoa.getSoLuongTon(),
                        hangHoa.getDonGia(),
                        hangHoa.tinhVat(),
                        hangHoa.getTenLoai());

                // Thêm vào danh sách outputDTO
                listOutputDTOS.add(getProductOutputDTO);
            }
            GetProductListResponseData getProductListResponseData = new GetProductListResponseData(listOutputDTOS);
            ((GetProductListPresenter) outputBoundary).exportResult(getProductListResponseData);
        } else if ("Hàng Thực Phẩm".equals(type)) {
            for (HangHoa hangThucPham : listHangHoa) {
                if (hangThucPham instanceof HangThucPham) {
                    GetProductListOutputDTO getProductListOutputDTO = new HangThucPhamDTO(
                            hangThucPham.getMaHang(),
                            hangThucPham.getTenHang(),
                            hangThucPham.getSoLuongTon(),
                            hangThucPham.getDonGia(),
                            hangThucPham.tinhVat(),
                            hangThucPham.getTenLoai(),
                            ((HangThucPham) hangThucPham).getNgaySanXuat(),
                            ((HangThucPham) hangThucPham).getNgayHetHan(),
                            ((HangThucPham) hangThucPham).getNhaCungCap()
                    );
                    listOutputDTOS.add(getProductListOutputDTO);
                }
                GetProductListResponseData getProductListResponseData = new GetProductListResponseData(listOutputDTOS);
                ((GetProductListPresenter) outputBoundary).exportResult(getProductListResponseData);
            }
        } else if ("Hàng Sành Sứ".equals(type)) {
            for (HangHoa hangSanhSu : listHangHoa) {
                if (hangSanhSu instanceof HangSanhSu) {

                    GetProductListOutputDTO getProductListOutputDTO = new HangSanhSuDTO(
                            hangSanhSu.getMaHang(),
                            hangSanhSu.getTenHang(),
                            hangSanhSu.getSoLuongTon(),
                            hangSanhSu.getDonGia(),
                            hangSanhSu.tinhVat(),
                            hangSanhSu.getTenLoai(),
                            ((HangSanhSu) hangSanhSu).getNhaSanXuat(),
                            ((HangSanhSu) hangSanhSu).getNgayNhapKho()
                    );
                    listOutputDTOS.add(getProductListOutputDTO);
                }
                GetProductListResponseData getProductListResponseData = new GetProductListResponseData(listOutputDTOS);
                ((GetProductListPresenter) outputBoundary).exportResult(getProductListResponseData);
            }
        } else {
            for (HangHoa hangDienMay : listHangHoa) {
                if (hangDienMay instanceof HangDienMay) {

                    GetProductListOutputDTO getProductListOutputDTO = new HangDienMayDTO(
                            hangDienMay.getMaHang(),
                            hangDienMay.getTenHang(),
                            hangDienMay.getSoLuongTon(),
                            hangDienMay.getDonGia(),
                            hangDienMay.tinhVat(),
                            hangDienMay.getTenLoai(),
                            ((HangDienMay) hangDienMay).getThoiGianBaoHanh(),
                            ((HangDienMay) hangDienMay).getCongSuat()
                    );
                    listOutputDTOS.add(getProductListOutputDTO);
                }
            }
            GetProductListResponseData getProductListResponseData = new GetProductListResponseData(listOutputDTOS);
            ((GetProductListPresenter) outputBoundary).exportResult(getProductListResponseData);
        }
    }
}
