
package controller;

import dao.KhoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Kho;

import java.io.IOException;
import java.util.List;

@WebServlet("/quanLyKho")
public class QuanLyKhoServlet extends HttpServlet {

    private KhoDAO khoDAO;

    @Override
    public void init() {

        khoDAO = new KhoDAO();

    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {

            taiDanhSach(request, response);

            return;

        }

        switch (action) {

            case "sua":

                suaKho(request, response);

                break;

            case "xoa":

                xoaKho(request, response);

                break;

            default:

                taiDanhSach(request, response);

                break;

        }

    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");

        if (action == null) {

            taiDanhSach(request, response);

            return;

        }

        switch (action) {

            case "them":

                themKho(request, response);

                break;

            case "capNhat":

                capNhatKho(request, response);

                break;

            default:

                taiDanhSach(request, response);

                break;

        }

    }
    private void themKho(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        Kho kho = new Kho();

        kho.setTenKho(request.getParameter("tenKho"));
        kho.setDiaChi(request.getParameter("diaChi"));
        kho.setNguoiQuanLy(request.getParameter("nguoiQuanLy"));
        kho.setSoDienThoai(request.getParameter("soDienThoai"));
        kho.setEmail(request.getParameter("email"));
        kho.setGhiChu(request.getParameter("ghiChu"));

        if (khoDAO.insert(kho)) {

            request.setAttribute(
                    "thongBao",
                    "Thêm kho thành công."
            );

        } else {

            request.setAttribute(
                    "loi",
                    "Không thể thêm kho."
            );

        }

        taiDanhSach(request, response);

    }

    private void capNhatKho(HttpServletRequest request,
                            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            int maKho = Integer.parseInt(
                    request.getParameter("maKho")
            );

            Kho kho = new Kho();

            kho.setMaKho(maKho);
            kho.setTenKho(request.getParameter("tenKho"));
            kho.setDiaChi(request.getParameter("diaChi"));
            kho.setNguoiQuanLy(request.getParameter("nguoiQuanLy"));
            kho.setSoDienThoai(request.getParameter("soDienThoai"));
            kho.setEmail(request.getParameter("email"));
            kho.setGhiChu(request.getParameter("ghiChu"));

            if (khoDAO.update(kho)) {

                request.setAttribute(
                        "thongBao",
                        "Cập nhật kho thành công."
                );

            } else {

                request.setAttribute(
                        "loi",
                        "Cập nhật kho thất bại."
                );

            }

        } catch (Exception e) {

            request.setAttribute(
                    "loi",
                    "Dữ liệu không hợp lệ."
            );

        }

        taiDanhSach(request, response);

    }

    private void suaKho(HttpServletRequest request,
                        HttpServletResponse response)
            throws ServletException, IOException {

        try {

            int maKho = Integer.parseInt(
                    request.getParameter("maKho")
            );

            Kho kho = khoDAO.findById(maKho);

            request.setAttribute("kho", kho);

        } catch (Exception e) {

            request.setAttribute(
                    "loi",
                    "Không tìm thấy kho."
            );

        }

        taiDanhSach(request, response);

    }

    private void xoaKho(HttpServletRequest request,
                        HttpServletResponse response)
            throws ServletException, IOException {

        try {

            int maKho = Integer.parseInt(
                    request.getParameter("maKho")
            );

            if (khoDAO.delete(maKho)) {

                request.setAttribute(
                        "thongBao",
                        "Xóa kho thành công."
                );

            } else {

                request.setAttribute(
                        "loi",
                        "Không thể xóa kho."
                );

            }

        } catch (Exception e) {

            request.setAttribute(
                    "loi",
                    "Dữ liệu không hợp lệ."
            );

        }

        taiDanhSach(request, response);

    }

    private void taiDanhSach(HttpServletRequest request,
                             HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute(
                "tongKho",
                khoDAO.demSoKho()
        );

        request.setAttribute(
                "tongTon",
                khoDAO.tongSoLuongTon()
        );

        request.setAttribute(
                "sapHet",
                khoDAO.sanPhamSapHet()
        );

        List<Kho> dsKho = khoDAO.getAll();

        request.setAttribute(
                "dsKho",
                dsKho
        );

        request.getRequestDispatcher("/admin/quanLyKho.jsp")
                .forward(request, response);

    }

}