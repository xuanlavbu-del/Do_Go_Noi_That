<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.GioHang" %>
<%@ page import="config.PaymentConfig" %>
<%
    List<GioHang> gioHang =
            (List<GioHang>) request.getAttribute("gioHang");

    Object tongTienObj =
            request.getAttribute("tongTien");

    double tongTien = 0;

    if (tongTienObj != null) {
        tongTien =
                ((Number) tongTienObj).doubleValue();
    }

    String loi =
            (String) request.getAttribute("loi");

    String thongBao =
            (String) request.getAttribute("thongBao");

    Object maDonObj =
            request.getAttribute("maDon");

    String maDon =
            maDonObj != null
                    ? String.valueOf(maDonObj)
                    : null;

    String noiDungThanhToan =
            (String) request.getAttribute(
                    "noiDungThanhToan"
            );

    String phuongThuc =
            (String) request.getAttribute(
                    "phuongThucThanhToan"
            );
%>

<!DOCTYPE html>
<html lang="vi">

<head>

    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width,
                   initial-scale=1.0">

    <title>Thanh toán đơn hàng</title>


    <style>

        * {
            box-sizing: border-box;
        }


        body {
            margin: 0;

            font-family:
                    Arial,
                    Helvetica,
                    sans-serif;

            background: #f5f6f8;

            color: #333;
        }


        .container {
            width: 90%;

            max-width: 1100px;

            margin: 40px auto;
        }


        .title {
            text-align: center;

            margin-bottom: 30px;
        }


        .title h1 {
            margin: 0;

            font-size: 30px;

            color: #222;
        }


        .payment-layout {
            display: grid;

            grid-template-columns:
                1fr 380px;

            gap: 25px;
        }


        .box {
            background: white;

            border-radius: 12px;

            padding: 25px;

            box-shadow:
                    0 3px 15px
                    rgba(0, 0, 0, 0.08);
        }


        .box h2 {
            margin-top: 0;

            margin-bottom: 20px;

            font-size: 21px;
        }


        .form-group {
            margin-bottom: 18px;
        }


        .form-group label {
            display: block;

            margin-bottom: 7px;

            font-weight: bold;
        }


        .form-group input,
        .form-group textarea {
            width: 100%;

            padding: 12px;

            border: 1px solid #ddd;

            border-radius: 7px;

            font-size: 15px;

            outline: none;
        }


        .form-group input:focus,
        .form-group textarea:focus {
            border-color: #777;
        }


        textarea {
            min-height: 100px;

            resize: vertical;
        }


        .payment-method {
            margin-top: 25px;
        }


        .payment-option {
            display: block;

            border: 1px solid #ddd;

            border-radius: 9px;

            padding: 15px;

            margin-bottom: 12px;

            cursor: pointer;

            transition: 0.2s;
        }


        .payment-option:hover {
            border-color: #555;

            background: #fafafa;
        }


        .payment-option input {
            margin-right: 10px;
        }


        .payment-name {
            font-weight: bold;
        }


        .payment-description {
            display: block;

            margin-left: 25px;

            margin-top: 5px;

            color: #777;

            font-size: 14px;
        }


        .cart-item {
            display: flex;

            justify-content:
                    space-between;

            gap: 15px;

            padding: 13px 0;

            border-bottom:
                    1px solid #eee;
        }


        .cart-item:last-child {
            border-bottom: none;
        }


        .product-name {
            font-weight: bold;
        }


        .product-info {
            color: #777;

            font-size: 14px;

            margin-top: 5px;
        }


        .product-price {
            text-align: right;

            white-space: nowrap;

            font-weight: bold;
        }


        .total {
            display: flex;

            justify-content:
                    space-between;

            margin-top: 20px;

            padding-top: 20px;

            border-top:
                    2px solid #eee;

            font-size: 20px;

            font-weight: bold;
        }


        .total-price {
            font-size: 22px;
        }


        .btn-submit {
            width: 100%;

            margin-top: 25px;

            padding: 14px;

            border: none;

            border-radius: 8px;

            background: #222;

            color: white;

            font-size: 17px;

            font-weight: bold;

            cursor: pointer;
        }


        .btn-submit:hover {
            background: #444;
        }


        .alert {
            padding: 15px;

            border-radius: 8px;

            margin-bottom: 20px;

            font-size: 15px;
        }


        .alert-error {
            background: #ffe5e5;

            color: #b00020;
        }


        .alert-success {
            background: #e6f7e9;

            color: #176b2c;
        }


        .payment-result {
            margin-top: 20px;

            padding: 18px;

            border-radius: 9px;

            background: #f5f5f5;
        }


        .payment-result p {
            margin: 8px 0;
        }


        .fixed-value {
            background: #f1f1f1;

            cursor: not-allowed;
        }


        @media (max-width: 800px) {

            .payment-layout {
                grid-template-columns: 1fr;
            }

            .container {
                width: 95%;
            }

        }

    </style>

</head>


<body>

<div class="container">


    <div class="title">

        <h1>
            Thanh toán đơn hàng
        </h1>

    </div>


    <%-- =========================================
         THÔNG BÁO LỖI
         ========================================= --%>

    <% if (loi != null && !loi.isEmpty()) { %>

    <div class="alert alert-error">

        <%= loi %>

    </div>

    <% } %>


    <%-- =========================================
         THÔNG BÁO THÀNH CÔNG
         ========================================= --%>

    <% if (thongBao != null && !thongBao.isEmpty()) { %>

    <div class="alert alert-success">

        <%= thongBao %>

    </div>

    <% } %>


    <%-- =========================================
         SAU KHI ĐẶT HÀNG
         ========================================= --%>

    <% if (maDon != null) { %>

    <div class="box payment-result">

        <h2>
            Đặt hàng thành công
        </h2>


        <p>
            Mã đơn hàng:

            <strong>
                DH<%= maDon %>
            </strong>
        </p>


        <p>
            Tổng tiền:

            <strong>
                <%= String.format(
                        "%,.0f",
                        tongTien
                ) %>
                VNĐ
            </strong>
        </p>


        <% if (noiDungThanhToan != null) { %>

        <p>
            Nội dung thanh toán:

            <strong>
                <%= noiDungThanhToan %>
            </strong>
        </p>

        <% } %>


        <% if ("VBSP".equals(phuongThuc)) { %>


        <%
            String qrUrl =
                    (String) request.getAttribute("qrUrl");
        %>

        <div class="qr-payment">

            <h3>
                Thanh toán bằng VBSP SmartBanking
            </h3>

            <div class="payment-information">

                <p>
                    <strong>Ngân hàng:</strong>
                    <%= PaymentConfig.BANK_NAME %>
                </p>

                <p>
                    <strong>Số tài khoản:</strong>
                    <%= PaymentConfig.ACCOUNT_NUMBER %>
                </p>

                <p>
                    <strong>Chủ tài khoản:</strong>
                    <%= PaymentConfig.ACCOUNT_NAME %>
                </p>

                <p>
                    <strong>Số tiền:</strong>

                    <span class="amount">
                <%= String.format(
                        "%,.0f",
                        tongTien
                ) %> VNĐ
            </span>
                </p>

                <p>
                    <strong>Nội dung chuyển khoản:</strong>

                    <span class="payment-content">
                <%= noiDungThanhToan %>
            </span>
                </p>

            </div>


            <% if (qrUrl != null && !qrUrl.isEmpty()) { %>

            <div class="qr-container">

                <img
                        src="<%= qrUrl %>"
                        alt="QR thanh toán"
                        class="qr-image"
                >

            </div>

            <% } else { %>

            <div class="alert alert-error">
                Không tạo được mã QR thanh toán.
            </div>

            <% } %>


            <div class="qr-instruction">

                <p>
                    1. Mở ứng dụng VBSP SmartBanking.
                </p>

                <p>
                    2. Chọn chức năng quét QR.
                </p>

                <p>
                    3. Quét mã QR ở trên.
                </p>

                <p>
                    4. Kiểm tra thông tin người nhận.
                </p>

                <p>
                    5. Kiểm tra số tiền và nội dung
                    trước khi xác nhận.
                </p>

            </div>



        </div>



        <% } else { %>

        <p>
            Bạn sẽ thanh toán khi
            nhận hàng.
        </p>

        <% } %>

    </div>


    <% } else { %>


    <form
            action="<%= request.getContextPath() %>/thanhToan"
            method="post"
            id="paymentForm"
    >


        <div class="payment-layout">


            <%-- =================================
                 THÔNG TIN GIAO HÀNG
                 ================================= --%>

            <div class="box">

                <h2>
                    Thông tin giao hàng
                </h2>


                <div class="form-group">

                    <label for="diaChiGiao">

                        Địa chỉ giao hàng
                        <span style="color:red">
                                *
                            </span>

                    </label>


                    <input
                            type="text"
                            id="diaChiGiao"
                            name="diaChiGiao"
                            required
                            placeholder="Nhập địa chỉ nhận hàng"
                    >

                </div>


                <div class="form-group">

                    <label for="ghiChu">

                        Ghi chú

                    </label>


                    <textarea
                            id="ghiChu"
                            name="ghiChu"
                            placeholder="Ghi chú cho đơn hàng..."
                    ></textarea>

                </div>


                <%-- =========================
                     PHƯƠNG THỨC THANH TOÁN
                     ========================= --%>

                <div class="payment-method">

                    <h2>
                        Phương thức thanh toán
                    </h2>


                    <label class="payment-option">

                        <input
                                type="radio"
                                name="phuongThucThanhToan"
                                value="COD"
                                checked
                        >

                        <span class="payment-name">

                                Thanh toán khi nhận hàng
                                (COD)

                            </span>


                        <span class="payment-description">

                                Thanh toán trực tiếp
                                cho nhân viên giao hàng.

                            </span>

                    </label>


                    <label class="payment-option">

                        <input
                                type="radio"
                                name="phuongThucThanhToan"
                                value="VBSP"
                        >

                        <span class="payment-name">

                                VBSP SmartBanking

                            </span>


                        <span class="payment-description">

                                Thanh toán bằng cách
                                quét QR qua ứng dụng
                                VBSP SmartBanking.

                            </span>

                    </label>

                </div>


                <button
                        type="submit"
                        class="btn-submit"
                        id="submitButton"
                >

                    ĐẶT HÀNG

                </button>

            </div>


            <%-- =================================
                 TÓM TẮT ĐƠN HÀNG
                 ================================= --%>

            <div class="box">

                <h2>
                    Đơn hàng của bạn
                </h2>


                <% if (gioHang != null) { %>

                <% for (GioHang sp : gioHang) { %>

                <div class="cart-item">


                    <div>

                        <div class="product-name">

                            <%= sp.getTenSanPham() %>

                        </div>


                        <div class="product-info">

                            Số lượng:

                            <%= sp.getSoLuong() %>

                        </div>

                    </div>


                    <div class="product-price">

                        <%= String.format(
                                "%,.0f",
                                sp.getThanhTien()
                        ) %>

                        VNĐ

                    </div>


                </div>

                <% } %>

                <% } %>


                <div class="total">

                        <span>
                            Tổng cộng
                        </span>


                    <span class="total-price">

                            <%= String.format(
                                    "%,.0f",
                                    tongTien
                            ) %>

                            VNĐ

                        </span>

                </div>

            </div>


        </div>

    </form>


    <% } %>

</div>


<script>
    document.addEventListener("DOMContentLoaded", function () {

        const form = document.getElementById("paymentForm");

        if (form) {

            form.addEventListener("submit", function () {

                const button =
                    document.getElementById("submitButton");

                if (button) {
                    button.disabled = true;
                    button.innerText = "ĐANG XỬ LÝ...";
                }

            });

        }

    });
</script>
</body>

</html>