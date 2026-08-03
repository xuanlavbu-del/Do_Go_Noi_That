<%@ page contentType="text/html;charset=UTF-8" %>


<!DOCTYPE html>

<html>


<head>

    <title>Đăng ký</title>


    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.6.2/css/bootstrap.min.css">


</head>


<body>



<div class="container mt-4">


    <div class="col-md-6 mx-auto">


        <h3>
            Tạo tài khoản
        </h3>



        <form action="dangKy"
              method="post">



            <input class="form-control mb-2"
                   name="hoTen"
                   placeholder="Họ tên">



            <input class="form-control mb-2"
                   name="email"
                   placeholder="Email">



            <input class="form-control mb-2"
                   type="password"
                   name="matKhau"
                   placeholder="Mật khẩu">



            <input class="form-control mb-2"
                   type="password"
                   name="xacNhanMatKhau"
                   placeholder="Nhập lại mật khẩu">



            <input class="form-control mb-2"
                   name="soDienThoai"
                   placeholder="Số điện thoại">



            <textarea class="form-control mb-2"
                      name="diaChi"
                      placeholder="Địa chỉ">
</textarea>



            <button class="btn btn-success">

                Đăng ký

            </button>



        </form>



    </div>

</div>


</body>


</html>