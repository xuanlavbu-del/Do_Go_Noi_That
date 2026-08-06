<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
<%@ page import="model.Kho"%>
<!DOCTYPE html>
<html>
<head>
    <title>Quản lý kho</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container-fluid mt-4">
    <div class="row">
        <div class="col-md-2">
            <div class="list-group">
                <a href="${pageContext.request.contextPath}/dashboard" class="list-group-item list-group-item-action">Dashboard</a>
                <a href="${pageContext.request.contextPath}/quanLyKho" class="list-group-item list-group-item-action active">Quản lý kho</a>
                <a href="${pageContext.request.contextPath}/chuyenKho" class="list-group-item list-group-item-action">Chuyển kho</a>
                <a href="${pageContext.request.contextPath}/kiemKe" class="list-group-item list-group-item-action">Kiểm kê kho</a>
                <a href="${pageContext.request.contextPath}/lich-su-nhap" class="list-group-item list-group-item-action">Lịch sử nhập</a>
                <a href="${pageContext.request.contextPath}/lich-su-xuat" class="list-group-item list-group-item-action">Lịch sử xuất</a>
                <a href="${pageContext.request.contextPath}/bao-cao-kho" class="list-group-item list-group-item-action">Báo cáo kho</a>
            </div>
        </div>
        <div class="col-md-10">
            <div class="card shadow">
                <div class="card-header bg-primary text-white">
                    <h4 class="mb-0">QUẢN LÝ KHO</h4>
                </div>
                <div class="card-body">

                    <div class="row mb-4">

                        <div class="col-md-4">
                            <div class="card border-primary">
                                <div class="card-body text-center">
                                    <h6>Tổng số kho</h6>
                                    <h3 class="text-primary">
                                        <%=request.getAttribute("tongKho")%>
                                    </h3>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-4">
                            <div class="card border-success">
                                <div class="card-body text-center">
                                    <h6>Tổng tồn kho</h6>
                                    <h3 class="text-success">
                                        <%=request.getAttribute("tongTon")%>
                                    </h3>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-4">
                            <div class="card border-danger">
                                <div class="card-body text-center">
                                    <h6>Sản phẩm sắp hết</h6>
                                    <h3 class="text-danger">
                                        <%=request.getAttribute("sapHet")%>
                                    </h3>
                                </div>
                            </div>
                        </div>

                    </div>

                        <%
String thongBao=(String)request.getAttribute("thongBao");
String loi=(String)request.getAttribute("loi");
if(thongBao!=null){
%>
                    <div class="alert alert-success">
                        <%=thongBao%>
                    </div>
                        <%
}
if(loi!=null){
%>
                    <div class="alert alert-danger">
                        <%=loi%>
                    </div>
                        <%
}
%>

                    <%
                        Kho khoSua=(Kho)request.getAttribute("kho");
                    %>

                    <form action="${pageContext.request.contextPath}/quanLyKho" method="post">
                        <input type="hidden"
                               name="maKho"
                               value="<%=khoSua!=null?khoSua.getMaKho():""%>">
                        <div class="row">

                            <div class="col-md-6">
                                <label>Tên kho</label>
                                <input type="text"
                                       name="tenKho"
                                       class="form-control"
                                       value="<%=khoSua!=null?khoSua.getTenKho():""%>"
                                       required>
                            </div>

                            <div class="col-md-6">
                                <label>Địa chỉ</label>
                                <input type="text"
                                       name="diaChi"
                                       class="form-control"
                                       value="<%=khoSua!=null?khoSua.getDiaChi():""%>"
                                       required>
                            </div>

                        </div>

                        <div class="row mt-3">

                            <div class="col-md-4">
                                <label>Người quản lý</label>
                                <input type="text"
                                       name="nguoiQuanLy"
                                       class="form-control"
                                       value="<%=khoSua!=null?khoSua.getNguoiQuanLy():""%>">
                            </div>

                            <div class="col-md-4">
                                <label>Số điện thoại</label>
                                <input type="text"
                                       name="soDienThoai"
                                       class="form-control"
                                       value="<%=khoSua!=null?khoSua.getSoDienThoai():""%>">
                            </div>

                            <div class="col-md-4">
                                <label>Email</label>
                                <<input type="email"
                                        name="email"
                                        class="form-control"
                                        value="<%=khoSua!=null?khoSua.getEmail():""%>">
                            </div>

                        </div>

                        <div class="row mt-3">

                            <div class="col-md-12">
                                <label>Ghi chú</label>
                                <textarea
                                        name="ghiChu"
                                        class="form-control"
                                        rows="3"><%=khoSua!=null?khoSua.getGhiChu():""%></textarea>
                            </div>

                        </div>

                        <div class="mt-4">

                            <%
                                if(khoSua==null){
                            %>

                            <button type="submit"
                                    name="action"
                                    value="them"
                                    class="btn btn-success">
                                Thêm kho
                            </button>

                            <%
                            }else{
                            %>

                            <button type="submit"
                                    name="action"
                                    value="capNhat"
                                    class="btn btn-warning">
                                Cập nhật kho
                            </button>

                            <a href="${pageContext.request.contextPath}/quanLyKho"
                               class="btn btn-secondary">
                                Hủy
                            </a>

                            <%
                                }
                            %>

                            <button type="reset" class="btn btn-secondary">
                                Làm mới
                            </button>

                        </div>

                        <hr>
                        <h5 class="mb-3">Danh sách kho</h5>

                        <div class="table-responsive">

                            <table class="table table-bordered table-hover table-striped">

                                <thead class="thead-dark">

                                <tr>

                                    <th width="70">Mã</th>

                                    <th>Tên kho</th>

                                    <th>Địa chỉ</th>

                                    <th>Người quản lý</th>

                                    <th>Số điện thoại</th>

                                    <th>Email</th>

                                    <th>Ghi chú</th>

                                    <th width="170">Thao tác</th>

                                </tr>

                                </thead>

                                <tbody>

                                <%

                                    List<Kho> dsKho=(List<Kho>)request.getAttribute("dsKho");

                                    if(dsKho!=null&&!dsKho.isEmpty()){

                                        for(Kho kho:dsKho){

                                %>

                                <tr>

                                    <td class="text-center">

                                        <%=kho.getMaKho()%>

                                    </td>

                                    <td>

                                        <%=kho.getTenKho()%>

                                    </td>

                                    <td>

                                        <%=kho.getDiaChi()%>

                                    </td>

                                    <td>

                                        <%=kho.getNguoiQuanLy()%>

                                    </td>

                                    <td>

                                        <%=kho.getSoDienThoai()%>

                                    </td>

                                    <td>

                                        <%=kho.getEmail()%>

                                    </td>

                                    <td>

                                        <%=kho.getGhiChu()%>

                                    </td>

                                    <td class="text-center">
                                        <a href="${pageContext.request.contextPath}/quanLyKho?action=sua&maKho=<%=kho.getMaKho()%>"
                                           class="btn btn-warning btn-sm">
                                            Sửa
                                        </a>

                                        <a href="${pageContext.request.contextPath}/quanLyKho?action=xoa&maKho=<%=kho.getMaKho()%>"
                                           class="btn btn-danger btn-sm"
                                           onclick="return confirm('Bạn có chắc muốn xóa kho này?');">
                                            Xóa
                                        </a>
                                        </td>

                                </tr>

                                <%

                                    }

                                }else{

                                %>

                                <tr>

                                    <td colspan="8" class="text-center text-danger">

                                        Chưa có dữ liệu kho.

                                    </td>

                                </tr>

                                <%

                                    }

                                %>

                                </tbody>

                                <tfoot>

                                <tr class="table-info">

                                    <th colspan="8">

                                        Tổng số kho:

                                        <%=request.getAttribute("tongKho")%>

                                    </th>

                                </tr>

                                </tfoot>

                            </table>

                        </div>

                        <div class="card mt-4">

                            <div class="card-header bg-info text-white">

                                Thông tin quản lý kho

                            </div>

                            <div class="card-body">

                                <div class="row">

                                    <div class="col-md-4">

                                        <div class="alert alert-primary mb-2">

                                            <strong>Tổng số kho:</strong>

                                            <%=request.getAttribute("tongKho")%>

                                        </div>

                                    </div>

                                    <div class="col-md-4">

                                        <div class="alert alert-success mb-2">

                                            <strong>Tổng tồn kho:</strong>

                                            <%=request.getAttribute("tongTon")%>

                                        </div>

                                    </div>

                                    <div class="col-md-4">

                                        <div class="alert alert-danger mb-2">

                                            <strong>Sản phẩm sắp hết:</strong>

                                            <%=request.getAttribute("sapHet")%>

                                        </div>

                                    </div>

                                </div>

                            </div>

                        </div>

                    </form>
                </div>

            </div>

        </div>

    </div>

</div>

<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

<script>

    function xacNhanXoa(maKho){

        return confirm("Bạn có chắc chắn muốn xóa kho có mã "+maKho+" ?");

    }

    function resetForm(){

        document.querySelector("form").reset();

    }

</script>

</body>

</html>