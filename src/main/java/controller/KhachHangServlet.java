package controller;

import dao.KhachHangDAO;
import model.KhachHang;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/khachhang")
public class KhachHangServlet extends HttpServlet {

    private final KhachHangDAO khachHangDAO =
            new KhachHangDAO();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String tuKhoa =
                request.getParameter("tuKhoa");

        List<KhachHang> danhSach;

        if (tuKhoa != null &&
                !tuKhoa.trim().isEmpty()) {

            danhSach =
                    khachHangDAO.timKiemKhachHang(tuKhoa);

            request.setAttribute(
                    "tuKhoa",
                    tuKhoa
            );

        } else {

            danhSach =
                    khachHangDAO.layTatCaKhachHang();

        }

        request.setAttribute(
                "danhSachKhachHang",
                danhSach
        );

        request.getRequestDispatcher(
                "/admin/quanLyKhachHang.jsp"
        ).forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);

    }

}