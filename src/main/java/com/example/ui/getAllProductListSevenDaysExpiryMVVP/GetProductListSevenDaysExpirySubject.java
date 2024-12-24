package com.example.ui.getAllProductListSevenDaysExpiryMVVP;

import java.util.ArrayList;
import java.util.List;

public abstract class GetProductListSevenDaysExpirySubject {
    private final List<GetProductListSevenDaysExpiryObserver> getProductListSevenDaysExpiryObservers = new ArrayList<>();

    public void addObserver(GetProductListSevenDaysExpiryObserver getProductListSevenDaysExpiryObserver) {
        getProductListSevenDaysExpiryObservers.add(getProductListSevenDaysExpiryObserver);
    }

    public void removeObserver(GetProductListSevenDaysExpiryObserver getProductListSevenDaysExpiryObserver) {
        getProductListSevenDaysExpiryObservers.remove(getProductListSevenDaysExpiryObserver);
    }

    protected void notifyObservers(List<GetProductListSevenDaysExpiryViewModel> data) {
        for (GetProductListSevenDaysExpiryObserver getProductListSevenDaysExpiryObserver : getProductListSevenDaysExpiryObservers) {
            getProductListSevenDaysExpiryObserver.updateGetProductListSevenDaysExpiry(data);
        }
    }
}

