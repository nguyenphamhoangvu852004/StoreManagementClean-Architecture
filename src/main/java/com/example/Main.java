package com.example;


import com.example.database.MysqlGetFoodList;
import com.example.database.MysqlGetProductInfoDetail.MysqlGetProductInfoDetail;
import com.example.database.MysqlGetProductList;

import com.example.database.MysqlGetTypeProductList;
import com.example.database.QuantityProduct.QuantityProduct;
import com.example.database.UpdateProduct.UpdateProductSqlDAO;
import com.example.dtos.productInfoDetail.Request.DienMayDetailInfoRequestDTO;
import com.example.dtos.productInfoDetail.Request.SanhSuDetailInfoRequestDTO;
import com.example.dtos.productInfoDetail.Request.ThucPhamDetailInfoRequestDTO;
import com.example.dtos.totalQuantityDTOs.TotalQuantityDienMayDTO;
import com.example.dtos.totalQuantityDTOs.TotalQuantitySanhSuDTO;
import com.example.dtos.totalQuantityDTOs.TotalQuantityThucPhamDTO;
=======
import com.example.database.MysqlGetTypeList;
import com.example.dtos.FindProductByIdDTOs.FindProductDTO;

import com.example.interfaces.DatabaseBoundary;
import com.example.interfaces.InputBoundary;
import com.example.interfaces.RequestData;
import com.example.ui.GetProductDetailInfoMVVP.GetProductDetailInfoPresenter;
import com.example.ui.MainController;
import com.example.ui.MainView;
import com.example.ui.TotalQuantityView.TotalQuantityPresenter;
import com.example.ui.TotalQuantityView.TotalQuantityView;
import com.example.ui.getAllProductListMVVP.GetProductListPresenter;
import com.example.ui.getAllProductListMVVP.GetProductlistViewModel;
import com.example.ui.getAllProductListSevenDaysExpiryMVVP.GetProductListSevenDaysExpiryPresenter;
import com.example.ui.getAllProductListSevenDaysExpiryMVVP.GetProductListSevenDaysExpiryView;
import com.example.ui.getAllProductListSevenDaysExpiryMVVP.GetProductListSevenDaysExpiryViewModel;
import com.example.ui.getTypeListMVVP.GetTypeListPresenter;
import com.example.ui.getTypeListMVVP.GetTypeListViewModel;
import com.example.ui.updateProductMVVP.UpdateProductView;
import com.example.ui.updateProductMVVP.UpdateProductPresenter;
import com.example.usecase.getListProductExpired.GetProductListSevenDayExpiryUseCase;
import com.example.usecase.getProductDetailInfo.GetProductDetailInfoUseCase;
import com.example.usecase.getProductList.GetProductListUseCase;
import com.example.usecase.getTypeList.GetTypeListUseCase;
import com.example.usecase.totalQuantityUseCase.TotalQuantityUseCase;
import com.example.usecase.updateProduct.UpdateProductUseCase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        List<GetProductlistViewModel> listGetProductListViewModel = new ArrayList<>();
        DatabaseBoundary getAllProductListDatabase = new MysqlGetProductList();
        GetProductListPresenter getProductListPresenter = new GetProductListPresenter(listGetProductListViewModel);
        InputBoundary getProductListUseCase = new GetProductListUseCase(getProductListPresenter, getAllProductListDatabase);

        List<GetTypeListViewModel> listGetTypeViewModel = new ArrayList<>();
        DatabaseBoundary getTypeListDatabase = new MysqlGetTypeList();
        GetTypeListPresenter getTypelistPresenter = new GetTypeListPresenter(listGetTypeViewModel);
        GetTypeListUseCase getTypeListUseCase = new GetTypeListUseCase(getTypelistPresenter, getTypeListDatabase);

        List<GetProductListSevenDaysExpiryViewModel> listSevenDaysExpiryViewModels = new ArrayList<>();
        DatabaseBoundary getFoodList = new MysqlGetFoodList();
        GetProductListSevenDaysExpiryPresenter getProductListSevenDaysExpiryPresenter = new GetProductListSevenDaysExpiryPresenter(listSevenDaysExpiryViewModels);
        InputBoundary getProductList7DaysExpiry = new GetProductListSevenDayExpiryUseCase(getProductListSevenDaysExpiryPresenter, getFoodList);

        UpdateProductView updateProductView = new UpdateProductView();
        DatabaseBoundary updateProductDB = new UpdateProductSqlDAO();
        UpdateProductPresenter updateProductPresenter = new UpdateProductPresenter(updateProductView);
        InputBoundary updateProduct = new UpdateProductUseCase(updateProductDB, updateProductPresenter);

        TotalQuantityView totalQuantityView = new TotalQuantityView();
        DatabaseBoundary totalQuantityDB = new QuantityProduct();
        TotalQuantityPresenter totalQuantityPresenter = new TotalQuantityPresenter(totalQuantityView);
        InputBoundary totalQuantity = new TotalQuantityUseCase(totalQuantityDB, totalQuantityPresenter);

        DatabaseBoundary getProductInfoDetailDB = new MysqlGetProductInfoDetail();
        GetProductDetailInfoPresenter getProductInfoDetailPresenter = new GetProductDetailInfoPresenter(updateProductView);
        InputBoundary getProductInfoDetailUseCase = new GetProductDetailInfoUseCase(getProductInfoDetailDB, getProductInfoDetailPresenter);


        MainController controller = new MainController(
                getProductListUseCase,
                getProductList7DaysExpiry,
                getTypeListUseCase,
                updateProduct,
                totalQuantity,
                getProductInfoDetailUseCase
        );

        MainView mainView = new MainView(controller);


        getProductListPresenter.addObserver(mainView);

        getTypelistPresenter.addObserver(mainView);
        controller.executeGetTypeList();
        getTypelistPresenter.removeObserver(mainView);

        mainView.getQuantityButton().addActionListener(e -> {
            RequestData requestDM = new TotalQuantityDienMayDTO();
            RequestData requestSS = new TotalQuantitySanhSuDTO();
            RequestData requestTP = new TotalQuantityThucPhamDTO();

            try {
                totalQuantityView.getQuantityView();
                controller.executeGetTotalQuantity(requestDM);
                controller.executeGetTotalQuantity(requestSS);
                controller.executeGetTotalQuantity(requestTP);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        mainView.getEditButton().addActionListener(e -> {
            int row = mainView.getTable().getSelectedRow();

            if (row != -1) {
                String maHang = mainView.getTable().getValueAt(row, 0).toString();
                String type = "";



                if (maHang.contains("HDM")) {
                    type = "HangDienMay";
                    String selectedType = mainView.getSelectedType();
                    updateProductView.getUpdateProductView(type, controller,selectedType);
                    RequestData requestData = new DienMayDetailInfoRequestDTO(maHang);

            getTypelistPresenter.addObserver(addProductView);

            try {
                controller.executeGetTypeList();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            addProductView.setVisible(true);




                    try {
                        controller.executeGetProductDetailInfo(requestData);


                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                } else if (maHang.contains("HTP")) {
                    type = "HangThucPham";


                    String selectedType = mainView.getSelectedType();
                    updateProductView.getUpdateProductView(type, controller, selectedType);

                    RequestData requestData = new ThucPhamDetailInfoRequestDTO(maHang);
                    try {
                        controller.executeGetProductDetailInfo(requestData);


                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                } else if (maHang.contains("HSS")) {
                    type = "HangSanhSu";

                    String selectedType = mainView.getSelectedType();
                    updateProductView.getUpdateProductView(type, controller, selectedType);

                    RequestData requestData = new SanhSuDetailInfoRequestDTO(maHang);
                    try {
                        controller.executeGetProductDetailInfo(requestData);

                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }


//                controller.executeGetProductList();
            }
        });

        mainView.getExpiryButton().addActionListener(e -> {
            GetProductListSevenDaysExpiryView getProductListSevenDaysExpiryView = new GetProductListSevenDaysExpiryView();
            getProductListSevenDaysExpiryPresenter.addObserver(getProductListSevenDaysExpiryView);
            try {
                controller.executeGetProductListSevenDayExpiry("Hàng Thực Phẩm");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        controller.executeGetTypeList();

    }

}
