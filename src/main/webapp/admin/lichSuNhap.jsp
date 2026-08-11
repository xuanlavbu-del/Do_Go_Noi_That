<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.PhieuNhap" %>

<%
    List<PhieuNhap> ds = (List<PhieuNhap>) request.getAttribute("dsPhieuNhap");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Lịch sử nhập kho</title>

    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
</head>

<body>

<div class="container mt-4">

    <h3 class="mb-4">
        Lịch sử nhập kho
    </h3>

    <table class="table table-bordered table-hover">

        <thead class="thead-dark">

        <tr>

            <th>Mã phiếu</th>

            <th>Ngày nhập</th>

            <th>Nhà cung cấp</th>

            <th>Tổng tiền</th>

            <th>Ghi chú</th>

            <th>Chi tiết</th>

        </tr>

        </thead>

        <tbody>

        <%
            if (ds != null) {
                for (PhieuNhap pn : ds) {
        %>

        <tr>

            <td><%= pn.getMaPhieuNhap() %></td>

            <td><%= pn.getNgayNhap() %></td>

            <td><%= pn.getNhaCungCap() %></td>

            <td><%= String.format("%,.0f", pn.getTongTien()) %> đ</td>

            <td><%= pn.getGhiChu() %></td>

            <td>

                <a href="<%=request.getContextPath()%>/chiTietPhieuNhap?maPhieuNhap=<%=pn.getMaPhieuNhap()%>"
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

    <a href="dashboard" class="btn btn-secondary">

        Quay lại

    </a>

</div>

</body>

</html>