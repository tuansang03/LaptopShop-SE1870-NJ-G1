<%-- 
    Document   : AboutUs.jsp
    Created on : Sep 20, 2024, 3:36:17 PM
    Author     : kieud
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>About Us</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        
        <link rel="icon" href="img/Fevicon.png" type="image/png">
        <link rel="stylesheet" href="vendors/bootstrap/bootstrap.min.css">
        <link rel="stylesheet" href="vendors/fontawesome/css/all.min.css">
        <link rel="stylesheet" href="vendors/themify-icons/themify-icons.css">
        <link rel="stylesheet" href="vendors/linericon/style.css">
        <link rel="stylesheet" href="vendors/owl-carousel/owl.theme.default.min.css">
        <link rel="stylesheet" href="vendors/owl-carousel/owl.carousel.min.css">
        <link rel="stylesheet" href="css/style.css">
        
        <style>
            .about_us {
                display: flex;
                flex-direction: column;
                align-items: center;
                width: 80%;
                margin: 0 auto;
                text-align: center;
            }
            .about_us p {
                transition: color 0.5s;
            }
            .about_us p:hover {
                color: #007BFF; /* Màu khi hover */
            }
            blockquote {
                margin: 20px 0;
                font-style: italic;
                border-left: 5px solid #007BFF;
                padding-left: 10px;
            }
        </style>
    </head>
    <body>
        <!--Header-->
        <%@include file="header.jsp" %>
        
        <!-- ================ start banner area ================= -->
        <section class="blog-banner-area" id="contact">
            <div class="container h-100">
                <div class="blog-banner">
                    <div class="text-center">
                        <h1>About Us</h1>
                        <nav aria-label="breadcrumb" class="banner-breadcrumb">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="#">Home</a></li>
                                <li class="breadcrumb-item active" aria-current="page">About Us</li>
                            </ol>
                        </nav>
                    </div>
                </div>
            </div>
        </section>
        <!-- ================ end banner area ================= -->
        
        <div class="about_us">            
            <h3>About Us: Our Journey into Building the Ultimate Laptop Store</h3>
            <img src="img/team.jpg" alt="Our Team" style="width: 50%; border-radius: 10px;">
            <p>
                Welcome to our Laptop Store, where technology meets passion! We are a group of five dedicated students who embarked on this journey with a shared love for technology and a vision to create a platform that simplifies the process of buying laptops.
            </p>
            <blockquote class="blockquote text-center">
                <p class="mb-0">"Chúng tôi không chỉ bán laptop, mà còn mang đến trải nghiệm công nghệ tốt nhất cho người dùng."</p>
                <footer class="blockquote-footer">Nguyễn Văn A, Thành viên nhóm</footer>
            </blockquote>
            <h4>Giá Trị Cốt Lõi</h4>
            <ul>
                <li>Đổi mới sáng tạo</li>
                <li>Khách hàng là trung tâm</li>
                <li>Đội ngũ làm việc tận tâm</li>
            </ul>
        </div>
        
        <%@include file="footer.jsp" %>
    </body>
</html>
