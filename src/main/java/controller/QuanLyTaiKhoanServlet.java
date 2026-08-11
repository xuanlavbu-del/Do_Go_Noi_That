
package controller;
import dao.TaiKhoanDAO;
import model.TaiKhoan;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;
import java.io.IOException;
import java.util.List;
@WebServlet("/quanLyTaiKhoan")
public class QuanLyTaiKhoanServlet extends HttpServlet {
    private TaiKhoanDAO taiKhoanDAO;

    @Override
    public void init() throws ServletException {
        taiKhoanDAO = new TaiKhoanDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null || action.trim().isEmpty()) {
            danhSach(request, response);
            return;
        }
        switch (action) {
            case "them":
                hienThiFormThem(request, response);
                break;
            case "sua":
                hienThiFormSua(request, response);
                break;
            case "xoa":
                xoaTaiKhoan(request, response);
                break;
            case "doiTrangThai":
                doiTrangThai(request, response);
                break;
            case "timKiem":
                timKiem(request, response);
                break;
            default:
                danhSach(request, response);
                break;
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if ("them".equals(action)) {
            themTaiKhoan(request, response);
            return;
        }
        if ("sua".equals(action)) {
            suaTaiKhoan(request, response);
            return;
        }
        danhSach(request, response);
    }
    private void danhSach(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<TaiKhoan> danhSachTaiKhoan = taiKhoanDAO.getAll();
        request.setAttribute("danhSachTaiKhoan", danhSachTaiKhoan);
        request.getRequestDispatcher("/admin/quanLyTaiKhoan.jsp")
                .forward(request, response);
    }
    private void hienThiFormThem(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/admin/themTaiKhoan.jsp")
                .forward(request, response);
    }
    private void hienThiFormSua(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        if (idStr == null || idStr.trim().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/quanLyTaiKhoan");
            return;
        }
        try {
            int maTaiKhoan = Integer.parseInt(idStr);
            TaiKhoan taiKhoan = taiKhoanDAO.getById(maTaiKhoan);
            if (taiKhoan == null) {
                response.sendRedirect(request.getContextPath() + "/quanLyTaiKhoan");
                return;
            }
            request.setAttribute("taiKhoan", taiKhoan);
            request.getRequestDispatcher("/admin/suaTaiKhoan.jsp")
                    .forward(request, response);
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/quanLyTaiKhoan");
        }
    }
    private void themTaiKhoan(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String tenDangNhap = request.getParameter("tenDangNhap");
        String matKhau = request.getParameter("matKhau");
        String hoTen = request.getParameter("hoTen");
        String email = request.getParameter("email");
        String soDienThoai = request.getParameter("soDienThoai");
        String vaiTro = request.getParameter("vaiTro");
        String trangThai = request.getParameter("trangThai");
        if (tenDangNhap == null || tenDangNhap.trim().isEmpty() ||
                matKhau == null || matKhau.trim().isEmpty() ||
                hoTen == null || hoTen.trim().isEmpty() ||
                email == null || email.trim().isEmpty()) {
            request.setAttribute("loi", "Vui lòng nhập đầy đủ thông tin bắt buộc!");
            hienThiFormThem(request, response);
            return;
        }
        tenDangNhap = tenDangNhap.trim();
        hoTen = hoTen.trim();
        email = email.trim();
        if (taiKhoanDAO.kiemTraEmailTonTai(email)) {
            request.setAttribute("loi", "Email đã tồn tại!");
            hienThiFormThem(request, response);
            return;
        }
        TaiKhoan taiKhoan = new TaiKhoan();
        taiKhoan.setTenDangNhap(tenDangNhap);
        taiKhoan.setMatKhau(BCrypt.hashpw(matKhau, BCrypt.gensalt()));
        taiKhoan.setHoTen(hoTen);
        taiKhoan.setEmail(email);
        taiKhoan.setSoDienThoai(soDienThoai);
        taiKhoan.setVaiTro(
                vaiTro == null || vaiTro.trim().isEmpty()
                        ? "KHACH"
                        : vaiTro.trim().toUpperCase()
        );
        taiKhoan.setTrangThai(
                trangThai == null || trangThai.trim().isEmpty()
                        ? "HOAT_DONG"
                        : trangThai.trim().toUpperCase()
        );
        boolean thanhCong = taiKhoanDAO.themTaiKhoan(taiKhoan);
        if (thanhCong) {
            response.sendRedirect(
                    request.getContextPath() + "/quanLyTaiKhoan"
            );
        } else {
            request.setAttribute("loi", "Thêm tài khoản thất bại!");
            request.setAttribute("taiKhoan", taiKhoan);
            hienThiFormThem(request, response);
        }
    }
    private void suaTaiKhoan(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("maTaiKhoan");
        if (idStr == null || idStr.trim().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/quanLyTaiKhoan");
            return;
        }
        try {
            int maTaiKhoan = Integer.parseInt(idStr);
            TaiKhoan taiKhoan = taiKhoanDAO.getById(maTaiKhoan);
            if (taiKhoan == null) {
                response.sendRedirect(request.getContextPath() + "/quanLyTaiKhoan");
                return;
            }
            String tenDangNhap = request.getParameter("tenDangNhap");
            String hoTen = request.getParameter("hoTen");
            String email = request.getParameter("email");
            String soDienThoai = request.getParameter("soDienThoai");
            String vaiTro = request.getParameter("vaiTro");
            String trangThai = request.getParameter("trangThai");
            if (tenDangNhap == null || tenDangNhap.trim().isEmpty() ||
                    hoTen == null || hoTen.trim().isEmpty() ||
                    email == null || email.trim().isEmpty()) {
                request.setAttribute("loi", "Vui lòng nhập đầy đủ thông tin bắt buộc!");
                request.setAttribute("taiKhoan", taiKhoan);
                request.getRequestDispatcher("/admin/suaTaiKhoan.jsp")
                        .forward(request, response);
                return;
            }
            taiKhoan.setTenDangNhap(tenDangNhap.trim());
            taiKhoan.setHoTen(hoTen.trim());
            taiKhoan.setEmail(email.trim());
            taiKhoan.setSoDienThoai(soDienThoai);
            taiKhoan.setVaiTro(
                    vaiTro == null || vaiTro.trim().isEmpty()
                            ? "KHACH"
                            : vaiTro.trim().toUpperCase()
            );
            taiKhoan.setTrangThai(
                    trangThai == null || trangThai.trim().isEmpty()
                            ? "HOAT_DONG"
                            : trangThai.trim().toUpperCase()
            );
            String matKhauMoi = request.getParameter("matKhau");
            if (matKhauMoi != null && !matKhauMoi.trim().isEmpty()) {
                taiKhoan.setMatKhau(
                        BCrypt.hashpw(matKhauMoi, BCrypt.gensalt())
                );
                taiKhoanDAO.doiMatKhau(
                        maTaiKhoan,
                        taiKhoan.getMatKhau()
                );
            }
            boolean thanhCong = taiKhoanDAO.suaTaiKhoan(taiKhoan);
            if (thanhCong) {
                response.sendRedirect(
                        request.getContextPath() + "/quanLyTaiKhoan"
                );
            } else {
                request.setAttribute("loi", "Cập nhật tài khoản thất bại!");
                request.setAttribute("taiKhoan", taiKhoan);
                request.getRequestDispatcher("/admin/suaTaiKhoan.jsp")
                        .forward(request, response);
            }
        } catch (NumberFormatException e) {
            response.sendRedirect(
                    request.getContextPath() + "/quanLyTaiKhoan"
            );
        }
    }
    private void xoaTaiKhoan(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String idStr = request.getParameter("id");
        if (idStr != null && !idStr.trim().isEmpty()) {
            try {
                int maTaiKhoan = Integer.parseInt(idStr);
                taiKhoanDAO.xoaTaiKhoan(maTaiKhoan);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        response.sendRedirect(
                request.getContextPath() + "/quanLyTaiKhoan"
        );
    }
    private void doiTrangThai(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String idStr = request.getParameter("id");
        String trangThai = request.getParameter("trangThai");
        if (idStr != null && trangThai != null) {
            try {
                int maTaiKhoan = Integer.parseInt(idStr);
                taiKhoanDAO.doiTrangThai(
                        maTaiKhoan,
                        trangThai
                );
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        response.sendRedirect(
                request.getContextPath() + "/quanLyTaiKhoan"
        );
    }
    private void timKiem(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String tuKhoa = request.getParameter("tuKhoa");
        List<TaiKhoan> danhSachTaiKhoan;
        if (tuKhoa == null || tuKhoa.trim().isEmpty()) {
            danhSachTaiKhoan = taiKhoanDAO.getAll();
        } else {
            danhSachTaiKhoan = taiKhoanDAO.timKiem(tuKhoa.trim());
        }
        request.setAttribute("danhSachTaiKhoan", danhSachTaiKhoan);
        request.getRequestDispatcher("/admin/quanLyTaiKhoan.jsp")
                .forward(request, response);
    }
}

