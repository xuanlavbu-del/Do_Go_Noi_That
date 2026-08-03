<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>

<html>


<head>


    <title>
        Thêm sản phẩm
    </title>



    <link rel="stylesheet"

          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.6.2/css/bootstrap.min.css">


</head>




<body>



<div class="container mt-5">


    <h3>

        Thêm sản phẩm mới

    </h3>



    <form action="<%=request.getContextPath()%>/quanlySanPham"
          method="post">



        <input type="hidden"

               name="hanhDong"

               value="them">






        <div class="form-group">


            <label>
                Tên sản phẩm
            </label>


            <input class="form-control"

                   name="tenSanPham">


        </div>







        <div class="form-group">


            <label>

                Giá

            </label>



            <input class="form-control"

                   name="gia"

                   type="number">


        </div>







        <div class="form-group">


            <label>

                Số lượng

            </label>



            <input class="form-control"

                   name="soLuong"

                   type="number">


        </div>








        <div class="form-group">


            <label>

                Hình ảnh

            </label>



            <input class="form-control"

                   name="hinhAnh"

                   placeholder="sofa.jpg">


        </div>







        <div class="form-group">


            <label>

                Mã danh mục

            </label>



            <input class="form-control"

                   name="maDanhMuc"

                   type="number">


        </div>







        <div class="form-group">


            <label>

                Mô tả

            </label>



            <textarea class="form-control"

                      name="moTa">

</textarea>


        </div>








        <button class="btn btn-success">

            Lưu sản phẩm

        </button>




        <a class="btn btn-secondary"

           href="<%=request.getContextPath()%>/quanlySanPham">

            Quay lại

        </a>




    </form>




</div>



</body>


</html>