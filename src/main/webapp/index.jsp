<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="model.SanPham"%>
<%@ page import="model.DanhMuc"%>
<%@ page import="model.GioHang"%>
<%@ page import="dao.GioHangDAO"%>

<!DOCTYPE html>

<html lang="vi">

<head>

    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>Đồ gỗ Việt</title>

    <!-- Bootstrap 4.6.2 -->
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

    <!-- Font Awesome -->
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

    <style>

        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
            font-family: Arial, Helvetica, sans-serif;
            color: #333;
            background: #fff;
        }


        /* ==================================================
           HEADER
        ================================================== */

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

        .logo i {
            margin-right: 7px;
        }


        /* ==================================================
           SEARCH
        ================================================== */

        .search-box {
            position: relative;
            width: 100%;
        }

        .search-box input {
            width: 100%;
            height: 42px;

            border: 1px solid #ddd;
            border-radius: 25px;

            padding: 0 50px 0 20px;

            outline: none;

            transition: border-color .2s;
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


        /* ==================================================
           SEARCH RESULTS
        ================================================== */

        .search-results {

            position: absolute;

            top: 48px;
            left: 0;

            width: 100%;

            background: white;

            border: 1px solid #ddd;
            border-radius: 8px;

            box-shadow:
                0 5px 15px rgba(0, 0, 0, 0.15);

            z-index: 99999;

            display: none;

            max-height: 400px;

            overflow-y: auto;
        }


        .search-result-item {

            display: flex;

            align-items: center;

            padding: 10px 15px;

            text-decoration: none;

            color: #333;

            border-bottom: 1px solid #eee;

            transition: background .2s;
        }

        .search-result-item:last-child {
            border-bottom: none;
        }

        .search-result-item:hover {

            background: #f7f7f7;

            text-decoration: none;

            color: #333;
        }


        .search-result-item img {

            width: 55px;
            height: 55px;

            object-fit: cover;

            border-radius: 5px;

            margin-right: 12px;

            flex-shrink: 0;
        }


        .search-result-info {
            flex: 1;
            min-width: 0;
        }


        .search-result-name {

            font-size: 15px;

            font-weight: 600;

            margin-bottom: 5px;

            white-space: nowrap;

            overflow: hidden;

            text-overflow: ellipsis;
        }


        .search-result-price {

            color: #c0392b;

            font-size: 14px;

            font-weight: 500;
        }


        .search-no-result {

            padding: 18px;

            text-align: center;

            color: #777;
        }


        .search-loading {

            padding: 18px;

            text-align: center;

            color: #8b5e34;
        }


        /* ==================================================
           HEADER ICON
        ================================================== */

        .header-icon {

            color: #333;

            font-size: 21px;

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

            font-size: 11px;

            text-align: center;

            line-height: 18px;
        }


        /* ==================================================
           NAVBAR
        ================================================== */

        .navbar-custom {

            background: #8b5e34;

            padding: 0;
        }

        .navbar-custom .nav-link {

            color: white !important;

            padding: 14px 22px !important;

            font-weight: 500;
        }

        .navbar-custom .nav-link:hover {

            background: #6d4525;
        }


        /* ==================================================
           BANNER
        ================================================== */

        .banner-section {

            width: 100%;
        }

        .banner-img {

            width: 100%;

            height: 520px;

            object-fit: cover;
        }

        .banner-overlay {

            position: absolute;

            left: 0;
            right: 0;

            top: 0;
            bottom: 0;

            display: flex;

            align-items: center;

            justify-content: center;

            text-align: center;

            color: white;

            background: rgba(0, 0, 0, 0.25);
        }

        .banner-content h1 {

            font-size: 48px;

            font-weight: bold;

            text-shadow: 2px 2px 5px #000;
        }

        .banner-content p {

            font-size: 21px;

            text-shadow: 1px 1px 3px #000;
        }

        .btn-banner {

            background: #8b5e34;

            color: white;

            padding: 12px 30px;

            border-radius: 4px;

            font-weight: bold;

            border: none;
        }

        .btn-banner:hover {

            color: white;

            background: #6d4525;
        }


        /* ==================================================
           SECTION
        ================================================== */

        .section {

            padding: 70px 0;
        }

        .section-title {

            text-align: center;

            margin-bottom: 45px;
        }

        .section-title h2 {

            font-size: 32px;

            font-weight: bold;

            color: #333;
        }

        .section-title h2:after {

            content: "";

            display: block;

            width: 60px;

            height: 3px;

            background: #8b5e34;

            margin: 15px auto;
        }

        .section-title p {

            color: #777;
        }


        /* ==================================================
           GIỚI THIỆU
        ================================================== */

        .about-section {

            background: #fafafa;
        }

        .about-image {

            width: 100%;

            height: 380px;

            object-fit: cover;

            border-radius: 5px;
        }

        .about-content {

            padding: 10px 20px;
        }

        .about-content h3 {

            color: #8b5e34;

            font-weight: bold;

            margin-bottom: 20px;
        }

        .about-content p {

            line-height: 1.8;

            color: #666;

            text-align: justify;
        }

        .about-feature {

            margin-top: 20px;
        }

        .about-feature i {

            color: #8b5e34;

            margin-right: 8px;
        }


        /* ==================================================
           VIDEO
        ================================================== */

        .video-wrapper {

            position: relative;

            width: 100%;

            padding-bottom: 56.25%;

            height: 0;

            overflow: hidden;

            border-radius: 7px;

            box-shadow:
                0 5px 20px rgba(0,0,0,.15);
        }

        .video-wrapper video,
        .video-wrapper iframe {

            position: absolute;

            width: 100%;

            height: 100%;

            left: 0;

            top: 0;

            border: 0;
        }


        /* ==================================================
           LỊCH SỬ
        ================================================== */

        .history-section {

            background: white;
        }

        .timeline {

            position: relative;

            max-width: 900px;

            margin: auto;
        }

        .timeline:before {

            content: "";

            position: absolute;

            left: 50%;

            top: 0;

            bottom: 0;

            width: 3px;

            background: #8b5e34;

            transform: translateX(-50%);
        }

        .timeline-item {

            position: relative;

            width: 50%;

            padding: 15px 40px;
        }

        .timeline-item:nth-child(odd) {

            left: 0;

            text-align: right;
        }

        .timeline-item:nth-child(even) {

            left: 50%;
        }

        .timeline-dot {

            position: absolute;

            top: 25px;

            width: 17px;

            height: 17px;

            background: #8b5e34;

            border-radius: 50%;

            border: 3px solid white;

            box-shadow:
                0 0 0 2px #8b5e34;
        }

        .timeline-item:nth-child(odd) .timeline-dot {

            right: -9px;
        }

        .timeline-item:nth-child(even) .timeline-dot {

            left: -9px;
        }

        .timeline-content {

            background: #fafafa;

            padding: 22px;

            border-radius: 6px;

            box-shadow:
                0 2px 10px rgba(0,0,0,.08);
        }

        .timeline-content h4 {

            color: #8b5e34;

            font-weight: bold;
        }

        .timeline-content p {

            margin-bottom: 0;

            color: #666;

            line-height: 1.7;
        }


        /* ==================================================
           SẢN PHẨM
        ================================================== */

        .product-section {

            background: #f8f8f8;
        }

        .category-title {

            margin-top: 45px;

            margin-bottom: 25px;

            padding-bottom: 12px;

            border-bottom: 2px solid #8b5e34;
        }

        .category-title h3 {

            color: #8b5e34;

            font-size: 24px;

            font-weight: bold;
        }

        .product-card {

            height: 100%;

            background: white;

            border-radius: 6px;

            overflow: hidden;

            transition: all .3s;

            box-shadow:
                0 2px 10px rgba(0,0,0,.06);
        }

        .product-card:hover {

            transform: translateY(-6px);

            box-shadow:
                0 8px 25px rgba(0,0,0,.15);
        }

        .product-img {

            width: 100%;

            height: 240px;

            object-fit: cover;
        }

        .product-info {

            padding: 18px;
        }

        .product-name {

            font-size: 18px;

            font-weight: bold;

            margin-bottom: 10px;
        }

        .product-price {

            color: #d35400;

            font-size: 19px;

            font-weight: bold;

            margin-bottom: 15px;
        }

        .product-description {

            color: #777;

            font-size: 14px;

            height: 42px;

            overflow: hidden;

            margin-bottom: 15px;
        }

        .btn-detail {

            background: #8b5e34;

            color: white;

            border: none;

            padding: 8px 18px;
        }

        .btn-detail:hover {

            background: #6d4525;

            color: white;
        }

        .btn-view-all {

            color: #8b5e34;

            border: 1px solid #8b5e34;

            padding: 9px 18px;

            border-radius: 4px;

            text-decoration: none;

            transition: .3s;
        }

        .btn-view-all:hover {

            background: #8b5e34;

            color: white;

            text-decoration: none;
        }

        .btn-view-all i {

            margin-left: 5px;
        }


        /* ==================================================
           LIÊN HỆ
        ================================================== */

        .contact-section {

            background: #fafafa;
        }

        .contact-box {

            background: white;

            padding: 30px;

            height: 100%;

            border-radius: 6px;

            box-shadow:
                0 3px 15px rgba(0,0,0,.07);
        }

        .contact-item {

            display: flex;

            margin-bottom: 25px;
        }

        .contact-icon {

            width: 45px;

            height: 45px;

            background: #8b5e34;

            color: white;

            border-radius: 50%;

            display: flex;

            align-items: center;

            justify-content: center;

            margin-right: 15px;

            flex-shrink: 0;
        }

        .contact-item h5 {

            margin-bottom: 5px;

            font-weight: bold;
        }

        .contact-item p {

            margin: 0;

            color: #777;
        }

        .map {

            width: 100%;

            height: 350px;

            border: 0;

            border-radius: 6px;
        }


        /* ==================================================
           FOOTER
        ================================================== */

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

            color: #bbb;

            display: block;

            margin-bottom: 10px;

            text-decoration: none;
        }

        .footer-link:hover {

            color: white;

            text-decoration: none;
        }

        .copyright {

            border-top: 1px solid #444;

            margin-top: 30px;

            padding-top: 20px;

            text-align: center;

            color: #999;
        }


        /* ==================================================
           RESPONSIVE
        ================================================== */

        @media(max-width: 768px) {

            .banner-img {

                height: 350px;
            }

            .banner-content h1 {

                font-size: 30px;
            }

            .banner-content p {

                font-size: 16px;
            }

            .timeline:before {

                left: 10px;
            }

            .timeline-item,
            .timeline-item:nth-child(even) {

                width: 100%;

                left: 0;

                text-align: left;

                padding-left: 40px;

                padding-right: 10px;
            }

            .timeline-item:nth-child(odd) .timeline-dot,
            .timeline-item:nth-child(even) .timeline-dot {

                left: 2px;
            }

        }

    </style>

</head>


<body>


<!-- ==================================================
     HEADER
================================================== -->

<header class="header">

    <div class="container header-top">

        <div class="row align-items-center">


            <!-- LOGO -->

            <div class="col-md-3 col-6">

                <a href="${pageContext.request.contextPath}/trangChu"
                   class="logo">

                    <i class="fas fa-couch"></i>

                    ĐỒ GỖ VIỆT

                </a>

            </div>


            <!-- ==================================================
                 SEARCH
            ================================================== -->

            <div class="col-md-6 d-none d-md-block">

                <form id="searchForm"
                      autocomplete="off">

                    <div class="search-box">

                        <input type="text"
                               id="searchInput"
                               name="keyword"
                               placeholder="Tìm kiếm sản phẩm..."
                               autocomplete="off">

                        <button type="submit"
                                id="searchButton">

                            <i class="fas fa-search"></i>

                        </button>


                        <!-- KẾT QUẢ TÌM KIẾM -->

                        <div id="searchResults"
                             class="search-results">

                        </div>

                    </div>

                </form>

            </div>


            <!-- ==================================================
                 USER + CART
            ================================================== -->

            <div class="col-md-3 col-6 text-right">

                <%

                    String hoTen =
                            (String) session.getAttribute("hoTen");

                %>


                <%

                    if (hoTen != null &&
                            !hoTen.trim().isEmpty()) {

                %>


                <!-- ĐÃ ĐĂNG NHẬP -->

                <div class="dropdown d-inline-block">

                    <a href="#"
                       class="header-icon dropdown-toggle"
                       data-toggle="dropdown">

                        <i class="fas fa-user-circle"></i>

                        Xin chào,

                        <strong>
                            <%= hoTen %>
                        </strong>

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


                <!-- CHƯA ĐĂNG NHẬP -->

                <a href="${pageContext.request.contextPath}/dangNhap"
                   class="header-icon">

                    <i class="fas fa-user"></i>

                    Đăng nhập

                </a>


                <%

                    }

                %>


                <%

                    int tongSoLuongGioHang = 0;

                    Object maTaiKhoanObj =
                            session.getAttribute("maTaiKhoan");


                    if (maTaiKhoanObj != null) {

                        try {

                            int maTaiKhoan =
                                    Integer.parseInt(
                                            maTaiKhoanObj.toString()
                                    );


                            GioHangDAO gioHangDAO =
                                    new GioHangDAO();


                            tongSoLuongGioHang =
                                    gioHangDAO.demSoLuong(
                                            maTaiKhoan
                                    );


                        } catch (Exception e) {

                            e.printStackTrace();

                            tongSoLuongGioHang = 0;
                        }


                    } else {


                        List<GioHang> gioHangKhach =
                                (List<GioHang>)
                                        session.getAttribute(
                                                "gioHangKhach"
                                        );


                        if (gioHangKhach != null) {

                            for (GioHang item :
                                    gioHangKhach) {

                                tongSoLuongGioHang +=
                                        item.getSoLuong();
                            }
                        }
                    }

                %>


                <!-- GIỎ HÀNG -->

                <a href="${pageContext.request.contextPath}/gioHang"
                   class="header-icon position-relative">

                    <i class="fas fa-shopping-cart"></i>

                    <span id="cartCount"
                          class="cart-count">

                        <%= tongSoLuongGioHang %>

                    </span>

                </a>

            </div>

        </div>

    </div>


    <!-- ==================================================
         MENU
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

                        <a class="nav-link"
                           href="#gioi-thieu">

                            Giới thiệu

                        </a>

                    </li>


                    <li class="nav-item">

                        <a class="nav-link"
                           href="#lich-su">

                            Lịch sử phát triển

                        </a>

                    </li>


                    <li class="nav-item">

                        <a class="nav-link"
                           href="#san-pham">

                            Sản phẩm

                        </a>

                    </li>


                    <li class="nav-item">

                        <a class="nav-link"
                           href="#lien-he">

                            Liên hệ

                        </a>

                    </li>


                </ul>

            </div>

        </div>

    </nav>

</header>


<!-- ==================================================
     BANNER
================================================== -->

<section class="banner-section">

    <div id="bannerCarousel"
         class="carousel slide"
         data-ride="carousel"
         data-interval="500">


        <ol class="carousel-indicators">

            <li data-target="#bannerCarousel"
                data-slide-to="0"
                class="active">
            </li>

            <li data-target="#bannerCarousel"
                data-slide-to="1">
            </li>

            <li data-target="#bannerCarousel"
                data-slide-to="2">
            </li>

        </ol>


        <div class="carousel-inner">


            <!-- BANNER 1 -->

            <div class="carousel-item active">

                <img src="${pageContext.request.contextPath}/images/ban_ghe_go.jpg"
                     class="banner-img"
                     alt="Nội thất">

                <div class="banner-overlay">

                    <div class="banner-content">

                        <h1>NỘI THẤT NGUYÊN KHÔI</h1>

                        <p>
                            Kiến tạo không gian sống hiện đại
                        </p>

                        <a href="#san-pham"
                           class="btn btn-banner">

                            KHÁM PHÁ NGAY

                        </a>

                    </div>

                </div>

            </div>


            <!-- BANNER 2 -->

            <div class="carousel-item">

                <img src="${pageContext.request.contextPath}/images/ke_tivi.jpg"
                     class="banner-img"
                     alt="Phòng khách">

                <div class="banner-overlay">

                    <div class="banner-content">

                        <h1>PHÒNG KHÁCH HIỆN ĐẠI</h1>

                        <p>
                            Sang trọng – Tinh tế – Tiện nghi
                        </p>

                        <a href="#san-pham"
                           class="btn btn-banner">

                            XEM SẢN PHẨM

                        </a>

                    </div>

                </div>

            </div>


            <!-- BANNER 3 -->

            <div class="carousel-item">

                <img src="${pageContext.request.contextPath}/images/giuong_go.jpg"
                     class="banner-img"
                     alt="Phòng ngủ">

                <div class="banner-overlay">

                    <div class="banner-content">

                        <h1>KHÔNG GIAN PHÒNG NGỦ</h1>

                        <p>
                            Mang đến giấc ngủ trọn vẹn
                        </p>

                        <a href="#san-pham"
                           class="btn btn-banner">

                            XEM SẢN PHẨM

                        </a>

                    </div>

                </div>

            </div>


        </div>


        <a class="carousel-control-prev"
           href="#bannerCarousel"
           role="button"
           data-slide="prev">

            <span class="carousel-control-prev-icon"></span>

        </a>


        <a class="carousel-control-next"
           href="#bannerCarousel"
           role="button"
           data-slide="next">

            <span class="carousel-control-next-icon"></span>

        </a>


    </div>

</section>


<!-- ==================================================
     GIỚI THIỆU
================================================== -->

<section class="section about-section"
         id="gioi-thieu">

    <div class="container">

        <div class="section-title">

            <h2>GIỚI THIỆU CÔNG TY</h2>

            <p>
                Đồng hành cùng khách hàng kiến tạo không gian sống
            </p>

        </div>


        <div class="row align-items-center">


            <div class="col-lg-6 mb-4 mb-lg-0">

                <div class="video-wrapper">

                    <video controls>

                        <source src="${pageContext.request.contextPath}/images/video1.mp4"
                                type="video/mp4">

                        Trình duyệt không hỗ trợ video.

                    </video>

                </div>

            </div>


            <div class="col-lg-6">

                <div class="about-content">

                    <h3>Nội Thất Đồ Gỗ Việt</h3>

                    <p>

                        Nội Thất Đồ Gỗ Việt là đơn vị hoạt động
                        trong lĩnh vực cung cấp các sản phẩm nội thất
                        dành cho gia đình, văn phòng và các công trình
                        nội thất.

                    </p>


                    <p>

                        Với định hướng phát triển bền vững,
                        chúng tôi luôn chú trọng đến chất lượng sản phẩm,
                        thiết kế hiện đại và dịch vụ chăm sóc khách hàng.

                    </p>


                    <div class="about-feature">

                        <p>
                            <i class="fas fa-check-circle"></i>
                            Sản phẩm chất lượng cao
                        </p>

                        <p>
                            <i class="fas fa-check-circle"></i>
                            Mẫu mã đa dạng
                        </p>

                        <p>
                            <i class="fas fa-check-circle"></i>
                            Giá cả cạnh tranh
                        </p>

                        <p>
                            <i class="fas fa-check-circle"></i>
                            Dịch vụ tư vấn chuyên nghiệp
                        </p>

                    </div>

                </div>

            </div>

        </div>

    </div>

</section>


<!-- ==================================================
     LỊCH SỬ
================================================== -->

<section class="section history-section"
         id="lich-su">

    <div class="container">

        <div class="section-title">

            <h2>QUÁ TRÌNH HÌNH THÀNH VÀ PHÁT TRIỂN</h2>

            <p>
                Những dấu mốc quan trọng của Nội Thất Đồ Gỗ Việt
            </p>

        </div>


        <div class="timeline">


            <div class="timeline-item">

                <div class="timeline-dot"></div>

                <div class="timeline-content">

                    <h4>2018</h4>

                    <p>
                        Thành lập Nội Thất Đồ Gỗ Việt,
                        bắt đầu hoạt động trong lĩnh vực nội thất.
                    </p>

                </div>

            </div>


            <div class="timeline-item">

                <div class="timeline-dot"></div>

                <div class="timeline-content">

                    <h4>2020</h4>

                    <p>
                        Mở rộng danh mục sản phẩm,
                        phục vụ khách hàng tại nhiều khu vực.
                    </p>

                </div>

            </div>


            <div class="timeline-item">

                <div class="timeline-dot"></div>

                <div class="timeline-content">

                    <h4>2022</h4>

                    <p>
                        Phát triển hệ thống bán hàng trực tuyến
                        và nâng cao chất lượng dịch vụ khách hàng.
                    </p>

                </div>

            </div>


            <div class="timeline-item">

                <div class="timeline-dot"></div>

                <div class="timeline-content">

                    <h4>2024</h4>

                    <p>
                        Mở rộng sản phẩm nội thất gia đình,
                        văn phòng và các sản phẩm trang trí.
                    </p>

                </div>

            </div>


            <div class="timeline-item">

                <div class="timeline-dot"></div>

                <div class="timeline-content">

                    <h4>2026</h4>

                    <p>
                        Tiếp tục phát triển thương hiệu,
                        hướng tới trở thành địa chỉ nội thất
                        uy tín và chuyên nghiệp.
                    </p>

                </div>

            </div>


        </div>

    </div>

</section>


<!-- ==================================================
     SẢN PHẨM
================================================== -->

<section class="section product-section"
         id="san-pham">

    <div class="container">

        <div class="section-title">

            <h2>SẢN PHẨM NỔI BẬT</h2>

            <p>
                Khám phá những sản phẩm nổi bật của chúng tôi
            </p>

        </div>


        <%

            Map<Integer, List<SanPham>>
                    sanPhamTheoDanhMuc =

                    (Map<Integer, List<SanPham>>)
                            request.getAttribute(
                                    "sanPhamTheoDanhMuc"
                            );


            if (sanPhamTheoDanhMuc != null
                    && !sanPhamTheoDanhMuc.isEmpty()) {


                for (Map.Entry<Integer, List<SanPham>>
                        entry :
                        sanPhamTheoDanhMuc.entrySet()) {


                    Integer maDanhMuc =
                            entry.getKey();


                    List<SanPham>
                            danhSachSanPham =
                            entry.getValue();

        %>


        <!-- DANH MỤC -->

        <div class="category-block">

            <div class="category-title">

                <div class="row align-items-center">

                    <div class="col">

                        <h3>

                            <i class="fas fa-couch"></i>

                            Danh mục <%= maDanhMuc %>

                        </h3>

                    </div>


                    <div class="col-auto">

                        <a href="${pageContext.request.contextPath}/sanPham?maDanhMuc=<%= maDanhMuc %>"
                           class="btn-view-all">

                            Xem tất cả

                            <i class="fas fa-arrow-right"></i>

                        </a>

                    </div>

                </div>

            </div>


            <!-- DANH SÁCH SẢN PHẨM -->

            <div class="row">

                <%

                    if (danhSachSanPham != null
                            && !danhSachSanPham.isEmpty()) {


                        int count = 0;


                        for (SanPham sp :
                                danhSachSanPham) {


                            if (count >= 4) {

                                break;
                            }


                            count++;


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


                        <img src="${pageContext.request.contextPath}/images/<%= hinhAnh %>"
                             alt="<%= sp.getTenSanPham() %>"
                             class="product-img"


                             onerror="this.onerror=null;this.src='${pageContext.request.contextPath}/images/default-product.jpg';">


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
                                        ? sp.getMoTa()
                                        : "Sản phẩm nội thất chất lượng cao."
                                %>

                            </div>


                            <a href="${pageContext.request.contextPath}/chiTietSanPham?id=<%= sp.getMaSanPham() %>"
                               class="btn btn-detail">

                                Xem chi tiết

                            </a>


                        </div>

                    </div>

                </div>


                <%

                        }

                    } else {

                %>


                <div class="col-12">

                    <div class="alert alert-info text-center">

                        Chưa có sản phẩm trong danh mục này.

                    </div>

                </div>


                <%

                    }

                %>


            </div>

        </div>


        <%

                }

            } else {

        %>


        <div class="alert alert-info text-center">

            Hiện chưa có sản phẩm nổi bật.

        </div>


        <%

            }

        %>


    </div>

</section>


<!-- ==================================================
     LIÊN HỆ
================================================== -->

<section class="section contact-section"
         id="lien-he">

    <div class="container">

        <div class="section-title">

            <h2>THÔNG TIN LIÊN HỆ</h2>

            <p>
                Hãy liên hệ với chúng tôi khi bạn cần tư vấn
            </p>

        </div>


        <div class="row">


            <div class="col-lg-5 mb-4">

                <div class="contact-box">


                    <div class="contact-item">

                        <div class="contact-icon">

                            <i class="fas fa-building"></i>

                        </div>

                        <div>

                            <h5>Công ty</h5>

                            <p>
                                Công ty TNHH Nội Thất Đồ Gỗ Việt
                            </p>

                        </div>

                    </div>


                    <div class="contact-item">

                        <div class="contact-icon">

                            <i class="fas fa-map-marker-alt"></i>

                        </div>

                        <div>

                            <h5>Địa chỉ</h5>

                            <p>
                                Thái Nguyên, Việt Nam
                            </p>

                        </div>

                    </div>


                    <div class="contact-item">

                        <div class="contact-icon">

                            <i class="fas fa-phone"></i>

                        </div>

                        <div>

                            <h5>Điện thoại</h5>

                            <p>
                                0123 456 789
                            </p>

                        </div>

                    </div>


                    <div class="contact-item">

                        <div class="contact-icon">

                            <i class="fas fa-envelope"></i>

                        </div>

                        <div>

                            <h5>Email</h5>

                            <p>
                                info@dogoviet.vn
                            </p>

                        </div>

                    </div>


                    <div class="contact-item">

                        <div class="contact-icon">

                            <i class="fas fa-clock"></i>

                        </div>

                        <div>

                            <h5>Thời gian làm việc</h5>

                            <p>
                                Thứ 2 - Chủ nhật: 08:00 - 21:00
                            </p>

                        </div>

                    </div>


                </div>

            </div>


            <div class="col-lg-7">

                <div class="contact-box">

                    <iframe
                            class="map"

                            src="https://www.google.com/maps?q=Thai%20Nguyen%20Vietnam&output=embed"

                            loading="lazy">

                    </iframe>

                </div>

            </div>


        </div>

    </div>

</section>


<!-- ==================================================
     FOOTER
================================================== -->

<footer class="footer">

    <div class="container">

        <div class="row">


            <div class="col-md-4 mb-4">

                <h5>NỘI THẤT ĐỒ GỖ VIỆT</h5>

                <p>

                    Kiến tạo không gian sống hiện đại,
                    tiện nghi và phù hợp với phong cách
                    của từng khách hàng.

                </p>

            </div>


            <div class="col-md-4 mb-4">

                <h5>LIÊN KẾT</h5>

                <a href="${pageContext.request.contextPath}/trangChu"
                   class="footer-link">

                    Trang chủ

                </a>

                <a href="#gioi-thieu"
                   class="footer-link">

                    Giới thiệu

                </a>

                <a href="#lich-su"
                   class="footer-link">

                    Lịch sử phát triển

                </a>

                <a href="#san-pham"
                   class="footer-link">

                    Sản phẩm

                </a>

                <a href="#lien-he"
                   class="footer-link">

                    Liên hệ

                </a>

            </div>


            <div class="col-md-4 mb-4">

                <h5>KẾT NỐI VỚI CHÚNG TÔI</h5>

                <p>

                    <i class="fab fa-facebook"></i>

                    Facebook

                </p>

                <p>

                    <i class="fab fa-youtube"></i>

                    YouTube

                </p>

                <p>

                    <i class="fab fa-instagram"></i>

                    Instagram

                </p>

            </div>


        </div>


        <div class="copyright">

            © 2026 Nội Thất Đồ Gỗ Việt.

            All Rights Reserved.

        </div>

    </div>

</footer>


<!-- ==================================================
     JAVASCRIPT
================================================== -->

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>


<script>
document.addEventListener("DOMContentLoaded", function () {

    const searchInput = document.getElementById("searchInput");
    const searchForm = document.getElementById("searchForm");
    const searchResults = document.getElementById("searchResults");

    const contextPath = "${pageContext.request.contextPath}";

    let searchTimer = null;
    let currentController = null;
    let requestNumber = 0;


    // =====================================================
    // KHI NGƯỜI DÙNG GÕ
    // =====================================================

    searchInput.addEventListener("input", function () {

        const keyword = this.value.trim();

        clearTimeout(searchTimer);

        // Hủy request trước đó
        if (currentController) {
            currentController.abort();
            currentController = null;
        }

        // Không có từ khóa
        if (keyword.length === 0) {

            searchResults.innerHTML = "";
            searchResults.style.display = "none";

            return;
        }


        // Hiển thị trạng thái đang tìm
        searchResults.innerHTML =
            '<div class="search-no-result">Đang tìm kiếm...</div>';

        searchResults.style.display = "block";


        // Chờ 300ms rồi mới gửi request
        searchTimer = setTimeout(function () {

            timKiemSanPham(keyword);

        }, 300);
    });


    // =====================================================
    // KHÔNG CHO FORM RELOAD INDEX.JSP
    // =====================================================

    searchForm.addEventListener("submit", function (event) {

        event.preventDefault();

        const keyword = searchInput.value.trim();

        if (keyword === "") {
            return;
        }

        timKiemSanPham(keyword);
    });


    // =====================================================
    // GỌI SERVLET TÌM KIẾM
    // =====================================================

    function timKiemSanPham(keyword) {

        // Tăng số request
        requestNumber++;

        const thisRequest = requestNumber;


        // Hủy request trước
        if (currentController) {
            currentController.abort();
        }


        currentController = new AbortController();


        const url =
            contextPath +
            "/timKiemSanPham?keyword=" +
            encodeURIComponent(keyword);


        fetch(url, {
            method: "GET",

            headers: {
                "Accept": "application/json"
            },

            cache: "no-store",

            signal: currentController.signal
        })

        .then(function (response) {

            if (!response.ok) {
                throw new Error(
                    "HTTP error: " + response.status
                );
            }

            return response.json();
        })

        .then(function (products) {

            // Nếu đây không phải request mới nhất
            // thì bỏ qua kết quả
            if (thisRequest !== requestNumber) {
                return;
            }


            // Kiểm tra dữ liệu
            if (!Array.isArray(products)) {

                hienThiKhongTimThay();

                return;
            }


            hienThiKetQua(products, keyword);
        })

        .catch(function (error) {

            // Request bị hủy thì bỏ qua
            if (error.name === "AbortError") {
                return;
            }

            console.error(
                "Lỗi tìm kiếm:",
                error
            );

            searchResults.innerHTML =
                '<div class="search-no-result">' +
                'Không thể tìm kiếm sản phẩm.' +
                '</div>';

            searchResults.style.display = "block";
        });
    }


    // =====================================================
    // HIỂN THỊ KẾT QUẢ
    // =====================================================

    function hienThiKetQua(products, keyword) {

        // XÓA KẾT QUẢ CŨ
        searchResults.innerHTML = "";


        if (products.length === 0) {

            hienThiKhongTimThay();

            return;
        }


        let html = "";


        products.forEach(function (sp) {

            // Kiểm tra dữ liệu sản phẩm
            if (!sp || !sp.maSanPham) {
                return;
            }


            const tenSanPham =
                sp.tenSanPham || "";


            const gia =
                Number(sp.gia || 0)
                    .toLocaleString("vi-VN");


            let hinhAnh =
                sp.hinhAnh || "default-product.jpg";


            const imageUrl =
                contextPath +
                "/images/" +
                encodeURIComponent(hinhAnh);


            const detailUrl =
                contextPath +
                "/chiTietSanPham?id=" +
                encodeURIComponent(sp.maSanPham);


            html +=
                '<a href="' +
                detailUrl +
                '" class="search-result-item">' +

                    '<img src="' +
                    imageUrl +
                    '" ' +
                    'alt="' +
                    escapeHtml(tenSanPham) +
                    '" ' +
                    'onerror="this.onerror=null;' +
                    'this.src=\'' +
                    contextPath +
                    '/images/default-product.jpg\'">' +

                    '<div class="search-result-info">' +

                        '<div class="search-result-name">' +
                            escapeHtml(tenSanPham) +
                        '</div>' +

                        '<div class="search-result-price">' +
                            gia +
                            ' VNĐ' +
                        '</div>' +

                    '</div>' +

                '</a>';
        });


        // Không có HTML hợp lệ
        if (html === "") {

            hienThiKhongTimThay();

            return;
        }


        searchResults.innerHTML = html;

        searchResults.style.display = "block";
    }


    // =====================================================
    // KHÔNG TÌM THẤY
    // =====================================================

    function hienThiKhongTimThay() {

        searchResults.innerHTML =
            '<div class="search-no-result">' +
            'Không tìm thấy sản phẩm.' +
            '</div>';

        searchResults.style.display = "block";
    }


    // =====================================================
    // CHỐNG HTML INJECTION
    // =====================================================

    function escapeHtml(text) {

        return String(text)

            .replace(/&/g, "&amp;")

            .replace(/</g, "&lt;")

            .replace(/>/g, "&gt;")

            .replace(/"/g, "&quot;")

            .replace(/'/g, "&#039;");
    }


    // =====================================================
    // CLICK RA NGOÀI Ô TÌM KIẾM
    // =====================================================

    document.addEventListener(
        "click",
        function (event) {

            if (!searchForm.contains(event.target)) {

                searchResults.style.display = "none";
            }
        }
    );


    // =====================================================
    // CLICK LẠI VÀO Ô TÌM KIẾM
    // =====================================================

    searchInput.addEventListener(
        "focus",
        function () {

            if (
                searchResults.innerHTML.trim() !== ""
            ) {

                searchResults.style.display = "block";
            }
        }
    );

});
</script>


</body>

</html>