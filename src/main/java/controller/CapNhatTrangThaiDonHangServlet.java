package controller;

import dao.DonHangDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/capNhatTrangThaiDonHang")
public class CapNhatTrangThaiDonHangServlet extends HttpServlet {

    private DonHangDAO donHangDAO;

    @Override
    public void init() {
        donHangDAO = new DonHangDAO();
    }


    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String maDonStr =
                request.getParameter("maDon");

        String trangThai =
                request.getParameter("trangThai");

        if (maDonStr == null ||
                trangThai == null ||
                trangThai.trim().isEmpty()) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/quanLyDonHang?error=duLieu"
            );

            return;
        }

        try {

            int maDon =
                    Integer.parseInt(maDonStr);

            boolean success =
                    donHangDAO.capNhatTrangThai(
                            maDon,
                            trangThai
                    );

            if (success) {

                response.sendRedirect(
                        request.getContextPath()
                                + "/chiTietDonHang?maDon="
                                + maDon
                                + "&success=capNhat"
                );

            } else {

                response.sendRedirect(
                        request.getContextPath()
                                + "/chiTietDonHang?maDon="
                                + maDon
                                + "&error=capNhat"
                );
            }

        } catch (NumberFormatException e) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/quanLyDonHang?error=duLieu"
            );
        }
    }
}
