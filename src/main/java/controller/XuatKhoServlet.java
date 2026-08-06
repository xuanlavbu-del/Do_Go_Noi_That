package controller;

import dao.PhieuXuatDAO;
import jakarta.servlet.ServletException;
import model.ChiTietPhieuXuat;
import model.PhieuXuat;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Date;
import dao.KhoDAO;
import dao.SanPhamDAO;
@WebServlet("/xuatKho")
public class XuatKhoServlet extends HttpServlet {
    private final KhoDAO khoDAO = new KhoDAO();
    private final SanPhamDAO sanPhamDAO = new SanPhamDAO();
    private final PhieuXuatDAO dao = new PhieuXuatDAO();


    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("dsKho", khoDAO.getAll());
        request.setAttribute("dsSanPham", sanPhamDAO.getAll());

        request.getRequestDispatcher("/admin/xuatKho.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException {

        request.setCharacterEncoding("UTF-8");

        try {

            PhieuXuat px = new PhieuXuat();

            px.setNgayXuat(Date.valueOf(request.getParameter("ngayXuat")));
            px.setNguoiNhan(request.getParameter("nguoiNhan"));
            px.setGhiChu(request.getParameter("ghiChu"));

            int maPhieu = dao.insertPhieuXuat(px);

            int maKho = Integer.parseInt(request.getParameter("maKho"));
            int maSanPham = Integer.parseInt(request.getParameter("maSanPham"));
            int soLuong = Integer.parseInt(request.getParameter("soLuong"));

            // Kiểm tra tồn kho
            int ton = dao.laySoLuongTon(maKho, maSanPham);

            if (soLuong > ton) {

                response.getWriter().println(
                        "<script>alert('Số lượng xuất lớn hơn tồn kho!');history.back();</script>"
                );

                return;
            }

            ChiTietPhieuXuat ct = new ChiTietPhieuXuat();

            ct.setMaPhieuXuat(maPhieu);
            ct.setMaKho(maKho);
            ct.setMaSanPham(maSanPham);
            ct.setSoLuong(soLuong);

            dao.insertChiTiet(ct);

            // Trừ tồn kho
            dao.giamTonKho(maKho, maSanPham, soLuong);

            response.sendRedirect(request.getContextPath() + "/tonKho");

        } catch (Exception e) {

            e.printStackTrace();

            response.getWriter().println(
                    "<script>alert('Xuất kho thất bại!');history.back();</script>"
            );
        }

    }

}