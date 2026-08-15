<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.GioHang" %>

<%
    /*
     * GioHangServlet cung cấp dữ liệu cho trang này.
     *
     * Chưa đăng nhập:
     *   session "gioHangKhach"
     *
     * Đã đăng nhập:
     *   GioHangDAO -> database theo maTaiKhoan
     */
    List<GioHang> gioHang =
            (List<GioHang>) request.getAttribute("gioHang");

    if (gioHang == null) {
        gioHang = new ArrayList<>();
    }

    Integer tongSoLuongObj =
            (Integer) request.getAttribute("tongSoLuong");

    int tongSoLuong =
            tongSoLuongObj != null ? tongSoLuongObj : 0;

    Double tongTienObj =
            (Double) request.getAttribute("tongTien");

    double tongTien =
            tongTienObj != null ? tongTienObj : 0;

    String hoTen =
            (String) session.getAttribute("hoTen");
%>

<!DOCTYPE html>
<html lang="vi">

<head>

    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>Giỏ hàng - Nội Thất Nguyên Khôi</title>

    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

    <style>

        body {
            margin: 0;
            background: #f8f8f8;
            font-family: Arial, sans-serif;
            color: #333;
        }

        .header {
            background: white;
            border-bottom: 1px solid #ddd;
        }

        .header-top {
            padding: 15px 0;
        }

        .logo {
            color: #8b5e34;
            font-size: 27px;
            font-weight: bold;
            text-decoration: none;
        }

        .logo:hover {
            color: #6d4525;
            text-decoration: none;
        }

        .header-icon {
            color: #333;
            font-size: 20px;
            margin-left: 20px;
            text-decoration: none;
        }

        .header-icon:hover {
            color: #8b5e34;
            text-decoration: none;
        }

        .cart-count {
            position: absolute;
            top: -10px;
            right: -10px;
            min-width: 18px;
            height: 18px;
            background: #dc3545;
            color: white;
            border-radius: 50%;
            text-align: center;
            line-height: 18px;
            font-size: 11px;
        }

        .navbar-custom {
            background: #8b5e34;
        }

        .navbar-custom .nav-link {
            color: white !important;
            padding: 14px 22px;
        }

        .navbar-custom .nav-link:hover {
            background: #6d4525;
        }

        .cart-section {
            padding: 60px 0;
        }

        .page-title {
            text-align: center;
            margin-bottom: 40px;
        }

        .page-title h1 {
            color: #8b5e34;
            font-weight: bold;
        }

        .cart-box {
            background: white;
            border-radius: 7px;
            padding: 25px;
            box-shadow: 0 3px 15px rgba(0,0,0,.07);
        }

        .cart-item {
            display: flex;
            align-items: center;
            border-bottom: 1px solid #eee;
            padding: 20px 0;
        }

        .cart-item:last-child {
            border-bottom: none;
        }

        .cart-image {
            width: 100px;
            height: 100px;
            object-fit: cover;
            border-radius: 5px;
            margin-right: 20px;
        }

        .cart-name {
            font-weight: bold;
            font-size: 17px;
        }

        .cart-price {
            color: #d35400;
            font-weight: bold;
            margin-top: 7px;
        }

        .quantity-box {
            display: flex;
            align-items: center;
        }

        .quantity-box button {
            width: 32px;
            height: 32px;
            border: 1px solid #ddd;
            background: #f5f5f5;
        }

        .quantity-box span {
            width: 45px;
            text-align: center;
        }

        .quantity-box form {
            display: inline-flex;
            margin: 0;
        }

        .quantity-box form button {
            cursor: pointer;
        }

        .item-total {
            color: #d35400;
            font-weight: bold;
        }

        .btn-delete {
            border: none;
            background: transparent;
            color: #dc3545;
            font-size: 18px;
        }

        .summary {
            background: white;
            padding: 25px;
            border-radius: 7px;
            box-shadow: 0 3px 15px rgba(0,0,0,.07);
        }

        .summary-title {
            font-size: 21px;
            font-weight: bold;
            margin-bottom: 20px;
        }

        .summary-row {
            display: flex;
            justify-content: space-between;
            padding: 10px 0;
        }

        .summary-total {
            border-top: 1px solid #ddd;
            margin-top: 10px;
            padding-top: 20px;
            font-size: 22px;
            font-weight: bold;
            color: #d35400;
        }

        .btn-checkout {
            width: 100%;
            background: #8b5e34;
            color: white;
            padding: 13px;
            border: none;
            font-weight: bold;
            margin-top: 20px;
        }

        .btn-checkout:hover {
            background: #6d4525;
            color: white;
        }

        .btn-shopping {
            color: #8b5e34;
            border: 1px solid #8b5e34;
            padding: 10px 20px;
        }

        .btn-shopping:hover {
            background: #8b5e34;
            color: white;
        }

        .empty-cart {
            text-align: center;
            padding: 70px 20px;
        }

        .empty-cart i {
            font-size: 70px;
            color: #ccc;
            margin-bottom: 20px;
        }

        .empty-cart h3 {
            color: #777;
        }

        .footer {
            background: #292929;
            color: #ddd;
            padding: 45px 0 20px;
            margin-top: 50px;
        }

        .footer h5 {
            color: white;
        }

        .footer p {
            color: #bbb;
        }

        .copyright {
            border-top: 1px solid #444;
            margin-top: 30px;
            padding-top: 20px;
            text-align: center;
            color: #999;
        }

        @media(max-width: 768px) {

            .cart-item {
                flex-wrap: wrap;
            }

            .cart-image {
                width: 80px;
                height: 80px;
            }

            .cart-item-info {
                flex: 1;
            }

            .quantity-box {
                margin-top: 15px;
            }

        }

    </style>

</head>

<body>

<header class="header">

    <div class="container header-top">

        <div class="row align-items-center">

            <div class="col-md-6 col-6">

                <a href="${pageContext.request.contextPath}/trangChu"
                   class="logo">

                    <i class="fas fa-couch"></i>
                    NGUYÊN KHÔI

                </a>

            </div>

            <div class="col-md-6 col-6 text-right">

                <%
                    if (hoTen != null && !hoTen.trim().isEmpty()) {
                %>

                <span>

                    <i class="fas fa-user-circle"></i>

                    <%= hoTen %>

                </span>

                <a href="${pageContext.request.contextPath}/dangXuat"
                   class="header-icon">

                    <i class="fas fa-sign-out-alt"></i>

                </a>

                <%
                } else {
                %>

                <a href="${pageContext.request.contextPath}/dangNhap"
                   class="header-icon">

                    <i class="fas fa-user"></i>
                    Đăng nhập

                </a>

                <%
                    }
                %>

            </div>

        </div>

    </div>


    <nav class="navbar navbar-expand-md navbar-custom">

        <div class="container">

            <div class="navbar-collapse show">

                <ul class="navbar-nav mx-auto">

                    <li class="nav-item">

                        <a class="nav-link"
                           href="${pageContext.request.contextPath}/trangChu">

                            <i class="fas fa-home"></i>
                            Trang chủ

                        </a>

                    </li>

                    <li class="nav-item">

                        <a class="nav-link"
                           href="${pageContext.request.contextPath}/sanPham">

                            Sản phẩm

                        </a>

                    </li>

                    <li class="nav-item">

                        <a class="nav-link"
                           href="${pageContext.request.contextPath}/gioHang">

                            <span style="position: relative; display: inline-block;">
                                <i class="fas fa-shopping-cart"></i>
                                <span id="cartCount" class="cart-count" style="display:<%= tongSoLuong > 0 ? "block" : "none" %>"><%= tongSoLuong %></span>
                            </span>
                            Giỏ hàng

                        </a>

                    </li>

                </ul>

            </div>

        </div>

    </nav>

</header>


<section class="cart-section">

    <div class="container">

        <div class="page-title">

            <h1>
                <i class="fas fa-shopping-cart"></i>
                GIỎ HÀNG
            </h1>

        </div>


        <% if (gioHang.isEmpty()) { %>

        <div class="cart-box">

            <div class="empty-cart">

                <i class="fas fa-shopping-cart"></i>

                <h3>Giỏ hàng đang trống</h3>

                <p>Bạn chưa có sản phẩm nào trong giỏ hàng.</p>

                <a href="${pageContext.request.contextPath}/sanPham"
                   class="btn btn-shopping">
                    Tiếp tục mua hàng
                </a>

            </div>

        </div>

        <% } else { %>

        <div class="row">

            <div class="col-lg-8 mb-4">

                <div class="cart-box">

                    <% for (int i = 0; i < gioHang.size(); i++) {
                        GioHang item = gioHang.get(i);
                        int soLuong = item.getSoLuong();
                        double gia = item.getGia();
                        double thanhTien = item.getThanhTien();
                        String ten = item.getTenSanPham();
                        String hinhAnh = item.getHinhAnh();
                    %>

                    <div class="cart-item">

                        <img
                                src="${pageContext.request.contextPath}/images/sanpham/<%= hinhAnh %>"
                                class="cart-image"
                                alt="<%= ten %>"
                                onerror="this.onerror=null;this.src='${pageContext.request.contextPath}/images/default-product.jpg';"
                        >

                        <div class="cart-item-info flex-grow-1">

                            <div class="cart-name">
                                <%= ten %>
                            </div>

                            <div class="cart-price">
                                Đơn giá:
                                <strong><%= String.format("%,.0f", gia) %></strong> VNĐ
                            </div>

                            <div class="quantity-box mt-2">

                                <form method="get" action="${pageContext.request.contextPath}/gioHang" style="display:inline;">
                                    <input type="hidden" name="hanhDong" value="capNhat">
                                    <input type="hidden" name="maSanPham" value="<%= item.getMaSanPham() %>">
                                    <input type="hidden" name="soLuong" value="<%= soLuong > 1 ? soLuong - 1 : 1 %>">
                                    <button type="submit" aria-label="Giảm số lượng"
                                            <%= soLuong <= 1 ? "disabled" : "" %>>−</button>
                                </form>

                                <span><%= soLuong %></span>

                                <form method="get" action="${pageContext.request.contextPath}/gioHang" style="display:inline;">
                                    <input type="hidden" name="hanhDong" value="capNhat">
                                    <input type="hidden" name="maSanPham" value="<%= item.getMaSanPham() %>">
                                    <input type="hidden" name="soLuong" value="<%= soLuong + 1 %>">
                                    <button type="submit" aria-label="Tăng số lượng">+</button>
                                </form>

                            </div>

                        </div>

                        <div class="text-right ml-3">

                            <div class="item-total">
                                <%= String.format("%,.0f", thanhTien) %> VNĐ
                            </div>

                            <form method="get" action="${pageContext.request.contextPath}/gioHang" class="mt-2">
                                <input type="hidden" name="hanhDong" value="xoa">
                                <input type="hidden" name="maSanPham" value="<%= item.getMaSanPham() %>">
                                <button type="submit"
                                        class="btn-delete"
                                        aria-label="Xóa sản phẩm"
                                        onclick="return confirm('Bạn có chắc muốn xóa sản phẩm này?');">
                                    <i class="fas fa-trash"></i>
                                </button>
                            </form>

                        </div>

                    </div>

                    <% } %>

                </div>

            </div>

            <div class="col-lg-4">

                <div class="summary">

                    <div class="summary-title">Tóm tắt đơn hàng</div>

                    <div class="summary-row">
                        <span>Số lượng</span>
                        <strong><%= tongSoLuong %> sản phẩm</strong>
                    </div>

                    <div class="summary-row">
                        <span>Tạm tính</span>
                        <strong><%= String.format("%,.0f", tongTien) %> VNĐ</strong>
                    </div>

                    <div class="summary-row">
                        <span>Phí vận chuyển</span>
                        <span>Liên hệ</span>
                    </div>

                    <div class="summary-row summary-total">
                        <span>Tổng cộng</span>
                        <span><%= String.format("%,.0f", tongTien) %> VNĐ</span>
                    </div>

                    <a href="${pageContext.request.contextPath}/thanhToan"
                       class="btn btn-checkout">
                        <i class="fas fa-credit-card"></i>
                        Tiến hành đặt hàng
                    </a>

                    <a href="${pageContext.request.contextPath}/sanPham"
                       class="btn btn-shopping btn-block mt-3">
                        Tiếp tục mua hàng
                    </a>

                </div>

            </div>

        </div>

        <% } %>

    </div>

</section>


<footer class="footer">

    <div class="container">

        <div class="row">

            <div class="col-md-6">

                <h5>NỘI THẤT NGUYÊN KHÔI</h5>

                <p>
                    Kiến tạo không gian sống hiện đại,
                    tiện nghi và chất lượng.
                </p>

            </div>

            <div class="col-md-6">

                <h5>LIÊN HỆ</h5>

                <p>
                    <i class="fas fa-phone"></i>
                    0123 456 789
                </p>

                <p>
                    <i class="fas fa-envelope"></i>
                    info@nguyenkhoi.vn
                </p>

            </div>

        </div>

        <div class="copyright">

            © 2026 Nội Thất Nguyên Khôi

        </div>

    </div>

</footer>


<script>

    // Số lượng đã được tính từ session/GioHang ở phía server.
    // Không dùng localStorage nữa để tránh lệch dữ liệu với ThanhToanServlet.
    document.addEventListener("DOMContentLoaded", function () {
        const cartCount = document.getElementById("cartCount");

        if (cartCount) {
            const total = <%= tongSoLuong %>;
            cartCount.innerText = total;
            cartCount.style.display = total > 0 ? "block" : "none";
        }
    });

</script>

</body>

</html>