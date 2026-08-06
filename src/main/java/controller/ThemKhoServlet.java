package controller;

import dao.KhoDAO;
import model.Kho;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/admin/themKho")
public class ThemKhoServlet extends HttpServlet {

    private final KhoDAO khoDAO = new KhoDAO();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/admin/themKho.jsp")
                .forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        Kho kho = new Kho();

        kho.setTenKho(request.getParameter("tenKho"));
        kho.setDiaChi(request.getParameter("diaChi"));
        kho.setNguoiQuanLy(request.getParameter("nguoiQuanLy"));
        String soDienThoai = request.getParameter("soDienThoai");
        String email = request.getParameter("email");
        String ghiChu = request.getParameter("ghiChu");

        response.sendRedirect("kho");

    }

}