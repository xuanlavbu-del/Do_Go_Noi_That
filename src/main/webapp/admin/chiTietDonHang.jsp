<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="java.util.List" %>
<%@ page import="model.DonHang" %>
<%@ page import="model.ChiTietDonHang" %>

<%
    DonHang donHang =
            (DonHang) request.getAttribute("donHang");

    List<ChiTietDonHang> danhSachChiTiet =
            (List<ChiTietDonHang>)
                    request.getAttribute("danhSachChiTiet");

    Double tongTien =
            (Double) request.getAttribute("tongTienChiTiet");

    Integer tongSoLuong =
            (Integer) request.getAttribute("tongSoLuong");

    if (tongTien == null) {
        tongTien = 0.0;
    }

    if (tongSoLuong == null) {
        tongSoLuong = 0;
    }
%>

<!DOCTYPE html>

<html lang="vi">

<head>

    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>
        Chi tiết đơn hàng
    </title>

    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">


    <style>

        body {
            background: #f5f6fa;
            font-family: Arial, sans-serif;
        }

        .container-fluid {
            padding: 25px;
        }

        .card {
            border: none;
            border-radius: 10px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.06);
            margin-bottom: 20px;
        }

        .card-header {
            background: white;
            border-bottom: 1px solid #eee;
            font-weight: 600;
        }

        .product-image {
            width: 70px;
            height: 70px;
            object-fit: cover;
            border-radius: 8px;
            border: 1px solid #ddd;
            background: #f8f9fa;
        }

        .product-image-empty {
            width: 70px;
            height: 70px;
            border-radius: 8px;
            background: #f1f1f1;
            display: flex;
            align-items: center;
            justify-content: center;
            color: #aaa;
        }

        .product-name {
            font-weight: 600;
        }

        .money {
            font-weight: 600;
            white-space: nowrap;
        }

        .total-money {
            color: #dc3545;
            font-size: 22px;
            font-weight: bold;
        }

        .status {
            display: inline-block;
            padding: 7px 12px;
            border-radius: 20px;
            font-size: 13px;
        }

        .info-label {
            color: #777;
            font-size: 13px;
            margin-bottom: 3px;
        }

        .info-value {
            font-weight: 500;
            color: #333;
        }

        .empty-data {
            text-align: center;
            padding: 50px;
            color: #888;
        }

        .table th {
            background: #f8f9fa;
            white-space: nowrap;
        }

        .table td {
            vertical-align: middle;
        }

        .address-box {
            min-height: 40px;
        }

    </style>

</head>


<body>

<div class="container-fluid">

    <!-- =========================================
         HEADER
         ========================================= -->

    <div class="d-flex
                justify-content-between
                align-items-center
                mb-4">

        <div>

            <h3 class="mb-1">

                <i class="fas fa-file-invoice mr-2"></i>

                Chi tiết đơn hàng

                #<%= donHang != null
                    ? donHang.getMaDon()
                    : "" %>

            </h3>

            <small class="text-muted">
                Thông tin chi tiết đơn hàng và sản phẩm
            </small>

        </div>


        <div>

            <a href="<%= request.getContextPath() %>/quanLyDonHang"
               class="btn btn-secondary">

                <i class="fas fa-arrow-left mr-1"></i>

                Quay lại

            </a>

        </div>

    </div>


    <!-- =========================================
         THÔNG TIN ĐƠN HÀNG
         ========================================= -->

    <% if (donHang != null) { %>

    <div class="card">

        <div class="card-header">

            <i class="fas fa-info-circle mr-2"></i>

            Thông tin đơn hàng

        </div>


        <div class="card-body">

            <div class="row">


                <!-- Mã đơn -->

                <div class="col-md-3 mb-3">

                    <div class="info-label">
                        Mã đơn hàng
                    </div>

                    <div class="info-value">

                        #<%= donHang.getMaDon() %>

                    </div>

                </div>


                <!-- Mã khách -->

                <div class="col-md-3 mb-3">

                    <div class="info-label">
                        Mã khách hàng
                    </div>

                    <div class="info-value">

                        KH<%= donHang.getMaKhachHang() %>

                    </div>

                </div>


                <!-- Ngày đặt -->

                <div class="col-md-3 mb-3">

                    <div class="info-label">
                        Ngày đặt
                    </div>

                    <div class="info-value">

                        <%= donHang.getNgayDat() != null
                                ? donHang.getNgayDat()
                                : "" %>

                    </div>

                </div>


                <!-- Trạng thái -->

                <div class="col-md-3 mb-3">

                    <div class="info-label">
                        Trạng thái
                    </div>

                    <div>

                        <%
                            String trangThai =
                                    donHang.getTrangThai();

                            String statusClass =
                                    "badge badge-warning";

                            if ("Đã xác nhận".equals(trangThai)) {
                                statusClass =
                                        "badge badge-info";
                            }
                            else if ("Đang giao".equals(trangThai)) {
                                statusClass =
                                        "badge badge-primary";
                            }
                            else if ("Hoàn thành".equals(trangThai)) {
                                statusClass =
                                        "badge badge-success";
                            }
                            else if ("Đã hủy".equals(trangThai)) {
                                statusClass =
                                        "badge badge-danger";
                            }
                        %>

                        <span class="<%= statusClass %>">

                            <%= trangThai != null
                                    ? trangThai
                                    : "Chưa xác định" %>

                        </span>

                    </div>

                </div>


                <!-- Địa chỉ -->

                <div class="col-md-8 mb-3">

                    <div class="info-label">
                        Địa chỉ giao hàng
                    </div>

                    <div class="info-value address-box">

                        <i class="fas fa-map-marker-alt
                                  text-danger mr-1"></i>

                        <%= donHang.getDiaChiGiao() != null
                                ? donHang.getDiaChiGiao()
                                : "Chưa có địa chỉ" %>

                    </div>

                </div>


                <!-- Ghi chú -->

                <div class="col-md-4 mb-3">

                    <div class="info-label">
                        Ghi chú
                    </div>

                    <div class="info-value">

                        <%= donHang.getGhiChu() != null
                                && !donHang.getGhiChu().trim().isEmpty()
                                ? donHang.getGhiChu()
                                : "Không có ghi chú" %>

                    </div>

                </div>

            </div>

        </div>

    </div>


    <!-- =========================================
         DANH SÁCH SẢN PHẨM
         ========================================= -->

    <div class="card">

        <div class="card-header">

            <i class="fas fa-box-open mr-2"></i>

            Sản phẩm trong đơn hàng

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
                            Sản phẩm
                        </th>

                        <th>
                            Mã sản phẩm
                        </th>

                        <th class="text-center">
                            Số lượng
                        </th>

                        <th class="text-right">
                            Đơn giá
                        </th>

                        <th class="text-right">
                            Thành tiền
                        </th>

                    </tr>

                    </thead>


                    <tbody>

                    <%
                        if (danhSachChiTiet != null
                                && !danhSachChiTiet.isEmpty()) {

                            int stt = 1;

                            for (ChiTietDonHang ct :
                                    danhSachChiTiet) {

                                String tenSanPham =
                                        ct.getTenSanPham();

                                if (tenSanPham == null ||
                                        tenSanPham.trim().isEmpty()) {

                                    tenSanPham =
                                            "Sản phẩm #" +
                                                    ct.getMaSanPham();
                                }

                                String hinhAnh =
                                        ct.getHinhAnh();
                    %>

                    <tr>

                        <!-- STT -->

                        <td class="text-center">

                            <%= stt++ %>

                        </td>


                        <!-- Sản phẩm + hình -->

                        <td>

                            <div class="d-flex
                                        align-items-center">

                                <%
                                    if (hinhAnh != null &&
                                            !hinhAnh.trim().isEmpty()) {
                                %>

                                <img src="<%= request.getContextPath() %>/images/<%= hinhAnh %>"
                                     class="product-image mr-3"
                                     alt="<%= tenSanPham %>"
                                     onerror="this.style.display='none';">

                                <%
                                } else {
                                %>

                                <div class="product-image-empty mr-3">

                                    <i class="fas fa-image"></i>

                                </div>

                                <%
                                    }
                                %>


                                <div>

                                    <div class="product-name">

                                        <%= tenSanPham %>

                                    </div>

                                </div>

                            </div>

                        </td>


                        <!-- Mã sản phẩm -->

                        <td>

                            SP<%= ct.getMaSanPham() %>

                        </td>


                        <!-- Số lượng -->

                        <td class="text-center">

                            <span class="badge badge-light"
                                  style="font-size:14px;">

                                <%= ct.getSoLuong() %>

                            </span>

                        </td>


                        <!-- Đơn giá -->

                        <td class="text-right money">

                            <%= String.format(
                                    "%,.0f",
                                    ct.getDonGia()
                            ) %> đ

                        </td>


                        <!-- Thành tiền -->

                        <td class="text-right money">

                            <%= String.format(
                                    "%,.0f",
                                    ct.getThanhTien()
                            ) %> đ

                        </td>

                    </tr>

                    <%
                        }

                    } else {
                    %>

                    <tr>

                        <td colspan="6"
                            class="empty-data">

                            <i class="fas fa-box-open fa-3x mb-3"></i>

                            <h5>
                                Không có sản phẩm
                            </h5>

                            <p>
                                Đơn hàng chưa có sản phẩm.
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


    <!-- =========================================
         TỔNG TIỀN
         ========================================= -->

    <div class="row">


        <!-- Tổng số lượng -->

        <div class="col-md-4">

            <div class="card">

                <div class="card-body text-center">

                    <div class="text-muted mb-2">

                        Tổng số lượng

                    </div>

                    <h3>

                        <%= tongSoLuong %>

                    </h3>

                    <small class="text-muted">
                        sản phẩm
                    </small>

                </div>

            </div>

        </div>


        <!-- Tổng tiền -->

        <div class="col-md-8">

            <div class="card">

                <div class="card-body">

                    <div class="d-flex
                                justify-content-between
                                align-items-center">

                        <span class="font-weight-bold">

                            Tổng tiền đơn hàng

                        </span>

                        <span class="total-money">

                            <%= String.format(
                                    "%,.0f",
                                    tongTien
                            ) %> đ

                        </span>

                    </div>

                </div>

            </div>

        </div>

    </div>


    <!-- =========================================
         CẬP NHẬT TRẠNG THÁI
         ========================================= -->

    <div class="card">

        <div class="card-header">

            <i class="fas fa-sync-alt mr-2"></i>

            Cập nhật trạng thái đơn hàng

        </div>


        <div class="card-body">

            <form method="post"
                  action="<%= request.getContextPath() %>/capNhatTrangThaiDonHang">

                <input type="hidden"
                       name="maDon"
                       value="<%= donHang.getMaDon() %>">


                <div class="row align-items-end">

                    <div class="col-md-8">

                        <label>
                            Trạng thái mới
                        </label>

                        <select name="trangThai"
                                class="form-control"
                                required>

                            <option value="Chờ xác nhận"
                                    <%= "Chờ xác nhận".equals(
                                            donHang.getTrangThai())
                                            ? "selected"
                                            : "" %>>

                                Chờ xác nhận

                            </option>

                            <option value="Đã xác nhận"
                                    <%= "Đã xác nhận".equals(
                                            donHang.getTrangThai())
                                            ? "selected"
                                            : "" %>>

                                Đã xác nhận

                            </option>

                            <option value="Đang giao"
                                    <%= "Đang giao".equals(
                                            donHang.getTrangThai())
                                            ? "selected"
                                            : "" %>>

                                Đang giao

                            </option>

                            <option value="Hoàn thành"
                                    <%= "Hoàn thành".equals(
                                            donHang.getTrangThai())
                                            ? "selected"
                                            : "" %>>

                                Hoàn thành

                            </option>

                            <option value="Đã hủy"
                                    <%= "Đã hủy".equals(
                                            donHang.getTrangThai())
                                            ? "selected"
                                            : "" %>>

                                Đã hủy

                            </option>

                        </select>

                    </div>


                    <div class="col-md-4">

                        <button type="submit"
                                class="btn btn-primary">

                            <i class="fas fa-save mr-1"></i>

                            Cập nhật trạng thái

                        </button>

                    </div>

                </div>

            </form>

        </div>

    </div>

    <% } %>

</div>


<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js">
</script>

<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js">
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js">
</script>

</body>

</html>

