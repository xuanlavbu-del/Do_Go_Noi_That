package controller;

import dao.GioHangDAO;
import dao.SanPhamDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.GioHang;
import model.SanPham;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/gioHang")
public class GioHangServlet extends HttpServlet {

    private GioHangDAO gioHangDAO;
    private SanPhamDAO sanPhamDAO;


    @Override
    public void init() {

        gioHangDAO = new GioHangDAO();
        sanPhamDAO = new SanPhamDAO();
    }


    // =====================================================
    // Kiểm tra người dùng đã đăng nhập chưa
    // =====================================================

    private boolean daDangNhap(HttpSession session) {

        return session.getAttribute("maTaiKhoan") != null;
    }


    // =====================================================
    // Lấy mã tài khoản hiện tại
    // =====================================================

    private Integer layMaTaiKhoan(HttpSession session) {

        Object value =
                session.getAttribute("maTaiKhoan");

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
    // Lấy giỏ khách chưa đăng nhập
    // =====================================================

    @SuppressWarnings("unchecked")
    private List<GioHang> layGioKhach(
            HttpSession session
    ) {

        List<GioHang> gioHang =
                (List<GioHang>)
                        session.getAttribute(
                                "gioHangKhach"
                        );

        if (gioHang == null) {

            gioHang =
                    new ArrayList<>();

            session.setAttribute(
                    "gioHangKhach",
                    gioHang
            );
        }

        return gioHang;
    }


    // =====================================================
    // GET /gioHang
    //
    // - Xem giỏ
    // - Tăng số lượng
    // - Giảm số lượng
    // - Xóa sản phẩm
    // =====================================================

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        HttpSession session =
                request.getSession();


        // =================================================
        // NGƯỜI ĐÃ ĐĂNG NHẬP
        // =================================================

        if (daDangNhap(session)) {

            Integer maTaiKhoan =
                    layMaTaiKhoan(session);

            if (maTaiKhoan == null) {

                response.sendRedirect(
                        request.getContextPath()
                                + "/dangNhap"
                );

                return;
            }


            String hanhDong =
                    request.getParameter(
                            "hanhDong"
                    );


            String maSanPhamParam =
                    request.getParameter(
                            "maSanPham"
                    );


            // =============================================
            // XÓA
            // =============================================

            if ("xoa".equals(hanhDong)
                    && maSanPhamParam != null) {

                int maSanPham =
                        Integer.parseInt(
                                maSanPhamParam
                        );

                gioHangDAO.xoaSanPham(
                        maTaiKhoan,
                        maSanPham
                );
            }


            // =============================================
            // CẬP NHẬT SỐ LƯỢNG
            // =============================================

            else if ("capNhat".equals(hanhDong)
                    && maSanPhamParam != null) {

                int maSanPham =
                        Integer.parseInt(
                                maSanPhamParam
                        );

                int soLuong =
                        Integer.parseInt(
                                request.getParameter(
                                        "soLuong"
                                )
                        );

                if (soLuong < 1) {
                    soLuong = 1;
                }

                gioHangDAO.capNhatSoLuong(
                        maTaiKhoan,
                        maSanPham,
                        soLuong
                );
            }


            // =============================================
            // LẤY GIỎ TỪ DATABASE
            // =============================================

            List<GioHang> gioHang =
                    gioHangDAO.layGioHang(
                            maTaiKhoan
                    );


            request.setAttribute(
                    "gioHang",
                    gioHang
            );


            request.setAttribute(
                    "tongSoLuong",
                    tinhTongSoLuong(gioHang)
            );


            request.setAttribute(
                    "tongTien",
                    tinhTongTien(gioHang)
            );


            request.getRequestDispatcher(
                            "gioHang.jsp"
                    )
                    .forward(
                            request,
                            response
                    );

            return;
        }


        // =================================================
        // KHÁCH CHƯA ĐĂNG NHẬP
        // =================================================

        List<GioHang> gioHang =
                layGioKhach(session);


        String hanhDong =
                request.getParameter(
                        "hanhDong"
                );


        String maSanPhamParam =
                request.getParameter(
                        "maSanPham"
                );


        // =============================================
        // XÓA
        // =============================================

        if ("xoa".equals(hanhDong)
                && maSanPhamParam != null) {

            int maSanPham =
                    Integer.parseInt(
                            maSanPhamParam
                    );

            gioHang.removeIf(
                    item ->
                            item.getMaSanPham()
                                    == maSanPham
            );
        }


        // =============================================
        // CẬP NHẬT
        // =============================================

        else if ("capNhat".equals(hanhDong)
                && maSanPhamParam != null) {

            int maSanPham =
                    Integer.parseInt(
                            maSanPhamParam
                    );

            int soLuong =
                    Integer.parseInt(
                            request.getParameter(
                                    "soLuong"
                            )
                    );

            if (soLuong < 1) {
                soLuong = 1;
            }


            for (GioHang item : gioHang) {

                if (item.getMaSanPham()
                        == maSanPham) {

                    item.setSoLuong(
                            soLuong
                    );

                    break;
                }
            }
        }


        // =============================================
        // Lưu lại giỏ khách
        // =============================================

        session.setAttribute(
                "gioHangKhach",
                gioHang
        );


        request.setAttribute(
                "gioHang",
                gioHang
        );


        request.setAttribute(
                "tongSoLuong",
                tinhTongSoLuong(gioHang)
        );


        request.setAttribute(
                "tongTien",
                tinhTongTien(gioHang)
        );


        request.getRequestDispatcher(
                        "gioHang.jsp"
                )
                .forward(
                        request,
                        response
                );
    }


    // =====================================================
    // POST /gioHang
    //
    // Thêm sản phẩm vào giỏ
    // =====================================================

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();

        // =====================================================
        // Lấy mã sản phẩm
        // =====================================================

        String maSanPhamParam =
                request.getParameter("maSanPham");

        if (maSanPhamParam == null ||
                maSanPhamParam.trim().isEmpty()) {

            response.sendRedirect(
                    request.getContextPath() + "/sanPham"
            );

            return;
        }

        int maSanPham;

        try {

            maSanPham =
                    Integer.parseInt(maSanPhamParam);

        } catch (NumberFormatException e) {

            response.sendRedirect(
                    request.getContextPath() + "/sanPham"
            );

            return;
        }


        // =====================================================
        // Lấy số lượng
        // =====================================================

        int soLuong = 1;

        String soLuongParam =
                request.getParameter("soLuong");

        if (soLuongParam != null &&
                !soLuongParam.trim().isEmpty()) {

            try {

                soLuong =
                        Integer.parseInt(soLuongParam);

            } catch (NumberFormatException e) {

                soLuong = 1;
            }
        }

        if (soLuong < 1) {
            soLuong = 1;
        }


        // =====================================================
        // Lấy sản phẩm
        // =====================================================

        SanPham sanPham =
                sanPhamDAO.laySanPhamTheoMa(
                        maSanPham
                );

        if (sanPham == null) {

            response.sendRedirect(
                    request.getContextPath() + "/sanPham"
            );

            return;
        }


        // =====================================================
        // ĐÃ ĐĂNG NHẬP
        // =====================================================

        if (daDangNhap(session)) {

            Integer maTaiKhoan =
                    layMaTaiKhoan(session);

            if (maTaiKhoan == null) {

                response.sendRedirect(
                        request.getContextPath()
                                + "/dangNhap"
                );

                return;
            }


            // Thêm vào database
            gioHangDAO.themVaoGioHang(
                    maTaiKhoan,
                    maSanPham,
                    soLuong
            );


            // =================================================
            // QUAN TRỌNG:
            // Không dùng Referer.
            // Luôn chuyển tới /gioHang.
            // =================================================

            response.sendRedirect(
                    request.getContextPath()
                            + "/gioHang"
            );

            return;
        }


        // =====================================================
        // CHƯA ĐĂNG NHẬP
        // =====================================================

        List<GioHang> gioHang =
                layGioKhach(session);

        boolean daCo = false;


        // =====================================================
        // Nếu sản phẩm đã có → cộng số lượng
        // =====================================================

        for (GioHang item : gioHang) {

            if (item.getMaSanPham()
                    == maSanPham) {

                item.setSoLuong(
                        item.getSoLuong()
                                + soLuong
                );

                daCo = true;

                break;
            }
        }


        // =====================================================
        // Nếu chưa có → thêm mới
        // =====================================================

        if (!daCo) {

            GioHang item =
                    new GioHang();

            item.setMaTaiKhoan(0);

            item.setMaSanPham(
                    sanPham.getMaSanPham()
            );

            item.setTenSanPham(
                    sanPham.getTenSanPham()
            );

            item.setGia(
                    sanPham.getGia()
            );

            item.setSoLuong(
                    soLuong
            );

            item.setHinhAnh(
                    sanPham.getHinhAnh()
            );

            gioHang.add(item);
        }


        // =====================================================
        // Lưu giỏ khách
        // =====================================================

        session.setAttribute(
                "gioHangKhach",
                gioHang
        );


        // =====================================================
        // Luôn chuyển tới /gioHang
        // =====================================================

        response.sendRedirect(
                request.getContextPath()
                        + "/gioHang"
        );
    }
    // =====================================================
// Tính tổng số lượng sản phẩm trong giỏ
// =====================================================

    private int tinhTongSoLuong(List<GioHang> gioHang) {

        int tong = 0;

        if (gioHang == null) {
            return 0;
        }

        for (GioHang item : gioHang) {

            tong += item.getSoLuong();
        }

        return tong;
    }


// =====================================================
// Tính tổng tiền trong giỏ
// =====================================================

    private double tinhTongTien(List<GioHang> gioHang) {

        double tong = 0;

        if (gioHang == null) {
            return 0;
        }

        for (GioHang item : gioHang) {

            tong += item.getThanhTien();
        }

        return tong;
    }
}