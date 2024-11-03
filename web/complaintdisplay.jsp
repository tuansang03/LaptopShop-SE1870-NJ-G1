<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>  
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Complaint Form - Laptop Shop</title>
        <link rel="icon" href="img/Fevicon.png" type="image/png">
        <link rel="stylesheet" href="vendors/bootstrap/bootstrap.min.css">
        <link rel="stylesheet" href="vendors/fontawesome/css/all.min.css">
        <link rel="stylesheet" href="vendors/themify-icons/themify-icons.css">
        <link rel="stylesheet" href="vendors/nice-select/nice-select.css">
        <link rel="stylesheet" href="vendors/owl-carousel/owl.theme.default.min.css">
        <link rel="stylesheet" href="vendors/owl-carousel/owl.carousel.min.css">
        <link rel="stylesheet" href="css/style.css">

        <style>
            .form-container {
                margin-top: 50px;
                margin-bottom: 50px;
                background-color: #f9f9f9;
                padding: 30px;
                border-radius: 8px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                max-width: 600px;
                margin-left: auto;
                margin-right: auto;
            }

            .form-title {
                font-size: 1.8em;
                font-weight: 500;
                color: #333;
                text-align: center;
                margin-bottom: 20px;
            }

            .form-control {
                border-radius: 5px;
            }

            .btn-submit {
                background-color: #35516d;
                color: white;
                border-radius: 5px;
                padding: 10px 20px;
                width: 100%;
            }

            .btn-submit:hover {
                background-color: #233a50;
            }

            .error-message {
                color: red;
                font-size: 0.9em;
            }
        </style>
    </head>
    <body>
        <c:if test="${sessionScope.user!=null}">
        <!--================ Start Header Menu Area =================-->
        <%@include file="header.jsp" %>
        <!--================ End Header Menu Area =================-->
        <main class="site-main">
            <section class="complaint-section">
                <div class="container">
                    <div class="form-container">
                        <h2 class="form-title">Submit a Complaint</h2>
                        <form action="submitComplaint" method="POST">
                            <div class="form-group">
                                <label for="name">Your Name</label>
                                <input type="text" class="form-control" id="name" name="name" 
                                    
                                       value="${sessionScope.user.userName}" required readonly>
                            </div>

                            <div class="form-group">
                                <label for="email">Your Email</label>
                                <input type="email" class="form-control" id="email" name="email" 
                                     
                                       value="${sessionScope.user.email}" required readonly>
                            </div>

                            <div class="form-group">
                                <label for="orderId">Order ID</label>
                                <input type="text" class="form-control" id="orderId" name="orderId" placeholder="Enter your order ID" required>
                            </div>
                            <div class="form-group">
                                <label for="message">Reason</label>
                                <textarea class="form-control" id="message" name="message" rows="5" placeholder="Please describe the issue..." required></textarea>
                            </div>
                            <button type="submit" class="btn btn-submit">Submit Complaint</button>
                        </form>
                    </div>
                </div>
            </section>
        </main>

        <!--================ Start Footer Area =================-->
        <%@include file="footer.jsp" %>
        <!--================ End Footer Area =================-->

        <script src="vendors/jquery/jquery-3.2.1.min.js"></script>
        <script src="vendors/bootstrap/bootstrap.bundle.min.js"></script>
        <script src="vendors/owl-carousel/owl.carousel.min.js"></script>
        <script src="vendors/nice-select/jquery.nice-select.min.js"></script>
        <script src="js/main.js"></script>
        </c:if>
        <c:if test="${sessionScope.sale!=null || sessionScope.admin!=null}">
            <%@include file="notallowpage.jsp" %>
        </c:if>
    </body>
</html>
