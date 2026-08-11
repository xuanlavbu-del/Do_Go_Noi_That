package controller;
import dao.TaiKhoanDAO;
import model.TaiKhoan;
import org.mindrot.jbcrypt.BCrypt;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/dangKy")
public class DangKyServlet extends HttpServlet {
    private TaiKhoanDAO taiKhoanDAO;
    @Override
    public void init() throws ServletException {
        taiKhoanDAO = new TaiKhoanDAO();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/dangKy.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String hoTen = request.getParameter("hoTen");
        String email = request.getParameter("email");
        String matKhau = request.getParameter("matKhau");
        String xacNhanMatKhau = request.getParameter("xacNhanMatKhau");
        String soDienThoai = request.getParameter("soDienThoai");
        String diaChi = request.getParameter("diaChi");
        if (hoTen == null || email == null || matKhau == null || xacNhanMatKhau == null || soDienThoai == null || hoTen.trim().isEmpty() || email.trim().isEmpty() || matKhau.trim().isEmpty() || xacNhanMatKhau.trim().isEmpty()) {
            request.setAttribute("loi", "Vui lòng nhập đầy đủ thông tin!");
            request.setAttribute("hoTen", hoTen);
            request.setAttribute("email", email);
            request.setAttribute("soDienThoai", soDienThoai);
            request.setAttribute("diaChi", diaChi);
            request.getRequestDispatcher("/dangKy.jsp").forward(request, response);
            return;
        }
        hoTen = hoTen.trim();
        email = email.trim();
        soDienThoai = soDienThoai.trim();
        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            request.setAttribute("loi", "Email không hợp lệ!");
            request.setAttribute("hoTen", hoTen);
            request.setAttribute("email", email);
            request.setAttribute("soDienThoai", soDienThoai);
            request.setAttribute("diaChi", diaChi);
            request.getRequestDispatcher("/dangKy.jsp").forward(request, response);
            return;
        }
        if (matKhau.length() < 6) {
            request.setAttribute("loi", "Mật khẩu phải có ít nhất 6 ký tự!");
            request.setAttribute("hoTen", hoTen);
            request.setAttribute("email", email);
            request.setAttribute("soDienThoai", soDienThoai);
            request.setAttribute("diaChi", diaChi);
            request.getRequestDispatcher("/dangKy.jsp").forward(request, response);
            return;
        }
        if (!matKhau.equals(xacNhanMatKhau)) {
            request.setAttribute("loi", "Mật khẩu xác nhận không đúng!");
            request.setAttribute("hoTen", hoTen);
            request.setAttribute("email", email);
            request.setAttribute("soDienThoai", soDienThoai);
            request.setAttribute("diaChi", diaChi);
            request.getRequestDispatcher("/dangKy.jsp").forward(request, response);
            return;
        }
        if (taiKhoanDAO.kiemTraEmailTonTai(email)) {
            request.setAttribute("loi", "Email đã được sử dụng!");
            request.setAttribute("hoTen", hoTen);
            request.setAttribute("email", email);
            request.setAttribute("soDienThoai", soDienThoai);
            request.setAttribute("diaChi", diaChi);
            request.getRequestDispatcher("/dangKy.jsp").forward(request, response);
            return;
        }
        String matKhauHash = BCrypt.hashpw(matKhau, BCrypt.gensalt());
        TaiKhoan taiKhoan = new TaiKhoan();
        taiKhoan.setTenDangNhap(email);
        taiKhoan.setHoTen(hoTen);
        taiKhoan.setEmail(email);
        taiKhoan.setMatKhau(matKhauHash);
        taiKhoan.setSoDienThoai(soDienThoai);
        taiKhoan.setVaiTro("KHACH");
        taiKhoan.setTrangThai("HOAT_DONG");
        boolean ketQua = taiKhoanDAO.dangKy(taiKhoan);
        if (ketQua) {
            request.setAttribute("thongBao", "Đăng ký thành công! Vui lòng đăng nhập.");
            request.getRequestDispatcher("/dangNhap.jsp").forward(request, response);
        } else {
            request.setAttribute("loi", "Đăng ký thất bại! Vui lòng kiểm tra lại thông tin.");
            request.setAttribute("hoTen", hoTen);
            request.setAttribute("email", email);
            request.setAttribute("soDienThoai", soDienThoai);
            request.setAttribute("diaChi", diaChi);
            request.getRequestDispatcher("/dangKy.jsp").forward(request, response);
        }
    }
}
