package com.example.ui.getTypeListMVVP;

import java.util.ArrayList;
import java.util.List;

public abstract class GetTypeListSubject {
    private final List<GetTypeListObserver> observers = new ArrayList<>();

    public void addObserver(GetTypeListObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(GetTypeListObserver observer) {
        observers.remove(observer);
    }

    protected void notifyObservers(List<GetTypeListViewModel> data) {
        for (GetTypeListObserver observer : observers) {
            observer.updateGetTypeList(data);
        }
    }
}

