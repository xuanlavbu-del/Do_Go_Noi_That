```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="java.util.List" %>
<%@ page import="model.SanPham" %>

<%
    List<SanPham> danhSach =
            (List<SanPham>) request.getAttribute("danhSachSanPham");

    String tuKhoa =
            (String) request.getAttribute("tuKhoa");

    if (tuKhoa == null) {
        tuKhoa = "";
    }
%>

<!DOCTYPE html>

<html lang="vi">

<head>

    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>Quản lý sản phẩm</title>


    <!-- Bootstrap -->

    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">


    <!-- Font Awesome -->

    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">


    <!-- CSS Admin -->

    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/css/admin.css">

</head>


<body>


<!-- ================= HEADER ================= -->

<jsp:include page="include/header.jsp"/>


<div class="container-fluid">

    <div class="row">


        <!-- ================= SIDEBAR ================= -->

        <div class="col-md-2 p-0">

            <jsp:include page="include/sidebar.jsp"/>

        </div>


        <!-- ================= CONTENT ================= -->

        <div class="col-md-10">

            <div class="container-fluid mt-4">


                <!-- ================= TITLE ================= -->

                <div class="d-flex justify-content-between
                            align-items-center mb-4">

                    <div>

                        <h3 class="font-weight-bold">

                            <i class="fa fa-box text-primary"></i>

                            Quản lý sản phẩm

                        </h3>

                        <p class="text-muted mb-0">

                            Quản lý thông tin sản phẩm trong hệ thống

                        </p>

                    </div>


                    <!-- THÊM SẢN PHẨM -->

                    <a href="<%=request.getContextPath()%>/admin/themSanPham.jsp"
                       class="btn btn-primary">

                        <i class="fa fa-plus"></i>

                        Thêm sản phẩm

                    </a>

                </div>


                <!-- ================= SEARCH ================= -->

                <div class="card shadow-sm mb-4">

                    <div class="card-body">

                        <form method="get"
                              action="<%=request.getContextPath()%>/quanlySanPham">


                            <div class="row">

                                <div class="col-md-9">

                                    <div class="input-group">

                                        <div class="input-group-prepend">

                                            <span class="input-group-text">

                                                <i class="fa fa-search"></i>

                                            </span>

                                        </div>


                                        <input type="text"
                                               name="tuKhoa"
                                               class="form-control"
                                               placeholder="Tìm kiếm theo tên sản phẩm..."
                                               value="<%=tuKhoa%>">

                                    </div>

                                </div>


                                <div class="col-md-3">

                                    <button type="submit"
                                            class="btn btn-primary">

                                        <i class="fa fa-search"></i>

                                        Tìm kiếm

                                    </button>


                                    <a href="<%=request.getContextPath()%>/quanlySanPham"
                                       class="btn btn-secondary">

                                        <i class="fa fa-refresh"></i>

                                    </a>

                                </div>

                            </div>

                        </form>

                    </div>

                </div>


                <!-- ================= DANH SÁCH ================= -->

                <div class="card shadow-sm">


                    <!-- CARD HEADER -->

                    <div class="card-header bg-white">

                        <div class="d-flex justify-content-between
                                    align-items-center">

                            <h5 class="mb-0 font-weight-bold">

                                <i class="fa fa-list"></i>

                                Danh sách sản phẩm

                            </h5>


                            <span class="badge badge-primary">

                                <%
                                    if (danhSach != null) {
                                        out.print(danhSach.size());
                                    } else {
                                        out.print(0);
                                    }
                                %>

                                sản phẩm

                            </span>

                        </div>

                    </div>


                    <!-- CARD BODY -->

                    <div class="card-body p-0">

                        <div class="table-responsive">


                            <table class="table table-bordered
                                          table-hover mb-0">


                                <!-- ================= HEADER TABLE ================= -->

                                <thead class="thead-light">

                                <tr>

                                    <th class="text-center"
                                        style="width:70px;">

                                        STT

                                    </th>


                                    <th class="text-center"
                                        style="width:80px;">

                                        Mã

                                    </th>


                                    <th class="text-center"
                                        style="width:110px;">

                                        Hình ảnh

                                    </th>


                                    <th>

                                        Tên sản phẩm

                                    </th>


                                    <th class="text-right"
                                        style="width:150px;">

                                        Giá

                                    </th>


                                    <th class="text-center"
                                        style="width:110px;">

                                        Số lượng

                                    </th>


                                    <th>

                                        Mô tả

                                    </th>


                                    <th class="text-center"
                                        style="width:180px;">

                                        Thao tác

                                    </th>

                                </tr>

                                </thead>


                                <!-- ================= BODY ================= -->

                                <tbody>

                                <%
                                    if (danhSach != null
                                            && !danhSach.isEmpty()) {

                                        int stt = 1;

                                        for (SanPham sp :
                                                danhSach) {
                                %>


                                <tr>


                                    <!-- STT -->

                                    <td class="text-center">

                                        <%=stt++%>

                                    </td>


                                    <!-- MÃ -->

                                    <td class="text-center">

                                        <%=sp.getMaSanPham()%>

                                    </td>


                                    <!-- HÌNH ẢNH -->

                                    <td class="text-center">

                                        <%
                                            if (sp.getHinhAnh() != null
                                                    && !sp.getHinhAnh()
                                                    .trim()
                                                    .isEmpty()) {
                                        %>

                                        <img
                                                src="<%=request.getContextPath()%>/images/<%=sp.getHinhAnh()%>"
                                                width="80"
                                                height="80"
                                                class="img-thumbnail"
                                                style="object-fit: cover;">

                                        <%
                                        } else {
                                        %>

                                        <div class="text-muted">

                                            <i class="fa fa-image fa-2x"></i>

                                            <br>

                                            Không có ảnh

                                        </div>

                                        <%
                                            }
                                        %>

                                    </td>


                                    <!-- TÊN -->

                                    <td>

                                        <strong>

                                            <%=sp.getTenSanPham()%>

                                        </strong>

                                    </td>


                                    <!-- GIÁ -->

                                    <td class="text-right">

                                        <strong>

                                            <%=String.format(
                                                    "%,.0f",
                                                    sp.getGia()
                                            )%>

                                            VNĐ

                                        </strong>

                                    </td>


                                    <!-- SỐ LƯỢNG -->

                                    <td class="text-center">

                                        <%
                                            if (sp.getSoLuong() <= 0) {
                                        %>

                                        <span class="badge badge-danger">

                                                Hết hàng

                                            </span>

                                        <%
                                        } else if (sp.getSoLuong() <= 5) {
                                        %>

                                        <span class="badge badge-warning">

                                                <%=sp.getSoLuong()%>

                                            </span>

                                        <%
                                        } else {
                                        %>

                                        <span class="badge badge-success">

                                                <%=sp.getSoLuong()%>

                                            </span>

                                        <%
                                            }
                                        %>

                                    </td>


                                    <!-- MÔ TẢ -->

                                    <td>

                                        <%
                                            String moTa =
                                                    sp.getMoTa();

                                            if (moTa == null
                                                    || moTa.trim().isEmpty()) {
                                        %>

                                        <span class="text-muted">

                                                Chưa có mô tả

                                            </span>

                                        <%
                                            } else {

                                                out.print(moTa);
                                            }
                                        %>

                                    </td>


                                    <!-- THAO TÁC -->

                                    <td class="text-center">


                                        <!-- SỬA -->

                                        <a href="<%=request.getContextPath()%>/quanlySanPham?hanhDong=sua&maSanPham=<%=sp.getMaSanPham()%>"
                                           class="btn btn-warning btn-sm">

                                            <i class="fa fa-edit"></i>

                                            Sửa

                                        </a>


                                        <!-- XÓA -->

                                        <a href="<%=request.getContextPath()%>/quanlySanPham?hanhDong=xoa&maSanPham=<%=sp.getMaSanPham()%>"
                                           class="btn btn-danger btn-sm"
                                           onclick="return confirm('Bạn có chắc chắn muốn xóa sản phẩm này không?');">

                                            <i class="fa fa-trash"></i>

                                            Xóa

                                        </a>

                                    </td>


                                </tr>


                                <%
                                    }

                                } else {
                                %>


                                <!-- ================= KHÔNG CÓ DỮ LIỆU ================= -->

                                <tr>

                                    <td colspan="8"
                                        class="text-center py-5">


                                        <div class="text-muted">


                                            <i class="fa fa-box-open fa-3x mb-3"></i>


                                            <h5>

                                                Không có sản phẩm nào

                                            </h5>


                                            <p>

                                                Chưa có dữ liệu sản phẩm
                                                hoặc không tìm thấy kết quả.

                                            </p>


                                            <a href="<%=request.getContextPath()%>/admin/themSanPham.jsp"
                                               class="btn btn-primary">

                                                <i class="fa fa-plus"></i>

                                                Thêm sản phẩm

                                            </a>


                                        </div>


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

        </div>

    </div>

</div>


<!-- ================= FOOTER ================= -->

<jsp:include page="include/footer.jsp"/>


<!-- ================= JAVASCRIPT ================= -->

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>


</body>

</html>
```
