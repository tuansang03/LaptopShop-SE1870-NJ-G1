<%-- 
    Document   : userprofile
    Created on : Oct 8, 2024, 1:22:21 PM
    Author     : kieud
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="dal.OderDAO" %>
<%@ page import="model.OrderDetail" %>
<%@ page import="model.Order" %> 
<%@ page import="model.User" %>
<%@ page import="dal.ImageDAOS" %>
<%@ page import="model.Image" %> 
<link rel="icon" href="img/Fevicon.png" type="image/png">
<link rel="stylesheet" href="vendors/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="vendors/fontawesome/css/all.min.css">
<link rel="stylesheet" href="vendors/themify-icons/themify-icons.css">
<link rel="stylesheet" href="vendors/nice-select/nice-select.css">
<link rel="stylesheet" href="vendors/owl-carousel/owl.theme.default.min.css">
<link rel="stylesheet" href="vendors/owl-carousel/owl.carousel.min.css">

<link rel="stylesheet" href="css/style.css">

<link rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
<!-- or -->
<link rel="stylesheet"
      href="https://unpkg.com/boxicons@latest/css/boxicons.min.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Nunito:ital,wght@0,200..1000;1,200..1000&family=PT+Sans:ital,wght@0,400;0,700;1,400;1,700&display=swap" rel="stylesheet">
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile</title>
        <link rel="stylesheet"
              href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
        <!-- or -->
        <link rel="stylesheet"
              href="https://unpkg.com/boxicons@latest/css/boxicons.min.css">
        <link
            rel="stylesheet"
            href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
            />
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
            />
        
    </head>
    <body>

      
            <%@include file="header.jsp" %>

            <style>

                body {
                    background: rgb(99, 39, 120)
                }
                .row{
                    align-items: last !important;
                }

                .form-control:focus {
                    box-shadow: none;
                    border-color: #BA68C8
                }

                .profile-button {
                    background: rgb(99, 39, 120);
                    box-shadow: none;
                    border: none
                }

                .profile-button:hover {
                    background: #682773
                }

                .profile-button:focus {
                    background: #682773;
                    box-shadow: none
                }

                .profile-button:active {
                    background: #682773;
                    box-shadow: none
                }

                .back:hover {
                    color: #682773;
                    cursor: pointer
                }

                .labels {
                    font-size: 11px
                }

                .add-experience:hover {
                    background: #BA68C8;
                    color: #fff;
                    cursor: pointer;
                    border: solid 1px #BA68C8
                }
                .option-profile{
                    margin-top: 110px;

                    height: 30%;
                }
                .option{
                    margin-top: 20px;
                    margin-bottom: 15px;
                    border: 2px solid gray;
                    font-size: 15px;
                    border-radius: 40px;
                    text-align: center;
                    width: 100%;
                    height: 20%;
                    padding: 10px;
                }




            </style>


            <section>
               
                <% 
                                       String profile = request.getParameter("profile");
                                       if ("info".equals(profile)) { 
                %>
                
                 <div class="container-fluid rounded bg-white mt-5 mb-5 margin-left">
                    <div class="row">
                        <div class="col-md-2 option-profile">
                            <ul style="background: #f9f9f9; border: 1px solid gray; border-radius: 10px; padding: 15px; ">
                                <li><a href="profile?profile=info" class="option">Profile Information</a></li>
                                <li><a href="profile?profile=ordermanage&see=all" class="option">Order Manage</a></li>
                                <li><a href="updatepassword.jsp" class="option">Change Password</a></li>
                                <li><a href="address?id=${sessionScope.user.id}&action=default" class="option">Address Manager</a></li>
                            </ul>
                        </div>
                        <div class="col-md-1.5 border-right">
                            <div class="d-flex flex-column align-items-center text-center p-3 py-5">
                                <img class="rounded-circle mt-5" width="150px" 
                                     src="https://thumbs.dreamstime.com/z/vector-illustration-isolated-white-background-user-profile-avatar-black-line-icon-user-profile-avatar-black-solid-icon-121102166.jpg?ct=jpeg" alt="Image" width="200""><span class="font-weight-bold">${sessionScope.user.fullName}</span><span class="text-black-50"></span><span> </span></div>
                        </div>
                        <!--CHo nay node de code de phan truong hop khi nguoi dung chon cac tuy chon-->
                        <div class="col-md-6 border-right ">
                            <form action="profile?profile=info&action=change" method="post">
                                <div class="p-3 py-5">
                                    <div class="d-flex justify-content-between align-items-center mb-3">
                                        <h4 class="text-right">My Profile</h4>
                                    </div>
                                     <div class="row mt-2">
                                    <div class="col-md-12">
                                            <label class="labels">UserName</label>
                                            <input type="text" class="form-control"  maxlength="50" placeholder="enter username" readonly="" value="${sessionScope.user.getUserName()}" name="username">
                                        </div>
                                        </div>
                                    <div class="row mt-2">
                                        <div class="col-md-12">
                                            <label class="labels">Full Name</label>
                                            <input type="text" class="form-control" maxlength="110" placeholder="enter user profile" value="${sessionScope.user.getFullName()}" name="name">
                                        </div>  
                                    </div>
                                    <div class="row mt-3">

                                        <div class="col-md-12">
                                            <label class="labels">Email</label>
                                            <input type="text" class="form-control" maxlength="125" readonly="You cant change email" placeholder="enter email id" value="${sessionScope.user.getEmail()}" name="email">
                                        </div>

                                    </div>
                                    <div class="row mt-3">
                                        
                                       
                                    </div>
                                    <div class="mt-5 text-center">
                                        <button class="btn btn-primary profile-button" type="submit">Save Profile</button>
                                    </div>
                                    <div>${error2}</div>
                                    <div>
                                        <h5 style="color: #5cb99a; text-align: center; margin-top: 25px">${mess}</h5>
                                    </div>
                                </div>

                                <% 
                                    } 
                                %>

                                <% 
            
        //===========================================================================================================
                        if ("ordermanage".equals(profile)) {
                                %>
                                <style>
                                    body {
                                        font-family: Arial, sans-serif;
                                        margin: 20px;
                                        background-color: #f4f4f4;
                                    }
                                    table {
                                        width: 100%;
                                        border-collapse: collapse;
                                        margin: 20px 0;
                                        background-color: #fff;
                                        font-family: "PT Sans", sans-serif;
                                        font-weight: 400;
                                        font-style: normal;
                                    }
                                    th, td {
                                        padding: 12px;
                                        text-align: left;
                                        border: 1px solid #ddd;
                                    }
                                    th {
                                        background-color: #6db3ec;
                                        color: white;
                                    }
                                    tr:hover {
                                        background-color: #f1f1f1;
                                    }
                                    /* Thiết lập cho phần tử background bên trong Button */
                                    .background {
                                        background-color: #f0f5f9;
                                        padding: 20px 15px 20px 15px;
                                        border-radius: 12px;
                                        box-shadow: 0 4px 10px rgba(0, 0, 0, 0.15); /* Đổ bóng để tạo chiều sâu */
                                        width: 100%;
                                        text-align: center;
                                        transition: background-color 0.3s ease; /* Hiệu ứng thay đổi màu nền */
                                    }

                                    .backgroundSelect {
                                        background: linear-gradient(135deg, #bd9aff, #f3ebe8);
                                        padding: 20px 15px 20px 15px;
                                        border-radius: 12px;
                                        box-shadow: 0 4px 10px rgba(0, 0, 0, 0.15); /* Đổ bóng để tạo chiều sâu */
                                        width: 100%;
                                        text-align: center;
                                        transition: background-color 0.3s ease; /* Hiệu ứng thay đổi màu nền */
                                    }

                                    Button:hover .background {
                                        background-color: #e2e8f0; /* Đổi màu nền khi di chuột */
                                    }

                                    /* Phong cách cho tiêu đề */
                                    .background h5 {
                                        font-size: 1.2em;
                                        color: #2d3748; /* Màu sắc nhã nhặn */
                                        font-weight: 600;
                                        margin-bottom: 10px;
                                    }

                                    /* Phong cách cho các văn bản mô tả */
                                    .background h6 {
                                        font-size: 0.9em;
                                        color: #718096;
                                        margin: 6px 0;
                                        font-weight: 500;
                                    }
                                    .x{
                                        border: none;
                                        font-size: 11px;
                                        cursor: pointer;
                                        padding: 15px;
                                        outline: none;
                                        transition: transform 0.2s ease; /* Hiệu ứng phóng to khi di chuột */
                                    }
                                    .x:hover {
                                        transform: rotateX(15deg);
                                       background-color: #8498ad;
                                    }
                                    td{
                                        border: none;
                                    }


                                </style>
                                
                                
                                <style></style>
                                <div>
                                    <div class="col-md-2 option-profile" style="position: fixed
                                        ">
                                        <ul style="background: #f9f9f9; border: 1px solid gray; border-radius: 10px; padding: 15px;">
                                <li><a href="profile?profile=info" class="option">Profile Information</a></li>
                                <li><a href="profile?profile=ordermanage&see=all" class="option">Order Manage</a></li>
                                 <li><a href="updatepassword.jsp" class="option">Change Password</a></li>
                                <li><a href="address?id=${sessionScope.user.id}&action=default" class="option">Address Manager</a></li>
                            </ul>
                        </div>
                                    <div>
                                        
                                        
                                <table style="width: 50%; margin-left: 25%;border: none;
                                       border-radius: 10px; overflow: hidden; text-align: center" >
                                    <thead>
                                        <tr >
                                            <th><a class="background x " style="color: black;" href="profile?profile=ordermanage&see=all">All</a></th>
                                            <th><a class="background x" style="color: black" href="profile?profile=ordermanage&see=wait">Pending Payment</a></th>
                                            <th><a class="background x" style="color: black" href="profile?profile=ordermanage&see=accepted">Accepted</a></th>
                                            <th><a class="background x" style="color: black" href="profile?profile=ordermanage&see=intransit">Shipping in Progress</a></th>
                                            <th><a class="background x" style="color: black" href="profile?profile=ordermanage&see=done">Successful</a></th>
                                            <th><a class="background x" style="color: black" href="profile?profile=ordermanage&see=rejected">Cancelled</a></th>
                                        </tr>
                                    </thead>
                                </table>
                                    </div>
                                </div>
                                <table style="width: 55%; align-items: center; text-align: center; margin: 0px auto;">
                                    <thead>
                                        <tr>
                                            
                                            <th>Order List</th> 

                                        </tr>
                                    </thead>

                                    <style>

                                        .product_image img {
                                            width: 100%;
                                            height: 100%;
                                        }
                                        .product_name {
                                            color: #002752;
                                        }
                                        .xl::first-letter{
                                               text-transform: uppercase;
                                              
                                        }
                                    </style>
                                    <c:forEach items="${orderListInfo}" var="o" varStatus="status">
                                        <c:set var="orderId" value="${status.index + 1}" />
                                        <c:set var="listOrder" value="${orderDAO.getOrderDetailsByOrderId(o.getOrder().getId())}" />

                                        <tr>
                                           
                                            <td style="width: 45%; max-height: 120px !important; background: #f7f7f7; border-bottom: 3px solid #9fbbd9">
                                                  
                                                            <c:if test="${o.getOrder().getOrderStatus() eq 'rejected'}">
                                                                <c:set var="color" value="#ff4c5d"></c:set>
                                                            </c:if>
                                                            <c:if test="${o.getOrder().getOrderStatus() eq 'accepted'}">
                                                                <c:set var="color" value="#33b933"></c:set>
                                                            </c:if>
                                                            <c:if test="${o.getOrder().getOrderStatus() eq 'failed'}">
                                                                <c:set var="color" value="black"></c:set>
                                                            </c:if>
                                                            <c:if test="${o.getOrder().getOrderStatus() eq 'done'}">
                                                                <c:set var="color" value="#3b8704"></c:set>
                                                            </c:if>
                                               
    <c:forEach items="${listOrder}" var="o1" varStatus="status">
    
    <c:if test="${status.index == 0}">
        <c:set var="image" value="${imageDAO.getOneImageByProductDetailID(o1.getProductDetail().getId())}" />
        
        <div style="display: flex;">
           
            
            <div style="display: flex; width: 100%" >
                <div style="width: 20%; border-right: 1px solid gray">
                    <div>
                        <p style="font-size: 12px">#${o.getOrder().getId()}</p>
                    </div>
                    <div>
                        <p style="font-size: 12px">Status &nbsp;
                           
                            
                            <span style="font-size: 13px;font-family: monospace; margin-left: 40px; color: ${color}; font-weight: bold"> ${o.getOrder().getOrderStatus().toUpperCase()}</span></p>
                        <p>
                            <div style="display: flex">
                                                              
                                <div style="margin: 0 auto ; color: black; font-weight: 200; margin-left: 10px"> <p style="font-size: 12px;">Total </p> </div>
                                                                <div> 
                                                                 <div style=" color: #EE4D2D; border-bottom: 1px solid gray; font-size: 12px; margin-right:  20px"><fmt:formatNumber value="${o.order.totalAmountAfter}" type="number"/>₫</div>
                                                                  </div>                                                             
                                                            </div>
                        </p>
                    </div>
                </div>
                <div style="width: 80%; display: flex; border-left: 1px solid gray; padding-left: 20px">
                     <div style="width: 110px; height: 80px">
                <img style="width: 100%; height: 100%;" src="${pageContext.request.contextPath}/images/${image.image}">
            </div>
            <div style="width: 88%; margin-left: 15px;">
                <div>
                    <h5 class="product_name" style="font-size: 13px; color: #002752; font-family: Nunito, sans-serif;">
                        <a href="profile?profile=orderdetail&id=${o.order.id}">
                            ${o1.productDetail.product.name} ${o1.productDetail.configuration.name} (${o1.productDetail.color.name})
                        </a>
                    </h5>
                </div>
                <div>
                    <p style="font-size: 11px; color: black;">${o1.productDetail.product.category.name}</p>
                    <p style="font-size: 11px">Type: ${o1.productDetail.configuration.name}</p>
                </div>
                <div style="display: flex; gap: 100%">

                    
                </div>
            </div>
                </div>
            </div>
        </div>
        <hr style="width: 60%;" />
    </c:if>

    
    <c:if test="${status.index == 1}">
        <p style="font-size: 10px; text-align: center">Tap "See order detail" to see all ordered products</p>
    </c:if>
</c:forEach>


<style>
    .buton:hover{
        background: #6db3ec;
        color: white;
    }
    
    </style>
<button class="buton" style=" margin-left: 44%; border: 1px solid gray;font-size: 13px;border-radius: 5px !important;"><a style="color: black; padding: 1px; " href="profile?profile=orderdetail&id=${o.order.id}">See Order Detail</a></button>


<script>
    function showMoreItems() {
       
    }
</script>

<!--                                                  
                                            </td>
<!--
                                            <td>${o.quantity}</td>
                                            <td>${o.order.orderDate}</td>-->
                                           

<!--                                            <c:if test="${o.order.paymentStatus == null}">
                                                <td>None</td>
                                            </c:if>
                                            <c:if test="${o.order.paymentStatus != null}">
                                                <td>${o.order.paymentStatus}</td>
                                            </c:if>d-->                         
                                            <!-- Chỉ hiển thị nút Return khi trạng thái đơn hàng là 'done' -->
<!--                                            <td>
                                                <c:if test="${o.order.orderStatus == 'done'}">
                                                    <a href="ReturnController?id=${o.order.id}&service=returnRequest">Return</a>
                                                </c:if>
                                            </td>-->

<!--                                            <td>
                                                <a href="profile?profile=orderdetail&id=${o.order.id}">
                                                    <i class='bx bx-show-alt'></i>
                                                </a>
                                            </td>-->
                                        </tr>
                                    </c:forEach>
                                </table>
                                <%
                                }
                                %>
                                <% 
                       if ("orderdetail".equals(profile)) {
                                %>
                                <style>
                                    * {
                                        padding: 0;
                                        margin: 0;
                                        box-sizing: border-box;
                                        font-family: "Roboto", sans-serif;
                                    }
                                    body {
                                        background-color: #fff3e0;
                                    }

                                    #order-heading {
                                        background-color: #edf4f7;
                                        position: relative;
                                        border-top-left-radius: 25px;
                                        max-width: 1000px;
                                        padding-top: 20px;
                                        margin: 30px auto 0px;
                                    }
                                    #order-heading .text-uppercase {
                                        font-size: 0.9rem;
                                        color: #777;
                                        font-weight: 600;
                                    }
                                    #order-heading .h4 {
                                        font-weight: 600;
                                    }
                                    #order-heading .h4 + div p {
                                        font-size: 0.8rem;
                                        color: #777;
                                    }
                                    .close {
                                        padding: 10px 15px;
                                        background-color: #777;
                                        border-radius: 50%;
                                        position: absolute;
                                        right: -15px;
                                        top: -20px;
                                    }
                                    .wrapper {
                                        padding: 0px 50px 50px;
                                        max-width: 1000px;
                                        margin: 0px auto 40px;
                                        border-bottom-left-radius: 25px;
                                        border-bottom-right-radius: 25px;
                                    }
                                    .table th {
                                        border-top: none;
                                    }
                                    .table thead tr.text-uppercase th {
                                        font-size: 0.8rem;
                                        padding-left: 0px;
                                        padding-right: 0px;
                                    }
                                    .table tbody tr th,
                                    .table tbody tr td {
                                        font-size: 0.9rem;
                                        padding-left: 0px;
                                        padding-right: 0px;
                                        padding-bottom: 5px;
                                    }
                                    .table-responsive {
                                        height: 100px;
                                    }
                                    .list div b {
                                        font-size: 0.8rem;
                                    }
                                    .list .order-item {
                                        font-weight: 600;
                                        color: #6db3ec;
                                    }
                                    .list:hover {
                                        background-color: #f4f4f4;
                                        cursor: pointer;
                                        border-radius: 5px;
                                    }
                                    label {
                                        margin-bottom: 0;
                                        padding: 0;
                                        font-weight: 900;
                                    }
                                    button.btn {
                                        font-size: 0.9rem;
                                        background-color: #66cdaa;
                                    }
                                    button.btn:hover {
                                        background-color: #5cb99a;
                                    }
                                    .price {
                                        color: #5cb99a;
                                        font-weight: 700;
                                    }
                                    p.text-justify {
                                        font-size: 0.9rem;
                                        margin: 0;
                                    }
                                    .row {
                                        margin: 0px;
                                    }

                                    .subscriptions {
                                        border: 1px solid #ddd;
                                        border-left: 5px solid #ffa500;
                                        padding: 10px;
                                    }
                                    .subscriptions div {
                                        font-size: 0.9rem;
                                    }

                                    @media (max-width: 425px) {
                                        .wrapper {
                                            padding: 20px;
                                        }
                                        body {
                                            font-size: 0.85rem;
                                        }
                                        .subscriptions div {
                                            padding-left: 5px;
                                        }
                                        img + label {
                                            font-size: 0.75rem;
                                        }
                                    }



                                </style>
                                <div
                                    class="d-flex flex-column justify-content-center align-items-center"
                                    id="order-heading"
                                    >
                                    <div class="text-uppercase">
                                        <p>Order detail</p>
                                    </div>
                                    <div class="h4">${currentOrder.getOrderDate()}</div>
                                    <c:if test="${currentOrderDetail.getOrder().getOrderStatus() eq 'wait'}">
                                        <c:set var="colorOrder" value="gray"/>
                                    </c:if>
                                    <c:if test="${currentOrderDetail.getOrder().getOrderStatus() eq 'accepted'}">
                                        <c:set var="colorOrder" value="#5cb99a"/>
                                    </c:if>
                                    <div class="pt-1">
                                        <p style="font-size: 18px">Order #${currentOrder.getId()} is currently<b style="color: ${colorOrder}"> ${currentOrder.getOrderStatus().isEmpty()? "None":currentOrder.getOrderStatus() }</b></p>
                                    </div>
                                    <div>
                                        <c:if test="${currentOrder.getTrackingcode() != null}">
                                            <h6>Tracking code: <b style="color: red">${currentOrder.getTrackingcode()}</b></h6>
                                        </c:if>
                                        
                                    </div>
                                    <a href="profile?profile=ordermanage"><div class="btn close text-white" ">&times;</div></a>
                                </div>
                                <div class="wrapper bg-white">
                                    <!--            <div class="table-responsive">
                                                    <table class="table table-borderless">
                                                        <thead>
                                                            <tr class="text-uppercase text-muted">
                                                                <th scope="col">product</th>
                                                                <th scope="col" class="text-right">total</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <tr>
                                                                <th scope="row">Babyblends: 1meal/day</th>
                                                                <td class="text-right"><b>$69.86</b></td>
                                                            </tr>
                                                        </tbody>
                                                    </table>
                                                </div>-->
                                    <div style="padding: 15px;"> Product List</div>

                                    <c:forEach items="${currentOrderDetailList}" var="o" varStatus="status">
                                        <c:if test="${not empty imamgeList}">
                                            <c:set var="image" value="${imamgeList[status.index]}" />
                                        </c:if>
                                        <div class="order-container d-flex flex-column">
    <div class="d-flex justify-content-start align-items-center list py-2" style="width: 100%; border-bottom: 1px solid #ddd; padding-bottom: 10px;">
        
        <div class="image-container mx-3">
            <c:if test="${not empty image}">
                <img src="${pageContext.request.contextPath}/images/${image.getImage()}" alt="Product Image" width="90" height="80" style="border-radius: 5px; object-fit: cover;"/>
            </c:if>
        </div>

        <a href="information?productId=${o.productDetail.id}" style="text-decoration: none; color: inherit; width: 100%;">
            <div class="order-item" style="width: 100%; font-size: 13px; line-height: 1.5; color: #6db3ec;">
                <b>${o.productDetail.product.name} ${o.productDetail.product.category.name}</b>
                ${o.productDetail.configuration.name} (${o.productDetail.color.name}}
                <br><p style="font-weight: 300; color: black; margin-top: 8px">x${o.getQuantity()}</p><br/>
            </div>
        </a>
    </div>

    <!-- Giá sản phẩm -->
    <div class="price-details" style="display: flex; justify-content: space-between; align-items: center; padding: 5px 0;">
        <div style="font-size: 14px; color: #555;"> 
            <span style="font-weight: bold;">Unit price  </span> <fmt:formatNumber value="${o.getUnitPrice()}" type="number"/>đ
        </div>
        <div style="font-size: 14px; color: #555;"> 
        </div>
        <div>
            <a href="seefeedback?orderdetailid=${o.id}" class="feedback-link" style="font-size: 14px; color: #007bff; text-decoration: underline;">Feedback</a>
        </div>
    </div>
</div>

                                    </c:forEach>


                                    <div class="pt-2 border-bottom mb-3"></div>
                                    <div class="d-flex justify-content-start align-items-center pl-3">
                                        <div class="text-muted">Payment Method</div>
                                        <div class="ml-auto">
                                            <img 
                                                src="http://kimloaithudo.com/uploads/images/20-Tin-tuc/Ship-COD.jpg"
                                                alt=""
                                                width="50"
                                                height="30"
                                                />
                                            <label>${currentOrder.getPaymentMethod()}</label>
                                        </div>
                                    </div>
                                    <div class="d-flex justify-content-start align-items-center pl-3">
                                        <div class="text-muted"><h5 style="font-size: 18px; padding: 15px">Total Price</h5></div>
                                        <div class="ml-auto" style="font-size: 18px;">
                                            <labe><fmt:formatNumber value="${currentOrder.getTotalAmountBefore()}" type="number"/>đ</labe>
                                        </div>
                                    </div>
                                    <!--            <div class="d-flex justify-content-start align-items-center py-1 pl-3">
                                                    <div class="text-muted">Shipping</div>
                                                    <div class="ml-auto">
                                                        <label>Free</label>
                                                    </div>
                                                </div>-->
                                    <div
                                        class="d-flex justify-content-start align-items-center pb-4 pl-3 border-bottom"
                                        >
                                        <div class="text-muted" >
                                            <button style="" class="text-white btn">${currentOrder.getVoucher().getDiscountPercent()}% Discount</button>
                                        </div>
                                        <div class="ml-auto price" style="font-size: 18px;">- <fmt:formatNumber value="${currentOrder.getDiscountAmount()}" type="number"/>đ</div>
                                    </div>
                                    <div
                                        class="d-flex justify-content-start align-items-center pl-3 py-3 mb-4 border-bottom"
                                        >
                                        <div class="text-muted">Today's Total</div>
                                        <div class="ml-auto h5" style="color: red"><fmt:formatNumber value="${currentOrder.getTotalAmountAfter()}" type="number"/>đ</div>

                                    </div>

                                    <c:if test="${currentOrder.getPaymentStatus() eq 'fail'}">
                                        <c:set var="color" value="red"/>
                                    </c:if>
                                    <c:if test="${currentOrder.getPaymentStatus() eq 'pending'}">
                                        <c:set var="color" value="#a9a927"/>
                                    </c:if>
                                    <c:if test="${currentOrder.getPaymentStatus() eq 'completed'}">
                                        <c:set var="color" value="#5cb99a"/>
                                    </c:if>
                                    <div style="display: flex">
                                        <div class="" style="margin-left: 15px;">Payment Status</div>
                                        <c:if test="${currentOrder.getPaymentStatus()== null}">
                                            <div class="ml-auto h5" style="color: black; font-weight: bold;">None</div>
                                        </c:if>

                                        <c:if test="${currentOrder.getPaymentStatus()!= null}">
                                            <div class="ml-auto h5" style="color: ${color}">${currentOrder.getPaymentStatus()}</div>
                                        </c:if>
                                    </div>
                                    <div class="row border rounded p-1 my-3">

                                        <div class="col-md-12 py-3">
                                            <div class="d-flex flex-column align-items start">
                                                <b style="font-size: 17px">Shipping Address</b>
                                                <p class="text-justify pt-2">
                                                    ${currentOrder.getAddress()}
                                                </p>

                                                <p class="text-justify" style="color: black">#Vietnam</p>
                                            </div>
                                        </div>
                                        <div class="col-md-12 py-3">
                                            <div class="d-flex flex-column align-items start">
                                                <b style="font-size: 17px">Customer Information</b>
                                                <p class="text-justify pt-2">
                                                    ${currentOrder.getName()}
                                                </p>
                                                <p class="text-justify pt-2">
                                                    ${currentOrder.getPhone()}
                                                </p>
                                                <b style="font-size: 14px">Email</b>
                                                <p class="text-justify pt-2">
                                                    ${currentOrder.getUser().getEmail()}
                                                </p>
                                                

                                            </div>
                                        </div>
                                    </div>

                                </div>

                                <%
                               }
                                %>
                        </div>
                        <!--CHo nay node de end code de phan truong hop khi nguoi dung chon cac tuy chon-->
                        <!--                <div class="col-md-4">
                                            <div class="p-3 py-5">
                                                <div class="d-flex justify-content-between align-items-center experience"><span>Edit Experience</span><span class="border px-3 p-1 add-experience"><i class="fa fa-plus"></i>&nbsp;Experience</span></div><br>
                                                <div class="col-md-12"><label class="labels">Experience in Designing</label><input type="text" class="form-control" placeholder="experience" value=""></div> <br>
                                                <div class="col-md-12"><label class="labels">Additional Details</label><input type="text" class="form-control" placeholder="additional details" value=""></div>
                                            </div>
                                        </div>-->
                    </div>
                </div>
            </section>
        </div>
    </div>
    <%@include file="footer.jsp" %>

</body>
</html>
