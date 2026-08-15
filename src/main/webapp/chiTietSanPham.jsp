<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.SanPham"%>

<%
    SanPham sp = (SanPham) request.getAttribute("sanPham");
%>

<!DOCTYPE html>
<html lang="vi">

<head>

    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>
        <%= sp != null ? sp.getTenSanPham() : "Chi tiết sản phẩm" %>
    </title>

    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

    <style>

        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background: #f8f8f8;
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
            text-decoration: none;
            color: #6d4525;
        }

        .header-icon {
            color: #333;
            margin-left: 20px;
            font-size: 20px;
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
            font-size: 11px;
            line-height: 18px;
            text-align: center;
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

        .detail-section {
            padding: 60px 0;
        }

        .breadcrumb {
            background: transparent;
            padding-left: 0;
        }

        .product-detail {
            background: white;
            padding: 30px;
            border-radius: 7px;
            box-shadow: 0 3px 15px rgba(0,0,0,.08);
        }

        .product-detail-image {
            width: 100%;
            height: 500px;
            object-fit: cover;
            border-radius: 6px;
        }

        .product-title {
            font-size: 32px;
            font-weight: bold;
            margin-bottom: 20px;
        }

        .product-price {
            font-size: 28px;
            font-weight: bold;
            color: #d35400;
            margin-bottom: 25px;
        }

        .product-description {
            color: #666;
            line-height: 1.8;
            margin-bottom: 25px;
        }

        .quantity-box {
            display: flex;
            align-items: center;
            margin-bottom: 25px;
        }

        .quantity-box button {
            width: 40px;
            height: 40px;
            border: 1px solid #ddd;
            background: #f5f5f5;
        }

        .quantity-box input {
            width: 60px;
            height: 40px;
            text-align: center;
            border: 1px solid #ddd;
            border-left: none;
            border-right: none;
        }

        .btn-cart {
            background: #8b5e34;
            color: white;
            padding: 13px 30px;
            border: none;
            font-weight: bold;
        }

        .btn-cart:hover {
            background: #6d4525;
            color: white;
        }

        .btn-buy {
            background: #d35400;
            color: white;
            padding: 13px 30px;
            border: none;
            font-weight: bold;
            margin-left: 10px;
        }

        .btn-buy:hover {
            background: #b94700;
            color: white;
        }

        .feature {
            border-top: 1px solid #eee;
            margin-top: 30px;
            padding-top: 25px;
        }

        .feature-item {
            margin-bottom: 12px;
        }

        .feature-item i {
            color: #8b5e34;
            width: 25px;
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
            padding-top: 20px;
            margin-top: 30px;
            text-align: center;
            color: #999;
        }

        @media(max-width: 768px) {

            .product-detail-image {
                height: 350px;
                margin-bottom: 30px;
            }

            .product-title {
                font-size: 25px;
            }

            .btn-buy {
                margin-left: 0;
                margin-top: 10px;
            }

        }

    </style>

</head>

<body>

<header class="header">

    <div class="container header-top">

        <div class="row align-items-center">

            <div class="col-md-4 col-6">

                <a href="${pageContext.request.contextPath}/trangChu"
                   class="logo">

                    <i class="fas fa-couch"></i>
                    NGUYÊN KHÔI

                </a>

            </div>

            <div class="col-md-8 col-6 text-right">

                <%
                    String hoTen = (String) session.getAttribute("hoTen");
                %>

                <%
                    if (hoTen != null && !hoTen.trim().isEmpty()) {
                %>

                <span class="mr-3">

                    <i class="fas fa-user-circle"></i>

                    Xin chào,
                    <strong><%= hoTen %></strong>

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

                <a href="${pageContext.request.contextPath}/gioHang.jsp"
                   class="header-icon position-relative">

                    <i class="fas fa-shopping-cart"></i>

                    <span id="cartCount"
                          class="cart-count">0</span>

                </a>

            </div>

        </div>

    </div>


    <nav class="navbar navbar-expand-md navbar-custom">

        <div class="container">

            <div class="collapse navbar-collapse show">

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
                           href="${pageContext.request.contextPath}/gioHang.jsp">

                            Giỏ hàng

                        </a>

                    </li>

                </ul>

            </div>

        </div>

    </nav>

</header>


<section class="detail-section">

    <div class="container">

        <div class="mb-3">

            <a href="${pageContext.request.contextPath}/trangChu">
                Trang chủ
            </a>

            <span> / </span>

            <a href="${pageContext.request.contextPath}/sanPham">
                Sản phẩm
            </a>

            <span> / Chi tiết</span>

        </div>


        <%
            if (sp == null) {
        %>

        <div class="alert alert-danger">

            Không tìm thấy sản phẩm.

        </div>

        <%
        } else {

            String hinhAnh = sp.getHinhAnh();

            if (hinhAnh == null
                    || hinhAnh.trim().isEmpty()) {

                hinhAnh = "default-product.jpg";
            }
        %>


        <div class="product-detail">

            <div class="row">

                <!-- IMAGE -->

                <div class="col-lg-6">

                    <img src="${pageContext.request.contextPath}/images/sanpham/<%= hinhAnh %>"
                         class="product-detail-image"
                         alt="<%= sp.getTenSanPham() %>"
                         onerror="this.src='${pageContext.request.contextPath}/images/default-product.jpg';">

                </div>


                <!-- INFORMATION -->

                <div class="col-lg-6">

                    <h1 class="product-title">

                        <%= sp.getTenSanPham() %>

                    </h1>


                    <div class="product-price">

                        <%= String.format("%,.0f", sp.getGia()) %> VNĐ

                    </div>


                    <div class="product-description">

                        <h5>Mô tả sản phẩm</h5>

                        <p>

                            <%= sp.getMoTa() != null
                                    ? sp.getMoTa()
                                    : "Sản phẩm nội thất chất lượng cao." %>

                        </p>

                    </div>


                    <!-- QUANTITY -->

                    <div>

                        <strong>Số lượng:</strong>


                        <div class="product-actions">

                            <form method="post"
                                  action="${pageContext.request.contextPath}/gioHang"
                                  class="d-inline">

                                <input type="hidden"
                                       name="maSanPham"
                                       value="<%= sp.getMaSanPham() %>">

                                <input type="hidden"
                                       name="soLuong"
                                       id="soLuongHidden"
                                       value="1">

                                <button type="submit"
                                        class="btn btn-cart">

                                    <i class="fas fa-cart-plus"></i>
                                    Thêm vào giỏ hàng

                                </button>

                            </form>


                            <form method="post"
                                  action="${pageContext.request.contextPath}/gioHang"
                                  class="d-inline ms-2">

                                <input type="hidden"
                                       name="maSanPham"
                                       value="<%= sp.getMaSanPham() %>">

                                <input type="hidden"
                                       name="soLuong"
                                       id="soLuongBuyHidden"
                                       value="1">

                                <input type="hidden"
                                       name="muaNgay"
                                       value="true">

                                <button type="submit"
                                        class="btn btn-buy">

                                    <i class="fas fa-bolt"></i>
                                    Mua ngay

                                </button>

                            </form>

                        </div>


                        <div class="feature-item">

                            <i class="fas fa-truck"></i>

                            Hỗ trợ giao hàng tận nơi

                        </div>

                        <div class="feature-item">

                            <i class="fas fa-shield-alt"></i>

                            Cam kết chất lượng

                        </div>

                        <div class="feature-item">

                            <i class="fas fa-headset"></i>

                            Tư vấn hỗ trợ 24/7

                        </div>

                    </div>

                </div>

            </div>

        </div>

        <%
            }
        %>

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
    function increaseQuantity() {

        const input =
            document.getElementById("quantity");

        if (!input) return;

        let value =
            parseInt(input.value) || 1;

        value++;

        input.value = value;

        syncQuantity(value);
    }


    function decreaseQuantity() {

        const input =
            document.getElementById("quantity");

        if (!input) return;

        let value =
            parseInt(input.value) || 1;

        if (value > 1) {
            value--;
            input.value = value;
            syncQuantity(value);
        }
    }


    function syncQuantity(value) {

        const cartQuantity =
            document.getElementById("soLuongHidden");

        const buyQuantity =
            document.getElementById("soLuongBuyHidden");

        if (cartQuantity) {
            cartQuantity.value = value;
        }

        if (buyQuantity) {
            buyQuantity.value = value;
        }
    }


    document.addEventListener("DOMContentLoaded", function () {

        const input =
            document.getElementById("quantity");

        if (!input) return;

        syncQuantity(
            parseInt(input.value) || 1
        );


        input.addEventListener("change", function () {

            let value =
                parseInt(this.value) || 1;

            if (value < 1) {
                value = 1;
            }

            this.value = value;

            syncQuantity(value);
        });

    });
</script>