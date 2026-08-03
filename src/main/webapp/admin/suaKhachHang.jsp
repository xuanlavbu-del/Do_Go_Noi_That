<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="model.KhachHang" %>

<%
  KhachHang kh = (KhachHang) request.getAttribute("khachHang");
%>

<!DOCTYPE html>

<html lang="vi">

<head>

  <meta charset="UTF-8">

  <title>

    Cập nhật khách hàng

  </title>

  <link rel="stylesheet"
        href="https://maxcdn.bootstrapcdn.com/bootstrap/4.6.2/css/bootstrap.min.css">

</head>

<body>

<!-- MENU ADMIN -->

<nav class="navbar navbar-dark bg-dark">

  <a class="navbar-brand"
     href="../index.jsp">

    Đồ Gỗ Nội Thất - ADMIN

  </a>

  <a class="btn btn-outline-light"
     href="../dangNhap">

    Đăng xuất

  </a>

</nav>

<div class="container mt-4">

  <div class="card">

    <div class="card-header bg-warning text-dark">

      <h4>

        Cập nhật khách hàng

      </h4>

    </div>

    <div class="card-body">

      <%
        String loi = (String) request.getAttribute("loi");

        if (loi != null) {
      %>

      <div class="alert alert-danger">

        <%=loi%>

      </div>

      <%
        }
      %>
      <form action="<%=request.getContextPath()%>/suaKhachHang"
            method="post">

        <input
                type="hidden"
                name="maKhachHang"
                value="<%=kh.getMaKhachHang()%>">

        <div class="form-group">

          <label>

            Họ và tên

          </label>

          <input
                  type="text"
                  name="hoTen"
                  class="form-control"
                  value="<%=kh.getHoTen()%>"
                  required>

        </div>

        <div class="form-group">

          <label>

            Giới tính

          </label>

          <select
                  name="gioiTinh"
                  class="form-control">

            <option value="Nam"
                    <%= "Nam".equals(kh.getGioiTinh()) ? "selected" : "" %>>

              Nam

            </option>

            <option value="Nữ"
                    <%= "Nữ".equals(kh.getGioiTinh()) ? "selected" : "" %>>

              Nữ

            </option>

            <option value="Khác"
                    <%= "Khác".equals(kh.getGioiTinh()) ? "selected" : "" %>>

              Khác

            </option>

          </select>

        </div>

        <div class="form-group">

          <label>

            Ngày sinh

          </label>

          <input
                  type="date"
                  name="ngaySinh"
                  class="form-control"
                  value="<%=kh.getNgaySinh() == null ? "" : kh.getNgaySinh()%>">

        </div>

        <div class="form-group">

          <label>

            Số điện thoại

          </label>

          <input
                  type="text"
                  name="soDienThoai"
                  class="form-control"
                  value="<%=kh.getSoDienThoai()%>"
                  required>

        </div>

        <div class="form-group">

          <label>

            Email

          </label>

          <input
                  type="email"
                  name="email"
                  class="form-control"
                  value="<%=kh.getEmail() == null ? "" : kh.getEmail()%>">

        </div>

        <div class="form-group">

          <label>

            Địa chỉ

          </label>

          <input
                  type="text"
                  name="diaChi"
                  class="form-control"
                  value="<%=kh.getDiaChi() == null ? "" : kh.getDiaChi()%>">

        </div>



        <button
                type="submit"
                class="btn btn-warning">

          Cập nhật

        </button>
        <a href="<%=request.getContextPath()%>/khachhang"
           class="btn btn-secondary">

          Quay lại

        </a>


      </form>

    </div>

  </div>

</div>

</body>

</html>