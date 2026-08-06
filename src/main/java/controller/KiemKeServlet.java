package controller;

import dao.KhoDAO;
import dao.KiemKeDAO;
import dao.TonKhoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ChiTietKiemKe;
import model.Kho;
import model.KiemKe;
import model.TonKho;
import java.util.ArrayList;

import java.io.IOException;
import java.util.List;

@WebServlet("/kiemKe")
public class KiemKeServlet extends HttpServlet {

    private KiemKeDAO kiemKeDAO;

    private TonKhoDAO tonKhoDAO;

    private KhoDAO khoDAO;

    @Override
    public void init() {

        kiemKeDAO = new KiemKeDAO();

        tonKhoDAO = new TonKhoDAO();

        khoDAO = new KhoDAO();

    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String action =
                request.getParameter("action");

        if (action == null) {

            taiTrang(request,response);

            return;

        }

        switch (action) {

            case "xoa":

                xoaPhieu(request,response);

                break;

            case "chiTiet":

                xemChiTiet(request,response);

                break;

            default:

                taiTrang(request,response);

                break;

        }

    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String action =
                request.getParameter("action");

        if (action == null) {

            taiTrang(request,response);

            return;

        }

        switch (action) {

            case "luu":

                luuKiemKe(request,response);

                break;

            default:

                taiTrang(request,response);

                break;

        }

    }

    private void luuKiemKe(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        try {

            int maKho =
                    Integer.parseInt(
                            request.getParameter("maKho")
                    );

            String nguoiKiemKe =
                    request.getParameter("nguoiKiemKe");

            String ghiChu =
                    request.getParameter("ghiChu");

            KiemKe kiemKe =
                    new KiemKe();

            kiemKe.setMaKho(maKho);

            kiemKe.setNguoiKiemKe(nguoiKiemKe);

            kiemKe.setGhiChu(ghiChu);

            int maKiemKe =
                    kiemKeDAO.taoPhieuKiemKe(kiemKe);

            if (maKiemKe == -1) {

                request.setAttribute(
                        "loi",
                        "Không thể tạo phiếu kiểm kê."
                );

                taiTrang(request,response);

                return;

            }

            String[] dsMaSanPham =
                    request.getParameterValues("maSanPham");

            String[] dsTonHeThong =
                    request.getParameterValues("tonHeThong");

            String[] dsTonThucTe =
                    request.getParameterValues("tonThucTe");

            if (dsMaSanPham != null) {

                for (int i = 0; i < dsMaSanPham.length; i++) {

                    int maSanPham =
                            Integer.parseInt(
                                    dsMaSanPham[i]
                            );

                    int tonHeThong =
                            Integer.parseInt(
                                    dsTonHeThong[i]
                            );

                    int tonThucTe =
                            Integer.parseInt(
                                    dsTonThucTe[i]
                            );

                    int chenhLech =
                            tonThucTe - tonHeThong;

                    ChiTietKiemKe ct =
                            new ChiTietKiemKe();

                    ct.setMaKiemKe(maKiemKe);

                    ct.setMaSanPham(maSanPham);

                    ct.setTonHeThong(tonHeThong);

                    ct.setTonThucTe(tonThucTe);

                    ct.setChenhLech(chenhLech);

                    kiemKeDAO.themChiTietKiemKe(ct);

                    kiemKeDAO.capNhatTonKhoSauKiemKe(
                            maSanPham,
                            maKho,
                            tonThucTe
                    );

                }

            }

            request.setAttribute(
                    "thongBao",
                    "Kiểm kê thành công."
            );

        } catch (Exception e) {

            request.setAttribute(
                    "loi",
                    "Có lỗi xảy ra: " + e.getMessage()
            );

        }

        taiTrang(request,response);

    }

    private void taiTrang(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        List<Kho> dsKho = khoDAO.getAll();
        int maKho = 0;

        try {

            maKho = Integer.parseInt(
                    request.getParameter("maKho")
            );

        } catch (Exception e) {

        }

        List<TonKho> dsTonKho;

        if (maKho == 0) {

            dsTonKho = new ArrayList<>();

        } else {

            dsTonKho = tonKhoDAO.getTonKhoTheoKho(maKho);

        }

        request.setAttribute("maKhoDangChon", maKho);
        List<KiemKe> dsKiemKe =
                kiemKeDAO.layTatCaPhieuKiemKe();

        request.setAttribute(
                "tongPhieu",
                kiemKeDAO.tongSoPhieuKiemKe()
        );

        request.setAttribute(
                "tongSanPham",
                kiemKeDAO.tongSanPhamDaKiemKe()
        );

        request.setAttribute(
                "tongChenhLech",
                kiemKeDAO.tongChenhLechTonKho()
        );

        request.setAttribute(
                "dsKho",
                dsKho
        );

        request.setAttribute(
                "dsTonKho",
                dsTonKho
        );

        request.setAttribute(
                "dsKiemKe",
                dsKiemKe
        );

        request.getRequestDispatcher("/admin/kiemKe.jsp")
                .forward(request,response);

    }

    private void xemChiTiet(HttpServletRequest request,
                            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            int maKiemKe =
                    Integer.parseInt(
                            request.getParameter("maKiemKe")
                    );

            KiemKe kiemKe =
                    kiemKeDAO.findById(maKiemKe);

            List<ChiTietKiemKe> dsChiTiet =
                    kiemKeDAO.layChiTietKiemKe(maKiemKe);

            request.setAttribute(
                    "kiemKe",
                    kiemKe
            );

            request.setAttribute(
                    "dsChiTiet",
                    dsChiTiet
            );

        } catch (Exception e) {

            request.setAttribute(
                    "loi",
                    "Không tìm thấy phiếu kiểm kê."
            );

        }

        taiTrang(request,response);

    }

    private void xoaPhieu(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        try {

            int maKiemKe =
                    Integer.parseInt(
                            request.getParameter("maKiemKe")
                    );

            if (kiemKeDAO.xoaPhieuKiemKe(maKiemKe)) {

                request.setAttribute(
                        "thongBao",
                        "Đã xóa phiếu kiểm kê."
                );

            } else {

                request.setAttribute(
                        "loi",
                        "Không thể xóa phiếu kiểm kê."
                );

            }

        } catch (Exception e) {

            request.setAttribute(
                    "loi",
                    "Có lỗi xảy ra."
            );

        }

        taiTrang(request,response);

    }

}