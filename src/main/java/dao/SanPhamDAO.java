package dao;

import ketnoi.KetNoiCSDL;
import model.SanPham;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SanPhamDAO {


    // ===============================
    // Lấy tất cả sản phẩm
    // ===============================

    public List<SanPham> layTatCaSanPham() {

        List<SanPham> danhSach = new ArrayList<>();

        String sql = "SELECT * FROM san_pham";

        try (
                Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                SanPham sanPham = new SanPham();

                sanPham.setMaSanPham(
                        rs.getInt("ma_san_pham")
                );

                sanPham.setTenSanPham(
                        rs.getString("ten_san_pham")
                );

                sanPham.setGia(
                        rs.getDouble("gia")
                );

                sanPham.setSoLuong(
                        rs.getInt("so_luong")
                );

                sanPham.setMoTa(
                        rs.getString("mo_ta")
                );

                sanPham.setHinhAnh(
                        rs.getString("hinh_anh")
                );

                sanPham.setMaDanhMuc(
                        rs.getInt("ma_danh_muc")
                );


                danhSach.add(sanPham);
            }


        } catch (Exception e) {

            e.printStackTrace();

        }

        return danhSach;
    }



    // ===============================
    // Lấy sản phẩm theo mã
    // ===============================

    public SanPham laySanPhamTheoMa(int maSanPham) {


        SanPham sanPham = null;


        String sql =
                "SELECT * FROM san_pham WHERE ma_san_pham=?";


        try (
                Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)

        ) {


            ps.setInt(1, maSanPham);


            ResultSet rs = ps.executeQuery();


            if (rs.next()) {


                sanPham = new SanPham();


                sanPham.setMaSanPham(
                        rs.getInt("ma_san_pham")
                );


                sanPham.setTenSanPham(
                        rs.getString("ten_san_pham")
                );


                sanPham.setGia(
                        rs.getDouble("gia")
                );


                sanPham.setSoLuong(
                        rs.getInt("so_luong")
                );


                sanPham.setMoTa(
                        rs.getString("mo_ta")
                );


                sanPham.setHinhAnh(
                        rs.getString("hinh_anh")
                );


                sanPham.setMaDanhMuc(
                        rs.getInt("ma_danh_muc")
                );

            }


        } catch (Exception e) {

            e.printStackTrace();

        }


        return sanPham;
    }




    // ===============================
    // Thêm sản phẩm
    // ===============================

    public boolean themSanPham(SanPham sanPham) {


        String sql =
                """
                INSERT INTO san_pham
                (
                    ten_san_pham,
                    gia,
                    so_luong,
                    mo_ta,
                    hinh_anh,
                    ma_danh_muc
                )
                VALUES(?,?,?,?,?,?)
                """;


        try (
                Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps =
                        conn.prepareStatement(sql)

        ) {


            ps.setString(
                    1,
                    sanPham.getTenSanPham()
            );


            ps.setDouble(
                    2,
                    sanPham.getGia()
            );


            ps.setInt(
                    3,
                    sanPham.getSoLuong()
            );


            ps.setString(
                    4,
                    sanPham.getMoTa()
            );


            ps.setString(
                    5,
                    sanPham.getHinhAnh()
            );


            ps.setInt(
                    6,
                    sanPham.getMaDanhMuc()
            );


            return ps.executeUpdate() > 0;


        } catch (Exception e) {

            e.printStackTrace();

        }


        return false;

    }





    // ===============================
    // Cập nhật sản phẩm
    // ===============================

    public boolean capNhatSanPham(SanPham sanPham) {


        String sql =
                """
                UPDATE san_pham
                SET
                    ten_san_pham=?,
                    gia=?,
                    so_luong=?,
                    mo_ta=?,
                    hinh_anh=?,
                    ma_danh_muc=?
                WHERE ma_san_pham=?
                """;


        try (
                Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps =
                        conn.prepareStatement(sql)

        ) {


            ps.setString(
                    1,
                    sanPham.getTenSanPham()
            );


            ps.setDouble(
                    2,
                    sanPham.getGia()
            );


            ps.setInt(
                    3,
                    sanPham.getSoLuong()
            );


            ps.setString(
                    4,
                    sanPham.getMoTa()
            );


            ps.setString(
                    5,
                    sanPham.getHinhAnh()
            );


            ps.setInt(
                    6,
                    sanPham.getMaDanhMuc()
            );


            ps.setInt(
                    7,
                    sanPham.getMaSanPham()
            );



            return ps.executeUpdate() > 0;


        } catch (Exception e) {

            e.printStackTrace();

        }


        return false;

    }





    // ===============================
    // Xóa sản phẩm
    // ===============================

    public boolean xoaSanPham(int maSanPham) {


        String sql =
                "DELETE FROM san_pham WHERE ma_san_pham=?";


        try (
                Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps =
                        conn.prepareStatement(sql)

        ) {


            ps.setInt(1, maSanPham);


            return ps.executeUpdate() > 0;


        } catch (Exception e) {

            e.printStackTrace();

        }


        return false;

    }





    // ===============================
    // Tìm kiếm sản phẩm
    // ===============================

    public List<SanPham> timKiemSanPham(String tuKhoa) {


        List<SanPham> danhSach =
                new ArrayList<>();


        String sql =
                """
                SELECT *
                FROM san_pham
                WHERE ten_san_pham LIKE ?
                """;



        try (
                Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps =
                        conn.prepareStatement(sql)

        ) {


            ps.setString(
                    1,
                    "%" + tuKhoa + "%"
            );


            ResultSet rs = ps.executeQuery();



            while (rs.next()) {


                SanPham sp = new SanPham();


                sp.setMaSanPham(
                        rs.getInt("ma_san_pham")
                );


                sp.setTenSanPham(
                        rs.getString("ten_san_pham")
                );


                sp.setGia(
                        rs.getDouble("gia")
                );


                sp.setSoLuong(
                        rs.getInt("so_luong")
                );


                sp.setMoTa(
                        rs.getString("mo_ta")
                );


                sp.setHinhAnh(
                        rs.getString("hinh_anh")
                );


                sp.setMaDanhMuc(
                        rs.getInt("ma_danh_muc")
                );


                danhSach.add(sp);

            }


        } catch(Exception e){

            e.printStackTrace();

        }


        return danhSach;

    }





    // ===============================
    // Lấy sản phẩm theo danh mục
    // ===============================

    public List<SanPham> laySanPhamTheoDanhMuc(int maDanhMuc) {


        List<SanPham> danhSach =
                new ArrayList<>();


        String sql =
                """
                SELECT *
                FROM san_pham
                WHERE ma_danh_muc=?
                """;


        try (
                Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps =
                        conn.prepareStatement(sql)

        ) {


            ps.setInt(
                    1,
                    maDanhMuc
            );


            ResultSet rs =
                    ps.executeQuery();


            while(rs.next()){


                SanPham sp =
                        new SanPham();


                sp.setMaSanPham(
                        rs.getInt("ma_san_pham")
                );

                sp.setTenSanPham(
                        rs.getString("ten_san_pham")
                );

                sp.setGia(
                        rs.getDouble("gia")
                );

                sp.setSoLuong(
                        rs.getInt("so_luong")
                );

                sp.setMoTa(
                        rs.getString("mo_ta")
                );

                sp.setHinhAnh(
                        rs.getString("hinh_anh")
                );

                sp.setMaDanhMuc(
                        rs.getInt("ma_danh_muc")
                );


                danhSach.add(sp);

            }


        } catch(Exception e){

            e.printStackTrace();

        }


        return danhSach;

    }





    // ===============================
    // Lấy sản phẩm mới nhất
    // ===============================

    public List<SanPham> laySanPhamMoi(int soLuong) {


        List<SanPham> danhSach =
                new ArrayList<>();


        String sql =
                """
                SELECT *
                FROM san_pham
                ORDER BY ma_san_pham DESC
                LIMIT ?
                """;


        try(
                Connection conn =
                        KetNoiCSDL.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql)

        ){

            ps.setInt(1, soLuong);


            ResultSet rs =
                    ps.executeQuery();


            while(rs.next()){

                SanPham sp =
                        new SanPham();

                sp.setMaSanPham(
                        rs.getInt("ma_san_pham")
                );

                sp.setTenSanPham(
                        rs.getString("ten_san_pham")
                );

                sp.setGia(
                        rs.getDouble("gia")
                );

                sp.setSoLuong(
                        rs.getInt("so_luong")
                );

                sp.setMoTa(
                        rs.getString("mo_ta")
                );

                sp.setHinhAnh(
                        rs.getString("hinh_anh")
                );

                sp.setMaDanhMuc(
                        rs.getInt("ma_danh_muc")
                );


                danhSach.add(sp);

            }


        }catch(Exception e){

            e.printStackTrace();

        }


        return danhSach;

    }

}