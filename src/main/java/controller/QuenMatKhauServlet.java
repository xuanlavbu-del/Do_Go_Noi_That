package controller;
import dao.TaiKhoanDAO;
import model.TaiKhoan;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Random;
@WebServlet("/quenMatKhau")
public class QuenMatKhauServlet extends HttpServlet {
    private TaiKhoanDAO taiKhoanDAO;
    @Override
    public void init() throws ServletException {
        taiKhoanDAO = new TaiKhoanDAO();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/quenMatKhau.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String email = request.getParameter("email");
        if (email == null || email.trim().isEmpty()) {
            request.setAttribute("loi", "Vui lòng nhập email!");
            request.getRequestDispatcher("/quenMatKhau.jsp").forward(request, response);
            return;
        }
        email = email.trim();
        TaiKhoan taiKhoan = taiKhoanDAO.getByEmail(email);
        if (taiKhoan == null) {
            request.setAttribute("loi", "Email không tồn tại trong hệ thống!");
            request.getRequestDispatcher("/quenMatKhau.jsp").forward(request, response);
            return;
        }
        if (taiKhoan.getTrangThai() != null && !"HOAT_DONG".equalsIgnoreCase(taiKhoan.getTrangThai())) {
            request.setAttribute("loi", "Tài khoản hiện đang bị khóa!");
            request.getRequestDispatcher("/quenMatKhau.jsp").forward(request, response);
            return;
        }
        Random random = new Random();
        String otp = String.format("%06d", random.nextInt(1000000));
        HttpSession session = request.getSession();
        session.setAttribute("otp", otp);
        session.setAttribute("otpExpire", System.currentTimeMillis() + 5 * 60 * 1000);
        session.setAttribute("resetTaiKhoanId", taiKhoan.getMaTaiKhoan());
        session.setAttribute("resetEmail", taiKhoan.getEmail());
        session.setAttribute("otpSoLanSai", 0);
        request.setAttribute("email", email);
        request.setAttribute("otp", otp);
        request.getRequestDispatcher("/quenMatKhau.jsp").forward(request, response);
    }
}