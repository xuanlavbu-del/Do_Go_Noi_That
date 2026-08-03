<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="java.util.List" %>
<%@ page import="model.SanPham" %>


<!DOCTYPE html>
<html lang="vi">

<head>

    <meta charset="UTF-8">

    <title>Đồ Gỗ Nội Thất</title>


    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.6.2/css/bootstrap.min.css">

    <link rel="stylesheet" href="css/style.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<body>
<header>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">


    <a class="navbar-brand" href="index.jsp">
        Đồ Gỗ Nội Thất
    </a>


    <button class="navbar-toggler"
            data-toggle="collapse"
            data-target="#menu">

        <span class="navbar-toggler-icon"></span>

    </button>


    <div class="collapse navbar-collapse"
         id="menu">


        <ul class="navbar-nav ml-auto">


            <li class="nav-item">
                <a class="nav-link"
                   href="sanPham">
                    Sản phẩm
                </a>
            </li>


            <li class="nav-item">
                <a class="nav-link"
                   href="gioHang">
                    Giỏ hàng
                </a>
            </li>


            <li class="nav-item">
                    <%
if(session.getAttribute("taiKhoan")==null){
%>

            <li class="nav-item">
                <a class="nav-link"
                   href="dangNhap">
                    Đăng nhập
                </a>
            </li>

            <%
            }else{
            %>

            <li class="nav-item">

    <span class="nav-link text-warning">

        Xin chào

        <%=session.getAttribute("hoTen")%>

    </span>

            </li>

            <li class="nav-item">

                <a class="nav-link text-danger"

                   href="dangXuat">

                    Đăng xuất

                </a>

            </li>

            <%
                }
            %>
            </li>


        </ul>


    </div>


</nav>
</header>



<div class="container mt-4">


    <h2 class="text-center mb-4">
        Sản phẩm nổi bật
    </h2>



    <div class="row">


        <%

            List<SanPham> danhSach =
                    (List<SanPham>)
                            request.getAttribute("danhSachSanPham");


            if(danhSach != null){


                for(SanPham sp:danhSach){

        %>



        <div class="col-md-4 mb-4">


            <div class="card">


                <img class="card-img-top"
                     src="images/<%=sp.getHinhAnh()%>"
                     height="220">



                <div class="card-body">


                    <h5>
                        <%=sp.getTenSanPham()%>
                    </h5>


                    <p>
                        Giá:
                        <b>
                            <%=sp.getGia()%>
                            VNĐ
                        </b>
                    </p>



                    <a class="btn btn-primary"
                       href="sanPham?hanhDong=chiTiet&maSanPham=<%=sp.getMaSanPham()%>">

                        Xem chi tiết

                    </a>



                    <form action="gioHang"
                          method="post"
                          style="display:inline">


                        <input type="hidden"
                               name="maSanPham"
                               value="<%=sp.getMaSanPham()%>">


                        <button class="btn btn-success">

                            Mua

                        </button>


                    </form>


                </div>


            </div>


        </div>



        <%

                }

            }

        %>


    </div>


</div>



</body>

</html>