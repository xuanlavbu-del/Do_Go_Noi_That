<%@ page contentType="text/html;charset=UTF-8" %>


<!DOCTYPE html>
<html>


<head>

    <title>Đăng nhập</title>

    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.6.2/css/bootstrap.min.css">

</head>



<body>


<div class="container mt-5">


    <div class="col-md-5 mx-auto">


        <h3 class="text-center">
            Đăng nhập
        </h3>



        <%

            String loi =
                    (String)request.getAttribute("loi");

            if(loi!=null){

        %>

        <div class="alert alert-danger">
            <%=loi%>
        </div>


        <%

            }

        %>



        <form action="dangNhap"
              method="post">



            <input class="form-control mb-3"
                   name="email"
                   placeholder="Email">



            <input class="form-control mb-3"
                   type="password"
                   name="matKhau"
                   placeholder="Mật khẩu">



            <button class="btn btn-primary btn-block">

                Đăng nhập

            </button>


        </form>


        <a href="dangKy.jsp">

            Chưa có tài khoản? Đăng ký

        </a>


    </div>


</div>


</body>


</html>