<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>

<html lang="vi">

<head>

    <meta charset="UTF-8">

    <title>Dashboard Admin</title>

    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.6.2/css/bootstrap.min.css">

    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">



</head>

<body>

<nav class="navbar navbar-dark bg-dark">

    <span class="navbar-brand">

        ĐỒ GỖ NỘI THẤT ADMIN

    </span>

    <span class="text-white">

        Xin chào

        <b>

            <%=session.getAttribute("hoTen")%>

        </b>

    </span>

</nav>
<jsp:include page="include/header.jsp"/>
<div class="container-fluid">

    <div class="row">

        <!-- SIDEBAR -->

        <div class="col-md-2 sidebar p-0">
            <jsp:include page="include/sidebar.jsp"/>
        </div>

        <!-- CONTENT -->

        <div class="col-md-10">

            <div class="container mt-4">

                <h2>

                    Dashboard

                </h2>

                <hr>

                <div class="row">

                    <div class="col-md-3">

                        <div class="card bg-primary text-white card-dashboard">

                            <div class="card-body">

                                <h3>

                                    ${tongSanPham}

                                </h3>

                                <p>

                                    Sản phẩm

                                </p>

                            </div>

                        </div>

                    </div>

                    <div class="col-md-3">

                        <div class="card bg-success text-white card-dashboard">

                            <div class="card-body">

                                <h3>

                                    ${tongKhachHang}

                                </h3>

                                <p>

                                    Khách hàng

                                </p>

                            </div>

                        </div>

                    </div>

                    <div class="col-md-3">

                        <div class="card bg-warning text-white card-dashboard">

                            <div class="card-body">

                                <h3>

                                    ${tongDonHang}

                                </h3>

                                <p>

                                    Đơn hàng

                                </p>

                            </div>

                        </div>

                    </div>

                    <div class="col-md-3">

                        <div class="card bg-danger text-white card-dashboard">

                            <div class="card-body">

                                <h3>

                                    ${tongTaiKhoan}

                                </h3>

                                <p>

                                    Tài khoản

                                </p>

                            </div>

                        </div>

                    </div>

                </div>

                <hr>



        </div>

    </div>

</div>
<jsp:include page="include/footer.jsp"/>
</body>

</html>