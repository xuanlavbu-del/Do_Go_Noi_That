package dao;

import ketnoi.KetNoiCSDL;
import model.ChiTietPhieuXuat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ChiTietPhieuXuatDAO{

    public ArrayList<ChiTietPhieuXuat> getByPhieuXuat(int maPhieuXuat){

        ArrayList<ChiTietPhieuXuat> ds=new ArrayList<>();

        String sql="""
                SELECT
                ct.ma_chi_tiet,
                ct.ma_phieu_xuat,
                ct.ma_san_pham,
                ct.ma_kho,
                ct.so_luong,
                sp.ten_san_pham,
                sp.gia,
                k.ten_kho
                FROM chi_tiet_phieu_xuat ct
                INNER JOIN san_pham sp
                ON ct.ma_san_pham=sp.ma_san_pham
                INNER JOIN kho k
                ON ct.ma_kho=k.ma_kho
                WHERE ct.ma_phieu_xuat=?
                ORDER BY ct.ma_chi_tiet
                """;

        try(
                Connection conn=KetNoiCSDL.getConnection();
                PreparedStatement ps=conn.prepareStatement(sql)
        ){

            ps.setInt(1,maPhieuXuat);

            ResultSet rs=ps.executeQuery();

            while(rs.next()){

                ChiTietPhieuXuat ct=new ChiTietPhieuXuat();

                ct.setMaChiTiet(rs.getInt("ma_chi_tiet"));

                ct.setMaPhieuXuat(rs.getInt("ma_phieu_xuat"));

                ct.setMaSanPham(rs.getInt("ma_san_pham"));

                ct.setMaKho(rs.getInt("ma_kho"));

                ct.setSoLuong(rs.getInt("so_luong"));

                ct.setTenSanPham(rs.getString("ten_san_pham"));

                ct.setTenKho(rs.getString("ten_kho"));

                ct.setDonGia(rs.getDouble("gia"));

                ct.setThanhTien(rs.getInt("so_luong")*rs.getDouble("gia"));

                ds.add(ct);

            }

        }catch(Exception e){

            e.printStackTrace();

        }

        return ds;

    }

    public double tinhTongTien(int maPhieuXuat){

        double tongTien=0;

        String sql="""
                SELECT
                SUM(ct.so_luong*sp.gia) AS tong_tien
                FROM chi_tiet_phieu_xuat ct
                INNER JOIN san_pham sp
                ON ct.ma_san_pham=sp.ma_san_pham
                WHERE ct.ma_phieu_xuat=?
                """;

        try(
                Connection conn=KetNoiCSDL.getConnection();
                PreparedStatement ps=conn.prepareStatement(sql)
        ){

            ps.setInt(1,maPhieuXuat);

            ResultSet rs=ps.executeQuery();

            if(rs.next()){

                tongTien=rs.getDouble("tong_tien");

            }

        }catch(Exception e){

            e.printStackTrace();

        }

        return tongTien;

    }

    public boolean insert(ChiTietPhieuXuat ct){

        String sql="""
                INSERT INTO chi_tiet_phieu_xuat(
                ma_phieu_xuat,
                ma_san_pham,
                ma_kho,
                so_luong
                )
                VALUES(?,?,?,?)
                """;

        try(
                Connection conn=KetNoiCSDL.getConnection();
                PreparedStatement ps=conn.prepareStatement(sql)
        ){

            ps.setInt(1,ct.getMaPhieuXuat());

            ps.setInt(2,ct.getMaSanPham());

            ps.setInt(3,ct.getMaKho());

            ps.setInt(4,ct.getSoLuong());

            return ps.executeUpdate()>0;

        }catch(Exception e){

            e.printStackTrace();

        }

        return false;

    }

    public ChiTietPhieuXuat findById(int maChiTiet){

        String sql="""
                SELECT
                ct.ma_chi_tiet,
                ct.ma_phieu_xuat,
                ct.ma_san_pham,
                ct.ma_kho,
                ct.so_luong,
                sp.ten_san_pham,
                sp.gia,
                k.ten_kho
                FROM chi_tiet_phieu_xuat ct
                INNER JOIN san_pham sp
                ON ct.ma_san_pham=sp.ma_san_pham
                INNER JOIN kho k
                ON ct.ma_kho=k.ma_kho
                WHERE ct.ma_chi_tiet=?
                """;

        try(
                Connection conn=KetNoiCSDL.getConnection();
                PreparedStatement ps=conn.prepareStatement(sql)
        ){

            ps.setInt(1,maChiTiet);

            ResultSet rs=ps.executeQuery();

            if(rs.next()){

                ChiTietPhieuXuat ct=new ChiTietPhieuXuat();

                ct.setMaChiTiet(rs.getInt("ma_chi_tiet"));

                ct.setMaPhieuXuat(rs.getInt("ma_phieu_xuat"));

                ct.setMaSanPham(rs.getInt("ma_san_pham"));

                ct.setMaKho(rs.getInt("ma_kho"));

                ct.setSoLuong(rs.getInt("so_luong"));

                ct.setTenSanPham(rs.getString("ten_san_pham"));

                ct.setTenKho(rs.getString("ten_kho"));

                ct.setDonGia(rs.getDouble("gia"));

                ct.setThanhTien(rs.getInt("so_luong")*rs.getDouble("gia"));

                return ct;

            }

        }catch(Exception e){

            e.printStackTrace();

        }

        return null;

    }

    public boolean update(ChiTietPhieuXuat ct){

        String sql="""
                UPDATE chi_tiet_phieu_xuat
                SET
                ma_san_pham=?,
                ma_kho=?,
                so_luong=?
                WHERE ma_chi_tiet=?
                """;

        try(
                Connection conn=KetNoiCSDL.getConnection();
                PreparedStatement ps=conn.prepareStatement(sql)
        ){

            ps.setInt(1,ct.getMaSanPham());

            ps.setInt(2,ct.getMaKho());

            ps.setInt(3,ct.getSoLuong());

            ps.setInt(4,ct.getMaChiTiet());

            return ps.executeUpdate()>0;

        }catch(Exception e){

            e.printStackTrace();

        }

        return false;

    }

    public ArrayList<ChiTietPhieuXuat> getAll(){

        ArrayList<ChiTietPhieuXuat> ds=new ArrayList<>();

        String sql="""
                SELECT
                ct.ma_chi_tiet,
                ct.ma_phieu_xuat,
                ct.ma_san_pham,
                ct.ma_kho,
                ct.so_luong,
                sp.ten_san_pham,
                sp.gia,
                k.ten_kho
                FROM chi_tiet_phieu_xuat ct
                INNER JOIN san_pham sp
                ON ct.ma_san_pham=sp.ma_san_pham
                INNER JOIN kho k
                ON ct.ma_kho=k.ma_kho
                ORDER BY ct.ma_chi_tiet DESC
                """;

        try(
                Connection conn=KetNoiCSDL.getConnection();
                PreparedStatement ps=conn.prepareStatement(sql);
                ResultSet rs=ps.executeQuery()
        ){

            while(rs.next()){

                ChiTietPhieuXuat ct=new ChiTietPhieuXuat();

                ct.setMaChiTiet(rs.getInt("ma_chi_tiet"));

                ct.setMaPhieuXuat(rs.getInt("ma_phieu_xuat"));

                ct.setMaSanPham(rs.getInt("ma_san_pham"));

                ct.setMaKho(rs.getInt("ma_kho"));

                ct.setSoLuong(rs.getInt("so_luong"));

                ct.setTenSanPham(rs.getString("ten_san_pham"));

                ct.setTenKho(rs.getString("ten_kho"));

                ct.setDonGia(rs.getDouble("gia"));

                ct.setThanhTien(rs.getInt("so_luong")*rs.getDouble("gia"));

                ds.add(ct);

            }

        }catch(Exception e){

            e.printStackTrace();

        }

        return ds;

    }

    public boolean deleteByPhieuXuat(int maPhieuXuat){

        String sql="""
                DELETE
                FROM chi_tiet_phieu_xuat
                WHERE ma_phieu_xuat=?
                """;

        try(
                Connection conn=KetNoiCSDL.getConnection();
                PreparedStatement ps=conn.prepareStatement(sql)
        ){

            ps.setInt(1,maPhieuXuat);

            return ps.executeUpdate()>0;

        }catch(Exception e){

            e.printStackTrace();

        }

        return false;

    }

    public boolean delete(int maChiTiet){

        String sql="""
                DELETE
                FROM chi_tiet_phieu_xuat
                WHERE ma_chi_tiet=?
                """;

        try(
                Connection conn=KetNoiCSDL.getConnection();
                PreparedStatement ps=conn.prepareStatement(sql)
        ){

            ps.setInt(1,maChiTiet);

            return ps.executeUpdate()>0;

        }catch(Exception e){

            e.printStackTrace();

        }

        return false;

    }

}