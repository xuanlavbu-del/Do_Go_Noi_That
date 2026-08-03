package dao;

import ketnoi.KetNoiCSDL;
import model.ChiTietDonHang;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChiTietDonHangDAO {


    // =====================================
    // Thêm một chi tiết đơn hàng
    // =====================================

    public boolean themChiTietDonHang(ChiTietDonHang chiTiet) {


        String sql =
                """
                INSERT INTO chi_tiet_don_hang
                (
                    ma_don_hang,
                    ma_san_pham,
                    don_gia,
                    so_luong
                )
                VALUES(?,?,?,?)
                """;


        try (
                Connection conn =
                        KetNoiCSDL.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql)

        ) {


            ps.setInt(
                    1,
                    chiTiet.getMaDonHang()
            );


            ps.setInt(
                    2,
                    chiTiet.getMaSanPham()
            );


            ps.setDouble(
                    3,
                    chiTiet.getDonGia()
            );


            ps.setInt(
                    4,
                    chiTiet.getSoLuong()
            );


            return ps.executeUpdate() > 0;


        } catch(Exception e) {

            e.printStackTrace();

        }


        return false;

    }





    // =====================================
    // Thêm nhiều chi tiết đơn hàng
    // =====================================

    public boolean themDanhSachChiTiet(
            List<ChiTietDonHang> danhSach
    ) {


        String sql =
                """
                INSERT INTO chi_tiet_don_hang
                (
                    ma_don_hang,
                    ma_san_pham,
                    don_gia,
                    so_luong
                )
                VALUES(?,?,?,?)
                """;


        Connection conn = null;


        try {


            conn =
                    KetNoiCSDL.getConnection();


            conn.setAutoCommit(false);


            PreparedStatement ps =
                    conn.prepareStatement(sql);



            for(ChiTietDonHang ct : danhSach){


                ps.setInt(
                        1,
                        ct.getMaDonHang()
                );


                ps.setInt(
                        2,
                        ct.getMaSanPham()
                );


                ps.setDouble(
                        3,
                        ct.getDonGia()
                );


                ps.setInt(
                        4,
                        ct.getSoLuong()
                );


                ps.addBatch();

            }


            ps.executeBatch();


            conn.commit();


            return true;



        } catch(Exception e){


            try {

                if(conn != null)
                    conn.rollback();

            } catch(SQLException ex){

                ex.printStackTrace();

            }


            e.printStackTrace();

        } finally {


            try {

                if(conn != null)
                    conn.close();

            } catch(SQLException e){

                e.printStackTrace();

            }

        }


        return false;

    }





    // =====================================
    // Lấy chi tiết theo mã đơn hàng
    // =====================================

    public List<ChiTietDonHang>
    layChiTietTheoMaDonHang(int maDonHang){


        List<ChiTietDonHang> danhSach =
                new ArrayList<>();


        String sql =
                """
                SELECT 
                    ct.*,
                    sp.ten_san_pham,
                    sp.hinh_anh
                FROM chi_tiet_don_hang ct
                JOIN san_pham sp
                ON ct.ma_san_pham = sp.ma_san_pham
                WHERE ct.ma_don_hang=?
                """;



        try(
                Connection conn =
                        KetNoiCSDL.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql)

        ){


            ps.setInt(
                    1,
                    maDonHang
            );


            ResultSet rs =
                    ps.executeQuery();



            while(rs.next()){


                ChiTietDonHang ct =
                        new ChiTietDonHang();



                ct.setMaChiTiet(
                        rs.getInt("ma_chi_tiet")
                );


                ct.setMaDonHang(
                        rs.getInt("ma_don_hang")
                );


                ct.setMaSanPham(
                        rs.getInt("ma_san_pham")
                );


                ct.setTenSanPham(
                        rs.getString("ten_san_pham")
                );


                ct.setHinhAnh(
                        rs.getString("hinh_anh")
                );


                ct.setDonGia(
                        rs.getDouble("don_gia")
                );


                ct.setSoLuong(
                        rs.getInt("so_luong")
                );


                danhSach.add(ct);

            }



        } catch(Exception e){

            e.printStackTrace();

        }



        return danhSach;

    }





    // =====================================
    // Lấy một chi tiết theo mã
    // =====================================

    public ChiTietDonHang
    layChiTietTheoMa(int maChiTiet){


        ChiTietDonHang chiTiet = null;


        String sql =
                """
                SELECT *
                FROM chi_tiet_don_hang
                WHERE ma_chi_tiet=?
                """;



        try(
                Connection conn =
                        KetNoiCSDL.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql)

        ){


            ps.setInt(
                    1,
                    maChiTiet
            );


            ResultSet rs =
                    ps.executeQuery();



            if(rs.next()){


                chiTiet =
                        new ChiTietDonHang();


                chiTiet.setMaChiTiet(
                        rs.getInt("ma_chi_tiet")
                );


                chiTiet.setMaDonHang(
                        rs.getInt("ma_don_hang")
                );


                chiTiet.setMaSanPham(
                        rs.getInt("ma_san_pham")
                );


                chiTiet.setDonGia(
                        rs.getDouble("don_gia")
                );


                chiTiet.setSoLuong(
                        rs.getInt("so_luong")
                );


            }



        }catch(Exception e){

            e.printStackTrace();

        }


        return chiTiet;

    }





    // =====================================
    // Cập nhật số lượng sản phẩm
    // =====================================

    public boolean capNhatSoLuong(
            int maChiTiet,
            int soLuong
    ){


        String sql =
                """
                UPDATE chi_tiet_don_hang
                SET so_luong=?
                WHERE ma_chi_tiet=?
                """;



        try(
                Connection conn =
                        KetNoiCSDL.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql)

        ){


            ps.setInt(
                    1,
                    soLuong
            );


            ps.setInt(
                    2,
                    maChiTiet
            );


            return ps.executeUpdate()>0;



        }catch(Exception e){

            e.printStackTrace();

        }


        return false;

    }





    // =====================================
    // Xóa chi tiết đơn hàng
    // =====================================

    public boolean xoaChiTietDonHang(
            int maChiTiet
    ){


        String sql =
                """
                DELETE FROM chi_tiet_don_hang
                WHERE ma_chi_tiet=?
                """;



        try(
                Connection conn =
                        KetNoiCSDL.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql)

        ){


            ps.setInt(
                    1,
                    maChiTiet
            );


            return ps.executeUpdate()>0;



        }catch(Exception e){

            e.printStackTrace();

        }


        return false;

    }





    // =====================================
    // Xóa toàn bộ chi tiết của đơn hàng
    // =====================================

    public boolean xoaTheoMaDonHang(
            int maDonHang
    ){


        String sql =
                """
                DELETE FROM chi_tiet_don_hang
                WHERE ma_don_hang=?
                """;



        try(
                Connection conn =
                        KetNoiCSDL.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql)

        ){


            ps.setInt(
                    1,
                    maDonHang
            );


            return ps.executeUpdate()>0;



        }catch(Exception e){

            e.printStackTrace();

        }


        return false;

    }





    // =====================================
    // Tính tổng tiền đơn hàng
    // =====================================

    public double tinhTongTien(
            int maDonHang
    ){


        double tongTien = 0;


        String sql =
                """
                SELECT SUM(don_gia * so_luong)
                AS tong_tien
                FROM chi_tiet_don_hang
                WHERE ma_don_hang=?
                """;



        try(
                Connection conn =
                        KetNoiCSDL.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql)

        ){


            ps.setInt(
                    1,
                    maDonHang
            );


            ResultSet rs =
                    ps.executeQuery();



            if(rs.next()){

                tongTien =
                        rs.getDouble("tong_tien");

            }



        }catch(Exception e){

            e.printStackTrace();

        }



        return tongTien;

    }


}