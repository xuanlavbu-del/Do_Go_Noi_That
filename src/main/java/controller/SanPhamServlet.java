package controller;


import dao.SanPhamDAO;
import model.SanPham;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;


import java.io.IOException;
import java.util.List;



@WebServlet("/sanPham")
public class SanPhamServlet extends HttpServlet {


    private SanPhamDAO sanPhamDAO;



    @Override
    public void init(){

        sanPhamDAO = new SanPhamDAO();

    }




    // =====================================
    // GET
    // =====================================

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    )
            throws ServletException, IOException {


        request.setCharacterEncoding("UTF-8");



        String hanhDong =
                request.getParameter("hanhDong");




        // -----------------------------
        // Xem chi tiết sản phẩm
        // -----------------------------

        if("chiTiet".equals(hanhDong)){


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
                            "chiTietSanPham.jsp"
                    )
                    .forward(request,response);



        }




        // -----------------------------
        // Tìm kiếm
        // -----------------------------

        else if("timKiem".equals(hanhDong)){



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
                            "danhSachSanPham.jsp"
                    )
                    .forward(request,response);



        }




        // -----------------------------
        // Lọc danh mục
        // -----------------------------

        else if("danhMuc".equals(hanhDong)){


            int maDanhMuc =
                    Integer.parseInt(
                            request.getParameter(
                                    "maDanhMuc"
                            )
                    );



            List<SanPham> danhSach =
                    sanPhamDAO.laySanPhamTheoDanhMuc(
                            maDanhMuc
                    );



            request.setAttribute(
                    "danhSachSanPham",
                    danhSach
            );


            request.getRequestDispatcher(
                            "danhSachSanPham.jsp"
                    )
                    .forward(request,response);


        }




        // -----------------------------
        // Mặc định
        // -----------------------------

        else {


            List<SanPham> danhSach =
                    sanPhamDAO.layTatCaSanPham();



            request.setAttribute(
                    "danhSachSanPham",
                    danhSach
            );



            request.getRequestDispatcher(
                            "danhSachSanPham.jsp"
                    )
                    .forward(request,response);


        }


    }





    // =====================================
    // POST
    // =====================================

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    )
            throws ServletException, IOException {


        doGet(request,response);

    }


}