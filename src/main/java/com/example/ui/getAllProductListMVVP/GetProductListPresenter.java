package com.example.ui.getAllProductListMVVP;

import com.example.dtos.getProductListDTOs.GetProductListOutputDTOs.GetProductListOutputDTO;
import com.example.dtos.getProductListDTOs.GetProductListOutputDTOs.HangDienMayDTO;
import com.example.dtos.getProductListDTOs.GetProductListOutputDTOs.HangSanhSuDTO;
import com.example.dtos.getProductListDTOs.GetProductListOutputDTOs.HangThucPhamDTO;
import com.example.dtos.getProductListDTOs.GetProductListResponseData;
import com.example.interfaces.OutputBoundary;
import com.example.interfaces.ResponseData;
import com.example.ui.getAllProductListMVVP.GetProductListViewModel.DienMayViewModel;
import com.example.ui.getAllProductListMVVP.GetProductListViewModel.GetProductlistViewModel;
import com.example.ui.getAllProductListMVVP.GetProductListViewModel.SanhSuViewModel;
import com.example.ui.getAllProductListMVVP.GetProductListViewModel.ThucPhamViewModel;

import java.text.DecimalFormat;
import java.util.List;

public class GetProductListPresenter extends GetProductListSubject implements OutputBoundary {
    private List<GetProductListOutputDTO> listOutDTO = null;
    private List<GetProductlistViewModel> listProductViewModel = null;

    public GetProductListPresenter(List<GetProductlistViewModel> list) {
        this.listProductViewModel = list;
    }

    @Override
    public void exportResult(ResponseData responseData) {
        listProductViewModel.clear();
        // Lấy danh sách DTO từ response
        this.listOutDTO = ((GetProductListResponseData) responseData).getList();

        // Định dạng số
        DecimalFormat decimalFormat = new DecimalFormat("#,###");

        // Duyệt qua từng sản phẩm trong danh sách DTO
        for (GetProductListOutputDTO product : listOutDTO) {
            String formattedDonGia = decimalFormat.format(product.getDonGia());
            String formattedVat = decimalFormat.format(product.getVAT());

            GetProductlistViewModel viewModel = null;

            // Xử lý dựa trên loại sản phẩm
            if (product instanceof HangThucPhamDTO) {
                HangThucPhamDTO thucPham = (HangThucPhamDTO) product;
                viewModel = new ThucPhamViewModel(
                        thucPham.getMaHang(),
                        thucPham.getTenHang(),
                        String.valueOf(thucPham.getSoLuongTon()),
                        formattedDonGia,
                        formattedVat,
                        thucPham.getTenLoai(),
                        thucPham.getNgaySanXuat().toString(),
                        thucPham.getNgayHetHan().toString(),
                        thucPham.getNhaCungCap()
                );
            } else if (product instanceof HangSanhSuDTO) {
                HangSanhSuDTO sanhSu = (HangSanhSuDTO) product;
                viewModel = new SanhSuViewModel(
                        sanhSu.getMaHang(),
                        sanhSu.getTenHang(),
                        String.valueOf(sanhSu.getSoLuongTon()),
                        formattedDonGia,
                        formattedVat,
                        sanhSu.getTenLoai(),
                        sanhSu.getNhaSanXuat(),
                        sanhSu.getNgayNhapKho().toString()
                );
            } else if (product instanceof HangDienMayDTO) {
                HangDienMayDTO dienMay = (HangDienMayDTO) product;
                viewModel = new DienMayViewModel(
                        dienMay.getMaHang(),
                        dienMay.getTenHang(),
                        String.valueOf(dienMay.getSoLuongTon()),
                        formattedDonGia,
                        formattedVat,
                        dienMay.getTenLoai(),
                        String.valueOf(dienMay.getThoiGianBaoHanh()),
                        String.valueOf(dienMay.getCongSuat())
                );
            } else {
                viewModel = new GetProductlistViewModel(product.getMaHang(), product.getTenHang(),
                        String.valueOf(product.getSoLuongTon()),
                        formattedDonGia,
                        String.valueOf(product.getVAT()),
                        product.getTenLoai());
            }


            // Thêm ViewModel vào danh sách
            if (viewModel != null) {
                this.listProductViewModel.add(viewModel);
            }
        }


        // Thông báo cho các observer
        notifyObservers(listProductViewModel);
    }
}
