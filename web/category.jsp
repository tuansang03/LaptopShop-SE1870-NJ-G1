<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Aroma Shop - Category</title>
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
        <style>
            .product-container {
                display: grid;
                grid-template-columns: repeat(3, 1fr); /* 3 cột, mỗi cột chia đều */
                gap: 20px; /* Khoảng cách giữa các sản phẩm */
                padding: 20px;
            }
            .product-item {
                border: 1px solid #ccc;
                padding: 10px;
                text-align: center;
                background-color: #f9f9f9;
                transition: transform 0.2s, box-shadow 0.2s;
            }
            .product-item:hover {
                transform: translateY(-5px);
                box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
            }
            .product-item img {
                width: 150px;
                height: 150px;
                object-fit: cover;
                margin-bottom: 10px;
            }
            .brand {
                font-weight: bold; /* Làm đậm thương hiệu */
                color: #808080; /* Màu chữ của thương hiệu */
                font-size: 14px; /* Kích thước chữ */
                margin-bottom: 5px; /* Khoảng cách giữa thương hiệu và tên sản phẩm */
            }
            .product-item h3 {
                font-size: 18px; /* Tăng kích thước chữ tên sản phẩm */
                margin: 10px 0;
                color: #333; /* Màu chữ cho tên sản phẩm */
            }
            .product-item p {
                font-size: 16px; /* Tăng kích thước chữ cho giá tiền */
                color: #007BFF; /* Màu chữ cho giá tiền */
                font-weight: bold; /* Làm đậm giá tiền */
            }
        </style>
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
                        <h1>Shop Category</h1>
                        <nav aria-label="breadcrumb" class="banner-breadcrumb">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="#">Home</a></li>
                                <li class="breadcrumb-item active" aria-current="page">Shop Category</li>
                            </ol>
                        </nav>
                    </div>
                </div>
            </div>
        </section>
        <!-- ================ end banner area ================= -->


        <!-- ================ category section start ================= -->		  
        <section class="section-margin--small mb-5">
            <div class="container">
                <div class="row">
                    <div class="col-xl-3 col-lg-4 col-md-5">
                        <form action="listproduct" method="get"> 
                            <div class="sidebar-categories">
                                <div class="head">Browse Categories</div>
                                <ul class="main-categories">
                                    <li class="common-filter">
                                        <ul>
                                            <!-- Khi tick vào radio button, form sẽ tự động submit -->
                                            <li class="filter-list">
                                                <input class="pixel-radio" type="radio" id="gaming" name="category" value="gaming">
                                                <label for="gaming">Gaming</label>
                                            </li>
                                            <li class="filter-list">
                                                <input class="pixel-radio" type="radio" id="ai" name="category" value="ai">
                                                <label for="ai">AI</label>
                                            </li>
                                            <li class="filter-list">
                                                <input class="pixel-radio" type="radio" id="graphic" name="category" value="graphic">
                                                <label for="graphic">Graphic</label>
                                            </li>
                                            <li class="filter-list">
                                                <input class="pixel-radio" type="radio" id="office" name="category" value="office">
                                                <label for="office">Office</label>
                                            </li>
                                            <li class="filter-list">
                                                <input class="pixel-radio" type="radio" id="workstation" name="category" value="workstation">
                                                <label for="workstation">Workstation</label>
                                            </li>
                                            <li class="filter-list">
                                                <input class="pixel-radio" type="radio" id="student" name="category" value="student">
                                                <label for="student">Student</label>
                                            </li>
                                            <li class="filter-list">
                                                <input class="pixel-radio" type="radio" id="touchscreen" name="category" value="touchscreen">
                                                <label for="touchscreen">Touch screen</label>
                                            </li>
                                            <li class="filter-list">
                                                <input class="pixel-radio" type="radio" id="thinandlight" name="category" value="thinandlight">
                                                <label for="thinandlight">Thin and light</label>
                                            </li>
                                        </ul>
                                    </li>
                                </ul>
                            </div>

                            <div class="sidebar-filter">
                                <div class="top-filter-head">Product Filters</div>
                                <div class="common-filter">
                                    <div class="head">Brands</div>
                                    <ul>
                                        <li class="filter-list"><input class="pixel-radio" type="radio" id="macbook" name="brand" value="macbook"><label>Macbook</label></li>
                                        <li class="filter-list"><input class="pixel-radio" type="radio" id="acer" name="brand" value="acer"><label>Acer</label></li>
                                        <li class="filter-list"><input class="pixel-radio" type="radio" id="asus" name="brand" value="asus"><label>ASUS</label></li>
                                        <li class="filter-list"><input class="pixel-radio" type="radio" id="dell" name="brand" value="dell"><label>Dell</label></li>
                                        <li class="filter-list"><input class="pixel-radio" type="radio" id="hp" name="brand" value="hp"><label>HP</label></li>
                                        <li class="filter-list"><input class="pixel-radio" type="radio" id="lenovo" name="brand" value="lenovo""><label>Lenovo</label></li>
                                        <li class="filter-list"><input class="pixel-radio" type="radio" id="msi" name="brand" value="msi""><label>MSI</label></li>
                                        <li class="filter-list"><input class="pixel-radio" type="radio" id="lg" name="brand" value="lg""><label>LG</label></li>
                                    </ul>
                                </div>
                                <div class="common-filter">
                                    <div class="head">Color</div>
                                    <ul>
                                        <li class="filter-list"><input class="pixel-radio" type="radio" id="black" name="color" value="black"><label>Black</label></li>
                                        <li class="filter-list"><input class="pixel-radio" type="radio" id="white" name="color" value="white"><label>White</label></li>
                                        <li class="filter-list"><input class="pixel-radio" type="radio" id="blue" name="color" value="blue"><label>Blue</label></li>
                                        <li class="filter-list"><input class="pixel-radio" type="radio" id="silver" name="color" value="silver"><label>Silver</label></li>
                                        <li class="filter-list"><input class="pixel-radio" type="radio" id="grey" name="color" value="grey"><label>Grey</label></li>
                                        <li class="filter-list"><input class="pixel-radio" type="radio" id="gold" name="color" value="gold"><label>Gold</label></li>
                                    </ul>
                                </div>
                                <div class="common-filter">
                                    <div class="head">Price</div>
                                    <div class="price-range-area">
                                        <div id="price-range"></div>
                                        <div class="value-wrapper d-flex">
                                            <div id="lower-value"></div>
                                            <span>VNĐ</span>
                                            <div class="to">to</div>
                                            <div id="upper-value"></div>
                                            <span>VNĐ</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <input type="submit" value="TÌM KIẾM" 
                                   style="width: 100px; height: 30px; background-color: background; border-radius: 10px;">
                        </form>
                    </div>


                    <div class="col-xl-9 col-lg-8 col-md-7">
                        <!-- Start Filter Bar -->
                        <div class="filter-bar d-flex flex-wrap align-items-center">
                            <div class="sorting">
                                <select>
                                    <option value="1">Default sorting</option>
                                    <option value="1">Default sorting</option>
                                    <option value="1">Default sorting</option>
                                </select>
                            </div>
                            <div class="sorting mr-auto">
                                <select>
                                    <option value="1">Show 12</option>
                                    <option value="1">Show 12</option>
                                    <option value="1">Show 12</option>
                                </select>
                            </div>
                            <div>
                                <div class="input-group filter-bar-search">
                                    <input type="text" placeholder="Search">
                                    <div class="input-group-append">
                                        <button type="button"><i class="ti-search"></i></button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- End Filter Bar -->








                        <!-- Start Best Seller -->
                        <section class="product-container">
                            <!-- Sử dụng thẻ c:forEach để lặp qua danh sách sản phẩm -->
                            <c:forEach var="p" items="${productlist}">
                                <div class="product-item">
                                    <!-- Hiển thị ảnh sản phẩm -->
                                    <img src="${p.img}" alt="${p.name}">

                                    <!-- Hiển thị thương hiệu -->
                                    <div class="brand">${p.brand}</div>

                                    <!-- Hiển thị tên sản phẩm -->
                                    <h3>${p.name}</h3>

                                    <!-- Hiển thị giá tiền -->
                                    <p>Giá: ${p.price} VND</p>
                                </div>
                            </c:forEach>

                            <!-- Nếu danh sách trống, hiển thị thông báo -->
                            <c:if test="${empty productlist}">
                                <p>Không có sản phẩm nào để hiển thị.</p>
                            </c:if>
                        </section>
                        <!-- End Best Seller -->
                    </div>
                </div>
            </div>
        </section>
        <!-- ================ category section end ================= -->	






        <!-- ================ top product area start ================= -->	
        <section class="related-product-area">
            <div class="container">
                <div class="section-intro pb-60px">
                    <p>Popular Item in the market</p>
                    <h2>Top <span class="section-intro__style">Product</span></h2>
                </div>
                <div class="row mt-30">
                    <div class="col-sm-6 col-xl-3 mb-4 mb-xl-0">
                        <div class="single-search-product-wrapper">
                            <div class="single-search-product d-flex">
                                <a href="#"><img src="img/product/product-sm-1.png" alt=""></a>
                                <div class="desc">
                                    <a href="#" class="title">Gray Coffee Cup</a>
                                    <div class="price">$170.00</div>
                                </div>
                            </div>
                            <div class="single-search-product d-flex">
                                <a href="#"><img src="img/product/product-sm-2.png" alt=""></a>
                                <div class="desc">
                                    <a href="#" class="title">Gray Coffee Cup</a>
                                    <div class="price">$170.00</div>
                                </div>
                            </div>
                            <div class="single-search-product d-flex">
                                <a href="#"><img src="img/product/product-sm-3.png" alt=""></a>
                                <div class="desc">
                                    <a href="#" class="title">Gray Coffee Cup</a>
                                    <div class="price">$170.00</div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-sm-6 col-xl-3 mb-4 mb-xl-0">
                        <div class="single-search-product-wrapper">
                            <div class="single-search-product d-flex">
                                <a href="#"><img src="img/product/product-sm-4.png" alt=""></a>
                                <div class="desc">
                                    <a href="#" class="title">Gray Coffee Cup</a>
                                    <div class="price">$170.00</div>
                                </div>
                            </div>
                            <div class="single-search-product d-flex">
                                <a href="#"><img src="img/product/product-sm-5.png" alt=""></a>
                                <div class="desc">
                                    <a href="#" class="title">Gray Coffee Cup</a>
                                    <div class="price">$170.00</div>
                                </div>
                            </div>
                            <div class="single-search-product d-flex">
                                <a href="#"><img src="img/product/product-sm-6.png" alt=""></a>
                                <div class="desc">
                                    <a href="#" class="title">Gray Coffee Cup</a>
                                    <div class="price">$170.00</div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-sm-6 col-xl-3 mb-4 mb-xl-0">
                        <div class="single-search-product-wrapper">
                            <div class="single-search-product d-flex">
                                <a href="#"><img src="img/product/product-sm-7.png" alt=""></a>
                                <div class="desc">
                                    <a href="#" class="title">Gray Coffee Cup</a>
                                    <div class="price">$170.00</div>
                                </div>
                            </div>
                            <div class="single-search-product d-flex">
                                <a href="#"><img src="img/product/product-sm-8.png" alt=""></a>
                                <div class="desc">
                                    <a href="#" class="title">Gray Coffee Cup</a>
                                    <div class="price">$170.00</div>
                                </div>
                            </div>
                            <div class="single-search-product d-flex">
                                <a href="#"><img src="img/product/product-sm-9.png" alt=""></a>
                                <div class="desc">
                                    <a href="#" class="title">Gray Coffee Cup</a>
                                    <div class="price">$170.00</div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-sm-6 col-xl-3 mb-4 mb-xl-0">
                        <div class="single-search-product-wrapper">
                            <div class="single-search-product d-flex">
                                <a href="#"><img src="img/product/product-sm-1.png" alt=""></a>
                                <div class="desc">
                                    <a href="#" class="title">Gray Coffee Cup</a>
                                    <div class="price">$170.00</div>
                                </div>
                            </div>
                            <div class="single-search-product d-flex">
                                <a href="#"><img src="img/product/product-sm-2.png" alt=""></a>
                                <div class="desc">
                                    <a href="#" class="title">Gray Coffee Cup</a>
                                    <div class="price">$170.00</div>
                                </div>
                            </div>
                            <div class="single-search-product d-flex">
                                <a href="#"><img src="img/product/product-sm-3.png" alt=""></a>
                                <div class="desc">
                                    <a href="#" class="title">Gray Coffee Cup</a>
                                    <div class="price">$170.00</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- ================ top product area end ================= -->		

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


        <!--================ Start footer Area  =================-->	
        <%@include file="footer.jsp" %>
        <!--================ End footer Area  =================-->



        <script src="vendors/jquery/jquery-3.2.1.min.js"></script>
        <script src="vendors/bootstrap/bootstrap.bundle.min.js"></script>
        <script src="vendors/skrollr.min.js"></script>
        <script src="vendors/owl-carousel/owl.carousel.min.js"></script>
        <script src="vendors/nice-select/jquery.nice-select.min.js"></script>
        <script src="vendors/nouislider/nouislider.min.js"></script>
        <script src="vendors/jquery.ajaxchimp.min.js"></script>
        <script src="vendors/mail-script.js"></script>
        <script src="js/main.js"></script>
    </body>
</html>