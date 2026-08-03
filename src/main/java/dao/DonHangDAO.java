package dao;

import ketnoi.KetNoiCSDL;
import model.DonHang;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DonHangDAO {


    // =====================================
    // Thêm đơn hàng
    // =====================================

    public boolean themDonHang(DonHang donHang) {


        String sql =
                """
                INSERT INTO don_hang
                (
                    ma_tai_khoan,
                    ngay_dat,
                    tong_tien,
                    trang_thai
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
                    donHang.getMaTaiKhoan()
            );


            ps.setDate(
                    2,
                    donHang.getNgayDat()
            );


            ps.setDouble(
                    3,
                    donHang.getTongTien()
            );


            ps.setString(
                    4,
                    donHang.getTrangThai()
            );


            return ps.executeUpdate() > 0;



        } catch(Exception e){

            e.printStackTrace();

        }


        return false;

    }





    // =====================================
    // Thêm đơn hàng và lấy mã tự sinh
    // =====================================

    public int themDonHangLayMa(DonHang donHang) {


        int maDonHang = 0;


        String sql =
                """
                INSERT INTO don_hang
                (
                    ma_tai_khoan,
                    ngay_dat,
                    tong_tien,
                    trang_thai
                )
                VALUES(?,?,?,?)
                """;


        try (
                Connection conn =
                        KetNoiCSDL.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(
                                sql,
                                Statement.RETURN_GENERATED_KEYS
                        )

        ) {


            ps.setInt(
                    1,
                    donHang.getMaTaiKhoan()
            );


            ps.setDate(
                    2,
                    donHang.getNgayDat()
            );


            ps.setDouble(
                    3,
                    donHang.getTongTien()
            );


            ps.setString(
                    4,
                    donHang.getTrangThai()
            );


            ps.executeUpdate();


            ResultSet rs =
                    ps.getGeneratedKeys();


            if(rs.next()){

                maDonHang =
                        rs.getInt(1);

            }



        }catch(Exception e){

            e.printStackTrace();

        }


        return maDonHang;

    }





    // =====================================
    // Lấy đơn hàng theo mã
    // =====================================

    public DonHang layDonHangTheoMa(int maDonHang){


        DonHang donHang = null;


        String sql =
                """
                SELECT *
                FROM don_hang
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


                donHang =
                        new DonHang();


                donHang.setMaDonHang(
                        rs.getInt("ma_don_hang")
                );


                donHang.setMaTaiKhoan(
                        rs.getInt("ma_tai_khoan")
                );


                donHang.setNgayDat(
                        rs.getDate("ngay_dat")
                );


                donHang.setTongTien(
                        rs.getDouble("tong_tien")
                );


                donHang.setTrangThai(
                        rs.getString("trang_thai")
                );


            }



        }catch(Exception e){

            e.printStackTrace();

        }


        return donHang;

    }





    // =====================================
    // Lấy đơn hàng theo khách hàng
    // =====================================

    public List<DonHang> layDonHangTheoTaiKhoan(int maTaiKhoan){


        List<DonHang> danhSach =
                new ArrayList<>();


        String sql =
                """
                SELECT *
                FROM don_hang
                WHERE ma_tai_khoan=?
                ORDER BY ma_don_hang DESC
                """;



        try(
                Connection conn =
                        KetNoiCSDL.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql)

        ){


            ps.setInt(
                    1,
                    maTaiKhoan
            );


            ResultSet rs =
                    ps.executeQuery();



            while(rs.next()){


                DonHang dh =
                        new DonHang();


                dh.setMaDonHang(
                        rs.getInt("ma_don_hang")
                );


                dh.setMaTaiKhoan(
                        rs.getInt("ma_tai_khoan")
                );


                dh.setNgayDat(
                        rs.getDate("ngay_dat")
                );


                dh.setTongTien(
                        rs.getDouble("tong_tien")
                );


                dh.setTrangThai(
                        rs.getString("trang_thai")
                );


                danhSach.add(dh);

            }



        }catch(Exception e){

            e.printStackTrace();

        }



        return danhSach;

    }





    // =====================================
    // Lấy tất cả đơn hàng (ADMIN)
    // =====================================

    public List<DonHang> layTatCaDonHang(){


        List<DonHang> danhSach =
                new ArrayList<>();


        String sql =
                """
                SELECT *
                FROM don_hang
                ORDER BY ma_don_hang DESC
                """;


        try(
                Connection conn =
                        KetNoiCSDL.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql);

                ResultSet rs =
                        ps.executeQuery()

        ){


            while(rs.next()){


                DonHang dh =
                        new DonHang();


                dh.setMaDonHang(
                        rs.getInt("ma_don_hang")
                );


                dh.setMaTaiKhoan(
                        rs.getInt("ma_tai_khoan")
                );


                dh.setNgayDat(
                        rs.getDate("ngay_dat")
                );


                dh.setTongTien(
                        rs.getDouble("tong_tien")
                );


                dh.setTrangThai(
                        rs.getString("trang_thai")
                );


                danhSach.add(dh);

            }



        }catch(Exception e){

            e.printStackTrace();

        }


        return danhSach;

    }





    // =====================================
    // Cập nhật trạng thái đơn hàng
    // =====================================

    public boolean capNhatTrangThai(
            int maDonHang,
            String trangThai
    ){


        String sql =
                """
                UPDATE don_hang
                SET trang_thai=?
                WHERE ma_don_hang=?
                """;



        try(
                Connection conn =
                        KetNoiCSDL.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql)

        ){


            ps.setString(
                    1,
                    trangThai
            );


            ps.setInt(
                    2,
                    maDonHang
            );


            return ps.executeUpdate()>0;



        }catch(Exception e){

            e.printStackTrace();

        }


        return false;

    }





    // =====================================
    // Cập nhật toàn bộ đơn hàng
    // =====================================

    public boolean capNhatDonHang(DonHang donHang){


        String sql =
                """
                UPDATE don_hang
                SET
                    ngay_dat=?,
                    tong_tien=?,
                    trang_thai=?
                WHERE ma_don_hang=?
                """;


        try(
                Connection conn =
                        KetNoiCSDL.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql)

        ){


            ps.setDate(
                    1,
                    donHang.getNgayDat()
            );


            ps.setDouble(
                    2,
                    donHang.getTongTien()
            );


            ps.setString(
                    3,
                    donHang.getTrangThai()
            );


            ps.setInt(
                    4,
                    donHang.getMaDonHang()
            );



            return ps.executeUpdate()>0;



        }catch(Exception e){

            e.printStackTrace();

        }


        return false;

    }





    // =====================================
    // Xóa đơn hàng
    // =====================================

    public boolean xoaDonHang(int maDonHang){


        String sql =
                """
                DELETE FROM don_hang
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


}