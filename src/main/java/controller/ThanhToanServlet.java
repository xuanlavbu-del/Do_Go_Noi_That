package controller;


import dao.ChiTietDonHangDAO;
import dao.DonHangDAO;

import model.ChiTietDonHang;
import model.DonHang;
import model.GioHang;
import model.TaiKhoan;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;


import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



@WebServlet("/thanhToan")
public class ThanhToanServlet extends HttpServlet {


    private DonHangDAO donHangDAO;

    private ChiTietDonHangDAO chiTietDonHangDAO;



    @Override
    public void init(){

        donHangDAO =
                new DonHangDAO();


        chiTietDonHangDAO =
                new ChiTietDonHangDAO();

    }





    // ===============================
    // Hiển thị trang thanh toán
    // ===============================

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    )
            throws ServletException, IOException {



        HttpSession session =
                request.getSession();



        Object taiKhoan =
                session.getAttribute(
                        "taiKhoan"
                );



        if(taiKhoan == null){


            response.sendRedirect(
                    "dangNhap.jsp"
            );


            return;

        }




        request.getRequestDispatcher(
                        "thanhToan.jsp"
                )
                .forward(
                        request,
                        response
                );

    }





    // ===============================
    // Xử lý đặt hàng
    // ===============================

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    )
            throws ServletException, IOException {


        HttpSession session =
                request.getSession();



        TaiKhoan taiKhoan =
                (TaiKhoan)
                        session.getAttribute(
                                "taiKhoan"
                        );



        if(taiKhoan == null){


            response.sendRedirect(
                    "dangNhap.jsp"
            );


            return;

        }





        List<GioHang> gioHang =
                (List<GioHang>)
                        session.getAttribute(
                                "gioHang"
                        );



        if(gioHang == null ||
                gioHang.isEmpty()){


            response.sendRedirect(
                    "gioHang"
            );


            return;

        }





        double tongTien = 0;



        for(GioHang sp:gioHang){


            tongTien +=
                    sp.getThanhTien();


        }





        // Tạo đơn hàng


        DonHang donHang =
                new DonHang();


        donHang.setMaTaiKhoan(
                taiKhoan.getMaTaiKhoan()
        );


        donHang.setNgayDat(
                Date.valueOf(
                        LocalDate.now()
                )
        );


        donHang.setTongTien(
                tongTien
        );


        donHang.setTrangThai(
                "DANG_XU_LY"
        );




        int maDonHang =
                donHangDAO.themDonHangLayMa(
                        donHang
                );





        if(maDonHang > 0){



            List<ChiTietDonHang>
                    danhSach =
                    new ArrayList<>();



            for(GioHang sp:gioHang){


                ChiTietDonHang ct =
                        new ChiTietDonHang();


                ct.setMaDonHang(
                        maDonHang
                );


                ct.setMaSanPham(
                        sp.getMaSanPham()
                );


                ct.setDonGia(
                        sp.getGia()
                );


                ct.setSoLuong(
                        sp.getSoLuong()
                );



                danhSach.add(ct);


            }




            chiTietDonHangDAO
                    .themDanhSachChiTiet(
                            danhSach
                    );



            // Xóa giỏ hàng


            session.removeAttribute(
                    "gioHang"
            );



            request.setAttribute(
                    "thongBao",
                    "Đặt hàng thành công!"
            );



            request.getRequestDispatcher(
                            "thanhToan.jsp"
                    )
                    .forward(
                            request,
                            response
                    );



        }else{


            request.setAttribute(
                    "loi",
                    "Đặt hàng thất bại!"
            );


            request.getRequestDispatcher(
                            "thanhToan.jsp"
                    )
                    .forward(
                            request,
                            response
                    );

        }


    }


}