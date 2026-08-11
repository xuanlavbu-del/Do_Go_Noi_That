<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.DanhMuc" %>

<%
    DanhMuc danhMuc =
            (DanhMuc) request.getAttribute("danhMuc");

    String loi =
            request.getParameter("loi");

    /*
     * Nếu không tìm thấy danh mục
     */
    if (danhMuc == null) {
%>

<!DOCTYPE html>
<html lang="vi">

<head>

    <meta charset="UTF-8">

    <title>Không tìm thấy danh mục</title>

    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">

    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/admin/css/admin.css">

</head>

<body>

<jsp:include page="include/header.jsp"/>

<div class="container-fluid">

    <div class="row">

        <div class="col-md-2 p-0">

            <jsp:include page="include/sidebar.jsp"/>

        </div>

        <div class="col-md-10">

            <div class="container-fluid mt-4">

                <div class="alert alert-danger">

                    <i class="fa fa-exclamation-circle"></i>

                    Không tìm thấy danh mục cần sửa.

                </div>

                <a href="<%=request.getContextPath()%>/quanLyDanhMuc"
                   class="btn btn-secondary">

                    <i class="fa fa-arrow-left"></i>

                    Quay lại

                </a>

            </div>

        </div>

    </div>

</div>

<jsp:include page="include/footer.jsp"/>

</body>

</html>

<%
        return;
    }
%>


<!DOCTYPE html>

<html lang="vi">

<head>

    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>Sửa danh mục</title>


    <!-- Bootstrap -->

    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">


    <!-- Font Awesome -->

    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">


    <!-- CSS Admin -->

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

                <div class="mb-4">

                    <h3 class="font-weight-bold">

                        <i class="fa fa-edit text-warning"></i>

                        Sửa danh mục

                    </h3>

                    <p class="text-muted mb-0">

                        Cập nhật thông tin danh mục sản phẩm

                    </p>

                </div>


                <!-- ================= THÔNG BÁO ================= -->

                <%
                    if ("trungTen".equals(loi)) {
                %>

                <div class="alert alert-danger alert-dismissible fade show">

                    <i class="fa fa-exclamation-circle"></i>

                    Tên danh mục đã tồn tại.
                    Vui lòng nhập tên khác.

                    <button type="button"
                            class="close"
                            data-dismiss="alert">

                        &times;

                    </button>

                </div>

                <%
                    }

                    if ("rong".equals(loi)) {
                %>

                <div class="alert alert-danger alert-dismissible fade show">

                    <i class="fa fa-exclamation-circle"></i>

                    Vui lòng nhập tên danh mục.

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


                <!-- ================= FORM ================= -->

                <div class="card shadow-sm">


                    <!-- CARD HEADER -->

                    <div class="card-header bg-white">

                        <h5 class="mb-0 font-weight-bold">

                            <i class="fa fa-folder-open text-warning"></i>

                            Thông tin danh mục

                        </h5>

                    </div>


                    <!-- CARD BODY -->

                    <div class="card-body">


                        <form method="post"
                              action="<%=request.getContextPath()%>/quanLyDanhMuc">


                            <!-- ================= HÀNH ĐỘNG ================= -->

                            <input type="hidden"
                                   name="hanhDong"
                                   value="capNhat">


                            <!-- ================= MÃ DANH MỤC ================= -->

                            <div class="form-group">

                                <label for="maDanhMuc">

                                    <strong>

                                        Mã danh mục

                                    </strong>

                                </label>


                                <input type="text"
                                       id="maDanhMuc"
                                       class="form-control"
                                       value="<%=danhMuc.getMaDanhMuc()%>"
                                       readonly>


                                <!--
                                    Mã danh mục thực sự gửi
                                    về Servlet
                                -->

                                <input type="hidden"
                                       name="maDanhMuc"
                                       value="<%=danhMuc.getMaDanhMuc()%>">


                                <small class="form-text text-muted">

                                    Mã danh mục được hệ thống tự động tạo
                                    và không thể thay đổi.

                                </small>

                            </div>


                            <!-- ================= TÊN DANH MỤC ================= -->

                            <div class="form-group">

                                <label for="tenDanhMuc">

                                    <strong>

                                        Tên danh mục

                                    </strong>

                                    <span class="text-danger">

                                        *

                                    </span>

                                </label>


                                <input type="text"
                                       id="tenDanhMuc"
                                       name="tenDanhMuc"
                                       class="form-control"
                                       maxlength="100"
                                       value="<%=danhMuc.getTenDanhMuc()%>"
                                       placeholder="Nhập tên danh mục..."
                                       required
                                       autofocus>


                                <small class="form-text text-muted">

                                    Tên danh mục không được để trống
                                    và tối đa 100 ký tự.

                                </small>

                            </div>


                            <!-- ================= MÔ TẢ ================= -->

                            <div class="form-group">

                                <label for="moTa">

                                    <strong>

                                        Mô tả

                                    </strong>

                                </label>


                                <textarea
                                        id="moTa"
                                        name="moTa"
                                        class="form-control"
                                        rows="5"
                                        placeholder="Nhập mô tả cho danh mục..."><%=danhMuc.getMoTa() == null ? "" : danhMuc.getMoTa()%></textarea>


                                <small class="form-text text-muted">

                                    Có thể để trống nếu danh mục
                                    không có mô tả.

                                </small>

                            </div>


                            <!-- ================= BUTTON ================= -->

                            <div class="mt-4">


                                <!-- CẬP NHẬT -->

                                <button type="submit"
                                        class="btn btn-warning">

                                    <i class="fa fa-save"></i>

                                    Cập nhật danh mục

                                </button>


                                <!-- HỦY -->

                                <a href="<%=request.getContextPath()%>/quanLyDanhMuc"
                                   class="btn btn-secondary">

                                    <i class="fa fa-times"></i>

                                    Hủy

                                </a>

                            </div>


                        </form>

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

