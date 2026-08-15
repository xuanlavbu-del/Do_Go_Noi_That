
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.DonHang" %>

<%
    List<DonHang> danhSachDonHang =
            (List<DonHang>) request.getAttribute("danhSachDonHang");

    Integer tongDonHang =
            (Integer) request.getAttribute("tongDonHang");

    Integer choXacNhan =
            (Integer) request.getAttribute("choXacNhan");

    Integer daXacNhan =
            (Integer) request.getAttribute("daXacNhan");

    Integer dangGiao =
            (Integer) request.getAttribute("dangGiao");

    Integer hoanThanh =
            (Integer) request.getAttribute("hoanThanh");

    Integer daHuy =
            (Integer) request.getAttribute("daHuy");

    String keyword =
            (String) request.getAttribute("keyword");

    String trangThai =
            (String) request.getAttribute("trangThai");

    if (tongDonHang == null) tongDonHang = 0;
    if (choXacNhan == null) choXacNhan = 0;
    if (daXacNhan == null) daXacNhan = 0;
    if (dangGiao == null) dangGiao = 0;
    if (hoanThanh == null) hoanThanh = 0;
    if (daHuy == null) daHuy = 0;
%>

<!DOCTYPE html>
<html lang="vi">

<head>

    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>Quản lý đơn hàng</title>

    <!-- Bootstrap -->
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

    <!-- Font Awesome -->
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

    <style>

        body {
            background-color: #f5f6fa;
            font-family: Arial, sans-serif;
        }

        .content {
            padding: 25px;
        }

        .page-title {
            font-weight: 600;
            color: #343a40;
        }

        .stat-card {
            border: none;
            border-radius: 10px;
            transition: 0.2s;
            height: 100%;
        }

        .stat-card:hover {
            transform: translateY(-3px);
            box-shadow: 0 5px 15px rgba(0,0,0,0.08);
        }

        .stat-icon {
            width: 50px;
            height: 50px;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 20px;
        }

        .icon-primary {
            background: #e8f0fe;
            color: #4285f4;
        }

        .icon-warning {
            background: #fff4db;
            color: #f39c12;
        }

        .icon-info {
            background: #e1f5fe;
            color: #039be5;
        }

        .icon-success {
            background: #e5f7ed;
            color: #28a745;
        }

        .icon-danger {
            background: #fde8e8;
            color: #dc3545;
        }

        .stat-number {
            font-size: 25px;
            font-weight: bold;
            margin-bottom: 2px;
        }

        .stat-title {
            color: #6c757d;
            font-size: 14px;
        }

        .card {
            border: none;
            border-radius: 10px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.05);
        }

        .card-header {
            background: white;
            border-bottom: 1px solid #eee;
            padding: 18px 20px;
            font-weight: 600;
        }

        .table th {
            background-color: #f8f9fa;
            color: #495057;
            font-size: 14px;
            white-space: nowrap;
        }

        .table td {
            vertical-align: middle;
            font-size: 14px;
        }

        .badge-status {
            padding: 7px 10px;
            border-radius: 20px;
            font-size: 12px;
        }

        .badge-cho {
            background: #fff3cd;
            color: #856404;
        }

        .badge-xacnhan {
            background: #d1ecf1;
            color: #0c5460;
        }

        .badge-giao {
            background: #cce5ff;
            color: #004085;
        }

        .badge-hoanthanh {
            background: #d4edda;
            color: #155724;
        }

        .badge-huy {
            background: #f8d7da;
            color: #721c24;
        }

        .btn-action {
            margin-right: 3px;
        }

        .search-box {
            max-width: 350px;
        }

        .money {
            font-weight: 600;
            color: #dc3545;
            white-space: nowrap;
        }

        .address {
            max-width: 230px;
        }

        .note {
            max-width: 180px;
        }

        .empty-data {
            padding: 50px;
            text-align: center;
            color: #888;
        }

    </style>

</head>

<body>

<div class="container-fluid content">

    <!-- ==========================================
         TIÊU ĐỀ
         ========================================== -->

    <div class="d-flex justify-content-between
                align-items-center mb-4">

        <div>
            <h3 class="page-title mb-1">
                <i class="fas fa-shopping-cart mr-2"></i>
                Quản lý đơn hàng
            </h3>

            <small class="text-muted">
                Quản lý và theo dõi các đơn hàng của khách hàng
            </small>
        </div>

    </div>


    <!-- ==========================================
         THỐNG KÊ
         ========================================== -->

    <div class="row mb-4">

        <!-- Tổng đơn -->
        <div class="col-xl col-md-4 col-sm-6 mb-3">

            <div class="card stat-card">

                <div class="card-body">

                    <div class="d-flex
                                justify-content-between
                                align-items-center">

                        <div>
                            <div class="stat-number">
                                <%= tongDonHang %>
                            </div>

                            <div class="stat-title">
                                Tổng đơn hàng
                            </div>
                        </div>

                        <div class="stat-icon icon-primary">
                            <i class="fas fa-shopping-cart"></i>
                        </div>

                    </div>

                </div>

            </div>

        </div>


        <!-- Chờ xác nhận -->
        <div class="col-xl col-md-4 col-sm-6 mb-3">

            <div class="card stat-card">

                <div class="card-body">

                    <div class="d-flex
                                justify-content-between
                                align-items-center">

                        <div>
                            <div class="stat-number">
                                <%= choXacNhan %>
                            </div>

                            <div class="stat-title">
                                Chờ xác nhận
                            </div>
                        </div>

                        <div class="stat-icon icon-warning">
                            <i class="fas fa-clock"></i>
                        </div>

                    </div>

                </div>

            </div>

        </div>


        <!-- Đã xác nhận -->
        <div class="col-xl col-md-4 col-sm-6 mb-3">

            <div class="card stat-card">

                <div class="card-body">

                    <div class="d-flex
                                justify-content-between
                                align-items-center">

                        <div>
                            <div class="stat-number">
                                <%= daXacNhan %>
                            </div>

                            <div class="stat-title">
                                Đã xác nhận
                            </div>
                        </div>

                        <div class="stat-icon icon-info">
                            <i class="fas fa-check"></i>
                        </div>

                    </div>

                </div>

            </div>

        </div>


        <!-- Đang giao -->
        <div class="col-xl col-md-4 col-sm-6 mb-3">

            <div class="card stat-card">

                <div class="card-body">

                    <div class="d-flex
                                justify-content-between
                                align-items-center">

                        <div>
                            <div class="stat-number">
                                <%= dangGiao %>
                            </div>

                            <div class="stat-title">
                                Đang giao
                            </div>
                        </div>

                        <div class="stat-icon icon-info">
                            <i class="fas fa-truck"></i>
                        </div>

                    </div>

                </div>

            </div>

        </div>


        <!-- Hoàn thành -->
        <div class="col-xl col-md-4 col-sm-6 mb-3">

            <div class="card stat-card">

                <div class="card-body">

                    <div class="d-flex
                                justify-content-between
                                align-items-center">

                        <div>
                            <div class="stat-number">
                                <%= hoanThanh %>
                            </div>

                            <div class="stat-title">
                                Hoàn thành
                            </div>
                        </div>

                        <div class="stat-icon icon-success">
                            <i class="fas fa-check-circle"></i>
                        </div>

                    </div>

                </div>

            </div>

        </div>


        <!-- Đã hủy -->
        <div class="col-xl col-md-4 col-sm-6 mb-3">

            <div class="card stat-card">

                <div class="card-body">

                    <div class="d-flex
                                justify-content-between
                                align-items-center">

                        <div>
                            <div class="stat-number">
                                <%= daHuy %>
                            </div>

                            <div class="stat-title">
                                Đã hủy
                            </div>
                        </div>

                        <div class="stat-icon icon-danger">
                            <i class="fas fa-times-circle"></i>
                        </div>

                    </div>

                </div>

            </div>

        </div>

    </div>


    <!-- ==========================================
         TÌM KIẾM + LỌC
         ========================================== -->

    <div class="card mb-4">

        <div class="card-body">

            <form method="get"
                  action="<%= request.getContextPath() %>/quanLyDonHang">

                <div class="form-row align-items-end">

                    <!-- Tìm kiếm -->

                    <div class="col-md-5 mb-2">

                        <label>
                            Tìm kiếm
                        </label>

                        <div class="input-group search-box">

                            <input type="text"
                                   name="keyword"
                                   class="form-control"
                                   placeholder="Mã đơn, mã khách hàng..."
                                   value="<%= keyword != null ? keyword : "" %>">

                            <div class="input-group-append">

                                <button class="btn btn-primary"
                                        type="submit">

                                    <i class="fas fa-search"></i>
                                    Tìm kiếm

                                </button>

                            </div>

                        </div>

                    </div>


                    <!-- Trạng thái -->

                    <div class="col-md-4 mb-2">

                        <label>
                            Trạng thái
                        </label>

                        <select name="trangThai"
                                class="form-control">

                            <option value="">
                                -- Tất cả trạng thái --
                            </option>

                            <option value="Chờ xác nhận"
                                    <%= "Chờ xác nhận".equals(trangThai)
                                            ? "selected" : "" %>>
                                Chờ xác nhận
                            </option>

                            <option value="Đã xác nhận"
                                    <%= "Đã xác nhận".equals(trangThai)
                                            ? "selected" : "" %>>
                                Đã xác nhận
                            </option>

                            <option value="Đang giao"
                                    <%= "Đang giao".equals(trangThai)
                                            ? "selected" : "" %>>
                                Đang giao
                            </option>

                            <option value="Hoàn thành"
                                    <%= "Hoàn thành".equals(trangThai)
                                            ? "selected" : "" %>>
                                Hoàn thành
                            </option>

                            <option value="Đã hủy"
                                    <%= "Đã hủy".equals(trangThai)
                                            ? "selected" : "" %>>
                                Đã hủy
                            </option>

                        </select>

                    </div>


                    <!-- Nút -->

                    <div class="col-md-3 mb-2">

                        <button type="submit"
                                class="btn btn-primary">

                            <i class="fas fa-filter"></i>
                            Lọc

                        </button>

                        <a href="<%= request.getContextPath() %>/quanLyDonHang"
                           class="btn btn-secondary">

                            <i class="fas fa-sync-alt"></i>
                            Làm mới

                        </a>

                    </div>

                </div>

            </form>

        </div>

    </div>


    <!-- ==========================================
         THÔNG BÁO
         ========================================== -->

    <%
        String success = request.getParameter("success");
        String error = request.getParameter("error");
    %>

    <% if ("capNhat".equals(success)) { %>

    <div class="alert alert-success alert-dismissible fade show">

        <i class="fas fa-check-circle mr-2"></i>
        Cập nhật trạng thái đơn hàng thành công.

        <button type="button"
                class="close"
                data-dismiss="alert">

            &times;

        </button>

    </div>

    <% } %>


    <% if ("xoa".equals(success)) { %>

    <div class="alert alert-success alert-dismissible fade show">

        <i class="fas fa-check-circle mr-2"></i>
        Xóa đơn hàng thành công.

        <button type="button"
                class="close"
                data-dismiss="alert">

            &times;

        </button>

    </div>

    <% } %>


    <% if (error != null) { %>

    <div class="alert alert-danger alert-dismissible fade show">

        <i class="fas fa-exclamation-circle mr-2"></i>
        Có lỗi xảy ra. Vui lòng thử lại.

        <button type="button"
                class="close"
                data-dismiss="alert">

            &times;

        </button>

    </div>

    <% } %>


    <!-- ==========================================
         DANH SÁCH ĐƠN HÀNG
         ========================================== -->

    <div class="card">

        <div class="card-header">

            <div class="d-flex
                        justify-content-between
                        align-items-center">

                <span>
                    <i class="fas fa-list mr-2"></i>
                    Danh sách đơn hàng
                </span>

                <span class="badge badge-primary">
                    <%= danhSachDonHang != null
                            ? danhSachDonHang.size()
                            : 0 %>
                    đơn hàng
                </span>

            </div>

        </div>


        <div class="card-body p-0">

            <div class="table-responsive">

                <table class="table table-hover mb-0">

                    <thead>

                    <tr>

                        <th class="text-center">
                            STT
                        </th>

                        <th>
                            Mã đơn
                        </th>

                        <th>
                            Khách hàng
                        </th>

                        <th>
                            Ngày đặt
                        </th>

                        <th>
                            Tổng tiền
                        </th>

                        <th>
                            Trạng thái
                        </th>

                        <th>
                            Địa chỉ giao
                        </th>

                        <th class="text-center">
                            Thao tác
                        </th>

                    </tr>

                    </thead>


                    <tbody>

                    <%
                        if (danhSachDonHang != null
                                && !danhSachDonHang.isEmpty()) {

                            int stt = 1;

                            for (DonHang dh : danhSachDonHang) {

                                String badgeClass = "badge-cho";

                                if ("Đã xác nhận".equals(dh.getTrangThai())) {
                                    badgeClass = "badge-xacnhan";
                                }
                                else if ("Đang giao".equals(dh.getTrangThai())) {
                                    badgeClass = "badge-giao";
                                }
                                else if ("Hoàn thành".equals(dh.getTrangThai())) {
                                    badgeClass = "badge-hoanthanh";
                                }
                                else if ("Đã hủy".equals(dh.getTrangThai())) {
                                    badgeClass = "badge-huy";
                                }
                    %>

                    <tr>

                        <!-- STT -->

                        <td class="text-center">
                            <%= stt++ %>
                        </td>


                        <!-- Mã đơn -->

                        <td>

                            <strong>
                                #<%= dh.getMaDon() %>
                            </strong>

                        </td>


                        <!-- Khách hàng -->

                        <td>

                            <i class="fas fa-user mr-1 text-muted"></i>

                            KH<%= dh.getMaKhachHang() %>

                        </td>


                        <!-- Ngày đặt -->

                        <td>

                            <%= dh.getNgayDat() != null
                                    ? dh.getNgayDat()
                                    : "" %>

                        </td>


                        <!-- Tổng tiền -->

                        <td class="money">

                            <%= String.format(
                                    "%,.0f",
                                    dh.getTongTien()
                            ) %> đ

                        </td>


                        <!-- Trạng thái -->

                        <td>

                            <span class="badge badge-status
                                         <%= badgeClass %>">

                                <%= dh.getTrangThai() != null
                                        ? dh.getTrangThai()
                                        : "Chưa xác định" %>

                            </span>

                        </td>


                        <!-- Địa chỉ -->

                        <td class="address">

                            <%
                                String diaChi = dh.getDiaChiGiao();

                                if (diaChi == null) {
                                    diaChi = "";
                                }

                                if (diaChi.length() > 35) {
                                    diaChi =
                                            diaChi.substring(0, 35)
                                                    + "...";
                                }
                            %>

                            <%= diaChi %>

                        </td>


                        <!-- Thao tác -->

                        <td class="text-center"
                            style="min-width: 180px;">

                            <!-- Xem -->

                            <a href="<%= request.getContextPath() %>/chiTietDonHang?maDon=<%= dh.getMaDon() %>"
                               class="btn btn-sm btn-info btn-action"
                               title="Xem chi tiết">

                                <i class="fas fa-eye"></i>

                            </a>


                            <!-- Cập nhật trạng thái -->

                            <button type="button"
                                    class="btn btn-sm btn-warning btn-action"
                                    data-toggle="modal"
                                    data-target="#modalTrangThai<%= dh.getMaDon() %>"
                                    title="Cập nhật trạng thái">

                                <i class="fas fa-edit"></i>

                            </button>


                            <!-- Xóa -->

                            <button type="button"
                                    class="btn btn-sm btn-danger btn-action"
                                    onclick="xoaDonHang(<%= dh.getMaDon() %>)"
                                    title="Xóa">

                                <i class="fas fa-trash"></i>

                            </button>

                        </td>

                    </tr>


                    <!-- ==========================================
                         MODAL CẬP NHẬT TRẠNG THÁI
                         ========================================== -->

                    <div class="modal fade"
                         id="modalTrangThai<%= dh.getMaDon() %>"
                         tabindex="-1">

                        <div class="modal-dialog">

                            <div class="modal-content">

                                <div class="modal-header">

                                    <h5 class="modal-title">

                                        <i class="fas fa-edit mr-2"></i>

                                        Cập nhật đơn
                                        #<%= dh.getMaDon() %>

                                    </h5>

                                    <button type="button"
                                            class="close"
                                            data-dismiss="modal">

                                        &times;

                                    </button>

                                </div>


                                <form method="post"
                                      action="<%= request.getContextPath() %>/capNhatTrangThaiDonHang">

                                    <div class="modal-body">

                                        <input type="hidden"
                                               name="maDon"
                                               value="<%= dh.getMaDon() %>">

                                        <div class="form-group">

                                            <label>
                                                Trạng thái đơn hàng
                                            </label>

                                            <select name="trangThai"
                                                    class="form-control"
                                                    required>

                                                <option value="Chờ xác nhận"
                                                        <%= "Chờ xác nhận".equals(dh.getTrangThai())
                                                                ? "selected" : "" %>>
                                                    Chờ xác nhận
                                                </option>

                                                <option value="Đã xác nhận"
                                                        <%= "Đã xác nhận".equals(dh.getTrangThai())
                                                                ? "selected" : "" %>>
                                                    Đã xác nhận
                                                </option>

                                                <option value="Đang giao"
                                                        <%= "Đang giao".equals(dh.getTrangThai())
                                                                ? "selected" : "" %>>
                                                    Đang giao
                                                </option>

                                                <option value="Hoàn thành"
                                                        <%= "Hoàn thành".equals(dh.getTrangThai())
                                                                ? "selected" : "" %>>
                                                    Hoàn thành
                                                </option>

                                                <option value="Đã hủy"
                                                        <%= "Đã hủy".equals(dh.getTrangThai())
                                                                ? "selected" : "" %>>
                                                    Đã hủy
                                                </option>

                                            </select>

                                        </div>

                                    </div>


                                    <div class="modal-footer">

                                        <button type="button"
                                                class="btn btn-secondary"
                                                data-dismiss="modal">

                                            Hủy

                                        </button>

                                        <button type="submit"
                                                class="btn btn-primary">

                                            <i class="fas fa-save mr-1"></i>
                                            Lưu thay đổi

                                        </button>

                                    </div>

                                </form>

                            </div>

                        </div>

                    </div>

                    <%
                        }
                    }
                    else {
                    %>

                    <tr>

                        <td colspan="8"
                            class="empty-data">

                            <i class="fas fa-shopping-cart fa-3x mb-3"></i>

                            <h5>
                                Không có đơn hàng
                            </h5>

                            <p class="mb-0">
                                Chưa tìm thấy đơn hàng phù hợp.
                            </p>

                        </td>

                    </tr>

                    <%
                        }
                    %>

                    </tbody>

                </table>

            </div>

        </div>

    </div>

</div>


<!-- ==========================================
     FORM XÓA ĐƠN HÀNG
     ========================================== -->

<form id="formXoaDonHang"
      method="post"
      action="<%= request.getContextPath() %>/quanLyDonHang">

    <input type="hidden"
           name="action"
           value="xoa">

    <input type="hidden"
           name="maDon"
           id="maDonXoa">

</form>


<!-- ==========================================
     JAVASCRIPT
     ========================================== -->

<script>

    function xoaDonHang(maDon) {

        if (confirm(
            "Bạn có chắc chắn muốn xóa đơn hàng #" +
            maDon +
            " không?"
        )) {

            document.getElementById("maDonXoa").value = maDon;

            document.getElementById("formXoaDonHang").submit();
        }
    }

</script>


<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js">
</script>

<!-- Popper -->
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js">
</script>

<!-- Bootstrap -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js">
</script>

</body>

</html>

