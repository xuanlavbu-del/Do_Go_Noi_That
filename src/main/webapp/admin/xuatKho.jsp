<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
<%@ page import="model.Kho"%>
<%@ page import="model.SanPham"%>

<%
    List<Kho> dsKho=(List<Kho>)request.getAttribute("dsKho");
    List<SanPham> dsSanPham=(List<SanPham>)request.getAttribute("dsSanPham");
%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Xuất Kho</title>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">

    <style>
        body{
            background:#f5f6fa;
        }
        .card{
            border-radius:12px;
            box-shadow:0 3px 12px rgba(0,0,0,.15);
        }
        .card-header{
            font-size:22px;
            font-weight:bold;
        }
        .table th{
            text-align:center;
            vertical-align:middle;
        }
        .table td{
            vertical-align:middle;
        }
        input[type=number]{
            text-align:center;
        }
        #tongTien{
            font-size:26px;
            font-weight:bold;
            color:#dc3545;
        }
        .btn-action{
            width:40px;
            height:40px;
        }
    </style>

</head>

<body>

<div class="container-fluid mt-4">

    <div class="card">

        <div class="card-header bg-danger text-white">

            <i class="fa-solid fa-file-export"></i>

            PHIẾU XUẤT KHO

        </div>

        <div class="card-body">

            <form
                    action="${pageContext.request.contextPath}/XuatKhoServlet"
                    method="post">

                <div class="row">

                    <div class="col-md-4 mb-3">

                        <label class="form-label">

                            Kho Xuất

                        </label>

                        <select
                                name="maKho"
                                id="maKho"
                                class="form-select"
                                required>

                            <option value="">-- Chọn Kho --</option>

                            <%
                                if(dsKho!=null){
                                    for(Kho k:dsKho){
                            %>

                            <option value="<%=k.getMaKho()%>">

                                <%=k.getTenKho()%>

                            </option>

                            <%
                                    }
                                }
                            %>

                        </select>

                    </div>

                    <div class="col-md-4 mb-3">

                        <label class="form-label">

                            Ngày Xuất

                        </label>

                        <input
                                type="date"
                                class="form-control"
                                name="ngayXuat"
                                required>

                    </div>

                    <div class="col-md-4 mb-3">

                        <label class="form-label">

                            Người Xuất

                        </label>

                        <input
                                type="text"
                                class="form-control"
                                name="nguoiXuat"
                                required>

                    </div>

                </div>

                <div class="row">

                    <div class="col-md-12">

                        <label class="form-label">

                            Ghi Chú

                        </label>

                        <textarea
                                class="form-control"
                                rows="3"
                                name="ghiChu"></textarea>

                    </div>

                </div>

                <hr>

                <div class="d-flex justify-content-between align-items-center mb-3">

                    <h4>

                        Danh Sách Sản Phẩm Xuất

                    </h4>

                    <button
                            type="button"
                            class="btn btn-primary"
                            data-bs-toggle="modal"
                            data-bs-target="#modalSanPham">

                        <i class="fa-solid fa-plus"></i>

                        Thêm Sản Phẩm

                    </button>

                </div>

                <table
                        class="table table-bordered table-hover"
                        id="bangXuatKho">

                    <thead class="table-dark">

                    <tr>

                        <th width="5%">#</th>

                        <th width="15%">Mã SP</th>

                        <th>Tên Sản Phẩm</th>

                        <th width="12%">Tồn Kho</th>

                        <th width="12%">SL Xuất</th>

                        <th width="15%">Đơn Giá</th>

                        <th width="15%">Thành Tiền</th>

                        <th width="8%">Xóa</th>

                    </tr>

                    </thead>

                    <tbody id="chiTietXuat">

                    </tbody>

                    <tfoot>

                    <tr>

                        <th colspan="6" class="text-end">

                            Tổng Tiền

                        </th>

                        <th id="tongTien">

                            0

                        </th>

                        <th></th>

                    </tr>

                    </tfoot>

                </table>
                <div class="modal fade" id="modalSanPham" tabindex="-1">
                    <div class="modal-dialog modal-xl">
                        <div class="modal-content">
                            <div class="modal-header bg-primary text-white">
                                <h5 class="modal-title">
                                    <i class="fa-solid fa-box"></i>
                                    Chọn Sản Phẩm
                                </h5>
                                <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal"></button>
                            </div>
                            <div class="modal-body">
                                <table class="table table-bordered table-hover">
                                    <thead class="table-dark">
                                    <tr>
                                        <th>Mã</th>
                                        <th>Tên sản phẩm</th>
                                        <th>Giá bán</th>
                                        <th>Thao tác</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <%
                                        if(dsSanPham!=null){
                                            for(SanPham sp:dsSanPham){
                                    %>
                                    <tr>
                                        <td><%=sp.getMaSanPham()%></td>
                                        <td><%=sp.getTenSanPham()%></td>
                                        <td><%=String.format("%,.0f",sp.getGia())%></td>
                                        <td class="text-center">
                                            <button
                                                    type="button"
                                                    class="btn btn-success btn-sm"
                                                    onclick="themSanPham(
                                                            '<%=sp.getMaSanPham()%>',
                                                            '<%=sp.getTenSanPham()%>',
                                                            '<%=sp.getGia()%>')">
                                                <i class="fa-solid fa-plus"></i>
                                            </button>
                                        </td>
                                    </tr>
                                    <%
                                            }
                                        }
                                    %>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="text-center mt-4">

                    <button type="submit" class="btn btn-success">
                        <i class="fa-solid fa-floppy-disk"></i>
                        Lưu Phiếu Xuất
                    </button>

                    <a href="${pageContext.request.contextPath}/QuanLyKhoServlet" class="btn btn-secondary">
                        <i class="fa-solid fa-arrow-left"></i>
                        Quay Lại
                    </a>

                </div>

            </form>

        </div>

    </div>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<script>

    let stt=0;

    function themSanPham(ma,ten,gia){

        let tbody=document.getElementById("chiTietXuat");

        for(let r of tbody.rows){

            if(r.cells[1].innerText===ma){

                alert("Sản phẩm đã tồn tại.");

                return;

            }

        }

        stt++;

        let row=tbody.insertRow();

        row.innerHTML=

            "<td>"+stt+"</td>"+

            "<td>"+ma+
            "<input type='hidden' name='maSanPham' value='"+ma+"'></td>"+

            "<td>"+ten+"</td>"+

            "<td><input type='number' class='form-control tonKho' value='0' readonly></td>"+

            "<td><input type='number' class='form-control soLuong' name='soLuong' value='1' min='1' onkeyup='capNhat()' onchange='capNhat()'></td>"+

            "<td><input type='number' class='form-control donGia' name='donGia' value='"+gia+"' readonly></td>"+

            "<td class='thanhTien text-end'>"+gia+"</td>"+

            "<td class='text-center'><button type='button' class='btn btn-danger btn-sm' onclick='xoaDong(this)'><i class='fa-solid fa-trash'></i></button></td>";

        capNhat();

    }

    function xoaDong(btn){

        btn.parentNode.parentNode.remove();

        danhSo();

        capNhat();

    }

    function danhSo(){

        let rows=document.querySelectorAll("#chiTietXuat tr");

        stt=0;

        rows.forEach(function(r){

            stt++;

            r.cells[0].innerText=stt;

        });

    }

    function capNhat(){

        let tong=0;

        let rows=document.querySelectorAll("#chiTietXuat tr");

        rows.forEach(function(r){

            let ton=parseInt(r.querySelector(".tonKho").value)||0;

            let sl=parseInt(r.querySelector(".soLuong").value)||0;

            let gia=parseFloat(r.querySelector(".donGia").value)||0;

            if(sl>ton && ton>0){

                alert("Số lượng xuất vượt quá tồn kho.");

                r.querySelector(".soLuong").value=ton;

                sl=ton;

            }

            let tt=sl*gia;

            r.querySelector(".thanhTien").innerHTML=tt.toLocaleString("vi-VN");

            tong+=tt;

        });

        document.getElementById("tongTien").innerHTML=tong.toLocaleString("vi-VN");

    }

    document.querySelector("form").addEventListener("submit",function(e){

        if(document.querySelectorAll("#chiTietXuat tr").length===0){

            alert("Vui lòng chọn ít nhất một sản phẩm.");

            e.preventDefault();

            return;

        }

    });

</script>

</body>

</html>