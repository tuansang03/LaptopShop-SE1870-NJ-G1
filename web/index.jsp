<%-- 
    Document   : newjsp
    Created on : Sep 16, 2024, 9:27:05 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Aroma Shop - Home</title>
        <link rel="icon" href="img/Fevicon.png" type="image/png">
        <link rel="stylesheet" href="vendors/bootstrap/bootstrap.min.css">
        <link rel="stylesheet" href="vendors/fontawesome/css/all.min.css">
        <link rel="stylesheet" href="vendors/themify-icons/themify-icons.css">
        <link rel="stylesheet" href="vendors/nice-select/nice-select.css">
        <link rel="stylesheet" href="vendors/owl-carousel/owl.theme.default.min.css">
        <link rel="stylesheet" href="vendors/owl-carousel/owl.carousel.min.css">

        <link rel="stylesheet" href="css/style.css">



    </head>
    <body>
        <style>
            .xxxx{
                width: 540px;
                height: 400px;


                margin-left: 10.5%
            }
            .xxxx img{
                width: auto;
                height: 401px;
                box-shadow: 4px 33px 1px gray;
                background-size: cover;
                background-position: center;
            }
            *{
                font-family: "Farro", sans-serif;
            }
            .ramdon_title{
                font-size: 2.4em; /* Kích thước chữ */
                font-weight: 400; /* Đậm */
                color: #333; /* Màu chữ */
                text-align: center; /* Căn giữa */
                padding: 20px; /* Khoảng cách bên trong */
                background-color: #f9f9f9; /* Nền nhẹ cho tiêu đề */
                border-radius: 8px; /* Bo tròn góc */
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* Đổ bóng nhẹ */
                margin: 0 0 20px 0; /* Khoảng cách bên ngoài */
            }
            .post{
                width: 32%
            }
            .hero-banner{
                
               
        </style>
        <!--================ Start Header Menu Area =================-->
        <%@include file="header.jsp" %>
        <!--================ End Header Menu Area =================-->
        <main class="site-main">

            <!--================ Hero banner start =================-->
            <section class="hero-banner">
                <div class="container">
                    <div class="row no-gutters align-items-center pt-60px">
                        <div class="col-5 d-none d-sm-block">
                            <div class="hero-banner__img">
                                <img class="img-fluid" src="img/home/hero-banner.png" alt="">
                            </div>
                        </div>
                        <div class="col-sm-7 col-lg-6 offset-lg-1 pl-4 pl-md-5 pl-lg-0">
                            <div class="hero-banner__content" >
                                <h4 class="fontx">Laptop Shop</h4>
                                <h1 class="fontx">Browse Our Best Laptop</h1>
                                <p class="fontx">Unleash Your Potential – Quality Laptops for Every Journey</p>
                                <a class="button button-hero" href="category.jsp">Browse Now</a>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <!--================ Hero banner start =================-->

            <!--================ Hero Carousel start =================-->

            <div class="ramdon_title">SOME RANDOM PRODUCT</div>


            <section class="section-margin mt-0">

                <div class="owl-carousel owl-theme hero-carousel xxx">

                    <c:if test="${empty listH}">
                        <p>No images available.</p>
                    </c:if>

                    <c:if test="${not empty listH}">

                        <c:forEach items="${listH}" var="o" varStatus="status">
                            <div class="hero-carousel__slide xxxx">
                                <img src="${o}" alt="" class="img-fluid">
                                <a href="#" class="hero-carousel__slideOverlay">
                                    <h3>Wireless Headphone</h3>
                                    <p>Accessories Item</p>
                                </a>
                            </div>
                        </c:forEach>
                    </c:if>

                </div>
            </section>


            <!-- ================ trending product section start ================= -->  
            <section class="section-margin calc-60px">
                <div class="container">
                    <div class="section-intro pb-60px">
                        <p>Popular Item in the market</p>
                        <h2><span class="section-intro__style">Trending Product</span></h2>
                    </div>
                    <div class="row">
                        <div class="col-md-6 col-lg-4 col-xl-3">
                            <div class="card text-center card-product">
                                <div class="card-product__img">
                                    <img class="card-img" src="img/product/product1.png" alt="">
                                    <ul class="card-product__imgOverlay">
                                        <li><button><i class="ti-search"></i></button></li>
                                        <li><button><i class="ti-shopping-cart"></i></button></li>
                                        <li><button><i class="ti-heart"></i></button></li>
                                    </ul>
                                </div>
                                <div class="card-body">
                                    <p>Accessories</p>
                                    <h4 class="card-product__title"><a href="single-product.jsp">Quartz Belt Watch</a></h4>
                                    <p class="card-product__price">$150.00</p>
                                </div>
                            </div>
                        </div>
                        
                        </div>
                    </div>
                </div>
            </section>
            <!-- ================ trending product section end ================= -->  


            <!-- ================ offer section start ================= --> 
            <section class="offer" id="parallax-1" data-anchor-target="#parallax-1" data-300-top="background-position: 20px 30px" data-top-bottom="background-position: 0 20px">
                <div class="container">
                    <div class="row">
                        <div class="col-xl-5">
                            <div class="offer__content text-center">
                                <h3>Up To 50% Off</h3>
                                <h4>Winter Sale</h4>
                                <p>Him she'd let them sixth saw light</p>
                                <a class="button button--active mt-3 mt-xl-4" href="#">Shop Now</a>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <!-- ================ offer section end ================= --> 

            <!-- ================ Best Selling item  carousel ================= --> 
            <section class="section-margin calc-60px">
                <div class="container">
                    <div class="section-intro pb-60px">
                        <p>Popular Item in the market</p>
                        <h2>Best <span class="section-intro__style">Sellers</span></h2>
                    </div>
                    <div class="owl-carousel owl-theme" id="bestSellerCarousel">
                        <div class="card text-center card-product">
                            <div class="card-product__img">
                                <img class="img-fluid" src="img/product/product1.png" alt="">
                                <ul class="card-product__imgOverlay">
                                    <li><button><i class="ti-search"></i></button></li>
                                    <li><button><i class="ti-shopping-cart"></i></button></li>
                                    <li><button><i class="ti-heart"></i></button></li>
                                </ul>
                            </div>
                            <div class="card-body">
                                <p>Accessories</p>
                                <h4 class="card-product__title"><a href="single-product.jsp">Quartz Belt Watch</a></h4>
                                <p class="card-product__price">$150.00</p>
                            </div>
                        </div>

                        <div class="card text-center card-product">
                            <div class="card-product__img">
                                <img class="img-fluid" src="img/product/product2.png" alt="">
                                <ul class="card-product__imgOverlay">
                                    <li><button><i class="ti-search"></i></button></li>
                                    <li><button><i class="ti-shopping-cart"></i></button></li>
                                    <li><button><i class="ti-heart"></i></button></li>
                                </ul>
                            </div>
                            <div class="card-body">
                                <p>Beauty</p>
                                <h4 class="card-product__title"><a href="single-product.jsp">Women Freshwash</a></h4>
                                <p class="card-product__price">$150.00</p>
                            </div>
                        </div>

                        <div class="card text-center card-product">
                            <div class="card-product__img">
                                <img class="img-fluid" src="img/product/product3.png" alt="">
                                <ul class="card-product__imgOverlay">
                                    <li><button><i class="ti-search"></i></button></li>
                                    <li><button><i class="ti-shopping-cart"></i></button></li>
                                    <li><button><i class="ti-heart"></i></button></li>
                                </ul>
                            </div>
                            <div class="card-body">
                                <p>Decor</p>
                                <h4 class="card-product__title"><a href="single-product.jsp">Room Flash Light</a></h4>
                                <p class="card-product__price">$150.00</p>
                            </div>
                        </div>

                        <div class="card text-center card-product">
                            <div class="card-product__img">
                                <img class="img-fluid" src="img/product/product4.png" alt="">
                                <ul class="card-product__imgOverlay">
                                    <li><button><i class="ti-search"></i></button></li>
                                    <li><button><i class="ti-shopping-cart"></i></button></li>
                                    <li><button><i class="ti-heart"></i></button></li>
                                </ul>
                            </div>
                            <div class="card-body">
                                <p>Decor</p>
                                <h4 class="card-product__title"><a href="single-product.jsp">Room Flash Light</a></h4>
                                <p class="card-product__price">$150.00</p>
                            </div>
                        </div>

                        <div class="card text-center card-product">
                            <div class="card-product__img">
                                <img class="img-fluid" src="img/product/product1.png" alt="">
                                <ul class="card-product__imgOverlay">
                                    <li><button><i class="ti-search"></i></button></li>
                                    <li><button><i class="ti-shopping-cart"></i></button></li>
                                    <li><button><i class="ti-heart"></i></button></li>
                                </ul>
                            </div>
                            <div class="card-body">
                                <p>Accessories</p>
                                <h4 class="card-product__title"><a href="single-product.jsp">Quartz Belt Watch</a></h4>
                                <p class="card-product__price">$150.00</p>
                            </div>
                        </div>

                        <div class="card text-center card-product">
                            <div class="card-product__img">
                                <img class="img-fluid" src="img/product/product2.png" alt="">
                                <ul class="card-product__imgOverlay">
                                    <li><button><i class="ti-search"></i></button></li>
                                    <li><button><i class="ti-shopping-cart"></i></button></li>
                                    <li><button><i class="ti-heart"></i></button></li>
                                </ul>
                            </div>
                            <div class="card-body">
                                <p>Beauty</p>
                                <h4 class="card-product__title"><a href="single-product.jsp">Women Freshwash</a></h4>
                                <p class="card-product__price">$150.00</p>
                            </div>
                        </div>

                        <div class="card text-center card-product">
                            <div class="card-product__img">
                                <img class="img-fluid" src="img/product/product3.png" alt="">
                                <ul class="card-product__imgOverlay">
                                    <li><button><i class="ti-search"></i></button></li>
                                    <li><button><i class="ti-shopping-cart"></i></button></li>
                                    <li><button><i class="ti-heart"></i></button></li>
                                </ul>
                            </div>
                            <div class="card-body">
                                <p>Decor</p>
                                <h4 class="card-product__title"><a href="single-product.jsp">Room Flash Light</a></h4>
                                <p class="card-product__price">$150.00</p>
                            </div>
                        </div>

                        <div class="card text-center card-product">
                            <div class="card-product__img">
                                <img class="img-fluid" src="img/product/product4.png" alt="">
                                <ul class="card-product__imgOverlay">
                                    <li><button><i class="ti-search"></i></button></li>
                                    <li><button><i class="ti-shopping-cart"></i></button></li>
                                    <li><button><i class="ti-heart"></i></button></li>
                                </ul>
                            </div>
                            <div class="card-body">
                                <p>Decor</p>
                                <h4 class="card-product__title"><a href="single-product.jsp">Room Flash Light</a></h4>
                                <p class="card-product__price">$150.00</p>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <!-- ================ Best Selling item  carousel end ================= --> 

            <!-- ================ Blog section start ================= -->  
            <section class="blog">
                <div class="container">
                    <div class="section-intro pb-60px">
                        <p>Popular Item in the market</p>
                        <h2>Latest <span class="section-intro__style">News</span></h2>
                    </div>

                    <div class="row">
                        
                        <c:forEach items="${listP}" var="p" varStatus="status">>
                        
                            <div class="post">
                            <div class="card card-blog">
                                <div class="card-blog__img">
                                    <img class="card-img rounded-0" src="${p.thumbnail}" alt="">
                                </div>
                                <div class="card-body">
                                    <ul class="card-blog__info">
                                        <li><a href="#">${p.user.userName}</a></li>
                                        <li><i class="ti-comments-smiley"></i>${p.brand.name}</li>
                                    </ul>
                                    <h4 class="card-blog__title"><a href="single-blog.jsp">${p.tittle}</a></h4>
                                    <p>${p.shortContent}</p>
                                    <a class="card-blog__link" href="#">Read More <i class="ti-arrow-right"></i></a>
                                </div>
                            </div>
                        </div>

                        </c:forEach>
                    </div>
                </div>
            </section>
            <!-- ================ Blog section end ================= -->  

            <!-- ================ Subscribe section start ================= --> 
            <section class="subscribe-position">
                <div class="container">
                    <div class="subscribe text-center">
                        <h3 class="subscribe__title">Get Update From Anywhere</h3>
                        <p>Bearing Void gathering light light his eavening unto dont afraid</p>
                        <div id="mc_embed_signup">
                            <form target="_blank" action="https://spondonit.us12.list-manage.com/subscribe/post?u=1462626880ade1ac87bd9c93a&amp;id=92a4423d01" method="get" class="subscribe-form form-inline mt-5 pt-1">
                                <div class="form-group ml-sm-auto">
                                    <input class="form-control mb-1" type="email" name="EMAIL" placeholder="Enter your email" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Your Email Address '" >
                                    <div class="info"></div>
                                </div>
                                <button class="button button-subscribe mr-auto mb-1" type="submit">Subscribe Now</button>
                                <div style="position: absolute; left: -5000px;">
                                    <input name="b_36c4fd991d266f23781ded980_aefe40901a" tabindex="-1" value="" type="text">
                                </div>

                            </form>
                        </div>

                    </div>
                </div>
            </section>
            <!-- ================ Subscribe section end ================= --> 



        </main>


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
