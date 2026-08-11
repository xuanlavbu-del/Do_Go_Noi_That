package controller;

import dao.DanhMucDAO;
import model.DanhMuc;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/quanLyDanhMuc")
public class DanhMucServlet extends HttpServlet {

    private DanhMucDAO danhMucDAO;

    @Override
    public void init() {
        danhMucDAO = new DanhMucDAO();
    }


    // ==========================
    // GET
    // ==========================

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String hanhDong =
                request.getParameter("hanhDong");


        // ==========================
        // Xóa
        // ==========================

        if ("xoa".equals(hanhDong)) {

            try {

                int maDanhMuc =
                        Integer.parseInt(
                                request.getParameter(
                                        "maDanhMuc"
                                )
                        );

                if (danhMucDAO.hasProduct(maDanhMuc)) {

                    response.sendRedirect(
                            request.getContextPath()
                                    + "/quanLyDanhMuc?loi=conSanPham"
                    );

                    return;
                }

                danhMucDAO.xoa(maDanhMuc);

                response.sendRedirect(
                        request.getContextPath()
                                + "/quanLyDanhMuc"
                );

                return;

            } catch (NumberFormatException e) {

                response.sendRedirect(
                        request.getContextPath()
                                + "/quanLyDanhMuc?loi=duLieu"
                );

                return;
            }
        }


        // ==========================
        // Sửa
        // ==========================

        if ("sua".equals(hanhDong)) {

            try {

                int maDanhMuc =
                        Integer.parseInt(
                                request.getParameter(
                                        "maDanhMuc"
                                )
                        );

                DanhMuc danhMuc =
                        danhMucDAO.getById(
                                maDanhMuc
                        );

                if (danhMuc == null) {

                    response.sendRedirect(
                            request.getContextPath()
                                    + "/quanLyDanhMuc?loi=khongTonTai"
                    );

                    return;
                }

                request.setAttribute(
                        "danhMuc",
                        danhMuc
                );

                request.getRequestDispatcher(
                        "/admin/suaDanhMuc.jsp"
                ).forward(
                        request,
                        response
                );

                return;

            } catch (NumberFormatException e) {

                response.sendRedirect(
                        request.getContextPath()
                                + "/quanLyDanhMuc?loi=duLieu"
                );

                return;
            }
        }


        // ==========================
        // Tìm kiếm
        // ==========================

        String tuKhoa =
                request.getParameter("tuKhoa");

        List<DanhMuc> danhSach;

        if (tuKhoa != null &&
                !tuKhoa.trim().isEmpty()) {

            danhSach =
                    danhMucDAO.timKiemDanhMuc(
                            tuKhoa.trim()
                    );

        } else {

            danhSach =
                    danhMucDAO.getAll();
        }


        request.setAttribute(
                "danhSachDanhMuc",
                danhSach
        );

        request.setAttribute(
                "tuKhoa",
                tuKhoa
        );


        request.getRequestDispatcher(
                "/admin/quanLyDanhMuc.jsp"
        ).forward(
                request,
                response
        );
    }


    // ==========================
    // POST
    // ==========================

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String hanhDong =
                request.getParameter("hanhDong");


        // ==========================
        // Thêm
        // ==========================

        if ("them".equals(hanhDong)) {

            String tenDanhMuc =
                    request.getParameter(
                            "tenDanhMuc"
                    );

            String moTa =
                    request.getParameter("moTa");


            if (tenDanhMuc == null ||
                    tenDanhMuc.trim().isEmpty()) {

                response.sendRedirect(
                        request.getContextPath()
                                + "/admin/themDanhMuc.jsp?loi=rong"
                );

                return;
            }


            tenDanhMuc =
                    tenDanhMuc.trim();


            if (danhMucDAO.tonTai(
                    tenDanhMuc
            )) {

                response.sendRedirect(
                        request.getContextPath()
                                + "/admin/themDanhMuc.jsp?loi=trungTen"
                );

                return;
            }


            DanhMuc danhMuc =
                    new DanhMuc();

            danhMuc.setTenDanhMuc(
                    tenDanhMuc
            );

            danhMuc.setMoTa(moTa);


            danhMucDAO.them(
                    danhMuc
            );
        }


        // ==========================
        // Cập nhật
        // ==========================

        else if ("capNhat".equals(hanhDong)) {

            try {

                int maDanhMuc =
                        Integer.parseInt(
                                request.getParameter(
                                        "maDanhMuc"
                                )
                        );

                String tenDanhMuc =
                        request.getParameter(
                                "tenDanhMuc"
                        );

                String moTa =
                        request.getParameter("moTa");


                if (tenDanhMuc == null ||
                        tenDanhMuc.trim().isEmpty()) {

                    response.sendRedirect(
                            request.getContextPath()
                                    + "/quanLyDanhMuc?hanhDong=sua"
                                    + "&maDanhMuc="
                                    + maDanhMuc
                                    + "&loi=rong"
                    );

                    return;
                }


                tenDanhMuc =
                        tenDanhMuc.trim();


                if (danhMucDAO.existsExceptId(
                        tenDanhMuc,
                        maDanhMuc
                )) {

                    response.sendRedirect(
                            request.getContextPath()
                                    + "/quanLyDanhMuc?hanhDong=sua"
                                    + "&maDanhMuc="
                                    + maDanhMuc
                                    + "&loi=trungTen"
                    );

                    return;
                }


                DanhMuc danhMuc =
                        new DanhMuc();

                danhMuc.setMaDanhMuc(
                        maDanhMuc
                );

                danhMuc.setTenDanhMuc(
                        tenDanhMuc
                );

                danhMuc.setMoTa(
                        moTa
                );


                danhMucDAO.sua(
                        danhMuc
                );


            } catch (NumberFormatException e) {

                response.sendRedirect(
                        request.getContextPath()
                                + "/quanLyDanhMuc?loi=duLieu"
                );

                return;
            }
        }


        response.sendRedirect(
                request.getContextPath()
                        + "/quanLyDanhMuc"
        );
    }
}