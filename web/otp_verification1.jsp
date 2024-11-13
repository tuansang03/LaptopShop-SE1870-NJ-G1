<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Add Staff</title>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link rel="shortcut icon" href="assets/img/logo/favicon.svg">
        <link rel="stylesheet" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/font-awesome.css">
        <link rel="stylesheet" href="assets/css/animate.css">
        <link rel="stylesheet" href="assets/css/magnific-popup.css">
        <link rel="stylesheet" href="assets/css/meanmenu.css">
        <link rel="stylesheet" href="assets/css/swiper-bundle.min.css">
        <link rel="stylesheet" href="assets/css/nice-select.css">
        <link rel="stylesheet" href="assets/css/main.css">
        <style>
            body {
                background-color: #f8f9fa; /* Màu nền nhẹ */
            }
            .login_box_area {
                padding: 60px 0; /* Thêm padding cho không gian */
            }
            .login_form_inner {
                background-color: #ffffff; /* Nền trắng cho form */
                border-radius: 8px; /* Bo góc */
                box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1); /* Đổ bóng */
                padding: 40px; /* Padding cho form */
            }
            .login_form_inner h3 {
                margin-bottom: 30px; /* Khoảng cách dưới tiêu đề */
                text-align: center; /* Canh giữa tiêu đề */
                font-weight: 600; /* Đậm tiêu đề */
                color: #007bff; /* Màu tiêu đề */
                width: 400px;   
            }
            .form-control {
                border: 1px solid #ced4da; /* Viền nhẹ cho input */
                border-radius: 5px; /* Bo góc cho input */
                transition: border-color 0.2s; /* Hiệu ứng chuyển màu */
            }
            .form-control:focus {
                border-color: #007bff; /* Màu viền khi focus */
                box-shadow: 0 0 5px rgba(0, 123, 255, 0.5); /* Hiệu ứng shadow */
            }
            .button-register {
                background-color: #007bff; /* Màu nền cho nút */
                color: white; /* Màu chữ trên nút */
                border: none; /* Bỏ viền cho nút */
                border-radius: 5px; /* Bo góc cho nút */
                transition: background-color 0.3s; /* Hiệu ứng chuyển màu */
            }
            .button-register:hover {
                background-color: #0056b3; /* Màu nền khi hover */
            }
        </style>
    </head>
    <body>
        <%@include file="sidebar.jsp" %>



        <!--================OTP Verification Box Area =================-->
        <section class="login_box_area section-margin">
            <div class="container">
                <div class="row">
                    <div class="col-lg-6 offset-lg-3" style="width: 400px   ">
                        <div class="login_form_inner" style="position: absolute ; left: 300px           ">
                            <h3>Verify OTP</h3>
                            <% if (request.getAttribute("success") != null) { %>
                            <div class="alert alert-info" role="alert">
                                <%= request.getAttribute("success") %>
                            </div>
                            <% } %>
                            <form class="row login_form" action="VerifyOTPServlet1" method="POST" id="otpForm">
                                <div class="col-md-12 form-group">
                                    <input type="text" class="form-control" id="otp" name="otp" placeholder="Enter OTP" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Enter OTP'" required>
                                </div>
                                <div class="col-md-12 form-group">
                                    <button type="submit" value="submit" class="button button-login w-100">Verify</button>
                                </div>
                            </form>
                            <div class="col-md-12 form-group text-center">
                                <a href="ResendOTP" onclick="comfirm()">Resend OTP</a>

                            </div>
                        </div>
                    </div>

                </div>

        </section>

        <style>
            .list-page {
                display: flex;
                justify-content: center;
                margin-top: 20px;
            }

            .list-page .item {
                display: flex;
                align-items: center;
            }

            .list-page .item a {
                text-decoration: none;
                color: #333;
                padding: 5px 10px;
                margin: 0 2px;
                border: 1px solid #ccc;
                border-radius: 3px;
            }

            .list-page .item a.active {
                background-color: #007bff;
                color: #fff;
                border-color: #007bff;
            }

            .list-page .item a:hover {
                background-color: #f0f0f0;
            }
        </style>
    </body>
</html>
