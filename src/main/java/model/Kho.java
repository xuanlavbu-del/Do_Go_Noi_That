package model;

public class Kho {

    private int maKho;
    private String tenKho;
    private String diaChi;
    private String nguoiQuanLy;
    private String soDienThoai;
    private String email;
    private String ghiChu;

    public Kho() {
    }

    public Kho(int maKho, String tenKho, String diaChi, String nguoiQuanLy, String soDienThoai, String email, String ghiChu) {
        this.maKho = maKho;
        this.tenKho = tenKho;
        this.diaChi = diaChi;
        this.nguoiQuanLy = nguoiQuanLy;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.ghiChu = ghiChu;
    }

    public int getMaKho() {
        return maKho;
    }
    public void setMaKho(int maKho) {
        this.maKho = maKho;
    }

    public String getTenKho() {
        return tenKho;
    }
    public void setTenKho(String tenKho) {
        this.tenKho = tenKho;
    }

    public String getDiaChi() {
        return diaChi;
    }
    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getNguoiQuanLy() {
        return nguoiQuanLy;
    }
    public void setNguoiQuanLy(String nguoiQuanLy) {
        this.nguoiQuanLy = nguoiQuanLy;
    }

    public String getSoDienThoai() {
        return soDienThoai;}
    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;}

    public String getEmail() {
        return email;}
    public void setEmail(String email) {
        this.email = email;}

    public String getGhiChu() { return ghiChu;}
    public void setGhiChu(String ghiChu) { this.ghiChu = ghiChu;}
    }




