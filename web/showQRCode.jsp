<%-- 
    Document   : showQRCode.jsp
    Created on : Oct 5, 2024, 4:26:08 PM
    Author     : ADMIN
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Show QR Code</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Aroma Shop - Cart</title>
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
                <script type="text/javascript">
                    // Chuyển hướng về trang home sau 5 giây
                    setTimeout(function () {
                        window.location.href = "home";
                    }, 5000);
                </script>
        <style>
            .flex {
                display: flex;
                justify-content: space-evenly;
                margin-top: 40px;
            }

            .left{
                background-color: #f9f9f9;
                border-radius: 15px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                padding: 20px;
            }

            .left h5 {
                color: rgb(0 0 0 / 42%);
            }

            .left h5 span {
                color: black;
                line-height: 54px;
                padding: 14px;
                font-size: 17px;
            }

            .right{
                
            }

            .right {
                box-shadow: 0px 0px 20px 9px rgb(159 159 159 / 50%);
                padding: 20px;
                width: 50%;
                text-align: center;
                background-color: #f9f9f9;
                border-radius: 15px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }

            .right h1 {
                font-size: 24px;
                color: #333;
                margin-bottom: 15px;
            }

            .right img {
                width: 200px;
                height: 200px;
                border: 5px solid #4CAF50;
                border-radius: 10px;
                transition: transform 0.3s ease;
                margin-bottom: 40px;
                margin-top: 20px;
            }

            .right img:hover {
                transform: scale(1.1);
            }

            .right p {
                font-size: 16px;
                color: #666;
                margin-top: 10px;
            }

        </style>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="flex">
            <div class="left">
                <h2>Order information</h2>
                <img width="100px"  src="img/logo.png"/>
                <hr style="border: none; height: 0.5px; background-color: #cec2c2;">
                <h5>Payment amount: 
                    <span style="font-size: 18px">
                        <fmt:formatNumber value="${total}" type="number"/>đ
                    </span>
                </h5>
                <h5>Transaction code: </br><span>${code}</span></h5>
                <h5>Content: </br><span>Thanh toán đơn hàng#${code}</span></h5>
            </div>
            <div class="right">
                <h1>QR Code for Payment</h1>
                <img src="${requestScope.qrPath}" alt="QR Code" />
                <p>Scan the QR code to complete payment.</p>
            </div>
        </div>
    </body>
</html>
