package com.example.database.QuantityProduct;

import com.example.database.MysqlConnection;
import com.example.entity.HangDienMay;
import com.example.entity.HangHoa;
import com.example.entity.HangSanhSu;
import com.example.entity.HangThucPham;
import com.example.interfaces.DatabaseBoundary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuantityProduct implements DatabaseBoundary {
    private Connection connection = null;


    public int getTotalQuantityByType(String type) throws SQLException {
        String query = "SELECT SUM(hh.soLuongTon) " +
                "FROM hanghoa hh " +
                "WHERE hh.maLoai = ?";
        return executeQuantityQuery(query, type);
    }

    private int executeQuantityQuery(String query, String productType) throws SQLException {
        connection = MysqlConnection.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int totalQuantity = 0;

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, productType);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                totalQuantity = resultSet.getInt(1);
            }
        } finally {
            if (resultSet != null) resultSet.close();
            if (preparedStatement != null) preparedStatement.close();
        }

        return totalQuantity;
    }
}
