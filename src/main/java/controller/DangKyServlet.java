package controller;

import dao.TaiKhoanDAO;
import model.TaiKhoan;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;


@WebServlet("/dangKy")
public class DangKyServlet extends HttpServlet {


    private TaiKhoanDAO taiKhoanDAO;


    @Override
    public void init() {

        taiKhoanDAO = new TaiKhoanDAO();

    }



    // =================================
    // Hiển thị trang đăng ký
    // =================================

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    )
            throws ServletException, IOException {


        request.getRequestDispatcher(
                        "dangKy.jsp"
                )
                .forward(request, response);

    }





    // =================================
    // Xử lý đăng ký
    // =================================

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    )
            throws ServletException, IOException {


        request.setCharacterEncoding("UTF-8");



        String hoTen =
                request.getParameter("hoTen");


        String email =
                request.getParameter("email");


        String matKhau =
                request.getParameter("matKhau");


        String xacNhanMatKhau =
                request.getParameter("xacNhanMatKhau");


        String soDienThoai =
                request.getParameter("soDienThoai");


        String diaChi =
                request.getParameter("diaChi");





        // ===============================
        // Kiểm tra dữ liệu rỗng
        // ===============================


        if(hoTen == null ||
                email == null ||
                matKhau == null ||
                soDienThoai == null ||
                diaChi == null ||
                hoTen.trim().isEmpty()
                ||
                email.trim().isEmpty()
                ||
                matKhau.trim().isEmpty()) {


            request.setAttribute(
                    "loi",
                    "Vui lòng nhập đầy đủ thông tin!"
            );


            request.getRequestDispatcher(
                            "dangKy.jsp"
                    )
                    .forward(request, response);


            return;

        }





        // ===============================
        // Kiểm tra mật khẩu
        // ===============================


        if(!matKhau.equals(xacNhanMatKhau)) {


            request.setAttribute(
                    "loi",
                    "Mật khẩu xác nhận không đúng!"
            );


            request.getRequestDispatcher(
                            "dangKy.jsp"
                    )
                    .forward(request, response);


            return;

        }





        // ===============================
        // Kiểm tra email tồn tại
        // ===============================


        if(taiKhoanDAO.kiemTraEmailTonTai(email)) {



            request.setAttribute(
                    "loi",
                    "Email đã được sử dụng!"
            );


            request.getRequestDispatcher(
                            "dangKy.jsp"
                    )
                    .forward(request, response);


            return;

        }





        // ===============================
        // Tạo tài khoản mới
        // ===============================


        TaiKhoan taiKhoan =
                new TaiKhoan();


        taiKhoan.setHoTen(
                hoTen
        );


        taiKhoan.setEmail(
                email
        );


        taiKhoan.setMatKhau(
                matKhau
        );


        taiKhoan.setSoDienThoai(
                soDienThoai
        );


        taiKhoan.setDiaChi(
                diaChi
        );


        // Mặc định khách hàng

        taiKhoan.setVaiTro(
                "KHACH"
        );





        boolean ketQua =
                taiKhoanDAO.dangKy(
                        taiKhoan
                );





        if(ketQua) {



            request.setAttribute(
                    "thongBao",
                    "Đăng ký thành công! Vui lòng đăng nhập."
            );



            request.getRequestDispatcher(
                            "dangNhap.jsp"
                    )
                    .forward(request, response);



        } else {



            request.setAttribute(
                    "loi",
                    "Đăng ký thất bại!"
            );


            request.getRequestDispatcher(
                            "dangKy.jsp"
                    )
                    .forward(request, response);


        }



    }


}