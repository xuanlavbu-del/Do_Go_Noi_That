package controller;

import dao.ChiTietPhieuNhapDAO;
import dao.KhoDAO;
import dao.PhieuNhapDAO;
import dao.SanPhamDAO;
import dao.TonKhoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ChiTietPhieuNhap;
import model.PhieuNhap;

import java.io.IOException;
import java.sql.Date;

@WebServlet("/nhapKho")
public class NhapKhoServlet extends HttpServlet {

    private KhoDAO khoDAO;
    private SanPhamDAO sanPhamDAO;
    private PhieuNhapDAO phieuNhapDAO;
    private ChiTietPhieuNhapDAO chiTietDAO;
    private TonKhoDAO tonKhoDAO;

    @Override
    public void init() {

        khoDAO = new KhoDAO();

        sanPhamDAO = new SanPhamDAO();

        phieuNhapDAO = new PhieuNhapDAO();

        chiTietDAO = new ChiTietPhieuNhapDAO();

        tonKhoDAO = new TonKhoDAO();

    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute(
                "dsKho",
                khoDAO.getAll());

        request.setAttribute(
                "dsSanPham",
                sanPhamDAO.getAll());

        request.getRequestDispatcher(
                        "/admin/nhapKho.jsp")
                .forward(request, response);

    }
    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        int maKho =
                Integer.parseInt(
                        request.getParameter("maKho"));

        int maSanPham =
                Integer.parseInt(
                        request.getParameter("maSanPham"));

        int soLuong =
                Integer.parseInt(
                        request.getParameter("soLuong"));

        double donGia =
                Double.parseDouble(
                        request.getParameter("donGia"));

        String ghiChu =
                request.getParameter("ghiChu");

        PhieuNhap pn = new PhieuNhap();

        pn.setNgayNhap(
                new Date(System.currentTimeMillis()));

        pn.setNhaCungCap("Nhập từ kho");

        pn.setTongTien(
                soLuong * donGia);

        pn.setGhiChu(ghiChu);

        int maPhieuNhap =
                phieuNhapDAO.themPhieuNhap(pn);

        ChiTietPhieuNhap ct =
                new ChiTietPhieuNhap();

        ct.setMaPhieuNhap(maPhieuNhap);

        ct.setMaSanPham(maSanPham);

        ct.setSoLuong(soLuong);

        ct.setDonGia(donGia);

        chiTietDAO.themChiTiet(ct);

        if (tonKhoDAO.tonTai(
                maSanPham,
                maKho)) {

            tonKhoDAO.capNhatSoLuong(
                    maSanPham,
                    maKho,
                    soLuong);

        } else {

            tonKhoDAO.themTonKho(
                    maSanPham,
                    maKho,
                    soLuong);

        }
        response.sendRedirect("quanLyKho");

    }

}