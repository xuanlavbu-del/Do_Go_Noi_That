package controller;
import dao.TaiKhoanDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.TaiKhoan;
import org.mindrot.jbcrypt.BCrypt;
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
    ) throws ServletException, IOException {
        request.getRequestDispatcher("dangNhap.jsp")
                .forward(request, response);
    }
    // ==============================
    // Xử lý đăng nhập
    // ==============================
    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String email = request.getParameter("email");
        String matKhau = request.getParameter("matKhau");
        // ==============================
        // Kiểm tra dữ liệu
        // ==============================
        if (email == null ||
                email.trim().isEmpty() ||
                matKhau == null ||
                matKhau.trim().isEmpty()) {
            request.setAttribute(
                    "loi",
                    "Vui lòng nhập đầy đủ thông tin!"
            );
            request.getRequestDispatcher("dangNhap.jsp")
                    .forward(request, response);
            return;
        }
        email = email.trim();
        // ==============================
        // Lấy tài khoản theo email
        // ==============================
        TaiKhoan taiKhoan =
                taiKhoanDAO.getByEmail(email);
        // ==============================
        // Kiểm tra tài khoản
        // ==============================
        if (taiKhoan == null) {
            request.setAttribute(
                    "loi",
                    "Email hoặc mật khẩu không đúng!"
            );
            request.getRequestDispatcher("dangNhap.jsp")
                    .forward(request, response);
            return;
        }
        // ==============================
        // Kiểm tra trạng thái
        // ==============================
        if (!"HOAT_DONG".equalsIgnoreCase(
                taiKhoan.getTrangThai())) {
            request.setAttribute(
                    "loi",
                    "Tài khoản của bạn đã bị khóa!"
            );
            request.getRequestDispatcher("dangNhap.jsp")
                    .forward(request, response);
            return;
        }
        // ==============================
        // Kiểm tra BCrypt
        // ==============================
        boolean matKhauDung = false;
        try {
            matKhauDung = BCrypt.checkpw(
                    matKhau,
                    taiKhoan.getMatKhau()
            );
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        if (!matKhauDung) {
            request.setAttribute(
                    "loi",
                    "Email hoặc mật khẩu không đúng!"
            );
            request.getRequestDispatcher("dangNhap.jsp")
                    .forward(request, response);
            return;
        }
        // ==============================
        // Đăng nhập thành công
        // ==============================
        HttpSession session = request.getSession();
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
        // ==============================
        // Phân quyền
        // ==============================
        if (taiKhoan.laQuanTri()) {
            response.sendRedirect(
                    request.getContextPath()
                            + "/dashboard"
            );
        } else {
            response.sendRedirect(
                    request.getContextPath()
                            + "/index.jsp"
            );
        }
    }
}