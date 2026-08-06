package controller;

import dao.KhoDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/admin/xoaKho")
public class XoaKhoServlet extends HttpServlet {

    private final KhoDAO khoDAO = new KhoDAO();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        int id =
                Integer.parseInt(request.getParameter("id"));

        khoDAO.delete(id);

        response.sendRedirect("kho");

    }

}