package dao;

import ketnoi.KetNoiCSDL;
import model.ChiTietPhieuNhap;

import java.sql.*;
import java.util.ArrayList;

public class ChiTietPhieuNhapDAO {
    // Thêm chi tiết phiếu nhập
    public boolean themChiTiet(ChiTietPhieuNhap ct) {

        String sql =
                """
                INSERT INTO chi_tiet_phieu_nhap
                (
                    ma_phieu_nhap,
                    ma_san_pham,
                    so_luong,
                    don_gia
                )
                VALUES
                (
                    ?,?,?,?
                )
                """;

        try (

                Connection conn =
                        KetNoiCSDL.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql);

        ) {

            ps.setInt(1, ct.getMaPhieuNhap());
            ps.setInt(2, ct.getMaSanPham());
            ps.setInt(3, ct.getSoLuong());
            ps.setDouble(4, ct.getDonGia());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;

    }

// Lấy danh sách chi tiết theo phiếu nhập
public ArrayList<ChiTietPhieuNhap> getByPhieuNhap(int maPhieuNhap) {

    ArrayList<ChiTietPhieuNhap> list =
            new ArrayList<>();

    String sql =
            """
            
                    SELECT
                ct.ma_phieu_nhap,
                ct.ma_san_pham,
                sp.ten_san_pham,
                ct.so_luong,
                ct.don_gia,
                ct.so_luong*ct.don_gia AS thanh_tien
            FROM chi_tiet_phieu_nhap ct
            INNER JOIN san_pham sp
            ON ct.ma_san_pham=sp.ma_san_pham
            WHERE ct.ma_phieu_nhap=?
            """;

    try (

            Connection conn =
                    KetNoiCSDL.getConnection();

            PreparedStatement ps =
                    conn.prepareStatement(sql);

    ) {

        ps.setInt(1, maPhieuNhap);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            ChiTietPhieuNhap ct =
                    new ChiTietPhieuNhap();



            ct.setMaPhieuNhap(
                    rs.getInt("ma_phieu_nhap")
            );

            ct.setMaSanPham(
                    rs.getInt("ma_san_pham")
            );

            ct.setSoLuong(
                    rs.getInt("so_luong")
            );

            ct.setDonGia(
                    rs.getDouble("don_gia")
            );
            ct.setTenSanPham(
                    rs.getString("ten_san_pham")
            );

            ct.setThanhTien(
                    rs.getDouble("thanh_tien")
            );
            list.add(ct);

        }

    } catch (Exception e) {

        e.printStackTrace();

    }

    return list;

}

// Xóa chi tiết theo phiếu nhập
public boolean xoaTheoPhieuNhap(int maPhieuNhap) {

    String sql =
            """
            DELETE
            FROM chi_tiet_phieu_nhap
            WHERE ma_phieu_nhap=?
            """;

    try (

            Connection conn =
                    KetNoiCSDL.getConnection();

            PreparedStatement ps =
                    conn.prepareStatement(sql);

    ) {

        ps.setInt(1, maPhieuNhap);

        return ps.executeUpdate() > 0;

    } catch (Exception e) {

        e.printStackTrace();

    }

    return false;

}
// Tính tổng tiền phiếu nhập
public double tinhTongTien(int maPhieuNhap) {

    String sql =
            """
            SELECT SUM(so_luong * don_gia)
            FROM chi_tiet_phieu_nhap
            WHERE ma_phieu_nhap=?
            """;

    try (

            Connection conn =
                    KetNoiCSDL.getConnection();

            PreparedStatement ps =
                    conn.prepareStatement(sql);

    ) {

        ps.setInt(1, maPhieuNhap);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

            return rs.getDouble(1);

        }

    } catch (Exception e) {

        e.printStackTrace();

    }

    return 0;

}


}