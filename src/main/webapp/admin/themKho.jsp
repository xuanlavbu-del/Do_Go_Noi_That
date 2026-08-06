<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thêm Kho</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" rel="stylesheet">
    <style>
        body{background:#f4f6f9;}
        .card{border-radius:10px;box-shadow:0 3px 10px rgba(0,0,0,.15);}
        .card-header{font-size:22px;font-weight:bold;}
        .form-label{font-weight:600;}
        .required{color:red;}
    </style>
</head>
<body>
<div class="container-fluid mt-4">
    <div class="row justify-content-center">
        <div class="col-lg-10">
            <div class="card">
                <div class="card-header bg-success text-white">
                    <i class="fa-solid fa-warehouse"></i>
                    THÊM KHO MỚI
                </div>
                <div class="card-body">
                    <form action="${pageContext.request.contextPath}/ThemKhoServlet" method="post" id="formKho">
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label class="form-label">Tên Kho <span class="required">*</span></label>
                                <input type="text" class="form-control" id="tenKho" name="tenKho" maxlength="100" required>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label class="form-label">Địa Chỉ <span class="required">*</span></label>
                                <input type="text" class="form-control" id="diaChi" name="diaChi" maxlength="255" required>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label class="form-label">Người Quản Lý</label>
                                <input type="text" class="form-control" id="nguoiQuanLy" name="nguoiQuanLy" maxlength="100">
                            </div>
                            <div class="col-md-6 mb-3">
                                <label class="form-label">Số Điện Thoại</label>
                                <input type="text" class="form-control" id="soDienThoai" name="soDienThoai" maxlength="20">
                            </div>
                            <div class="col-md-6 mb-3">
                                <label class="form-label">Email</label>
                                <input type="email" class="form-control" id="email" name="email" maxlength="100">
                            </div>

                            <div class="col-md-12 mb-3">
                                <label class="form-label">Ghi Chú</label>
                                <textarea class="form-control" id="ghiChu" name="ghiChu" rows="4"></textarea>
                            </div>
                            <div class="row">
                                <div class="col-md-12 text-center mt-3">
                                    <button type="submit" class="btn btn-success me-2">
                                        <i class="fa-solid fa-floppy-disk"></i>
                                        Lưu Kho
                                    </button>
                                    <button type="reset" class="btn btn-warning me-2">
                                        <i class="fa-solid fa-rotate-right"></i>
                                        Làm Mới
                                    </button>
                                    <a href="${pageContext.request.contextPath}/QuanLyKhoServlet" class="btn btn-secondary">
                                        <i class="fa-solid fa-arrow-left"></i>
                                        Quay Lại
                                    </a>
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
    const form=document.getElementById("formKho");
    form.addEventListener("submit",function(e){
        let tenKho=document.getElementById("tenKho").value.trim();
        let diaChi=document.getElementById("diaChi").value.trim();
        let nguoiQuanLy=document.getElementById("nguoiQuanLy").value.trim();
        let soDienThoai=document.getElementById("soDienThoai").value.trim();
        let email=document.getElementById("email").value.trim();
        if(tenKho.length<2){
            alert("Tên kho phải có ít nhất 2 ký tự.");
            document.getElementById("tenKho").focus();
            e.preventDefault();
            return;
        }
        if(diaChi.length<5){
            alert("Địa chỉ không hợp lệ.");
            document.getElementById("diaChi").focus();
            e.preventDefault();
            return;
        }
        if(nguoiQuanLy.length>100){
            alert("Tên người quản lý quá dài.");
            document.getElementById("nguoiQuanLy").focus();
            e.preventDefault();
            return;
        }
        if(soDienThoai!==""){
            let phone=/^(0|\+84)[0-9]{9,10}$/;
            if(!phone.test(soDienThoai)){
                alert("Số điện thoại không hợp lệ.");
                document.getElementById("soDienThoai").focus();
                e.preventDefault();
                return;
            }
        }
        if(email!==""){
            let mail=/^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/;
            if(!mail.test(email)){
                alert("Email không hợp lệ.");
                document.getElementById("email").focus();
                e.preventDefault();
                return;
            }
        }
        if(!confirm("Bạn có muốn thêm kho mới không?")){
            e.preventDefault();
        }
    });
</script>
</body>
</html>