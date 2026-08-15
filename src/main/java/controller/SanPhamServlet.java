package controller;

import dao.SanPhamDAO;
import model.SanPham;
import dao.DanhMucDAO;
import model.DanhMuc;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/sanPham")
public class SanPhamServlet extends HttpServlet {

    private SanPhamDAO sanPhamDAO;
    private DanhMucDAO danhMucDAO;
    @Override
    public void init() {
            sanPhamDAO = new SanPhamDAO();
            danhMucDAO = new DanhMucDAO();
            }


    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");


        // =========================================
        // LẤY THAM SỐ
        // =========================================

        String hanhDong =
                request.getParameter("hanhDong");

        String maDanhMucParam =
                request.getParameter("maDanhMuc");


        // =========================================
// XEM TẤT CẢ SẢN PHẨM THEO DANH MỤC
//
// URL:
// /sanPham?maDanhMuc=4
// =========================================

        if (maDanhMucParam != null
                && !maDanhMucParam.trim().isEmpty()) {

            try {

                int maDanhMuc =
                        Integer.parseInt(maDanhMucParam);

                // Lấy toàn bộ sản phẩm thuộc danh mục
                List<SanPham> danhSach =
                        sanPhamDAO.laySanPhamTheoDanhMuc(
                                maDanhMuc
                        );

                // Lấy thông tin danh mục hiện tại
                DanhMuc danhMuc =
                        danhMucDAO.getById(maDanhMuc);

                // Lấy tất cả danh mục để hiển thị menu
                List<DanhMuc> danhSachDanhMuc =
                        danhMucDAO.getAll();

                request.setAttribute(
                        "danhSachSanPham",
                        danhSach
                );

                request.setAttribute(
                        "danhSachDanhMuc",
                        danhSachDanhMuc
                );

                request.setAttribute(
                        "danhMuc",
                        danhMuc
                );

                request.setAttribute(
                        "maDanhMuc",
                        maDanhMuc
                );

                request.getRequestDispatcher(
                        "/danhSachSanPham.jsp"
                ).forward(
                        request,
                        response
                );

                return;

            } catch (NumberFormatException e) {

                response.sendRedirect(
                        request.getContextPath()
                                + "/sanPham"
                );

                return;
            }
        }


        // =========================================
        // 2. XEM CHI TIẾT
        //
        // URL:
        // /sanPham?hanhDong=chiTiet&maSanPham=4
        // =========================================

        if ("chiTiet".equals(hanhDong)) {

            String maSanPhamParam =
                    request.getParameter(
                            "maSanPham"
                    );


            if (maSanPhamParam == null
                    || maSanPhamParam.trim().isEmpty()) {

                response.sendRedirect(
                        request.getContextPath()
                                + "/sanPham"
                );

                return;
            }


            try {

                int maSanPham =
                        Integer.parseInt(
                                maSanPhamParam
                        );


                SanPham sanPham =
                        sanPhamDAO.laySanPhamTheoMa(
                                maSanPham
                        );


                request.setAttribute(
                        "sanPham",
                        sanPham
                );


                request.getRequestDispatcher(
                        "/chiTietSanPham.jsp"
                ).forward(
                        request,
                        response
                );


                return;


            } catch (NumberFormatException e) {

                response.sendRedirect(
                        request.getContextPath()
                                + "/sanPham"
                );

                return;
            }
        }


        // =========================================
        // 3. TÌM KIẾM
        //
        // URL:
        // /sanPham?hanhDong=timKiem&tuKhoa=...
        // =========================================

        if ("timKiem".equals(hanhDong)) {

            String tuKhoa =
                    request.getParameter(
                            "tuKhoa"
                    );


            List<SanPham> danhSach =
                    sanPhamDAO.timKiemSanPham(
                            tuKhoa
                    );


            request.setAttribute(
                    "danhSachSanPham",
                    danhSach
            );


            request.getRequestDispatcher(
                    "/danhSachSanPham.jsp"
            ).forward(
                    request,
                    response
            );


            return;
        }


        // =========================================
        // 4. LỌC DANH MỤC KIỂU CŨ
        //
        // URL:
        // /sanPham?hanhDong=danhMuc&maDanhMuc=4
        //
        // Giữ lại để các link cũ vẫn hoạt động.
        // =========================================

        if ("danhMuc".equals(hanhDong)) {

            String maDanhMucCu =
                    request.getParameter(
                            "maDanhMuc"
                    );


            if (maDanhMucCu != null
                    && !maDanhMucCu.trim().isEmpty()) {

                try {

                    int maDanhMuc =
                            Integer.parseInt(
                                    maDanhMucCu
                            );


                    List<SanPham> danhSach =
                            sanPhamDAO
                                    .laySanPhamTheoDanhMuc(
                                            maDanhMuc
                                    );


                    request.setAttribute(
                            "danhSachSanPham",
                            danhSach
                    );


                    request.setAttribute(
                            "maDanhMuc",
                            maDanhMuc
                    );


                } catch (NumberFormatException e) {

                    request.setAttribute(
                            "danhSachSanPham",
                            sanPhamDAO.getAll()
                    );

                }

            } else {

                request.setAttribute(
                        "danhSachSanPham",
                        sanPhamDAO.getAll()
                );

            }


            request.getRequestDispatcher(
                    "/danhSachSanPham.jsp"
            ).forward(
                    request,
                    response
            );


            return;
        }


        // =========================================
        // 5. MẶC ĐỊNH
        //
        // /sanPham
        //
        // Hiển thị tất cả sản phẩm
        // =========================================

        List<SanPham> danhSach =
                sanPhamDAO.getAll();


        request.setAttribute(
                "danhSachSanPham",
                danhSach
        );


        request.getRequestDispatcher(
                "/danhSachSanPham.jsp"
        ).forward(
                request,
                response
        );
    }


    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);

    }
}