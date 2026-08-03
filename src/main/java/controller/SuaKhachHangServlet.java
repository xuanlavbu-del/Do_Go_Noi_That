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

@WebServlet("/suaKhachHang")
public class SuaKhachHangServlet extends HttpServlet {

    private final KhachHangDAO khachHangDAO =
            new KhachHangDAO();

    // ===============================
    // Hiển thị form sửa khách hàng
    // ===============================

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String ma =
                request.getParameter("maKhachHang");

        if (ma == null || ma.trim().isEmpty()) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/khachhang"
            );

            return;

        }

        int maKhachHang =
                Integer.parseInt(ma);

        KhachHang khachHang =
                khachHangDAO.layKhachHangTheoMa(maKhachHang);

        if (khachHang == null) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/khachhang"
            );

            return;

        }

        request.setAttribute(
                "khachHang",
                khachHang
        );

        request.getRequestDispatcher(
                "/admin/suaKhachHang.jsp"
        ).forward(request, response);

    }



    // ===============================
    // Cập nhật khách hàng
    // ===============================

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        int maKhachHang =
                Integer.parseInt(
                        request.getParameter("maKhachHang")
                );

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
        // Kiểm tra dữ liệu
        // ===============================

        if (hoTen == null ||
                hoTen.trim().isEmpty()) {

            request.setAttribute(
                    "loi",
                    "Họ tên không được để trống."
            );

            doGet(request, response);

            return;

        }

        if (soDienThoai == null ||
                soDienThoai.trim().isEmpty()) {

            request.setAttribute(
                    "loi",
                    "Số điện thoại không được để trống."
            );

            doGet(request, response);

            return;

        }



        // ===============================
        // Chuyển đổi ngày sinh
        // ===============================

        Date ngaySinh = null;

        try {

            if (ngaySinhStr != null &&
                    !ngaySinhStr.isEmpty()) {

                ngaySinh =
                        Date.valueOf(ngaySinhStr);

            }

        } catch (Exception e) {

            request.setAttribute(
                    "loi",
                    "Ngày sinh không hợp lệ."
            );

            doGet(request, response);

            return;

        }



        // ===============================
        // Lấy dữ liệu cũ
        // ===============================

        KhachHang cu =
                khachHangDAO.layKhachHangTheoMa(maKhachHang);

        if (cu == null) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/khachhang"
            );

            return;

        }



        // ===============================
        // Kiểm tra số điện thoại
        // ===============================

        if (!cu.getSoDienThoai().equals(soDienThoai)
                &&
                khachHangDAO.kiemTraTrungSoDienThoai(soDienThoai)) {

            request.setAttribute(
                    "loi",
                    "Số điện thoại đã tồn tại."
            );

            doGet(request, response);

            return;

        }



        // ===============================
        // Kiểm tra email
        // ===============================

        if (email != null &&
                !email.trim().isEmpty()) {

            String emailCu =
                    cu.getEmail();

            if ((emailCu == null ||
                    !emailCu.equals(email))
                    &&
                    khachHangDAO.kiemTraTrungEmail(email)) {

                request.setAttribute(
                        "loi",
                        "Email đã tồn tại."
                );

                doGet(request, response);

                return;

            }

        }



        // ===============================
        // Tạo đối tượng
        // ===============================

        KhachHang khachHang =
                new KhachHang();

        khachHang.setMaKhachHang(
                maKhachHang
        );

        khachHang.setHoTen(
                hoTen
        );

        khachHang.setGioiTinh(
                gioiTinh
        );

        khachHang.setNgaySinh(
                ngaySinh
        );

        khachHang.setSoDienThoai(
                soDienThoai
        );

        khachHang.setEmail(
                email
        );

        khachHang.setDiaChi(
                diaChi
        );



        // ===============================
        // Cập nhật
        // ===============================

        boolean ketQua =
                khachHangDAO.capNhatKhachHang(khachHang);

        if (ketQua) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/khachhang"
            );

        } else {

            request.setAttribute(
                    "loi",
                    "Cập nhật khách hàng thất bại."
            );

            doGet(request, response);

        }

    }

}