package controller;

import dao.TonKhoDAO;
import model.TonKho;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/tonKho")
public class TonKhoServlet extends HttpServlet {

    private final TonKhoDAO tonKhoDAO = new TonKhoDAO();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String keyword = request.getParameter("keyword");

        List<TonKho> ds;

        if(keyword == null || keyword.trim().isEmpty()){

            ds = tonKhoDAO.getAllTonKho();

        }else{

            ds = tonKhoDAO.search(keyword);

        }

        request.setAttribute("dsTonKho", ds);

        request.getRequestDispatcher("/admin/quanLyTonKho.jsp")
                .forward(request,response);

    }

}