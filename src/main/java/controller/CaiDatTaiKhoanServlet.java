package controller;
import dao.TaiKhoanDAO;
import model.TaiKhoan;
import org.mindrot.jbcrypt.BCrypt;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet("/caiDatTaiKhoan")
public class CaiDatTaiKhoanServlet extends HttpServlet {
    private TaiKhoanDAO taiKhoanDAO;
    @Override
    public void init() throws ServletException {
        taiKhoanDAO = new TaiKhoanDAO();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("taiKhoan") == null) {
            response.sendRedirect(request.getContextPath() + "/dangNhap");
            return;
        }
        TaiKhoan taiKhoanSession = (TaiKhoan) session.getAttribute("taiKhoan");
        TaiKhoan taiKhoan = taiKhoanDAO.getById(taiKhoanSession.getMaTaiKhoan());
        if (taiKhoan == null) {
            session.invalidate();
            response.sendRedirect(request.getContextPath() + "/dangNhap");
            return;
        }
        session.setAttribute("taiKhoan", taiKhoan);

        session.setAttribute("hoTen", taiKhoan.getHoTen());
        session.setAttribute("vaiTro", taiKhoan.getVaiTro());
        request.setAttribute("taiKhoan", taiKhoan);
        String tab = request.getParameter("tab");
        if (!"matKhau".equals(tab)) {
            tab = "thongTin";
        }
        request.setAttribute("tab", tab);
        request.getRequestDispatcher("/caiDatTaiKhoan.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("taiKhoan") == null) {
            response.sendRedirect(request.getContextPath() + "/dangNhap");
            return;
        }
        TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("taiKhoan");
        int maTaiKhoan = taiKhoan.getMaTaiKhoan();
        String action = request.getParameter("action");
        if ("capNhatThongTin".equals(action)) {
            capNhatThongTin(request, response, session, taiKhoan, maTaiKhoan);
            return;
        }
        if ("doiMatKhau".equals(action)) {
            doiMatKhau(request, response, session, taiKhoan, maTaiKhoan);
            return;
        }
        response.sendRedirect(request.getContextPath() + "/caiDatTaiKhoan");
    }
    private void capNhatThongTin(HttpServletRequest request, HttpServletResponse response, HttpSession session, TaiKhoan taiKhoan, int maTaiKhoan) throws ServletException, IOException {
        String tenDangNhap = request.getParameter("tenDangNhap");
        String hoTen = request.getParameter("hoTen");
        String email = request.getParameter("email");
        String soDienThoai = request.getParameter("soDienThoai");
        if (tenDangNhap == null || tenDangNhap.trim().isEmpty() || hoTen == null || hoTen.trim().isEmpty() || email == null || email.trim().isEmpty()) {
            request.setAttribute("loiThongTin", "Họ tên và email không được để trống.");
            request.setAttribute("tab", "thongTin");
            request.setAttribute("taiKhoan", taiKhoan);
            request.getRequestDispatcher("/caiDatTaiKhoan.jsp").forward(request, response);
            return;
        }

        tenDangNhap = tenDangNhap.trim();
        hoTen = hoTen.trim();
        email = email.trim();
        soDienThoai = soDienThoai == null ? "" : soDienThoai.trim();
        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {

            request.setAttribute("loiThongTin", "Email không hợp lệ.");
            request.setAttribute("tab", "thongTin");
            request.setAttribute("taiKhoan", taiKhoan);
            request.getRequestDispatcher("/caiDatTaiKhoan.jsp").forward(request, response);
            return;
        }
        if (!tenDangNhap.matches("^[A-Za-z0-9._-]{3,30}$")) {
            request.setAttribute("loiThongTin", "Tên đăng nhập chỉ được chứa chữ cái, số, dấu chấm, gạch dưới hoặc gạch ngang và có từ 3 đến 30 ký tự.");
            request.setAttribute("tab", "thongTin");
            request.setAttribute("taiKhoan", taiKhoan);
            request.getRequestDispatcher("/caiDatTaiKhoan.jsp").forward(request, response);
            return;
        }
        if (taiKhoanDAO.kiemTraTenDangNhapTonTai(tenDangNhap, maTaiKhoan)) {
            request.setAttribute("loiThongTin", "Tên đăng nhập này đã được sử dụng.");
            request.setAttribute("tab", "thongTin");
            request.setAttribute("taiKhoan", taiKhoan);
            request.getRequestDispatcher("/caiDatTaiKhoan.jsp").forward(request, response);
            return;
        }
        if (taiKhoanDAO.kiemTraEmailTonTai(email, maTaiKhoan)) {
            request.setAttribute("loiThongTin", "Email này đã được sử dụng bởi tài khoản khác.");
            request.setAttribute("tab", "thongTin");
            request.setAttribute("taiKhoan", taiKhoan);
            request.getRequestDispatcher("/caiDatTaiKhoan.jsp").forward(request, response);
            return;
        }
        if (taiKhoanDAO.capNhatThongTinCaNhan(maTaiKhoan, tenDangNhap, hoTen, email, soDienThoai)) {
            TaiKhoan taiKhoanMoi = taiKhoanDAO.getById(maTaiKhoan);
            session.setAttribute("taiKhoan", taiKhoanMoi);
            session.setAttribute("maTaiKhoan", taiKhoanMoi.getMaTaiKhoan());
            session.setAttribute("tenDangNhap", taiKhoanMoi.getTenDangNhap());
            session.setAttribute("hoTen", taiKhoanMoi.getHoTen());
            session.setAttribute("soDienThoai", taiKhoanMoi.getSoDienThoai());
            session.setAttribute("vaiTro", taiKhoanMoi.getVaiTro());
            request.setAttribute("taiKhoan", taiKhoanMoi);
            request.setAttribute("thongBaoThongTin", "Cập nhật thông tin cá nhân thành công.");
        } else {
            request.setAttribute("taiKhoan", taiKhoan);
            request.setAttribute("loiThongTin", "Cập nhật thông tin thất bại.");
        }
        request.setAttribute("tab", "thongTin");
        request.getRequestDispatcher("/caiDatTaiKhoan.jsp").forward(request, response);
    }
    private void doiMatKhau(HttpServletRequest request, HttpServletResponse response, HttpSession session, TaiKhoan taiKhoan, int maTaiKhoan) throws ServletException, IOException {
        String matKhauCu = request.getParameter("matKhauCu");
        String matKhauMoi = request.getParameter("matKhauMoi");
        String xacNhanMatKhau = request.getParameter("xacNhanMatKhau");
        if (matKhauCu == null || matKhauMoi == null || xacNhanMatKhau == null || matKhauCu.isEmpty() || matKhauMoi.isEmpty() || xacNhanMatKhau.isEmpty()) {
            request.setAttribute("loiMatKhau", "Vui lòng nhập đầy đủ thông tin.");
            request.setAttribute("tab", "matKhau");
            request.setAttribute("taiKhoan", taiKhoan);
            request.getRequestDispatcher("/caiDatTaiKhoan.jsp").forward(request, response);
            return;
        }
        if (taiKhoan.getMatKhau() == null || !BCrypt.checkpw(matKhauCu, taiKhoan.getMatKhau())) {
            request.setAttribute("loiMatKhau", "Mật khẩu hiện tại không đúng.");
            request.setAttribute("tab", "matKhau");
            request.setAttribute("taiKhoan", taiKhoan);
            request.getRequestDispatcher("/caiDatTaiKhoan.jsp").forward(request, response);
            return;
        }
        if (matKhauMoi.length() < 6) {
            request.setAttribute("loiMatKhau", "Mật khẩu mới phải có ít nhất 6 ký tự.");
            request.setAttribute("tab", "matKhau");
            request.setAttribute("taiKhoan", taiKhoan);
            request.getRequestDispatcher("/caiDatTaiKhoan.jsp").forward(request, response);
            return;
        }
        if (!matKhauMoi.equals(xacNhanMatKhau)) {
            request.setAttribute("loiMatKhau", "Xác nhận mật khẩu mới không khớp.");
            request.setAttribute("tab", "matKhau");
            request.setAttribute("taiKhoan", taiKhoan);
            request.getRequestDispatcher("/caiDatTaiKhoan.jsp").forward(request, response);
            return;
        }
        if (BCrypt.checkpw(matKhauMoi, taiKhoan.getMatKhau())) {
            request.setAttribute("loiMatKhau", "Mật khẩu mới phải khác mật khẩu hiện tại.");
            request.setAttribute("tab", "matKhau");
            request.setAttribute("taiKhoan", taiKhoan);
            request.getRequestDispatcher("/caiDatTaiKhoan.jsp").forward(request, response);
            return;
        }
        String matKhauHash = BCrypt.hashpw(matKhauMoi, BCrypt.gensalt());
        if (taiKhoanDAO.doiMatKhau(maTaiKhoan, matKhauHash)) {
            TaiKhoan taiKhoanMoi = taiKhoanDAO.getById(maTaiKhoan);
            session.setAttribute("taiKhoan", taiKhoanMoi);
            session.setAttribute("maTaiKhoan", taiKhoanMoi.getMaTaiKhoan());
            session.setAttribute("hoTen", taiKhoanMoi.getHoTen());
            session.setAttribute("vaiTro", taiKhoanMoi.getVaiTro());
            request.setAttribute("taiKhoan", taiKhoanMoi);
            request.setAttribute("thongBaoMatKhau", "Đổi mật khẩu thành công.");
        } else {
            request.setAttribute("taiKhoan", taiKhoan);
            request.setAttribute("loiMatKhau", "Đổi mật khẩu thất bại.");
        }
        request.setAttribute("tab", "matKhau");
        request.getRequestDispatcher("/caiDatTaiKhoan.jsp").forward(request, response);
    }
}
