package controller;

import dao.KhoDAO;
import model.Kho;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/kho")
public class KhoServlet extends HttpServlet {

    private final KhoDAO khoDAO = new KhoDAO();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        List<Kho> dsKho = khoDAO.getAll();

        request.setAttribute("dsKho", dsKho);

        request.getRequestDispatcher("/admin/quanLyKho.jsp")
                .forward(request, response);

    }

}