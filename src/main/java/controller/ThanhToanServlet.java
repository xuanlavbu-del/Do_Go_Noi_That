package controller;

import dao.ChiTietDonHangDAO;
import dao.DonHangDAO;
import dao.GioHangDAO;
import dao.KhachHangDAO;
import service.VietQRService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.ChiTietDonHang;
import model.DonHang;
import model.GioHang;
import model.KhachHang;
import model.TaiKhoan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/thanhToan")
public class ThanhToanServlet extends HttpServlet {

    private DonHangDAO donHangDAO;
    private ChiTietDonHangDAO chiTietDonHangDAO;
    private GioHangDAO gioHangDAO;
    private KhachHangDAO khachHangDAO;
    private VietQRService vietQRService;

    // =====================================================
    // INIT
    // =====================================================

    @Override
    public void init() {

        donHangDAO = new DonHangDAO();

        chiTietDonHangDAO =
                new ChiTietDonHangDAO();

        gioHangDAO =
                new GioHangDAO();

        khachHangDAO =
                new KhachHangDAO();
    }


    // =====================================================
    // GET
    // Hiển thị trang thanh toán
    // =====================================================

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session =
                request.getSession();


        // -------------------------------------------------
        // Kiểm tra đăng nhập
        // -------------------------------------------------

        Integer maTaiKhoan =
                layMaTaiKhoan(session);

        if (maTaiKhoan == null) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/dangNhap"
            );

            return;
        }


        // -------------------------------------------------
        // Lấy giỏ hàng từ DATABASE
        // -------------------------------------------------

        List<GioHang> gioHang =
                gioHangDAO.layGioHang(
                        maTaiKhoan
                );


        if (gioHang == null ||
                gioHang.isEmpty()) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/gioHang"
            );

            return;
        }


        // -------------------------------------------------
        // Tính tổng tiền
        // -------------------------------------------------

        double tongTien =
                tinhTongTien(gioHang);


        // -------------------------------------------------
        // Đưa dữ liệu sang JSP
        // -------------------------------------------------

        request.setAttribute(
                "gioHang",
                gioHang
        );

        request.setAttribute(
                "tongTien",
                tongTien
        );


        request.getRequestDispatcher(
                "/thanhToan.jsp"
        ).forward(
                request,
                response
        );
    }


    // =====================================================
    // POST
    // ĐẶT HÀNG
    // =====================================================

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");


        HttpSession session =
                request.getSession();


        // =================================================
        // 1. KIỂM TRA ĐĂNG NHẬP
        // =================================================

        Integer maTaiKhoan =
                layMaTaiKhoan(session);


        if (maTaiKhoan == null) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/dangNhap"
            );

            return;
        }


        // =================================================
        // 2. LẤY TÀI KHOẢN
        // =================================================

        TaiKhoan taiKhoan =
                (TaiKhoan)
                        session.getAttribute(
                                "taiKhoan"
                        );


        if (taiKhoan == null) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/dangNhap"
            );

            return;
        }


        // =================================================
        // 3. LẤY KHÁCH HÀNG
        // =================================================

        String email =
                taiKhoan.getEmail();


        if (email == null ||
                email.trim().isEmpty()) {

            hienThiLoi(
                    request,
                    response,
                    "Tài khoản chưa có email!"
            );

            return;
        }


        KhachHang khachHang =
                khachHangDAO
                        .layKhachHangTheoEmail(
                                email
                        );


        if (khachHang == null) {

            hienThiLoi(
                    request,
                    response,
                    "Không tìm thấy thông tin khách hàng!"
            );

            return;
        }


        int maKhachHang =
                khachHang.getMaKhachHang();


        // =================================================
        // 4. LẤY GIỎ HÀNG TỪ DATABASE
        // =================================================

        List<GioHang> gioHang =
                gioHangDAO.layGioHang(
                        maTaiKhoan
                );


        if (gioHang == null ||
                gioHang.isEmpty()) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/gioHang"
            );

            return;
        }


        // =================================================
        // 5. TÍNH TỔNG TIỀN
        // =================================================

        double tongTien =
                tinhTongTien(gioHang);


        if (tongTien <= 0) {

            hienThiLoi(
                    request,
                    response,
                    "Tổng tiền đơn hàng không hợp lệ!"
            );

            return;
        }


        // =================================================
        // 6. LẤY THÔNG TIN GIAO HÀNG
        // =================================================

        String diaChiGiao =
                request.getParameter(
                        "diaChiGiao"
                );

        String ghiChu =
                request.getParameter(
                        "ghiChu"
                );


        if (diaChiGiao == null) {
            diaChiGiao = "";
        }

        if (ghiChu == null) {
            ghiChu = "";
        }


        diaChiGiao =
                diaChiGiao.trim();

        ghiChu =
                ghiChu.trim();


        // =================================================
        // 7. LẤY PHƯƠNG THỨC THANH TOÁN
        // =================================================

        String phuongThucThanhToan =
                request.getParameter(
                        "phuongThucThanhToan"
                );


        if (phuongThucThanhToan == null ||
                phuongThucThanhToan.trim().isEmpty()) {

            phuongThucThanhToan =
                    "COD";
        }


        phuongThucThanhToan =
                phuongThucThanhToan.trim()
                        .toUpperCase();


        // =================================================
        // 8. CHỈ CHO PHÉP 2 PHƯƠNG THỨC
        // =================================================

        if (!"COD".equals(
                phuongThucThanhToan)

                &&

                !"VBSP".equals(
                        phuongThucThanhToan)) {

            hienThiLoi(
                    request,
                    response,
                    "Phương thức thanh toán không hợp lệ!"
            );

            return;
        }


        // =================================================
        // 9. TẠO ĐƠN HÀNG
        // =================================================

        DonHang donHang =
                new DonHang();


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
        // 10. THÔNG TIN THANH TOÁN
        // =================================================

        donHang.setPhuongThucThanhToan(
                phuongThucThanhToan
        );


        /*
         * COD:
         *
         * Chưa thanh toán.
         *
         * VBSP:
         *
         * Cũng chưa thanh toán.
         *
         * Chỉ khi hệ thống xác nhận
         * tiền thực sự đã vào tài khoản
         * mới chuyển thành DA_THANH_TOAN.
         */

        donHang.setTrangThaiThanhToan(
                "CHUA_THANH_TOAN"
        );


        donHang.setMaGiaoDich(
                null
        );


        // =================================================
        // 11. TẠM CHƯA CÓ MÃ ĐƠN
        // =================================================

        /*
         * maDon chỉ có sau INSERT.
         *
         * Vì vậy chưa thể tạo:
         *
         * DH123
         *
         * ở thời điểm này.
         *
         * Sau khi INSERT lấy được maDon,
         * chúng ta mới tạo nội dung thanh toán.
         */

        donHang.setNoiDungThanhToan(
                null
        );


        // =================================================
        // 12. TẠO ĐƠN HÀNG
        // =================================================

        int maDon =
                donHangDAO.themDonHangLayMa(
                        donHang
                );


        if (maDon <= 0) {

            hienThiLoi(
                    request,
                    response,
                    "Không thể tạo đơn hàng!"
            );

            return;
        }


        // =================================================
        // 13. TẠO NỘI DUNG THANH TOÁN
        // =================================================

        String noiDungThanhToan =
                "DH" + maDon;


        /*
         * Ví dụ:
         *
         * maDon = 125
         *
         * => DH125
         *
         * Khách hàng sẽ chuyển khoản
         * với nội dung:
         *
         * DH125
         */

        boolean capNhatNoiDung =
                donHangDAO.capNhatNoiDungThanhToan(
                        maDon,
                        noiDungThanhToan
                );


        if (!capNhatNoiDung) {

            hienThiLoi(
                    request,
                    response,
                    "Tạo đơn hàng thành công nhưng không thể tạo nội dung thanh toán!"
            );

            return;
        }


        // =================================================
        // 14. TẠO CHI TIẾT ĐƠN HÀNG
        // =================================================

        List<ChiTietDonHang> danhSach =
                new ArrayList<>();


        for (GioHang sp : gioHang) {

            ChiTietDonHang ct =
                    new ChiTietDonHang();


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


            danhSach.add(
                    ct
            );
        }


        // =================================================
        // 15. LƯU CHI TIẾT
        // =================================================

        boolean themChiTiet =
                chiTietDonHangDAO
                        .themDanhSachChiTiet(
                                danhSach
                        );


        if (!themChiTiet) {

            /*
             * Đơn hàng đã được tạo nhưng
             * chi tiết không lưu được.
             *
             * Không xóa giỏ hàng.
             */

            hienThiLoi(
                    request,
                    response,
                    "Không thể lưu chi tiết đơn hàng!"
            );

            return;
        }


        // =================================================
        // 16. XÓA GIỎ HÀNG DATABASE
        // =================================================

        boolean xoaGioHang =
                gioHangDAO.xoaTatCa(
                        maTaiKhoan
                );


        if (!xoaGioHang) {

            /*
             * Không làm thất bại đơn hàng.
             *
             * Đơn đã được lưu.
             *
             * Chỉ ghi log để kiểm tra.
             */

            System.out.println(
                    "CẢNH BÁO: Không thể xóa giỏ hàng. "
                            + "maTaiKhoan="
                            + maTaiKhoan
            );
        }


        // =================================================
        // 17. LƯU THÔNG TIN ĐƠN VÀO REQUEST
        // =================================================

        request.setAttribute(
                "maDon",
                maDon
        );


        request.setAttribute(
                "tongTien",
                tongTien
        );


        request.setAttribute(
                "phuongThucThanhToan",
                phuongThucThanhToan
        );


        request.setAttribute(
                "noiDungThanhToan",
                noiDungThanhToan
        );


        // =================================================
        // 18. COD
        // =================================================

        if ("COD".equals(
                phuongThucThanhToan)) {

            request.setAttribute(
                    "thongBao",
                    "Đặt hàng thành công! Bạn sẽ thanh toán khi nhận hàng."
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
        // 19. VBSP
        // =================================================

        if ("VBSP".equals(
                phuongThucThanhToan)) {

            /*
             * ==========================================
             * TẠO QR
             * ==========================================
             */

            VietQRService qrService =
                    new VietQRService();


            String qrUrl =
                    qrService.taoQR(
                            tongTien,
                            noiDungThanhToan
                    );


            request.setAttribute(
                    "qrUrl",
                    qrUrl
            );


            request.setAttribute(
                    "thongBao",
                    "Đơn hàng đã được tạo. Vui lòng quét mã QR bằng VBSP SmartBanking để thanh toán."
            );


            request.getRequestDispatcher(
                    "/thanhToan.jsp"
            ).forward(
                    request,
                    response
            );

            return;
        }
    }


    // =====================================================
    // LẤY MÃ TÀI KHOẢN TỪ SESSION
    // =====================================================

    private Integer layMaTaiKhoan(
            HttpSession session) {

        Object value =
                session.getAttribute(
                        "maTaiKhoan"
                );


        if (value == null) {
            return null;
        }


        if (value instanceof Integer) {

            return (Integer) value;
        }


        try {

            return Integer.parseInt(
                    value.toString()
            );

        } catch (NumberFormatException e) {

            return null;
        }
    }


    // =====================================================
    // TÍNH TỔNG TIỀN
    // =====================================================

    private double tinhTongTien(
            List<GioHang> gioHang) {

        double tongTien = 0;


        for (GioHang sp : gioHang) {

            if (sp == null) {
                continue;
            }


            if (sp.getSoLuong() <= 0) {
                continue;
            }


            if (sp.getGia() < 0) {
                continue;
            }


            tongTien +=
                    sp.getThanhTien();
        }


        return tongTien;
    }


    // =====================================================
    // HIỂN THỊ LỖI
    // =====================================================

    private void hienThiLoi(
            HttpServletRequest request,
            HttpServletResponse response,
            String loi)
            throws ServletException, IOException {

        request.setAttribute(
                "loi",
                loi
        );


        request.getRequestDispatcher(
                "/thanhToan.jsp"
        ).forward(
                request,
                response
        );
    }
}