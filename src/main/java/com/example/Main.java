package com.example;



import com.example.addProduct.*;

import com.example.database.FindProductDAO;

import com.example.database.MysqlGetFoodList;
import com.example.database.MysqlGetProductList;
import com.example.database.MysqlGetTypeProductList;
import com.example.dtos.FindProductByIdDTOs.FindProductDTO;
import com.example.dtos.getProductListDTOs.GetProductListInputDTO;
import com.example.interfaces.DatabaseBoundary;
import com.example.interfaces.InputBoundary;
import com.example.removeProduct.database.RemoveProductDAO;
import com.example.removeProduct.usecase.RemoveProductUseCase;
import com.example.removeProduct.view.RemoveProductPresenter;
import com.example.removeProduct.view.RemoveProductView;
import com.example.removeProduct.view.RemoveProductViewModel;
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

        // Add UseCase
        AddProductDAO addProductDAO = new AddProductDAO();
        AddProductViewModel addViewModel = new AddProductViewModel();
        AddProductPresenter addProductPresenter = new AddProductPresenter(addViewModel);
        AddProductUseCase addProductUseCase = new AddProductUseCase(addProductPresenter, addProductDAO);

        // Remove UseCase
        RemoveProductDAO removeProductDAO = new RemoveProductDAO();
        RemoveProductViewModel removeViewModel = new RemoveProductViewModel();
        RemoveProductPresenter removeProductPresenter = new RemoveProductPresenter(removeViewModel);
        RemoveProductUseCase removeProductUseCase = new RemoveProductUseCase(removeProductDAO, removeProductPresenter);

        DatabaseBoundary findProductDatabase = new FindProductDAO();
        OutputBoundary findProductPresenter = new FindProductPresenter();
        InputBoundary findProductUseCase = new FindProductByIDUseCase(findProductDatabase, findProductPresenter);


        MainController controller = new MainController(
                getProductListUseCase,
                getProductList7DaysExpiry,
                getTypeListUseCase,
                addProductUseCase,
                removeProductUseCase,
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

        // Delete
        mainView.getDeleteButton().addActionListener(e -> {
            String maHang = mainView.getMaHang();
            RemoveProductView view = new RemoveProductView(maHang, controller, removeViewModel);
            view.setVisible(true);
        });

        mainView.getAddButton().addActionListener(e -> {
            AddProductView addProductView = new AddProductView(controller, addViewModel);

            getTypelistPresenter.addObserver(addProductView);
            addProductView.setVisible(true);
            try {
                getTypeListUseCase.execute(null);
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
