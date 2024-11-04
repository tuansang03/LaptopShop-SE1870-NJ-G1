<%-- 
    Document   : forgotpassword
    Created on : Oct 22, 2024
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Aroma Shop - Forgot Password</title>
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
                    <h1>Forgot Password</h1>
                    <nav aria-label="breadcrumb" class="banner-breadcrumb">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="home">Home</a></li>
                            <li class="breadcrumb-item active" aria-current="page">Forgot Password</li>
                        </ol>
                    </nav>
                </div>
            </div>
        </div>
    </section>
    <!-- ================ end banner area ================= -->

    <!--================Forgot Password Box Area =================-->
    <section class="login_box_area section-margin">
        <div class="container">
            <div class="row">
                <div class="col-lg-6">
                    <div class="login_box_img">
                        <div class="hover">
                            <h4>Remember your password?</h4>
                            <p>If you remember it, you can log in to your account.</p>
                            <a class="button button-account" href="login">Log In</a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="login_form_inner">
                        <h3>Reset Password</h3>
                        <form class="row login_form" action="resetPassword" id="contactForm" method="post">
                            <div class="col-md-12 form-group">
                                <input type="text" class="form-control" id="username" name="username" placeholder="Username" 
                                       onfocus="this.placeholder = ''" onblur="this.placeholder = 'Username'" required/>
                            </div>
                            <div class="col-md-12 form-group">
                                <input type="email" class="form-control" id="email" name="email" placeholder="Email" 
                                       onfocus="this.placeholder = ''" onblur="this.placeholder = 'Email'" required>
                            </div>
                            <div class="col-md-12 form-group">
                                <button type="submit" value="submit" class="button button-login w-100">Send Reset Link</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!--================End Forgot Password Box Area =================-->

    <!-- SweetAlert Notification Scripts -->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script>
        // Display success or error message if available
        <% if (request.getAttribute("success") != null) { %>
            Swal.fire({
                icon: 'success',
                title: 'Success!',
                text: '<%= request.getAttribute("success") %>',
                confirmButtonText: 'OK'
            });
        <% } %>

        <% if (request.getAttribute("error") != null) { %>
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: '<%= request.getAttribute("error") %>',
                confirmButtonText: 'OK'
            });
        <% } %>
    </script>

    <!--================ Start Footer Area =================-->    
    <%@include file="footer.jsp" %>
    <!--================ End Footer Area =================-->

    <!-- JS Script Links -->
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
