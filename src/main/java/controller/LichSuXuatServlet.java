package controller;

import dao.PhieuXuatDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.PhieuXuat;

import java.io.IOException;
import java.util.List;

@WebServlet("/lichSuXuat")
public class LichSuXuatServlet extends HttpServlet {

    private final PhieuXuatDAO phieuXuatDAO =
            new PhieuXuatDAO();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        List<PhieuXuat> dsPhieuXuat =
                phieuXuatDAO.getAll();

        request.setAttribute(
                "dsPhieuXuat",
                dsPhieuXuat
        );

        request.getRequestDispatcher(
                "admin/lichSuXuat.jsp"
        ).forward(request, response);

    }

}