package controller;

import dao.SanPhamDAO;
import model.SanPham;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/chiTietSanPham")
public class ChiTietSanPhamServlet extends HttpServlet {

    private SanPhamDAO sanPhamDAO;

    @Override
    public void init() {
        sanPhamDAO = new SanPhamDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");

        if (id == null || id.trim().isEmpty()) {
            response.sendRedirect(
                    request.getContextPath() + "/sanPham"
            );
            return;
        }

        try {

            int maSanPham = Integer.parseInt(id);

            SanPham sanPham =
                    sanPhamDAO.laySanPhamTheoMa(maSanPham);

            if (sanPham == null) {

                response.sendRedirect(
                        request.getContextPath() + "/sanPham"
                );

                return;
            }

            request.setAttribute(
                    "sanPham",
                    sanPham
            );

            request.getRequestDispatcher(
                    "/chiTietSanPham.jsp"
            ).forward(request, response);

        } catch (NumberFormatException e) {

            response.sendRedirect(
                    request.getContextPath() + "/sanPham"
            );
        }
    }
}