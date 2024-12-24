package com.example.removeProduct.database;

import com.example.database.MysqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RemoveProductDAO implements RemoveProductDatabaseBoundary {
    public boolean removeProduct(String maHang){
        try (Connection conn = MysqlConnection.getConnection()) {
            conn.setAutoCommit(false); // Bắt đầu giao dịch

            String sqlDeleteLoaiHang = null;

            // Xác định loại sản phẩm dựa vào tiền tố của mã hàng
            if (maHang.startsWith("HTP")) {
                sqlDeleteLoaiHang = "DELETE FROM hangthucpham WHERE maHang = ?";
            } else if (maHang.startsWith("HDM")) {
                sqlDeleteLoaiHang = "DELETE FROM hangdienmay WHERE maHang = ?";
            } else if (maHang.startsWith("HSS")) {
                sqlDeleteLoaiHang = "DELETE FROM hangsanhsu WHERE maHang = ?";
            } else {
                throw new SQLException("Unknown product type for maHang: " + maHang);
            }

            // Xóa sản phẩm từ bảng loại hàng cụ thể
            try (PreparedStatement stmtDeleteLoaiHang = conn.prepareStatement(sqlDeleteLoaiHang)) {
                stmtDeleteLoaiHang.setString(1, maHang);
                stmtDeleteLoaiHang.executeUpdate();
            }

            // Xóa bản ghi trong bảng hanghoa
            String sqlDeleteHangHoa = "DELETE FROM hanghoa WHERE maHang = ?";
            try (PreparedStatement stmtDeleteHangHoa = conn.prepareStatement(sqlDeleteHangHoa)) {
                stmtDeleteHangHoa.setString(1, maHang);
                int rowsAffected = stmtDeleteHangHoa.executeUpdate();

                if (rowsAffected > 0) {
                    conn.commit(); // Hoàn thành giao dịch
                    return true; // Xóa thành công
                } else {
                    conn.rollback(); // Hoàn tác nếu không tìm thấy mã hàng trong bảng hanghoa
                    return false; // Không tìm thấy mã hàng để xóa
                }
            }

        } catch (SQLException e) {
            return false;
        }
    }
}
