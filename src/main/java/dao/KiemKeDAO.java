package dao;

import ketnoi.KetNoiCSDL;
import model.ChiTietKiemKe;
import model.KiemKe;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KiemKeDAO {

    // ==========================================
    // Tạo phiếu kiểm kê
    // ==========================================

    public int taoPhieuKiemKe(KiemKe kiemKe) {

        String sql =
                """
                INSERT INTO kiem_ke
                (
                    ma_kho,
                    ngay_kiem_ke,
                    nguoi_kiem_ke,
                    ghi_chu
                )
                VALUES
                (
                    ?,
                    NOW(),
                    ?,
                    ?
                )
                """;

        try (
                Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps =
                        conn.prepareStatement(
                                sql,
                                Statement.RETURN_GENERATED_KEYS
                        )
        ) {

            ps.setInt(
                    1,
                    kiemKe.getMaKho()
            );

            ps.setString(
                    2,
                    kiemKe.getNguoiKiemKe()
            );

            ps.setString(
                    3,
                    kiemKe.getGhiChu()
            );

            int ketQua = ps.executeUpdate();

            if (ketQua > 0) {

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

    // ==========================================
    // Thêm chi tiết kiểm kê
    // ==========================================

    public boolean themChiTietKiemKe(
            ChiTietKiemKe chiTiet
    ) {

        String sql =
                """
                INSERT INTO chi_tiet_kiem_ke
                (
                    ma_kiem_ke,
                    ma_san_pham,
                    ton_he_thong,
                    ton_thuc_te,
                    chenh_lech
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
                Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setInt(
                    1,
                    chiTiet.getMaKiemKe()
            );

            ps.setInt(
                    2,
                    chiTiet.getMaSanPham()
            );

            ps.setInt(
                    3,
                    chiTiet.getTonHeThong()
            );

            ps.setInt(
                    4,
                    chiTiet.getTonThucTe()
            );

            ps.setInt(
                    5,
                    chiTiet.getChenhLech()
            );

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;

    }

    // ==========================================
    // Lấy danh sách phiếu kiểm kê
    // ==========================================

    public List<KiemKe> layTatCaPhieuKiemKe() {

        List<KiemKe> ds = new ArrayList<>();

        String sql =
                """
                SELECT
                    kk.ma_kiem_ke,
                    kk.ma_kho,
                    kk.ngay_kiem_ke,
                    kk.nguoi_kiem_ke,
                    kk.ghi_chu,
                    k.ten_kho
                FROM kiem_ke kk
                INNER JOIN kho k
                    ON kk.ma_kho = k.ma_kho
                ORDER BY kk.ma_kiem_ke DESC
                """;

        try (
                Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                KiemKe kk = new KiemKe();

                kk.setMaKiemKe(
                        rs.getInt("ma_kiem_ke")
                );

                kk.setMaKho(
                        rs.getInt("ma_kho")
                );

                kk.setNgayKiemKe(
                        rs.getTimestamp("ngay_kiem_ke")
                );

                kk.setNguoiKiemKe(
                        rs.getString("nguoi_kiem_ke")
                );

                kk.setGhiChu(
                        rs.getString("ghi_chu")
                );

                kk.setTenKho(
                        rs.getString("ten_kho")
                );

                ds.add(kk);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return ds;

    }

    // ==========================================
    // Lấy chi tiết kiểm kê
    // ==========================================

    public List<ChiTietKiemKe> layChiTietKiemKe(
            int maKiemKe
    ) {

        List<ChiTietKiemKe> ds = new ArrayList<>();

        String sql =
                """
                SELECT
                    ct.*,
                    sp.ten_san_pham,
                    sp.gia
                FROM chi_tiet_kiem_ke ct
                INNER JOIN san_pham sp
                    ON ct.ma_san_pham =
                       sp.ma_san_pham
                WHERE ct.ma_kiem_ke=?
                ORDER BY sp.ten_san_pham
                """;

        try (
                Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setInt(
                    1,
                    maKiemKe
            );

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                ChiTietKiemKe ct =
                        new ChiTietKiemKe();

                ct.setMaChiTiet(
                        rs.getInt("ma_chi_tiet")
                );

                ct.setMaKiemKe(
                        rs.getInt("ma_kiem_ke")
                );

                ct.setMaSanPham(
                        rs.getInt("ma_san_pham")
                );

                ct.setTonHeThong(
                        rs.getInt("ton_he_thong")
                );

                ct.setTonThucTe(
                        rs.getInt("ton_thuc_te")
                );

                ct.setChenhLech(
                        rs.getInt("chenh_lech")
                );

                ct.setTenSanPham(
                        rs.getString("ten_san_pham")
                );

                ct.setGia(
                        rs.getDouble("gia")
                );

                ds.add(ct);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return ds;

    }

    // ==========================================
    // Tìm phiếu kiểm kê theo mã
    // ==========================================

    public KiemKe findById(
            int maKiemKe
    ) {

        String sql =
                """
                SELECT
                    kk.*,
                    k.ten_kho
                FROM kiem_ke kk
                INNER JOIN kho k
                    ON kk.ma_kho=k.ma_kho
                WHERE kk.ma_kiem_ke=?
                """;

        try (
                Connection conn =
                        KetNoiCSDL.getConnection();
                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setInt(
                    1,
                    maKiemKe
            );

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                KiemKe kk =
                        new KiemKe();

                kk.setMaKiemKe(
                        rs.getInt("ma_kiem_ke")
                );

                kk.setMaKho(
                        rs.getInt("ma_kho")
                );

                kk.setNgayKiemKe(
                        rs.getTimestamp("ngay_kiem_ke")
                );

                kk.setNguoiKiemKe(
                        rs.getString("nguoi_kiem_ke")
                );

                kk.setGhiChu(
                        rs.getString("ghi_chu")
                );

                kk.setTenKho(
                        rs.getString("ten_kho")
                );

                return kk;

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;

    }
    // ==========================================
    // Cập nhật tồn kho sau kiểm kê
    // ==========================================

    public boolean capNhatTonKhoSauKiemKe(
            int maSanPham,
            int maKho,
            int tonMoi
    ) {

        String sql =
                """
                UPDATE ton_kho
                SET so_luong=?
                WHERE ma_san_pham=?
                AND ma_kho=?
                """;

        try (
                Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setInt(1, tonMoi);
            ps.setInt(2, maSanPham);
            ps.setInt(3, maKho);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;

    }

    // ==========================================
    // Xóa phiếu kiểm kê
    // ==========================================

    public boolean xoaPhieuKiemKe(
            int maKiemKe
    ) {

        String sql =
                """
                DELETE
                FROM kiem_ke
                WHERE ma_kiem_ke=?
                """;

        try (
                Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setInt(1, maKiemKe);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;

    }

    // ==========================================
    // Tìm kiếm phiếu kiểm kê
    // ==========================================

    public List<KiemKe> timKiemPhieuKiemKe(
            String tuKhoa
    ) {

        List<KiemKe> ds = new ArrayList<>();

        String sql =
                """
                SELECT
                    kk.*,
                    k.ten_kho
                FROM kiem_ke kk
                INNER JOIN kho k
                    ON kk.ma_kho=k.ma_kho
                WHERE
                    k.ten_kho LIKE ?
                    OR kk.nguoi_kiem_ke LIKE ?
                ORDER BY kk.ma_kiem_ke DESC
                """;

        try (
                Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            String key = "%" + tuKhoa + "%";

            ps.setString(1, key);
            ps.setString(2, key);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                KiemKe kk = new KiemKe();

                kk.setMaKiemKe(
                        rs.getInt("ma_kiem_ke")
                );

                kk.setMaKho(
                        rs.getInt("ma_kho")
                );

                kk.setNgayKiemKe(
                        rs.getTimestamp("ngay_kiem_ke")
                );

                kk.setNguoiKiemKe(
                        rs.getString("nguoi_kiem_ke")
                );

                kk.setGhiChu(
                        rs.getString("ghi_chu")
                );

                kk.setTenKho(
                        rs.getString("ten_kho")
                );

                ds.add(kk);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return ds;

    }

    // ==========================================
    // Tổng số phiếu kiểm kê
    // ==========================================

    public int tongSoPhieuKiemKe() {

        String sql =
                """
                SELECT COUNT(*)
                FROM kiem_ke
                """;

        try (
                Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps =
                        conn.prepareStatement(sql);
                ResultSet rs =
                        ps.executeQuery()
        ) {

            if (rs.next()) {

                return rs.getInt(1);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return 0;

    }

    // ==========================================
    // Tổng số sản phẩm đã kiểm kê
    // ==========================================

    public int tongSanPhamDaKiemKe() {

        String sql =
                """
                SELECT COUNT(*)
                FROM chi_tiet_kiem_ke
                """;

        try (
                Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps =
                        conn.prepareStatement(sql);
                ResultSet rs =
                        ps.executeQuery()
        ) {

            if (rs.next()) {

                return rs.getInt(1);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return 0;

    }

    // ==========================================
    // Tổng chênh lệch tồn kho
    // ==========================================

    public int tongChenhLechTonKho() {

        String sql =
                """
                SELECT IFNULL(SUM(ABS(chenh_lech)),0)
                FROM chi_tiet_kiem_ke
                """;

        try (
                Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps =
                        conn.prepareStatement(sql);
                ResultSet rs =
                        ps.executeQuery()
        ) {

            if (rs.next()) {

                return rs.getInt(1);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return 0;

    }

}