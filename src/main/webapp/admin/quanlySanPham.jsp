<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="java.util.List" %>
<%@ page import="model.SanPham" %>

<%
    List<SanPham> danhSach =
            (List<SanPham>) request.getAttribute("danhSachSanPham");
%>

<!DOCTYPE html>

<html lang="vi">

<head>

    <meta charset="UTF-8">

    <title>

        Quản lý sản phẩm

    </title>

    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.6.2/css/bootstrap.min.css">

    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">

    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/css/admin.css">

</head>

<body>

<jsp:include page="include/header.jsp"/>

<div class="container-fluid">

    <div class="row">

        <!-- MENU -->

        <div class="col-md-2 p-0 sidebar">

            <jsp:include page="include/sidebar.jsp"/>

        </div>

        <!-- NỘI DUNG -->

        <div class="col-md-10">

            <div class="container-fluid mt-4">

                <div class="d-flex justify-content-between align-items-center mb-4">

                    <h2>

                        <i class="fa fa-box"></i>

                        Quản lý sản phẩm

                    </h2>

                    <a class="btn btn-success"

                       href="<%=request.getContextPath()%>/admin/themSanPham.jsp">

                        <i class="fa fa-plus"></i>

                        Thêm sản phẩm

                    </a>

                </div>

                <div class="card shadow">

                    <div class="card-header bg-primary text-white">

                        Danh sách sản phẩm

                    </div>

                    <div class="card-body">

                        <table class="table table-bordered table-hover table-striped">

                            <thead class="thead-dark">

                            <tr>

                                <th width="70">

                                    Mã

                                </th>

                                <th width="100">

                                    Hình

                                </th>

                                <th>

                                    Tên sản phẩm

                                </th>

                                <th width="150">

                                    Giá

                                </th>

                                <th width="100">

                                    Số lượng

                                </th>

                                <th>

                                    Mô tả

                                </th>

                                <th width="170">

                                    Chức năng

                                </th>

                            </tr>

                            </thead>

                            <tbody>

                            <%

                                if (danhSach != null &&
                                        !danhSach.isEmpty()) {

                                    for (SanPham sp : danhSach) {

                            %>

                            <tr>

                                <td>

                                    <%=sp.getMaSanPham()%>

                                </td>

                                <td class="text-center">

                                    <img

                                            src="<%=request.getContextPath()%>/images/<%=sp.getHinhAnh()%>"

                                            width="80"

                                            height="80"

                                            class="img-thumbnail">

                                </td>

                                <td>

                                    <%=sp.getTenSanPham()%>

                                </td>

                                <td>

                                    <%=String.format("%,.0f", sp.getGia())%>

                                    VNĐ

                                </td>

                                <td class="text-center">

                                    <%=sp.getSoLuong()%>

                                </td>

                                <td>

                                    <%=sp.getMoTa()%>

                                </td>

                                <td class="text-center">

                                    <a class="btn btn-warning btn-sm"

                                       href="<%=request.getContextPath()%>/quanlySanPham?hanhDong=sua&maSanPham=<%=sp.getMaSanPham()%>">

                                        <i class="fa fa-edit"></i>

                                        Sửa

                                    </a>

                                    <a class="btn btn-danger btn-sm"

                                       href="<%=request.getContextPath()%>/quanlySanPham?hanhDong=xoa&maSanPham=<%=sp.getMaSanPham()%>"

                                       onclick="return confirm('Bạn có chắc muốn xóa sản phẩm này?')">

                                        <i class="fa fa-trash"></i>

                                        Xóa

                                    </a>

                                </td>

                            </tr>

                            <%

                                }

                            } else {

                            %>

                            <tr>

                                <td colspan="7" class="text-center text-danger">

                                    Chưa có sản phẩm nào.

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

<jsp:include page="include/footer.jsp"/>

<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

</body>

</html>