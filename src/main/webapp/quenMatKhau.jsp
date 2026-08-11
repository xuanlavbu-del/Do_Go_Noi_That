<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>

<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quên mật khẩu</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <style>
        body{background:#f5f5f5;min-height:100vh;}
        .card{border:none;border-radius:10px;}
        .card-header{border-radius:10px 10px 0 0!important;}
        .logo{font-size:28px;font-weight:bold;color:#8B4512;}
        .otp-input{text-align:center;font-size:24px;letter-spacing:8px;font-weight:bold;}
        .password-box{position:relative;}
        .show-password{position:absolute;right:10px;top:38px;cursor:pointer;color:#6c757d;font-size:13px;}
        .step-title{font-weight:600;color:#343a40;}
    </style>
</head>
<body>
<div class="container">
    <div class="row justify-content-center mt-5 mb-5">
        <div class="col-md-6 col-lg-5">
            <div class="text-center mb-4">
                <div class="logo">ĐỒ GỖ NỘI THẤT</div>
                <h4 class="mt-3">Quên mật khẩu</h4>
                <p class="text-muted">Khôi phục tài khoản của bạn</p>
            </div>
            <div class="card shadow">
                <div class="card-header bg-primary text-white text-center">
                    <h5 class="mb-0">Khôi phục mật khẩu</h5>
                </div>
                <div class="card-body">
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
                    <% if (request.getParameter("thongBao") != null && "datMatKhauThanhCong".equals(request.getParameter("thongBao"))) { %>
                    <div class="alert alert-success">
                        Đặt mật khẩu mới thành công! Bạn có thể đăng nhập bằng mật khẩu mới.
                    </div>
                    <% } %>
                    <div class="step-title mb-3">Bước 1: Nhập email</div>
                    <form action="${pageContext.request.contextPath}/quenMatKhau" method="post">
                        <div class="form-group">
                            <label for="email">Email</label>
                            <input type="email" id="email" name="email" class="form-control" placeholder="Nhập email đã đăng ký" value="<%= session.getAttribute("resetEmail") != null ? session.getAttribute("resetEmail") : (request.getAttribute("email") != null ? request.getAttribute("email") : "") %>" required>
                        </div>
                        <button type="submit" class="btn btn-primary btn-block">Gửi mã OTP</button>
                    </form>
                    <% if (session.getAttribute("otp") != null && !Boolean.TRUE.equals(session.getAttribute("otpDaXacNhan"))) { %>
                    <hr>
                    <div class="step-title mb-3">Bước 2: Xác nhận OTP</div>
                    <div class="alert alert-info text-center">
                        Mã OTP đã được tạo cho:
                        <br>
                        <strong><%= session.getAttribute("resetEmail") %></strong>
                    </div>
                    <div class="alert alert-warning text-center">
                        <span>Mã OTP kiểm tra:</span>
                        <br>
                        <strong style="font-size:28px;letter-spacing:5px;"><%= session.getAttribute("otp") %></strong>
                        <br>
                        <small>Chỉ hiển thị OTP trong quá trình phát triển.</small>
                    </div>
                    <form action="${pageContext.request.contextPath}/xacNhanOTP" method="post">
                        <div class="form-group">
                            <label for="otp">Nhập mã OTP</label>
                            <input type="text" id="otp" name="otp" class="form-control otp-input" placeholder="000000" maxlength="6" pattern="[0-9]{6}" inputmode="numeric" required>
                            <small class="form-text text-muted text-center">OTP có hiệu lực trong 5 phút.</small>
                        </div>
                        <button type="submit" class="btn btn-success btn-block">Xác nhận OTP</button>
                    </form>
                    <% } %>
                    <% if (Boolean.TRUE.equals(session.getAttribute("otpDaXacNhan"))) { %>
                    <hr>
                    <div class="step-title mb-3">Bước 3: Đặt mật khẩu mới</div>
                    <div class="alert alert-success">
                        OTP đã được xác nhận. Vui lòng nhập mật khẩu mới.
                    </div>
                    <form action="${pageContext.request.contextPath}/datMatKhauMoi" method="post">
                        <div class="form-group password-box">
                            <label for="matKhauMoi">Mật khẩu mới</label>
                            <input type="password" id="matKhauMoi" name="matKhauMoi" class="form-control" placeholder="Nhập mật khẩu mới" minlength="6" required>
                            <span class="show-password" onclick="hienThiMatKhau('matKhauMoi','iconMoi')"><span id="iconMoi">Hiện</span></span>
                        </div>
                        <div class="form-group password-box">
                            <label for="xacNhanMatKhau">Xác nhận mật khẩu mới</label>
                            <input type="password" id="xacNhanMatKhau" name="xacNhanMatKhau" class="form-control" placeholder="Nhập lại mật khẩu mới" minlength="6" required>
                            <span class="show-password" onclick="hienThiMatKhau('xacNhanMatKhau','iconXacNhan')"><span id="iconXacNhan">Hiện</span></span>
                        </div>
                        <button type="submit" class="btn btn-success btn-block">Đặt mật khẩu mới</button>
                    </form>
                    <% } %>
                    <div class="text-center mt-3">
                        <a href="${pageContext.request.contextPath}/dangNhap">Quay lại đăng nhập</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    function hienThiMatKhau(id,iconId){var input=document.getElementById(id);var icon=document.getElementById(iconId);if(input.type==="password"){input.type="text";icon.innerHTML="Ẩn";}else{input.type="password";icon.innerHTML="Hiện";}}
    var otp=document.getElementById("otp");
    if(otp){otp.addEventListener("input",function(){this.value=this.value.replace(/[^0-9]/g,"").slice(0,6);});}
</script>
</body>
</html>
