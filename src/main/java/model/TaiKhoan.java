package model;

import java.io.Serializable;

public class TaiKhoan implements Serializable {

    private static final long serialVersionUID = 1L;

    // Thuộc tính
    private int maTaiKhoan;
    private String hoTen;
    private String email;
    private String matKhau;
    private String soDienThoai;
    private String diaChi;
    private String vaiTro;

    // Constructor mặc định
    public TaiKhoan() {
    }

    // Constructor đầy đủ
    public TaiKhoan(int maTaiKhoan, String hoTen, String email,
                    String matKhau, String soDienThoai,
                    String diaChi, String vaiTro) {

        this.maTaiKhoan = maTaiKhoan;
        this.hoTen = hoTen;
        this.email = email;
        this.matKhau = matKhau;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
        this.vaiTro = vaiTro;
    }

    // Constructor thêm mới (không có mã)
    public TaiKhoan(String hoTen, String email,
                    String matKhau, String soDienThoai,
                    String diaChi, String vaiTro) {

        this.hoTen = hoTen;
        this.email = email;
        this.matKhau = matKhau;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
        this.vaiTro = vaiTro;
    }

    // Getter & Setter

    public int getMaTaiKhoan() {
        return maTaiKhoan;
    }

    public void setMaTaiKhoan(int maTaiKhoan) {
        this.maTaiKhoan = maTaiKhoan;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(String vaiTro) {
        this.vaiTro = vaiTro;
    }

    // Kiểm tra có phải quản trị viên
    public boolean laQuanTri() {
        return "ADMIN".equalsIgnoreCase(vaiTro);
    }

    // Kiểm tra có phải khách hàng
    public boolean laKhachHang() {
        return "KHACH".equalsIgnoreCase(vaiTro);
    }

    @Override
    public String toString() {
        return "TaiKhoan{" +
                "maTaiKhoan=" + maTaiKhoan +
                ", hoTen='" + hoTen + '\'' +
                ", email='" + email + '\'' +
                ", matKhau='" + matKhau + '\'' +
                ", soDienThoai='" + soDienThoai + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", vaiTro='" + vaiTro + '\'' +
                '}';
    }
}