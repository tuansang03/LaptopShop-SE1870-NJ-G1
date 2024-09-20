<%-- 
    Document   : otp_verification
    Created on : Sep 16, 2024
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Aroma Shop - OTP Verification</title>
        <link rel="icon" href="img/Fevicon.png" type="image/png">
        <link rel="stylesheet" href="vendors/bootstrap/bootstrap.min.css">
        <link rel="stylesheet" href="vendors/fontawesome/css/all.min.css">
        <link rel="stylesheet" href="vendors/themify-icons/themify-icons.css">
        <link rel="stylesheet" href="vendors/linericon/style.css">
        <link rel="stylesheet" href="vendors/owl-carousel/owl.theme.default.min.css">
        <link rel="stylesheet" href="vendors/owl-carousel/owl.carousel.min.css">
        <link rel="stylesheet" href="vendors/nice-select/nice-select.css">
        <link rel="stylesheet" href="vendors/nouislider/nouislider.min.css">
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <!--================ Start Header Menu Area =================-->
        <%@include file="header.jsp" %>
        <!--================ End Header Menu Area =================-->

        <!-- ================ start banner area ================= -->  
        <section class="blog-banner-area" id="category">
            <div class="container h-100">
                <div class="blog-banner">
                    <div class="text-center">
                        <h1>OTP Verification</h1>
                        <nav aria-label="breadcrumb" class="banner-breadcrumb">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="#">Home</a></li>
                                <li class="breadcrumb-item active" aria-current="page">OTP Verification</li>
                            </ol>
                        </nav>
                    </div>
                </div>
            </div>
        </section>
        <!-- ================ end banner area ================= -->

        <!--================OTP Verification Box Area =================-->
        <section class="login_box_area section-margin">
            <div class="container">
                <div class="row">
                    <div class="col-lg-6 offset-lg-3">
                        <div class="login_form_inner">
                            <h3>Verify OTP</h3>
                            <% if (request.getAttribute("message") != null) { %>
                            <div class="alert alert-info" role="alert">
                                <%= request.getAttribute("message") %>
                            </div>
                            <% } %>
                            <form class="row login_form" action="VerifyOTPServlet" method="POST" id="otpForm">
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

        <!--================ Start footer Area  =================-->  
        <%@include file="footer.jsp" %>
        <!--================ End footer Area  =================-->

        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

        <script>
                                    // Hiển thị thông báo nếu OTP sai
                                    const comfirm = () => {
                                        if (confirm("Do you want to resend the OTP?")) {

                                            window.location.href = "ResendOTP";
                                        }
                                    };
            <% if (request.getAttribute("error") != null) { %>
                                    Swal.fire({
                                        icon: 'error',
                                        title: 'Lỗi!',
                                        text: '<%= request.getAttribute("error") %>',
                                        confirmButtonText: 'OK'
                                    });
            <% } %>

                                    // Hiển thị thông báo nếu gửi OTP thành công
            <% if (request.getAttribute("otpresendmsg") != null) { %>
                                    Swal.fire({
                                        icon: 'success',
                                        title: 'Thành công!',
                                        text: '<%= request.getAttribute("message") %>',
                                        confirmButtonText: 'OK'
                                    });
            <% } %>
        </script>

        <script src="vendors/jquery/jquery-3.2.1.min.js"></script>
        <script src="vendors/bootstrap/bootstrap.bundle.min.js"></script>
        <script src="vendors/skrollr.min.js"></script>
        <script src="vendors/owl-carousel/owl.carousel.min.js"></script>
        <script src="vendors/nice-select/jquery.nice-select.min.js"></script>
        <script src="vendors/jquery.ajaxchimp.min.js"></script>
        <script src="vendors/mail-script.js"></script>
        <script src="js/main.js"></script>
    </body>
</html>
