<%@page import="java.net.URLEncoder"%>
<%@page import="java.nio.charset.StandardCharsets"%>
<%@page import="model.Config"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat, java.util.Date" %>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <meta name="description" content="">
        <meta name="author" content="">
        <title>PAYMENT RESULT</title>
        <!-- Bootstrap core CSS -->
        <link href="/vnpay_jsp/assets/bootstrap.min.css" rel="stylesheet"/>
        <!-- Custom styles for this template -->
        <link href="/vnpay_jsp/assets/jumbotron-narrow.css" rel="stylesheet"> 
        <script src="/vnpay_jsp/assets/jquery-1.11.3.min.js"></script>

        <style>
            body {
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                background-color: #eef2f5;
                margin: 0;
                padding: 20px;
            }

            .container {
                max-width: 600px;
                margin: auto;
                background: #ffffff;
                padding: 30px;
                border-radius: 10px;
                box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
            }

            .header {
                text-align: center;
                margin-bottom: 20px;
            }

            .header h3 {
                margin: 0;
                color: #333;
                font-size: 24px;
            }

            a {
                display: inline-block;
                margin-bottom: 20px;
                text-decoration: none;
                color: #fff;
                background-color: #007bff; /* Blue color */
                padding: 12px 20px;
                border-radius: 5px;
                font-weight: bold;
                transition: background-color 0.3s;
            }

            a:hover {
                background-color: #0056b3; /* Darker blue on hover */
            }

            .form-group {
                margin-bottom: 20px;
                padding: 10px;
                border: 1px solid #e0e0e0;
                border-radius: 5px;
                background-color: #f9f9f9;
            }

            .form-group label {
                display: block;
                font-weight: 600;
                color: #444;
            }

            .result {
                display: inline-block;
                margin-left: 10px;
                font-size: 1.2em;
                font-weight: bold;
            }

            .result.money {
                color: #28a745; /* Green for the amount */
            }

            .result.time {
                color: #6c757d; /* Gray for the time */
            }

            .result span {
                color: #000; /* Default text color */
            }

            .footer {
                text-align: center;
                margin-top: 30px;
                font-size: 14px;
                color: #888;
            }

            .footer p {
                margin: 0;
            }

            .success {
                color: #28a745; /* Green for success */
            }

            .fail {
                color: #dc3545; /* Red for fail */
            }
        </style>
    </head>
    <body>

        <%
    String vnp_PayDate = request.getParameter("vnp_PayDate"); // Thay bằng giá trị thực tế

    SimpleDateFormat inputFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    Date date = inputFormat.parse(vnp_PayDate);
    String formattedDate = outputFormat.format(date);
        %>
        <div>
            <a href="/LaptopShop/home">Back To Home</a>
        </div>
        <!--Begin display -->
        <div class="container">
            <div class="header clearfix">
                <h3 class="text-muted">PAYMENT RESULT</h3>
            </div>
            <div class="table-responsive">
                <div class="form-group">
                    <label>Transaction ID:</label>
                    <span class="result">${vnp_TxnRef}</span>
                </div>    
                <div class="form-group">
                    <label>Amount:</label>
                    <span class="result money" id="amount">${vnp_Amount}</span>
                </div>  
                <div class="form-group">
                    <label>Transaction Description:</label><br>
                    Name:   <span class="result"> ${name}</span><br>
                    Address:<span class="result"> ${address}</span><br>
                    Phone<span class="result"> ${phone}</span>
                </div> 
                <!--                <div class="form-group">
                                    <label>Transaction ID at CTT VNPAY-QR:</label>
                                    <span class="result"><%=request.getParameter("vnp_TransactionNo")%></span>
                                </div> -->
                <div class="form-group">
                    <label>Bank Code:</label>
                    <span class="result">${vnp_BankCode}</span>
                </div> 
                <div class="form-group">
                    <label>Payment Time:</label>
                    <span class="result time" id="paymentTime"><%= formattedDate %></span>
                </div>
                <div class="form-group">
                    <label>Transaction Status:</label>

                    <c:if test="${vnp_ResponseCode == '00'}">
                        <span style="color: green" class="result">
                            Success 
                        </span>
                    </c:if>

                    <c:if test="${vnp_ResponseCode != '00'}">
                        <span style="color: red" class="result">
                            Fail
                        </span>
                    </c:if>
                </div> 
            </div>
            <footer class="footer">
                <p>&copy; VNPAY 2020</p>
            </footer>
        </div>

        <script>
            // Định dạng số tiền
            const amountElement = document.getElementById('amount');
            // Lấy giá trị số tiền, xóa 2 số 0 cuối cùng
            const amountValue = parseFloat(amountElement.textContent) / 100; // Chia cho 100 để loại bỏ hai số 0
            const formattedAmount = amountValue.toLocaleString('vi-VN', {
                style: 'currency',
                currency: 'VND'
            });
            amountElement.textContent = formattedAmount;

            const year = vnp_PayDate.slice(0, 4);
            const month = vnp_PayDate.slice(4, 6);
            const day = vnp_PayDate.slice(6, 8);
            const hours = vnp_PayDate.slice(8, 10);
            const minutes = vnp_PayDate.slice(10, 12);
            const seconds = vnp_PayDate.slice(12, 14);

            // Định dạng lại chuỗi ngày tháng
            const formattedDate = `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;

                // Cập nhật nội dung của phần tử span
                document.getElementById("paymentTime").textContent = formattedDate;
        </script>
    </body>

</html>
