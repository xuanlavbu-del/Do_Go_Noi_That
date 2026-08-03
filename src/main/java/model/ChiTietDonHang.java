package model;

import java.io.Serializable;

public class ChiTietDonHang implements Serializable {

    private static final long serialVersionUID = 1L;

    private int maChiTiet;
    private int maDonHang;
    private int maSanPham;

    private String tenSanPham;
    private String hinhAnh;

    private double donGia;
    private int soLuong;

    // Constructor mặc định
    public ChiTietDonHang() {
    }

    // Constructor đầy đủ
    public ChiTietDonHang(int maChiTiet,
                          int maDonHang,
                          int maSanPham,
                          String tenSanPham,
                          String hinhAnh,
                          double donGia,
                          int soLuong) {

        this.maChiTiet = maChiTiet;
        this.maDonHang = maDonHang;
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.hinhAnh = hinhAnh;
        this.donGia = donGia;
        this.soLuong = soLuong;
    }

    // Constructor thêm mới
    public ChiTietDonHang(int maDonHang,
                          int maSanPham,
                          String tenSanPham,
                          String hinhAnh,
                          double donGia,
                          int soLuong) {

        this.maDonHang = maDonHang;
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.hinhAnh = hinhAnh;
        this.donGia = donGia;
        this.soLuong = soLuong;
    }

    public int getMaChiTiet() {
        return maChiTiet;
    }

    public void setMaChiTiet(int maChiTiet) {
        this.maChiTiet = maChiTiet;
    }

    public int getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(int maDonHang) {
        this.maDonHang = maDonHang;
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

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    // Thành tiền
    public double getThanhTien() {
        return donGia * soLuong;
    }

    @Override
    public String toString() {
        return "ChiTietDonHang{" +
                "maChiTiet=" + maChiTiet +
                ", maDonHang=" + maDonHang +
                ", maSanPham=" + maSanPham +
                ", tenSanPham='" + tenSanPham + '\'' +
                ", hinhAnh='" + hinhAnh + '\'' +
                ", donGia=" + donGia +
                ", soLuong=" + soLuong +
                ", thanhTien=" + getThanhTien() +
                '}';
    }
}