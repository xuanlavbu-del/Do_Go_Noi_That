package controller;


import dao.SanPhamDAO;

import model.SanPham;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;


import java.io.IOException;
import java.util.List;



@WebServlet("/quanlySanPham")
public class QuanlySanPhamServlet extends HttpServlet {


    private SanPhamDAO sanPhamDAO;



    @Override
    public void init(){

        sanPhamDAO =
                new SanPhamDAO();

    }





    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    )
            throws ServletException, IOException {



        String hanhDong =
                request.getParameter(
                        "hanhDong"
                );





        // ==========================
        // Xóa sản phẩm
        // ==========================


        if("xoa".equals(hanhDong)){


            int maSanPham =
                    Integer.parseInt(
                            request.getParameter(
                                    "maSanPham"
                            )
                    );


            sanPhamDAO.xoaSanPham(
                    maSanPham
            );



            response.sendRedirect(
                    "quanlySanPham"
            );


            return;

        }





        // ==========================
        // Sửa sản phẩm
        // ==========================


        if("sua".equals(hanhDong)){


            int maSanPham =
                    Integer.parseInt(
                            request.getParameter(
                                    "maSanPham"
                            )
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
                            "/admin/suaSanPham.jsp"
                    )
                    .forward(
                            request,
                            response
                    );


            return;

        }





        // ==========================
        // Danh sách
        // ==========================


        List<SanPham> danhSach =
                sanPhamDAO.layTatCaSanPham();



        request.setAttribute(
                "danhSachSanPham",
                danhSach
        );



        request.getRequestDispatcher(
                        "/admin/quanlySanPham.jsp"
                )
                .forward(
                        request,
                        response
                );


    }







    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    )
            throws ServletException, IOException {



        request.setCharacterEncoding(
                "UTF-8"
        );



        String hanhDong =
                request.getParameter(
                        "hanhDong"
                );





        // ==========================
        // Thêm sản phẩm
        // ==========================


        if("them".equals(hanhDong)){



            SanPham sp =
                    new SanPham();



            sp.setTenSanPham(
                    request.getParameter(
                            "tenSanPham"
                    )
            );



            sp.setGia(
                    Double.parseDouble(
                            request.getParameter(
                                    "gia"
                            )
                    )
            );



            sp.setSoLuong(
                    Integer.parseInt(
                            request.getParameter(
                                    "soLuong"
                            )
                    )
            );



            sp.setMoTa(
                    request.getParameter(
                            "moTa"
                    )
            );



            sp.setHinhAnh(
                    request.getParameter(
                            "hinhAnh"
                    )
            );



            sp.setMaDanhMuc(
                    Integer.parseInt(
                            request.getParameter(
                                    "maDanhMuc"
                            )
                    )
            );



            sanPhamDAO.themSanPham(sp);



        }





        // ==========================
        // Cập nhật sản phẩm
        // ==========================


        else if("capNhat".equals(hanhDong)){



            SanPham sp =
                    new SanPham();



            sp.setMaSanPham(
                    Integer.parseInt(
                            request.getParameter(
                                    "maSanPham"
                            )
                    )
            );



            sp.setTenSanPham(
                    request.getParameter(
                            "tenSanPham"
                    )
            );



            sp.setGia(
                    Double.parseDouble(
                            request.getParameter(
                                    "gia"
                            )
                    )
            );



            sp.setSoLuong(
                    Integer.parseInt(
                            request.getParameter(
                                    "soLuong"
                            )
                    )
            );



            sp.setMoTa(
                    request.getParameter(
                            "moTa"
                    )
            );



            sp.setHinhAnh(
                    request.getParameter(
                            "hinhAnh"
                    )
            );



            sp.setMaDanhMuc(
                    Integer.parseInt(
                            request.getParameter(
                                    "maDanhMuc"
                            )
                    )
            );



            sanPhamDAO.capNhatSanPham(
                    sp
            );


        }





        response.sendRedirect(
                "quanlySanPham"
        );


    }


}