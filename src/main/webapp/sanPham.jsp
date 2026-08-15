<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.SanPham"%>
<%@ page import="model.DanhMuc"%>

<!DOCTYPE html>
<html lang="vi">

<head>

    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>Sản phẩm - Nội Thất Nguyên Khôi</title>

    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">


    <style>

        * {
            box-sizing: border-box;
        }

        html {
            scroll-behavior: smooth;
        }

        body {
            margin: 0;
            font-family: Arial, Helvetica, sans-serif;

            background: #f8f8f8;

            color: #333;
        }

        a {
            text-decoration: none;
        }

        a:hover {
            text-decoration: none;
        }


        /* =====================================================
           HEADER
        ===================================================== */

        .header {
            background: #fff;

            border-bottom: 1px solid #ddd;

            position: sticky;
            top: 0;

            z-index: 1000;
        }

        .header-top {
            padding: 15px 0;
        }


        /* LOGO */

        .logo {
            color: #8b5e34;

            font-size: 27px;
            font-weight: bold;

            white-space: nowrap;
        }

        .logo:hover {
            color: #6d4525;
        }

        .logo i {
            margin-right: 6px;
        }


        /* SEARCH */

        .search-box {
            position: relative;
        }

        .search-box input {
            width: 100%;
            height: 42px;

            border: 1px solid #ddd;

            border-radius: 25px;

            padding: 0 52px 0 20px;

            outline: none;
        }

        .search-box input:focus {
            border-color: #8b5e34;
        }

        .search-box button {
            position: absolute;

            right: 5px;
            top: 4px;

            width: 34px;
            height: 34px;

            border: none;

            border-radius: 50%;

            background: #8b5e34;

            color: white;

            cursor: pointer;
        }

        .search-box button:hover {
            background: #6d4525;
        }


        /* USER */

        .header-icon {
            color: #333;

            font-size: 18px;

            margin-left: 18px;
        }

        .header-icon:hover {
            color: #8b5e34;
        }


        /* CART */

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

            text-align: center;

            line-height: 18px;
        }


        /* =====================================================
           NAVBAR
        ===================================================== */

        .navbar-custom {
            background: #8b5e34;

            padding: 0;
        }

        .navbar-custom .nav-link {
            color: white !important;

            padding: 14px 22px !important;

            font-weight: 500;
        }

        .navbar-custom .nav-link:hover,
        .navbar-custom .nav-link.active {
            background: #6d4525;
        }

        .navbar-custom .nav-link i {
            margin-right: 5px;
        }


        /* =====================================================
           PAGE HEADER
        ===================================================== */

        .page-header {
            background: white;

            padding: 50px 0 45px;

            text-align: center;

            border-bottom: 1px solid #eee;
        }

        .page-header h1 {
            margin: 0;

            color: #8b5e34;

            font-size: 34px;

            font-weight: bold;
        }

        .page-header h1:after {
            content: "";

            display: block;

            width: 60px;
            height: 3px;

            background: #8b5e34;

            margin: 15px auto;
        }

        .page-header p {
            margin: 0;

            color: #777;
        }


        /* =====================================================
           BREADCRUMB
        ===================================================== */

        .breadcrumb {
            background: transparent;

            justify-content: center;

            margin: 12px 0 0;

            padding: 0;
        }

        .breadcrumb-item a {
            color: #8b5e34;
        }

        .breadcrumb-item.active {
            color: #777;
        }


        /* =====================================================
           PRODUCT AREA
        ===================================================== */

        .product-section {
            padding: 50px 0 70px;
        }


        /* =====================================================
           CATEGORY
        ===================================================== */

        .category-box {
            background: white;

            border-radius: 6px;

            padding: 22px;

            margin-bottom: 35px;

            box-shadow: 0 2px 10px rgba(0,0,0,.06);
        }

        .category-title {
            color: #8b5e34;

            font-size: 20px;

            font-weight: bold;

            margin-bottom: 18px;
        }

        .category-title i {
            margin-right: 7px;
        }

        .category-list {
            display: flex;

            flex-wrap: wrap;

            gap: 10px;
        }

        .category-item {
            display: inline-block;

            padding: 9px 18px;

            border: 1px solid #8b5e34;

            border-radius: 4px;

            color: #8b5e34;

            font-size: 14px;

            transition: .2s;
        }

        .category-item:hover,
        .category-item.active {
            background: #8b5e34;

            color: white;
        }


        /* =====================================================
           SECTION TITLE
        ===================================================== */

        .section-title {
            display: flex;

            align-items: center;

            justify-content: space-between;

            margin-bottom: 30px;

            padding-bottom: 15px;

            border-bottom: 2px solid #8b5e34;
        }

        .section-title h2 {
            margin: 0;

            color: #8b5e34;

            font-size: 27px;

            font-weight: bold;
        }

        .section-title h2 i {
            margin-right: 8px;
        }

        .product-count {
            color: #777;

            margin-top: 6px;
        }

        .product-count strong {
            color: #8b5e34;
        }


        /* =====================================================
           PRODUCT CARD
        ===================================================== */

        .product-card {
            height: 100%;

            background: white;

            border-radius: 6px;

            overflow: hidden;

            box-shadow: 0 2px 10px rgba(0,0,0,.06);

            transition: all .3s ease;
        }

        .product-card:hover {
            transform: translateY(-6px);

            box-shadow: 0 8px 25px rgba(0,0,0,.15);
        }

        .product-image {
            width: 100%;

            height: 240px;

            object-fit: cover;

            display: block;

            background: #f2f2f2;

            transition: transform .4s ease;
        }

        .product-card:hover .product-image {
            transform: scale(1.04);
        }

        .product-image-box {
            height: 240px;

            overflow: hidden;

            background: #f2f2f2;
        }

        .product-info {
            padding: 18px;
        }

        .product-name {
            height: 44px;

            overflow: hidden;

            margin-bottom: 10px;

            color: #333;

            font-size: 18px;

            font-weight: bold;

            line-height: 1.35;
        }

        .product-price {
            margin-bottom: 12px;

            color: #d35400;

            font-size: 19px;

            font-weight: bold;
        }

        .product-description {
            height: 43px;

            overflow: hidden;

            margin-bottom: 17px;

            color: #777;

            font-size: 14px;

            line-height: 1.5;
        }

        .btn-detail {
            display: inline-block;

            padding: 9px 18px;

            background: #8b5e34;

            color: white;

            border-radius: 4px;

            font-size: 14px;
        }

        .btn-detail:hover {
            background: #6d4525;

            color: white;
        }


        /* =====================================================
           EMPTY
        ===================================================== */

        .empty-box {
            background: white;

            padding: 70px 20px;

            border-radius: 6px;

            text-align: center;

            box-shadow: 0 2px 10px rgba(0,0,0,.06);
        }

        .empty-box i {
            color: #8b5e34;

            font-size: 45px;

            margin-bottom: 18px;
        }

        .empty-box h4 {
            color: #555;

            margin-bottom: 10px;
        }

        .empty-box p {
            color: #888;
        }


        /* =====================================================
           FOOTER
        ===================================================== */

        .footer {
            background: #292929;

            color: #ddd;

            padding: 50px 0 20px;
        }

        .footer h5 {
            color: white;

            margin-bottom: 20px;

            font-weight: bold;
        }

        .footer p {
            color: #bbb;

            line-height: 1.7;
        }

        .footer-link {
            display: block;

            color: #bbb;

            margin-bottom: 10px;
        }

        .footer-link:hover {
            color: white;
        }

        .footer-link i {
            width: 20px;
        }

        .copyright {
            border-top: 1px solid #444;

            margin-top: 30px;

            padding-top: 20px;

            text-align: center;

            color: #999;
        }


        /* =====================================================
           RESPONSIVE
        ===================================================== */

        @media(max-width: 768px) {

            .logo {
                font-size: 22px;
            }

            .header-icon {
                font-size: 16px;

                margin-left: 8px;
            }

            .page-header h1 {
                font-size: 28px;
            }

            .section-title {
                display: block;
            }

            .product-image-box,
            .product-image {
                height: 220px;
            }

        }

    </style>

</head>


<body>


<!-- =====================================================
     HEADER
===================================================== -->

<header class="header">


    <div class="container header-top">


        <div class="row align-items-center">


            <!-- LOGO -->

            <div class="col-md-3 col-6">

                <a href="${pageContext.request.contextPath}/trangChu"
                   class="logo">

                    <i class="fas fa-couch"></i>

                    NGUYÊN KHÔI

                </a>

            </div>


            <!-- SEARCH -->

            <div class="col-md-6 d-none d-md-block">

                <form action="${pageContext.request.contextPath}/timKiemSanPham"
                      method="get">

                    <div class="search-box">

                        <input type="text"
                               name="keyword"
                               placeholder="Tìm kiếm sản phẩm...">

                        <button type="submit">

                            <i class="fas fa-search"></i>

                        </button>

                    </div>

                </form>

            </div>


            <!-- ACCOUNT -->

            <div class="col-md-3 col-6 text-right">


                <%
                    String hoTen =
                            (String) session.getAttribute("hoTen");
                %>


                <%
                    if (hoTen != null
                            && !hoTen.trim().isEmpty()) {
                %>


                <div class="dropdown d-inline-block">

                    <a href="#"
                       class="header-icon dropdown-toggle"
                       data-toggle="dropdown">

                        <i class="fas fa-user-circle"></i>

                        <%= hoTen %>

                    </a>


                    <div class="dropdown-menu dropdown-menu-right">


                        <a class="dropdown-item"
                           href="${pageContext.request.contextPath}/thongTinTaiKhoan">

                            <i class="fas fa-user"></i>

                            Thông tin tài khoản

                        </a>


                        <a class="dropdown-item"
                           href="${pageContext.request.contextPath}/donHang">

                            <i class="fas fa-shopping-bag"></i>

                            Đơn hàng của tôi

                        </a>


                        <div class="dropdown-divider"></div>


                        <a class="dropdown-item text-danger"
                           href="${pageContext.request.contextPath}/dangXuat">

                            <i class="fas fa-sign-out-alt"></i>

                            Đăng xuất

                        </a>


                    </div>

                </div>


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


                <!-- CART -->

                <a href="${pageContext.request.contextPath}/gioHang.jsp"
                   class="header-icon position-relative">

                    <i class="fas fa-shopping-cart"></i>

                    <span id="cartCount"
                          class="cart-count">

                        0

                    </span>

                </a>


            </div>

        </div>

    </div>



    <!-- =================================================
         NAVBAR
    ================================================== -->

    <nav class="navbar navbar-expand-md navbar-custom">


        <div class="container">


            <button class="navbar-toggler"
                    type="button"
                    data-toggle="collapse"
                    data-target="#mainMenu">

                <i class="fas fa-bars text-white"></i>

            </button>


            <div class="collapse navbar-collapse"
                 id="mainMenu">


                <ul class="navbar-nav mx-auto">


                    <li class="nav-item">

                        <a class="nav-link"
                           href="${pageContext.request.contextPath}/trangChu">

                            <i class="fas fa-home"></i>

                            Trang chủ

                        </a>

                    </li>


                    <li class="nav-item">

                        <a class="nav-link active"
                           href="${pageContext.request.contextPath}/sanPham">

                            <i class="fas fa-couch"></i>

                            Sản phẩm

                        </a>

                    </li>


                    <li class="nav-item">

                        <a class="nav-link"
                           href="${pageContext.request.contextPath}/trangChu#gioi-thieu">

                            <i class="fas fa-info-circle"></i>

                            Giới thiệu

                        </a>

                    </li>


                    <li class="nav-item">

                        <a class="nav-link"
                           href="${pageContext.request.contextPath}/trangChu#lich-su">

                            <i class="fas fa-history"></i>

                            Lịch sử phát triển

                        </a>

                    </li>


                    <li class="nav-item">

                        <a class="nav-link"
                           href="${pageContext.request.contextPath}/trangChu#lien-he">

                            <i class="fas fa-envelope"></i>

                            Liên hệ

                        </a>

                    </li>


                </ul>

            </div>

        </div>

    </nav>

</header>



<!-- =====================================================
     PAGE HEADER
===================================================== -->

<section class="page-header">


    <div class="container">


        <h1>

            SẢN PHẨM

        </h1>


        <p>

            Khám phá các sản phẩm nội thất chất lượng cao

        </p>


        <ol class="breadcrumb">


            <li class="breadcrumb-item">

                <a href="${pageContext.request.contextPath}/trangChu">

                    Trang chủ

                </a>

            </li>


            <li class="breadcrumb-item active">

                Sản phẩm

            </li>


        </ol>


    </div>

</section>



<!-- =====================================================
     PRODUCT SECTION
===================================================== -->

<section class="product-section">


    <div class="container">


        <%

            List<SanPham> danhSachSanPham =
                    (List<SanPham>)
                            request.getAttribute(
                                    "danhSachSanPham"
                            );


            List<DanhMuc> danhSachDanhMuc =
                    (List<DanhMuc>)
                            request.getAttribute(
                                    "danhSachDanhMuc"
                            );


            Object maDanhMucObj =
                    request.getAttribute(
                            "maDanhMuc"
                    );


            int maDanhMuc = 0;


            if (maDanhMucObj != null) {

                try {

                    maDanhMuc =
                            Integer.parseInt(
                                    maDanhMucObj.toString()
                            );

                } catch (Exception e) {

                    maDanhMuc = 0;

                }

            }


            int soLuong =
                    danhSachSanPham != null
                            ? danhSachSanPham.size()
                            : 0;

        %>



        <!-- =================================================
             CATEGORY
        ================================================== -->

        <div class="category-box">


            <div class="category-title">

                <i class="fas fa-layer-group"></i>

                Danh mục sản phẩm

            </div>


            <div class="category-list">


                <!-- ALL -->

                <a href="${pageContext.request.contextPath}/sanPham"
                   class="category-item
                   <%= maDanhMuc == 0 ? "active" : "" %>">

                    Tất cả sản phẩm

                </a>


                <%

                    if (danhSachDanhMuc != null
                            && !danhSachDanhMuc.isEmpty()) {


                        for (DanhMuc dm :
                                danhSachDanhMuc) {

                %>


                <a href="${pageContext.request.contextPath}/sanPham?maDanhMuc=<%= dm.getMaDanhMuc() %>"
                   class="category-item
                   <%= maDanhMuc == dm.getMaDanhMuc()
                           ? "active"
                           : "" %>">

                    <%= dm.getTenDanhMuc() %>

                </a>


                <%

                        }

                    }

                %>


            </div>

        </div>



        <!-- =================================================
             TITLE
        ================================================== -->

        <div class="section-title">


            <div>


                <h2>

                    <i class="fas fa-couch"></i>


                    <%
                        if (maDanhMuc > 0) {
                    %>

                    Sản phẩm theo danh mục

                    <%
                    } else {
                    %>

                    Tất cả sản phẩm

                    <%
                        }
                    %>


                </h2>


                <div class="product-count">

                    Có

                    <strong>
                        <%= soLuong %>
                    </strong>

                    sản phẩm

                </div>


            </div>


        </div>



        <!-- =================================================
             PRODUCT LIST
        ================================================== -->

        <div class="row">


            <%

                if (danhSachSanPham != null
                        && !danhSachSanPham.isEmpty()) {


                    for (SanPham sp :
                            danhSachSanPham) {


                        String hinhAnh =
                                sp.getHinhAnh();


                        if (hinhAnh == null
                                || hinhAnh.trim().isEmpty()) {

                            hinhAnh =
                                    "default-product.jpg";

                        }

            %>


            <div class="col-lg-3 col-md-4 col-sm-6 mb-4">


                <div class="product-card">


                    <!-- IMAGE -->

                    <div class="product-image-box">


                        <img src="${pageContext.request.contextPath}/images/sanpham/<%= hinhAnh %>"
                             class="product-image"
                             alt="<%= sp.getTenSanPham() %>"
                             loading="lazy"
                             onerror="this.onerror=null;this.src='${pageContext.request.contextPath}/images/default-product.jpg';">


                    </div>


                    <!-- INFO -->

                    <div class="product-info">


                        <div class="product-name">

                            <%= sp.getTenSanPham() %>

                        </div>


                        <div class="product-price">

                            <%= String.format(
                                    "%,.0f",
                                    sp.getGia()
                            ) %>

                            VNĐ

                        </div>


                        <div class="product-description">

                            <%= sp.getMoTa() != null
                                    && !sp.getMoTa().trim().isEmpty()
                                    ? sp.getMoTa()
                                    : "Sản phẩm nội thất chất lượng cao." %>

                        </div>


                        <div class="d-flex justify-content-between align-items-center">

                            <a href="${pageContext.request.contextPath}/chiTietSanPham?id=<%= sp.getMaSanPham() %>"
                               class="btn-detail">

                                <i class="fas fa-eye"></i>

                                Xem chi tiết

                            </a>

                            <form method="post"
                                  action="${pageContext.request.contextPath}/gioHang"
                                  style="display:inline;">

                                <input type="hidden"
                                       name="maSanPham"
                                       value="<%= sp.getMaSanPham() %>">

                                <button type="submit"
                                        class="btn btn-sm btn-success">

                                    <i class="fas fa-cart-plus"></i>

                                    Thêm giỏ

                                </button>

                            </form>

                        </div>


                    </div>

                </div>

            </div>


            <%

                }

            } else {

            %>


            <!-- EMPTY -->

            <div class="col-12">


                <div class="empty-box">


                    <i class="fas fa-couch"></i>


                    <h4>

                        Không có sản phẩm

                    </h4>


                    <p>

                        Hiện tại chưa có sản phẩm
                        trong danh mục này.

                    </p>


                    <a href="${pageContext.request.contextPath}/sanPham"
                       class="category-item">

                        Xem tất cả sản phẩm

                    </a>


                </div>

            </div>


            <%

                }

            %>


        </div>


    </div>

</section>



<!-- =====================================================
     FOOTER
===================================================== -->

<footer class="footer">


    <div class="container">


        <div class="row">


            <div class="col-md-4 mb-4">


                <h5>

                    NỘI THẤT NGUYÊN KHÔI

                </h5>


                <p>

                    Kiến tạo không gian sống hiện đại,
                    tiện nghi và phù hợp với phong cách
                    của từng khách hàng.

                </p>


            </div>


            <div class="col-md-4 mb-4">


                <h5>

                    LIÊN KẾT

                </h5>


                <a href="${pageContext.request.contextPath}/trangChu"
                   class="footer-link">

                    <i class="fas fa-home"></i>

                    Trang chủ

                </a>


                <a href="${pageContext.request.contextPath}/sanPham"
                   class="footer-link">

                    <i class="fas fa-couch"></i>

                    Sản phẩm

                </a>


                <a href="${pageContext.request.contextPath}/trangChu#gioi-thieu"
                   class="footer-link">

                    <i class="fas fa-info-circle"></i>

                    Giới thiệu

                </a>


                <a href="${pageContext.request.contextPath}/trangChu#lien-he"
                   class="footer-link">

                    <i class="fas fa-envelope"></i>

                    Liên hệ

                </a>


            </div>


            <div class="col-md-4 mb-4">


                <h5>

                    LIÊN HỆ

                </h5>


                <p>

                    <i class="fas fa-map-marker-alt"></i>

                    Thái Nguyên, Việt Nam

                </p>


                <p>

                    <i class="fas fa-phone"></i>

                    0123 456 789

                </p>


                <p>

                    <i class="fas fa-envelope"></i>

                    info@nguyenkhoi.vn

                </p>


                <p>

                    <i class="fas fa-clock"></i>

                    08:00 - 21:00

                </p>


            </div>


        </div>


        <div class="copyright">

            © 2026 Nội Thất Nguyên Khôi.
            All Rights Reserved.

        </div>


    </div>

</footer>



<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>


</body>

</html>