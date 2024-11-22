package com.example.ui.getAllProductListMVVP;

import java.util.List;

public interface GetProductListObserver{
    void updateGetProductList(List<GetProductlistViewModel> data);
}
