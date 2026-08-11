package controller;

import dao.PhieuNhapDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.PhieuNhap;

import java.io.IOException;
import java.util.List;

@WebServlet("/lichSuNhap")
public class LichSuNhapServlet extends HttpServlet {

    private final PhieuNhapDAO phieuNhapDAO = new PhieuNhapDAO();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        List<PhieuNhap> dsPhieuNhap =
                phieuNhapDAO.getAll();

        request.setAttribute(
                "dsPhieuNhap",
                dsPhieuNhap
        );

        request.getRequestDispatcher(
                "admin/lichSuNhap.jsp"
        ).forward(request, response);

    }

}