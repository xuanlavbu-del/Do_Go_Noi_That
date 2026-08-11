package controller;

import dao.ChiTietPhieuXuatDAO;
import dao.PhieuXuatDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ChiTietPhieuXuat;
import model.PhieuXuat;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/chiTietPhieuXuat")
public class ChiTietPhieuXuatServlet extends HttpServlet{

    private ChiTietPhieuXuatDAO chiTietDAO;
    private PhieuXuatDAO phieuXuatDAO;

    @Override
    public void init(){

        chiTietDAO=new ChiTietPhieuXuatDAO();
        phieuXuatDAO=new PhieuXuatDAO();

    }

    @Override
    protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{

        try{

            String id=request.getParameter("maPhieuXuat");

            if(id==null||id.trim().isEmpty()){

                response.sendRedirect(request.getContextPath()+"/lichSuXuat");

                return;

            }

            int maPhieuXuat=Integer.parseInt(id);

            PhieuXuat phieuXuat=phieuXuatDAO.findById(maPhieuXuat);

            if(phieuXuat==null){

                request.setAttribute("loi","Không tìm thấy phiếu xuất.");

                request.getRequestDispatcher("/admin/lichSuXuat.jsp").forward(request,response);

                return;

            }

            ArrayList<ChiTietPhieuXuat> dsChiTiet=chiTietDAO.getByPhieuXuat(maPhieuXuat);

            double tongTien=chiTietDAO.tinhTongTien(maPhieuXuat);

            request.setAttribute("phieuXuat",phieuXuat);

            request.setAttribute("dsChiTiet",dsChiTiet);

            request.setAttribute("tongTien",tongTien);

            request.getRequestDispatcher("/admin/chiTietPhieuXuat.jsp").forward(request,response);

        }catch(NumberFormatException e){

            response.sendRedirect(request.getContextPath()+"/lichSuXuat");

        }catch(Exception e){

            e.printStackTrace();

            request.setAttribute("loi","Có lỗi xảy ra: "+e.getMessage());

            request.getRequestDispatcher("/admin/lichSuXuat.jsp").forward(request,response);

        }

    }

    @Override
    protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{

        doGet(request,response);

    }

}