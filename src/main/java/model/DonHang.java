package model;

import java.io.Serializable;
import java.sql.Timestamp;

public class DonHang implements Serializable {

    private int maDon;
    private int maKhachHang;
    private Timestamp ngayDat;
    private double tongTien;
    private String trangThai;
    private String diaChiGiao;
    private String ghiChu;

    public DonHang() {
    }

    public DonHang(int maDon, int maKhachHang, Timestamp ngayDat,
                   double tongTien, String trangThai,
                   String diaChiGiao, String ghiChu) {

        this.maDon = maDon;
        this.maKhachHang = maKhachHang;
        this.ngayDat = ngayDat;
        this.tongTien = tongTien;
        this.trangThai = trangThai;
        this.diaChiGiao = diaChiGiao;
        this.ghiChu = ghiChu;
    }

    public DonHang(int maKhachHang, double tongTien,
                   String trangThai, String diaChiGiao,
                   String ghiChu) {

        this.maKhachHang = maKhachHang;
        this.tongTien = tongTien;
        this.trangThai = trangThai;
        this.diaChiGiao = diaChiGiao;
        this.ghiChu = ghiChu;
    }

    public int getMaDon() {
        return maDon;
    }

    public void setMaDon(int maDon) {
        this.maDon = maDon;
    }

    public int getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(int maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public Timestamp getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(Timestamp ngayDat) {
        this.ngayDat = ngayDat;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getDiaChiGiao() {
        return diaChiGiao;
    }

    public void setDiaChiGiao(String diaChiGiao) {
        this.diaChiGiao = diaChiGiao;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
}