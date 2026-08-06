package model;

import java.sql.Timestamp;

public class KiemKe {

    private int maKiemKe;
    private int maKho;
    private Timestamp ngayKiemKe;
    private String nguoiKiemKe;
    private String ghiChu;
    private String tenKho;

    public KiemKe() {
    }

    public int getMaKiemKe() {
        return maKiemKe;
    }

    public void setMaKiemKe(int maKiemKe) {
        this.maKiemKe = maKiemKe;
    }

    public int getMaKho() {
        return maKho;
    }

    public void setMaKho(int maKho) {
        this.maKho = maKho;
    }

    public Timestamp getNgayKiemKe() {
        return ngayKiemKe;
    }

    public void setNgayKiemKe(Timestamp ngayKiemKe) {
        this.ngayKiemKe = ngayKiemKe;
    }

    public String getNguoiKiemKe() {
        return nguoiKiemKe;
    }

    public void setNguoiKiemKe(String nguoiKiemKe) {
        this.nguoiKiemKe = nguoiKiemKe;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getTenKho() {
        return tenKho;
    }

    public void setTenKho(String tenKho) {
        this.tenKho = tenKho;
    }

}