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
                <p style="color: red; margin-bottom: 0;" class="text-center" >${error}</p>
                <div class="cart_inner">
                    <div class="table-responsive">
                        <div style="display: flex;justify-content: right;margin-right: 10%;font-size: 16px;" >
                            <a onclick="doDeleteALLProduct(${cartID})" href="#">Delete All</a>
                        </div>
                        <table class="table" style="table-layout: fixed;">
                            <thead>
                                <tr>
                                    <th style="width: 2%"  scope="col">
                                    </th>
                                    <th style="width:45%;" scope="col">Product</th>
                                    <th style="width: 20%;" scope="col">Price</th>
                                    <th style="width: 15%;" scope="col">Quantity</th>
                                    <th style="width: 18%;" scope="col">Total</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:set value="0" var="total"/>
                                <c:forEach items="${requestScope.listCartItem}" var="p">
                                    <tr>
                                        <td>
                                            <form action="selectProduct">
                                                <input type="hidden" name="pid" value="${p.getProductdetail().getId()}">
                                                <input name="selected" ${p.getStatus().equals("checked") ? 'checked': ''} onchange="this.form.submit()" type="checkbox">
                                            </form>
                                        </td>
                                        <td>
                                            <div class="media">
                                                <div>
                                                    <c:forEach items="${requestScope.listImages}" var="i">
                                                        <div class="d-flex">
                                                            <c:if test="${p.getProductdetail().getId() == i.getProductDetail().getId()}">
                                                                <img style="width: 80px" src="${pageContext.request.contextPath}/images/${i.getImage()}" alt="">
                                                            </c:if>
                                                        </div>
                                                    </c:forEach>
                                                </div>
                                                <div class="media-body">
                                                    <a href="information?productId=${p.getProductdetail().getId()}">${p.getProductdetail().getProduct().getName()} (${p.getProductdetail().getProduct().getCategory().getName()}, ${p.getProductdetail().getProduct().getBrand().getName()}, ${p.getProductdetail().getConfiguration().getName()})</a>
                                                    <p>${p.getProductdetail().getColor().getName()}</p>
                                                    <p style="font-size: 11px;">${p.getProductdetail().getConfiguration().getName()}</p>
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
                                            <div style="display: flex; flex-direction: column;">
                                                <div>
                                                    <div style="display: flex; align-items: center; justify-content: space-between; text-align: center;border: 1px solid;border-color: #ded7d7;border-radius: 6px; width: 78%;">
                                                        <a style="font-size: 14px; padding: 5px 10px; text-decoration: none; color: #000;" href="changeQuantity?num=-1&cid=${p.getCart().getId()}&pdtid=${p.getProductdetail().getId()}">-</a>
                                                        <form action="inputQuantityProduct">
                                                            <input type="hidden" name="pdtid" value="${p.getProductdetail().getId()}"/>
                                                            <input type="text" name="qty" id="sst" maxlength="12" value="${p.getQuantity()}" 
                                                                   title="Quantity:" style="all: unset; width: 100%;" 
                                                                   onchange="this.form.submit();">
                                                        </form>
                                                        <a style="font-size: 14px; padding: 5px 10px; text-decoration: none; color: #000;" href="changeQuantity?num=1&cid=${p.getCart().getId()}&pdtid=${p.getProductdetail().getId()}">+</a>

                                                    </div>
                                                </div>
                                                <div>
                                                    <a style="margin-left: 20%;" onclick="doDeleteProduct(${p.getCart().getId()}, ${p.getProductdetail().getId()}, '${p.getProductdetail().getProduct().getName()}')" href="#">Delete</a>
                                                </div>

                                            </div>
                                        </td>
                                        <td>
                                            <h5>
                                                <fmt:formatNumber value="${(p.getQuantity()) * (p.getProductdetail().getPrice())}" type="number"/>đ
                                            </h5>
                                            <c:set value="${(p.getQuantity()) * (p.getProductdetail().getPrice())}" var="price"/>
                                            <c:if test="${p.getStatus() == 'checked'}">
                                                <c:set value="${price + total}" var="total"/>
                                            </c:if>
                                        </td>
                                    </tr>
                                </c:forEach>


                                <tr>
                                    <td>

                                    </td>
                                    <td>

                                    </td>
                                    <td>
                                        <h5>Subtotal</h5>
                                    </td>
                                    <td>
                                        <h5>
                                            <fmt:formatNumber value="${total}" type="number"/>đ
                                        </h5>
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
                                            <a class="primary-btn ml-2" href="loadInfoCheckout">Proceed to checkout</a>
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
