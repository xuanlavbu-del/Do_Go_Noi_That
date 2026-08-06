package dao;

import ketnoi.KetNoiCSDL;
import model.TonKho;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TonKhoDAO {

    // ==========================================
    // Danh sách tồn kho
    // ==========================================

    public List<TonKho> getAllTonKho() {

        List<TonKho> ds = new ArrayList<>();

        String sql =
                "SELECT tk.ma_ton," +
                        "tk.ma_san_pham," +
                        "tk.ma_kho," +
                        "tk.so_luong," +
                        "sp.ten_san_pham," +
                        "k.ten_kho," +
                        "sp.gia " +
                        "FROM ton_kho tk " +
                        "JOIN san_pham sp ON tk.ma_san_pham=sp.ma_san_pham " +
                        "JOIN kho k ON tk.ma_kho=k.ma_kho " +
                        "ORDER BY k.ten_kho,sp.ten_san_pham";

        try(
                Connection conn= KetNoiCSDL.getConnection();
                PreparedStatement ps=conn.prepareStatement(sql);
                ResultSet rs=ps.executeQuery()
        ){

            while(rs.next()){

                TonKho tk=new TonKho();

                tk.setMaTon(rs.getInt("ma_ton"));
                tk.setMaSanPham(rs.getInt("ma_san_pham"));
                tk.setMaKho(rs.getInt("ma_kho"));
                tk.setSoLuong(rs.getInt("so_luong"));
                tk.setTenSanPham(rs.getString("ten_san_pham"));
                tk.setTenKho(rs.getString("ten_kho"));
                tk.setGia(rs.getDouble("gia"));

                ds.add(tk);

            }

        }catch(Exception e){

            e.printStackTrace();

        }

        return ds;

    }


    // ==========================================
    // Tìm kiếm
    // ==========================================

    public List<TonKho> search(String keyword) {

        List<TonKho> ds = new ArrayList<>();

        String sql = """
                SELECT

                    tk.ma_ton,

                    tk.ma_san_pham,

                    tk.ma_kho,

                    tk.so_luong,

                    sp.ten_san_pham,

                    sp.gia,

                    k.ten_kho

                FROM ton_kho tk

                INNER JOIN san_pham sp

                    ON tk.ma_san_pham = sp.ma_san_pham

                INNER JOIN kho k

                    ON tk.ma_kho = k.ma_kho

                WHERE

                    sp.ten_san_pham LIKE ?

                    OR

                    k.ten_kho LIKE ?

                ORDER BY sp.ten_san_pham
                """;

        try (

                Connection conn =
                        KetNoiCSDL.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql);

        ) {

            String key = "%" + keyword + "%";

            ps.setString(1, key);
            ps.setString(2, key);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                TonKho tk = new TonKho();

                tk.setMaTon(
                        rs.getInt("ma_ton"));

                tk.setMaSanPham(
                        rs.getInt("ma_san_pham"));

                tk.setMaKho(
                        rs.getInt("ma_kho"));

                tk.setSoLuong(
                        rs.getInt("so_luong"));

                tk.setTenSanPham(
                        rs.getString("ten_san_pham"));

                tk.setGia(
                        rs.getDouble("gia"));

                tk.setTenKho(
                        rs.getString("ten_kho"));

                ds.add(tk);

            }

        }

        catch (Exception e) {

            e.printStackTrace();

        }

        return ds;

    }

    // ==========================================
    // Lấy tồn kho theo mã
    // ==========================================

    public TonKho findById(int maTon) {

        String sql = """
                SELECT

                    tk.ma_ton,

                    tk.ma_san_pham,

                    tk.ma_kho,

                    tk.so_luong,

                    sp.ten_san_pham,

                    sp.gia,

                    k.ten_kho

                FROM ton_kho tk

                INNER JOIN san_pham sp

                    ON tk.ma_san_pham = sp.ma_san_pham

                INNER JOIN kho k

                    ON tk.ma_kho = k.ma_kho

                WHERE

                    tk.ma_ton = ?
                """;

        try (

                Connection conn =
                        KetNoiCSDL.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql);

        ) {

            ps.setInt(1, maTon);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                TonKho tk = new TonKho();

                tk.setMaTon(
                        rs.getInt("ma_ton"));

                tk.setMaSanPham(
                        rs.getInt("ma_san_pham"));

                tk.setMaKho(
                        rs.getInt("ma_kho"));

                tk.setSoLuong(
                        rs.getInt("so_luong"));

                tk.setTenSanPham(
                        rs.getString("ten_san_pham"));

                tk.setGia(
                        rs.getDouble("gia"));

                tk.setTenKho(
                        rs.getString("ten_kho"));

                return tk;

            }

        }

        catch (Exception e) {

            e.printStackTrace();

        }

        return null;

    }


// ==========================================
// Cập nhật số lượng tồn kho
// ==========================================

public boolean capNhatSoLuong(int maSanPham,
                              int maKho,
                              int soLuongMoi) {

    String sql = """
                UPDATE ton_kho
                SET so_luong = ?
                WHERE ma_san_pham = ?
                AND ma_kho = ?
                """;

    try (

            Connection conn =
                    KetNoiCSDL.getConnection();

            PreparedStatement ps =
                    conn.prepareStatement(sql);

    ) {

        ps.setInt(1, soLuongMoi);
        ps.setInt(2, maSanPham);
        ps.setInt(3, maKho);

        return ps.executeUpdate() > 0;

    } catch (Exception e) {

        e.printStackTrace();

    }

    return false;

}

// ==========================================
// Tổng số lượng tồn kho
// ==========================================

public int tongSoLuongTon() {

    String sql = """
                SELECT IFNULL(SUM(so_luong),0)
                FROM ton_kho
                """;

    try (

            Connection conn =
                    KetNoiCSDL.getConnection();

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery()

    ) {

        if (rs.next()) {

            return rs.getInt(1);

        }

    } catch (Exception e) {

        e.printStackTrace();

    }

    return 0;

}

// ==========================================
// Tổng giá trị kho
// ==========================================

public double tongGiaTriKho() {

    String sql = """
                SELECT

                    IFNULL(SUM(tk.so_luong * sp.gia),0)

                FROM ton_kho tk

                INNER JOIN san_pham sp

                ON tk.ma_san_pham = sp.ma_san_pham
                """;

    try (

            Connection conn =
                    KetNoiCSDL.getConnection();

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery()

    ) {

        if (rs.next()) {

            return rs.getDouble(1);

        }

    } catch (Exception e) {

        e.printStackTrace();

    }

    return 0;

}

// ==========================================
// Sản phẩm sắp hết hàng (<=5)
// ==========================================

public List<TonKho> sanPhamSapHet() {

    List<TonKho> ds = new ArrayList<>();

    String sql = """
                SELECT

                    tk.ma_ton,

                    tk.ma_san_pham,

                    tk.ma_kho,

                    tk.so_luong,

                    sp.ten_san_pham,

                    sp.gia,

                    k.ten_kho

                FROM ton_kho tk

                INNER JOIN san_pham sp

                    ON tk.ma_san_pham = sp.ma_san_pham

                INNER JOIN kho k

                    ON tk.ma_kho = k.ma_kho

                WHERE tk.so_luong <= 5

                ORDER BY tk.so_luong ASC
                """;

    try (

            Connection conn =
                    KetNoiCSDL.getConnection();

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery()

    ) {

        while (rs.next()) {

            TonKho tk = new TonKho();

            tk.setMaTon(rs.getInt("ma_ton"));
            tk.setMaSanPham(rs.getInt("ma_san_pham"));
            tk.setMaKho(rs.getInt("ma_kho"));
            tk.setSoLuong(rs.getInt("so_luong"));
            tk.setTenSanPham(rs.getString("ten_san_pham"));
            tk.setGia(rs.getDouble("gia"));
            tk.setTenKho(rs.getString("ten_kho"));

            ds.add(tk);

        }

    } catch (Exception e) {

        e.printStackTrace();

    }

    return ds;

}

// ==========================================
// Sản phẩm hết hàng
// ==========================================

public List<TonKho> sanPhamHetHang() {

    List<TonKho> ds = new ArrayList<>();

    String sql = """
                SELECT

                    tk.ma_ton,

                    tk.ma_san_pham,

                    tk.ma_kho,

                    tk.so_luong,

                    sp.ten_san_pham,

                    sp.gia,

                    k.ten_kho

                FROM ton_kho tk

                INNER JOIN san_pham sp

                    ON tk.ma_san_pham = sp.ma_san_pham

                INNER JOIN kho k

                    ON tk.ma_kho = k.ma_kho

                WHERE tk.so_luong = 0

                ORDER BY sp.ten_san_pham
                """;

    try (

            Connection conn =
                    KetNoiCSDL.getConnection();

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery()

    ) {

        while (rs.next()) {

            TonKho tk = new TonKho();

            tk.setMaTon(rs.getInt("ma_ton"));
            tk.setMaSanPham(rs.getInt("ma_san_pham"));
            tk.setMaKho(rs.getInt("ma_kho"));
            tk.setSoLuong(rs.getInt("so_luong"));
            tk.setTenSanPham(rs.getString("ten_san_pham"));
            tk.setGia(rs.getDouble("gia"));
            tk.setTenKho(rs.getString("ten_kho"));

            ds.add(tk);

        }

    } catch (Exception e) {

        e.printStackTrace();

    }

    return ds;

}
// ==========================================
// Danh sách tồn kho theo kho
// ==========================================

public List<TonKho> getByKho(int maKho) {

    List<TonKho> ds = new ArrayList<>();

    String sql = """
                SELECT
                    tk.ma_ton,
                    tk.ma_san_pham,
                    tk.ma_kho,
                    tk.so_luong,
                    sp.ten_san_pham,
                    sp.gia,
                    k.ten_kho
                FROM ton_kho tk
                INNER JOIN san_pham sp
                    ON tk.ma_san_pham = sp.ma_san_pham
                INNER JOIN kho k
                    ON tk.ma_kho = k.ma_kho
                WHERE tk.ma_kho = ?
                ORDER BY sp.ten_san_pham
                """;

    try (
            Connection conn = KetNoiCSDL.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
    ) {

        ps.setInt(1, maKho);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            TonKho tk = new TonKho();

            tk.setMaTon(rs.getInt("ma_ton"));
            tk.setMaSanPham(rs.getInt("ma_san_pham"));
            tk.setMaKho(rs.getInt("ma_kho"));
            tk.setSoLuong(rs.getInt("so_luong"));
            tk.setTenSanPham(rs.getString("ten_san_pham"));
            tk.setGia(rs.getDouble("gia"));
            tk.setTenKho(rs.getString("ten_kho"));

            ds.add(tk);
        }

    } catch (Exception e) {

        e.printStackTrace();

    }

    return ds;
}

// ==========================================
// Danh sách tồn kho theo sản phẩm
// ==========================================

public List<TonKho> getBySanPham(int maSanPham) {

    List<TonKho> ds = new ArrayList<>();

    String sql = """
                SELECT
                    tk.ma_ton,
                    tk.ma_san_pham,
                    tk.ma_kho,
                    tk.so_luong,
                    sp.ten_san_pham,
                    sp.gia,
                    k.ten_kho
                FROM ton_kho tk
                INNER JOIN san_pham sp
                    ON tk.ma_san_pham = sp.ma_san_pham
                INNER JOIN kho k
                    ON tk.ma_kho = k.ma_kho
                WHERE tk.ma_san_pham = ?
                ORDER BY k.ten_kho
                """;

    try (
            Connection conn = KetNoiCSDL.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
    ) {

        ps.setInt(1, maSanPham);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            TonKho tk = new TonKho();

            tk.setMaTon(rs.getInt("ma_ton"));
            tk.setMaSanPham(rs.getInt("ma_san_pham"));
            tk.setMaKho(rs.getInt("ma_kho"));
            tk.setSoLuong(rs.getInt("so_luong"));
            tk.setTenSanPham(rs.getString("ten_san_pham"));
            tk.setGia(rs.getDouble("gia"));
            tk.setTenKho(rs.getString("ten_kho"));

            ds.add(tk);

        }

    } catch (Exception e) {

        e.printStackTrace();

    }

    return ds;

}

// ==========================================
// Lấy số lượng tồn
// ==========================================

public int laySoLuongTon(int maSanPham, int maKho) {

    String sql = """
                SELECT so_luong
                FROM ton_kho
                WHERE ma_san_pham = ?
                AND ma_kho = ?
                """;

    try (
            Connection conn = KetNoiCSDL.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
    ) {

        ps.setInt(1, maSanPham);
        ps.setInt(2, maKho);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

            return rs.getInt("so_luong");

        }

    } catch (Exception e) {

        e.printStackTrace();

    }

    return 0;

}

// ==========================================
// Kiểm tra tồn kho đã tồn tại
// ==========================================

public boolean tonTai(int maSanPham, int maKho) {

    String sql = """
                SELECT COUNT(*)
                FROM ton_kho
                WHERE ma_san_pham = ?
                AND ma_kho = ?
                """;

    try (
            Connection conn = KetNoiCSDL.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
    ) {

        ps.setInt(1, maSanPham);
        ps.setInt(2, maKho);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

            return rs.getInt(1) > 0;

        }

    } catch (Exception e) {

        e.printStackTrace();

    }

    return false;

}

// ==========================================
// Thống kê theo kho
// ==========================================
public List<TonKho> getTonKhoTheoKho(int maKho) {

    List<TonKho> ds = new ArrayList<>();

    String sql =
            """
            SELECT
                tk.*,
                sp.ten_san_pham,
                sp.gia,
                k.ten_kho
            FROM ton_kho tk
            INNER JOIN san_pham sp
                ON tk.ma_san_pham=sp.ma_san_pham
            INNER JOIN kho k
                ON tk.ma_kho=k.ma_kho
            WHERE tk.ma_kho=?
            ORDER BY sp.ten_san_pham
            """;

    try(
            Connection conn=KetNoiCSDL.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql)
    ){

        ps.setInt(1,maKho);

        ResultSet rs=ps.executeQuery();

        while(rs.next()){

            TonKho tk=new TonKho();

            tk.setMaTon(rs.getInt("ma_ton"));
            tk.setMaSanPham(rs.getInt("ma_san_pham"));
            tk.setMaKho(rs.getInt("ma_kho"));
            tk.setSoLuong(rs.getInt("so_luong"));
            tk.setTenSanPham(rs.getString("ten_san_pham"));
            tk.setTenKho(rs.getString("ten_kho"));
            tk.setGia(rs.getDouble("gia"));

            ds.add(tk);

        }

    }catch(Exception e){

        e.printStackTrace();

    }

    return ds;

}


// Thống kê theo sản phẩm
// ==========================================

    public List<Object[]> thongKeTheoSanPham() {

        List<Object[]> ds = new ArrayList<>();

        String sql = """
            SELECT
                sp.ten_san_pham,
                SUM(tk.so_luong) AS tong_so_luong,
                SUM(tk.so_luong * sp.gia) AS tong_gia_tri
            FROM ton_kho tk
            INNER JOIN san_pham sp
                ON tk.ma_san_pham = sp.ma_san_pham
            GROUP BY
                sp.ma_san_pham,
                sp.ten_san_pham
            ORDER BY tong_gia_tri DESC
            """;

        try (
                Connection conn = KetNoiCSDL.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                ds.add(new Object[]{

                        rs.getString("ten_san_pham"),

                        rs.getInt("tong_so_luong"),

                        rs.getDouble("tong_gia_tri")

                });

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return ds;

    }


    public boolean themTonKho(
            int maSanPham,
            int maKho,
            int soLuong) {

        String sql = """
            INSERT INTO ton_kho
            (
                ma_san_pham,
                ma_kho,
                so_luong
            )
            VALUES
            (
                ?,
                ?,
                ?
            )
            """;

        try (

                Connection conn =
                        KetNoiCSDL.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql);

        ) {

            ps.setInt(1, maSanPham);
            ps.setInt(2, maKho);
            ps.setInt(3, soLuong);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;

    }

    public TonKho layTonKho(int maSanPham,int maKho){

        String sql=
                "SELECT tk.*,sp.ten_san_pham,k.ten_kho,sp.gia " +
                        "FROM ton_kho tk " +
                        "JOIN san_pham sp ON tk.ma_san_pham=sp.ma_san_pham " +
                        "JOIN kho k ON tk.ma_kho=k.ma_kho " +
                        "WHERE tk.ma_san_pham=? AND tk.ma_kho=?";

        try(
                Connection conn=KetNoiCSDL.getConnection();
                PreparedStatement ps=conn.prepareStatement(sql)
        ){

            ps.setInt(1,maSanPham);
            ps.setInt(2,maKho);

            ResultSet rs=ps.executeQuery();

            if(rs.next()){

                TonKho tk=new TonKho();

                tk.setMaTon(rs.getInt("ma_ton"));
                tk.setMaSanPham(rs.getInt("ma_san_pham"));
                tk.setMaKho(rs.getInt("ma_kho"));
                tk.setSoLuong(rs.getInt("so_luong"));
                tk.setTenSanPham(rs.getString("ten_san_pham"));
                tk.setTenKho(rs.getString("ten_kho"));
                tk.setGia(rs.getDouble("gia"));

                return tk;

            }

        }catch(Exception e){

            e.printStackTrace();

        }

        return null;

    }

    public boolean congTonKho(int maSanPham,int maKho,int soLuong){

        String sql=
                "UPDATE ton_kho SET so_luong=so_luong+? WHERE ma_san_pham=? AND ma_kho=?";

        try(
                Connection conn=KetNoiCSDL.getConnection();
                PreparedStatement ps=conn.prepareStatement(sql)
        ){

            ps.setInt(1,soLuong);
            ps.setInt(2,maSanPham);
            ps.setInt(3,maKho);

            return ps.executeUpdate()>0;

        }catch(Exception e){

            e.printStackTrace();

        }

        return false;

    }

    public boolean truTonKho(int maSanPham,int maKho,int soLuong){

        String sql=
                "UPDATE ton_kho SET so_luong=so_luong-? WHERE ma_san_pham=? AND ma_kho=?";

        try(
                Connection conn=KetNoiCSDL.getConnection();
                PreparedStatement ps=conn.prepareStatement(sql)
        ){

            ps.setInt(1,soLuong);
            ps.setInt(2,maSanPham);
            ps.setInt(3,maKho);

            return ps.executeUpdate()>0;

        }catch(Exception e){

            e.printStackTrace();

        }

        return false;

    }

    public boolean xoaTonKho(int maTon){

        String sql=
                "DELETE FROM ton_kho WHERE ma_ton=?";

        try(
                Connection conn=KetNoiCSDL.getConnection();
                PreparedStatement ps=conn.prepareStatement(sql)
        ){

            ps.setInt(1,maTon);

            return ps.executeUpdate()>0;

        }catch(Exception e){

            e.printStackTrace();

        }

        return false;

    }

}