package controller;

import dao.KhachHangDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/xoaKhachHang")
public class XoaKhachHangServlet extends HttpServlet {

    private final KhachHangDAO khachHangDAO =
            new KhachHangDAO();

    // ===============================
    // Xóa khách hàng
    // ===============================

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String ma =
                request.getParameter("maKhachHang");

        // Kiểm tra tham số

        if (ma == null || ma.trim().isEmpty()) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/khachhang"
            );

            return;

        }

        try {

            int maKhachHang =
                    Integer.parseInt(ma);

            // Kiểm tra khách hàng tồn tại

            if (!khachHangDAO.kiemTraTonTai(maKhachHang)) {

                response.sendRedirect(
                        request.getContextPath()
                                + "/khachhang"
                );

                return;

            }

            // Xóa khách hàng

            boolean ketQua =
                    khachHangDAO.xoaKhachHang(maKhachHang);

            if (ketQua) {

                response.sendRedirect(
                        request.getContextPath()
                                + "/khachhang"
                );

            } else {

                request.setAttribute(
                        "loi",
                        "Không thể xóa khách hàng."
                );

                request.getRequestDispatcher(
                        "/admin/quanLyKhachHang.jsp"
                ).forward(request, response);

            }

        } catch (NumberFormatException e) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/khachhang"
            );

        }

    }

    // ===============================
    // POST gọi GET
    // ===============================

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);

    }

}