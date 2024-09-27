<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Account Banned</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">
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
        .container {
            margin-top: 50px;
            text-align: center;
            color: #333;
        }

        .container .alert {
            background-color: #ffcccc;
            color: #b30000;
            border-color: #b30000;
            padding: 20px;
            font-size: 18px;
        }

        .container h1 {
            color: #b30000;
            font-weight: bold;
            font-size: 36px;
            margin-bottom: 20px;
        }

        .container p {
            font-size: 20px;
        }

        .container .btn-back {
            margin-top: 30px;
        }

        .container .btn-back a {
            color: #fff;
            text-decoration: none;
            padding: 10px 20px;
            background-color: #007bff;
            border-radius: 5px;
        }

        .container .btn-back a:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Account Banned</h1>
        <div class="alert alert-danger">
            Your account has been permanently banned due to violation of the general rules.
        </div>
        <p>If you believe this is a mistake, please contact our support team.</p>

        <div class="btn-back">
            <a href="home"><i class="fas fa-home"></i> Go Back to Home</a>
        </div>
    </div>
</body>
</html>
