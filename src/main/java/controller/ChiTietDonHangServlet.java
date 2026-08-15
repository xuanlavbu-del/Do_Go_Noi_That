package controller;

import dao.ChiTietDonHangDAO;
import dao.DonHangDAO;
import model.ChiTietDonHang;
import model.DonHang;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/chiTietDonHang")
public class ChiTietDonHangServlet extends HttpServlet {

    private DonHangDAO donHangDAO;
    private ChiTietDonHangDAO chiTietDonHangDAO;

    @Override
    public void init() {

        donHangDAO = new DonHangDAO();
        chiTietDonHangDAO = new ChiTietDonHangDAO();
    }


    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String maDonStr = request.getParameter("maDon");

        if (maDonStr == null || maDonStr.trim().isEmpty()) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/quanLyDonHang"
            );

            return;
        }

        try {

            int maDon = Integer.parseInt(maDonStr);

            // ==============================
            // Lấy đơn hàng
            // ==============================
            DonHang donHang =
                    donHangDAO.layDonHangTheoMa(maDon);

            if (donHang == null) {

                response.sendRedirect(
                        request.getContextPath()
                                + "/quanLyDonHang?error=khongTimThay"
                );

                return;
            }

            // ==============================
            // Lấy chi tiết đơn hàng
            // ==============================
            List<ChiTietDonHang> danhSachChiTiet =
                    chiTietDonHangDAO.layTheoMaDon(maDon);

            // ==============================
            // Tính tổng tiền
            // ==============================
            double tongTien =
                    chiTietDonHangDAO.tinhTongTien(maDon);

            // ==============================
            // Tổng số lượng sản phẩm
            // ==============================
            int tongSoLuong =
                    chiTietDonHangDAO.demSoLuongSanPham(maDon);

            request.setAttribute(
                    "donHang",
                    donHang
            );

            request.setAttribute(
                    "danhSachChiTiet",
                    danhSachChiTiet
            );

            request.setAttribute(
                    "tongTienChiTiet",
                    tongTien
            );

            request.setAttribute(
                    "tongSoLuong",
                    tongSoLuong
            );

            request.getRequestDispatcher(
                    "/admin/chiTietDonHang.jsp"
            ).forward(request, response);

        } catch (NumberFormatException e) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/quanLyDonHang?error=duLieu"
            );
        }
    }
}

