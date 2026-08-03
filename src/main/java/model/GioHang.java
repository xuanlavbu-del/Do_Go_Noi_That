package model;

import java.io.Serializable;

public class GioHang implements Serializable {

    private static final long serialVersionUID = 1L;

    private int maGioHang;
    private int maTaiKhoan;
    private int maSanPham;
    private String tenSanPham;
    private double gia;
    private int soLuong;
    private String hinhAnh;

    // Constructor mặc định
    public GioHang() {
    }

    // Constructor đầy đủ
    public GioHang(int maGioHang, int maTaiKhoan,
                   int maSanPham, String tenSanPham,
                   double gia, int soLuong,
                   String hinhAnh) {

        this.maGioHang = maGioHang;
        this.maTaiKhoan = maTaiKhoan;
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.gia = gia;
        this.soLuong = soLuong;
        this.hinhAnh = hinhAnh;
    }

    // Constructor thêm mới
    public GioHang(int maTaiKhoan,
                   int maSanPham,
                   String tenSanPham,
                   double gia,
                   int soLuong,
                   String hinhAnh) {

        this.maTaiKhoan = maTaiKhoan;
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.gia = gia;
        this.soLuong = soLuong;
        this.hinhAnh = hinhAnh;
    }

    public int getMaGioHang() {
        return maGioHang;
    }

    public void setMaGioHang(int maGioHang) {
        this.maGioHang = maGioHang;
    }

    public int getMaTaiKhoan() {
        return maTaiKhoan;
    }

    public void setMaTaiKhoan(int maTaiKhoan) {
        this.maTaiKhoan = maTaiKhoan;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    // Thành tiền
    public double getThanhTien() {
        return gia * soLuong;
    }

    @Override
    public String toString() {
        return "GioHang{" +
                "maGioHang=" + maGioHang +
                ", maTaiKhoan=" + maTaiKhoan +
                ", maSanPham=" + maSanPham +
                ", tenSanPham='" + tenSanPham + '\'' +
                ", gia=" + gia +
                ", soLuong=" + soLuong +
                ", hinhAnh='" + hinhAnh + '\'' +
                '}';
    }
}