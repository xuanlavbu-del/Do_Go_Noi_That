package controller;

import dao.DonHangDAO;
import model.DonHang;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/quanLyDonHang")
public class QuanLyDonHangServlet extends HttpServlet {

    private DonHangDAO donHangDAO;

    @Override
    public void init() {
        donHangDAO = new DonHangDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String keyword = request.getParameter("keyword");
        String trangThai = request.getParameter("trangThai");

        List<DonHang> danhSach;

        // ==============================
        // Tìm kiếm
        // ==============================
        if (keyword != null && !keyword.trim().isEmpty()) {

            danhSach = donHangDAO.timKiem(keyword.trim());

        }
        // ==============================
        // Lọc theo trạng thái
        // ==============================
        else if (trangThai != null && !trangThai.trim().isEmpty()) {

            danhSach = donHangDAO.layTheoTrangThai(trangThai);

        }
        // ==============================
        // Hiển thị tất cả
        // ==============================
        else {

            danhSach = donHangDAO.layTatCaDonHang();
        }

        // Danh sách đơn hàng
        request.setAttribute("danhSachDonHang", danhSach);

        // Thống kê
        request.setAttribute(
                "tongDonHang",
                donHangDAO.demDonHang()
        );

        request.setAttribute(
                "choXacNhan",
                donHangDAO.demTheoTrangThai("Chờ xác nhận")
        );

        request.setAttribute(
                "daXacNhan",
                donHangDAO.demTheoTrangThai("Đã xác nhận")
        );

        request.setAttribute(
                "dangGiao",
                donHangDAO.demTheoTrangThai("Đang giao")
        );

        request.setAttribute(
                "hoanThanh",
                donHangDAO.demTheoTrangThai("Hoàn thành")
        );

        request.setAttribute(
                "daHuy",
                donHangDAO.demTheoTrangThai("Đã hủy")
        );

        request.setAttribute("keyword", keyword);
        request.setAttribute("trangThai", trangThai);

        request.getRequestDispatcher(
                "/admin/quanLyDonHang.jsp"
        ).forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");

        // ==============================
        // Cập nhật trạng thái
        // ==============================
        if ("capNhatTrangThai".equals(action)) {

            String maDonStr = request.getParameter("maDon");
            String trangThai = request.getParameter("trangThai");

            try {

                int maDon = Integer.parseInt(maDonStr);

                boolean success =
                        donHangDAO.capNhatTrangThai(
                                maDon,
                                trangThai
                        );

                if (success) {

                    response.sendRedirect(
                            request.getContextPath()
                                    + "/quanLyDonHang?success=capNhat"
                    );

                } else {

                    response.sendRedirect(
                            request.getContextPath()
                                    + "/quanLyDonHang?error=capNhat"
                    );
                }

            } catch (NumberFormatException e) {

                response.sendRedirect(
                        request.getContextPath()
                                + "/quanLyDonHang?error=duLieu"
                );
            }

            return;
        }


        // ==============================
        // Xóa đơn hàng
        // ==============================
        if ("xoa".equals(action)) {

            String maDonStr = request.getParameter("maDon");

            try {

                int maDon = Integer.parseInt(maDonStr);

                /*
                 * Xóa chi tiết trước vì
                 * chi_tiet_don_hang có khóa ngoại
                 * tham chiếu đến don_hang.
                 */
                dao.ChiTietDonHangDAO chiTietDAO =
                        new dao.ChiTietDonHangDAO();

                chiTietDAO.xoaTheoMaDon(maDon);

                boolean success =
                        donHangDAO.xoaDonHang(maDon);

                if (success) {

                    response.sendRedirect(
                            request.getContextPath()
                                    + "/quanLyDonHang?success=xoa"
                    );

                } else {

                    response.sendRedirect(
                            request.getContextPath()
                                    + "/quanLyDonHang?error=xoa"
                    );
                }

            } catch (NumberFormatException e) {

                response.sendRedirect(
                        request.getContextPath()
                                + "/quanLyDonHang?error=duLieu"
                );
            }

            return;
        }

        response.sendRedirect(
                request.getContextPath()
                        + "/quanLyDonHang"
        );
    }
}
