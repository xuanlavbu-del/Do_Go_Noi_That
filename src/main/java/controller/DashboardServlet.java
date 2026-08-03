package controller;

import dao.KhachHangDAO;
import dao.SanPhamDAO;
import dao.TaiKhoanDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    private SanPhamDAO sanPhamDAO;

    private KhachHangDAO khachHangDAO;

    private TaiKhoanDAO taiKhoanDAO;

    @Override
    public void init() {

        sanPhamDAO = new SanPhamDAO();

        khachHangDAO = new KhachHangDAO();

        taiKhoanDAO = new TaiKhoanDAO();

    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    )
            throws ServletException, IOException {

        HttpSession session =
                request.getSession(false);

        // Chưa đăng nhập

        if (session == null ||
                session.getAttribute("taiKhoan") == null) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/dangNhap"
            );

            return;

        }

        // Không phải Admin

        String vaiTro =
                (String) session.getAttribute("vaiTro");

        if (vaiTro == null ||
                !vaiTro.equalsIgnoreCase("ADMIN")) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/index.jsp"
            );

            return;

        }

        // ==========================
        // Thống kê
        // ==========================

        int tongSanPham = 0;

        int tongKhachHang = 0;

        int tongTaiKhoan = 0;

        int tongDonHang = 0;

        try {

            tongSanPham =
                    sanPhamDAO
                            .layTatCaSanPham()
                            .size();

        } catch (Exception e) {

            e.printStackTrace();

        }

        try {

            tongKhachHang =
                    khachHangDAO
                            .layTatCaKhachHang()
                            .size();

        } catch (Exception e) {

            e.printStackTrace();

        }

        try {

            tongTaiKhoan =
                    taiKhoanDAO
                            .layTatCaTaiKhoan()
                            .size();

        } catch (Exception e) {

            e.printStackTrace();

        }

        // Chưa có DAO đơn hàng

        tongDonHang = 0;

        request.setAttribute(
                "tongSanPham",
                tongSanPham
        );

        request.setAttribute(
                "tongKhachHang",
                tongKhachHang
        );

        request.setAttribute(
                "tongTaiKhoan",
                tongTaiKhoan
        );

        request.setAttribute(
                "tongDonHang",
                tongDonHang
        );

        request.getRequestDispatcher(
                "/admin/dashboard.jsp"
        ).forward(
                request,
                response
        );

    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    )
            throws ServletException, IOException {

        doGet(request, response);

    }

}