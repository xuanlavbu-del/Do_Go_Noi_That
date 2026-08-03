package controller;


import model.GioHang;
import model.SanPham;

import dao.SanPhamDAO;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



@WebServlet("/gioHang")
public class GioHangServlet extends HttpServlet {


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


        HttpSession session =
                request.getSession();



        List<GioHang> gioHang =
                (List<GioHang>)
                        session.getAttribute(
                                "gioHang"
                        );



        if(gioHang == null){

            gioHang =
                    new ArrayList<>();

        }



        String hanhDong =
                request.getParameter(
                        "hanhDong"
                );




        // =================================
        // Xóa sản phẩm
        // =================================

        if("xoa".equals(hanhDong)){


            int maSanPham =
                    Integer.parseInt(
                            request.getParameter(
                                    "maSanPham"
                            )
                    );



            gioHang.removeIf(
                    sp ->
                            sp.getMaSanPham()
                                    ==
                                    maSanPham
            );


        }





        // =================================
        // Cập nhật số lượng
        // =================================

        else if("capNhat".equals(hanhDong)){



            int maSanPham =
                    Integer.parseInt(
                            request.getParameter(
                                    "maSanPham"
                            )
                    );


            int soLuong =
                    Integer.parseInt(
                            request.getParameter(
                                    "soLuong"
                            )
                    );



            for(GioHang sp : gioHang){


                if(sp.getMaSanPham()
                        ==
                        maSanPham){


                    sp.setSoLuong(
                            soLuong
                    );


                    break;

                }

            }

        }




        session.setAttribute(
                "gioHang",
                gioHang
        );



        request.setAttribute(
                "tongTien",
                tinhTongTien(gioHang)
        );



        request.getRequestDispatcher(
                        "gioHang.jsp"
                )
                .forward(request,response);


    }





    // =====================================
    // Thêm giỏ hàng
    // =====================================


    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    )
            throws ServletException, IOException {



        request.setCharacterEncoding(
                "UTF-8"
        );



        HttpSession session =
                request.getSession();



        List<GioHang> gioHang =
                (List<GioHang>)
                        session.getAttribute(
                                "gioHang"
                        );



        if(gioHang == null){

            gioHang =
                    new ArrayList<>();

        }




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



        boolean daCo = false;



        for(GioHang item : gioHang){


            if(item.getMaSanPham()
                    ==
                    maSanPham){



                item.setSoLuong(
                        item.getSoLuong()+1
                );


                daCo=true;


                break;

            }

        }




        if(!daCo){


            GioHang item =
                    new GioHang();


            item.setMaSanPham(
                    sanPham.getMaSanPham()
            );


            item.setTenSanPham(
                    sanPham.getTenSanPham()
            );


            item.setGia(
                    sanPham.getGia()
            );


            item.setSoLuong(
                    1
            );


            item.setHinhAnh(
                    sanPham.getHinhAnh()
            );


            gioHang.add(item);

        }





        session.setAttribute(
                "gioHang",
                gioHang
        );



        response.sendRedirect(
                "gioHang"
        );


    }





    // =====================================
    // Tính tổng tiền
    // =====================================

    private double tinhTongTien(
            List<GioHang> gioHang
    ){


        double tong=0;


        for(GioHang sp:gioHang){


            tong +=
                    sp.getThanhTien();


        }


        return tong;

    }



}