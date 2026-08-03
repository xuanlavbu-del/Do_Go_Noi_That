package controller;

import dao.KhachHangDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.KhachHang;

import java.io.IOException;
import java.sql.Date;

@WebServlet("/themKhachHang")
public class ThemKhachHangServlet extends HttpServlet {

    private final KhachHangDAO khachHangDAO =
            new KhachHangDAO();

    // ===============================
    // Hiển thị trang thêm khách hàng
    // ===============================

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher(
                "/admin/themKhachHang.jsp"
        ).forward(request, response);

    }

    // ===============================
    // Xử lý thêm khách hàng
    // ===============================

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String hoTen =
                request.getParameter("hoTen");

        String gioiTinh =
                request.getParameter("gioiTinh");

        String ngaySinhStr =
                request.getParameter("ngaySinh");

        String soDienThoai =
                request.getParameter("soDienThoai");

        String email =
                request.getParameter("email");

        String diaChi =
                request.getParameter("diaChi");


        // ===============================
        // Kiểm tra dữ liệu bắt buộc
        // ===============================

        if (hoTen == null || hoTen.trim().isEmpty()
                || soDienThoai == null || soDienThoai.trim().isEmpty()) {

            request.setAttribute(
                    "loi",
                    "Họ tên và số điện thoại không được để trống."
            );

            request.getRequestDispatcher(
                    "/admin/themKhachHang.jsp"
            ).forward(request, response);

            return;
        }

        // ===============================
        // Kiểm tra trùng số điện thoại
        // ===============================

        if (khachHangDAO.kiemTraTrungSoDienThoai(soDienThoai)) {

            request.setAttribute(
                    "loi",
                    "Số điện thoại đã tồn tại."
            );

            request.getRequestDispatcher(
                    "/admin/themKhachHang.jsp"
            ).forward(request, response);

            return;
        }

        // ===============================
        // Kiểm tra email
        // ===============================

        if (email != null
                && !email.trim().isEmpty()
                && khachHangDAO.kiemTraTrungEmail(email)) {

            request.setAttribute(
                    "loi",
                    "Email đã tồn tại."
            );

            request.getRequestDispatcher(
                    "/admin/themKhachHang.jsp"
            ).forward(request, response);

            return;
        }

        // ===============================
        // Chuyển ngày sinh
        // ===============================

        Date ngaySinh = null;

        try {

            if (ngaySinhStr != null
                    && !ngaySinhStr.isEmpty()) {

                ngaySinh =
                        Date.valueOf(ngaySinhStr);

            }

        } catch (Exception e) {

            request.setAttribute(
                    "loi",
                    "Ngày sinh không hợp lệ."
            );

            request.getRequestDispatcher(
                    "/admin/themKhachHang.jsp"
            ).forward(request, response);

            return;

        }

        // ===============================
        // Tạo đối tượng khách hàng
        // ===============================

        KhachHang khachHang =
                new KhachHang();

        khachHang.setHoTen(hoTen);

        khachHang.setGioiTinh(gioiTinh);

        khachHang.setNgaySinh(ngaySinh);

        khachHang.setSoDienThoai(soDienThoai);

        khachHang.setEmail(email);

        khachHang.setDiaChi(diaChi);


        // ===============================
        // Lưu CSDL
        // ===============================

        boolean ketQua =
                khachHangDAO.themKhachHang(khachHang);

        if (ketQua) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/khachhang"
            );

        } else {

            request.setAttribute(
                    "loi",
                    "Không thể thêm khách hàng."
            );

            request.getRequestDispatcher(
                    "/admin/themKhachHang.jsp"
            ).forward(request, response);

        }

    }

}