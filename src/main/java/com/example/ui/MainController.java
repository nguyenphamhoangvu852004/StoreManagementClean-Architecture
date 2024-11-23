package com.example.ui;

import com.example.dtos.getProductListDTOs.GetProductListInputDTO;
import com.example.dtos.getProductListSevenDayExpiryDTOs.GetProductListSevenDayExpiryInputDTO;
import com.example.interfaces.InputBoundary;
import com.example.interfaces.RequestData;

import java.sql.SQLException;

public class MainController {

    private InputBoundary getProductListUseCase;
    private InputBoundary getProductListSevenDayExpiryUseCase;
    private InputBoundary getTypeListUseCase;

    private InputBoundary updateProductUseCase;

    private InputBoundary getTotalQuantityUseCase;

    private InputBoundary getProductDetailInfoUseCase;


    public MainController(InputBoundary getProductListUseCase, InputBoundary getProductListSevenDayExpiryUseCase, InputBoundary getTypeListUseCase, InputBoundary updateProductUseCase, InputBoundary getTotalQuantityUseCase, InputBoundary getProductDetailInfoUseCase) {
        this.getProductListUseCase = getProductListUseCase;
        this.getProductListSevenDayExpiryUseCase = getProductListSevenDayExpiryUseCase;
        this.getTypeListUseCase = getTypeListUseCase;
        this.updateProductUseCase = updateProductUseCase;
        this.getTotalQuantityUseCase = getTotalQuantityUseCase;
        this.getProductDetailInfoUseCase = getProductDetailInfoUseCase;
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

    public void executeUpdateProduct(RequestData requestData) throws SQLException {
        updateProductUseCase.execute(requestData);
    }

    public void executeGetTotalQuantity(RequestData requestData) throws SQLException {
        getTotalQuantityUseCase.execute(requestData);
    }

    public void executeGetProductDetailInfo(RequestData requestData) throws SQLException {
        getProductDetailInfoUseCase.execute(requestData);
    }
}


