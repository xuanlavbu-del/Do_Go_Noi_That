<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>

<html lang="vi">

<head>

  <meta charset="UTF-8">

  <title>

    Thêm khách hàng

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

    <div class="card-header bg-success text-white">

      <h4>

        Thêm khách hàng

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

      <form action="<%=request.getContextPath()%>/themKhachHang"
            method="post">

        <div class="form-group">

          <label>

            Họ và tên

          </label>

          <input
                  type="text"
                  name="hoTen"
                  class="form-control"
                  required>

        </div>

        <div class="form-group">

          <label>

            Giới tính

          </label>

          <select
                  name="gioiTinh"
                  class="form-control">

            <option value="Nam">

              Nam

            </option>

            <option value="Nữ">

              Nữ

            </option>

            <option value="Khác">

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
                  class="form-control">

        </div>

        <div class="form-group">

          <label>

            Số điện thoại

          </label>

          <input
                  type="text"
                  name="soDienThoai"
                  class="form-control"
                  required>

        </div>

        <div class="form-group">

          <label>

            Email

          </label>

          <input
                  type="email"
                  name="email"
                  class="form-control">

        </div>

        <div class="form-group">

          <label>

            Địa chỉ

          </label>

          <input
                  type="text"
                  name="diaChi"
                  class="form-control">

        </div>



        <button
                type="submit"
                class="btn btn-success">

          Lưu khách hàng

        </button>

        <a href="../khachhang"
           class="btn btn-secondary">

          Quay lại

        </a>

      </form>

    </div>

  </div>

</div>

</body>

</html>