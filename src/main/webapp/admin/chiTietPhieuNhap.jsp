<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.PhieuNhap"%>
<%@ page import="model.ChiTietPhieuNhap"%>
<%
    PhieuNhap phieuNhap=(PhieuNhap)request.getAttribute("phieuNhap");
    ArrayList<ChiTietPhieuNhap> dsChiTiet=(ArrayList<ChiTietPhieuNhap>)request.getAttribute("dsChiTiet");
    Double tongTien=(Double)request.getAttribute("tongTien");
    if(tongTien==null){
        tongTien=0.0;
    }
%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Chi tiết phiếu nhập</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
    <style>
        body{background:#f4f6f9;}
        .card{box-shadow:0 2px 6px rgba(0,0,0,.15);}
        .table th,.table td{vertical-align:middle;}
    </style>
</head>
<body>
<div class="container-fluid mt-4">
    <div class="row">
        <div class="col-md-12">
            <div class="card">
                <div class="card-header bg-primary text-white">
                    <h4 class="mb-0">
                        <i class="fas fa-file-import"></i>
                        Chi tiết phiếu nhập
                    </h4>
                </div>
                <div class="card-body">
                    <div class="row mb-3">
                        <div class="col-md-3">
                            <label><b>Mã phiếu</b></label>
                            <input type="text" class="form-control" value="<%=phieuNhap.getMaPhieuNhap()%>" readonly>
                        </div>
                        <div class="col-md-3">
                            <label><b>Ngày nhập</b></label>
                            <input type="text" class="form-control" value="<%=phieuNhap.getNgayNhap()%>" readonly>
                        </div>
                        <div class="col-md-3">
                            <label><b>Nhà cung cấp</b></label>
                            <input type="text" class="form-control" value="<%=phieuNhap.getNhaCungCap()%>" readonly>
                        </div>
                        <div class="col-md-3">
                            <label><b>Ghi chú</b></label>
                            <input type="text" class="form-control" value="<%=phieuNhap.getGhiChu()==null?"":phieuNhap.getGhiChu()%>" readonly>
                        </div>
                    </div>
                    <hr>
                    <div class="table-responsive">
                        <table class="table table-bordered table-hover">
                            <thead class="thead-dark">
                            <tr>
                                <th width="60">STT</th>
                                <th width="120">Mã SP</th>
                                <th>Tên sản phẩm</th>
                                <th width="120">Số lượng</th>
                                <th width="150">Đơn giá</th>
                                <th width="170">Thành tiền</th>
                            </tr>
                            </thead>
                            <tbody>
<%
    int stt=1;
    if(dsChiTiet!=null){
        for(ChiTietPhieuNhap ct:dsChiTiet){
%>

<tr>
    <td><%=stt++%></td>
    <td><%=ct.getMaSanPham()%></td>
    <td><%=ct.getTenSanPham()%></td>
    <td class="text-center"><%=ct.getSoLuong()%></td>
    <td class="text-right"><%=String.format("%,.0f",ct.getDonGia())%> VNĐ</td>
    <td class="text-right"><%=String.format("%,.0f",ct.getThanhTien())%> VNĐ</td>
</tr>
<%
    }
}else{
%>
<tr>
    <td colspan="6" class="text-center text-danger">
        Không có dữ liệu chi tiết phiếu nhập.
    </td>
</tr>
<%
    }
%>
                            </tbody>
                            <tfoot class="bg-light">
                            <tr>
                                <th colspan="5" class="text-right">
                                    Tổng tiền
                                </th>
                                <th class="text-right text-danger">
                                    <%=String.format("%,.0f",tongTien)%> VNĐ
                                </th>
                            </tr>
                            </tfoot>
                        </table>
                    </div>

                    <div class="row mt-3">
                        <div class="col-md-6 text-left">
                            <a href="${pageContext.request.contextPath}/lich-su-nhap" class="btn btn-secondary">
                                <i class="fas fa-arrow-left"></i>
                                Quay lại
                            </a>
                        </div>
                        <div class="col-md-6 text-right">
                            <button type="button" class="btn btn-primary" onclick="window.print()">
                                <i class="fas fa-print"></i>
                                In phiếu nhập
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>