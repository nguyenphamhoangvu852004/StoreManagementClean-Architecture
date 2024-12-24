package com.example.addProduct;

import java.sql.SQLException;

public class AddProductPresenter implements AddProductOutputBoundary {

    private AddProductViewModel addProductViewModel;
    private AddProductOutputDTO response;

    public AddProductPresenter(AddProductViewModel viewModel){
        this.addProductViewModel = viewModel;
    }

    public AddProductPresenter(){
    }

    @Override
    public void presenter(AddProductOutputDTO addProductOutputDTO) throws SQLException {
        addProductViewModel.setMessage(addProductOutputDTO.getMessage());
    }

    @Override
    public void exportResult(AddProductOutputDTO addProductOutputDTO) {
        this.response = addProductOutputDTO;
    }

    public AddProductOutputDTO getResponse() {
        return response;
    }
}
