<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="model.Kho"%>
<%@ page import="model.SanPham"%>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Nhập kho</title>

    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

</head>

<body>

<div class="container mt-4">

    <h2 class="mb-4">

        Nhập kho

    </h2>

    <form action="nhapKho" method="post">

        <div class="row">

            <div class="col-md-6">

                <label>Kho</label>

                <select
                        class="form-control"
                        name="maKho"
                        required>

                    <%

                        List<Kho> dsKho =
                                (List<Kho>) request.getAttribute("dsKho");

                        if(dsKho!=null){

                            for(Kho k : dsKho){

                    %>

                    <option
                            value="<%=k.getMaKho()%>">

                        <%=k.getTenKho()%>

                    </option>

                    <%

                            }

                        }

                    %>

                </select>

            </div>

            <div class="col-md-6">

                <label>Sản phẩm</label>

                <select
                        class="form-control"
                        name="maSanPham"
                        required>

                    <%

                        List<SanPham> dsSanPham =
                                (List<SanPham>) request.getAttribute("dsSanPham");

                        if(dsSanPham!=null){

                            for(SanPham sp : dsSanPham){

                    %>

                    <option
                            value="<%=sp.getMaSanPham()%>">

                        <%=sp.getTenSanPham()%>

                    </option>

                    <%

                            }

                        }

                    %>

                </select>

            </div>

        </div>

        <div class="row mt-3">

            <div class="col-md-6">

                <label>Số lượng</label>

                <input
                        type="number"
                        name="soLuong"
                        class="form-control"
                        min="1"
                        required>

            </div>

            <div class="col-md-6">

                <label>Đơn giá</label>

                <input
                        type="number"
                        name="donGia"
                        class="form-control"
                        min="0"
                        step="1000"
                        required>

            </div>

        </div>

        <div class="mt-3">

            <label>Ghi chú</label>

            <textarea

                    name="ghiChu"

                    class="form-control"

                    rows="4">

</textarea>

        </div>

        <div class="mt-4">

            <button
                    type="submit"
                    class="btn btn-success">

                Lưu phiếu nhập

            </button>

            <a
                    href="quanLyKho"
                    class="btn btn-secondary">

                Quay lại

            </a>

        </div>

    </form>
    <script>

        document.querySelector("form")

            .addEventListener("submit",

                function(e){

                    let sl=

                        document.getElementsByName("soLuong")[0].value;

                    if(sl<=0){

                        alert("Số lượng phải lớn hơn 0");

                        e.preventDefault();

                    }

                });

    </script>

</div>

</body>

</html>