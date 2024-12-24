package com.example.ui.getAllProductListMVVP;

import java.util.List;

import com.example.ui.getAllProductListMVVP.GetProductListViewModel.GetProductlistViewModel;

public interface GetProductListObserver{
    void updateGetProductList(List<GetProductlistViewModel> data, int totalItems);
}
