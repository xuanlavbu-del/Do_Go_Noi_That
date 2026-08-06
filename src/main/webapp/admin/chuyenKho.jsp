<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.TonKho" %>
<!DOCTYPE html>
<html>
<head>
    <title>Chuyển kho</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container-fluid mt-4">
    <div class="row">
        <div class="col-md-2">
            <div class="list-group">
                <a href="${pageContext.request.contextPath}/dashboard" class="list-group-item list-group-item-action">Dashboard</a>
                <a href="${pageContext.request.contextPath}/quanLyKho" class="list-group-item list-group-item-action">Quản lý kho</a>
                <a href="${pageContext.request.contextPath}/kiem-ke" class="list-group-item list-group-item-action">Kiểm kê</a>
                <a href="${pageContext.request.contextPath}/chuyen-kho" class="list-group-item list-group-item-action active">Chuyển kho</a>
                <a href="${pageContext.request.contextPath}/lich-su-nhap" class="list-group-item list-group-item-action">Lịch sử nhập</a>
                <a href="${pageContext.request.contextPath}/lich-su-xuat" class="list-group-item list-group-item-action">Lịch sử xuất</a>
                <a href="${pageContext.request.contextPath}/bao-cao-kho" class="list-group-item list-group-item-action">Báo cáo kho</a>
            </div>
        </div>
        <div class="col-md-10">
            <div class="card shadow">
                <div class="card-header bg-primary text-white">
                    <h4 class="mb-0">CHUYỂN HÀNG GIỮA CÁC KHO</h4>
                </div>
                <div class="card-body">
                    <%
                        String thongBao=(String)request.getAttribute("thongBao");
                        String loi=(String)request.getAttribute("loi");
                        if(thongBao!=null){
                    %>
                    <div class="alert alert-success alert-dismissible fade show">
                        <%=thongBao%>
                        <button type="button" class="close" data-dismiss="alert">&times;</button>
                    </div>
                    <%
                        }
                        if(loi!=null){
                    %>
                    <div class="alert alert-danger alert-dismissible fade show">
                        <%=loi%>
                        <button type="button" class="close" data-dismiss="alert">&times;</button>
                    </div>
                    <%
                        }
                    %>
                    <form action="${pageContext.request.contextPath}/chuyen-kho" method="post">
                        <div class="row">
                            <div class="col-md-3">
                                <label>Mã sản phẩm</label>
                                <input type="number" name="maSanPham" class="form-control" required>
                            </div>
                            <div class="col-md-3">
                                <label>Kho nguồn</label>
                                <select name="khoNguon" class="form-control" required>
                                    <option value="">-- Chọn kho --</option>
                                    <%
                                        List<TonKho> ds=(List<TonKho>)request.getAttribute("dsTonKho");
                                        java.util.LinkedHashMap<Integer,String> dsKho=new java.util.LinkedHashMap<>();
                                        if(ds!=null){
                                            for(TonKho tk:ds){
                                                if(!dsKho.containsKey(tk.getMaKho())){
                                                    dsKho.put(tk.getMaKho(),tk.getTenKho());
                                                }
                                            }
                                            for(Integer ma:dsKho.keySet()){
                                    %>
                                    <option value="<%=ma%>"><%=dsKho.get(ma)%></option>
                                    <%
                                            }
                                        }
                                    %>
                                </select>
                            </div>
                            <div class="col-md-3">
                                <label>Kho đích</label>
                                <select name="khoDich" class="form-control" required>
                                    <option value="">-- Chọn kho --</option>
                                    <%
                                        if(dsKho.size()>0){
                                            for(Integer ma:dsKho.keySet()){
                                    %>
                                    <option value="<%=ma%>"><%=dsKho.get(ma)%></option>
                                    <%
                                            }
                                        }
                                    %>
                                </select>
                            </div>
                            <div class="col-md-3">
                                <label>Số lượng</label>
                                <input type="number" name="soLuong" class="form-control" min="1" required>
                            </div>
                        </div>
                        <div class="mt-3">
                            <button type="submit" class="btn btn-success">Chuyển kho</button>
                            <button type="reset" class="btn btn-secondary">Làm mới</button>
                        </div>
                    </form>
                    <hr>
                    <h5 class="mb-3">Danh sách tồn kho hiện tại</h5>
                    <table class="table table-bordered table-hover table-striped">
                        <thead class="thead-dark">
                        <tr>
                            <th>Mã tồn</th>
                            <th>Mã SP</th>
                            <th>Tên sản phẩm</th>
                            <th>Kho</th>
                            <th>Số lượng</th>
                            <th>Đơn giá</th>
                            <th>Giá trị tồn</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            double tongGiaTri=0;
                            if(ds!=null&&ds.size()>0){
                                for(TonKho tk:ds){
                                    double giaTri=tk.getGia()*tk.getSoLuong();
                                    tongGiaTri+=giaTri;
                        %>
                        <tr>
                            <td><%=tk.getMaTon()%></td>
                            <td><%=tk.getMaSanPham()%></td>
                            <td><%=tk.getTenSanPham()%></td>
                            <td><%=tk.getTenKho()%></td>
                            <td class="text-center">
                                <%
                                    if(tk.getSoLuong()==0){
                                %>
                                <span class="badge badge-danger">0</span>
                                <%
                                }else if(tk.getSoLuong()<10){
                                %>
                                <span class="badge badge-warning"><%=tk.getSoLuong()%></span>
                                <%
                                }else{
                                %>
                                <span class="badge badge-success"><%=tk.getSoLuong()%></span>
                                <%
                                    }
                                %>
                            </td>
                            <td><%=String.format("%,.0f",tk.getGia())%> đ</td>
                            <td><%=String.format("%,.0f",giaTri)%> đ</td>
                        </tr>
                        <%
                            }
                        }else{
                        %>
                        <tr>
                            <td colspan="7" class="text-center text-danger">
                                Chưa có dữ liệu tồn kho.
                            </td>
                        </tr>
                        <%
                            }
                        %>
                        </tbody>
                        <tfoot>
                        <tr class="table-info">
                            <th colspan="6" class="text-right">Tổng giá trị tồn kho</th>
                            <th><%=String.format("%,.0f",tongGiaTri)%> đ</th>
                        </tr>
                        </tfoot>
                    </table>
                    <div class="card mt-4">
                        <div class="card-header bg-info text-white">
                            Hướng dẫn chuyển kho
                        </div>
                        <div class="card-body">
                            <ul class="mb-0">
                                <li>Nhập đúng mã sản phẩm cần chuyển.</li>
                                <li>Chọn kho nguồn và kho đích.</li>
                                <li>Số lượng chuyển phải lớn hơn 0.</li>
                                <li>Kho nguồn phải còn đủ hàng.</li>
                                <li>Kho nguồn và kho đích không được trùng nhau.</li>
                                <li>Nếu kho đích chưa có sản phẩm hệ thống sẽ tự tạo tồn kho mới.</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>