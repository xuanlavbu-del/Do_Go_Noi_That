<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="java.util.List" %>
<%@ page import="model.KhachHang" %>

<%
    List<KhachHang> danhSach =
            (List<KhachHang>) request.getAttribute("danhSachKhachHang");
%>

<!DOCTYPE html>

<html lang="vi">

<head>

    <meta charset="UTF-8">

    <title>

        Quản lý khách hàng

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

        <!-- Sidebar -->

        <div class="col-md-2 p-0 sidebar">

            <jsp:include page="include/sidebar.jsp"/>

        </div>

        <!-- Content -->

        <div class="col-md-10">

            <div class="container-fluid mt-4">

                <div class="d-flex justify-content-between align-items-center mb-4">

                    <h2>

                        <i class="fa fa-users"></i>

                        Quản lý khách hàng

                    </h2>

                    <a class="btn btn-success"

                       href="<%=request.getContextPath()%>/admin/themKhachHang.jsp">

                        <i class="fa fa-user-plus"></i>

                        Thêm khách hàng

                    </a>

                </div>

                <div class="card shadow">

                    <div class="card-header bg-success text-white">

                        Danh sách khách hàng

                    </div>

                    <div class="card-body">

                        <table class="table table-bordered table-hover table-striped">

                            <thead class="thead-dark">

                            <tr>

                                <th width="70">

                                    Mã

                                </th>

                                <th>

                                    Họ và tên

                                </th>

                                <th width="100">

                                    Giới tính

                                </th>

                                <th width="130">

                                    Ngày sinh

                                </th>

                                <th width="130">

                                    Điện thoại

                                </th>

                                <th>

                                    Email

                                </th>

                                <th>

                                    Địa chỉ

                                </th>

                                <th width="180">

                                    Chức năng

                                </th>

                            </tr>

                            </thead>

                            <tbody>

                            <%

                                if (danhSach != null &&
                                        !danhSach.isEmpty()) {

                                    for (KhachHang kh : danhSach) {

                            %>

                            <tr>

                                <td>

                                    <%=kh.getMaKhachHang()%>

                                </td>

                                <td>

                                    <%=kh.getHoTen()%>

                                </td>

                                <td class="text-center">

                                    <%=kh.getGioiTinh()%>

                                </td>

                                <td>

                                    <%=kh.getNgaySinh()%>

                                </td>

                                <td>

                                    <%=kh.getSoDienThoai()%>

                                </td>

                                <td>

                                    <%=kh.getEmail()%>

                                </td>

                                <td>

                                    <%=kh.getDiaChi()%>

                                </td>

                                <td class="text-center">

                                    <a class="btn btn-warning btn-sm"

                                       href="<%=request.getContextPath()%>/suaKhachHang?maKhachHang=<%=kh.getMaKhachHang()%>">

                                        <i class="fa fa-edit"></i>

                                        Sửa

                                    </a>

                                    <a class="btn btn-danger btn-sm"

                                       href="<%=request.getContextPath()%>/xoaKhachHang?maKhachHang=<%=kh.getMaKhachHang()%>"

                                       onclick="return confirm('Bạn có chắc muốn xóa khách hàng này?')">

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

                                <td colspan="8"

                                    class="text-center text-danger">

                                    Chưa có khách hàng nào.

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