<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="java.util.List" %>
<%@ page import="model.SanPham" %>


<!DOCTYPE html>

<html lang="vi">


<head>


    <meta charset="UTF-8">


    <title>
        Danh sách sản phẩm
    </title>



    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.6.2/css/bootstrap.min.css">



    <link rel="stylesheet"
          href="css/style.css">


</head>



<body>



<!-- HEADER -->

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">


    <a class="navbar-brand"
       href="index.jsp">

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

                <a class="nav-link"
                   href="dangNhap.jsp">

                    Đăng nhập

                </a>

            </li>



        </ul>


    </div>


</nav>





<!-- SEARCH -->


<div class="container mt-4">


    <form action="sanPham"
          method="get">


        <input type="hidden"
               name="hanhDong"
               value="timKiem">



        <div class="input-group">


            <input type="text"
                   class="form-control"
                   name="tuKhoa"
                   placeholder="Tìm kiếm đồ nội thất...">



            <div class="input-group-append">


                <button class="btn btn-primary">

                    Tìm kiếm

                </button>


            </div>



        </div>



    </form>



</div>







<!-- PRODUCT LIST -->


<div class="container mt-5">


    <h2 class="text-center mb-4">

        Danh sách sản phẩm

    </h2>




    <div class="row">



        <%


            List<SanPham> danhSach =

                    (List<SanPham>)
                            request.getAttribute(
                                    "danhSachSanPham"
                            );



            if(danhSach != null){



                for(SanPham sp : danhSach){


        %>





        <div class="col-md-4 mb-4">


            <div class="card h-100">



                <img class="card-img-top"

                     src="images/<%=sp.getHinhAnh()%>"

                     height="250">





                <div class="card-body">



                    <h5 class="card-title">

                        <%=sp.getTenSanPham()%>

                    </h5>




                    <p>

                        Giá:

                        <strong class="text-danger">

                            <%=String.format("%,.0f",sp.getGia())%>

                            VNĐ

                        </strong>


                    </p>




                    <p>

                        Số lượng:

                        <%=sp.getSoLuong()%>

                    </p>






                    <a class="btn btn-info"

                       href="sanPham?hanhDong=chiTiet&maSanPham=<%=sp.getMaSanPham()%>">


                        Chi tiết


                    </a>





                    <form action="gioHang"
                          method="post"
                          style="display:inline;">



                        <input type="hidden"

                               name="maSanPham"

                               value="<%=sp.getMaSanPham()%>">





                        <button class="btn btn-success">

                            Mua ngay

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






<footer class="bg-dark text-white text-center p-3">


    © 2026 Đồ Gỗ Nội Thất


</footer>



</body>


</html>