package controller;

import dao.DanhMucDAO;
import dao.SanPhamDAO;
import model.DanhMuc;
import model.SanPham;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/trangChu")
public class TrangChuServlet extends HttpServlet {


    private DanhMucDAO danhMucDAO;
    private SanPhamDAO sanPhamDAO;

    @Override
    public void init() throws ServletException {

        danhMucDAO = new DanhMucDAO();
        sanPhamDAO = new SanPhamDAO();

    }


    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // ==============================
        // LẤY DANH SÁCH DANH MỤC
        // ==============================

        List<DanhMuc> danhSachDanhMuc =
                danhMucDAO.getAll();


        // ==============================
        // SẢN PHẨM THEO TỪNG DANH MỤC
        // ==============================

        Map<Integer, List<SanPham>> sanPhamTheoDanhMuc =
                new LinkedHashMap<>();


        for (DanhMuc danhMuc : danhSachDanhMuc) {

            List<SanPham> danhSachSanPham =
                    sanPhamDAO.laySanPhamNoiBatTheoDanhMuc(
                            danhMuc.getMaDanhMuc(),
                            4
                    );


            // Chỉ thêm danh mục có sản phẩm
            if (danhSachSanPham != null
                    && !danhSachSanPham.isEmpty()) {

                sanPhamTheoDanhMuc.put(
                        danhMuc.getMaDanhMuc(),
                        danhSachSanPham
                );

            }

        }


        // ==============================
        // GỬI DỮ LIỆU SANG INDEX.JSP
        // ==============================

        request.setAttribute(
                "sanPhamTheoDanhMuc",
                sanPhamTheoDanhMuc
        );


        request.setAttribute(
                "danhSachDanhMuc",
                danhSachDanhMuc
        );


        // ==============================
        // FORWARD VỀ TRANG CHỦ
        // ==============================

        request.getRequestDispatcher(
                "/index.jsp"
        ).forward(request, response);

    }


    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);

    }


}
