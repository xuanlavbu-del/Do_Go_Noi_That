<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="vi">

<head>

    <meta charset="UTF-8">

    <title>Dashboard Quản Trị</title>

    <meta name="viewport"
          content="width=device-width, initial-scale=1">

    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">

    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/admin-lte@3.2/dist/css/adminlte.min.css">

    <style>

        .brand-link{

            font-size:22px;

            font-weight:bold;

        }

        .small-box{

            border-radius:10px;

        }

        .content-wrapper{

            background:#f4f6f9;

        }

        .nav-sidebar .nav-link{

            font-size:15px;

        }

    </style>

</head>

<body class="hold-transition sidebar-mini layout-fixed">

<div class="wrapper">

    <nav class="main-header navbar navbar-expand navbar-white navbar-light">

        <ul class="navbar-nav">

            <li class="nav-item">

                <a class="nav-link"
                   data-widget="pushmenu"
                   href="#">

                    <i class="fas fa-bars"></i>

                </a>

            </li>

        </ul>

        <ul class="navbar-nav ml-auto">

            <li class="nav-item dropdown">

                <a class="nav-link"
                   data-toggle="dropdown"
                   href="#">

                    <i class="far fa-user-circle fa-lg"></i>

                </a>

                <div class="dropdown-menu dropdown-menu-right">

                <span class="dropdown-item">

                    Xin chào Admin

                </span>

                    <div class="dropdown-divider"></div>

                    <a href="${pageContext.request.contextPath}/dangXuat"
                       class="dropdown-item">

                        <i class="fas fa-sign-out-alt mr-2"></i>

                        Đăng xuất

                    </a>

                </div>

            </li>

        </ul>

    </nav>

    <aside class="main-sidebar sidebar-dark-primary elevation-4">

        <a href="${pageContext.request.contextPath}/dashboard"
           class="brand-link">

            <i class="fas fa-couch ml-3"></i>

            <span class="brand-text ml-2">

            Đồ Gỗ Nội Thất

        </span>

        </a>

        <div class="sidebar">

            <div class="user-panel mt-3 pb-3 mb-3 d-flex">

                <div class="image">

                    <img src="https://cdn-icons-png.flaticon.com/512/3135/3135715.png"
                         class="img-circle elevation-2">

                </div>

                <div class="info">

                    <a href="#"
                       class="d-block">

                        Administrator

                    </a>

                </div>

            </div>

            <nav class="mt-2">

                <ul class="nav nav-pills nav-sidebar flex-column"

                    data-widget="treeview"

                    role="menu"

                    data-accordion="false">

                    <li class="nav-item">

                        <a href="${pageContext.request.contextPath}/dashboard"

                           class="nav-link active">

                            <i class="nav-icon fas fa-gauge-high"></i>

                            <p>

                                Dashboard

                            </p>

                        </a>

                    </li>

                    <li class="nav-item has-treeview">

                        <a href="#"

                           class="nav-link">

                            <i class="nav-icon fas fa-box"></i>

                            <p>

                                Quản lý sản phẩm

                                <i class="right fas fa-angle-left"></i>

                            </p>

                        </a>

                        <ul class="nav nav-treeview">

                            <li class="nav-item">

                                <a href="${pageContext.request.contextPath}/quanlySanPham"

                                   class="nav-link">

                                    <i class="far fa-circle nav-icon"></i>

                                    <p>Danh sách sản phẩm</p>

                                </a>

                            </li>

                            <li class="nav-item">

                                <a href="${pageContext.request.contextPath}/quanLyDanhMuc"

                                   class="nav-link">

                                    <i class="far fa-circle nav-icon"></i>

                                    <p>Danh mục</p>

                                </a>

                            </li>

                        </ul>

                    </li>
                    <li class="nav-item has-treeview">

                        <a href="#"
                           class="nav-link">

                            <i class="nav-icon fas fa-warehouse"></i>

                            <p>

                                Quản lý kho

                                <i class="right fas fa-angle-left"></i>

                            </p>

                        </a>

                        <ul class="nav nav-treeview">

                            <li class="nav-item">

                                <a href="${pageContext.request.contextPath}/quanLyKho"
                                   class="nav-link">

                                    <i class="far fa-circle nav-icon"></i>

                                    <p>

                                        Tổng quan kho

                                    </p>

                                </a>

                            </li>

                            <li class="nav-item">

                                <a href="${pageContext.request.contextPath}/kiemKe"
                                   class="nav-link">

                                    <i class="far fa-circle nav-icon"></i>

                                    <p>

                                        Kiểm kê kho

                                    </p>

                                </a>

                            </li>

                            <li class="nav-item">

                                <a href="${pageContext.request.contextPath}/chuyenKho"
                                   class="nav-link">

                                    <i class="far fa-circle nav-icon"></i>

                                    <p>

                                        Chuyển kho

                                    </p>

                                </a>

                            </li>

                            <li class="nav-item">

                                <a href="${pageContext.request.contextPath}/lichSuNhap"
                                   class="nav-link">

                                    <i class="far fa-circle nav-icon"></i>

                                    <p>

                                        Lịch sử nhập

                                    </p>

                                </a>

                            </li>

                            <li class="nav-item">

                                <a href="${pageContext.request.contextPath}/lichSuXuat"
                                   class="nav-link">

                                    <i class="far fa-circle nav-icon"></i>

                                    <p>

                                        Lịch sử xuất

                                    </p>

                                </a>

                            </li>

                            <li class="nav-item">

                                <a href="${pageContext.request.contextPath}/baoCaoKho"
                                   class="nav-link">

                                    <i class="far fa-circle nav-icon"></i>

                                    <p>

                                        Báo cáo kho

                                    </p>

                                </a>

                            </li>

                        </ul>

                    </li>

                    <li class="nav-item">

                        <a href="${pageContext.request.contextPath}/khachhang"
                           class="nav-link">

                            <i class="nav-icon fas fa-users"></i>

                            <p>

                                Khách hàng

                            </p>

                        </a>

                    </li>

                    <li class="nav-item">

                        <a href="${pageContext.request.contextPath}/quanLyDonHang"
                           class="nav-link">

                            <i class="nav-icon fas fa-cart-shopping"></i>

                            <p>

                                Đơn hàng

                            </p>

                        </a>

                    </li>

                    <li class="nav-item">

                        <a href="${pageContext.request.contextPath}/quanLyTaiKhoan"
                           class="nav-link">

                            <i class="nav-icon fas fa-user-shield"></i>

                            <p>

                                Tài khoản

                            </p>

                        </a>

                    </li>

                    <li class="nav-item">

                        <a href="${pageContext.request.contextPath}/thongKe"
                           class="nav-link">

                            <i class="nav-icon fas fa-chart-pie"></i>

                            <p>

                                Thống kê

                            </p>

                        </a>

                    </li>

                    <li class="nav-item">

                        <a href="${pageContext.request.contextPath}/caiDat"
                           class="nav-link">

                            <i class="nav-icon fas fa-gears"></i>

                            <p>

                                Cài đặt

                            </p>

                        </a>

                    </li>

                    <li class="nav-item">

                        <a href="${pageContext.request.contextPath}/dangXuat"
                           class="nav-link text-danger">

                            <i class="nav-icon fas fa-right-from-bracket"></i>

                            <p>

                                Đăng xuất

                            </p>

                        </a>

                    </li>

                </ul>

            </nav>

        </div>

    </aside>

    <div class="content-wrapper">

        <section class="content-header">

            <div class="container-fluid">

                <div class="row mb-2">

                    <div class="col-sm-6">

                        <h1>

                            Dashboard Quản Trị

                        </h1>

                    </div>

                    <div class="col-sm-6">

                        <ol class="breadcrumb float-sm-right">

                            <li class="breadcrumb-item">

                                <a href="${pageContext.request.contextPath}/dashboard">

                                    Trang chủ

                                </a>

                            </li>

                            <li class="breadcrumb-item active">

                                Dashboard

                            </li>

                        </ol>

                    </div>

                </div>

            </div>

        </section>

        <section class="content">

            <div class="container-fluid">

                <div class="row">
                    <div class="col-lg-3 col-6">

                        <div class="small-box bg-info">

                            <div class="inner">

                                <h3>${tongSanPham}</h3>

                                <p>Sản phẩm</p>

                            </div>

                            <div class="icon">

                                <i class="fas fa-box"></i>

                            </div>

                            <a href="${pageContext.request.contextPath}/quanlySanPham"
                               class="small-box-footer">

                                Chi tiết

                                <i class="fas fa-arrow-circle-right"></i>

                            </a>

                        </div>

                    </div>

                    <div class="col-lg-3 col-6">

                        <div class="small-box bg-success">

                            <div class="inner">

                                <h3>${tongKhachHang}</h3>

                                <p>Khách hàng</p>

                            </div>

                            <div class="icon">

                                <i class="fas fa-users"></i>

                            </div>

                            <a href="${pageContext.request.contextPath}/khachhang"
                               class="small-box-footer">

                                Chi tiết

                                <i class="fas fa-arrow-circle-right"></i>

                            </a>

                        </div>

                    </div>

                    <div class="col-lg-3 col-6">

                        <div class="small-box bg-warning">

                            <div class="inner">

                                <h3>${tongDonHang}</h3>

                                <p>Đơn hàng</p>

                            </div>

                            <div class="icon">

                                <i class="fas fa-cart-shopping"></i>

                            </div>

                            <a href="${pageContext.request.contextPath}/quanLyDonHang"
                               class="small-box-footer">

                                Chi tiết

                                <i class="fas fa-arrow-circle-right"></i>

                            </a>

                        </div>

                    </div>

                    <div class="col-lg-3 col-6">

                        <div class="small-box bg-danger">

                            <div class="inner">

                                <h3>${tongTaiKhoan}</h3>

                                <p>Tài khoản</p>

                            </div>

                            <div class="icon">

                                <i class="fas fa-user-shield"></i>

                            </div>

                            <a href="${pageContext.request.contextPath}/quanLyTaiKhoan"
                               class="small-box-footer">

                                Chi tiết

                                <i class="fas fa-arrow-circle-right"></i>

                            </a>

                        </div>

                    </div>

                    <div class="col-lg-3 col-6">

                        <div class="small-box bg-primary">

                            <div class="inner">

                                <h3>${tongKho}</h3>

                                <p>Kho</p>

                            </div>

                            <div class="icon">

                                <i class="fas fa-warehouse"></i>

                            </div>

                            <a href="${pageContext.request.contextPath}/quanLyKho"
                               class="small-box-footer">

                                Chi tiết

                                <i class="fas fa-arrow-circle-right"></i>

                            </a>

                        </div>

                    </div>

                    <div class="col-lg-3 col-6">

                        <div class="small-box bg-secondary">

                            <div class="inner">

                                <h3>${tongTonKho}</h3>

                                <p>Tổng tồn kho</p>

                            </div>

                            <div class="icon">

                                <i class="fas fa-cubes"></i>

                            </div>

                            <a href="${pageContext.request.contextPath}/baoCaoKho"
                               class="small-box-footer">

                                Chi tiết

                                <i class="fas fa-arrow-circle-right"></i>

                            </a>

                        </div>

                    </div>

                    <div class="col-lg-3 col-6">

                        <div class="small-box bg-indigo">

                            <div class="inner">

                                <h3>${tongPhieuNhap}</h3>

                                <p>Phiếu nhập</p>

                            </div>

                            <div class="icon">

                                <i class="fas fa-file-import"></i>

                            </div>

                            <a href="${pageContext.request.contextPath}/lichSuNhap"
                               class="small-box-footer">

                                Chi tiết

                                <i class="fas fa-arrow-circle-right"></i>

                            </a>

                        </div>

                    </div>

                    <div class="col-lg-3 col-6">

                        <div class="small-box bg-maroon">

                            <div class="inner">

                                <h3>${tongPhieuXuat}</h3>

                                <p>Phiếu xuất</p>

                            </div>

                            <div class="icon">

                                <i class="fas fa-file-export"></i>

                            </div>

                            <a href="${pageContext.request.contextPath}/lichSuXuat"
                               class="small-box-footer">

                                Chi tiết

                                <i class="fas fa-arrow-circle-right"></i>

                            </a>

                        </div>

                    </div>

                    <div class="col-lg-3 col-6">

                        <div class="small-box bg-teal">

                            <div class="inner">

                                <h3>${tongKiemKe}</h3>

                                <p>Phiếu kiểm kê</p>

                            </div>

                            <div class="icon">

                                <i class="fas fa-clipboard-check"></i>

                            </div>

                            <a href="${pageContext.request.contextPath}/kiemKe"
                               class="small-box-footer">

                                Chi tiết

                                <i class="fas fa-arrow-circle-right"></i>

                            </a>

                        </div>

                    </div>

                    <div class="col-lg-3 col-6">

                        <div class="small-box bg-orange">

                            <div class="inner">

                                <h3>${tongChuyenKho}</h3>

                                <p>Chuyển kho</p>

                            </div>

                            <div class="icon">

                                <i class="fas fa-right-left"></i>

                            </div>

                            <a href="${pageContext.request.contextPath}/chuyenKho"
                               class="small-box-footer">

                                Chi tiết

                                <i class="fas fa-arrow-circle-right"></i>

                            </a>

                        </div>

                    </div>

                </div>

                <div class="row">
                    <div class="col-lg-8">

                        <div class="card">

                            <div class="card-header bg-primary">

                                <h3 class="card-title text-white">

                                    <i class="fas fa-chart-bar mr-2"></i>

                                    Thống kê hệ thống

                                </h3>

                            </div>

                            <div class="card-body p-0">

                                <table class="table table-bordered table-hover mb-0">

                                    <thead class="thead-dark">

                                    <tr>

                                        <th>Danh mục</th>

                                        <th class="text-center">Số lượng</th>

                                    </tr>

                                    </thead>

                                    <tbody>

                                    <tr>

                                        <td>Tổng sản phẩm</td>

                                        <td class="text-center">

                                            ${tongSanPham}

                                        </td>

                                    </tr>

                                    <tr>

                                        <td>Tổng khách hàng</td>

                                        <td class="text-center">

                                            ${tongKhachHang}

                                        </td>

                                    </tr>

                                    <tr>

                                        <td>Tổng đơn hàng</td>

                                        <td class="text-center">

                                            ${tongDonHang}

                                        </td>

                                    </tr>

                                    <tr>

                                        <td>Tổng kho</td>

                                        <td class="text-center">

                                            ${tongKho}

                                        </td>

                                    </tr>

                                    <tr>

                                        <td>Tổng tồn kho</td>

                                        <td class="text-center">

                                            ${tongTonKho}

                                        </td>

                                    </tr>

                                    <tr>

                                        <td>Phiếu nhập</td>

                                        <td class="text-center">

                                            ${tongPhieuNhap}

                                        </td>

                                    </tr>

                                    <tr>

                                        <td>Phiếu xuất</td>

                                        <td class="text-center">

                                            ${tongPhieuXuat}

                                        </td>

                                    </tr>

                                    <tr>

                                        <td>Phiếu kiểm kê</td>

                                        <td class="text-center">

                                            ${tongKiemKe}

                                        </td>

                                    </tr>

                                    <tr>

                                        <td>Phiếu chuyển kho</td>

                                        <td class="text-center">

                                            ${tongChuyenKho}

                                        </td>

                                    </tr>

                                    </tbody>

                                </table>

                            </div>

                        </div>

                    </div>

                    <div class="col-lg-4">

                        <div class="card">

                            <div class="card-header bg-danger">

                                <h3 class="card-title text-white">

                                    <i class="fas fa-triangle-exclamation mr-2"></i>

                                    Sản phẩm sắp hết

                                </h3>

                            </div>

                            <div class="card-body">

                                <h1 class="text-center text-danger">

                                    ${sanPhamSapHet}

                                </h1>

                                <p class="text-center">

                                    Sản phẩm tồn dưới 10

                                </p>

                            </div>

                        </div>

                        <div class="card">

                            <div class="card-header bg-success">

                                <h3 class="card-title text-white">

                                    <i class="fas fa-bolt mr-2"></i>

                                    Truy cập nhanh

                                </h3>

                            </div>

                            <div class="card-body">

                                <a href="${pageContext.request.contextPath}/quanlySanPham"

                                   class="btn btn-primary btn-block mb-2">

                                    <i class="fas fa-box mr-2"></i>

                                    Quản lý sản phẩm

                                </a>

                                <a href="${pageContext.request.contextPath}/quanLyKho"

                                   class="btn btn-info btn-block mb-2">

                                    <i class="fas fa-warehouse mr-2"></i>

                                    Quản lý kho

                                </a>

                                <a href="${pageContext.request.contextPath}/kiemKe"

                                   class="btn btn-warning btn-block mb-2">

                                    <i class="fas fa-clipboard-check mr-2"></i>

                                    Kiểm kê

                                </a>

                                <a href="${pageContext.request.contextPath}/chuyenKho"

                                   class="btn btn-secondary btn-block mb-2">

                                    <i class="fas fa-right-left mr-2"></i>

                                    Chuyển kho

                                </a>

                                <a href="${pageContext.request.contextPath}/bao-cao-kho"

                                   class="btn btn-danger btn-block">

                                    <i class="fas fa-chart-column mr-2"></i>

                                    Báo cáo kho

                                </a>

                            </div>

                        </div>

                    </div>

                </div>

            </div>

        </section>

    </div>
                <footer class="main-footer">

                    <strong>

                        Copyright &copy; 2026

                        Đồ Gỗ Nội Thất.

                    </strong>

                    All rights reserved.

                    <div class="float-right d-none d-sm-inline-block">

                        Version 1.0

                    </div>

                </footer>

            </div>

            <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>

            <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

            <script src="https://cdn.jsdelivr.net/npm/admin-lte@3.2/dist/js/adminlte.min.js"></script>

</body>

</html>