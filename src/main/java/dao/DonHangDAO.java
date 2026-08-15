package dao;

import ketnoi.KetNoiCSDL;
import model.DonHang;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DonHangDAO {

    // ==============================
    // Chuyển ResultSet -> DonHang
    // ==============================
    private DonHang mapResultSet(ResultSet rs) throws SQLException {

        DonHang donHang = new DonHang();

        donHang.setMaDon(rs.getInt("ma_don"));
        donHang.setMaKhachHang(rs.getInt("ma_khach_hang"));
        donHang.setNgayDat(rs.getTimestamp("ngay_dat"));
        donHang.setTongTien(rs.getDouble("tong_tien"));
        donHang.setTrangThai(rs.getString("trang_thai"));
        donHang.setDiaChiGiao(rs.getString("dia_chi_giao"));
        donHang.setGhiChu(rs.getString("ghi_chu"));

        return donHang;
    }


    // ==============================
    // Thêm đơn hàng
    // ==============================
    public boolean themDonHang(DonHang donHang) {

        String sql = "INSERT INTO don_hang " +
                "(ma_khach_hang, tong_tien, trang_thai, dia_chi_giao, ghi_chu) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (
                Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, donHang.getMaKhachHang());
            ps.setDouble(2, donHang.getTongTien());
            ps.setString(3, donHang.getTrangThai());
            ps.setString(4, donHang.getDiaChiGiao());
            ps.setString(5, donHang.getGhiChu());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    // ==============================
    // Thêm đơn hàng và lấy mã đơn
    // ==============================
    public int themDonHangLayMa(DonHang donHang) {

        String sql = "INSERT INTO don_hang " +
                "(ma_khach_hang, tong_tien, trang_thai, dia_chi_giao, ghi_chu) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (
                Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps =
                        conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {

            ps.setInt(1, donHang.getMaKhachHang());
            ps.setDouble(2, donHang.getTongTien());
            ps.setString(3, donHang.getTrangThai());
            ps.setString(4, donHang.getDiaChiGiao());
            ps.setString(5, donHang.getGhiChu());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {

                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }


    // ==============================
    // Lấy đơn hàng theo mã
    // ==============================
    public DonHang layDonHangTheoMa(int maDon) {

        String sql =
                "SELECT * FROM don_hang WHERE ma_don = ?";

        try (
                Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, maDon);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return mapResultSet(rs);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    // ==============================
    // Lấy tất cả đơn hàng
    // ==============================
    public List<DonHang> layTatCaDonHang() {

        List<DonHang> danhSach = new ArrayList<>();

        String sql =
                "SELECT * FROM don_hang ORDER BY ma_don DESC";

        try (
                Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {
                danhSach.add(mapResultSet(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return danhSach;
    }


    // ==============================
    // Lấy đơn theo khách hàng
    // ==============================
    public List<DonHang> layDonHangTheoKhachHang(int maKhachHang) {

        List<DonHang> danhSach = new ArrayList<>();

        String sql =
                "SELECT * FROM don_hang " +
                        "WHERE ma_khach_hang = ? " +
                        "ORDER BY ma_don DESC";

        try (
                Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, maKhachHang);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    danhSach.add(mapResultSet(rs));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return danhSach;
    }


    // ==============================
    // Cập nhật trạng thái
    // ==============================
    public boolean capNhatTrangThai(int maDon, String trangThai) {

        String sql =
                "UPDATE don_hang " +
                        "SET trang_thai = ? " +
                        "WHERE ma_don = ?";

        try (
                Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, trangThai);
            ps.setInt(2, maDon);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    // ==============================
    // Cập nhật đơn hàng
    // ==============================
    public boolean capNhatDonHang(DonHang donHang) {

        String sql =
                "UPDATE don_hang SET " +
                        "ma_khach_hang = ?, " +
                        "tong_tien = ?, " +
                        "trang_thai = ?, " +
                        "dia_chi_giao = ?, " +
                        "ghi_chu = ? " +
                        "WHERE ma_don = ?";

        try (
                Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, donHang.getMaKhachHang());
            ps.setDouble(2, donHang.getTongTien());
            ps.setString(3, donHang.getTrangThai());
            ps.setString(4, donHang.getDiaChiGiao());
            ps.setString(5, donHang.getGhiChu());
            ps.setInt(6, donHang.getMaDon());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    // ==============================
    // Xóa đơn hàng
    // ==============================
    public boolean xoaDonHang(int maDon) {

        String sql =
                "DELETE FROM don_hang WHERE ma_don = ?";

        try (
                Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, maDon);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    // ==============================
    // Tìm kiếm đơn hàng
    // ==============================
    public List<DonHang> timKiem(String keyword) {

        List<DonHang> danhSach = new ArrayList<>();

        String sql =
                "SELECT * FROM don_hang " +
                        "WHERE CAST(ma_don AS CHAR) LIKE ? " +
                        "OR CAST(ma_khach_hang AS CHAR) LIKE ? " +
                        "OR trang_thai LIKE ? " +
                        "ORDER BY ma_don DESC";

        try (
                Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            String key = "%" + keyword + "%";

            ps.setString(1, key);
            ps.setString(2, key);
            ps.setString(3, key);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    danhSach.add(mapResultSet(rs));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return danhSach;
    }


    // ==============================
    // Lọc theo trạng thái
    // ==============================
    public List<DonHang> layTheoTrangThai(String trangThai) {

        List<DonHang> danhSach = new ArrayList<>();

        String sql =
                "SELECT * FROM don_hang " +
                        "WHERE trang_thai = ? " +
                        "ORDER BY ma_don DESC";

        try (
                Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, trangThai);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    danhSach.add(mapResultSet(rs));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return danhSach;
    }


    // ==============================
    // Đếm tổng số đơn hàng
    // ==============================
    public int demDonHang() {

        String sql =
                "SELECT COUNT(*) FROM don_hang";

        try (
                Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }


    // ==============================
    // Đếm đơn theo trạng thái
    // ==============================
    public int demTheoTrangThai(String trangThai) {

        String sql =
                "SELECT COUNT(*) " +
                        "FROM don_hang " +
                        "WHERE trang_thai = ?";

        try (
                Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, trangThai);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
}