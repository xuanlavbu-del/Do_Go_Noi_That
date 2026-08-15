package dao;

import ketnoi.KetNoiCSDL;
import model.ChiTietDonHang;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChiTietDonHangDAO {

    // ==========================================
    // Chuyển ResultSet thành đối tượng
    // ==========================================
    private ChiTietDonHang mapResultSet(ResultSet rs) throws SQLException {

        ChiTietDonHang chiTiet = new ChiTietDonHang();

        chiTiet.setMaCTDH(rs.getInt("ma_CTDH"));
        chiTiet.setMaDon(rs.getInt("ma_don"));
        chiTiet.setMaSanPham(rs.getInt("ma_san_pham"));
        chiTiet.setSoLuong(rs.getInt("so_luong"));
        chiTiet.setDonGia(rs.getDouble("don_gia"));
        chiTiet.setThanhTien(rs.getDouble("thanh_tien"));

        return chiTiet;
    }


    // ==========================================
    // Thêm chi tiết đơn hàng
    // ==========================================
    public boolean themChiTiet(ChiTietDonHang chiTiet) {

        String sql =
                "INSERT INTO chi_tiet_don_hang " +
                        "(ma_don, ma_san_pham, so_luong, don_gia, thanh_tien) " +
                        "VALUES (?, ?, ?, ?, ?)";

        try (
                Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, chiTiet.getMaDon());
            ps.setInt(2, chiTiet.getMaSanPham());
            ps.setInt(3, chiTiet.getSoLuong());
            ps.setDouble(4, chiTiet.getDonGia());
            ps.setDouble(5, chiTiet.getThanhTien());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    // ==========================================
    // Thêm chi tiết và lấy mã CTDH
    // ==========================================
    public int themChiTietLayMa(ChiTietDonHang chiTiet) {

        String sql =
                "INSERT INTO chi_tiet_don_hang " +
                        "(ma_don, ma_san_pham, so_luong, don_gia, thanh_tien) " +
                        "VALUES (?, ?, ?, ?, ?)";

        try (
                Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps =
                        conn.prepareStatement(
                                sql,
                                Statement.RETURN_GENERATED_KEYS
                        )
        ) {

            ps.setInt(1, chiTiet.getMaDon());
            ps.setInt(2, chiTiet.getMaSanPham());
            ps.setInt(3, chiTiet.getSoLuong());
            ps.setDouble(4, chiTiet.getDonGia());
            ps.setDouble(5, chiTiet.getThanhTien());

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


    // ==========================================
    // Lấy chi tiết theo mã CTDH
    // ==========================================
    public ChiTietDonHang layTheoMa(int maCTDH) {

        String sql =
                "SELECT * FROM chi_tiet_don_hang " +
                        "WHERE ma_CTDH = ?";

        try (
                Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, maCTDH);

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


    // ==========================================
    // Lấy tất cả chi tiết của một đơn hàng
    // ==========================================
    public List<ChiTietDonHang> layTheoMaDon(int maDon) {

        List<ChiTietDonHang> danhSach = new ArrayList<>();

        String sql =
                "SELECT * FROM chi_tiet_don_hang " +
                        "WHERE ma_don = ? " +
                        "ORDER BY ma_CTDH ASC";

        try (
                Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, maDon);

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


    // ==========================================
    // Lấy tất cả chi tiết đơn hàng
    // ==========================================
    public List<ChiTietDonHang> layTatCa() {

        List<ChiTietDonHang> danhSach = new ArrayList<>();

        String sql =
                "SELECT * FROM chi_tiet_don_hang " +
                        "ORDER BY ma_CTDH DESC";

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


    // ==========================================
    // Cập nhật chi tiết đơn hàng
    // ==========================================
    public boolean capNhatChiTiet(ChiTietDonHang chiTiet) {

        String sql =
                "UPDATE chi_tiet_don_hang SET " +
                        "ma_san_pham = ?, " +
                        "so_luong = ?, " +
                        "don_gia = ?, " +
                        "thanh_tien = ? " +
                        "WHERE ma_CTDH = ?";

        try (
                Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, chiTiet.getMaSanPham());
            ps.setInt(2, chiTiet.getSoLuong());
            ps.setDouble(3, chiTiet.getDonGia());
            ps.setDouble(4, chiTiet.getThanhTien());
            ps.setInt(5, chiTiet.getMaCTDH());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    // ==========================================
    // Cập nhật số lượng
    // ==========================================
    public boolean capNhatSoLuong(int maCTDH, int soLuong) {

        String sql =
                "UPDATE chi_tiet_don_hang " +
                        "SET so_luong = ?, " +
                        "thanh_tien = don_gia * ? " +
                        "WHERE ma_CTDH = ?";

        try (
                Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, soLuong);
            ps.setInt(2, soLuong);
            ps.setInt(3, maCTDH);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    // ==========================================
    // Xóa một chi tiết đơn hàng
    // ==========================================
    public boolean xoaChiTiet(int maCTDH) {

        String sql =
                "DELETE FROM chi_tiet_don_hang " +
                        "WHERE ma_CTDH = ?";

        try (
                Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, maCTDH);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    // ==========================================
    // Xóa toàn bộ chi tiết của đơn hàng
    // ==========================================
    public boolean xoaTheoMaDon(int maDon) {

        String sql =
                "DELETE FROM chi_tiet_don_hang " +
                        "WHERE ma_don = ?";

        try (
                Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, maDon);

            ps.executeUpdate();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    // ==========================================
    // Tính tổng tiền của một đơn hàng
    // ==========================================
    public double tinhTongTien(int maDon) {

        String sql =
                "SELECT COALESCE(SUM(thanh_tien), 0) " +
                        "FROM chi_tiet_don_hang " +
                        "WHERE ma_don = ?";

        try (
                Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, maDon);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return rs.getDouble(1);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }


// =====================================================
// Thêm nhiều chi tiết đơn hàng
// =====================================================

    public boolean themDanhSachChiTiet(
            List<ChiTietDonHang> danhSach) {

        String sql =
                "INSERT INTO chi_tiet_don_hang " +
                        "(ma_don, ma_san_pham, so_luong, don_gia, thanh_tien) " +
                        "VALUES (?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement ps = null;

        try {

            conn = KetNoiCSDL.getConnection();

            ps = conn.prepareStatement(sql);

            for (ChiTietDonHang ct : danhSach) {

                ps.setInt(
                        1,
                        ct.getMaDon()
                );

                ps.setInt(
                        2,
                        ct.getMaSanPham()
                );

                ps.setInt(
                        3,
                        ct.getSoLuong()
                );

                ps.setDouble(
                        4,
                        ct.getDonGia()
                );

                ps.setDouble(
                        5,
                        ct.getThanhTien()
                );

                ps.addBatch();
            }

            ps.executeBatch();

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;

        } finally {

            try {

                if (ps != null) {
                    ps.close();
                }

                if (conn != null) {
                    conn.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



    // ==========================================
    // Đếm số sản phẩm trong đơn
    // ==========================================
    public int demSoLuongSanPham(int maDon) {

        String sql =
                "SELECT COALESCE(SUM(so_luong), 0) " +
                        "FROM chi_tiet_don_hang " +
                        "WHERE ma_don = ?";

        try (
                Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, maDon);

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

