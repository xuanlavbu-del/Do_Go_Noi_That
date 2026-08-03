<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%@ page import="model.SanPham" %>



<!DOCTYPE html>

<html>


<head>


    <title>

        Sửa sản phẩm

    </title>




    <link rel="stylesheet"

          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.6.2/css/bootstrap.min.css">


</head>




<body>



<%


    SanPham sp =

            (SanPham)
                    request.getAttribute(
                            "sanPham"
                    );


%>






<div class="container mt-5">


    <h3>

        Cập nhật sản phẩm

    </h3>



    <form action="<%=request.getContextPath()%>/quanlySanPham"
          method="post">

        <input type="hidden"

               name="hanhDong"

               value="capNhat">





        <input type="hidden"

               name="maSanPham"

               value="<%=sp.getMaSanPham()%>">







        <div class="form-group">


            <label>

                Tên sản phẩm

            </label>



            <input class="form-control"

                   name="tenSanPham"

                   value="<%=sp.getTenSanPham()%>">


        </div>








        <div class="form-group">


            <label>

                Giá

            </label>


            <input class="form-control"

                   type="number"

                   name="gia"

                   value="<%=sp.getGia()%>">


        </div>







        <div class="form-group">


            <label>

                Số lượng

            </label>


            <input class="form-control"

                   type="number"

                   name="soLuong"

                   value="<%=sp.getSoLuong()%>">


        </div>








        <div class="form-group">


            <label>

                Hình ảnh

            </label>


            <input class="form-control"

                   name="hinhAnh"

                   value="<%=sp.getHinhAnh()%>">


        </div>








        <div class="form-group">


            <label>

                Mã danh mục

            </label>


            <input class="form-control"

                   name="maDanhMuc"

                   value="<%=sp.getMaDanhMuc()%>">


        </div>







        <div class="form-group">


            <label>

                Mô tả

            </label>


            <textarea class="form-control"

                      name="moTa"><%=sp.getMoTa()%></textarea>


        </div>







        <button class="btn btn-primary">

            Cập nhật

        </button>



        <a class="btn btn-secondary"

           href="<%=request.getContextPath()%>/quanlySanPham">

            Hủy

        </a>

    </form>



</div>





</body>


</html>