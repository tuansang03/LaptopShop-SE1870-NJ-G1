<%-- 
    Document   : footer
    Created on : Sep 16, 2024, 9:41:13 PM
    Author     : ADMIN
--%>


<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>  
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Footer</title>
        <link rel="stylesheet" href="vendors/bootstrap/bootstrap.min.css">
        <link rel="stylesheet" href="vendors/fontawesome/css/all.min.css">
        <link rel="stylesheet" href="css/style.css">
<style>
    .footer {
        background-color: #8bb0d5 !important; /* Màu nền trắng xám */
        color: black !important; /* Màu chữ tối */
        padding: 40px 0 !important;
        border-top: 1px solid #e9ecef !important; /* Đường viền trên */
    }
    .footer h4 {
        font-size: 18px !important;
        margin-bottom: 20px !important;
        color: black !important; /* Màu tiêu đề */
    }
    .footer p, .footer ul {
        font-size: 14px !important;
        line-height: 1.6 !important;
    }
    .footer .list a {
        color: #343a40 !important; /* Màu chữ liên kết */
        text-decoration: none !important;
    }
    .footer .list a:hover {
        color: #007BFF !important; /* Màu chữ liên kết khi hover */
        text-decoration: underline !important; /* Gạch chân khi hover */
    }
    .footer-bottom {
        padding: 20px 0 !important;
    }
    .footer-bottom p {
        margin: 0 !important;
        font-size: 14px !important;
    }
    .instafeed img {
        width: 100% !important; /* Ảnh gallery tự động điều chỉnh kích thước */
        border-radius: 5px !important; /* Bo góc ảnh */
    }
    .footer-area{
        background-color: #f0f0f0 !important; /* Màu nền trắng xám */
        color: #343a40 !important; /* Màu chữ tối */
    }
    /* Định vị widget ở góc phải dưới màn hình */
df-messenger {
  position: fixed;
  bottom: 20px; /* Điều chỉnh khoảng cách với cạnh dưới */
  right: 20px;  /* Điều chỉnh khoảng cách với cạnh phải */
  z-index: 1000; /* Đảm bảo widget nằm trên các thành phần khác */
}

/* Điều chỉnh kích thước của widget */
df-messenger {
  width: 300px; /* Đặt chiều rộng mong muốn */
  height: 400px; /* Đặt chiều cao mong muốn */
}

df-messenger .chat-wrapper {
  border-radius: 10px; /* Bo góc để trông mềm mại hơn */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); /* Đổ bóng để widget nổi bật */
}

</style>


    </head>
    <body>
        <c:if test="${sessionScope.user!=null || (sessionScope.user==null && 
                      sessionScope.sale==null && sessionScope.admin==null)}">
        <footer class="footer">
            <div class="footer-area">
                <div class="container">
                    <div class="row section_gap">
                        <div class="col-lg-4 col-md-6 col-sm-6">
                            <div class="single-footer-widget tp_widgets">
                                <h4 class="footer_title large_title">Our Mission</h4>
                                <p>
                                    <legend>LaptopShop</legend> Our mission is to provide high-quality laptops that empower individuals and businesses. We strive to offer the latest technology at competitive prices, ensuring a seamless shopping experience with exceptional customer service. Your success is our priority!
                                </p>
                            </div>
                        </div>
                        <div class="col-lg-2 col-md-6 col-sm-6">
                            <div class="single-footer-widget tp_widgets">
                                <h4 class="footer_title">Quick Links</h4>
                                <ul class="list">
                                    <li><a href="home">Home</a></li>
                                    <li><a href="category.jsp">Shop</a></li>
                                    <li><a href="blog.jsp">Blog</a></li>
                                    <li><a href="AboutUs.jsp">About Us</a></li>
                                </ul>
                            </div>
                        </div>

                    </div>
                </div>
            </div>

            <div class="footer-bottom">
                <div class="container">
                    <div class="row d-flex">
                        <p class="col-lg-12 footer-text text-center">
<!--                            Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="fa fa-heart" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank" style="color: #007BFF;">Colorlib</a>-->
                        </p>
                    </div>
                </div>
            </div>
        </footer>
        </c:if>
  
    </body>
    <script src="https://www.gstatic.com/dialogflow-console/fast/messenger/bootstrap.js?v=1"></script>
<df-messenger
  intent="WELCOME"
  chat-title="LaptopShop"
  agent-id="618e0042-fe69-4333-80ac-5f0e0c7e9e9f"
  language-code="en"
></df-messenger>
</html>
