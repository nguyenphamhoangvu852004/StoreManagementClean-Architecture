package com.example.ui.getTypeListMVVP;

import java.util.List;

public interface GetTypeListObserver {
    void updateGetTypeList(List<GetTypeListViewModel> data);
}
