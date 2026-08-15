package controller;

import dao.ChiTietDonHangDAO;
import dao.DonHangDAO;

import model.ChiTietDonHang;
import model.DonHang;
import model.GioHang;
import model.TaiKhoan;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/thanhToan")
public class ThanhToanServlet extends HttpServlet {

    private DonHangDAO donHangDAO;
    private ChiTietDonHangDAO chiTietDonHangDAO;

    @Override
    public void init() {

        donHangDAO = new DonHangDAO();
        chiTietDonHangDAO = new ChiTietDonHangDAO();
    }

    // =====================================================
    // HIỂN THỊ TRANG THANH TOÁN
    // =====================================================

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();

        TaiKhoan taiKhoan =
                (TaiKhoan) session.getAttribute("taiKhoan");

        // Chưa đăng nhập
        if (taiKhoan == null) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/dangNhap.jsp"
            );

            return;
        }

        request.getRequestDispatcher(
                "/thanhToan.jsp"
        ).forward(request, response);
    }

    // =====================================================
    // XỬ LÝ ĐẶT HÀNG
    // =====================================================

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();

        // =================================================
        // 1. Kiểm tra đăng nhập
        // =================================================

        TaiKhoan taiKhoan =
                (TaiKhoan) session.getAttribute("taiKhoan");

        if (taiKhoan == null) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/dangNhap.jsp"
            );

            return;
        }

        // =================================================
        // 2. Lấy giỏ hàng
        // =================================================

        List<GioHang> gioHang =
                (List<GioHang>)
                        session.getAttribute("gioHang");

        if (gioHang == null || gioHang.isEmpty()) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/gioHang"
            );

            return;
        }

        // =================================================
        // 3. Tính tổng tiền
        // =================================================

        double tongTien = 0;

        for (GioHang sp : gioHang) {

            tongTien += sp.getThanhTien();
        }

        // =================================================
        // 4. Lấy thông tin giao hàng
        // =================================================

        String diaChiGiao =
                request.getParameter("diaChiGiao");

        String ghiChu =
                request.getParameter("ghiChu");

        if (diaChiGiao == null) {
            diaChiGiao = "";
        }

        if (ghiChu == null) {
            ghiChu = "";
        }

        // =================================================
        // 5. Lấy mã khách hàng
        // =================================================

        /*
         * CSDL mới sử dụng:
         *
         * ma_khach_hang
         *
         * KHÔNG sử dụng:
         *
         * ma_tai_khoan
         * maTaiKhoan
         */

        int maKhachHang = 0;

        /*
         * Nếu TaiKhoan.java của project có:
         *
         * getMaKhachHang()
         *
         * thì sử dụng dòng dưới.
         */



        // =================================================
        // 6. Tạo đối tượng đơn hàng
        // =================================================

        DonHang donHang = new DonHang();

        donHang.setMaKhachHang(
                maKhachHang
        );



        donHang.setTongTien(
                tongTien
        );

        donHang.setTrangThai(
                "Chờ xác nhận"
        );

        donHang.setDiaChiGiao(
                diaChiGiao
        );

        donHang.setGhiChu(
                ghiChu
        );

        // =================================================
        // 7. Thêm đơn hàng
        // =================================================

        int maDon =
                donHangDAO.themDonHangLayMa(
                        donHang
                );

        // =================================================
        // 8. Kiểm tra tạo đơn
        // =================================================

        if (maDon <= 0) {

            request.setAttribute(
                    "loi",
                    "Đặt hàng thất bại!"
            );

            request.getRequestDispatcher(
                    "/thanhToan.jsp"
            ).forward(
                    request,
                    response
            );

            return;
        }

        // =================================================
        // 9. Tạo danh sách chi tiết đơn hàng
        // =================================================

        List<ChiTietDonHang> danhSach =
                new ArrayList<>();

        for (GioHang sp : gioHang) {

            ChiTietDonHang ct =
                    new ChiTietDonHang();

            /*
             * CSDL mới sử dụng ma_don.
             *
             * Model ChiTietDonHang phải có:
             *
             * setMaDon()
             *
             * Không sử dụng setMaDonHang().
             */

            ct.setMaDon(
                    maDon
            );

            ct.setMaSanPham(
                    sp.getMaSanPham()
            );

            ct.setSoLuong(
                    sp.getSoLuong()
            );

            ct.setDonGia(
                    sp.getGia()
            );

            ct.setThanhTien(
                    sp.getThanhTien()
            );

            danhSach.add(ct);
        }

        // =================================================
        // 10. Thêm chi tiết đơn hàng
        // =================================================

        boolean themChiTiet =
                chiTietDonHangDAO.themDanhSachChiTiet(
                        danhSach
                );

        // =================================================
        // 11. Kiểm tra thêm chi tiết
        // =================================================

        if (!themChiTiet) {

            /*
             * Nếu thêm chi tiết thất bại,
             * thông báo lỗi.
             */

            request.setAttribute(
                    "loi",
                    "Tạo đơn hàng thành công nhưng không thể lưu chi tiết đơn hàng!"
            );

            request.getRequestDispatcher(
                    "/thanhToan.jsp"
            ).forward(
                    request,
                    response
            );

            return;
        }

        // =================================================
        // 12. Xóa giỏ hàng
        // =================================================

        session.removeAttribute(
                "gioHang"
        );

        // =================================================
        // 13. Thông báo thành công
        // =================================================

        request.setAttribute(
                "thongBao",
                "Đặt hàng thành công!"
        );

        request.setAttribute(
                "maDon",
                maDon
        );

        // =================================================
        // 14. Hiển thị lại trang thanh toán
        // =================================================

        request.getRequestDispatcher(
                "/thanhToan.jsp"
        ).forward(
                request,
                response
        );
    }
}

