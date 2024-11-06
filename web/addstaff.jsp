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
    <c:if test="${sessionScope.admin!=null}">
    <%@include file="sidebar.jsp" %>

    <!--================Login Box Area =================-->
    <section class="login_box_area section-margin">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-lg-6">
                    <div class="login_form_inner register_form_inner">
                        <h3>Create an account</h3>
                        <form class="row login_form" action="register1" id="register_form" method="post">
                            <div class="col-md-12 form-group">
                                <input type="text" class="form-control" id="name" name="name" placeholder="Username" required>
                            </div>
                            <div class="col-md-12 form-group">
                                <input type="text" class="form-control" id="firstName" name="firstName" placeholder="First Name" required>
                            </div>
                            <div class="col-md-12 form-group">
                                <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Last Name" required>
                            </div>
                            <div class="col-md-12 form-group">
                                <input type="email" class="form-control" id="email" name="email" placeholder="Email Address" required>
                            </div>
                            <div class="col-md-12 form-group">
                                <input type="password" class="form-control" id="password" name="password" placeholder="Password" required>
                            </div>
                            <div class="col-md-12 form-group">
                                <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" placeholder="Confirm Password" required>
                            </div>
                            <div class="col-md-12 form-group">
                                <button type="submit" value="submit" class="button button-register w-100">Register</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script>
        function validateForm() {
            var password = document.getElementById("password").value;
            var confirmPassword = document.getElementById("confirmPassword").value;

            if (password !== confirmPassword) {
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: 'Mật khẩu xác nhận không khớp!'
                });
                return false; // Ngăn form submit nếu mật khẩu không khớp
            }
            return true; // Cho phép form submit nếu mật khẩu khớp
        }
        <% if (request.getAttribute("error") != null) { %>
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: '<%= request.getAttribute("error") %>'
        });
        <% } %>
    </script>

    <!--================End Login Box Area =================-->

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
    </c:if>
        <c:if test="${sessionScope.sale!=null || sessionScope.user!=null ||(sessionScope.user==null && 
                      sessionScope.sale==null && sessionScope.admin==null) }">
            <%@include file="notallowpage.jsp" %>
        </c:if>
</body>
</html>
