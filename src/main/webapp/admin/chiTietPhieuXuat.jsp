<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.PhieuXuat"%>
<%@ page import="model.ChiTietPhieuXuat"%>
<%
    PhieuXuat phieuXuat=(PhieuXuat)request.getAttribute("phieuXuat");
    ArrayList<ChiTietPhieuXuat> dsChiTiet=(ArrayList<ChiTietPhieuXuat>)request.getAttribute("dsChiTiet");

    Double tongTien=(Double)request.getAttribute("tongTien");

    if(tongTien==null){
        tongTien=0.0;
    }
%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <title>Chi Tiết Phiếu Xuất</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
    <style>
        body{
            background:#f4f6f9;
        }
        .card{
            border-radius:10px;
            box-shadow:0 2px 10px rgba(0,0,0,.15);
        }
        .card-header{
            font-size:22px;
            font-weight:bold;
        }
        label{
            font-weight:bold;
        }
        .table th{
            background:#343a40;
            color:#fff;
            text-align:center;
        }
        .table td{
            vertical-align:middle;
        }
        .text-money{
            color:#dc3545;
            font-weight:bold;
        }
    </style>
</head>
<body>
<div class="container-fluid mt-4">
    <div class="card">
        <div class="card-header bg-primary text-white">
            <i class="fas fa-file-export"></i>
            CHI TIẾT PHIẾU XUẤT
        </div>
        <div class="card-body">
            <div class="row">
                <div class="col-md-3 mb-3">
                    <label>Mã Phiếu</label>
                    <input type="text" class="form-control" value="<%=phieuXuat.getMaPhieuXuat()%>" readonly>
                </div>
                <div class="col-md-3 mb-3">
                    <label>Ngày Xuất</label>
                    <input type="text" class="form-control" value="<%=phieuXuat.getNgayXuat()%>" readonly>
                </div>
                <div class="col-md-3 mb-3">
                    <label>Khách Hàng</label>
                    <input type="text" class="form-control" value="<%=phieuXuat.getNguoiNhan()%>" readonly>
                </div>
                <div class="col-md-3 mb-3">
                    <label>Tổng Tiền</label>
                    <input type="text" class="form-control text-danger font-weight-bold" value="<%=String.format("%,.0f",tongTien)%> đ" readonly>
                </div>
                <div class="col-md-12 mb-3">
                    <label>Ghi Chú</label>
                    <textarea class="form-control" rows="3" readonly><%=phieuXuat.getGhiChu()==null?"":phieuXuat.getGhiChu()%></textarea>
                </div>
            </div>
            <hr>
            <h5 class="mb-3">
                <i class="fas fa-list"></i>
                Danh Sách Sản Phẩm Xuất
            </h5>
            <table class="table table-bordered table-hover">
                <thead>
                <tr>
                    <th width="60">STT</th>
                    <th>Mã SP</th>
                    <th>Tên Sản Phẩm</th>
                    <th>Kho</th>
                    <th width="100">Số Lượng</th>
                    <th width="140">Đơn Giá</th>
                    <th width="160">Thành Tiền</th>
                </tr>
                </thead>
                <tbody>
<%
    int stt=1;
    if(dsChiTiet!=null){
        for(ChiTietPhieuXuat ct:dsChiTiet){
%>

<tr>
    <td class="text-center"><%=stt++%></td>
    <td class="text-center"><%=ct.getMaSanPham()%></td>
    <td><%=ct.getTenSanPham()%></td>
    <td><%=ct.getTenKho()%></td>
    <td class="text-center"><%=ct.getSoLuong()%></td>
    <td class="text-right"><%=String.format("%,.0f",ct.getDonGia())%> đ</td>
    <td class="text-right text-danger font-weight-bold"><%=String.format("%,.0f",ct.getThanhTien())%> đ</td>
</tr>
<%
    }
}else{
%>
<tr>
    <td colspan="7" class="text-center text-danger">
        Không có dữ liệu chi tiết phiếu xuất.
    </td>
</tr>
<%
    }
%>
                </tbody>
                <tfoot>
                <tr class="table-warning">
                    <th colspan="6" class="text-right">
                        TỔNG TIỀN
                    </th>
                    <th class="text-right text-danger">
                        <%=String.format("%,.0f",tongTien)%> đ
                    </th>
                </tr>
                </tfoot>
            </table>
            <div class="row mt-4">
                <div class="col-md-12 text-center">
                    <a href="<%=request.getContextPath()%>/lichSuXuat" class="btn btn-secondary">
                        <i class="fas fa-arrow-left"></i>
                        Quay Lại
                    </a>
                    <button type="button" class="btn btn-primary ml-2" onclick="window.print()">
                        <i class="fas fa-print"></i>
                        In Phiếu Xuất
                    </button>
                </div>
            </div>

        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<script>
    window.onload=function(){
        let rows=document.querySelectorAll("tbody tr");
        if(rows.length===1&&rows[0].cells.length===1){
            rows[0].classList.add("table-warning");
        }
    };
</script>
</body>
</html>