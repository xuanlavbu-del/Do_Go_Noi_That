<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
<%@ page import="model.Kho"%>
<%@ page import="model.TonKho"%>
<%@ page import="model.KiemKe"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Kiểm kê kho</title>

    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

    <style>

        body{
            background:#f5f6fa;
        }

        .card{
            border-radius:10px;
            box-shadow:0 2px 8px rgba(0,0,0,.1);
        }

        .table td,
        .table th{
            vertical-align:middle;
        }

    </style>

</head>

<body>

<div class="container-fluid mt-4">

    <div class="row">

        <div class="col-md-2">

            <div class="list-group">

                <a href="${pageContext.request.contextPath}/dashboard"
                   class="list-group-item list-group-item-action">
                    Dashboard
                </a>

                <a href="${pageContext.request.contextPath}/quanLyKho"
                   class="list-group-item list-group-item-action">
                    Quản lý kho
                </a>

                <a href="${pageContext.request.contextPath}/kiemKe"
                   class="list-group-item list-group-item-action active">
                    Kiểm kê kho
                </a>

                <a href="${pageContext.request.contextPath}/chuyenKho"
                   class="list-group-item list-group-item-action">
                    Chuyển kho
                </a>

                <a href="${pageContext.request.contextPath}/lich-su-nhap"
                   class="list-group-item list-group-item-action">
                    Lịch sử nhập
                </a>

                <a href="${pageContext.request.contextPath}/lich-su-xuat"
                   class="list-group-item list-group-item-action">
                    Lịch sử xuất
                </a>

                <a href="${pageContext.request.contextPath}/bao-cao-kho"
                   class="list-group-item list-group-item-action">
                    Báo cáo kho
                </a>

            </div>

        </div>

        <div class="col-md-10">

            <div class="row mb-3">

                <div class="col-md-4">

                    <div class="card border-primary">

                        <div class="card-body text-center">

                            <h5>Tổng phiếu kiểm kê</h5>

                            <h2 class="text-primary">

                                <%=request.getAttribute("tongPhieu")==null?0:request.getAttribute("tongPhieu")%>

                            </h2>

                        </div>

                    </div>

                </div>

                <div class="col-md-4">

                    <div class="card border-success">

                        <div class="card-body text-center">

                            <h5>Sản phẩm kiểm kê</h5>

                            <h2 class="text-success">

                                <%=request.getAttribute("tongSanPham")==null?0:request.getAttribute("tongSanPham")%>

                            </h2>

                        </div>

                    </div>

                </div>

                <div class="col-md-4">

                    <div class="card border-danger">

                        <div class="card-body text-center">

                            <h5>Tổng chênh lệch</h5>

                            <h2 class="text-danger">

                                <%=request.getAttribute("tongChenhLech")==null?0:request.getAttribute("tongChenhLech")%>

                            </h2>

                        </div>

                    </div>

                </div>

            </div>

            <div class="card">

                <div class="card-header bg-primary text-white">

                    <h4 class="mb-0">

                        LẬP PHIẾU KIỂM KÊ

                    </h4>

                </div>

                <div class="card-body">

                        <%
                        if(request.getAttribute("thongBao")!=null){
                    %>

                    <div class="alert alert-success">

                        <%=request.getAttribute("thongBao")%>

                    </div>

                        <%
                        }
                    %>

                        <%
                        if(request.getAttribute("loi")!=null){
                    %>

                    <div class="alert alert-danger">

                        <%=request.getAttribute("loi")%>

                    </div>

                        <%
                        }
                    %>

                    <form method="get"
                          action="${pageContext.request.contextPath}/kiemKe">

                        <div class="row">

                            <div class="col-md-4">

                                <label>Kho</label>

                                <select
                                        name="maKho"
                                        class="form-control"
                                        onchange="this.form.submit()"
                                        required>

                                    <option value="">

                                        -- Chọn kho --

                                    </option>

                                    <%

                                        List<Kho> dsKho=
                                                (List<Kho>)request.getAttribute("dsKho");

                                        if(dsKho!=null){

                                            for(Kho kho:dsKho){

                                    %>

                                    <option
                                            value="<%=kho.getMaKho()%>"
                                            <%=request.getAttribute("maKhoDangChon")!=null &&
                                                    ((Integer)request.getAttribute("maKhoDangChon")==kho.getMaKho())
                                                    ?"selected":""
                                            %>>

                                        <%=kho.getTenKho()%>

                                    </option>

                                    <%

                                            }

                                        }

                                    %>

                                </select>
                            </div>
                            </div>
                        </form>
                            <div class="col-md-4">

                                <label>Người kiểm kê</label>

                                <input
                                        type="text"
                                        name="nguoiKiemKe"
                                        class="form-control"
                                        required>

                            </div>

                            <div class="col-md-4">

                                <label>Ghi chú</label>

                                <input
                                        type="text"
                                        name="ghiChu"
                                        class="form-control">

                            </div>

                        </div>

                        <hr>


                <form method="post"
                      action="${pageContext.request.contextPath}/kiem-ke?action=luu">

                    <input
                            type="hidden"
                            name="maKho"
                            value="${maKhoDangChon}">
                        <h5 class="mb-3">Danh sách sản phẩm kiểm kê</h5>

                        <table class="table table-bordered table-hover">

                            <thead class="thead-dark">

                            <tr>

                                <th width="5%">STT</th>

                                <th width="10%">Mã SP</th>

                                <th>Tên sản phẩm</th>

                                <th>Kho</th>

                                <th width="12%">Tồn hệ thống</th>

                                <th width="15%">Tồn thực tế</th>

                                <th width="12%">Chênh lệch</th>

                            </tr>

                            </thead>

                            <tbody>

                            <%

                                List<TonKho> dsTonKho =
                                        (List<TonKho>) request.getAttribute("dsTonKho");

                                int stt = 1;

                                if(dsTonKho != null){

                                    for(TonKho tk : dsTonKho){

                            %>

                            <tr>

                                <td>

                                    <%=stt++%>

                                </td>

                                <td>

                                    <%=tk.getMaSanPham()%>

                                    <input
                                            type="hidden"
                                            name="maSanPham[]"
                                            value="<%=tk.getMaSanPham()%>">

                                </td>

                                <td>

                                    <%=tk.getTenSanPham()%>

                                </td>

                                <td>

                                    <%=tk.getTenKho()%>

                                </td>

                                <td>

                                    <%=tk.getSoLuong()%>

                                    <input
                                            type="hidden"
                                            name="tonHeThong[]"
                                            value="<%=tk.getSoLuong()%>">

                                </td>

                                <td>

                                    <input
                                            type="number"
                                            class="form-control ton-thuc-te"
                                            name="tonThucTe[]"
                                            value="<%=tk.getSoLuong()%>"
                                            min="0"
                                            required>

                                </td>

                                <td>

            <span class="chenh-lech font-weight-bold text-primary">

                0

            </span>

                                </td>

                            </tr>

                            <%

                                    }

                                }

                            %>

                            </tbody>

                        </table>

                        <div class="text-right">

                            <button
                                    type="submit"
                                    class="btn btn-success">

                                Lưu phiếu kiểm kê

                            </button>
                        </div>
                </form>
                            <button
                                    type="reset"
                                    class="btn btn-secondary">

                                Làm mới

                            </button>

                        </div>

                    </form>

                </div>

            </div>

            <br>

            <div class="card mt-4">

                <div class="card-header bg-info text-white">

                    <h5 class="mb-0">

                        LỊCH SỬ PHIẾU KIỂM KÊ

                    </h5>

                </div>

                <div class="card-body">

                    <table class="table table-bordered table-striped table-hover">

                        <thead class="thead-dark">

                        <tr>

                            <th>Mã phiếu</th>

                            <th>Kho</th>

                            <th>Ngày kiểm kê</th>

                            <th>Người kiểm kê</th>

                            <th>Ghi chú</th>

                            <th width="180">Thao tác</th>

                        </tr>

                        </thead>

                        <tbody>

                        <%

                            List<KiemKe> dsKiemKe =
                                    (List<KiemKe>)request.getAttribute("dsKiemKe");

                            if(dsKiemKe!=null){

                                for(KiemKe kk:dsKiemKe){

                        %>

                        <tr>

                            <td>

                                <%=kk.getMaKiemKe()%>

                            </td>

                            <td>

                                <%=kk.getTenKho()%>

                            </td>

                            <td>

                                <%=kk.getNgayKiemKe()%>

                            </td>

                            <td>

                                <%=kk.getNguoiKiemKe()%>

                            </td>

                            <td>

                                <%=kk.getGhiChu()%>

                            </td>

                            <td>

                                <a
                                        href="${pageContext.request.contextPath}/kiemke?action=chiTiet&maKiemKe=<%=kk.getMaKiemKe()%>"
                                        class="btn btn-primary btn-sm">

                                    Chi tiết

                                </a>

                                <a
                                        href="${pageContext.request.contextPath}/kiemKe?action=xoa&maKiemKe=<%=kk.getMaKiemKe()%>"
                                        class="btn btn-danger btn-sm"
                                        onclick="return confirm('Bạn có chắc muốn xóa phiếu kiểm kê này?')">

                                    Xóa

                                </a>

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

            <%

                if(request.getAttribute("dsChiTiet")!=null){

                    List<model.ChiTietKiemKe> dsChiTiet =
                            (List<model.ChiTietKiemKe>)request.getAttribute("dsChiTiet");

            %>

            <div class="card mt-4">

                <div class="card-header bg-secondary text-white">

                    <h5 class="mb-0">

                        CHI TIẾT PHIẾU KIỂM KÊ

                    </h5>

                </div>

                <div class="card-body">

                    <table class="table table-bordered">

                        <thead class="thead-light">

                        <tr>

                            <th>Mã SP</th>

                            <th>Tên sản phẩm</th>

                            <th>Tồn hệ thống</th>

                            <th>Tồn thực tế</th>

                            <th>Chênh lệch</th>

                        </tr>

                        </thead>

                        <tbody>

                        <%

                            for(model.ChiTietKiemKe ct : dsChiTiet){

                        %>

                        <tr>

                            <td>

                                <%=ct.getMaSanPham()%>

                            </td>

                            <td>

                                <%=ct.getTenSanPham()%>

                            </td>

                            <td>

                                <%=ct.getTonHeThong()%>

                            </td>

                            <td>

                                <%=ct.getTonThucTe()%>

                            </td>

                            <td>

                                <%

                                    if(ct.getChenhLech()>0){

                                %>

                                <span class="text-success">

                        +<%=ct.getChenhLech()%>

                    </span>

                                <%

                                }else if(ct.getChenhLech()<0){

                                %>

                                <span class="text-danger">

                        <%=ct.getChenhLech()%>

                    </span>

                                <%

                                }else{

                                %>

                                0

                                <%

                                    }

                                %>

                            </td>

                        </tr>

                        <%

                            }

                        %>

                        </tbody>

                    </table>

                </div>

            </div>

            <%

                }

            %>

        </div>

    </div>

</div>

<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

<script>

    document.querySelectorAll("tbody tr").forEach(function(row){

        let tonHeThongInput=row.querySelector("input[name='tonHeThong']");

        let tonThucTeInput=row.querySelector("input[name='tonThucTe']");

        let chenhLech=row.querySelector(".chenh-lech");

        if(tonHeThongInput&&tonThucTeInput&&chenhLech){

            function tinh(){

                let ht=parseInt(tonHeThongInput.value)||0;

                let tt=parseInt(tonThucTeInput.value)||0;

                let cl=tt-ht;

                chenhLech.innerHTML=cl;

                if(cl>0){

                    chenhLech.className="chenh-lech text-success font-weight-bold";

                    chenhLech.innerHTML="+"+cl;

                }else if(cl<0){

                    chenhLech.className="chenh-lech text-danger font-weight-bold";

                }else{

                    chenhLech.className="chenh-lech text-primary font-weight-bold";

                }

            }

            tonThucTeInput.addEventListener("keyup",tinh);

            tonThucTeInput.addEventListener("change",tinh);

        }

    });

</script>

</body>

</html>