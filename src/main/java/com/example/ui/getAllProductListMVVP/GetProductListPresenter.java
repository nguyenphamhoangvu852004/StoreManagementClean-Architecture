package com.example.ui.getAllProductListMVVP;

import com.example.dtos.getProductListDTOs.GetProductListOutputDTO;
import com.example.dtos.getProductListDTOs.GetProductListResponseData;
import com.example.interfaces.OutputBoundary;
import com.example.interfaces.ResponseData;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class GetProductListPresenter extends GetProductListSubject implements OutputBoundary {
    private List<GetProductListOutputDTO> listOutDTO = null;
    private List<GetProductlistViewModel> listProductViewModel = null;

    public GetProductListPresenter(List<GetProductlistViewModel> list){
        this.listProductViewModel = list;
    }
    @Override
    public void exportResult(ResponseData responseData) {

        if (responseData instanceof GetProductListResponseData) {
            this.listOutDTO = ((GetProductListResponseData) responseData).getList();
            this.listProductViewModel = new ArrayList<>();

            DecimalFormat decimalFormat = new DecimalFormat("#,###");
            for (GetProductListOutputDTO product : listOutDTO) {
                String formattedDonGia = decimalFormat.format(product.getDonGia());
                String formattedVat = decimalFormat.format(product.getVAT());

                GetProductlistViewModel viewModel = new GetProductlistViewModel(
                        product.getMaHang(),
                        product.getTenHang(),
                        String.valueOf(product.getSoLuongTon()),
                        formattedDonGia,
                        formattedVat
                );
                this.listProductViewModel.add(viewModel);
            }

            notifyObservers(listProductViewModel);
        }

    }
}



