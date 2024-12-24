package com.example.ui.getAllProductListMVVP;

import java.util.ArrayList;
import java.util.List;

import com.example.ui.getAllProductListMVVP.GetProductListViewModel.GetProductlistViewModel;

public abstract class GetProductListSubject {
    private final List<GetProductListObserver> observers = new ArrayList<>();

    public void addObserver(GetProductListObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(GetProductListObserver observer) {
        observers.remove(observer);
    }

    protected void notifyObservers(List<GetProductlistViewModel> data, int totalItems) {
        for (GetProductListObserver observer : observers) {
            observer.updateGetProductList(data, totalItems);
        }
    }
}
