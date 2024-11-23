package com.example.database.UpdateProduct;

import com.example.database.MysqlConnection;
import com.example.entity.HangDienMay;
import com.example.entity.HangHoa;
import com.example.entity.HangSanhSu;
import com.example.entity.HangThucPham;
import com.example.interfaces.DatabaseBoundary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateProductSqlDAO implements DatabaseBoundary {
    public String updateProduct(HangHoa hangHoa) {
        // Determine the type of product and call the appropriate method
        if (hangHoa instanceof HangDienMay) {
            return updateDienMayProduct((HangDienMay) hangHoa);
        } else if (hangHoa instanceof HangSanhSu) {
            return updateSanhSuProduct((HangSanhSu) hangHoa);
        } else if (hangHoa instanceof HangThucPham) {
            return updateThucPhamProduct((HangThucPham) hangHoa);
        } else {
            return "Loại sản phẩm không được hỗ trợ.";
        }
    }

    private String updateDienMayProduct(HangDienMay hangDienMay) {
        String queryHangDienMay = "UPDATE hangdienmay SET thoiGianBaoHanh = ?, congSuat = ? WHERE maHang = ?";
        String queryHangHoa = "UPDATE hanghoa SET tenHang = ?, soLuongTon = ?, donGia = ? WHERE maHang = ?";

        try (Connection connection = MysqlConnection.getConnection()) {
            connection.setAutoCommit(false);

            try (PreparedStatement preparedStatementHangDienMay = connection.prepareStatement(queryHangDienMay)) {
                preparedStatementHangDienMay.setInt(1, hangDienMay.getThoiGianBaoHanh());
                preparedStatementHangDienMay.setDouble(2, hangDienMay.getCongSuat());
                preparedStatementHangDienMay.setString(3, hangDienMay.getMaHang());

                int rowsUpdated = preparedStatementHangDienMay.executeUpdate();
                if (rowsUpdated == 0) {
                    connection.rollback();
                    return "Mã hàng không tìm thấy trong bảng hangdienmay, vui lòng kiểm tra lại.";
                }
            }

            try (PreparedStatement preparedStatementHangHoa = connection.prepareStatement(queryHangHoa)) {
                preparedStatementHangHoa.setString(1, hangDienMay.getTenHang());
                preparedStatementHangHoa.setInt(2, hangDienMay.getSoLuongTon());
                preparedStatementHangHoa.setDouble(3, hangDienMay.getDonGia());
                preparedStatementHangHoa.setString(4, hangDienMay.getMaHang());

                int rowsUpdated = preparedStatementHangHoa.executeUpdate();
                if (rowsUpdated == 0) {
                    connection.rollback();
                    return "Mã hàng không tìm thấy trong bảng hanghoa, vui lòng kiểm tra lại.";
                }
            }

            connection.commit();
            return "Cập nhật sản phẩm điện máy thành công.";

        } catch (SQLException e) {
            e.printStackTrace();
            return "Lỗi cập nhật sản phẩm điện máy.";
        }
    }

    private String updateSanhSuProduct(HangSanhSu hangSanhSu) {
        String queryHangSanhSu = "UPDATE hangsangsu SET nhaSanXuat = ?, ngayNhapKho = ? WHERE maHang = ?";
        String queryHangHoa = "UPDATE hanghoa SET tenHang = ?, soLuongTon = ?, donGia = ? WHERE maHang = ?";

        try (Connection connection = MysqlConnection.getConnection()) {
            connection.setAutoCommit(false);

            try (PreparedStatement preparedStatementHangSanhSu = connection.prepareStatement(queryHangSanhSu)) {
                preparedStatementHangSanhSu.setString(1, hangSanhSu.getNhaSanXuat());
                preparedStatementHangSanhSu.setDate(2, java.sql.Date.valueOf(hangSanhSu.getNgayNhapKho()));
                preparedStatementHangSanhSu.setString(3, hangSanhSu.getMaHang());

                int rowsUpdated = preparedStatementHangSanhSu.executeUpdate();
                if (rowsUpdated == 0) {
                    connection.rollback();
                    return "Mã hàng không tìm thấy trong bảng hangsangsu, vui lòng kiểm tra lại.";
                }
            }

            try (PreparedStatement preparedStatementHangHoa = connection.prepareStatement(queryHangHoa)) {
                preparedStatementHangHoa.setString(1, hangSanhSu.getTenHang());
                preparedStatementHangHoa.setInt(2, hangSanhSu.getSoLuongTon());
                preparedStatementHangHoa.setDouble(3, hangSanhSu.getDonGia());
                preparedStatementHangHoa.setString(4, hangSanhSu.getMaHang());

                int rowsUpdated = preparedStatementHangHoa.executeUpdate();
                if (rowsUpdated == 0) {
                    connection.rollback();
                    return "Mã hàng không tìm thấy trong bảng hanghoa, vui lòng kiểm tra lại.";
                }
            }

            connection.commit();
            return "Cập nhật sản phẩm sành sứ thành công.";

        } catch (SQLException e) {
            e.printStackTrace();
            return "Lỗi cập nhật sản phẩm sành sứ";
        }
    }

    private String updateThucPhamProduct(HangThucPham hangThucPham) {
        String queryHangThucPham = "UPDATE hangthucpham SET ngaySanXuat = ?, ngayHetHan = ?, nhaCungCap = ? WHERE maHang = ?";
        String queryHangHoa = "UPDATE hanghoa SET tenHang = ?, soLuongTon = ?, donGia = ? WHERE maHang = ?";

        try (Connection connection = MysqlConnection.getConnection()) {
            connection.setAutoCommit(false);

            try (PreparedStatement preparedStatementHangThucPham = connection.prepareStatement(queryHangThucPham)) {
                preparedStatementHangThucPham.setDate(1, java.sql.Date.valueOf(hangThucPham.getNgaySanXuat()));
                preparedStatementHangThucPham.setDate(2, java.sql.Date.valueOf(hangThucPham.getNgayHetHan()));
                preparedStatementHangThucPham.setString(3, hangThucPham.getNhaCungCap());
                preparedStatementHangThucPham.setString(4, hangThucPham.getMaHang());

                int rowsUpdated = preparedStatementHangThucPham.executeUpdate();
                if (rowsUpdated == 0) {
                    connection.rollback();
                    return "Mã hàng không tìm thấy trong bảng hangthucpham, vui lòng kiểm tra lại.";
                }
            }

            try (PreparedStatement preparedStatementHangHoa = connection.prepareStatement(queryHangHoa)) {
                preparedStatementHangHoa.setString(1, hangThucPham.getTenHang());
                preparedStatementHangHoa.setInt(2, hangThucPham.getSoLuongTon());
                preparedStatementHangHoa.setDouble(3, hangThucPham.getDonGia());
                preparedStatementHangHoa.setString(4, hangThucPham.getMaHang());

                int rowsUpdated = preparedStatementHangHoa.executeUpdate();
                if (rowsUpdated == 0) {
                    connection.rollback();
                    return "Mã hàng không tìm thấy trong bảng hanghoa, vui lòng kiểm tra lại.";
                }
            }

            connection.commit();
            return "Cập nhật sản phẩm thực phẩm thành công.";

        } catch (SQLException e) {
            e.printStackTrace();
            return "Lỗi cập nhật sản phẩm thực phẩm";
        }
    }
}
