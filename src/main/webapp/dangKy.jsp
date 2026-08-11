<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>

<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng ký tài khoản</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <style>
        body{min-height:100vh;background:#f5f6fa;}
        .register-container{min-height:100vh;display:flex;align-items:center;justify-content:center;padding:30px 15px;}
        .register-card{width:100%;max-width:520px;border:none;border-radius:12px;box-shadow:0 5px 25px rgba(0,0,0,0.1);}
        .register-header{text-align:center;padding:30px 25px 10px;}
        .register-header h3{font-weight:600;margin-bottom:5px;}
        .register-header p{color:#6c757d;margin-bottom:0;}
        .register-body{padding:25px;}
        .form-group label{font-weight:500;}
        .btn-register{width:100%;padding:10px;font-weight:500;}
        .login-link{text-align:center;margin-top:20px;}
        .password-note{font-size:13px;color:#6c757d;}
    </style>
</head>
<body>
<div class="register-container">
    <div class="card register-card">
        <div class="register-header">
            <h3>Đăng ký tài khoản</h3>
            <p>Tạo tài khoản mới tại Đồ Gỗ Nội Thất</p>
        </div>
        <div class="register-body">
            <% if (request.getAttribute("loi") != null) { %>
            <div class="alert alert-danger">
                <%= request.getAttribute("loi") %>
            </div>
            <% } %>
            <form action="${pageContext.request.contextPath}/dangKy" method="post">
                <div class="form-group">
                    <label for="hoTen">Họ và tên</label>
                    <input type="text" class="form-control" id="hoTen" name="hoTen" value="<%= request.getAttribute("hoTen") != null ? request.getAttribute("hoTen") : "" %>" placeholder="Nhập họ và tên" required>
                </div>
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" class="form-control" id="email" name="email" value="<%= request.getAttribute("email") != null ? request.getAttribute("email") : "" %>" placeholder="Nhập email" autocomplete="email" required>
                </div>
                <div class="form-group">
                    <label for="soDienThoai">Số điện thoại</label>
                    <input type="tel" class="form-control" id="soDienThoai" name="soDienThoai" value="<%= request.getAttribute("soDienThoai") != null ? request.getAttribute("soDienThoai") : "" %>" placeholder="Nhập số điện thoại">
                </div>
                <div class="form-group">
                    <label for="diaChi">Địa chỉ</label>
                    <input type="text" class="form-control" id="diaChi" name="diaChi" value="<%= request.getAttribute("diaChi") != null ? request.getAttribute("diaChi") : "" %>" placeholder="Nhập địa chỉ">
                </div>
                <div class="form-group">
                    <label for="matKhau">Mật khẩu</label>
                    <input type="password" class="form-control" id="matKhau" name="matKhau" placeholder="Nhập mật khẩu" autocomplete="new-password" minlength="6" required>
                    <small class="password-note">Mật khẩu phải có ít nhất 6 ký tự.</small>
                </div>
                <div class="form-group">
                    <label for="xacNhanMatKhau">Xác nhận mật khẩu</label>
                    <input type="password" class="form-control" id="xacNhanMatKhau" name="xacNhanMatKhau" placeholder="Nhập lại mật khẩu" autocomplete="new-password" minlength="6" required>
                </div>
                <div class="form-group form-check">
                    <input type="checkbox" class="form-check-input" id="hienMatKhau" onclick="hienThiMatKhau()">
                    <label class="form-check-label" for="hienMatKhau">Hiển thị mật khẩu</label>
                </div>
                <button type="submit" class="btn btn-primary btn-register">Đăng ký</button>
            </form>
            <div class="login-link">
                Đã có tài khoản?
                <a href="${pageContext.request.contextPath}/dangNhap">Đăng nhập</a>
            </div>
        </div>
    </div>
</div>
<script>
    function hienThiMatKhau(){const matKhau=document.getElementById("matKhau");const xacNhan=document.getElementById("xacNhanMatKhau");const checkbox=document.getElementById("hienMatKhau");if(checkbox.checked){matKhau.type="text";xacNhan.type="text";}else{matKhau.type="password";xacNhan.type="password";}}
</script>
</body>
</html>
