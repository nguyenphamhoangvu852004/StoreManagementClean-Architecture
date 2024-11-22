package com.example.ui;

import com.example.dtos.FindProductByIdDTOs.FindProductDTO;
import com.example.dtos.getProductListDTOs.GetProductListInputDTO;
import com.example.dtos.getProductListSevenDayExpiryDTOs.GetProductListSevenDayExpiryInputDTO;
import com.example.interfaces.InputBoundary;
import com.example.interfaces.RequestData;

import java.sql.SQLException;

public class MainController {

    private InputBoundary getProductListUseCase;
    private InputBoundary getProductListSevenDayExpiryUseCase;
    private InputBoundary getTypeListUseCase;
    private InputBoundary findProductByIDUseCase;
    public MainController(InputBoundary getProductListUseCase, InputBoundary getProductListSevenDayExpiryUseCase, InputBoundary getTypeListUseCase,InputBoundary findProductByIDUseCase) {
        this.getProductListUseCase = getProductListUseCase;
        this.getProductListSevenDayExpiryUseCase = getProductListSevenDayExpiryUseCase;
        this.getTypeListUseCase = getTypeListUseCase;
        this.findProductByIDUseCase = findProductByIDUseCase;
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

    public void executeFindProductByID(String s) throws SQLException {
        findProductByIDUseCase.execute(new FindProductDTO(s));
    }

}


