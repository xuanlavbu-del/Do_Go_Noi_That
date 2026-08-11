<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.TaiKhoan" %>
<%
    TaiKhoan taiKhoan = (TaiKhoan) request.getAttribute("taiKhoan");
    if (taiKhoan == null) {
        taiKhoan = (TaiKhoan) session.getAttribute("taiKhoan");
    }
    String tab = (String) request.getAttribute("tab");
    if (tab == null) {
        tab = request.getParameter("tab");
    }
    if (tab == null || (!"thongTin".equals(tab) && !"matKhau".equals(tab))) {
        tab = "thongTin";
    }
%>

<!DOCTYPE html>

<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cài đặt tài khoản</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <style>
        body{background:#f5f6fa;min-height:100vh;}
        .container-main{max-width:1000px;margin:40px auto;}
        .card{border:none;border-radius:12px;box-shadow:0 3px 15px rgba(0,0,0,.08);}
        .profile-sidebar{background:#fff;border-radius:12px;padding:20px;}
        .profile-icon{width:80px;height:80px;border-radius:50%;background:#007bff;color:#fff;display:flex;align-items:center;justify-content:center;font-size:34px;font-weight:bold;margin:0 auto 15px;}
        .profile-name{text-align:center;font-weight:600;font-size:18px;}
        .profile-email{text-align:center;color:#6c757d;font-size:14px;word-break:break-word;}
        .menu-item{display:block;padding:12px 15px;color:#343a40;text-decoration:none;border-radius:6px;margin-top:5px;}
        .menu-item:hover{background:#f1f3f5;text-decoration:none;color:#007bff;}
        .menu-item.active{background:#007bff;color:#fff;}
        .content-card{padding:30px;}
        .form-label{font-weight:500;}
        .password-box{position:relative;}
        .show-password{position:absolute;right:12px;top:38px;color:#6c757d;cursor:pointer;font-size:13px;}
        .info-row{padding:10px 0;border-bottom:1px solid #eee;}
        .info-label{font-weight:600;color:#555;}
    </style>
</head>
<body>
<div class="container container-main">
    <div class="row">
        <div class="col-md-4 mb-4">
            <div class="profile-sidebar">
                <div class="profile-icon">
                    <%= taiKhoan != null && taiKhoan.getHoTen() != null && !taiKhoan.getHoTen().trim().isEmpty() ? taiKhoan.getHoTen().trim().substring(0,1).toUpperCase() : "K" %>
                </div>
                <div class="profile-name">
                    <%= taiKhoan != null && taiKhoan.getHoTen() != null ? taiKhoan.getHoTen() : "Khách hàng" %>
                </div>
                <div class="profile-email">
                    <%= taiKhoan != null && taiKhoan.getEmail() != null ? taiKhoan.getEmail() : "" %>
                </div>
                <hr>
                <a href="${pageContext.request.contextPath}/caiDatTaiKhoan?tab=thongTin" class="menu-item <%= "thongTin".equals(tab) ? "active" : "" %>">Thông tin cá nhân</a>
                <a href="${pageContext.request.contextPath}/caiDatTaiKhoan?tab=matKhau" class="menu-item <%= "matKhau".equals(tab) ? "active" : "" %>">Đổi mật khẩu</a>
                <a href="${pageContext.request.contextPath}/dangXuat" class="menu-item">Đăng xuất</a>
                <a href="${pageContext.request.contextPath}/index.jsp" class="menu-item">Quay lại trang chủ</a>
            </div>
        </div>
        <div class="col-md-8">
            <div class="card content-card">
                <% if ("thongTin".equals(tab)) { %>
                <h4 class="mb-4">Thông tin cá nhân</h4>
                <% if (request.getAttribute("loiThongTin") != null) { %>
                <div class="alert alert-danger"><%= request.getAttribute("loiThongTin") %></div>
                <% } %>
                <% if (request.getAttribute("thongBaoThongTin") != null) { %>
                <div class="alert alert-success"><%= request.getAttribute("thongBaoThongTin") %></div>
                <% } %>
                <form action="${pageContext.request.contextPath}/caiDatTaiKhoan" method="post">
                    <input type="hidden" name="action" value="capNhatThongTin">

                    <div class="form-group">
                        <label class="form-label">Tên đăng nhập</label>
                        <input type="text" name="tenDangNhap" class="form-control" value="<%= taiKhoan != null && taiKhoan.getTenDangNhap() != null ? taiKhoan.getTenDangNhap() : "" %>" required>
                    </div>
                    <div class="form-group">
                        <label class="form-label">Họ và tên</label>
                        <input type="text" name="hoTen" class="form-control" value="<%= taiKhoan != null && taiKhoan.getHoTen() != null ? taiKhoan.getHoTen() : "" %>" required>
                    </div>
                    <div class="form-group">
                        <label class="form-label">Email</label>
                        <input type="email" name="email" class="form-control" value="<%= taiKhoan != null && taiKhoan.getEmail() != null ? taiKhoan.getEmail() : "" %>" required>
                    </div>
                    <div class="form-group">
                        <label class="form-label">Số điện thoại</label>
                        <input type="tel" name="soDienThoai" class="form-control" value="<%= taiKhoan != null && taiKhoan.getSoDienThoai() != null ? taiKhoan.getSoDienThoai() : "" %>">
                    </div>
                    <div class="form-group">
                        <label class="form-label">Vai trò</label>
                        <input type="text" class="form-control" value="<%= taiKhoan != null && taiKhoan.getVaiTro() != null ? taiKhoan.getVaiTro() : "" %>" readonly>
                    </div>
                    <button type="submit" class="btn btn-primary">Lưu thay đổi</button>
                </form>
                <% } else { %>
                <h4 class="mb-4">Đổi mật khẩu</h4>
                <% if (request.getAttribute("loiMatKhau") != null) { %>
                <div class="alert alert-danger"><%= request.getAttribute("loiMatKhau") %></div>
                <% } %>
                <% if (request.getAttribute("thongBaoMatKhau") != null) { %>
                <div class="alert alert-success"><%= request.getAttribute("thongBaoMatKhau") %></div>
                <% } %>
                <div class="alert alert-info">
                    Mật khẩu của bạn được mã hóa bằng BCrypt và không thể hiển thị trực tiếp.
                </div>
                <form action="${pageContext.request.contextPath}/caiDatTaiKhoan" method="post">
                    <input type="hidden" name="action" value="doiMatKhau">
                    <div class="form-group password-box">
                        <label class="form-label">Mật khẩu hiện tại</label>
                        <input type="password" id="matKhauCu" name="matKhauCu" class="form-control" placeholder="Nhập mật khẩu hiện tại" required>
                        <span class="show-password" onclick="hienThiMatKhau('matKhauCu','iconCu')"><span id="iconCu">Hiện</span></span>
                    </div>
                    <div class="form-group password-box">
                        <label class="form-label">Mật khẩu mới</label>
                        <input type="password" id="matKhauMoi" name="matKhauMoi" class="form-control" placeholder="Nhập mật khẩu mới" minlength="6" required>
                        <span class="show-password" onclick="hienThiMatKhau('matKhauMoi','iconMoi')"><span id="iconMoi">Hiện</span></span>
                    </div>
                    <div class="form-group password-box">
                        <label class="form-label">Xác nhận mật khẩu mới</label>
                        <input type="password" id="xacNhanMatKhau" name="xacNhanMatKhau" class="form-control" placeholder="Nhập lại mật khẩu mới" minlength="6" required>
                        <span class="show-password" onclick="hienThiMatKhau('xacNhanMatKhau','iconXacNhan')"><span id="iconXacNhan">Hiện</span></span>
                    </div>
                    <div class="d-flex justify-content-between align-items-center">
                        <a href="${pageContext.request.contextPath}/caiDatTaiKhoan?tab=thongTin" class="btn btn-secondary">Quay lại</a>
                        <button type="submit" class="btn btn-primary">Đổi mật khẩu</button>
                    </div>
                </form>
                <% } %>
            </div>
        </div>
    </div>
</div>
<script>
    function hienThiMatKhau(id,iconId){var input=document.getElementById(id);var icon=document.getElementById(iconId);if(input.type==="password"){input.type="text";icon.innerHTML="Ẩn";}else{input.type="password";icon.innerHTML="Hiện";}}
</script>
</body>
</html>
