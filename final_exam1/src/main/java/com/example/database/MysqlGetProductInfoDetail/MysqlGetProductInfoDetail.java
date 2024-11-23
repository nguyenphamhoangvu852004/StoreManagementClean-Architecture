package com.example.database.MysqlGetProductInfoDetail;

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
import java.time.LocalDate;

public class MysqlGetProductInfoDetail implements DatabaseBoundary {
    public HangHoa getProductInfoDetail(String type, String maHang) {

        if (type.equals("HangDienMay")) {
            return getDienMayProductDetail(maHang);
        } else if (type.equals("HangSanhSu")) {
            return getSanhSuProductDetail(maHang);
        } else if (type.equals("HangThucPham")) {
            return getThucPhamProductDetail(maHang);
        }

        return null;
    }

    private HangHoa getDienMayProductDetail(String maHang) {
        String queryHangDienMay = "SELECT * FROM hangdienmay WHERE maHang = ?";
        String queryHangHoa = "SELECT * FROM hanghoa WHERE maHang = ?";

        try (Connection connection = MysqlConnection.getConnection()) {
            try (PreparedStatement preparedStatementHangDienMay = connection.prepareStatement(queryHangDienMay)) {
                preparedStatementHangDienMay.setString(1, maHang);
                ResultSet rsHangDienMay = preparedStatementHangDienMay.executeQuery();

                if (rsHangDienMay.next()) {
                    int thoiGianBaoHanh = rsHangDienMay.getInt("thoiGianBaoHanh");
                    double congSuat = rsHangDienMay.getDouble("congSuat");

                    try (PreparedStatement preparedStatementHangHoa = connection.prepareStatement(queryHangHoa)) {
                        preparedStatementHangHoa.setString(1, maHang);
                        ResultSet rsHangHoa = preparedStatementHangHoa.executeQuery();

                        if (rsHangHoa.next()) {
                            String tenHang = rsHangHoa.getString("tenHang");
                            int soLuongTon = rsHangHoa.getInt("soLuongTon");
                            double donGia = rsHangHoa.getDouble("donGia");

                            return new HangDienMay(maHang, tenHang, soLuongTon, donGia, "HDM", thoiGianBaoHanh, congSuat);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    private HangHoa getSanhSuProductDetail(String maHang) {
        String queryHangSanhSu = "SELECT * FROM hangsanhsu WHERE maHang = ?";
        String queryHangHoa = "SELECT * FROM hanghoa WHERE maHang = ?";

        try (Connection connection = MysqlConnection.getConnection()) {
            try (PreparedStatement preparedStatementHangSanhSu = connection.prepareStatement(queryHangSanhSu)) {
                preparedStatementHangSanhSu.setString(1, maHang);
                ResultSet rsHangSanhSu = preparedStatementHangSanhSu.executeQuery();

                if (rsHangSanhSu.next()) {
                    String nhaSanXuat = rsHangSanhSu.getString("nhaSanXuat");
                    LocalDate ngayNhapKho = LocalDate.parse(rsHangSanhSu.getString("ngayNhapKho"));


                    try (PreparedStatement preparedStatementHangHoa = connection.prepareStatement(queryHangHoa)) {
                        preparedStatementHangHoa.setString(1, maHang);
                        ResultSet rsHangHoa = preparedStatementHangHoa.executeQuery();

                        if (rsHangHoa.next()) {
                            String tenHang = rsHangHoa.getString("tenHang");
                            int soLuongTon = rsHangHoa.getInt("soLuongTon");
                            double donGia = rsHangHoa.getDouble("donGia");

                            return new HangSanhSu(maHang, tenHang, soLuongTon, donGia, "HDM", nhaSanXuat, ngayNhapKho);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    // Method to get Thuc Pham product details
    private HangHoa getThucPhamProductDetail(String maHang) {
        String queryHangThucPham = "SELECT * FROM hangthucpham WHERE maHang = ?";
        String queryHangHoa = "SELECT * FROM hanghoa WHERE maHang = ?";

        try (Connection connection = MysqlConnection.getConnection()) {
            try (PreparedStatement preparedStatementHangThucPham = connection.prepareStatement(queryHangThucPham)) {
                preparedStatementHangThucPham.setString(1, maHang);
                ResultSet rsHangThucPham = preparedStatementHangThucPham.executeQuery();

                if (rsHangThucPham.next()) {
                    LocalDate ngaySanXuat = LocalDate.parse(rsHangThucPham.getString("ngaySanXuat"));
                    LocalDate ngayHetHan = LocalDate.parse(rsHangThucPham.getString("ngayHetHan"));
                    String nhaCungCap = rsHangThucPham.getString("nhaCungCap");

                    try (PreparedStatement preparedStatementHangHoa = connection.prepareStatement(queryHangHoa)) {
                        preparedStatementHangHoa.setString(1, maHang);
                        ResultSet rsHangHoa = preparedStatementHangHoa.executeQuery();

                        if (rsHangHoa.next()) {
                            String tenHang = rsHangHoa.getString("tenHang");
                            int soLuongTon = rsHangHoa.getInt("soLuongTon");
                            double donGia = rsHangHoa.getDouble("donGia");

                            return new HangThucPham(maHang, tenHang, soLuongTon, donGia, "HDM", ngaySanXuat, ngayHetHan, nhaCungCap);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
