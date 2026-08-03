package dao;

import ketnoi.KetNoiCSDL;
import model.TaiKhoan;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaiKhoanDAO {


    // =====================================
    // Đăng nhập
    // =====================================

    public TaiKhoan dangNhap(String email, String matKhau) {

        TaiKhoan taiKhoan = null;


        String sql =
                """
                SELECT *
                FROM tai_khoan
                WHERE email=? AND mat_khau=?
                """;


        try (
                Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {


            ps.setString(1, email);
            ps.setString(2, matKhau);


            ResultSet rs =
                    ps.executeQuery();


            if (rs.next()) {


                taiKhoan = new TaiKhoan();


                taiKhoan.setMaTaiKhoan(
                        rs.getInt("ma_tai_khoan")
                );


                taiKhoan.setHoTen(
                        rs.getString("ho_ten")
                );


                taiKhoan.setEmail(
                        rs.getString("email")
                );


                taiKhoan.setMatKhau(
                        rs.getString("mat_khau")
                );


                taiKhoan.setSoDienThoai(
                        rs.getString("so_dien_thoai")
                );


                taiKhoan.setDiaChi(
                        rs.getString("dia_chi")
                );


                taiKhoan.setVaiTro(
                        rs.getString("vai_tro")
                );

            }


        } catch(Exception e){

            e.printStackTrace();

        }


        return taiKhoan;

    }





    // =====================================
    // Đăng ký tài khoản
    // =====================================

    public boolean dangKy(TaiKhoan taiKhoan) {


        String sql =
                """
                INSERT INTO tai_khoan
                (
                    ho_ten,
                    email,
                    mat_khau,
                    so_dien_thoai,
                    dia_chi,
                    vai_tro
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
                    taiKhoan.getHoTen()
            );


            ps.setString(
                    2,
                    taiKhoan.getEmail()
            );


            ps.setString(
                    3,
                    taiKhoan.getMatKhau()
            );


            ps.setString(
                    4,
                    taiKhoan.getSoDienThoai()
            );


            ps.setString(
                    5,
                    taiKhoan.getDiaChi()
            );


            ps.setString(
                    6,
                    taiKhoan.getVaiTro()
            );


            return ps.executeUpdate() > 0;



        } catch(Exception e){

            e.printStackTrace();

        }


        return false;

    }





    // =====================================
    // Kiểm tra email tồn tại
    // =====================================

    public boolean kiemTraEmailTonTai(String email) {


        String sql =
                """
                SELECT ma_tai_khoan
                FROM tai_khoan
                WHERE email=?
                """;


        try(
                Connection conn =
                        KetNoiCSDL.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql)

        ){


            ps.setString(1,email);


            ResultSet rs =
                    ps.executeQuery();


            return rs.next();



        }catch(Exception e){

            e.printStackTrace();

        }


        return false;

    }





    // =====================================
    // Lấy tài khoản theo mã
    // =====================================

    public TaiKhoan layTaiKhoanTheoMa(int maTaiKhoan) {


        TaiKhoan taiKhoan = null;


        String sql =
                """
                SELECT *
                FROM tai_khoan
                WHERE ma_tai_khoan=?
                """;


        try(
                Connection conn =
                        KetNoiCSDL.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql)

        ){


            ps.setInt(1,maTaiKhoan);


            ResultSet rs =
                    ps.executeQuery();



            if(rs.next()){


                taiKhoan =
                        new TaiKhoan();



                taiKhoan.setMaTaiKhoan(
                        rs.getInt("ma_tai_khoan")
                );


                taiKhoan.setHoTen(
                        rs.getString("ho_ten")
                );


                taiKhoan.setEmail(
                        rs.getString("email")
                );


                taiKhoan.setMatKhau(
                        rs.getString("mat_khau")
                );


                taiKhoan.setSoDienThoai(
                        rs.getString("so_dien_thoai")
                );


                taiKhoan.setDiaChi(
                        rs.getString("dia_chi")
                );


                taiKhoan.setVaiTro(
                        rs.getString("vai_tro")
                );


            }



        }catch(Exception e){

            e.printStackTrace();

        }


        return taiKhoan;

    }





    // =====================================
    // Lấy tất cả tài khoản
    // =====================================

    public List<TaiKhoan> layTatCaTaiKhoan(){


        List<TaiKhoan> danhSach =
                new ArrayList<>();


        String sql =
                "SELECT * FROM tai_khoan";


        try(
                Connection conn =
                        KetNoiCSDL.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql);

                ResultSet rs =
                        ps.executeQuery()

        ){


            while(rs.next()){


                TaiKhoan tk =
                        new TaiKhoan();


                tk.setMaTaiKhoan(
                        rs.getInt("ma_tai_khoan")
                );


                tk.setHoTen(
                        rs.getString("ho_ten")
                );


                tk.setEmail(
                        rs.getString("email")
                );


                tk.setMatKhau(
                        rs.getString("mat_khau")
                );


                tk.setSoDienThoai(
                        rs.getString("so_dien_thoai")
                );


                tk.setDiaChi(
                        rs.getString("dia_chi")
                );


                tk.setVaiTro(
                        rs.getString("vai_tro")
                );


                danhSach.add(tk);

            }


        }catch(Exception e){

            e.printStackTrace();

        }


        return danhSach;

    }





    // =====================================
    // Cập nhật tài khoản
    // =====================================

    public boolean capNhatTaiKhoan(TaiKhoan taiKhoan){


        String sql =
                """
                UPDATE tai_khoan
                SET
                    ho_ten=?,
                    email=?,
                    mat_khau=?,
                    so_dien_thoai=?,
                    dia_chi=?,
                    vai_tro=?
                WHERE ma_tai_khoan=?
                """;



        try(
                Connection conn =
                        KetNoiCSDL.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql)

        ){


            ps.setString(
                    1,
                    taiKhoan.getHoTen()
            );


            ps.setString(
                    2,
                    taiKhoan.getEmail()
            );


            ps.setString(
                    3,
                    taiKhoan.getMatKhau()
            );


            ps.setString(
                    4,
                    taiKhoan.getSoDienThoai()
            );


            ps.setString(
                    5,
                    taiKhoan.getDiaChi()
            );


            ps.setString(
                    6,
                    taiKhoan.getVaiTro()
            );


            ps.setInt(
                    7,
                    taiKhoan.getMaTaiKhoan()
            );



            return ps.executeUpdate()>0;



        }catch(Exception e){

            e.printStackTrace();

        }


        return false;

    }





    // =====================================
    // Xóa tài khoản
    // =====================================

    public boolean xoaTaiKhoan(int maTaiKhoan){


        String sql =
                """
                DELETE FROM tai_khoan
                WHERE ma_tai_khoan=?
                """;


        try(
                Connection conn =
                        KetNoiCSDL.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql)

        ){


            ps.setInt(1,maTaiKhoan);


            return ps.executeUpdate()>0;



        }catch(Exception e){

            e.printStackTrace();

        }


        return false;

    }

}