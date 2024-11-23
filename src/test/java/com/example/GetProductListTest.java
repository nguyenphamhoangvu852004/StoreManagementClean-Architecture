package com.example;

import com.example.database.MysqlGetProductList;
import com.example.dtos.getProductListDTOs.GetProductListInputDTO;
import com.example.dtos.getProductListDTOs.GetProductListOutputDTOs.GetProductListOutputDTO;
import com.example.interfaces.DatabaseBoundary;
import com.example.interfaces.InputBoundary;
import com.example.ui.getAllProductListMVVP.GetProductListPresenter;
import com.example.ui.getAllProductListMVVP.GetProductListViewModel.GetProductlistViewModel;
import com.example.usecase.getProductList.GetProductListUseCase;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GetProductListTest {

    @Test
    public void testGetAllProductListOK() throws SQLException {

        List<GetProductlistViewModel> list = new ArrayList<>();
        GetProductListPresenter p = new GetProductListPresenter(list);
        DatabaseBoundary databaseBoundary = new MysqlGetProductList();
        InputBoundary i = new GetProductListUseCase(p, databaseBoundary);
        i.execute(new GetProductListInputDTO("Tất Cả"));
        List<GetProductlistViewModel> list2 = p.getListProductViewModel();
        assertEquals(34, list2.size());
    }
}
