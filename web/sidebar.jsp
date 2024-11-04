<%-- 
    Document   : sidebar
    Created on : Jun 29, 2024, 8:29:22 AM
    Author     : ADMIN
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <html lang="en">
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>ADMIN System</title>
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
        </head>
        <style>
            body {
                background-color: #F6F6F6;
            }
            .sidebar {
                min-height: 100vh;
                background-color: #ffffff;
                border-right: 1px solid #ddd;
            }
            .sidebar a {
                color: #333;
                padding: 15px;
                display: block;
                text-decoration: none;
            }
            .sidebar a:hover {
                background-color: #f0f0f0;
            }
            .sidebar .logo img {
                width: 100px;
                margin: 20px;
            }
            .sidebar .user {
                margin-left: 20px;
                margin-top: 30px;
            }
            .content {
                padding: 20px;
            }
            .products {
                display: flex;
                flex-wrap: wrap;
            }
            .product-card {
                background-color: white;
                border: 1px solid #ddd;
                border-radius: 8px;
                margin: 10px;
                width: calc(33.333% - 20px);
                padding: 15px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                text-align: center;
            }
            .product-card img {
                max-width: 100%;
                border-bottom: 1px solid #ddd;
                padding-bottom: 15px;
                margin-bottom: 15px;
            }
            .product-card h5 {
                margin-bottom: 15px;
            }
            .product-card .price {
                color: #FF6600;
                font-weight: bold;
            }
            .product-card .old-price {
                text-decoration: line-through;
                color: #888;
                margin-right: 10px;
            }
            .cart {
                border-left: 1px solid #ddd;
                padding: 20px;
            }
            .cart-summary {
                border: 1px solid #ddd;
                padding: 15px;
                border-radius: 8px;
                background-color: #fff;
            }
            .cart-summary .apply-btn {
                color: #FF6600;
                cursor: pointer;
            }
            .cart-summary .total {
                font-weight: bold;
            }
            .cart-buttons {
                display: flex;
                justify-content: space-between;
                margin-top: 20px;
            }
            .cart-buttons button {
                width: 48%;
            }
            .search {
                background-color: #007bff;
                color: white;
                border: none;
                padding: 5px 20px;
                cursor: pointer;
                transition: background-color 0.3s ease;
                margin-left: 10px;
            }
            .search:hover {
                background-color: #0056b3;
            }
            .search-container {
                display: flex;
                align-items: center;
                width: 80%;
            }
            .header-container {
                display: flex;
                justify-content: space-evenly;
                align-items: center;
            }
            @media (max-width: 768px) {
                .product-card {
                    width: calc(50% - 20px);
                }
            }
            @media (max-width: 576px) {
                .product-card {
                    width: calc(100% - 20px);
                }
            }


            .container {
                width: 80%;
                margin: auto;
            }

            table {
                width: 100%;
                border-collapse: collapse;
            }

            table, th, td {
                border: 1px solid #ddd;
                padding: 15px;
                text-align: left;
            }

            th {
                background-color: rgb(14, 192, 195);
                color: white;
            }

            tr:nth-child(even) {
                background-color: #f2f2f2;
            }

            .btn {
                border: none;
                color: white;
                padding: 10px 20px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 16px;
                margin: 4px 2px;
                transition-duration: 0.4s;
                cursor: pointer;
            }

            .btn-primary {
                background-color: white;
                color: black;
                border: 2px solid #4CAF50;
            }

            .btn-primary:hover {
                background-color: #4CAF50;
                color: white;
            }

            .btn-danger {
                background-color: white;
                color: black;
                border: 2px solid #f44336;
            }

            .btn-danger:hover {
                background-color: #f44336;
                color: white;
            }

            .btn a {
                text-decoration: none;
                color: black;
            }


            .close {
                color: #aaa;
                float: right;
                font-size: 28px;
                font-weight: bold;
            }

            .close:hover,
            .close:focus {
                color: black;
                text-decoration: none;
                cursor: pointer;
            }

            .list-page {
                float: right;
                clear: both;
                margin-top: 10px;
            }

            .list-page .item a{
                color: #000;
                text-decoration: none;
                justify-content: center;
                align-items: center;
                margin-right: 30px;
                border-radius: 50%;
                border: 3px solid #ccc;
            }


            .add-form {
                display: none;
                position: fixed;
                z-index: 1;
                left: 0;
                top: 0;
                width: 100%;
                height: 100%;
                overflow: auto;
                background-color: rgba(0,0,0,0.4);
                padding-top: 60px;
            }

            .add-form-content {
                background-color: #fefefe;
                margin: 5% auto 15% auto;
                border: 1px solid #888;
                width: 50%;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
            }

            .add-form-content label {
                display: block;
                margin: 10px 0;
                font-weight: bold;
            }

            .add-form-content input[type="text"],
            .add-form-content textarea {
                width: 100%;
                padding: 12px 20px;
                margin: 8px 0;
                display: inline-block;
                border: 1px solid #ccc;
                border-radius: 4px;
                box-sizing: border-box;
            }

            .add-form-content input[type="submit"] {
                width: 100%;
                background-color: #4CAF50;
                color: white;
                padding: 14px 20px;
                margin: 8px 0;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }

            .add-form-content input[type="submit"]:hover {
                background-color: #45a049;
            }


        </style>
    </head>
    <body>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-2 sidebar">
                    <div class="logo">
                        <a  <img src="" alt="Logo"></a> 
                    </div>



                    <a href="readProduct"><i class="fas fa-box-open"></i> Manage Product</a>




                    
                    <a href="CustomerManageController?service=listallstaff"><i class="fas fa-calendar"></i> Manage Staff</a>
                    <a href="BrandController?service=listall"><i class="fas fa-calendar"></i> Manage Brand</a>
                    
                    <a href="ConfigurationManageController?service=listall"><i class="fas fa-calendar"></i> Manage Configuration</a>
                    <a href="CategoryController?service=listall"><i class="fas fa-server"></i> Manage Category</a>
                    <a href="ColorController?service=listall"><i class="fas fa-server"></i> Manage Color</a>
                    
                    <a href="StatisticController?service=listall"><i class="fas fa-calendar"></i> Statistic</a>
                    <a href="SaleStatisticController2?service=listall"><i class="fas fa-calendar"></i> Statistic</a>
                    
                    <a href="voucherManager"><i class="fas fa-server"></i> Manage Voucher</a>
                   
                    <a href="updateAccountPage.jsp"><i class="fas fa-upload  "></i>Update Account</a>
                    <div class="user">

                        <p>Hello ${sessionScope.admin.fullName}</p>

                        <a href="logout"><i class="fas fa-sign-out-alt"></i> Logout</a>
                    </div>
                </div>
                </html>