package dao;
import ketnoi.KetNoiCSDL;
import model.TaiKhoan;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
public class TaiKhoanDAO {
    public TaiKhoan dangNhap(String email, String matKhau) {
        String sql = "SELECT * FROM tai_khoan WHERE email = ? AND trang_thai = 'HOAT_DONG'";
        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    TaiKhoan taiKhoan = mapTaiKhoan(rs);
                    if (taiKhoan.getMatKhau() != null && BCrypt.checkpw(matKhau, taiKhoan.getMatKhau())) {
                        return taiKhoan;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean dangKy(TaiKhoan taiKhoan) {
        String sql = "INSERT INTO tai_khoan (ten_dang_nhap, mat_khau, ho_ten, email, so_dien_thoai, vai_tro, trang_thai) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, taiKhoan.getTenDangNhap());
            ps.setString(2, taiKhoan.getMatKhau());
            ps.setString(3, taiKhoan.getHoTen());
            ps.setString(4, taiKhoan.getEmail());
            ps.setString(5, taiKhoan.getSoDienThoai());
            ps.setString(6, taiKhoan.getVaiTro());
            ps.setString(7, taiKhoan.getTrangThai() == null ? "HOAT_DONG" : taiKhoan.getTrangThai());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean kiemTraEmailTonTai(String email) {
        String sql = "SELECT ma_tai_khoan FROM tai_khoan WHERE email = ?";
        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean kiemTraEmailTonTai(String email, int maTaiKhoan) {
        String sql = "SELECT ma_tai_khoan FROM tai_khoan WHERE email = ? AND ma_tai_khoan <> ?";
        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setInt(2, maTaiKhoan);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public TaiKhoan getById(int maTaiKhoan) {
        String sql = "SELECT * FROM tai_khoan WHERE ma_tai_khoan = ?";
        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maTaiKhoan);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapTaiKhoan(rs);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public TaiKhoan getByEmail(String email) {
        String sql = "SELECT * FROM tai_khoan WHERE email = ?";
        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapTaiKhoan(rs);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<TaiKhoan> getAll() {
        List<TaiKhoan> danhSach = new ArrayList<>();
        String sql = "SELECT * FROM tai_khoan ORDER BY ma_tai_khoan DESC";
        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                danhSach.add(mapTaiKhoan(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return danhSach;
    }
    public List<TaiKhoan> timKiem(String tuKhoa) {
        List<TaiKhoan> danhSach = new ArrayList<>();
        String sql = "SELECT * FROM tai_khoan WHERE ten_dang_nhap LIKE ? OR ho_ten LIKE ? OR email LIKE ? OR so_dien_thoai LIKE ? ORDER BY ma_tai_khoan DESC";
        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            String key = "%" + (tuKhoa == null ? "" : tuKhoa.trim()) + "%";
            ps.setString(1, key);
            ps.setString(2, key);
            ps.setString(3, key);
            ps.setString(4, key);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    danhSach.add(mapTaiKhoan(rs));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return danhSach;
    }
    public boolean themTaiKhoan(TaiKhoan taiKhoan) {
        return dangKy(taiKhoan);
    }
    public boolean suaTaiKhoan(TaiKhoan taiKhoan) {
        String sql = "UPDATE tai_khoan SET ten_dang_nhap = ?, mat_khau = ?, ho_ten = ?, email = ?, so_dien_thoai = ?, vai_tro = ?, trang_thai = ? WHERE ma_tai_khoan = ?";
        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, taiKhoan.getTenDangNhap());
            ps.setString(2, taiKhoan.getMatKhau());
            ps.setString(3, taiKhoan.getHoTen());
            ps.setString(4, taiKhoan.getEmail());
            ps.setString(5, taiKhoan.getSoDienThoai());
            ps.setString(6, taiKhoan.getVaiTro());
            ps.setString(7, taiKhoan.getTrangThai());
            ps.setInt(8, taiKhoan.getMaTaiKhoan());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean xoaTaiKhoan(int maTaiKhoan) {
        String sql = "DELETE FROM tai_khoan WHERE ma_tai_khoan = ?";
        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maTaiKhoan);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean doiTrangThai(int maTaiKhoan, String trangThai) {
        String sql = "UPDATE tai_khoan SET trang_thai = ? WHERE ma_tai_khoan = ?";
        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, trangThai);
            ps.setInt(2, maTaiKhoan);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean tonTaiTenDangNhap(String tenDangNhap, int maTaiKhoan) {
        String sql = "SELECT ma_tai_khoan FROM tai_khoan WHERE ten_dang_nhap = ? AND ma_tai_khoan <> ?";
        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tenDangNhap);
            ps.setInt(2, maTaiKhoan);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean doiMatKhau(int maTaiKhoan, String matKhau) {
        String sql = "UPDATE tai_khoan SET mat_khau = ? WHERE ma_tai_khoan = ?";
        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, matKhau);
            ps.setInt(2, maTaiKhoan);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean capNhatMatKhau(int maTaiKhoan, String matKhau) {
        return doiMatKhau(maTaiKhoan, matKhau);
    }
    private TaiKhoan mapTaiKhoan(ResultSet rs) throws Exception {
        TaiKhoan taiKhoan = new TaiKhoan();
        taiKhoan.setMaTaiKhoan(rs.getInt("ma_tai_khoan"));
        taiKhoan.setTenDangNhap(rs.getString("ten_dang_nhap"));
        taiKhoan.setMatKhau(rs.getString("mat_khau"));
        taiKhoan.setHoTen(rs.getString("ho_ten"));
        taiKhoan.setEmail(rs.getString("email"));
        taiKhoan.setSoDienThoai(rs.getString("so_dien_thoai"));
        taiKhoan.setVaiTro(rs.getString("vai_tro"));
        taiKhoan.setTrangThai(rs.getString("trang_thai"));
        return taiKhoan;
    }

    //người dùng cập nhật thông tin cá nhân
    public boolean kiemTraTenDangNhapTonTai(String tenDangNhap, int maTaiKhoan) {
        String sql = "SELECT ma_tai_khoan FROM tai_khoan WHERE ten_dang_nhap = ? AND ma_tai_khoan <> ?";
        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tenDangNhap);
            ps.setInt(2, maTaiKhoan);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean capNhatThongTinCaNhan(int maTaiKhoan, String tenDangNhap, String hoTen, String email, String soDienThoai) {
        String sql = "UPDATE tai_khoan SET ten_dang_nhap = ?, ho_ten = ?, email = ?, so_dien_thoai = ? WHERE ma_tai_khoan = ?";
        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tenDangNhap);
            ps.setString(2, hoTen);
            ps.setString(3, email);
            ps.setString(4, soDienThoai);
            ps.setInt(5, maTaiKhoan);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}