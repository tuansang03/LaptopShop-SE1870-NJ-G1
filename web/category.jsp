<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

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
                        <h1>Laptop List</h1>
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
                            <div class="sidebar-filter">
                                <div class="top-filter-head">Browse Brands</div>
                                <div class="common-filter">
                                    <ul>
                                        <c:forEach items="${brandlist}" var="b">
                                            <li class="filter-list">
                                                <input class="pixel-radio" type="checkbox" name="brand[]" value="${b.name}"
                                                       <c:if test="${fn:contains(selectedBrands, b.name)}">checked</c:if>>
                                                <label>${b.name}</label>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </div>
                            </div>

                            <div class="sidebar-filter">
                                <div class="top-filter-head">Browse Categories</div>
                                <div class="common-filter">
                                    <ul>
                                        <c:forEach items="${categorylist}" var="c">
                                            <li class="filter-list">
                                                <input class="pixel-radio" type="checkbox" name="category[]" value="${c.name}"
                                                       <c:if test="${fn:contains(selectedCategories, c.name)}">checked</c:if>>
                                                <label>${c.name}</label>
                                            </li>    
                                        </c:forEach>
                                    </ul>
                                </div>
                            </div>

                            <input type="submit" value="TÌM KIẾM" 
                                   style="width: 100px; height: 30px; background-color: background; border-radius: 10px;">
                        </form>

                    </div>


                    <div class="col-xl-9 col-lg-8 col-md-7">
                        <!-- Start Filter Bar -->
                        <div class="input-group filter-bar-search">
                            <div class="sorting">
                                <form action="listproduct">
                                    <select name="price">
                                        <option value="default">Default sorting</option>
                                        <option value="asc">Sort by price ascending</option>
                                        <option value="desc">Sort by price descending</option>   
                                    </select>
                                    <input type="submit"value="Sort" />    
                                </form>

                            </div>
                            <div>
                                <div class="input-group filter-bar-search">
                                    <form action="listproduct">
                                        <input type="text" name="name" placeholder="Search">
                                        <input type="submit"value="Search" />    
                                    </form>

                                </div>
                            </div>

                        </div>
                        <!-- End Filter Bar -->
                        <c:if test="${!empty productlist}">
                            <p class="product-container">(Tìm thấy ${size} sản phẩm)</p>
                        </c:if>




                        <!-- Start Best Seller -->
                        <section class="product-container">
                            <!-- Sử dụng thẻ c:forEach để lặp qua danh sách sản phẩm -->
                            <c:forEach var="p" items="${productlist}">
                                <!-- Mỗi thẻ sản phẩm trở thành một liên kết -->
                                <a href="information?productId=${p.detail}" class="product-link">
                                    <div class="product-item">
                                        <!-- Hiển thị ảnh sản phẩm -->
                                        <img src="${p.img}" alt="${p.name}">

                                        <!-- Hiển thị thương hiệu -->
                                        <div class="brand">${p.brand}</div>

                                        <!-- Hiển thị tên sản phẩm -->
                                        <h3>${p.name}</h3>

                                        <!-- Hiển thị giá tiền -->
                                        <p>Giá: ${p.price}</p>
                                    </div>
                                </a>
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