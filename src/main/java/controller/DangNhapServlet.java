package controller;

import dao.TaiKhoanDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import model.TaiKhoan;

import java.io.IOException;


@WebServlet("/dangNhap")
public class DangNhapServlet extends HttpServlet {


    private TaiKhoanDAO taiKhoanDAO;


    @Override
    public void init() {

        taiKhoanDAO = new TaiKhoanDAO();

    }



    // ==============================
    // Hiển thị trang đăng nhập
    // ==============================

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    )
            throws ServletException, IOException {


        request.getRequestDispatcher(
                        "dangNhap.jsp"
                )
                .forward(request, response);

    }





    // ==============================
    // Xử lý đăng nhập
    // ==============================

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    )
            throws ServletException, IOException {


        request.setCharacterEncoding("UTF-8");


        String email =
                request.getParameter("email");


        String matKhau =
                request.getParameter("matKhau");



        // Kiểm tra dữ liệu nhập

        if(email == null ||
                email.trim().isEmpty()
                ||
                matKhau == null ||
                matKhau.trim().isEmpty()) {



            request.setAttribute(
                    "loi",
                    "Vui lòng nhập đầy đủ thông tin!"
            );


            request.getRequestDispatcher(
                            "dangNhap.jsp"
                    )
                    .forward(request, response);


            return;

        }





        // Gọi DAO kiểm tra đăng nhập

        TaiKhoan taiKhoan =
                taiKhoanDAO.dangNhap(
                        email,
                        matKhau
                );




        if(taiKhoan != null) {


            // Tạo session

            HttpSession session =
                    request.getSession();



            session.setAttribute(
                    "taiKhoan",
                    taiKhoan
            );



            session.setAttribute(
                    "maTaiKhoan",
                    taiKhoan.getMaTaiKhoan()
            );



            session.setAttribute(
                    "hoTen",
                    taiKhoan.getHoTen()
            );



            session.setAttribute(
                    "vaiTro",
                    taiKhoan.getVaiTro()
            );





            // ===========================
            // Phân quyền
            // ===========================


            if(taiKhoan.laQuanTri()) {



                response.sendRedirect(
                        request.getContextPath()
                                + "/dashboard"
                );



            } else {



                response.sendRedirect(
                        "index.jsp"
                );


            }





        } else {



            request.setAttribute(
                    "loi",
                    "Email hoặc mật khẩu không đúng!"
            );



            request.getRequestDispatcher(
                            "dangNhap.jsp"
                    )
                    .forward(request, response);


        }


    }


}