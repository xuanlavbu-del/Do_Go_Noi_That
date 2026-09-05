package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.SanPhamDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.SanPham;

import java.io.IOException;
import java.util.List;

@WebServlet("/timKiemSanPham")
public class TimKiemSanPhamServlet extends HttpServlet {

    private SanPhamDAO sanPhamDAO;
    private ObjectMapper objectMapper;

    @Override
    public void init() {
        sanPhamDAO = new SanPhamDAO();
        objectMapper = new ObjectMapper();
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        String keyword = request.getParameter("keyword");

        if (keyword == null || keyword.trim().isEmpty()) {
            response.getWriter().write("[]");
            return;
        }

        keyword = keyword.trim();

        List<SanPham> danhSach =
                sanPhamDAO.timKiemSanPham(keyword);

        objectMapper.writeValue(
                response.getWriter(),
                danhSach
        );
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }
}