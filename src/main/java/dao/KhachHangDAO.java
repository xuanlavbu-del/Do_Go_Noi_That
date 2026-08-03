package dao;

import ketnoi.KetNoiCSDL;
import model.KhachHang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class KhachHangDAO {

    // ===============================
    // Lấy tất cả khách hàng
    // ===============================

    public List<KhachHang> layTatCaKhachHang() {

        List<KhachHang> danhSach =
                new ArrayList<>();

        String sql =
                "SELECT * FROM KhachHang ORDER BY maKhachHang DESC";

        try (
                Connection conn =
                        KetNoiCSDL.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql);

                ResultSet rs =
                        ps.executeQuery()
        ) {

            while (rs.next()) {

                KhachHang kh =
                        new KhachHang();

                kh.setMaKhachHang(
                        rs.getInt("maKhachHang")
                );

                kh.setHoTen(
                        rs.getString("hoTen")
                );

                kh.setGioiTinh(
                        rs.getString("gioiTinh")
                );

                kh.setNgaySinh(
                        rs.getDate("ngaySinh")
                );

                kh.setSoDienThoai(
                        rs.getString("soDienThoai")
                );

                kh.setEmail(
                        rs.getString("email")
                );

                kh.setDiaChi(
                        rs.getString("diaChi")
                );

                kh.setNgayTao(
                        rs.getTimestamp("ngayTao")
                );

                danhSach.add(kh);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return danhSach;

    }



    // ===============================
    // Lấy khách hàng theo mã
    // ===============================

    public KhachHang layKhachHangTheoMa(int maKhachHang) {

        KhachHang khachHang = null;

        String sql =
                "SELECT * FROM KhachHang WHERE maKhachHang=?";

        try (

                Connection conn =
                        KetNoiCSDL.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql)

        ) {

            ps.setInt(
                    1,
                    maKhachHang
            );

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                khachHang =
                        new KhachHang();

                khachHang.setMaKhachHang(
                        rs.getInt("maKhachHang")
                );

                khachHang.setHoTen(
                        rs.getString("hoTen")
                );

                khachHang.setGioiTinh(
                        rs.getString("gioiTinh")
                );

                khachHang.setNgaySinh(
                        rs.getDate("ngaySinh")
                );

                khachHang.setSoDienThoai(
                        rs.getString("soDienThoai")
                );

                khachHang.setEmail(
                        rs.getString("email")
                );

                khachHang.setDiaChi(
                        rs.getString("diaChi")
                );


                khachHang.setNgayTao(
                        rs.getTimestamp("ngayTao")
                );

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return khachHang;

    }

    // ===============================
    // Thêm khách hàng
    // ===============================

    public boolean themKhachHang(KhachHang khachHang) {

        String sql =
                """
                INSERT INTO KhachHang
                (
                    hoTen,
                    gioiTinh,
                    ngaySinh,
                    soDienThoai,
                    email,
                    diaChi
                )
                VALUES(?,?,?,?,?,?)
                """;

        try (
                Connection conn =
                        KetNoiCSDL.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql)

        ) {

            ps.setString(
                    1,
                    khachHang.getHoTen()
            );

            ps.setString(
                    2,
                    khachHang.getGioiTinh()
            );

            ps.setDate(
                    3,
                    khachHang.getNgaySinh()
            );

            ps.setString(
                    4,
                    khachHang.getSoDienThoai()
            );

            ps.setString(
                    5,
                    khachHang.getEmail()
            );

            ps.setString(
                    6,
                    khachHang.getDiaChi()
            );


            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;

    }





    // ===============================
    // Cập nhật khách hàng
    // ===============================

    public boolean capNhatKhachHang(KhachHang khachHang) {

        String sql =
                """
                UPDATE KhachHang
                SET
                    hoTen=?,
                    gioiTinh=?,
                    ngaySinh=?,
                    soDienThoai=?,
                    email=?,
                    diaChi=?
                WHERE maKhachHang=?
                """;

        try (
                Connection conn =
                        KetNoiCSDL.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql)

        ) {

            ps.setString(
                    1,
                    khachHang.getHoTen()
            );

            ps.setString(
                    2,
                    khachHang.getGioiTinh()
            );

            ps.setDate(
                    3,
                    khachHang.getNgaySinh()
            );

            ps.setString(
                    4,
                    khachHang.getSoDienThoai()
            );

            ps.setString(
                    5,
                    khachHang.getEmail()
            );

            ps.setString(
                    6,
                    khachHang.getDiaChi()
            );


            ps.setInt(
                    7,
                    khachHang.getMaKhachHang()
            );

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;

    }
    // ===============================
    // Xóa khách hàng
    // ===============================

    public boolean xoaKhachHang(int maKhachHang) {

        String sql =
                "DELETE FROM KhachHang WHERE maKhachHang=?";

        try (
                Connection conn =
                        KetNoiCSDL.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql)

        ) {

            ps.setInt(
                    1,
                    maKhachHang
            );

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;

    }





    // ===============================
    // Tìm kiếm khách hàng
    // ===============================

    public List<KhachHang> timKiemKhachHang(String tuKhoa) {

        List<KhachHang> danhSach =
                new ArrayList<>();

        String sql =
                """
                SELECT *
                FROM KhachHang
                WHERE hoTen LIKE ?
                   OR soDienThoai LIKE ?
                   OR email LIKE ?
                ORDER BY maKhachHang DESC
                """;

        try (
                Connection conn =
                        KetNoiCSDL.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql)

        ) {

            String timKiem = "%" + tuKhoa + "%";

            ps.setString(1, timKiem);
            ps.setString(2, timKiem);
            ps.setString(3, timKiem);

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                KhachHang kh =
                        new KhachHang();

                kh.setMaKhachHang(
                        rs.getInt("maKhachHang")
                );

                kh.setHoTen(
                        rs.getString("hoTen")
                );

                kh.setGioiTinh(
                        rs.getString("gioiTinh")
                );

                kh.setNgaySinh(
                        rs.getDate("ngaySinh")
                );

                kh.setSoDienThoai(
                        rs.getString("soDienThoai")
                );

                kh.setEmail(
                        rs.getString("email")
                );

                kh.setDiaChi(
                        rs.getString("diaChi")
                );


                kh.setNgayTao(
                        rs.getTimestamp("ngayTao")
                );

                danhSach.add(kh);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return danhSach;

    }





    // ===============================
    // Kiểm tra trùng số điện thoại
    // ===============================

    public boolean kiemTraTrungSoDienThoai(String soDienThoai) {

        String sql =
                "SELECT * FROM KhachHang WHERE soDienThoai=?";

        try (
                Connection conn =
                        KetNoiCSDL.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql)

        ) {

            ps.setString(
                    1,
                    soDienThoai
            );

            ResultSet rs =
                    ps.executeQuery();

            return rs.next();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;

    }





    // ===============================
    // Kiểm tra trùng email
    // ===============================

    public boolean kiemTraTrungEmail(String email) {

        String sql =
                "SELECT * FROM KhachHang WHERE email=?";

        try (
                Connection conn =
                        KetNoiCSDL.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql)

        ) {

            ps.setString(
                    1,
                    email
            );

            ResultSet rs =
                    ps.executeQuery();

            return rs.next();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;

    }





    // ===============================
    // Kiểm tra khách hàng tồn tại
    // ===============================

    public boolean kiemTraTonTai(int maKhachHang) {

        String sql =
                "SELECT * FROM KhachHang WHERE maKhachHang=?";

        try (
                Connection conn =
                        KetNoiCSDL.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql)

        ) {

            ps.setInt(
                    1,
                    maKhachHang
            );

            ResultSet rs =
                    ps.executeQuery();

            return rs.next();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;

    }

}