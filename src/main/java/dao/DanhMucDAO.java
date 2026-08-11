package dao;

import ketnoi.KetNoiCSDL;
import model.DanhMuc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DanhMucDAO {

    // ==========================
    // Lấy tất cả danh mục
    // ==========================

    public List<DanhMuc> getAll() {

        List<DanhMuc> danhSach = new ArrayList<>();

        String sql = """
                SELECT *
                FROM danh_muc
                ORDER BY ma_danh_muc DESC
                """;

        try (
                Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                DanhMuc dm = new DanhMuc();

                dm.setMaDanhMuc(
                        rs.getInt("ma_danh_muc")
                );

                dm.setTenDanhMuc(
                        rs.getString("ten_danh_muc")
                );

                dm.setMoTa(
                        rs.getString("mo_ta")
                );

                danhSach.add(dm);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return danhSach;
    }


    // ==========================
    // Lấy danh mục theo mã
    // ==========================

    public DanhMuc getById(int maDanhMuc) {

        DanhMuc dm = null;

        String sql = """
                SELECT *
                FROM danh_muc
                WHERE ma_danh_muc=?
                """;

        try (
                Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, maDanhMuc);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    dm = new DanhMuc();

                    dm.setMaDanhMuc(
                            rs.getInt("ma_danh_muc")
                    );

                    dm.setTenDanhMuc(
                            rs.getString("ten_danh_muc")
                    );

                    dm.setMoTa(
                            rs.getString("mo_ta")
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dm;
    }


    // ==========================
    // Thêm danh mục
    // ==========================

    public boolean them(DanhMuc dm) {

        String sql = """
                INSERT INTO danh_muc
                (
                    ten_danh_muc,
                    mo_ta
                )
                VALUES (?, ?)
                """;

        try (
                Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(
                    1,
                    dm.getTenDanhMuc()
            );

            ps.setString(
                    2,
                    dm.getMoTa()
            );

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    // ==========================
    // Sửa danh mục
    // ==========================

    public boolean sua(DanhMuc dm) {

        String sql = """
                UPDATE danh_muc
                SET
                    ten_danh_muc=?,
                    mo_ta=?
                WHERE ma_danh_muc=?
                """;

        try (
                Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(
                    1,
                    dm.getTenDanhMuc()
            );

            ps.setString(
                    2,
                    dm.getMoTa()
            );

            ps.setInt(
                    3,
                    dm.getMaDanhMuc()
            );

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    // ==========================
    // Xóa danh mục
    // ==========================

    public boolean xoa(int maDanhMuc) {

        String sql = """
                DELETE FROM danh_muc
                WHERE ma_danh_muc=?
                """;

        try (
                Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, maDanhMuc);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    // ==========================
    // Kiểm tra tên danh mục tồn tại
    // ==========================

    public boolean tonTai(String tenDanhMuc) {

        String sql = """
                SELECT COUNT(*)
                FROM danh_muc
                WHERE ten_danh_muc=?
                """;

        try (
                Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, tenDanhMuc);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    // ==========================
    // Kiểm tra tên khi cập nhật
    // ==========================

    public boolean existsExceptId(
            String tenDanhMuc,
            int maDanhMuc
    ) {

        String sql = """
                SELECT COUNT(*)
                FROM danh_muc
                WHERE ten_danh_muc=?
                AND ma_danh_muc<>?
                """;

        try (
                Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, tenDanhMuc);
            ps.setInt(2, maDanhMuc);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    // ==========================
    // Kiểm tra danh mục có sản phẩm
    // ==========================

    public boolean hasProduct(int maDanhMuc) {

        String sql = """
                SELECT COUNT(*)
                FROM san_pham
                WHERE ma_danh_muc=?
                """;

        try (
                Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, maDanhMuc);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    // ==========================
    // Tìm kiếm danh mục
    // ==========================

    public List<DanhMuc> timKiemDanhMuc(
            String tuKhoa
    ) {

        List<DanhMuc> danhSach =
                new ArrayList<>();

        String sql = """
                SELECT *
                FROM danh_muc
                WHERE ten_danh_muc LIKE ?
                ORDER BY ma_danh_muc DESC
                """;

        try (
                Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(
                    1,
                    "%" + tuKhoa + "%"
            );

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    DanhMuc dm = new DanhMuc();

                    dm.setMaDanhMuc(
                            rs.getInt("ma_danh_muc")
                    );

                    dm.setTenDanhMuc(
                            rs.getString("ten_danh_muc")
                    );

                    dm.setMoTa(
                            rs.getString("mo_ta")
                    );

                    danhSach.add(dm);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return danhSach;
    }
}