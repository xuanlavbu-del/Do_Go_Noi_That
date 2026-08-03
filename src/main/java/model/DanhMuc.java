package model;

import java.io.Serializable;

public class DanhMuc implements Serializable {

    private static final long serialVersionUID = 1L;

    private int maDanhMuc;
    private String tenDanhMuc;
    private String moTa;

    // Constructor mặc định
    public DanhMuc() {
    }

    // Constructor có mã danh mục
    public DanhMuc(int maDanhMuc, String tenDanhMuc, String moTa) {
        this.maDanhMuc = maDanhMuc;
        this.tenDanhMuc = tenDanhMuc;
        this.moTa = moTa;
    }

    // Constructor thêm mới (không có mã)
    public DanhMuc(String tenDanhMuc, String moTa) {
        this.tenDanhMuc = tenDanhMuc;
        this.moTa = moTa;
    }

    public int getMaDanhMuc() {
        return maDanhMuc;
    }

    public void setMaDanhMuc(int maDanhMuc) {
        this.maDanhMuc = maDanhMuc;
    }

    public String getTenDanhMuc() {
        return tenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        this.tenDanhMuc = tenDanhMuc;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    @Override
    public String toString() {
        return "DanhMuc{" +
                "maDanhMuc=" + maDanhMuc +
                ", tenDanhMuc='" + tenDanhMuc + '\'' +
                ", moTa='" + moTa + '\'' +
                '}';
    }
}