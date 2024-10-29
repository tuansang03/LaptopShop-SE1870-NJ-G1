<%-- 
    Document   : orderSuccess.jsp
    Created on : Oct 28, 2024, 3:25:22 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Order Success</title>
        <link rel="stylesheet" href="styles.css">

        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                margin: 0;
                padding: 0;
            }

            .container {
                max-width: 600px;
                margin: 100px auto;
                padding: 20px;
                background: #fff;
                border-radius: 8px;
                box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
                text-align: center;
            }

            h1 {
                color: #4CAF50;
            }

            p {
                color: #555;
            }

            .btn {
                display: inline-block;
                margin-top: 20px;
                padding: 10px 20px;
                background-color: #4CAF50;
                color: white;
                text-decoration: none;
                border-radius: 5px;
                transition: background-color 0.3s;
            }

            .btn:hover {
                background-color: #45a049;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1>Thank You for Your Order!</h1>
            <p>Your order has been placed successfully.</p>
            <p>We will send you a confirmation email shortly.</p>
            <p>If you have any questions, feel free to contact our support team.</p>
            <a href="home" class="btn">Continue Shopping</a>
        </div>
    </body>
</html>
