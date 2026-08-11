
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.DanhMuc" %>

<%
    List<DanhMuc> danhSachDanhMuc =
            (List<DanhMuc>) request.getAttribute("danhSachDanhMuc");

    String tuKhoa =
            (String) request.getAttribute("tuKhoa");

    String loi =
            request.getParameter("loi");

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

    <title>Quản lý danh mục</title>

    <!-- Bootstrap -->
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

    <!-- Font Awesome -->
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">

    <!-- CSS admin -->
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/admin/css/admin.css">

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

                            <i class="fa fa-folder-open text-primary"></i>

                            Quản lý danh mục

                        </h3>

                        <p class="text-muted mb-0">

                            Quản lý danh mục sản phẩm trong hệ thống

                        </p>

                    </div>


                    <a href="<%=request.getContextPath()%>/admin/themDanhMuc.jsp"
                       class="btn btn-primary">

                        <i class="fa fa-plus"></i>

                        Thêm danh mục

                    </a>

                </div>


                <!-- ================= THÔNG BÁO ================= -->

                <%
                    if ("conSanPham".equals(loi)) {
                %>

                <div class="alert alert-danger alert-dismissible fade show">

                    <i class="fa fa-exclamation-circle"></i>

                    Không thể xóa danh mục vì danh mục này
                    đang có sản phẩm.

                    <button type="button"
                            class="close"
                            data-dismiss="alert">

                        &times;

                    </button>

                </div>

                <%
                    }

                    if ("khongTonTai".equals(loi)) {
                %>

                <div class="alert alert-danger alert-dismissible fade show">

                    <i class="fa fa-exclamation-circle"></i>

                    Danh mục không tồn tại.

                    <button type="button"
                            class="close"
                            data-dismiss="alert">

                        &times;

                    </button>

                </div>

                <%
                    }

                    if ("duLieu".equals(loi)) {
                %>

                <div class="alert alert-danger alert-dismissible fade show">

                    <i class="fa fa-exclamation-circle"></i>

                    Dữ liệu không hợp lệ.

                    <button type="button"
                            class="close"
                            data-dismiss="alert">

                        &times;

                    </button>

                </div>

                <%
                    }
                %>


                <!-- ================= SEARCH ================= -->

                <div class="card shadow-sm mb-4">

                    <div class="card-body">

                        <form method="get"
                              action="<%=request.getContextPath()%>/quanLyDanhMuc">

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
                                               placeholder="Tìm kiếm theo tên danh mục..."
                                               value="<%=tuKhoa%>">

                                    </div>

                                </div>


                                <div class="col-md-3">

                                    <button type="submit"
                                            class="btn btn-primary">

                                        <i class="fa fa-search"></i>

                                        Tìm kiếm

                                    </button>

                                    <a href="<%=request.getContextPath()%>/quanLyDanhMuc"
                                       class="btn btn-secondary">

                                        <i class="fa fa-refresh"></i>

                                    </a>

                                </div>

                            </div>

                        </form>

                    </div>

                </div>


                <!-- ================= TABLE ================= -->

                <div class="card shadow-sm">

                    <div class="card-header bg-white">

                        <div class="d-flex justify-content-between
                                    align-items-center">

                            <h5 class="mb-0 font-weight-bold">

                                <i class="fa fa-list"></i>

                                Danh sách danh mục

                            </h5>


                            <span class="badge badge-primary">

                                <%
                                    if (danhSachDanhMuc != null) {
                                        out.print(danhSachDanhMuc.size());
                                    } else {
                                        out.print(0);
                                    }
                                %>

                                danh mục

                            </span>

                        </div>

                    </div>


                    <div class="card-body p-0">

                        <div class="table-responsive">

                            <table class="table table-bordered
                                          table-hover mb-0">

                                <thead class="thead-light">

                                <tr>

                                    <th class="text-center"
                                        style="width: 80px;">

                                        STT

                                    </th>

                                    <th style="width: 120px;">

                                        Mã danh mục

                                    </th>

                                    <th>

                                        Tên danh mục

                                    </th>

                                    <th>

                                        Mô tả

                                    </th>

                                    <th class="text-center"
                                        style="width: 180px;">

                                        Thao tác

                                    </th>

                                </tr>

                                </thead>


                                <tbody>

                                <%
                                    if (danhSachDanhMuc != null
                                            && !danhSachDanhMuc.isEmpty()) {

                                        int stt = 1;

                                        for (DanhMuc dm :
                                                danhSachDanhMuc) {
                                %>

                                <tr>

                                    <!-- STT -->

                                    <td class="text-center">

                                        <%=stt++%>

                                    </td>


                                    <!-- MÃ -->

                                    <td>

                                        <%=dm.getMaDanhMuc()%>

                                    </td>


                                    <!-- TÊN -->

                                    <td>

                                        <strong>

                                            <%=dm.getTenDanhMuc()%>

                                        </strong>

                                    </td>


                                    <!-- MÔ TẢ -->

                                    <td>

                                        <%
                                            String moTa =
                                                    dm.getMoTa();

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

                                        <a href="<%=request.getContextPath()%>/quanLyDanhMuc?hanhDong=sua&maDanhMuc=<%=dm.getMaDanhMuc()%>"
                                           class="btn btn-warning btn-sm">

                                            <i class="fa fa-edit"></i>

                                            Sửa

                                        </a>


                                        <!-- XÓA -->

                                        <a href="<%=request.getContextPath()%>/quanLyDanhMuc?hanhDong=xoa&maDanhMuc=<%=dm.getMaDanhMuc()%>"
                                           class="btn btn-danger btn-sm"
                                           onclick="return confirm('Bạn có chắc chắn muốn xóa danh mục này không?');">

                                            <i class="fa fa-trash"></i>

                                            Xóa

                                        </a>

                                    </td>

                                </tr>

                                <%
                                    }

                                } else {
                                %>

                                <!-- KHÔNG CÓ DỮ LIỆU -->

                                <tr>

                                    <td colspan="5"
                                        class="text-center py-5">

                                        <div class="text-muted">

                                            <i class="fa fa-folder-open fa-3x mb-3"></i>

                                            <h5>

                                                Không có danh mục nào

                                            </h5>

                                            <p>

                                                Chưa có dữ liệu danh mục
                                                hoặc không tìm thấy kết quả.

                                            </p>

                                            <a href="<%=request.getContextPath()%>/admin/themDanhMuc.jsp"
                                               class="btn btn-primary">

                                                <i class="fa fa-plus"></i>

                                                Thêm danh mục

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
