package model;

public class ChiTietPhieuNhap {

    private int maChiTiet;
    private int maPhieuNhap;
    private int maSanPham;
    private int maKho;
    private int soLuong;
    private double donGia;
    private String tenSanPham;
    private double thanhTien;


    public ChiTietPhieuNhap() {
    }

    public int getMaChiTiet() {
        return maChiTiet;
    }

    public void setMaChiTiet(int maChiTiet) {
        this.maChiTiet = maChiTiet;
    }

    public int getMaPhieuNhap() {
        return maPhieuNhap;
    }

    public void setMaPhieuNhap(int maPhieuNhap) {
        this.maPhieuNhap = maPhieuNhap;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public int getMaKho() {
        return maKho;
    }

    public void setMaKho(int maKho) {
        this.maKho = maKho;
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

    public String getTenSanPham() { return tenSanPham; }

    public void setTenSanPham(String tenSanPham) { this.tenSanPham = tenSanPham; }

    public double getThanhTien() { return thanhTien; }

    public void setThanhTien(double thanhTien) { this.thanhTien = thanhTien; }
}