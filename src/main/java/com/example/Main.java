package com.example;


import com.example.database.FindProductDAO;
import com.example.database.MysqlGetFoodList;
import com.example.database.MysqlGetProductList;
import com.example.database.MysqlGetTypeProductList;
import com.example.dtos.FindProductByIdDTOs.FindProductDTO;
import com.example.interfaces.DatabaseBoundary;
import com.example.interfaces.InputBoundary;
import com.example.interfaces.OutputBoundary;
import com.example.interfaces.RequestData;
import com.example.ui.MainController;
import com.example.ui.MainView;
import com.example.ui.findProductMVVP.FindProductPresenter;
import com.example.ui.findProductMVVP.FindProductViewModel;
import com.example.ui.getAllProductListMVVP.GetProductListPresenter;
import com.example.ui.getAllProductListMVVP.GetProductlistViewModel;
import com.example.ui.getAllProductListSevenDaysExpiryMVVP.GetProductListSevenDaysExpiryPresenter;
import com.example.ui.getAllProductListSevenDaysExpiryMVVP.GetProductListSevenDaysExpiryView;
import com.example.ui.getAllProductListSevenDaysExpiryMVVP.GetProductListSevenDaysExpiryViewModel;
import com.example.ui.getTypeListMVVP.GetTypeListPresenter;
import com.example.ui.getTypeListMVVP.GetTypeListViewModel;
import com.example.usecase.FindProduct.FindProductByIDUseCase;
import com.example.usecase.getListProductExpired.GetProductListSevenDayExpiryUseCase;
import com.example.usecase.getProductList.GetProductListUseCase;
import com.example.usecase.getTypeList.GetTypeListUseCase;

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
        DatabaseBoundary getTypeListDatabase = new MysqlGetTypeProductList();
        GetTypeListPresenter getTypelistPresenter = new GetTypeListPresenter(listGetTypeViewModel);
        GetTypeListUseCase getTypeListUseCase = new GetTypeListUseCase(getTypelistPresenter, getTypeListDatabase);

        List<GetProductListSevenDaysExpiryViewModel> listSevenDaysExpiryViewModels = new ArrayList<>();
        DatabaseBoundary getFoodList = new MysqlGetFoodList();
        GetProductListSevenDaysExpiryPresenter getProductListSevenDaysExpiryPresenter = new GetProductListSevenDaysExpiryPresenter(listSevenDaysExpiryViewModels);
        InputBoundary getProductList7DaysExpiry = new GetProductListSevenDayExpiryUseCase(getProductListSevenDaysExpiryPresenter, getFoodList);


        DatabaseBoundary findProductDatabase = new FindProductDAO();
        OutputBoundary findProductPresenter = new FindProductPresenter();
        InputBoundary findProductUseCase = new FindProductByIDUseCase(findProductDatabase, findProductPresenter);


        MainController controller = new MainController(
                getProductListUseCase,
                getProductList7DaysExpiry,
                getTypeListUseCase,
                findProductUseCase
        );
        MainView mainView = new MainView(controller);


        getProductListPresenter.addObserver(mainView);
        getTypelistPresenter.addObserver(mainView);

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

        mainView.getFindProductButton().addActionListener(e -> {
            String intput = mainView.getFindProductTextField().getText().toString().trim();
            try {
                controller.executeFindProductByID(intput);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

}
