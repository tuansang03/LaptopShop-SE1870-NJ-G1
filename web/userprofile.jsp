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
            <div class="container-fluid rounded bg-white mt-5 mb-5 margin-left">
                <div class="row">
                    <div class="col-md-2 option-profile">
                        <ul>
                            <li><a href="profile?profile=info" class="option">Profile Information</a></li>
                            <li><a href="profile?profile=ordermanage" class="option">Order Manage</a></li>
                        </ul>
                    </div>
                    <div class="col-md-1.5 border-right">
                        <div class="d-flex flex-column align-items-center text-center p-3 py-5">
                            <img class="rounded-circle mt-5" width="150px" 
                                 src="${pageContext.request.contextPath}/images/${img.dell1.jp}" alt="Image" width="200""><span class="font-weight-bold">${sessionScope.user.fullName}</span><span class="text-black-50"></span><span> </span></div>
                    </div>
                    <!--CHo nay node de code de phan truong hop khi nguoi dung chon cac tuy chon-->
                    <div class="col-md-7 border-right ">

                        <% 
                                   String profile = request.getParameter("profile");
                                   if ("info".equals(profile)) { 
                        %>
                        <div class="p-3 py-5">
                            <div class="d-flex justify-content-between align-items-center mb-3">
                                <h4 class="text-right">Profile Settings</h4>
                            </div>
                            <div class="row mt-2">
                                <div class="col-md-12">
                                    <label class="labels">Full Name</label>
                                    <input type="text" class="form-control" placeholder="enter user profile" value="${sessionScope.user.getFullName()}">
                                </div>  
                            </div>
                            <div class="row mt-3">
                                <div class="col-md-12">
                                    <label class="labels">Address</label>
                                    <input type="text" class="form-control" placeholder="enter address line 1" value="${orderInfo.getAddress()}">
                                </div>
                                <div class="col-md-12">
                                    <label class="labels">Email ID</label>
                                    <input type="text" class="form-control" placeholder="enter email id" value="${sessionScope.user.getEmail()}">
                                </div>
                                <div class="col-md-12">
                                    <label class="labels">Phone</label>
                                    <input type="text" class="form-control" placeholder="phone" value="${orderInfo.getPhone()}">
                                </div>
                            </div>
                            <div class="row mt-3">
                                <div class="col-md-6">
                                    <label class="labels">UserName</label>
                                    <input type="text" class="form-control" placeholder="enter username" value="${sessionScope.user.getUserName()}">
                                </div>
                                <div class="col-md-6">
                                    <label class="labels">Password</label>
                                    <input type="text" class="form-control" value="*********" placeholder="enter password" readonly>
                                </div>
                            </div>
                            <div class="mt-5 text-center">
                                <button class="btn btn-primary profile-button" type="button">Save Profile</button>
                            </div>
                        </div>
                        <% 
                            } 
                        %>

                        <% 
            

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
        }
        th, td {
            padding: 12px;
            text-align: left;
            border: 1px solid #ddd;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
    </style>
    <table>
        <thead>
            <tr>
                <th></th>
                <th>Product</th>
                <th>Total Quantity</th>
                <th>Order Date</th>
                <th>Total Price</th>
                <th>OrderStatus</th>
                <th></th>
            </tr>
        </thead>
        
        <style>
            .product_image{
                width: 30%;
                height: 70px;
            }
            .product_image img{
                width: 100%;
                height: 100%;
            }
            
            
        </style>
<!--            orderListInfo-->
<%--<c:set var="count" value="0" />--%>
   <c:forEach items="${orderListInfo}" var="o" varStatus="status">
        <c:set var="orderId" value="${o.order.id}" />
        <c:choose>
            <c:when test="${not empty orderId}">
                <c:set var="listOrder" value="${orderDAO.getOrderDetailsByOrderId(orderId)}" />
                <tr>
                    <td>${o.order.id}</td>
                    <td>
                       <c:if test="${not empty listOrder}">
     <c:forEach items="${listOrder}" var="o1">
        <div>
           
            <div class="product_name">
                ${o1.productDetail.product.name} <!-- Tên sản phẩm -->
            </div>
             
<!--            <div class="product_image">
                <img src="">
            </div>-->
        </c:forEach>
        </div>
 
</c:if>
<c:if test="${empty listOrder}">
    <div>Không có sản phẩm nào trong đơn hàng.</div>
</c:if>

                    </td>
                    <td>${o.quantity}</td> 
                    <td>${o.order.orderDate}</td>
                    <td><fmt:formatNumber value="${o.order.totalAmountAfter}" type="number"/>đ</td>
                    <td>${o.order.orderStatus}</td> 
                    
                    <td><a href="profile?profile=orderdetail&id=${o.order.id}"><i class='bx bx-show-alt'></i></a></td>
                </tr>
            </c:when>
        </c:choose>
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
            <div class="pt-1">
                <p >Order #${currentOrderDetail.getId()} is currently<b class="text-danger"> ${currentOrderDetail.getOrder().getOrderStatus()}</b></p>
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

            <div class="d-flex justify-content-start align-items-center list py-1">
                <div><b></b></div>
                <div class="mx-3">
                    <img
                        src="https://images.pexels.com/photos/206959/pexels-photo-206959.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"
                        alt="apple"
                        class=""
                        width="30"
                        height="30"
                        />
                </div>
                
                <div class="order-item">${o.getProductDetail().getProduct().getName()}  </div>&nbsp x${o.getQuantity()}
                
                
            </div>
                <div style="padding-left: 1.7%;"> Unit price: &nbsp<fmt:formatNumber value="${o.getUnitPrice()}" type="number"/>đ</div>
                <div style="padding-left: 1.7%;"> Total price: &nbsp<fmt:formatNumber value="${(o.getUnitPrice()*o.getQuantity())}" type="number"/>đ</div>

                </c:forEach>
            

            <div class="pt-2 border-bottom mb-3"></div>
            <div class="d-flex justify-content-start align-items-center pl-3">
                <div class="text-muted">Payment Method</div>
                <div class="ml-auto">
                    <img
                        src="https://www.freepnglogos.com/uploads/mastercard-png/mastercard-logo-logok-15.png"
                        alt=""
                        width="30"
                        height="30"
                        />
                    <label>${currentOrder.getPaymentMethod()}</label>
                </div>
            </div>
                <div class="d-flex justify-content-start align-items-center pl-3">
                <div class="text-muted">Total Price</div>
                <div class="ml-auto">
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
                <div class="text-muted">
                    <button class="text-white btn">${currentOrder.getVoucher().getDiscountPercent()}% Discount</button>
                </div>
                <div class="ml-auto price">- <fmt:formatNumber value="${currentOrder.getDiscountAmount()}" type="number"/>đ</div>
            </div>
            <div
                class="d-flex justify-content-start align-items-center pl-3 py-3 mb-4 border-bottom"
                >
                <div class="text-muted">Today's Total</div>
                <div class="ml-auto h5" style="color: red"><fmt:formatNumber value="${currentOrder.getTotalAmountAfter()}" type="number"/>đ</div>
            </div>
            <div class="row border rounded p-1 my-3">
               
                <div class="col-md-12 py-3">
                    <div class="d-flex flex-column align-items start">
                        <b>Shipping Address</b>
                        <p class="text-justify pt-2">
                            ${currentOrder.getAddress()}
                        </p>
                        <p class="text-justify" style="color: black">Vietnam</p>
                    </div>
                </div>
                        <div class="col-md-12 py-3">
                    <div class="d-flex flex-column align-items start">
                        <b>Phone Number</b>
                        <p class="text-justify pt-2">
                            ${currentOrder.getPhone()}
                        </p>
                        
                    </div>
                </div>
            </div>
            <div class="pl-3 font-weight-bold">Related Subsriptions</div>
            <div class="d-sm-flex justify-content-between rounded my-3 subscriptions">
                <div>
                    <b>#9632</b>
                </div>
                <div>May 22, 2017</div>
                <div>Status: Processing</div>
                <div>Total: <b> $68.8 for 10 items</b></div>
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
