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

@WebServlet("/chuyenKho")
public class ChuyenKhoServlet extends HttpServlet {

    private final TonKhoDAO tonKhoDAO = new TonKhoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<TonKho> dsTonKho = tonKhoDAO.getAllTonKho();

        request.setAttribute("dsTonKho", dsTonKho);

        request.getRequestDispatcher("/admin/chuyenKho.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            int maSanPham = Integer.parseInt(request.getParameter("maSanPham"));

            int khoNguon = Integer.parseInt(request.getParameter("khoNguon"));

            int khoDich = Integer.parseInt(request.getParameter("khoDich"));

            int soLuong = Integer.parseInt(request.getParameter("soLuong"));

            if (khoNguon == khoDich) {

                request.setAttribute("loi",
                        "Kho nguồn và kho đích phải khác nhau.");

                doGet(request, response);

                return;
            }

            TonKho tonNguon =
                    tonKhoDAO.layTonKho(maSanPham, khoNguon);

            if (tonNguon == null) {

                request.setAttribute("loi",
                        "Không tìm thấy sản phẩm trong kho nguồn.");

                doGet(request, response);

                return;
            }

            if (tonNguon.getSoLuong() < soLuong) {

                request.setAttribute("loi",
                        "Số lượng tồn không đủ.");

                doGet(request, response);

                return;
            }

            int soLuongMoiNguon =
                    tonNguon.getSoLuong() - soLuong;

            tonKhoDAO.truTonKho(maSanPham, khoNguon, soLuong);

            TonKho tonDich = tonKhoDAO.layTonKho(maSanPham, khoDich);

            if (tonDich == null) {

                tonKhoDAO.themTonKho(
                        maSanPham,
                        khoDich,
                        soLuong
                );

            } else {

                tonKhoDAO.congTonKho(
                        maSanPham,
                        khoDich,
                        soLuong
                );
            }

            request.setAttribute("thongBao",
                    "Chuyển kho thành công.");

        } catch (Exception e) {

            request.setAttribute("loi",
                    "Có lỗi xảy ra: " + e.getMessage());
        }

        doGet(request, response);
    }

}