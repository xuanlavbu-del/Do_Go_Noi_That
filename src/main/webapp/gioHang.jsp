<%@ page import="java.util.List" %>
<%@ page import="model.GioHang" %>


<!DOCTYPE html>

<html>


<head>

    <title>Giỏ hàng</title>


    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.6.2/css/bootstrap.min.css">


</head>



<body>


<div class="container mt-4">


    <h2>
        Giỏ hàng
    </h2>



    <table class="table table-bordered">


        <tr>

            <th>Sản phẩm</th>

            <th>Giá</th>

            <th>Số lượng</th>

            <th>Thành tiền</th>

            <th></th>

        </tr>



        <%


            List<GioHang> gioHang =
                    (List<GioHang>)
                            session.getAttribute("gioHang");


            if(gioHang!=null){


                for(GioHang sp:gioHang){


        %>


        <tr>


            <td>
                <%=sp.getTenSanPham()%>
            </td>


            <td>
                <%=sp.getGia()%>
            </td>


            <td>
                <%=sp.getSoLuong()%>
            </td>


            <td>
                <%=sp.getThanhTien()%>
            </td>


            <td>


                <a class="btn btn-danger"
                   href="gioHang?hanhDong=xoa&maSanPham=<%=sp.getMaSanPham()%>">

                    Xóa

                </a>


            </td>


        </tr>


        <%

                }

            }

        %>


    </table>



    <a class="btn btn-success"
       href="thanhToan">

        Thanh toán

    </a>



</div>


</body>


</html>