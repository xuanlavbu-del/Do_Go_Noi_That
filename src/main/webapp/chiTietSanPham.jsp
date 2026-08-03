<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%@ page import="model.SanPham" %>



<!DOCTYPE html>


<html lang="vi">


<head>


    <meta charset="UTF-8">


    <title>

        Chi tiết sản phẩm

    </title>



    <link rel="stylesheet"

          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.6.2/css/bootstrap.min.css">



    <link rel="stylesheet"

          href="css/style.css">


</head>



<body>




<nav class="navbar navbar-expand-lg navbar-dark bg-dark">



    <a class="navbar-brand"

       href="index.jsp">

        Đồ Gỗ Nội Thất

    </a>




    <ul class="navbar-nav ml-auto">



        <li class="nav-item">

            <a class="nav-link"

               href="gioHang">

                Giỏ hàng

            </a>

        </li>



    </ul>



</nav>







<%


    SanPham sanPham =

            (SanPham)

                    request.getAttribute(
                            "sanPham"
                    );



    if(sanPham != null){



%>







<div class="container mt-5">


    <div class="row">






        <!-- IMAGE -->


        <div class="col-md-6">


            <img class="img-fluid rounded"

                 src="images/<%=sanPham.getHinhAnh()%>"

                 style="width:100%;height:400px;object-fit:cover;">



        </div>







        <!-- INFORMATION -->


        <div class="col-md-6">



            <h2>

                <%=sanPham.getTenSanPham()%>

            </h2>




            <h4 class="text-danger">


                <%=String.format("%,.0f",
                        sanPham.getGia())%>

                VNĐ


            </h4>





            <p>


                <strong>Số lượng:</strong>


                <%=sanPham.getSoLuong()%>


            </p>






            <p>


                <strong>Mô tả:</strong>


            </p>



            <p>


                <%=sanPham.getMoTa()%>


            </p>







            <form action="gioHang"

                  method="post">



                <input type="hidden"

                       name="maSanPham"

                       value="<%=sanPham.getMaSanPham()%>">





                <button class="btn btn-success btn-lg">


                    Thêm vào giỏ hàng


                </button>



            </form>





        </div>





    </div>


</div>






<%


}


else{


%>



<div class="container mt-5">


    <div class="alert alert-danger">


        Không tìm thấy sản phẩm


    </div>


</div>



<%


    }


%>






<footer class="bg-dark text-white text-center p-3 mt-5">


    © Đồ Gỗ Nội Thất


</footer>



</body>


</html>