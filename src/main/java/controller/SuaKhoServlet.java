package controller;

import dao.KhoDAO;
import model.Kho;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/admin/suaKho")
public class SuaKhoServlet extends HttpServlet {

    private final KhoDAO khoDAO = new KhoDAO();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String maKhoStr = request.getParameter("maKho");

        System.out.println("URI      : " + request.getRequestURI());
        System.out.println("Query    : " + request.getQueryString());
        System.out.println("maKhoStr : [" + maKhoStr + "]");

        if (maKhoStr == null || maKhoStr.trim().isEmpty()) {
            response.getWriter().println("Khong nhan duoc maKho");
            return;
        }

        int maKho = Integer.parseInt(maKhoStr);

        Kho kho = khoDAO.findById(maKho);

        if (kho == null) {
            response.sendRedirect(request.getContextPath() + "/quanLyKho");
            return;
        }

        request.setAttribute("kho", kho);

        request.getRequestDispatcher("/admin/suaKho.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        Kho kho = new Kho();

        kho.setMaKho(
                Integer.parseInt(request.getParameter("maKho")));

        kho.setTenKho(request.getParameter("tenKho"));

        kho.setDiaChi(request.getParameter("diaChi"));

        kho.setNguoiQuanLy(request.getParameter("nguoiQuanLy"));
        kho.setSoDienThoai(request.getParameter("soDienThoai"));
        kho.setEmail(request.getParameter("email"));
        kho.setGhiChu(request.getParameter("ghiChu"));
        khoDAO.update(kho);

        response.sendRedirect(request.getContextPath() + "/quanLyKho");
    }

}