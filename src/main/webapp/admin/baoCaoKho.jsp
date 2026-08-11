<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.TonKho" %>

<%
    List<TonKho> ds = (List<TonKho>) request.getAttribute("dsTonKho");
%>

<!DOCTYPE html>

<html>

<head>

    <title>Báo cáo tồn kho</title>

    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

</head>

<body>

<div class="container mt-4">

    <h3 class="mb-4">

        Báo cáo tồn kho

    </h3>

    <table class="table table-bordered table-striped">

        <thead class="thead-dark">

        <tr>

            <th>Sản phẩm</th>

            <th>Kho</th>

            <th>Số lượng</th>

        </tr>

        </thead>

        <tbody>

        <%

            if(ds!=null){

                for(TonKho tk:ds){

        %>

        <tr>

            <td><%=tk.getTenSanPham()%></td>

            <td><%=tk.getTenKho()%></td>

            <td>

                <span class="badge badge-primary">

                    <%=tk.getSoLuong()%>

                </span>

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