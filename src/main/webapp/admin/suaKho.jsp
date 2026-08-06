<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="model.Kho"%>
<%
    Kho kho=(Kho)request.getAttribute("kho");
    if(kho==null){
        response.sendRedirect(request.getContextPath()+"/quanLyKho");
        return;
    }
%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sửa Kho</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
    <style>
        body{
            background:#f4f6f9;
        }
        .card{
            border-radius:12px;
            box-shadow:0 3px 12px rgba(0,0,0,.15);
        }
        .card-header{
            font-size:22px;
            font-weight:bold;
        }
        label{
            font-weight:600;
        }
        .form-control:focus{
            box-shadow:none;
            border-color:#0d6efd;
        }
        .btn{
            min-width:120px;
        }
        .required{
            color:red;
        }
    </style>
</head>
<body>

<div class="container-fluid mt-4">

    <div class="row">

        <div class="col-md-12">

            <div class="card">

                <div class="card-header bg-warning text-dark">

                    <i class="fa-solid fa-warehouse"></i>

                    SỬA THÔNG TIN KHO

                </div>

                <div class="card-body">
                    <form action="${pageContext.request.contextPath}/admin/suaKho" method="post">

                        <input type="hidden" name="maKho" value="<%=kho.getMaKho()%>">

                        <div class="row">

                            <div class="col-md-6 mb-3">

                                <label>Mã Kho</label>

                                <input
                                        type="text"
                                        class="form-control"
                                        value="<%=kho.getMaKho()%>"
                                        readonly>

                            </div>

                            <div class="col-md-6 mb-3">

                                <label>Tên Kho <span class="required">*</span></label>

                                <input
                                        type="text"
                                        name="tenKho"
                                        class="form-control"
                                        maxlength="100"
                                        required
                                        value="<%=kho.getTenKho()%>">

                            </div>

                            <div class="col-md-6 mb-3">

                                <label>Địa Chỉ <span class="required">*</span></label>

                                <input
                                        type="text"
                                        name="diaChi"
                                        class="form-control"
                                        maxlength="255"
                                        required
                                        value="<%=kho.getDiaChi()%>">

                            </div>

                            <div class="col-md-6 mb-3">

                                <label>Người Quản Lý</label>

                                <input
                                        type="text"
                                        name="nguoiQuanLy"
                                        class="form-control"
                                        maxlength="100"
                                        value="<%=kho.getNguoiQuanLy()%>">

                            </div>

                            <div class="col-md-6 mb-3">

                                <label>Số Điện Thoại</label>

                                <input
                                        type="text"
                                        name="soDienThoai"
                                        class="form-control"
                                        maxlength="15"
                                        value="<%=kho.getSoDienThoai()%>">

                            </div>

                            <div class="col-md-6 mb-3">

                                <label>Email</label>

                                <input
                                        type="email"
                                        name="email"
                                        class="form-control"
                                        maxlength="100"
                                        value="<%=kho.getEmail()%>">

                            </div>

                            <div class="col-md-12 mb-3">

                                <label>Ghi Chú</label>

                                <textarea
                                        name="ghiChu"
                                        class="form-control"
                                        rows="4"><%=kho.getGhiChu()==null?"":kho.getGhiChu()%></textarea>

                            </div>
                            <div class="row">

                                <div class="col-md-12 text-center mt-3">

                                    <button type="submit" class="btn btn-success me-2">
                                        <i class="fa-solid fa-floppy-disk"></i>
                                        Lưu Thay Đổi
                                    </button>
                                    <a href="${pageContext.request.contextPath}/quanLyKho"
                                       class="btn btn-secondary me-2">
                                        <i class="fa-solid fa-arrow-left"></i>
                                        Quay Lại
                                    </a>

                                    <button type="reset" class="btn btn-danger">
                                        <i class="fa-solid fa-rotate-left"></i>
                                        Khôi Phục
                                    </button>

                                </div>

                            </div>
                        </div>
                    </form>

                </div>

            </div>

        </div>

    </div>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<script>

    const form=document.querySelector("form");

    form.addEventListener("submit",function(e){

        let tenKho=document.getElementsByName("tenKho")[0].value.trim();

        let diaChi=document.getElementsByName("diaChi")[0].value.trim();

        let nguoiQuanLy=document.getElementsByName("nguoiQuanLy")[0].value.trim();

        let soDienThoai=document.getElementsByName("soDienThoai")[0].value.trim();

        let email=document.getElementsByName("email")[0].value.trim();

        if(tenKho===""){

            alert("Tên kho không được để trống.");

            e.preventDefault();

            return;

        }

        if(diaChi===""){

            alert("Địa chỉ không được để trống.");

            e.preventDefault();

            return;

        }

        if(soDienThoai!==""){

            const phone=/^[0-9]{9,11}$/;

            if(!phone.test(soDienThoai)){

                alert("Số điện thoại không hợp lệ.");

                e.preventDefault();

                return;

            }

        }

        if(email!==""){

            const mail=/^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/;

            if(!mail.test(email)){

                alert("Email không hợp lệ.");

                e.preventDefault();

                return;

            }

        }

        if(nguoiQuanLy.length>100){

            alert("Tên người quản lý quá dài.");

            e.preventDefault();

            return;

        }

        if(!confirm("Bạn có chắc muốn cập nhật thông tin kho?")){

            e.preventDefault();

            return;

        }

    });

</script>

</body>

</html>