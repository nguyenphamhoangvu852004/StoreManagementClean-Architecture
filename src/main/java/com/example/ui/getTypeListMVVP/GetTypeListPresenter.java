package com.example.ui.getTypeListMVVP;

import com.example.dtos.getTypeListDTOs.GetTypeListResponseData;
import com.example.dtos.getTypeListDTOs.GetTypeOutputDTO;
import com.example.interfaces.OutputBoundary;
import com.example.interfaces.ResponseData;

import java.util.List;

public class GetTypeListPresenter extends GetTypeListSubject implements OutputBoundary {

    private List<GetTypeOutputDTO> listGetTypeOutputDTO = null;
    private List<GetTypeListViewModel> listGetTypeViewModel = null;

    public GetTypeListPresenter(List<GetTypeListViewModel> listGetTypeViewModel) {
        this.listGetTypeViewModel = listGetTypeViewModel;
    }

    @Override
    public void exportResult(ResponseData responseData) {
        if (responseData instanceof GetTypeListResponseData) {
            this.listGetTypeOutputDTO = ((GetTypeListResponseData) responseData).getTypeList();
            this.listGetTypeViewModel.clear();
            for (GetTypeOutputDTO getTypeOutputDTO : listGetTypeOutputDTO) {
                GetTypeListViewModel getTypeListViewModel = new GetTypeListViewModel(getTypeOutputDTO.getType());
                this.listGetTypeViewModel.add(getTypeListViewModel);
            }

            notifyObservers(listGetTypeViewModel);
        }
    }

}
