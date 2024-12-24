package com.example.ui;

import java.sql.SQLException;

import com.example.addProduct.AddProductDTO;
import com.example.addProduct.AddProductUseCase;
import com.example.dtos.FindProductByIdDTOs.FindProductDTO;
import com.example.dtos.getProductListDTOs.GetProductListInputDTO;
import com.example.dtos.getProductListSevenDayExpiryDTOs.GetProductListSevenDayExpiryInputDTO;
import com.example.interfaces.InputBoundary;
import com.example.interfaces.RequestData;
import com.example.removeProduct.dto.RemoveProductDTO;
import com.example.removeProduct.usecase.RemoveProductUseCase;

public class MainController {

    private InputBoundary getProductListUseCase;
    private InputBoundary getProductListSevenDayExpiryUseCase;
    private InputBoundary getTypeListUseCase;
    private InputBoundary findProductByIDUseCase;
    private AddProductUseCase addProductUseCase;
    private RemoveProductUseCase removeProductUseCase;

    private InputBoundary updateProductUseCase;

    private InputBoundary getTotalQuantityUseCase;

    private InputBoundary getProductDetailInfoUseCase;

    public MainController(InputBoundary getProductListUseCase, InputBoundary getProductListSevenDayExpiryUseCase,
            InputBoundary getTypeListUseCase, AddProductUseCase addProductUseCase,
            RemoveProductUseCase removeProductUseCase, InputBoundary findProductByIDUseCase,
            InputBoundary updateProductUseCase, InputBoundary getTotalQuantityUseCase,
            InputBoundary getProductDetailInfoUseCase) {
        this.getProductListUseCase = getProductListUseCase;
        this.getProductListSevenDayExpiryUseCase = getProductListSevenDayExpiryUseCase;
        this.getTypeListUseCase = getTypeListUseCase;
        this.addProductUseCase = addProductUseCase;
        this.removeProductUseCase = removeProductUseCase;
        this.findProductByIDUseCase = findProductByIDUseCase;
        this.updateProductUseCase = updateProductUseCase;
        this.getTotalQuantityUseCase = getTotalQuantityUseCase;
        this.getProductDetailInfoUseCase = getProductDetailInfoUseCase;
    }

    public void executeGetProductList(String s, int currentPage, int pageSize) throws SQLException {
        GetProductListInputDTO getProductListInputDTO = new GetProductListInputDTO(s, currentPage, pageSize);
        getProductListUseCase.execute(getProductListInputDTO);
    }

    public void executeGetProductListSevenDayExpiry(String s) throws SQLException {
        getProductListSevenDayExpiryUseCase.execute(new GetProductListSevenDayExpiryInputDTO(s));
    }

    public void executeGetTypeList() throws SQLException {
        getTypeListUseCase.execute(null);
    }

    public void executeAddProduct(AddProductDTO a) throws SQLException {
        addProductUseCase.execute(a);
    }

    public void executeRemoveProduct(RemoveProductDTO a) throws SQLException {
        removeProductUseCase.execute(a);
    }

    public void executeFindProductByID(String s) throws SQLException {
        findProductByIDUseCase.execute(new FindProductDTO(s));

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
