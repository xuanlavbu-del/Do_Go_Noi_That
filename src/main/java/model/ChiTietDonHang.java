package model;

import java.io.Serializable;

public class ChiTietDonHang implements Serializable {

    private static final long serialVersionUID = 1L;

    private int maCTDH;
    private int maDon;
    private int maSanPham;
    private int soLuong;
    private double donGia;
    private double thanhTien;

    public ChiTietDonHang() {
    }

    public ChiTietDonHang(int maCTDH, int maDon, int maSanPham,
                          int soLuong, double donGia, double thanhTien) {
        this.maCTDH = maCTDH;
        this.maDon = maDon;
        this.maSanPham = maSanPham;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
    }

    public ChiTietDonHang(int maDon, int maSanPham,
                          int soLuong, double donGia, double thanhTien) {
        this.maDon = maDon;
        this.maSanPham = maSanPham;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
    }

    public int getMaCTDH() {
        return maCTDH;
    }

    public void setMaCTDH(int maCTDH) {
        this.maCTDH = maCTDH;
    }

    public int getMaDon() {
        return maDon;
    }

    public void setMaDon(int maDon) {
        this.maDon = maDon;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }

    @Override
    public String toString() {
        return "ChiTietDonHang{" +
                "maCTDH=" + maCTDH +
                ", maDon=" + maDon +
                ", maSanPham=" + maSanPham +
                ", soLuong=" + soLuong +
                ", donGia=" + donGia +
                ", thanhTien=" + thanhTien +
                '}';
    }
}

