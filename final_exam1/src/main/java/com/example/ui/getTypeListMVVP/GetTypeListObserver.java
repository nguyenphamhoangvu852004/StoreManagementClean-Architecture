package com.example.ui.getTypeListMVVP;

import com.example.ui.getAllProductListMVVP.GetProductlistViewModel;

import java.util.List;

public interface GetTypeListObserver {
    void updateGetTypeList(List<GetTypeListViewModel> data);
}
