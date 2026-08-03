package model;

import java.io.Serializable;
import java.sql.Date;

public class DonHang implements Serializable {

    private static final long serialVersionUID = 1L;

    private int maDonHang;
    private int maTaiKhoan;
    private Date ngayDat;
    private double tongTien;
    private String trangThai;

    // Constructor mặc định
    public DonHang() {
    }

    // Constructor đầy đủ
    public DonHang(int maDonHang,
                   int maTaiKhoan,
                   Date ngayDat,
                   double tongTien,
                   String trangThai) {

        this.maDonHang = maDonHang;
        this.maTaiKhoan = maTaiKhoan;
        this.ngayDat = ngayDat;
        this.tongTien = tongTien;
        this.trangThai = trangThai;
    }

    // Constructor thêm mới
    public DonHang(int maTaiKhoan,
                   Date ngayDat,
                   double tongTien,
                   String trangThai) {

        this.maTaiKhoan = maTaiKhoan;
        this.ngayDat = ngayDat;
        this.tongTien = tongTien;
        this.trangThai = trangThai;
    }

    public int getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(int maDonHang) {
        this.maDonHang = maDonHang;
    }

    public int getMaTaiKhoan() {
        return maTaiKhoan;
    }

    public void setMaTaiKhoan(int maTaiKhoan) {
        this.maTaiKhoan = maTaiKhoan;
    }

    public Date getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(Date ngayDat) {
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

    // Kiểm tra đơn hàng đã giao
    public boolean daGiao() {
        return "DA_GIAO".equalsIgnoreCase(trangThai);
    }

    // Kiểm tra đơn hàng đang xử lý
    public boolean dangXuLy() {
        return "DANG_XU_LY".equalsIgnoreCase(trangThai);
    }

    @Override
    public String toString() {
        return "DonHang{" +
                "maDonHang=" + maDonHang +
                ", maTaiKhoan=" + maTaiKhoan +
                ", ngayDat=" + ngayDat +
                ", tongTien=" + tongTien +
                ", trangThai='" + trangThai + '\'' +
                '}';
    }
}