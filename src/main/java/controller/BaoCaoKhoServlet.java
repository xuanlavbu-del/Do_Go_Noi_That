package controller;

import dao.TonKhoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.TonKho;

import java.io.IOException;
import java.util.List;

@WebServlet("/baoCaoKho")
public class BaoCaoKhoServlet extends HttpServlet {

    private final TonKhoDAO tonKhoDAO =
            new TonKhoDAO();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        List<TonKho> dsTonKho =
                tonKhoDAO.getAllTonKho();

        request.setAttribute(
                "dsTonKho",
                dsTonKho
        );

        request.getRequestDispatcher(
                "admin/baoCaoKho.jsp"
        ).forward(request, response);

    }

}