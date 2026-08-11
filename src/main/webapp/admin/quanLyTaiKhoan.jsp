
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.TaiKhoan" %>
<%
    List<TaiKhoan> danhSachTaiKhoan = (List<TaiKhoan>) request.getAttribute("danhSachTaiKhoan");
    if (danhSachTaiKhoan == null) {
        danhSachTaiKhoan = new java.util.ArrayList<>();
    }
%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản lý tài khoản</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <style>
        body {
            background-color: #f5f6fa;
        }
        .content {
            padding: 30px;
        }
        .card {
            border: none;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.08);
        }
        .table th {
            background-color: #343a40;
            color: white;
            vertical-align: middle;
        }
        .table td {
            vertical-align: middle;
        }
        .badge {
            padding: 7px 10px;
        }
        .search-box {
            max-width: 350px;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <div class="content">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <div>
                <h3 class="mb-1">Quản lý tài khoản</h3>
                <p class="text-muted mb-0">Quản lý tài khoản người dùng và quản trị viên</p>
            </div>
            <a href="${pageContext.request.contextPath}/quanLyTaiKhoan?action=them"
               class="btn btn-primary">
                <i class="fas fa-plus"></i> Thêm tài khoản
            </a>
        </div>

        <% if (request.getAttribute("thongBao") != null) { %>
        <div class="alert alert-success alert-dismissible fade show">
            <%= request.getAttribute("thongBao") %>
            <button type="button" class="close" data-dismiss="alert">&times;</button>
        </div>
        <% } %>

        <% if (request.getAttribute("loi") != null) { %>
        <div class="alert alert-danger alert-dismissible fade show">
            <%= request.getAttribute("loi") %>
            <button type="button" class="close" data-dismiss="alert">&times;</button>
        </div>
        <% } %>

        <div class="card">
            <div class="card-body">
                <div class="d-flex justify-content-between align-items-center mb-3">
                    <h5 class="mb-0">Danh sách tài khoản</h5>

                    <form action="${pageContext.request.contextPath}/quanLyTaiKhoan"
                          method="get"
                          class="form-inline">
                        <input type="hidden" name="action" value="timKiem">
                        <input type="text"
                               name="tuKhoa"
                               class="form-control mr-2 search-box"
                               placeholder="Tìm tên, email..."
                               value="<%= request.getParameter("tuKhoa") != null ? request.getParameter("tuKhoa") : "" %>">
                        <button type="submit" class="btn btn-secondary">
                            Tìm kiếm
                        </button>
                    </form>
                </div>

                <div class="table-responsive">
                    <table class="table table-bordered table-hover">
                        <thead>
                        <tr>
                            <th class="text-center">Mã</th>
                            <th>Tên đăng nhập</th>
                            <th>Họ tên</th>
                            <th>Email</th>
                            <th>Số điện thoại</th>
                            <th class="text-center">Vai trò</th>
                            <th class="text-center">Mật khẩu</th>
                            <th class="text-center">Trạng thái</th>
                            <th class="text-center">Thao tác</th>
                        </tr>
                        </thead>
                        <tbody>
                        <% if (danhSachTaiKhoan.isEmpty()) { %>
                        <tr>
                            <td colspan="9" class="text-center text-muted">
                                Không có tài khoản nào.
                            </td>
                        </tr>
                        <% } else { %>
                        <% for (TaiKhoan tk : danhSachTaiKhoan) { %>
                        <tr>
                            <td class="text-center">
                                <%= tk.getMaTaiKhoan() %>
                            </td>

                            <td>
                                <strong>
                                    <%= tk.getTenDangNhap() %>
                                </strong>
                            </td>

                            <td>
                                <%= tk.getHoTen() != null ? tk.getHoTen() : "" %>
                            </td>

                            <td>
                                <%= tk.getEmail() != null ? tk.getEmail() : "" %>
                            </td>

                            <td>
                                <%= tk.getSoDienThoai() != null ? tk.getSoDienThoai() : "" %>
                            </td>

                            <td class="text-center">
                                <% if ("ADMIN".equalsIgnoreCase(tk.getVaiTro())) { %>
                                <span class="badge badge-danger">
                                                ADMIN
                                            </span>
                                <% } else { %>
                                <span class="badge badge-info">
                                                KHACH
                                            </span>
                                <% } %>
                            </td>

                            <td class="text-center">
                                        <span class="text-muted">
                                            ********
                                        </span>
                            </td>

                            <td class="text-center">
                                <% if ("HOAT_DONG".equalsIgnoreCase(tk.getTrangThai())) { %>
                                <span class="badge badge-success">
                                                Hoạt động
                                            </span>
                                <% } else { %>
                                <span class="badge badge-secondary">
                                                Đã khóa
                                            </span>
                                <% } %>
                            </td>

                            <td class="text-center">
                                <a href="${pageContext.request.contextPath}/quanLyTaiKhoan?action=sua&id=<%= tk.getMaTaiKhoan() %>"
                                   class="btn btn-sm btn-warning mb-1">
                                    Sửa
                                </a>

                                <% if ("HOAT_DONG".equalsIgnoreCase(tk.getTrangThai())) { %>
                                <a href="${pageContext.request.contextPath}/quanLyTaiKhoan?action=doiTrangThai&id=<%= tk.getMaTaiKhoan() %>&trangThai=BI_KHOA"
                                   class="btn btn-sm btn-secondary mb-1"
                                   onclick="return confirm('Bạn có chắc muốn khóa tài khoản này?');">
                                    Khóa
                                </a>
                                <% } else { %>
                                <a href="${pageContext.request.contextPath}/quanLyTaiKhoan?action=doiTrangThai&id=<%= tk.getMaTaiKhoan() %>&trangThai=HOAT_DONG"
                                   class="btn btn-sm btn-success mb-1">
                                    Mở khóa
                                </a>
                                <% } %>

                                <a href="${pageContext.request.contextPath}/quanLyTaiKhoan?action=xoa&id=<%= tk.getMaTaiKhoan() %>"
                                   class="btn btn-sm btn-danger mb-1"
                                   onclick="return confirm('Bạn có chắc muốn xóa tài khoản này?');">
                                    Xóa
                                </a>
                            </td>
                        </tr>
                        <% } %>
                        <% } %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <div class="mt-3">
            <a href="${pageContext.request.contextPath}/dashboard"
               class="btn btn-outline-secondary">
                ← Quay lại Dashboard
            </a>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
```
