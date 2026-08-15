package model;

import java.io.Serializable;
import java.sql.Timestamp;

public class DonHang implements Serializable {

    private static final long serialVersionUID = 1L;

    private int maDon;
    private int maKhachHang;
    private Timestamp ngayDat;

    private double tongTien;

    private String trangThai;
    private String diaChiGiao;
    private String ghiChu;

    // ==============================
    // THÔNG TIN THANH TOÁN
    // ==============================

    private String phuongThucThanhToan;

    private String trangThaiThanhToan;

    private String noiDungThanhToan;

    private String maGiaoDich;


    // ==============================
    // CONSTRUCTOR
    // ==============================

    public DonHang() {
    }


    public DonHang(
            int maDon,
            int maKhachHang,
            Timestamp ngayDat,
            double tongTien,
            String trangThai,
            String diaChiGiao,
            String ghiChu,
            String phuongThucThanhToan,
            String trangThaiThanhToan,
            String noiDungThanhToan,
            String maGiaoDich) {

        this.maDon = maDon;
        this.maKhachHang = maKhachHang;
        this.ngayDat = ngayDat;
        this.tongTien = tongTien;
        this.trangThai = trangThai;
        this.diaChiGiao = diaChiGiao;
        this.ghiChu = ghiChu;

        this.phuongThucThanhToan = phuongThucThanhToan;
        this.trangThaiThanhToan = trangThaiThanhToan;
        this.noiDungThanhToan = noiDungThanhToan;
        this.maGiaoDich = maGiaoDich;
    }


    public DonHang(
            int maKhachHang,
            double tongTien,
            String trangThai,
            String diaChiGiao,
            String ghiChu) {

        this.maKhachHang = maKhachHang;
        this.tongTien = tongTien;
        this.trangThai = trangThai;
        this.diaChiGiao = diaChiGiao;
        this.ghiChu = ghiChu;

        this.phuongThucThanhToan = "COD";
        this.trangThaiThanhToan = "CHUA_THANH_TOAN";
    }


    // ==============================
    // GET / SET
    // ==============================

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


    // ==============================
    // THANH TOÁN
    // ==============================

    public String getPhuongThucThanhToan() {
        return phuongThucThanhToan;
    }

    public void setPhuongThucThanhToan(String phuongThucThanhToan) {
        this.phuongThucThanhToan = phuongThucThanhToan;
    }


    public String getTrangThaiThanhToan() {
        return trangThaiThanhToan;
    }

    public void setTrangThaiThanhToan(String trangThaiThanhToan) {
        this.trangThaiThanhToan = trangThaiThanhToan;
    }


    public String getNoiDungThanhToan() {
        return noiDungThanhToan;
    }

    public void setNoiDungThanhToan(String noiDungThanhToan) {
        this.noiDungThanhToan = noiDungThanhToan;
    }


    public String getMaGiaoDich() {
        return maGiaoDich;
    }

    public void setMaGiaoDich(String maGiaoDich) {
        this.maGiaoDich = maGiaoDich;
    }
}