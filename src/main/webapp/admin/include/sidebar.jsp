<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="bg-dark text-white vh-100">

    <h5 class="text-center pt-3">

        MENU

    </h5>

    <hr class="bg-light">

    <a class="d-block text-white p-3"

       href="<%=request.getContextPath()%>/dashboard">

        🏠 Dashboard

    </a>

    <a class="d-block text-white p-3"

       href="<%=request.getContextPath()%>/quanLyKho">

        📦 Quản lý kho

    </a>

    <a class="d-block text-white p-3"

       href="<%=request.getContextPath()%>/quanlySanPham">

        📦 Quản lý sản phẩm

    </a>

    <a class="d-block text-white p-3"

       href="<%=request.getContextPath()%>/khachhang">

        👤 Quản lý khách hàng

    </a>

    <a class="d-block text-white p-3"

       href="<%=request.getContextPath()%>/quanLyDanhMuc">

        📂 Danh mục

    </a>

    <a class="d-block text-white p-3"

       href="#">

        🛒 Đơn hàng

    </a>

    <a class="d-block text-white p-3"

       href="#">

        👥 Tài khoản

    </a>

    <hr class="bg-light">

    <a class="d-block text-danger p-3"

       href="<%=request.getContextPath()%>/dangXuat">

        🚪 Đăng xuất

    </a>

</div>