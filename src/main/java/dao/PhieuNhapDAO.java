package dao;

import ketnoi.KetNoiCSDL;
import model.PhieuNhap;
import model.ChiTietPhieuNhap;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhieuNhapDAO {

    // ===============================
    // Thêm phiếu nhập
    // ===============================

    public int themPhieuNhap(PhieuNhap phieuNhap) {

        String sql = """
                INSERT INTO phieu_nhap
                (
                    ngay_nhap,
                    nha_cung_cap,
                    tong_tien,
                    ghi_chu
                )
                VALUES
                (
                    ?,
                    ?,
                    ?,
                    ?
                )
                """;

        try (

                Connection conn =
                        KetNoiCSDL.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(
                                sql,
                                Statement.RETURN_GENERATED_KEYS
                        );

        ) {

            ps.setDate(
                    1,
                    phieuNhap.getNgayNhap()
            );

            ps.setString(
                    2,
                    phieuNhap.getNhaCungCap()
            );

            ps.setDouble(
                    3,
                    phieuNhap.getTongTien()
            );

            ps.setString(
                    4,
                    phieuNhap.getGhiChu()
            );

            int rows = ps.executeUpdate();

            if (rows > 0) {

                ResultSet rs =
                        ps.getGeneratedKeys();

                if (rs.next()) {

                    return rs.getInt(1);

                }

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return -1;

    }

    // ===============================
    // Thêm chi tiết phiếu nhập
    // ===============================

    public boolean insertChiTiet(
            ChiTietPhieuNhap ct
    ) {

        String sql = """
                INSERT INTO chi_tiet_phieu_nhap
                (
                    ma_phieu_nhap,
                    ma_san_pham,
                    ma_kho,
                    so_luong,
                    don_gia
                )
                VALUES
                (
                    ?,
                    ?,
                    ?,
                    ?,
                    ?
                )
                """;

        try (

                Connection conn =
                        KetNoiCSDL.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql);

        ) {

            ps.setInt(
                    1,
                    ct.getMaPhieuNhap()
            );

            ps.setInt(
                    2,
                    ct.getMaSanPham()
            );

            ps.setInt(
                    3,
                    ct.getMaKho()
            );

            ps.setInt(
                    4,
                    ct.getSoLuong()
            );

            ps.setDouble(
                    5,
                    ct.getDonGia()
            );

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;

    }

    // ===============================
    // Danh sách phiếu nhập
    // ===============================

    public List<PhieuNhap> getAll() {

        List<PhieuNhap> ds =
                new ArrayList<>();

        String sql = """
                SELECT *
                FROM phieu_nhap
                ORDER BY ma_phieu_nhap DESC
                """;

        try (

                Connection conn =
                        KetNoiCSDL.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql);

                ResultSet rs =
                        ps.executeQuery()

        ) {

            while (rs.next()) {

                PhieuNhap pn =
                        new PhieuNhap();

                pn.setMaPhieuNhap(
                        rs.getInt("ma_phieu_nhap"));

                pn.setNgayNhap(
                        rs.getDate("ngay_nhap"));

                pn.setNhaCungCap(
                        rs.getString("nha_cung_cap"));

                pn.setTongTien(
                        rs.getDouble("tong_tien"));

                pn.setGhiChu(
                        rs.getString("ghi_chu"));

                ds.add(pn);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return ds;

    }

    // ===============================
    // Tìm theo mã
    // ===============================

    public PhieuNhap findById(
            int maPhieuNhap
    ) {

        String sql = """
                SELECT *
                FROM phieu_nhap
                WHERE ma_phieu_nhap = ?
                """;

        try (

                Connection conn =
                        KetNoiCSDL.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql);

        ) {

            ps.setInt(
                    1,
                    maPhieuNhap
            );

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                PhieuNhap pn =
                        new PhieuNhap();

                pn.setMaPhieuNhap(
                        rs.getInt("ma_phieu_nhap"));

                pn.setNgayNhap(
                        rs.getDate("ngay_nhap"));

                pn.setNhaCungCap(
                        rs.getString("nha_cung_cap"));

                pn.setTongTien(
                        rs.getDouble("tong_tien"));

                pn.setGhiChu(
                        rs.getString("ghi_chu"));

                return pn;

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;

    }

    // ===============================
    // Lưu phiếu nhập (Transaction)
    // ===============================

    public boolean luuPhieuNhap(
            PhieuNhap phieuNhap,
            List<ChiTietPhieuNhap> dsChiTiet
    ) {

        Connection conn = null;

        try {

            conn = KetNoiCSDL.getConnection();
            conn.setAutoCommit(false);

            String sqlPhieuNhap = """
                    INSERT INTO phieu_nhap
                    (
                        ngay_nhap,
                        nha_cung_cap,
                        tong_tien,
                        ghi_chu
                    )
                    VALUES
                    (
                        ?,
                        ?,
                        ?,
                        ?
                    )
                    """;

            PreparedStatement psPN =
                    conn.prepareStatement(
                            sqlPhieuNhap,
                            Statement.RETURN_GENERATED_KEYS
                    );

            psPN.setDate(1, phieuNhap.getNgayNhap());
            psPN.setString(2, phieuNhap.getNhaCungCap());
            psPN.setDouble(3, phieuNhap.getTongTien());
            psPN.setString(4, phieuNhap.getGhiChu());

            psPN.executeUpdate();

            ResultSet rs = psPN.getGeneratedKeys();

            int maPhieuNhap = 0;

            if (rs.next()) {
                maPhieuNhap = rs.getInt(1);
            }

            String sqlCT = """
                    INSERT INTO chi_tiet_phieu_nhap
                    (
                        ma_phieu_nhap,
                        ma_san_pham,
                        ma_kho,
                        so_luong,
                        don_gia
                    )
                    VALUES
                    (
                        ?,
                        ?,
                        ?,
                        ?,
                        ?
                    )
                    """;

            PreparedStatement psCT =
                    conn.prepareStatement(sqlCT);

            for (ChiTietPhieuNhap ct : dsChiTiet) {

                psCT.setInt(1, maPhieuNhap);
                psCT.setInt(2, ct.getMaSanPham());
                psCT.setInt(3, ct.getMaKho());
                psCT.setInt(4, ct.getSoLuong());
                psCT.setDouble(5, ct.getDonGia());

                psCT.executeUpdate();

                capNhatTonKho(
                        conn,
                        ct.getMaSanPham(),
                        ct.getMaKho(),
                        ct.getSoLuong()
                );
            }

            conn.commit();

            return true;

        } catch (Exception e) {

            try {

                if (conn != null) {
                    conn.rollback();
                }

            } catch (Exception ex) {

                ex.printStackTrace();

            }

            e.printStackTrace();

        } finally {

            try {

                if (conn != null) {

                    conn.setAutoCommit(true);
                    conn.close();

                }

            } catch (Exception e) {

                e.printStackTrace();

            }

        }

        return false;

    }

    // ===============================
    // Cập nhật tồn kho
    // ===============================

    private void capNhatTonKho(
            Connection conn,
            int maSanPham,
            int maKho,
            int soLuong
    ) throws SQLException {

        String checkSql = """
                SELECT so_luong
                FROM ton_kho
                WHERE ma_san_pham = ?
                AND ma_kho = ?
                """;

        PreparedStatement psCheck =
                conn.prepareStatement(checkSql);

        psCheck.setInt(1, maSanPham);
        psCheck.setInt(2, maKho);

        ResultSet rs = psCheck.executeQuery();

        if (rs.next()) {

            String updateSql = """
                    UPDATE ton_kho
                    SET so_luong = so_luong + ?
                    WHERE ma_san_pham = ?
                    AND ma_kho = ?
                    """;

            PreparedStatement psUpdate =
                    conn.prepareStatement(updateSql);

            psUpdate.setInt(1, soLuong);
            psUpdate.setInt(2, maSanPham);
            psUpdate.setInt(3, maKho);

            psUpdate.executeUpdate();

        } else {

            String insertSql = """
                    INSERT INTO ton_kho
                    (
                        ma_san_pham,
                        ma_kho,
                        so_luong
                    )
                    VALUES
                    (
                        ?,
                        ?,
                        ?
                    )
                    """;

            PreparedStatement psInsert =
                    conn.prepareStatement(insertSql);

            psInsert.setInt(1, maSanPham);
            psInsert.setInt(2, maKho);
            psInsert.setInt(3, soLuong);

            psInsert.executeUpdate();

        }

    }

    // ===============================
    // Lấy chi tiết phiếu nhập
    // ===============================

    public List<ChiTietPhieuNhap> getChiTietPhieuNhap(
            int maPhieuNhap
    ) {

        List<ChiTietPhieuNhap> ds =
                new ArrayList<>();

        String sql = """
                SELECT *
                FROM chi_tiet_phieu_nhap
                WHERE ma_phieu_nhap = ?
                """;

        try (

                Connection conn =
                        KetNoiCSDL.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql);

        ) {

            ps.setInt(1, maPhieuNhap);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                ChiTietPhieuNhap ct =
                        new ChiTietPhieuNhap();

                ct.setMaPhieuNhap(
                        rs.getInt("ma_phieu_nhap"));

                ct.setMaSanPham(
                        rs.getInt("ma_san_pham"));

                ct.setMaKho(
                        rs.getInt("ma_kho"));

                ct.setSoLuong(
                        rs.getInt("so_luong"));

                ct.setDonGia(
                        rs.getDouble("don_gia"));

                ds.add(ct);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return ds;

    }

    // ===============================
    // Xóa phiếu nhập
    // ===============================

    public boolean deletePhieuNhap(
            int maPhieuNhap
    ) {

        Connection conn = null;

        try {

            conn = KetNoiCSDL.getConnection();

            conn.setAutoCommit(false);

            PreparedStatement psCT =
                    conn.prepareStatement("""
                            DELETE FROM chi_tiet_phieu_nhap
                            WHERE ma_phieu_nhap = ?
                            """);

            psCT.setInt(1, maPhieuNhap);
            psCT.executeUpdate();

            PreparedStatement psPN =
                    conn.prepareStatement("""
                            DELETE FROM phieu_nhap
                            WHERE ma_phieu_nhap = ?
                            """);

            psPN.setInt(1, maPhieuNhap);

            boolean ok = psPN.executeUpdate() > 0;

            conn.commit();

            return ok;

        } catch (Exception e) {

            try {

                if (conn != null) {

                    conn.rollback();

                }

            } catch (Exception ex) {

                ex.printStackTrace();

            }

            e.printStackTrace();

        } finally {

            try {

                if (conn != null) {

                    conn.setAutoCommit(true);
                    conn.close();

                }

            } catch (Exception e) {

                e.printStackTrace();

            }

        }

        return false;

    }

}