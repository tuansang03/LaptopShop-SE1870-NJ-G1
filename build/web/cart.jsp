<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
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

    </head>
    <body>
        <!--================ Start Header Menu Area =================-->
        <%@include file="header.jsp" %>
        <!--================ End Header Menu Area =================-->

        <!-- ================ start banner area ================= -->	
        <section class="blog-banner-area" id="category">
            <div class="container h-100">
                <div class="blog-banner">
                    <div class="text-center">
                        <h1>Shopping Cart</h1>
                        <nav aria-label="breadcrumb" class="banner-breadcrumb">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="#">Home</a></li>
                                <li class="breadcrumb-item active" aria-current="page">Shopping Cart</li>
                            </ol>
                        </nav>
                    </div>
                </div>
            </div>
        </section>
        <!-- ================ end banner area ================= -->



        <!--================Cart Area =================-->
        <section class="cart_area">
            <div class="container">
                <div class="cart_inner">
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th scope="col">Product</th>
                                    <th scope="col">Price</th>
                                    <th scope="col">Quantity</th>
                                    <th scope="col">Total</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:set value="0" var="total"/>
                                <c:forEach items="${requestScope.listCartItem}" var="p">
                                    <tr>
                                        <td>
                                            <div class="media">
                                                <div>
                                                    <c:forEach items="${requestScope.listImages}" var="i">
                                                        <div class="d-flex">
                                                            <c:if test="${p.getProductdetail().getId() == i.getProductDetail().getId()}">
                                                                <img style="width: 150px" src="${i.getImage()}" alt="">
                                                            </c:if>
                                                        </div>
                                                    </c:forEach>
                                                </div>
                                                <div class="media-body">
                                                    <a href="#">${p.getProductdetail().getProduct().getName()}</a>
                                                    <p>${p.getProductdetail().getColor().getName()}</p>
                                                </div>

                                            </div>
                                        </td>
                                        <td>
                                            <h5 style="font-weight: bold;">
                                                <!--price form database-->
                                                <fmt:formatNumber value="${p.getProductdetail().getPrice()}" type="number"/>đ

                                            </h5>
                                            <h5 style="text-decoration: line-through; font-size: 12px; color: #a19c9c; padding-left: 16px">
                                                <!--price form setup-->
                                                <fmt:formatNumber value="${(p.getProductdetail().getPrice()) * 1.2}" type="number"/>đ
                                            </h5>
                                        </td>
                                        <td >
                                            <div style="display: flex; flex-direction: column">
                                                <div class="product_count">
                                                    <div style="display: flex; align-items: center; justify-content: left; text-align: center;">
                                                        <a style="font-size: 12px; padding: 5px 10px; text-decoration: none; color: #000;" href="changeQuantity?num=1&cid=${p.getCart().getId()}&pdtid=${p.getProductdetail().getId()}">+</a>
                                                        <input type="text" name="qty" id="sst" maxlength="12" value="${p.getQuantity()}" title="Quantity:" style="text-align: center;padding: 5px;">
                                                        <a style="font-size: 12px; padding: 5px 10px; text-decoration: none; color: #000;" href="changeQuantity?num=-1&cid=${p.getCart().getId()}&pdtid=${p.getProductdetail().getId()}">-</a>
                                                    </div>
                                                </div>
                                                <div>
                                                    <a style="margin-left: 20px;" href="deleteCart?cid=${p.getCart().getId()}&pid=${p.getProductdetail().getId()}">Delete</a>
                                                </div>

                                            </div>
                                        </td>
                                        <td>
                                            <h5>
                                                <fmt:formatNumber value="${(p.getQuantity()) * (p.getProductdetail().getPrice())}" type="number"/>đ
                                            </h5>
                                            <c:set value="${(p.getQuantity()) * (p.getProductdetail().getPrice())}" var="price"/>
                                            <c:set value="${price + total}" var="total"/>
                                        </td>
                                    </tr>
                                </c:forEach>

                                <tr class="bottom_button">
                                    <td>
                                        <a class="button" href="#">Update Cart</a>
                                    </td>
                                    <td>

                                    </td>
                                    <td>

                                    </td>
                                    <td>
                                        <div class="cupon_text d-flex align-items-center">
                                            <input type="text" placeholder="Coupon Code">
                                            <a class="primary-btn" href="#">Apply</a>
                                            <a class="button" href="#">Have a Coupon?</a>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>

                                    </td>
                                    <td>

                                    </td>
                                    <td>
                                        <h5>Subtotal</h5>
                                    </td>
                                    <td>
                                        <h5><fmt:formatNumber value="${total}" type="number"/>đ</h5>
                                    </td>
                                </tr>
                                <tr class="shipping_area">
                                    <td class="d-none d-md-block">

                                    </td>
                                    <td>

                                    </td>
                                    <td>
                                        <h5>Shipping</h5>
                                    </td>
                                    <td>
                                        <div class="shipping_box">
                                            <ul class="list">
                                                <li><a href="#">Flat Rate: $5.00</a></li>
                                                <li><a href="#">Free Shipping</a></li>
                                                <li><a href="#">Flat Rate: $10.00</a></li>
                                                <li class="active"><a href="#">Local Delivery: $2.00</a></li>
                                            </ul>
                                            <h6>Calculate Shipping <i class="fa fa-caret-down" aria-hidden="true"></i></h6>
                                            <select class="shipping_select">
                                                <option value="1">Bangladesh</option>
                                                <option value="2">India</option>
                                                <option value="4">Pakistan</option>
                                            </select>
                                            <select class="shipping_select">
                                                <option value="1">Select a State</option>
                                                <option value="2">Select a State</option>
                                                <option value="4">Select a State</option>
                                            </select>
                                            <input type="text" placeholder="Postcode/Zipcode">
                                            <a class="gray_btn" href="#">Update Details</a>
                                        </div>
                                    </td>
                                </tr>
                                <tr class="out_button_area">
                                    <td class="d-none-l">

                                    </td>
                                    <td class="">

                                    </td>
                                    <td>

                                    </td>
                                    <td>
                                        <div class="checkout_btn_inner d-flex align-items-center">
                                            <a class="gray_btn" href="#">Continue Shopping</a>
                                            <a class="primary-btn ml-2" href="#">Proceed to checkout</a>
                                        </div>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </section>
        <!--================End Cart Area =================-->



        <!--================ Start footer Area  =================-->	
        <%@include file="footer.jsp" %>
        <!--================ End footer Area  =================-->



        <script src="vendors/jquery/jquery-3.2.1.min.js"></script>
        <script src="vendors/bootstrap/bootstrap.bundle.min.js"></script>
        <script src="vendors/skrollr.min.js"></script>
        <script src="vendors/owl-carousel/owl.carousel.min.js"></script>
        <script src="vendors/nice-select/jquery.nice-select.min.js"></script>
        <script src="vendors/jquery.ajaxchimp.min.js"></script>
        <script src="vendors/mail-script.js"></script>
        <script src="js/main.js"></script>
    </body>
</html>