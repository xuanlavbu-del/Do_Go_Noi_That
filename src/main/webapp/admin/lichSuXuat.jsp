<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.PhieuXuat" %>

<%
    List<PhieuXuat> ds = (List<PhieuXuat>) request.getAttribute("dsPhieuXuat");
%>

<!DOCTYPE html>

<html>

<head>

    <title>Lịch sử xuất kho</title>

    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

</head>

<body>

<div class="container mt-4">

    <h3 class="mb-4">

        Lịch sử xuất kho

    </h3>

    <table class="table table-bordered table-hover">

        <thead class="thead-dark">

        <tr>

            <th>Mã phiếu</th>

            <th>Ngày xuất</th>

            <th>Khách hàng</th>

            <th>Tổng tiền</th>

            <th>Ghi chú</th>

            <th>Chi tiết</th>

        </tr>

        </thead>

        <tbody>

        <%

            if(ds!=null){

                for(PhieuXuat px:ds){

        %>

        <tr>

            <td><%=px.getMaPhieuXuat()%></td>

            <td><%=px.getNgayXuat()%></td>

            <td><%=px.getNguoiNhan()%></td>

            <td><%= String.format("%,.0f", px.getTongTien()) %> đ</td>

            <td><%=px.getGhiChu()%></td>

            <td>
                <a href="<%=request.getContextPath()%>/chiTietPhieuXuat?maPhieuXuat=<%=px.getMaPhieuXuat()%>"
                   class="btn btn-info btn-sm">
                    Xem
                </a>
            </td>
        </tr>

        <%

                }

            }

        %>

        </tbody>

    </table>

    <a href="dashboard"
       class="btn btn-secondary">

        Quay lại

    </a>

</div>

</body>

</html>