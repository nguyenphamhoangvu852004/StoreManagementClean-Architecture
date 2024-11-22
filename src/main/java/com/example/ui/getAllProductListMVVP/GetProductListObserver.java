package com.example.ui.getAllProductListMVVP;

import com.example.ui.getAllProductListMVVP.GetProductListViewModel.GetProductlistViewModel;

import java.util.List;

public interface GetProductListObserver{
    void updateGetProductList(List<GetProductlistViewModel> data);
}
