<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>

<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng nhập</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <style>
        body{min-height:100vh;background:#f5f6fa;}
        .login-container{min-height:100vh;display:flex;align-items:center;justify-content:center;padding:30px 15px;}
        .login-card{width:100%;max-width:430px;border:none;border-radius:12px;box-shadow:0 5px 25px rgba(0,0,0,0.10);}
        .login-header{text-align:center;padding:30px 25px 10px;}
        .login-header h3{font-weight:600;margin-bottom:5px;}
        .login-header p{color:#6c757d;margin-bottom:0;}
        .login-body{padding:25px;}
        .form-group label{font-weight:500;}
        .btn-login{width:100%;padding:10px;font-weight:500;}
        .forgot-password{text-align:right;margin-top:-10px;margin-bottom:15px;}
        .register-link{text-align:center;margin-top:20px;}
        .back-home{text-align:center;margin-top:15px;}
    </style>
</head>
<body>
<div class="login-container">
    <div class="card login-card">
        <div class="login-header">
            <h3>Đăng nhập</h3>
            <p>Đăng nhập vào tài khoản của bạn</p>
        </div>
        <div class="login-body">
            <% if (request.getAttribute("loi") != null) { %>
            <div class="alert alert-danger">
                <%= request.getAttribute("loi") %>
            </div>
            <% } %>
            <% if (request.getAttribute("thongBao") != null) { %>
            <div class="alert alert-success">
                <%= request.getAttribute("thongBao") %>
            </div>
            <% } %>
            <% if ("doiMatKhauThanhCong".equals(request.getParameter("thongBao"))) { %>
            <div class="alert alert-success">
                Đổi mật khẩu thành công! Vui lòng đăng nhập bằng mật khẩu mới.
            </div>
            <% } %>
            <form action="${pageContext.request.contextPath}/dangNhap" method="post">
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" class="form-control" id="email" name="email" value="<%= request.getParameter("email") != null ? request.getParameter("email") : "" %>" placeholder="Nhập email" autocomplete="email" required>
                </div>
                <div class="form-group">
                    <label for="matKhau">Mật khẩu</label>
                    <input type="password" class="form-control" id="matKhau" name="matKhau" placeholder="Nhập mật khẩu" autocomplete="current-password" required>
                </div>
                <div class="forgot-password">
                    <a href="${pageContext.request.contextPath}/quenMatKhau">Quên mật khẩu?</a>
                </div>
                <div class="form-group form-check">
                    <input type="checkbox" class="form-check-input" id="hienMatKhau" onclick="hienThiMatKhau()">
                    <label class="form-check-label" for="hienMatKhau">Hiển thị mật khẩu</label>
                </div>
                <button type="submit" class="btn btn-primary btn-login">Đăng nhập</button>
            </form>
            <div class="register-link">
                Chưa có tài khoản?
                <a href="${pageContext.request.contextPath}/dangKy">Đăng ký ngay</a>
            </div>
            <div class="back-home">
                <a href="${pageContext.request.contextPath}/index.jsp" class="text-muted">← Quay lại trang chủ</a>
            </div>
        </div>
    </div>
</div>
<script>
    function hienThiMatKhau(){const input=document.getElementById("matKhau");const checkbox=document.getElementById("hienMatKhau");if(checkbox.checked){input.type="text";}else{input.type="password";}}
</script>
</body>
</html>
