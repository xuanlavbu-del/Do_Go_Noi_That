package controller;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet("/xacNhanOTP")
public class XacNhanOTPServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("otp") == null || session.getAttribute("resetTaiKhoanId") == null) {
            response.sendRedirect(request.getContextPath() + "/quenMatKhau");
            return;
        }
        request.getRequestDispatcher("/quenMatKhau.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("otp") == null || session.getAttribute("resetTaiKhoanId") == null) {
            response.sendRedirect(request.getContextPath() + "/quenMatKhau");
            return;
        }
        String otpNhap = request.getParameter("otp");
        if (otpNhap == null || otpNhap.trim().isEmpty()) {
            request.setAttribute("loi", "Vui lòng nhập mã OTP!");
            request.getRequestDispatcher("/quenMatKhau.jsp").forward(request, response);
            return;
        }
        otpNhap = otpNhap.trim();
        String otpSession = (String) session.getAttribute("otp");
        Long otpExpire = (Long) session.getAttribute("otpExpire");
        if (otpExpire == null || System.currentTimeMillis() > otpExpire) {
            session.removeAttribute("otp");
            session.removeAttribute("otpExpire");
            session.removeAttribute("otpSoLanSai");
            request.setAttribute("loi", "Mã OTP đã hết hạn. Vui lòng yêu cầu mã OTP mới!");
            request.getRequestDispatcher("/quenMatKhau.jsp").forward(request, response);
            return;
        }
        if (!otpNhap.equals(otpSession)) {
            Integer soLanSai = (Integer) session.getAttribute("otpSoLanSai");
            if (soLanSai == null) {
                soLanSai = 0;
            }
            soLanSai++;
            session.setAttribute("otpSoLanSai", soLanSai);
            if (soLanSai >= 5) {
                session.removeAttribute("otp");
                session.removeAttribute("otpExpire");
                session.removeAttribute("otpSoLanSai");
                session.removeAttribute("resetTaiKhoanId");
                session.removeAttribute("resetEmail");
                request.setAttribute("loi", "Bạn đã nhập sai OTP quá 5 lần. Vui lòng yêu cầu mã OTP mới!");
                request.getRequestDispatcher("/quenMatKhau.jsp").forward(request, response);
                return;
            }
            request.setAttribute("loi", "Mã OTP không chính xác! Bạn còn " + (5 - soLanSai) + " lần thử.");
            request.getRequestDispatcher("/quenMatKhau.jsp").forward(request, response);
            return;
        }
        session.setAttribute("otpDaXacNhan", true);
        session.removeAttribute("otp");
        session.removeAttribute("otpExpire");
        session.removeAttribute("otpSoLanSai");
        response.sendRedirect(request.getContextPath() + "/quenMatKhau");
    }
}