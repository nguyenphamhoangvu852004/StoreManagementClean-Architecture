package com.example.removeProduct.view;


import com.example.removeProduct.dto.RemoveProductOutputDTO;

public class RemoveProductPresenter implements RemoveProductOutputBoundary {

    private final RemoveProductViewModel viewModel;
    private RemoveProductOutputDTO response;

    public RemoveProductPresenter(RemoveProductViewModel viewModel ) {
        this.viewModel = viewModel;
    }

    @Override
    public void presenter(RemoveProductOutputDTO outputDTO) {
        this.viewModel.setMessage(outputDTO.getMessage());
    }

    @Override
    public void exportResult(RemoveProductOutputDTO productOutputDTO) {
        this.response = productOutputDTO;
    }

    public RemoveProductOutputDTO getResponse() {
        return response;
    }
}
