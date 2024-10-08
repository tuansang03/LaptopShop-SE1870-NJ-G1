<%-- 
    Document   : newjsp
    Created on : Sep 16, 2024, 9:27:05 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

        <link rel="stylesheet"
              href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
        <!-- or -->
        <link rel="stylesheet"
              href="https://unpkg.com/boxicons@latest/css/boxicons.min.css">


    </head>
    <body>
        <style>
            .xxxx{
                width: 450px;
                height: 400px;


                margin-left: 10.5%
            }
            .xxxxx{
                width: 100%
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
                font-size: 1.8em; /* Kích thước chữ */
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
                width: 30%
            }
            .hero-banner{
            }
            .button-hero{
                background: wheat;
                color: black;
                border: 1px solid #000;

            }
            .hero-banner::before{
                background: #8bb0d5 !important;
                background-repeat: no-repeat !important;
                background-size: cover !important;
                background-position: center !important;

            }
            .nhot{
                text-align: justify;
                font-weight: 400;
            }
            .tien{
                color: #f36767;
            }
            .card-product__price{
                font-size: 18px !important;
            }
            .card-product__title{
                font-size: 19px !important;
                color: #35516d !important;
            }
            .borderr{
                border:1px solid gray !important;
                box-shadow: 0px 0px 5px gray;
            }
            .cart_button{

            }
            .borderr{
                height: 500px
            }
           
            .card-product__imgOverlay {
                display: flex;
                flex-direction: row;
                justify-content: center; /* Căn giữa theo chiều dọc */
                align-items: center;     /* Căn giữa theo chiều ngang */
                gap: 30px;               /* Khoảng cách giữa các icon */
                padding: 0;              /* Xóa padding mặc định của ul */
                margin: 0;               /* Xóa margin mặc định của ul */
                list-style: none;        /* Xóa dấu chấm trước mỗi li */
                position: absolute;      /* Đảm bảo overlay nằm trên hình ảnh */
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                background: #b4d8ff !important;
                
                
            }

            .card-product__imgOverlay li {
                list-style: none;
                margin-top: 50px;
            }

            .card-product__imgOverlay li a {
                color: black;            /* Tùy chỉnh màu sắc cho icon nếu cần */
                font-size: 18px;
            }
            .product:hover .card-product__imgOverlay{
                opacity: 0.7 !important;
            }

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
                                <a class="button button-hero" href="listproduct">Browse Now</a>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <!--================ Hero banner start =================-->
            
            <!--================ Hero Carousel start =================-->

            <div class="ramdon_title">EXPLORE OUR PRODUCT</div>


            <section class="section-margin mt-0">

                <div class="owl-carousel owl-theme hero-carousel xxx">

                    <c:if test="${empty listR}">
                        <p>No images available.</p>
                    </c:if>

                    <c:if test="${not empty listR}">

                        <c:forEach items="${listR}" var="o" varStatus="status">
                            <div class="hero-carousel__slide xxxx">
                                <div class="xxxxx">
                                    <img src="${o.getImage()}" alt="" class="img-fluid">
                                </div>
                                <a href="information?productId=${o.productDetail.id}" class="hero-carousel__slideOverlay">
                                    <p class="nhot">${o.getProductDetail().getProduct().getBrand().getName()}</p>
                                    <h5 class="" style="color: white">
                                        ${o.getProductDetail().getProduct().getName()} 
                                        ${o.getProductDetail().getProduct().getCategory().getName()} 
                                        ${o.getProductDetail().getConfiguration().getName()} 
                                        (${o.getProductDetail().getColor().getName()})
                                    </h5>
                                    <h5 class="card-product__price nhot tien" >
                                        <!--price form database-->
                                        <fmt:formatNumber value="${o.getProductDetail().getPrice()}" type="number"/>đ
                                    </h5>
                                    <h5 style="text-decoration: line-through; font-size: 16px; color: #a19c9c; padding-left: 16px ">
                                        <!--price form setup-->
                                        <fmt:formatNumber value="${(o.getProductDetail().getPrice()) * 1.2}" type="number"/>đ
                                    </h5>
                                </a>
                            </div>
                        </c:forEach>

                    </c:if>

                </div>
            </section>

            <!-- ================ offer section start ================= --> 
            <section class="offer" id="parallax-1" data-anchor-target="#parallax-1" data-300-top="background-position: 20px 30px" data-top-bottom="background-position: 0 20px">
                <div class="container">
                    <div class="row">
                        <div class="col-xl-5">
                            <div class="offer__content text-center">
                                <h3>UP TO 20% OFF</h3>
                                <h4>Fall Sale</h4>
                                <p>Tap <a href="listproduct">Now</a> To Explore Our <legend style="color: #ef6767; font-weight: bold">BEST LAPTOPS</legend></p>
                                <a class="button button--active mt-3 mt-xl-4" href="listproduct">Shop Now</a>
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
                        <p>Newest Item in the market</p>
                        <h2>Newest <span class="section-intro__style">Products</span></h2>
                    </div>

                    <div class="owl-carousel owl-theme " id="bestSellerCarousel">
                        <c:forEach items="${ListDetail}" var="o3" varStatus="status">
                            <!-- Sử dụng chỉ mục từ ListDetail để lấy ảnh tương ứng từ ListPics -->
                            <c:set var="image" value="${ListPics[status.index]}" />

                            <div class="card text-center card-product borderr">
                                <div class="card-product__img">
                                    <img class="img-fluid" src="${image.getImage()}" alt=""> <!-- Sử dụng 'image' để lấy hình ảnh -->
                                    <ul class="card-product__imgOverlay">
                                        <li style="margin-top: 30px;"><a href="information?productId=${o3.getId()}"><i class="ti-search"></i></a></li>
                                        <li class="cart_button" style="margin-top: 30px;"><a class="ti-shopping-cart" href="addtocart?pid=${o3.getProduct().getId()}&&colorid=${o3.getColor().getId()}&&confid=${o3.getConfiguration().getId()}"></a></li>
                                        <li style="margin-top: 30px;"><a href="#"><i class="ti-heart"></i></a></li>
                                    </ul>

                                </div>
                                <div class="card-body">
                                    <p class="nhot">${o3.getProduct().getBrand().getName()}</p>
                                    <h5 class="card-product__title nhot">

                                        <a href="information?productId=${o3.getId()}">${o3.getProduct().getName()} 

                                            ${o3.getProduct().getCategory().getName()} ${o3.getConfiguration().getName()} (${o3.getColor().getName()})</a>
                                    </h5>
                                    <h5 class="card-product__price nhot tien" style="text-align: center">
                                        <fmt:formatNumber value="${o3.getPrice()}" type="number"/> đ
                                    </h5>
                                    <h5 style="text-decoration: line-through; font-size: 12px; color: #a19c9c; padding-left: 16px">
                                        <fmt:formatNumber value="${(o3.getPrice()) * 1.2}" type="number"/> đ
                                    </h5>
                                </div>
                            </div>
                        </c:forEach>


                    </di

                </div>

            </section>
            <!-- ================ Best Selling item  carousel end ================= --> 
            <!-- ================ Explore Product item  carousel ================= --> 

            <!-- ================ Explore Product item  carousel end ================= --> 

            <!-- ================ Blog section start ================= -->  
            <section class="blog">
                <div class="container">
                    <div class="section-intro pb-60px">
                        <p>Popular Item in the market</p>
                        <h2>Latest <span class="section-intro__style">News</span></h2>
                    </div>

                    <div class="row">

                        <c:forEach items="${listP}" var="p" varStatus="status">
                            <style>
                                .tach{
                                    margin-right: 15px;
                                }

                            </style>

                            <div class="post tach">
                                <div class="card card-blog">
                                    <div class="card-blog__img">
                                        <img class="card-img rounded-0" src="${p.thumbnail}" alt="">
                                    </div>
                                    <div class="card-body">
                                        <ul class="card-blog__info">
                                            <li><a href="#" class="bx bxs-user"> ${p.user.userName}</a></li>
                                            <li><i class="bx bx-laptop"></i>${p.brand.name}</li>
                                            <li><i class="bx bx-calendar"></i>${p.publishDate}</li>
                                        </ul>
                                        <h4 class="card-blog__title"><a href="postdetail?id=${p.id}">${p.tittle}</a></h4>
                                        <p>${p.shortContent}</p>
                                        <a class="card-blog__link" href="postdetail?id=${p.id}">Read More <i class="ti-arrow-right"></i></a>
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
