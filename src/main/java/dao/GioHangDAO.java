package dao;

import model.GioHang;
import ketnoi.KetNoiCSDL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GioHangDAO {

    // =====================================================
    // Lấy toàn bộ giỏ hàng của một tài khoản
    // =====================================================

    public List<GioHang> layGioHang(int maTaiKhoan) {

        List<GioHang> danhSach = new ArrayList<>();

        String sql =
                "SELECT " +
                        "gh.ma_gio_hang, " +
                        "gh.ma_tai_khoan, " +
                        "gh.ma_san_pham, " +
                        "gh.so_luong, " +
                        "sp.ten_san_pham, " +
                        "sp.gia, " +
                        "sp.hinh_anh " +
                        "FROM gio_hang gh " +
                        "JOIN san_pham sp " +
                        "ON gh.ma_san_pham = sp.ma_san_pham " +
                        "WHERE gh.ma_tai_khoan = ? " +
                        "ORDER BY gh.ma_gio_hang DESC";

        try (
                Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, maTaiKhoan);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                GioHang gioHang = new GioHang();

                gioHang.setMaGioHang(
                        rs.getInt("ma_gio_hang")
                );

                gioHang.setMaTaiKhoan(
                        rs.getInt("ma_tai_khoan")
                );

                gioHang.setMaSanPham(
                        rs.getInt("ma_san_pham")
                );

                gioHang.setTenSanPham(
                        rs.getString("ten_san_pham")
                );

                gioHang.setGia(
                        rs.getDouble("gia")
                );

                gioHang.setSoLuong(
                        rs.getInt("so_luong")
                );

                gioHang.setHinhAnh(
                        rs.getString("hinh_anh")
                );

                danhSach.add(gioHang);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return danhSach;
    }


    // =====================================================
    // Kiểm tra sản phẩm đã có trong giỏ hàng chưa
    // =====================================================

    public GioHang layTheoSanPham(
            int maTaiKhoan,
            int maSanPham
    ) {

        String sql =
                "SELECT " +
                        "gh.ma_gio_hang, " +
                        "gh.ma_tai_khoan, " +
                        "gh.ma_san_pham, " +
                        "gh.so_luong, " +
                        "sp.ten_san_pham, " +
                        "sp.gia, " +
                        "sp.hinh_anh " +
                        "FROM gio_hang gh " +
                        "JOIN san_pham sp " +
                        "ON gh.ma_san_pham = sp.ma_san_pham " +
                        "WHERE gh.ma_tai_khoan = ? " +
                        "AND gh.ma_san_pham = ?";

        try (
                Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, maTaiKhoan);
            ps.setInt(2, maSanPham);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                GioHang gioHang = new GioHang();

                gioHang.setMaGioHang(
                        rs.getInt("ma_gio_hang")
                );

                gioHang.setMaTaiKhoan(
                        rs.getInt("ma_tai_khoan")
                );

                gioHang.setMaSanPham(
                        rs.getInt("ma_san_pham")
                );

                gioHang.setTenSanPham(
                        rs.getString("ten_san_pham")
                );

                gioHang.setGia(
                        rs.getDouble("gia")
                );

                gioHang.setSoLuong(
                        rs.getInt("so_luong")
                );

                gioHang.setHinhAnh(
                        rs.getString("hinh_anh")
                );

                return gioHang;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    // =====================================================
    // Thêm sản phẩm vào giỏ
    // Nếu sản phẩm đã có thì tăng số lượng
    // =====================================================

    public boolean themVaoGioHang(
            int maTaiKhoan,
            int maSanPham,
            int soLuong
    ) {

        String sql =
                "INSERT INTO gio_hang " +
                        "(ma_tai_khoan, ma_san_pham, so_luong) " +
                        "VALUES (?, ?, ?) " +
                        "ON DUPLICATE KEY UPDATE " +
                        "so_luong = so_luong + VALUES(so_luong)";

        try (
                Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, maTaiKhoan);
            ps.setInt(2, maSanPham);
            ps.setInt(3, soLuong);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    // =====================================================
    // Cập nhật số lượng
    // =====================================================

    public boolean capNhatSoLuong(
            int maTaiKhoan,
            int maSanPham,
            int soLuong
    ) {

        if (soLuong <= 0) {
            return xoaSanPham(
                    maTaiKhoan,
                    maSanPham
            );
        }

        String sql =
                "UPDATE gio_hang " +
                        "SET so_luong = ? " +
                        "WHERE ma_tai_khoan = ? " +
                        "AND ma_san_pham = ?";

        try (
                Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, soLuong);
            ps.setInt(2, maTaiKhoan);
            ps.setInt(3, maSanPham);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    // =====================================================
    // Tăng số lượng thêm 1
    // =====================================================

    public boolean tangSoLuong(
            int maTaiKhoan,
            int maSanPham
    ) {

        String sql =
                "UPDATE gio_hang " +
                        "SET so_luong = so_luong + 1 " +
                        "WHERE ma_tai_khoan = ? " +
                        "AND ma_san_pham = ?";

        try (
                Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, maTaiKhoan);
            ps.setInt(2, maSanPham);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    // =====================================================
    // Giảm số lượng đi 1
    // Nếu còn 0 thì xóa
    // =====================================================

    public boolean giamSoLuong(
            int maTaiKhoan,
            int maSanPham
    ) {

        String sql =
                "UPDATE gio_hang " +
                        "SET so_luong = so_luong - 1 " +
                        "WHERE ma_tai_khoan = ? " +
                        "AND ma_san_pham = ? " +
                        "AND so_luong > 1";

        try (
                Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            int ketQua = ps.executeUpdate();

            if (ketQua > 0) {
                return true;
            }

            // Nếu số lượng đang là 1 thì xóa
            GioHang gioHang =
                    layTheoSanPham(
                            maTaiKhoan,
                            maSanPham
                    );

            if (gioHang != null &&
                    gioHang.getSoLuong() <= 1) {

                return xoaSanPham(
                        maTaiKhoan,
                        maSanPham
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    // =====================================================
    // Xóa một sản phẩm khỏi giỏ
    // =====================================================

    public boolean xoaSanPham(
            int maTaiKhoan,
            int maSanPham
    ) {

        String sql =
                "DELETE FROM gio_hang " +
                        "WHERE ma_tai_khoan = ? " +
                        "AND ma_san_pham = ?";

        try (
                Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, maTaiKhoan);
            ps.setInt(2, maSanPham);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    // =====================================================
    // Xóa toàn bộ giỏ hàng của tài khoản
    // =====================================================

    public boolean xoaTatCa(
            int maTaiKhoan
    ) {

        String sql =
                "DELETE FROM gio_hang " +
                        "WHERE ma_tai_khoan = ?";

        try (
                Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, maTaiKhoan);

            return ps.executeUpdate() >= 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    // =====================================================
    // Đếm tổng số lượng sản phẩm trong giỏ
    // =====================================================

    public int demSoLuong(
            int maTaiKhoan
    ) {

        String sql =
                "SELECT COALESCE(SUM(so_luong), 0) " +
                        "FROM gio_hang " +
                        "WHERE ma_tai_khoan = ?";

        try (
                Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, maTaiKhoan);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }


    // =====================================================
    // Tính tổng tiền
    // =====================================================

    public double tinhTongTien(
            int maTaiKhoan
    ) {

        String sql =
                "SELECT COALESCE(" +
                        "SUM(sp.gia * gh.so_luong), 0" +
                        ") " +
                        "FROM gio_hang gh " +
                        "JOIN san_pham sp " +
                        "ON gh.ma_san_pham = sp.ma_san_pham " +
                        "WHERE gh.ma_tai_khoan = ?";

        try (
                Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, maTaiKhoan);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getDouble(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}