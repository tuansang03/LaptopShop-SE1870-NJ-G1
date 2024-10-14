<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Aroma Shop - Checkout</title>
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
                        <h1>Product Checkout</h1>
                        <nav aria-label="breadcrumb" class="banner-breadcrumb">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="#">Home</a></li>
                                <li class="breadcrumb-item active" aria-current="page">Checkout</li>
                            </ol>
                        </nav>
                    </div>
                </div>
            </div>
        </section>
        <!-- ================ end banner area ================= -->


        <!--================Checkout Area =================-->
        <section class="checkout_area section-margin--small">
            <div class="container">
                <form action="applyVoucher">
                    <h6 class="text-center" style="color: green; font-family: sans-serif;">${success}</h6>
                    <h6 class="text-center" style="color: red; font-family: sans-serif;">${error}</h6>
                    <div class="cupon_area" style="display: flex; justify-content: space-around; align-items: center;">
                        <input value="${voucherInput}" type="text" name="voucher" placeholder="Enter coupon code">
                        <Button type="submit" class="button button-coupon">Apply Coupon</Button>
                    </div>
                    <input value="${totalPrice}" name="totalBefore" type="hidden"/>
                </form>
                <div class="billing_details">
                    <div class="row">
                        <form action="processingCheckOut" >
                            <div style="display: flex">
                                <div class="col-lg-8">
                                    <h3>Billing Details</h3>
                                    <div class="row contact_form">
                                        <div class="col-md-12 form-group">
                                            Name: <input required value="${name}" type="text" class="form-control" name="name"   placeholder="Name">
                                        </div>
                                        <div class="col-md-12 form-group">
                                           Address: <input value="${address}" required type="text" class="form-control" name="address"   placeholder="Address">
                                        </div>
                                        <div class="col-md-12 form-group">
                                           Phone: <input value="${phone}" required type="number" class="form-control" name="phone"   placeholder="Phone">
                                        </div>
                                        <div class="col-md-12 form-group">
                                           Email: <input readonly value="${email}" type="email" class="form-control" name="email"  placeholder="Email">
                                        </div>
                                        <div class="col-md-12 form-group mb-0">
                                            <div class="creat_account">
                                                <h3>Shipping Details</h3>
                                            </div>
                                        Note: <textarea class="form-control" name="message" rows="1" placeholder="Order Notes"></textarea>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-4">
                                    <div class="order_box">
                                        <h2>Your Order</h2>
                                        <h2>Product</h2>
                                        <ul class="list">
                                            <c:forEach items="${cartItem}" var="c">
                                                <div style="display: flex">
                                                    <div style="margin-right: 6px">
                                                        <li>
                                                            <c:forEach items="${listImages}" var="i">
                                                                <c:if test="${i.getProductDetail().getId() == c.getProductdetail().getId()}">
                                                                    <img style="width: 40px" src="${pageContext.request.contextPath}/images/${i.getImage()}" alt="">
                                                                </c:if>
                                                            </c:forEach>
                                                        </li>
                                                    </div>
                                                    <div>
                                                        <li>${c.getProductdetail().getProduct().getName()}</li>
                                                        <li>Quantity: ${c.getQuantity()}</li>
                                                        <li>
                                                            Price: 
                                                            <fmt:formatNumber value="${c.getProductdetail().getPrice() * c.getQuantity()}" type="number"/>đ
                                                        </li>
                                                        <c:set value="${c.getProductdetail().getPrice() * c.getQuantity()}" var="price"></c:set>
                                                        <c:set value="${price + totalBefore}" var="totalBefore"></c:set>
                                                        </div>
                                                    </div>
                                                    <hr style="border: none; height: 0.5px; background-color: #cec2c2;">

                                            </c:forEach>
                                        </ul>
                                        <h2>Total</h2>
                                        <ul>
                                            <li>Provisional Total: 
                                                <input type="hidden" value="${totalBefore}" name="totalPriceBeforeDiscount"/>
                                                <fmt:formatNumber value="${totalBefore}" type="number"/>đ
                                            </li>
                                            <li>Voucher: 
                                                <c:if test="${voucher == null}">
                                                    0%
                                                </c:if>
                                                <c:if test="${voucher != null}">
                                                    <c:set value="${voucher.getDiscountPercent()}" var="voucherDiscount"></c:set>
                                                    ${voucherDiscount}%
                                                    <input type="hidden" value="${voucher.getId()}" name="voucherID"/>
                                                </c:if>
                                            </li>
                                            <li style="display: flex; align-items: center">Total: 
                                                <p style="font-size: 18px; padding-left: 8px; color: red; margin-bottom: 0;">
                                                    <c:if test="${voucher == null}">
                                                        <fmt:formatNumber value="${totalBefore}" type="number" />đ
                                                        <input type="hidden" value="${totalBefore}" name="totalPriceAfterDiscount"/>
                                                    </c:if>   
                                                    <c:if test="${voucher != null}">
                                                        <fmt:formatNumber value="${totalBefore - (voucherDiscount/100) * totalBefore}" type="number"/>đ
                                                    </c:if>
                                                <p/>
                                            </li>
                                        </ul>
                                        <div class="payment_item">
                                            <input checked type="radio"  name="selector" value="nhanhang">
                                            <label >Payment upon receipt</label>
                                        </div>
                                        <div class="payment_item">
                                            <input type="radio" name="selector"  value="payment">
                                            <label >Pay now</label>
                                        </div>

                                        <div class="text-center">
                                            <button class="button button-paypal">Proceed to Payment</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>                                           
                    </div>
                </div>
            </div>
        </section>
        <!--================End Checkout Area =================-->



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