package com.example.database;

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

public class FindProductDAO implements DatabaseBoundary {
    public HangHoa findProductById(String id) {
        String baseQuery = "SELECT * FROM hanghoa WHERE maHang = ?";
        try (Connection conn = MysqlConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(baseQuery)) {
            stmt.setString(1, id.trim());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Lấy thông tin chung từ bảng `hanghoa`
                String maHang = rs.getString("maHang");
                String tenHang = rs.getString("tenHang");
                int soLuongTon = rs.getInt("soLuongTon");
                double donGia = rs.getDouble("donGia");

                // Kiểm tra loại sản phẩm và truy vấn bảng chi tiết
                if (maHang.startsWith("HDM")) {
                    return findHangDienMay(conn, maHang, tenHang, soLuongTon, donGia);
                } else if (maHang.startsWith("HSS")) {
                    return findHangSanhSu(conn, maHang, tenHang, soLuongTon, donGia);
                } else if (maHang.startsWith("HTP")) {
                    return findHangThucPham(conn, maHang, tenHang, soLuongTon, donGia);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Không tìm thấy sản phẩm
    }

    private HangHoa findHangDienMay(Connection conn, String maHang, String tenHang, int soLuongTon, double donGia) throws SQLException {
        String query = "SELECT * FROM hangdienmay WHERE maHang = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, maHang);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int thoiGianBaoHanh = rs.getInt("thoiGianBaoHanh");
                double congSuat = rs.getDouble("congSuat");
                return new HangDienMay(maHang, tenHang, soLuongTon, donGia, "Điện Máy", thoiGianBaoHanh, congSuat);
            }
        }
        return null;
    }

    private HangHoa findHangSanhSu(Connection conn, String maHang, String tenHang, int soLuongTon, double donGia) throws SQLException {
        String query = "SELECT * FROM hangsanhsu WHERE maHang = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, maHang);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String nhaSanXuat = rs.getString("nhaSanXuat");
                LocalDate ngayNhapKho = rs.getDate("ngayNhapKho").toLocalDate();
                return new HangSanhSu(maHang, tenHang, soLuongTon, donGia, "Sành Sứ", nhaSanXuat, ngayNhapKho);
            }
        }
        return null;
    }

    private HangHoa findHangThucPham(Connection conn, String maHang, String tenHang, int soLuongTon, double donGia) throws SQLException {
        String query = "SELECT * FROM hangthucpham WHERE maHang = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, maHang);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                LocalDate ngaySanXuat = rs.getDate("ngaySanXuat").toLocalDate();
                LocalDate ngayHetHan = rs.getDate("ngayHetHan").toLocalDate();
                String nhaCungCap = rs.getString("nhaCungCap");
                return new HangThucPham(maHang, tenHang, soLuongTon, donGia, "Thực Phẩm", ngaySanXuat, ngayHetHan, nhaCungCap);
            }
        }
        return null;
    }
}