package controller;

import dao.ChiTietPhieuNhapDAO;
import dao.PhieuNhapDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ChiTietPhieuNhap;
import model.PhieuNhap;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/chiTietPhieuNhap")
public class ChiTietPhieuNhapServlet extends HttpServlet {

    private ChiTietPhieuNhapDAO chiTietDAO;
    private PhieuNhapDAO phieuNhapDAO;

    @Override
    public void init() {

        chiTietDAO = new ChiTietPhieuNhapDAO();

        phieuNhapDAO = new PhieuNhapDAO();

    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        try {

            String id =
                    request.getParameter("maPhieuNhap");

            if (id == null || id.isEmpty()) {

                response.sendRedirect(
                        request.getContextPath()
                                + "/lichSuNhap"
                );

                return;

            }

            int maPhieuNhap =
                    Integer.parseInt(id);

            PhieuNhap phieuNhap =
                    phieuNhapDAO.findById(maPhieuNhap);

            if (phieuNhap == null) {

                request.setAttribute(
                        "loi",
                        "Không tìm thấy phiếu nhập."
                );

                request.getRequestDispatcher(
                        "/admin/lichSuNhap.jsp"
                ).forward(request, response);

                return;

            }

            ArrayList<ChiTietPhieuNhap> dsChiTiet =
                    chiTietDAO.getByPhieuNhap(maPhieuNhap);

            double tongTien =
                    chiTietDAO.tinhTongTien(maPhieuNhap);

            request.setAttribute(
                    "phieuNhap",
                    phieuNhap
            );

            request.setAttribute(
                    "dsChiTiet",
                    dsChiTiet
            );

            request.setAttribute(
                    "tongTien",
                    tongTien
            );

            request.getRequestDispatcher(
                    "/admin/chiTietPhieuNhap.jsp"
            ).forward(request, response);

        } catch (Exception e) {

            e.printStackTrace();

            request.setAttribute(
                    "loi",
                    "Có lỗi xảy ra: "
                            + e.getMessage()
            );

            request.getRequestDispatcher(
                    "/admin/lichSuNhap.jsp"
            ).forward(request, response);

        }

    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);

    }

}