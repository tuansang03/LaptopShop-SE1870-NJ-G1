<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Aroma Shop - Product Details</title>
        <link rel="icon" href="img/Fevicon.png" type="image/png">
        <link rel="stylesheet" href="vendors/bootstrap/bootstrap.min.css">
        <link rel="stylesheet" href="vendors/fontawesome/css/all.min.css">
        <link rel="stylesheet" href="vendors/themify-icons/themify-icons.css">
        <link rel="stylesheet" href="vendors/linericon/style.css">
        <link rel="stylesheet" href="vendors/nice-select/nice-select.css">
        <link rel="stylesheet" href="vendors/owl-carousel/owl.theme.default.min.css">
        <link rel="stylesheet" href="vendors/owl-carousel/owl.carousel.min.css">
        <link rel="stylesheet" href="css/style.css">

    </head>
    <body>
        <!--================ Start Header Menu Area =================-->
        <%@include file="header.jsp" %>
        <!--================ End Header Menu Area =================-->

        <!-- ================ start banner area ================= -->	
        <section class="blog-banner-area" id="blog">
            <div class="container h-100">
                <div class="blog-banner">
                    <div class="text-center">
                        <h1>Product Information</h1>
                        <nav aria-label="breadcrumb" class="banner-breadcrumb">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="#">Home</a></li>
                                <li class="breadcrumb-item active" aria-current="page">Shop Single</li>
                            </ol>
                        </nav>
                    </div>
                </div>
            </div>
        </section>
        <!-- ================ end banner area ================= -->


        <!--================Single Product Area =================-->
        <div class="product_image_area">
            <div class="container">
                <div class="row s_product_inner">
                    <div class="col-lg-6">
                        <head>
                            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css">
                            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.theme.default.min.css">

                            <style>
                                .main-image {
                                    position: relative;
                                    width: 100%;
                                    height: auto;
                                    margin-bottom: 10px;
                                }

                                .main-image img {
                                    width: 100%;
                                    height: auto;
                                    object-fit: cover;
                                }

                                /* Thumbnail Carousel Section */
                                .thumbnail-carousel {
                                    margin-top: 5px;
                                }

                                .thumbnail-item {
                                    display: inline-block;
                                    cursor: pointer;
                                }

                                .thumbnail-item img {
                                    width: 80px;
                                    height: 80px;
                                    object-fit: cover;
                                    border: 2px solid #ddd;
                                    margin-right: 10px;
                                }

                                .thumbnail-item img:hover {
                                    border-color: #333;
                                }

                                /* Style for navigation buttons */
                                .owl-nav button {
                                    position: absolute;
                                    top: 50%;
                                    transform: translateY(-50%);
                                    background-color: rgba(0, 0, 0, 0.5);
                                    color: white;
                                    font-size: 30px;
                                    border: none;
                                    padding: 10px;
                                    cursor: pointer;
                                }

                                .owl-nav button.owl-prev {
                                    left: -30px;
                                }

                                .owl-nav button.owl-next {
                                    right: -30px;
                                }

                                .owl-nav button:hover {
                                    background-color: rgba(0, 0, 0, 0.8);
                                }

                                .owl-nav button span {
                                    display: block;
                                    width: 30px;
                                    height: 30px;
                                    text-align: center;
                                    line-height: 30px;
                                }

                                /* Spacing between thumbnail carousel and main image */
                                .thumbnail-carousel {
                                    margin-top: 10px;
                                }

                                button {
                                    padding: 10px 20px;
                                    margin: 5px;
                                    border: 1px solid #ccc;
                                    background-color: #f0f0f0;
                                    cursor: pointer;
                                }

                                button.btn-selected {
                                    background-color: #007bff;
                                    color: white;
                                    border: 1px solid #007bff;
                                }
                                /* Style cho container của carousel */
                                .product-carousel-container {
                                    display: flex;
                                    align-items: center;
                                    position: relative;
                                    padding: 10px; /* Thêm khoảng cách giữa sản phẩm và nút bấm */
                                }

                                /* Style cho các nút trượt trái và phải */
                                .carousel-prev, .carousel-next {
                                    background-color: #333;
                                    color: white;
                                    border: none;
                                    padding: 10px;
                                    cursor: pointer;
                                    position: absolute;
                                    top: 50%;
                                    transform: translateY(-50%);
                                    z-index: 1; /* Đảm bảo nút bấm nằm trên các sản phẩm nhưng không đè lên */
                                    outline: none; /* Loại bỏ viền khi nút được chọn */
                                    width: 40px;
                                    height: 40px;
                                    display: flex;
                                    justify-content: center;
                                    align-items: center;
                                    border-radius: 50%;
                                }

                                /* Nút trái */
                                .carousel-prev {
                                    left: -50px; /* Di chuyển nút ra ngoài khung chứa sản phẩm */
                                }

                                /* Nút phải */
                                .carousel-next {
                                    right: -50px; /* Di chuyển nút ra ngoài khung chứa sản phẩm */
                                }

                                /* Style cho khối sản phẩm, thiết lập chế độ ngang */
                                .product-carousel {
                                    display: flex;
                                    overflow-x: auto;
                                    scroll-behavior: smooth;
                                    gap: 15px; /* Khoảng cách giữa các sản phẩm */
                                    padding: 10px 0;
                                    scrollbar-width: none; /* Ẩn thanh cuộn */
                                    position: relative;
                                    z-index: 0; /* Đảm bảo khối sản phẩm nằm bên dưới nút bấm */
                                }

                                /* Ẩn thanh cuộn cho các trình duyệt khác */
                                .product-carousel::-webkit-scrollbar {
                                    display: none;
                                }

                                /* Style cho từng sản phẩm */
                                .product-item {
                                    min-width: 150px;
                                    max-width: 150px;
                                    text-align: center;
                                    padding: 10px;
                                    border: 1px solid #ddd;
                                    border-radius: 8px;
                                    background-color: #f9f9f9;
                                }

                                .product-item img {
                                    width: 100%;
                                    height: auto;
                                    object-fit: contain;
                                }

                                /* Style cho thương hiệu và tên sản phẩm */
                                .brand {
                                    font-size: 12px;
                                    color: #666;
                                }

                                h3 {
                                    font-size: 14px;
                                    margin: 5px 0;
                                }

                                p {
                                    font-size: 14px;
                                    color: #000;
                                }

                                .btn-selected {
                                    background-color: #4CAF50; /* Ví dụ: màu xanh lá cây */
                                    color: white;
                                    font-weight: bold;
                                }

                            </style>
                        </head>


                        <div class="main-image">
                            <div class="owl-carousel owl-theme s_Product_carousel">
                                <c:forEach items="${image}" var="i">
                                    <img class="img-fluid large-image" src="${i.image}" alt="">
                                </c:forEach>
                            </div>
                        </div>

                        <!-- Thumbnails Section -->
                        <div class="thumbnail-carousel owl-carousel owl-theme">
                            <c:forEach items="${image}" var="i">
                                <div class="thumbnail-item">
                                    <img class="img-thumbnail small-image" src="${i.image}" alt="">
                                </div>
                            </c:forEach>
                        </div>

                        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
                        <script src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.min.js"></script>

                        <script>
                            $(document).ready(function () {
                                // Initialize the main image carousel
                                var mainCarousel = $('.s_Product_carousel').owlCarousel({
                                    items: 1,
                                    loop: true,
                                    dots: false,
                                    nav: true,
                                    navText: ['<span>&lt;</span>', '<span>&gt;</span>'],
                                    autoplay: false
                                });

                                // Initialize the thumbnail carousel
                                var thumbnailCarousel = $('.thumbnail-carousel').owlCarousel({
                                    items: 4,
                                    margin: 10,
                                    dots: false,
                                    nav: true,
                                    autoplay: false
                                });

                                // Sync thumbnail click with the main carousel
                                thumbnailCarousel.on('click', '.owl-item', function () {
                                    var index = $(this).index(); // Get the index of the clicked thumbnail
                                    mainCarousel.trigger('to.owl.carousel', index); // Move to the corresponding main image
                                });
                            });
                        </script>

                        </body>



                    </div>
                    <div class="col-lg-5 offset-lg-1">
                        <div class="s_product_text">
                            <h3>${detail.product.name}</h3>
                            <ul class="list">
                                <li><a class="active" href="listproduct?brand%5B%5D=${detail.product.brand.name}"><span>Brand</span>: ${detail.product.brand.name}</a></li>
                                <li><a class="active" href="listproduct?category%5B%5D=${detail.product.category.name}"><span>Category</span>: ${detail.product.category.name}</a></li>
                            </ul>
                            <br>
                            Dung lượng:
                            <form action="information" method="get">
                                <c:forEach items="${config}" var="c">
                                    <button type="submit" name="productId" value="${c.id}" 
                                            class="${co == c.name ? 'btn-selected' : ''}" >
                                        ${c.name}
                                    </button>
                                </c:forEach>
                            </form>

                            Color:
                            <form action="information" method="get">
                                <c:forEach items="${color}" var="c">
                                    <button type="submit" name="productId" value="${c.id}" 
                                            class="${col == c.name ? 'btn-selected' : ''}">
                                        ${c.name}
                                    </button>
                                </c:forEach>
                            </form>

                            <ul class="list">
                                <li><a class="active"><span>Quantity</span>: ${detail.quantity}</a></li>
                            </ul>


                            <br>
                            <h2>${price}</h2>
                            <h5 style="text-decoration: line-through; font-size: 16px; color: #a19c9c; padding-left: 16px ">
                                ${sale}đ
                            </h5>
                            <p>${detail.shortDescription}</p>
                            <div class="card_area d-flex align-items-center">
                                <a class="button primary-btn" href="addtocart?pid=${detail.product.id}&&colorid=${detail.color.id}&&confid=${detail.configuration.id}">Add to Cart</a>
                                <a class="icon_btn" href="addtowishlist?pid=${detail.id}&&uid=${user.id}"><i class="lnr lnr lnr-heart"></i></a>
                            </div>

                            


                            <div class="card_area d-flex align-items-center">
                                <a class="button primary-btn" href="compare?productid=${detail.id}">Compare Product</a>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--================End Single Product Area =================-->

        <!--================Product Description Area =================-->
        <section class="product_description_area">
            <div class="container">
                <ul class="nav nav-tabs" id="myTab" role="tablist">
                    <li class="nav-item">
                        <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">Description</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile"
                           aria-selected="false">Specification</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="contact-tab" data-toggle="tab" href="#contact" role="tab" aria-controls="contact"
                           aria-selected="false">Comments</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="review-tab" data-toggle="tab" href="#review" role="tab" aria-controls="review"
                           aria-selected="false">Reviews</a>
                    </li>
                </ul>
                <div class="tab-content" id="myTabContent">
                    <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                        <p>${detail.description}</p>

                    </div>
                    <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                        <div class="table-responsive">
                            <table class="table">
                                <tbody>
                                    <c:forEach items="${attribute}" var="a">
                                        <tr>
                                            <td>
                                                <h5>${a.attribute.name}</h5>
                                            </td>
                                            <td>
                                                <h5>${a.value}</h5>
                                            </td>
                                        </tr>    
                                    </c:forEach>

                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="tab-pane fade" id="contact" role="tabpanel" aria-labelledby="contact-tab">
                        <div class="row">
                            <div class="col-lg-6">
                                <div class="comment_list">
                                    <div class="review_item">
                                        <div class="media">
                                            <div class="d-flex">
                                                <img src="img/product/review-1.png" alt="">
                                            </div>
                                            <div class="media-body">
                                                <h4>Blake Ruiz</h4>
                                                <h5>12th Feb, 2018 at 05:56 pm</h5>
                                                <a class="reply_btn" href="#">Reply</a>
                                            </div>
                                        </div>
                                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et
                                            dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea
                                            commodo</p>
                                    </div>
                                    <div class="review_item reply">
                                        <div class="media">
                                            <div class="d-flex">
                                                <img src="img/product/review-2.png" alt="">
                                            </div>
                                            <div class="media-body">
                                                <h4>Blake Ruiz</h4>
                                                <h5>12th Feb, 2018 at 05:56 pm</h5>
                                                <a class="reply_btn" href="#">Reply</a>
                                            </div>
                                        </div>
                                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et
                                            dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea
                                            commodo</p>
                                    </div>
                                    <div class="review_item">
                                        <div class="media">
                                            <div class="d-flex">
                                                <img src="img/product/review-3.png" alt="">
                                            </div>
                                            <div class="media-body">
                                                <h4>Blake Ruiz</h4>
                                                <h5>12th Feb, 2018 at 05:56 pm</h5>
                                                <a class="reply_btn" href="#">Reply</a>
                                            </div>
                                        </div>
                                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et
                                            dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea
                                            commodo</p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="review_box">
                                    <h4>Post a comment</h4>
                                    <form class="row contact_form" action="contact_process.php" method="post" id="contactForm" novalidate="novalidate">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <input type="text" class="form-control" id="name" name="name" placeholder="Your Full name">
                                            </div>
                                        </div>
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <input type="email" class="form-control" id="email" name="email" placeholder="Email Address">
                                            </div>
                                        </div>
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <input type="text" class="form-control" id="number" name="number" placeholder="Phone Number">
                                            </div>
                                        </div>
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <textarea class="form-control" name="message" id="message" rows="1" placeholder="Message"></textarea>
                                            </div>
                                        </div>
                                        <div class="col-md-12 text-right">
                                            <button type="submit" value="submit" class="btn primary-btn">Submit Now</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="tab-pane fade" id="review" role="tabpanel" aria-labelledby="review-tab">
                        <div class="row">
                            <div class="col-lg-6">
                                <div class="row total_rate">
                                    <div class="col-6">
                                        <div class="box_total">
                                            <h5>Overall</h5>
                                            <h4>4.0</h4>
                                            <h6>(03 Reviews)</h6>
                                        </div>
                                    </div>
                                    <div class="col-6">
                                        <div class="rating_list">
                                            <h3>Based on 3 Reviews</h3>
                                            <ul class="list">
                                                <li><a href="#">5 Star <i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i
                                                            class="fa fa-star"></i><i class="fa fa-star"></i> 01</a></li>
                                                <li><a href="#">4 Star <i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i
                                                            class="fa fa-star"></i><i class="fa fa-star"></i> 01</a></li>
                                                <li><a href="#">3 Star <i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i
                                                            class="fa fa-star"></i><i class="fa fa-star"></i> 01</a></li>
                                                <li><a href="#">2 Star <i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i
                                                            class="fa fa-star"></i><i class="fa fa-star"></i> 01</a></li>
                                                <li><a href="#">1 Star <i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i
                                                            class="fa fa-star"></i><i class="fa fa-star"></i> 01</a></li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <div class="review_list">
                                    <div class="review_item">
                                        <div class="media">
                                            <div class="d-flex">
                                                <img src="img/product/review-1.png" alt="">
                                            </div>
                                            <div class="media-body">
                                                <h4>Blake Ruiz</h4>
                                                <i class="fa fa-star"></i>
                                                <i class="fa fa-star"></i>
                                                <i class="fa fa-star"></i>
                                                <i class="fa fa-star"></i>
                                                <i class="fa fa-star"></i>
                                            </div>
                                        </div>
                                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et
                                            dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea
                                            commodo</p>
                                    </div>
                                    <div class="review_item">
                                        <div class="media">
                                            <div class="d-flex">
                                                <img src="img/product/review-2.png" alt="">
                                            </div>
                                            <div class="media-body">
                                                <h4>Blake Ruiz</h4>
                                                <i class="fa fa-star"></i>
                                                <i class="fa fa-star"></i>
                                                <i class="fa fa-star"></i>
                                                <i class="fa fa-star"></i>
                                                <i class="fa fa-star"></i>
                                            </div>
                                        </div>
                                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et
                                            dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea
                                            commodo</p>
                                    </div>
                                    <div class="review_item">
                                        <div class="media">
                                            <div class="d-flex">
                                                <img src="img/product/review-3.png" alt="">
                                            </div>
                                            <div class="media-body">
                                                <h4>Blake Ruiz</h4>
                                                <i class="fa fa-star"></i>
                                                <i class="fa fa-star"></i>
                                                <i class="fa fa-star"></i>
                                                <i class="fa fa-star"></i>
                                                <i class="fa fa-star"></i>
                                            </div>
                                        </div>
                                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et
                                            dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea
                                            commodo</p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="review_box">
                                    <h4>Add a Review</h4>
                                    <p>Your Rating:</p>
                                    <ul class="list">
                                        <li><a href="#"><i class="fa fa-star"></i></a></li>
                                        <li><a href="#"><i class="fa fa-star"></i></a></li>
                                        <li><a href="#"><i class="fa fa-star"></i></a></li>
                                        <li><a href="#"><i class="fa fa-star"></i></a></li>
                                        <li><a href="#"><i class="fa fa-star"></i></a></li>
                                    </ul>
                                    <p>Outstanding</p>
                                    <form action="#/" class="form-contact form-review mt-3">
                                        <div class="form-group">
                                            <input class="form-control" name="name" type="text" placeholder="Enter your name" required>
                                        </div>
                                        <div class="form-group">
                                            <input class="form-control" name="email" type="email" placeholder="Enter email address" required>
                                        </div>
                                        <div class="form-group">
                                            <input class="form-control" name="subject" type="text" placeholder="Enter Subject">
                                        </div>
                                        <div class="form-group">
                                            <textarea class="form-control different-control w-100" name="textarea" id="textarea" cols="30" rows="5" placeholder="Enter Message"></textarea>
                                        </div>
                                        <div class="form-group text-center text-md-right mt-3">
                                            <button type="submit" class="button button--active button-review">Submit Now</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!--================End Product Description Area =================-->

        <!--================ Start related Product area =================-->  
        <section class="related-product-area section-margin--small mt-0">
            <div class="container">
                <div class="section-intro pb-60px">
                    <h2><span class="section-intro__style">Related Product</span></h2>
                </div>
                <div class="product-carousel-container">
                    <!-- Nút mũi tên để trượt sang trái -->
                    <button class="carousel-prev" onclick="scrollLeft()">❮</button>

                    <div class="product-carousel">
                        <c:forEach var="p" items="${listproduct}">
                            <a href="information?productId=${p.detail}" class="product-link">
                                <div class="product-item">
                                    <img src="${p.img}" alt="${p.name}">
                                    <div class="brand">${p.brand}</div>
                                    <h6>${p.name}</h6>
                                    <p>Giá: ${p.price}</p>
                                </div>
                            </a>
                        </c:forEach>
                        <c:if test="${empty listproduct}">
                            <p>Không có sản phẩm nào để hiển thị.</p>
                        </c:if>
                    </div>

                    <!-- Nút mũi tên để trượt sang phải -->
                    <button class="carousel-next" onclick="scrollRight()">❯</button>
                </div>

            </div>
        </section>
        <!--================ end related Product area =================-->  	

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