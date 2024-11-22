package com.example.ui;

import com.example.addProduct.AddProductDTO;
import com.example.addProduct.AddProductUseCase;
import com.example.dtos.getProductListDTOs.GetProductListInputDTO;
import com.example.dtos.getProductListSevenDayExpiryDTOs.GetProductListSevenDayExpiryInputDTO;
import com.example.interfaces.InputBoundary;
import com.example.removeProduct.dto.RemoveProductDTO;
import com.example.removeProduct.usecase.RemoveProductUseCase;

import java.sql.SQLException;

public class MainController {

    private InputBoundary getProductListUseCase;
    private InputBoundary getProductListSevenDayExpiryUseCase;
    private InputBoundary getTypeListUseCase;
    private AddProductUseCase addProductUseCase;
    private RemoveProductUseCase removeProductUseCase;

    public MainController(InputBoundary getProductListUseCase, InputBoundary getProductListSevenDayExpiryUseCase, InputBoundary getTypeListUseCase, AddProductUseCase addProductUseCase, RemoveProductUseCase removeProductUseCase) {
        this.getProductListUseCase = getProductListUseCase;
        this.getProductListSevenDayExpiryUseCase = getProductListSevenDayExpiryUseCase;
        this.getTypeListUseCase = getTypeListUseCase;
        this.addProductUseCase = addProductUseCase;
        this.removeProductUseCase = removeProductUseCase;
    }

    public void executeGetProductList(String s) throws SQLException {
        getProductListUseCase.execute(new GetProductListInputDTO(s));
    }

    public void executeGetProductListSevenDayExpiry(String s) throws SQLException {
        getProductListSevenDayExpiryUseCase.execute(new GetProductListSevenDayExpiryInputDTO(s));
    }

    public void executeGetTypeList() throws SQLException {
        getTypeListUseCase.execute(null);
    }

    public void executeAddProduct(AddProductDTO a) {
        addProductUseCase.execute(a);
    }

    public void executeRemoveProduct(RemoveProductDTO a){
        removeProductUseCase.execute(a);
    }

}


