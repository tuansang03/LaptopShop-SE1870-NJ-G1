<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Aroma Shop - Reset Password</title>
    <link rel="icon" href="img/Fevicon.png" type="image/png">
    <link rel="stylesheet" href="vendors/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="vendors/fontawesome/css/all.min.css">
    <link rel="stylesheet" href="vendors/themify-icons/themify-icons.css">
    <link rel="stylesheet" href="vendors/linericon/style.css">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <%@include file="header.jsp" %>

    <!-- ================ Start Banner Area ================= -->    
    <section class="blog-banner-area" id="category">
        <div class="container h-100">
            <div class="blog-banner">
                <div class="text-center">
                    <h1>Reset Password</h1>
                    <nav aria-label="breadcrumb" class="banner-breadcrumb">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="#">Home</a></li>
                            <li class="breadcrumb-item active" aria-current="page">Reset Password</li>
                        </ol>
                    </nav>
                </div>
            </div>
        </div>
    </section>
    <!-- ================ End Banner Area ================= -->

    <!--================Reset Password Box Area =================-->
    <section class="login_box_area section-margin">
        <div class="container">
            <div class="row">
                <div class="col-lg-6 offset-lg-3">
                    <div class="login_form_inner">
                        <h3>Enter New Password</h3>
                        <form class="row login_form" action="updatePassword" id="resetPasswordForm" method="post" onsubmit="return validatePassword();">
                            <div class="col-md-12 form-group">
                                <input type="password" class="form-control" id="newPassword" name="newPassword" placeholder="New Password" 
                                       onfocus="this.placeholder = ''" onblur="this.placeholder = 'New Password'" required>
                            </div>
                            <div class="col-md-12 form-group">
                                <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" placeholder="Confirm Password" 
                                       onfocus="this.placeholder = ''" onblur="this.placeholder = 'Confirm Password'" required>
                            </div>
                            <div class="col-md-12 form-group">
                                <button type="submit" class="button button-login w-100">Reset Password</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!--================End Reset Password Box Area =================-->

    <!-- SweetAlert Notification Scripts -->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script>
    function validatePassword() {
        var newPassword = document.getElementById("newPassword").value;
        var confirmPassword = document.getElementById("confirmPassword").value;

        // Ki?m tra ?? dài c?a m?t kh?u m?i
        if (newPassword.length < 8) {
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'New password must be at least 8 characters long!',
                confirmButtonText: 'OK'
            });
            return false; // Ng?n ch?n g?i bi?u m?u
        }

        // Ki?m tra xem hai m?t kh?u có kh?p không
        if (newPassword !== confirmPassword) {
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'Passwords do not match!',
                confirmButtonText: 'OK'
            });
            return false; // Ng?n ch?n g?i bi?u m?u
        }

        return true; // Cho phép g?i bi?u m?u
    }
</script>


    <%@include file="footer.jsp" %>

    <script src="vendors/jquery/jquery-3.2.1.min.js"></script>
    <script src="vendors/bootstrap/bootstrap.bundle.min.js"></script>
    <script src="js/main.js"></script>
</body>
</html>
