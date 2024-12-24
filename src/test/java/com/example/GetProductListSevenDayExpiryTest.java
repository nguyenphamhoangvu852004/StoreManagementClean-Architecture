package com.example;

import com.example.database.MysqlGetFoodList;
import com.example.database.MysqlGetProductList;
import com.example.dtos.getProductListSevenDayExpiryDTOs.GetProductListSevenDayExpiryInputDTO;
import com.example.ui.getAllProductListSevenDaysExpiryMVVP.GetProductListSevenDaysExpiryPresenter;
import com.example.ui.getAllProductListSevenDaysExpiryMVVP.GetProductListSevenDaysExpiryViewModel;
import com.example.interfaces.DatabaseBoundary;
import com.example.interfaces.InputBoundary;
import com.example.usecase.getListProductExpired.GetProductListSevenDayExpiryUseCase;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GetProductListSevenDayExpiryTest {
    @Test
    public void getProductListSevenDayExpiryOK() throws SQLException {
        List<GetProductListSevenDaysExpiryViewModel> list = new ArrayList<>();
        DatabaseBoundary mdao = new MysqlGetFoodList();
        GetProductListSevenDaysExpiryPresenter p = new GetProductListSevenDaysExpiryPresenter(list);
        InputBoundary i = new GetProductListSevenDayExpiryUseCase(p, mdao);
        i.execute(new GetProductListSevenDayExpiryInputDTO("Hàng Thực Phẩm"));

        assertEquals(3, p.getListSevenDaysExpiryViewModels().size()); // Kiểm tra số lượng sản phẩm là 10
    }
}
