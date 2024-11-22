package com.example.database;

import com.example.entity.HangHoa;
import com.example.entity.HangThucPham;
import com.example.interfaces.DatabaseBoundary;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MysqlGetFoodList implements DatabaseBoundary {

    public List<HangHoa> getProductList(String type) {
        List<HangHoa> productDB = new ArrayList<>();

        // Câu truy vấn với dấu hỏi thay vì giá trị cụ thể
        String sqlQueryHangThucPham = "SELECT h.maHang, h.tenHang, h.soLuongTon, h.donGia, t.ngaySanXuat, t.ngayHetHan, t.nhaCungCap, l.tenLoai "
                + "FROM hanghoa h INNER JOIN hangthucpham t ON h.maHang = t.maHang "
                + "INNER JOIN loaihang l ON h.maLoai = l.maLoai WHERE l.tenLoai = ?";

        // Sử dụng try-with-resources cho Connection và PreparedStatement để tự động đóng kết nối sau khi hoàn thành
        try (Connection connection = MysqlConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQueryHangThucPham);
            preparedStatement.setString(1, type); // Gán giá trị `type` vào dấu hỏi

            ResultSet resultSet = preparedStatement.executeQuery(); // Thực thi câu truy vấn

            // Xử lý kết quả truy vấn
            while (resultSet != null && resultSet.next()) {
                HangHoa hangHoa = new HangThucPham(
                        resultSet.getString("maHang"),
                        resultSet.getString("tenHang"),
                        resultSet.getInt("soLuongTon"),
                        resultSet.getDouble("donGia"),
                        resultSet.getString("tenLoai"),
                        resultSet.getDate("ngaySanXuat").toLocalDate(),
                        resultSet.getDate("ngayHetHan").toLocalDate(),
                        resultSet.getString("nhaCungCap")
                );
                productDB.add(hangHoa); // Thêm sản phẩm vào danh sách
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productDB;
    }

}
