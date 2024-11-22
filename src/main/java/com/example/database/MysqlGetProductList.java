package com.example.database;

import com.example.entity.HangDienMay;
import com.example.entity.HangHoa;
import com.example.entity.HangSanhSu;
import com.example.entity.HangThucPham;
import com.example.interfaces.DatabaseBoundary;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MysqlGetProductList implements DatabaseBoundary {

    public enum ProductType {
        HANG_DIEN_MAY("Hàng Điện Máy",
                "SELECT h.maHang, h.tenHang, h.soLuongTon, h.donGia, d.thoiGianBaoHanh, d.congSuat, l.tenLoai "
                        + "FROM hanghoa h INNER JOIN hangdienmay d ON h.maHang = d.maHang "
                        + "INNER JOIN loaihang l ON h.maLoai = l.maLoai WHERE l.tenLoai = 'Hàng Điện Máy'"),
        HANG_SANH_SU("Hàng Sành Sứ",
                "SELECT h.maHang, h.tenHang, h.soLuongTon, h.donGia, s.nhaSanXuat, s.ngayNhapKho, l.tenLoai "
                        + "FROM hanghoa h INNER JOIN hangsanhsu s ON h.maHang = s.maHang "
                        + "INNER JOIN loaihang l ON h.maLoai = l.maLoai WHERE l.tenLoai = 'Hàng Sành Sứ'"),
        HANG_THUC_PHAM("Hàng Thực Phẩm",
                "SELECT h.maHang, h.tenHang, h.soLuongTon, h.donGia, t.ngaySanXuat, t.ngayHetHan, t.nhaCungCap, l.tenLoai "
                        + "FROM hanghoa h INNER JOIN hangthucpham t ON h.maHang = t.maHang "
                        + "INNER JOIN loaihang l ON h.maLoai = l.maLoai WHERE l.tenLoai = 'Hàng Thực Phẩm'"),
        ALL_PRODUCTS("Tất Cả",
                "SELECT h.maHang, h.tenHang, h.soLuongTon, h.donGia, l.tenLoai "
                        + "FROM hanghoa h INNER JOIN loaihang l ON h.maLoai = l.maLoai");

        private final String typeName;
        private final String sqlQuery;

        ProductType(String typeName, String sqlQuery) {
            this.typeName = typeName;
            this.sqlQuery = sqlQuery;
        }

        public String getTypeName() {
            return typeName;
        }

        public String getSqlQuery() {
            return sqlQuery;
        }

        public static ProductType fromString(String typeName) {
            for (ProductType type : values()) {
                if (type.getTypeName().equalsIgnoreCase(typeName)) {
                    return type;
                }
            }
            return null; // Hoặc bạn có thể ném một ngoại lệ nếu không tìm thấy
        }
    }

    public List<HangHoa> getProductList(String type) {
        List<HangHoa> productDB = new ArrayList<>();

        // Lấy loại sản phẩm từ ProductType enum
        ProductType productType = ProductType.fromString(type);
        if (productType == null) {
            return productDB; // Trả về danh sách trống nếu không có loại sản phẩm hợp lệ
        }

        // Dùng try-with-resources để tự động đóng kết nối sau khi xong
        try (Connection connection = MysqlConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(productType.getSqlQuery());

            while (resultSet.next()) {
                HangHoa hangHoa = createProductFromResultSet(resultSet, productType);
                if (hangHoa != null) {
                    productDB.add(hangHoa);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productDB;
    }

    private HangHoa createProductFromResultSet(ResultSet resultSet, ProductType productType) throws SQLException {
        switch (productType) {
            case HANG_DIEN_MAY:
                return new HangDienMay(
                        resultSet.getString("maHang"),
                        resultSet.getString("tenHang"),
                        resultSet.getInt("soLuongTon"),
                        resultSet.getDouble("donGia"),
                        resultSet.getString("tenLoai"),
                        resultSet.getInt("thoiGianBaoHanh"),
                        resultSet.getDouble("congSuat")
                );
            case HANG_SANH_SU:
                return new HangSanhSu(
                        resultSet.getString("maHang"),
                        resultSet.getString("tenHang"),
                        resultSet.getInt("soLuongTon"),
                        resultSet.getDouble("donGia"),
                        resultSet.getString("tenLoai"),
                        resultSet.getString("nhaSanXuat"),
                        resultSet.getDate("ngayNhapKho").toLocalDate()
                );
            case HANG_THUC_PHAM:
                return new HangThucPham(
                        resultSet.getString("maHang"),
                        resultSet.getString("tenHang"),
                        resultSet.getInt("soLuongTon"),
                        resultSet.getDouble("donGia"),
                        resultSet.getString("tenLoai"),
                        resultSet.getDate("ngaySanXuat").toLocalDate(),
                        resultSet.getDate("ngayHetHan").toLocalDate(),
                        resultSet.getString("nhaCungCap")
                );
            case ALL_PRODUCTS:
                // Trả về một đối tượng mặc định cho tất cả các sản phẩm
                String tenLoai = resultSet.getString("tenLoai");
                if (tenLoai.equals("Hàng Điện Máy")) {
                    return new HangDienMay(
                            resultSet.getString("maHang"),
                            resultSet.getString("tenHang"),
                            resultSet.getInt("soLuongTon"),
                            resultSet.getDouble("donGia"),
                            tenLoai,
                            0,  // thoiGianBaoHanh mặc định
                            0.0 // congSuat mặc định
                    );
                } else if (tenLoai.equals("Hàng Sành Sứ")) {
                    return new HangSanhSu(
                            resultSet.getString("maHang"),
                            resultSet.getString("tenHang"),
                            resultSet.getInt("soLuongTon"),
                            resultSet.getDouble("donGia"),
                            tenLoai,
                            "",  // nhaSanXuat mặc định
                            null // ngayNhapKho mặc định
                    );
                } else if (tenLoai.equals("Hàng Thực Phẩm")) {
                    return new HangThucPham(
                            resultSet.getString("maHang"),
                            resultSet.getString("tenHang"),
                            resultSet.getInt("soLuongTon"),
                            resultSet.getDouble("donGia"),
                            tenLoai,
                            null, // ngaySanXuat mặc định
                            null, // ngayHetHan mặc định
                            ""    // nhaCungCap mặc định
                    );
                }
                break;
        }
        return null; // Trả về null nếu không khớp với bất kỳ loại nào
    }
}