package dao;

import ketnoi.KetNoiCSDL;
import model.Kho;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KhoDAO {

    // ===========================
    // Lấy danh sách kho

    public List<Kho> getAll() {

        List<Kho> dsKho = new ArrayList<>();

        String sql = """
                SELECT *
                FROM kho
                ORDER BY ma_kho DESC
                """;

        try (
                Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                Kho kho = new Kho();

                kho.setMaKho(rs.getInt("ma_kho"));
                kho.setTenKho(rs.getString("ten_kho"));
                kho.setDiaChi(rs.getString("dia_chi"));
                kho.setNguoiQuanLy(rs.getString("nguoi_quan_ly"));
                kho.setSoDienThoai(rs.getString("so_dien_thoai"));
                kho.setEmail(rs.getString("email"));
                kho.setGhiChu(rs.getString("ghi_chu"));
                dsKho.add(kho);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dsKho;
    }

    // ===========================
    // Tìm theo mã
    // ===========================
    public Kho findById(int maKho) {

        String sql = """
                SELECT *
                FROM kho
                WHERE ma_kho = ?
                """;

        try (
                Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, maKho);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Kho kho = new Kho();

                kho.setMaKho(rs.getInt("ma_kho"));
                kho.setTenKho(rs.getString("ten_kho"));
                kho.setDiaChi(rs.getString("dia_chi"));
                kho.setNguoiQuanLy(rs.getString("nguoi_quan_ly"));
                kho.setSoDienThoai(rs.getString("so_dien_thoai"));
                kho.setEmail(rs.getString("email"));
                kho.setGhiChu(rs.getString("ghi_chu"));
                return kho;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // ===========================
    // Thêm kho
    // ===========================
    public boolean insert(Kho kho) {

        String sql = """
                INSERT INTO kho
                                            (
                                                ten_kho,
                                                dia_chi,
                                                nguoi_quan_ly,
                                                so_dien_thoai,
                                                email,
                                                ghi_chu
                                            )
                                            VALUES
                                            (?,?,?,?,?,?)
                                            """;

        try (
                Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, kho.getTenKho());
            ps.setString(2, kho.getDiaChi());
            ps.setString(3, kho.getNguoiQuanLy());
            ps.setString(4, kho.getSoDienThoai());
            ps.setString(5, kho.getEmail());
            ps.setString(6, kho.getGhiChu());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }

    // ===========================
    // Cập nhật kho
    // ===========================
    public boolean update(Kho kho) {

        String sql = """
                UPDATE kho
                                            SET
                                                ten_kho=?,
                                                dia_chi=?,
                                                nguoi_quan_ly=?,
                                                so_dien_thoai=?,
                                                email=?,
                                                ghi_chu=?
                                            WHERE ma_kho=?
                                            """;

        try (
                Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, kho.getTenKho());
            ps.setString(2, kho.getDiaChi());
            ps.setString(3, kho.getNguoiQuanLy());
            ps.setString(4, kho.getSoDienThoai());
            ps.setString(5, kho.getEmail());
            ps.setString(6, kho.getGhiChu());
            ps.setInt(7, kho.getMaKho());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }

    // ===========================
    // Xóa kho
    // ===========================
    public boolean delete(int maKho) {

        String sql = """
                DELETE
                FROM kho
                WHERE ma_kho = ?
                """;

        try (
                Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, maKho);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return false;
    }

    // ===========================
    // Tìm kiếm
    // ===========================
    public List<Kho> search(String keyword) {

        List<Kho> dsKho = new ArrayList<>();

        String sql = """
                SELECT *
                FROM kho
                WHERE
                    ten_kho LIKE ?
                    OR dia_chi LIKE ?
                    OR nguoi_quan_ly LIKE ?
                ORDER BY ma_kho DESC
                """;

        try (
                Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            String key = "%" + keyword + "%";

            ps.setString(1, key);
            ps.setString(2, key);
            ps.setString(3, key);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Kho kho = new Kho();

                kho.setMaKho(rs.getInt("ma_kho"));
                kho.setTenKho(rs.getString("ten_kho"));
                kho.setDiaChi(rs.getString("dia_chi"));
                kho.setNguoiQuanLy(rs.getString("nguoi_quan_ly"));

                dsKho.add(kho);

            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return dsKho;
    }

    // ===========================
    // Kiểm tra kho tồn tại
    // ===========================
    public boolean tonTai(int maKho) {

        String sql = "SELECT COUNT(*) FROM kho WHERE ma_kho=?";

        try (
                Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, maKho);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // ===========================
    // Tổng số kho
    // ===========================
    public int demSoKho() {

        String sql = """
                SELECT COUNT(*)
                FROM kho
                """;

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

    // Đếm tổng sản phẩm trong tất cả các kho
    public int tongSoLuongTon() {

        String sql = "SELECT IFNULL(SUM(so_luong),0) FROM ton_kho";

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

    // Đếm số sản phẩm sắp hết
    public int sanPhamSapHet() {

        String sql =
                """
                SELECT COUNT(*)
                FROM ton_kho
                WHERE so_luong < 10
                """;

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

}