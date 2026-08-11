
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thêm tài khoản</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">
<div class="container py-5">
    <div class="card shadow">
        <div class="card-header bg-primary text-white">
            <h4 class="mb-0">Thêm tài khoản</h4>
        </div>
        <div class="card-body">
            <% if (request.getAttribute("loi") != null) { %>
            <div class="alert alert-danger">
                <%= request.getAttribute("loi") %>
            </div>
            <% } %>
            <form action="${pageContext.request.contextPath}/quanLyTaiKhoan" method="post">
                <input type="hidden" name="action" value="them">
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label>Tên đăng nhập</label>
                        <input type="text" name="tenDangNhap" class="form-control" required>
                    </div>
                    <div class="form-group col-md-6">
                        <label>Mật khẩu</label>
                        <input type="password" name="matKhau" class="form-control" required>
                    </div>
                </div>
                <div class="form-group">
                    <label>Họ tên</label>
                    <input type="text" name="hoTen" class="form-control" required>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label>Email</label>
                        <input type="email" name="email" class="form-control" required>
                    </div>
                    <div class="form-group col-md-6">
                        <label>Số điện thoại</label>
                        <input type="text" name="soDienThoai" class="form-control">
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label>Vai trò</label>
                        <select name="vaiTro" class="form-control">
                            <option value="KHACH">KHACH</option>
                            <option value="ADMIN">ADMIN</option>
                        </select>
                    </div>
                    <div class="form-group col-md-6">
                        <label>Trạng thái</label>
                        <select name="trangThai" class="form-control">
                            <option value="HOAT_DONG">HOAT_DONG</option>
                            <option value="BI_KHOA">BI_KHOA</option>
                        </select>
                    </div>
                </div>
                <button type="submit" class="btn btn-primary">Thêm tài khoản</button>
                <a href="${pageContext.request.contextPath}/quanLyTaiKhoan"
                   class="btn btn-secondary">Hủy</a>
            </form>
        </div>
    </div>
</div>
</body>
</html>

