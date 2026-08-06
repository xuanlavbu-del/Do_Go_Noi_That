package dao;

import ketnoi.KetNoiCSDL;
import model.PhieuXuat;
import model.ChiTietPhieuXuat;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhieuXuatDAO {

    // Thêm phiếu xuất
    public int insertPhieuXuat(PhieuXuat phieuXuat) {

        String sql = """
                INSERT INTO phieu_xuat
                (
                    ngay_xuat,
                    nguoi_nhan,
                    ghi_chu
                )
                VALUES
                (
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
                    phieuXuat.getNgayXuat()
            );

            ps.setString(
                    2,
                    phieuXuat.getNguoiNhan()
            );

            ps.setString(
                    3,
                    phieuXuat.getGhiChu()
            );

            int rows = ps.executeUpdate();

            if (rows > 0) {

                ResultSet rs =
                        ps.getGeneratedKeys();

                if (rs.next()) {

                    return rs.getInt(1);

                }

            }

        }

        catch (Exception e) {

            e.printStackTrace();

        }

        return -1;

    }


    // Thêm chi tiết phiếu xuất
    public boolean insertChiTiet(
            ChiTietPhieuXuat ct
    ) {
        String sql = """
                INSERT INTO chi_tiet_phieu_xuat
                (
                    ma_phieu_xuat,
                    ma_san_pham,
                    ma_kho,
                    so_luong
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
                        conn.prepareStatement(sql);
        ) {

            ps.setInt(
                    1,
                    ct.getMaPhieuXuat()
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

            return ps.executeUpdate() > 0;

        }

        catch (Exception e) {

            e.printStackTrace();

        }

        return false;

    }


    // Danh sách phiếu xuất

    public List<PhieuXuat> getAll() {

        List<PhieuXuat> ds =
                new ArrayList<>();

        String sql = """
                SELECT *
                FROM phieu_xuat
                ORDER BY ma_phieu_xuat DESC
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

                PhieuXuat px =
                        new PhieuXuat();

                px.setMaPhieuXuat(
                        rs.getInt("ma_phieu_xuat"));

                px.setNgayXuat(
                        rs.getDate("ngay_xuat"));

                px.setNguoiNhan(
                        rs.getString("nguoi_nhan"));

                px.setGhiChu(
                        rs.getString("ghi_chu"));

                ds.add(px);

            }

        }

        catch (Exception e) {

            e.printStackTrace();

        }

        return ds;

    }

    // Tìm theo mã
    public PhieuXuat findById(
            int maPhieuXuat
    ) {

        String sql = """
                SELECT *
                FROM phieu_xuat
                WHERE ma_phieu_xuat = ?
                """;

        try (

                Connection conn =
                        KetNoiCSDL.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql);

        ) {

            ps.setInt(
                    1,
                    maPhieuXuat
            );

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                PhieuXuat px =
                        new PhieuXuat();

                px.setMaPhieuXuat(
                        rs.getInt("ma_phieu_xuat"));

                px.setNgayXuat(
                        rs.getDate("ngay_xuat"));

                px.setNguoiNhan(
                        rs.getString("nguoi_nhan"));

                px.setGhiChu(
                        rs.getString("ghi_chu"));

                return px;

            }

        }

        catch (Exception e) {

            e.printStackTrace();

        }

        return null;

    }
    // ===============================
    // Lưu phiếu xuất (Transaction)
    // ===============================

    public boolean luuPhieuXuat(
            PhieuXuat phieuXuat,
            List<ChiTietPhieuXuat> dsChiTiet
    ) {

        Connection conn = null;

        try {

            conn = KetNoiCSDL.getConnection();

            conn.setAutoCommit(false);

            // -------------------------
            // Lưu phiếu xuất
            // -------------------------

            String sqlPhieu = """
                    INSERT INTO phieu_xuat
                    (
                        ngay_xuat,
                        nguoi_nhan,
                        ghi_chu
                    )
                    VALUES
                    (
                        ?,
                        ?,
                        ?
                    )
                    """;

            PreparedStatement psPhieu =
                    conn.prepareStatement(
                            sqlPhieu,
                            Statement.RETURN_GENERATED_KEYS
                    );

            psPhieu.setDate(
                    1,
                    phieuXuat.getNgayXuat()
            );

            psPhieu.setString(
                    2,
                    phieuXuat.getNguoiNhan()
            );

            psPhieu.setString(
                    3,
                    phieuXuat.getGhiChu()
            );

            psPhieu.executeUpdate();

            ResultSet rs =
                    psPhieu.getGeneratedKeys();

            int maPhieuXuat = 0;

            if (rs.next()) {

                maPhieuXuat = rs.getInt(1);

            }

            // -------------------------
            // Lưu chi tiết
            // -------------------------

            String sqlChiTiet = """
                    INSERT INTO chi_tiet_phieu_xuat
                    (
                        ma_phieu_xuat,
                        ma_san_pham,
                        ma_kho,
                        so_luong
                    )
                    VALUES
                    (
                        ?,
                        ?,
                        ?,
                        ?
                    )
                    """;

            PreparedStatement psCT =
                    conn.prepareStatement(sqlChiTiet);

            for (ChiTietPhieuXuat ct : dsChiTiet) {

                if (!kiemTraTonKho(
                        conn,
                        ct.getMaSanPham(),
                        ct.getMaKho(),
                        ct.getSoLuong()
                )) {

                    throw new SQLException(
                            "Sản phẩm "
                                    + ct.getMaSanPham()
                                    + " không đủ tồn kho."
                    );

                }

                psCT.setInt(
                        1,
                        maPhieuXuat
                );

                psCT.setInt(
                        2,
                        ct.getMaSanPham()
                );

                psCT.setInt(
                        3,
                        ct.getMaKho()
                );

                psCT.setInt(
                        4,
                        ct.getSoLuong()
                );

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

        }

        catch (Exception e) {

            try {

                if (conn != null) {

                    conn.rollback();

                }

            }

            catch (Exception ex) {

                ex.printStackTrace();

            }

            e.printStackTrace();

        }

        finally {

            try {

                if (conn != null) {

                    conn.setAutoCommit(true);

                    conn.close();

                }

            }

            catch (Exception e) {

                e.printStackTrace();

            }

        }

        return false;

    }

    // ===============================
    // Kiểm tra tồn kho
    // ===============================

    private boolean kiemTraTonKho(
            Connection conn,
            int maSanPham,
            int maKho,
            int soLuongCanXuat
    ) throws SQLException {

        String sql = """
                SELECT so_luong
                FROM ton_kho
                WHERE ma_san_pham = ?
                AND ma_kho = ?
                """;

        PreparedStatement ps =
                conn.prepareStatement(sql);

        ps.setInt(
                1,
                maSanPham
        );

        ps.setInt(
                2,
                maKho
        );

        ResultSet rs =
                ps.executeQuery();

        if (rs.next()) {

            return rs.getInt("so_luong")
                    >= soLuongCanXuat;

        }

        return false;

    }

    // ===============================
    // Trừ tồn kho
    // ===============================

    private void capNhatTonKho(
            Connection conn,
            int maSanPham,
            int maKho,
            int soLuongXuat
    ) throws SQLException {

        String sql = """
                UPDATE ton_kho
                SET so_luong = so_luong - ?
                WHERE ma_san_pham = ?
                AND ma_kho = ?
                """;

        PreparedStatement ps =
                conn.prepareStatement(sql);

        ps.setInt(
                1,
                soLuongXuat
        );

        ps.setInt(
                2,
                maSanPham
        );

        ps.setInt(
                3,
                maKho
        );

        ps.executeUpdate();

    }

    // ===============================
    // Lấy chi tiết phiếu xuất
    // ===============================

    public List<ChiTietPhieuXuat> getChiTietPhieuXuat(
            int maPhieuXuat
    ) {

        List<ChiTietPhieuXuat> ds =
                new ArrayList<>();

        String sql = """
                SELECT *
                FROM chi_tiet_phieu_xuat
                WHERE ma_phieu_xuat = ?
                ORDER BY ma_chi_tiet
                """;

        try (

                Connection conn =
                        KetNoiCSDL.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql);

        ) {

            ps.setInt(
                    1,
                    maPhieuXuat
            );

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                ChiTietPhieuXuat ct =
                        new ChiTietPhieuXuat();

                ct.setMaChiTiet(
                        rs.getInt("ma_chi_tiet"));

                ct.setMaPhieuXuat(
                        rs.getInt("ma_phieu_xuat"));

                ct.setMaSanPham(
                        rs.getInt("ma_san_pham"));

                ct.setMaKho(
                        rs.getInt("ma_kho"));

                ct.setSoLuong(
                        rs.getInt("so_luong"));

                ds.add(ct);

            }

        }

        catch (Exception e) {

            e.printStackTrace();

        }

        return ds;

    }

    // ===============================
    // Xóa phiếu xuất (Transaction)
    // ===============================

    public boolean deletePhieuXuat(
            int maPhieuXuat
    ) {

        Connection conn = null;

        try {

            conn = KetNoiCSDL.getConnection();

            conn.setAutoCommit(false);

            // ------------------------------------
            // Lấy chi tiết để cộng lại tồn kho
            // ------------------------------------

            List<ChiTietPhieuXuat> ds =
                    getChiTietPhieuXuat(maPhieuXuat);

            String sqlCongTon = """
                    UPDATE ton_kho
                    SET so_luong = so_luong + ?
                    WHERE ma_san_pham = ?
                    AND ma_kho = ?
                    """;

            PreparedStatement psTon =
                    conn.prepareStatement(sqlCongTon);

            for (ChiTietPhieuXuat ct : ds) {

                psTon.setInt(
                        1,
                        ct.getSoLuong()
                );

                psTon.setInt(
                        2,
                        ct.getMaSanPham()
                );

                psTon.setInt(
                        3,
                        ct.getMaKho()
                );

                psTon.executeUpdate();

            }

            // ------------------------------------
            // Xóa chi tiết
            // ------------------------------------

            PreparedStatement psCT =
                    conn.prepareStatement("""
                            DELETE FROM chi_tiet_phieu_xuat
                            WHERE ma_phieu_xuat = ?
                            """);

            psCT.setInt(
                    1,
                    maPhieuXuat
            );

            psCT.executeUpdate();

            // ------------------------------------
            // Xóa phiếu
            // ------------------------------------

            PreparedStatement psPX =
                    conn.prepareStatement("""
                            DELETE FROM phieu_xuat
                            WHERE ma_phieu_xuat = ?
                            """);

            psPX.setInt(
                    1,
                    maPhieuXuat
            );

            boolean ok =
                    psPX.executeUpdate() > 0;

            conn.commit();

            return ok;

        }

        catch (Exception e) {

            try {

                if (conn != null) {

                    conn.rollback();

                }

            }

            catch (Exception ex) {

                ex.printStackTrace();

            }

            e.printStackTrace();

        }

        finally {

            try {

                if (conn != null) {

                    conn.setAutoCommit(true);

                    conn.close();

                }

            }

            catch (Exception e) {

                e.printStackTrace();

            }

        }

        return false;

    }


    public int laySoLuongTon(int maKho, int maSanPham) {

        String sql = "SELECT so_luong FROM ton_kho WHERE ma_kho=? AND ma_san_pham=?";

        try (Connection con = KetNoiCSDL.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, maKho);
            ps.setInt(2, maSanPham);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("so_luong");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public boolean giamTonKho(int maKho, int maSanPham, int soLuong) {

        String sql = """
            UPDATE ton_kho
            SET so_luong = so_luong - ?
            WHERE ma_kho = ?
            AND ma_san_pham = ?
            """;

        try (Connection con = KetNoiCSDL.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, soLuong);
            ps.setInt(2, maKho);
            ps.setInt(3, maSanPham);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

}
