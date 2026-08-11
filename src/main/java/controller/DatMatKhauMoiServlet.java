package controller;
import dao.TaiKhoanDAO;
import org.mindrot.jbcrypt.BCrypt;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet("/datMatKhauMoi")
public class DatMatKhauMoiServlet extends HttpServlet {
    private TaiKhoanDAO taiKhoanDAO;
    @Override
    public void init() throws ServletException {
        taiKhoanDAO = new TaiKhoanDAO();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("otpDaXacNhan") == null || session.getAttribute("resetTaiKhoanId") == null) {
            response.sendRedirect(request.getContextPath() + "/quenMatKhau");
            return;
        }
        Boolean otpDaXacNhan = (Boolean) session.getAttribute("otpDaXacNhan");
        if (!Boolean.TRUE.equals(otpDaXacNhan)) {
            response.sendRedirect(request.getContextPath() + "/quenMatKhau");
            return;
        }
        request.getRequestDispatcher("/datMatKhauMoi.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("otpDaXacNhan") == null || session.getAttribute("resetTaiKhoanId") == null) {
            response.sendRedirect(request.getContextPath() + "/quenMatKhau");
            return;
        }
        Boolean otpDaXacNhan = (Boolean) session.getAttribute("otpDaXacNhan");
        if (!Boolean.TRUE.equals(otpDaXacNhan)) {
            response.sendRedirect(request.getContextPath() + "/quenMatKhau");
            return;
        }
        Object idObject = session.getAttribute("resetTaiKhoanId");
        if (idObject == null) {
            response.sendRedirect(request.getContextPath() + "/quenMatKhau");
            return;
        }
        int maTaiKhoan;
        try {
            maTaiKhoan = ((Number) idObject).intValue();
        } catch (Exception e) {
            session.invalidate();
            response.sendRedirect(request.getContextPath() + "/quenMatKhau");
            return;
        }
        String matKhauMoi = request.getParameter("matKhauMoi");
        String xacNhanMatKhau = request.getParameter("xacNhanMatKhau");
        if (matKhauMoi == null || xacNhanMatKhau == null || matKhauMoi.trim().isEmpty() || xacNhanMatKhau.trim().isEmpty()) {
            request.setAttribute("loi", "Vui lòng nhập đầy đủ mật khẩu mới!");
            request.getRequestDispatcher("/datMatKhauMoi.jsp").forward(request, response);
            return;
        }
        if (matKhauMoi.length() < 6) {
            request.setAttribute("loi", "Mật khẩu mới phải có ít nhất 6 ký tự!");
            request.getRequestDispatcher("/datMatKhauMoi.jsp").forward(request, response);
            return;
        }
        if (!matKhauMoi.equals(xacNhanMatKhau)) {
            request.setAttribute("loi", "Xác nhận mật khẩu không khớp!");
            request.getRequestDispatcher("/datMatKhauMoi.jsp").forward(request, response);
            return;
        }
        String matKhauHash = BCrypt.hashpw(matKhauMoi, BCrypt.gensalt());
        boolean thanhCong = taiKhoanDAO.doiMatKhau(maTaiKhoan, matKhauHash);
        if (!thanhCong) {
            request.setAttribute("loi", "Không thể cập nhật mật khẩu. Vui lòng thử lại!");
            request.getRequestDispatcher("/datMatKhauMoi.jsp").forward(request, response);
            return;
        }
        session.removeAttribute("otpDaXacNhan");
        session.removeAttribute("resetTaiKhoanId");
        session.removeAttribute("resetEmail");
        session.removeAttribute("otp");
        session.removeAttribute("otpExpire");
        session.removeAttribute("otpSoLanSai");
        session.invalidate();
        response.sendRedirect(request.getContextPath() + "/dangNhap?thongBao=doiMatKhauThanhCong");
    }
}