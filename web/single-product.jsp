<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
                    <div class="col-md-12">
                        <c:if test="${msg != null}">
                            <div class="alert alert-danger" role="alert">
                                <strong>Error:</strong> ${msg}
                            </div>
                        </c:if>
                        <c:if test="${success != null}">
                            <div class="alert alert-success" role="alert">
                                <strong>Success:</strong> ${success}
                            </div>
                        </c:if>
                    </div>
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

                                /* Phần nền mờ cho overlay */
                                /* Nền mờ cho overlay */
                                #overlay {
                                    display: none; /* Ẩn mặc định */
                                    position: fixed;
                                    top: 0;
                                    left: 0;
                                    width: 100%;
                                    height: 100%;
                                    background-color: rgba(0, 0, 0, 0.6); /* Màu nền mờ */
                                    z-index: 1000;
                                    overflow-y: auto; /* Cho phép cuộn khi nội dung dài */
                                }

                                /* Nội dung popup */
                                .popup_content {
                                    background-color: #fff;
                                    margin: 5% auto;
                                    padding: 20px;
                                    border-radius: 10px;
                                    width: 70%;
                                    box-shadow: 0px 4px 16px rgba(0, 0, 0, 0.3);
                                    z-index: 1001;
                                    margin-top: 100px;
                                }

                                /* Nút đóng */
                                .close_btn {
                                    float: right;
                                    font-size: 24px;
                                    font-weight: bold;
                                    cursor: pointer;
                                }

                                .close_btn:hover {
                                    color: red;
                                }

                                /* Căn chỉnh các bình luận */
                                .comment_list {
                                    max-height: 500px;
                                    overflow-y: auto; /* Cuộn cho danh sách bình luận nếu quá dài */
                                    padding: 10px;
                                }

                                /* Giao diện mỗi bình luận */
                                .review_item {
                                    background-color: #f9f9f9;
                                    padding: 15px;
                                    margin-bottom: 10px;
                                    border-radius: 8px;
                                    box-shadow: 0px 2px 8px rgba(0, 0, 0, 0.1);
                                }

                                .review_item h4 {
                                    margin: 0;
                                    font-size: 16px;
                                    color: #333;
                                }

                                .review_item h5 {
                                    margin: 5px 0;
                                    font-size: 14px;
                                    color: #999;
                                }

                                .review_item p {
                                    font-size: 14px;
                                    color: #555;
                                }

                                /* Giao diện cho phản hồi */
                                .review_item.reply {
                                    background-color: #eef1f7;
                                    margin-left: 20px;
                                    padding-left: 20px;
                                    border-left: 2px solid #007bff;
                                }

                                .reply h4 {
                                    font-size: 15px;
                                    color: #007bff;
                                }

                                .reply span {
                                    font-size: 12px;
                                    color: #555;
                                }

                                /* Nút Show All */
                                #showAllBtn {
                                    background-color: #007bff;
                                    color: white;
                                    padding: 10px 20px;
                                    border: none;
                                    border-radius: 5px;
                                    cursor: pointer;
                                    font-size: 14px;
                                }

                                #showAllBtn:hover {
                                    background-color: #0056b3;
                                }


                            </style>

                        </head>


                        <body>
                            <div class="main-image">
                                <div class="owl-carousel owl-theme s_Product_carousel">
                                    <c:forEach items="${image}" var="i">
                                        <img class="img-fluid large-image" src="${pageContext.request.contextPath}/images/${i.image}" alt="">
                                    </c:forEach>
                                </div>
                            </div>

                            <!-- Thumbnails Section -->
                            <div class="thumbnail-carousel owl-carousel owl-theme">
                                <c:forEach items="${image}" var="i">
                                    <div class="thumbnail-item">
                                        <img class="img-thumbnail small-image" src="${pageContext.request.contextPath}/images/${i.image}" alt="">
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
                            <h6>Rating ${rating} <span style="color: gold;">&#9733;</span> Rated: ${number} Sold: ${numsale}</h6>
                            <ul class="list">
                                <li><a class="active" href="listproduct?brand%5B%5D=${detail.product.brand.name}"><span>Brand</span>: ${detail.product.brand.name}</a></li>
                                <li><a class="active" href="listproduct?category%5B%5D=${detail.product.category.name}"><span>Category</span>: ${detail.product.category.name}</a></li>
                            </ul>
                            <br>
                            Storage :
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
                                ${sale}
                            </h5>
                            <p>${detail.shortDescription}</p>
                            <div class="card_area d-flex align-items-center">
                                <c:if test="${sessionScope.user != null}">
                                    <a class="button primary-btn" href="addtocart?pid=${detail.product.id}&&colorid=${detail.color.id}&&confid=${detail.configuration.id}">Add to Cart</a>
                                </c:if>
                                <c:if test="${sessionScope.user == null}">
                                    <a class="button primary-btn" href="login">Add to Cart</a>
                                </c:if>
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
                                    <!-- Biến đếm để giới hạn hiển thị 3 bình luận đầu tiên -->
                                    <c:set var="index" value="0" />
                                    <c:forEach items="${commentList}" var="comment">
                                        <!-- Hiển thị chỉ 3 bình luận đầu tiên -->
                                        <c:if test="${index < 3}">
                                            <!-- Hiển thị bình luận chính -->
                                            <c:if test="${comment.repplyCommentId == null}">
                                                <div class="review_item">
                                                    <div class="media">
                                                        <div class="media-body">
                                                            <h4>${comment.user.fullName}<c:if test="${comment.user.role.id == 2}">(Saler)</c:if></h4>
                                                            <h5><fmt:formatDate value="${comment.commentDate}" pattern="dd/MM/yyyy HH:mm" /></h5>
                                                        </div>
                                                    </div>
                                                    <p>${comment.commentContent}</p>
                                                </div>

                                                <!-- Hiển thị phản hồi cho bình luận chính -->
                                                <div class="replies">
                                                    <c:forEach items="${commentList}" var="reply">
                                                        <c:if test="${reply.repplyCommentId == comment.id}">
                                                            <div class="review_item reply">
                                                                <div class="media">
                                                                    <div class="media-body">
                                                                        <h4>${reply.user.fullName}<c:if test="${reply.user.role.id == 2}">(Saler)</c:if> <span style="font-size: 12px; color: #007bff;">(Reply)</span></h4>
                                                                        <h5><fmt:formatDate value="${reply.commentDate}" pattern="dd/MM/yyyy HH:mm" /></h5>
                                                                    </div>
                                                                </div>
                                                                <p>${reply.commentContent}</p>
                                                            </div>
                                                        </c:if>
                                                    </c:forEach>
                                                </div>
                                                <hr>
                                            </c:if>
                                        </c:if>
                                        <!-- Tăng biến đếm lên 1 -->
                                        <c:set var="index" value="${index + 1}" />
                                    </c:forEach>
                                </div>

                                <!-- Nút Show All - Chỉ hiện khi có nhiều hơn 3 bình luận -->
                                <c:if test="${fn:length(commentList) > 3}">
                                    <button type="button" id="showAllBtn">Show All</button>
                                </c:if>

                                <!-- Overlay màn hình đè lên -->
                                <div id="overlay">
                                    <div class="popup_content">
                                        <span class="close_btn">&times;</span>
                                        <h2>All Comment</h2>
                                        <div class="comment_list">
                                            <!-- Hiển thị toàn bộ bình luận trong popup -->
                                            <c:forEach items="${commentList}" var="comment">
                                                <c:if test="${comment.repplyCommentId == null}">
                                                    <div class="review_item">
                                                        <div class="media">
                                                            <div class="media-body">
                                                                <h4>${comment.user.fullName}<c:if test="${comment.user.role.id == 2}">(Saler)</c:if></h4>
                                                                <h5><fmt:formatDate value="${comment.commentDate}" pattern="dd/MM/yyyy HH:mm" /></h5>
                                                            </div>
                                                        </div>
                                                        <p>${comment.commentContent}</p>
                                                    </div>

                                                    <!-- Hiển thị phản hồi -->
                                                    <div class="replies">
                                                        <c:forEach items="${commentList}" var="reply">
                                                            <c:if test="${reply.repplyCommentId == comment.id}">
                                                                <div class="review_item reply">
                                                                    <div class="media">
                                                                        <div class="media-body">
                                                                            <h4>${reply.user.fullName}<c:if test="${reply.user.role.id == 2}">(Saler)</c:if> <span>(Reply)</span></h4>
                                                                            <h5><fmt:formatDate value="${reply.commentDate}" pattern="dd/MM/yyyy HH:mm" /></h5>
                                                                        </div>
                                                                    </div>
                                                                    <p>${reply.commentContent}</p>
                                                                </div>
                                                            </c:if>
                                                        </c:forEach>
                                                    </div>
                                                    <hr>
                                                </c:if>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>





                            </div>

                            <c:if test="${sessionScope.user!=null}">
                                <div class="col-lg-6">
                                    <div class="review_box p-4 shadow-sm border rounded">
                                        <h4 class="mb-4">Post a Comment</h4>
                                        <form action="submitComment" method="post">
                                            <!-- Comment Content -->
                                            <div class="form-group mb-3">
                                                <label for="commentContent" class="form-label">Comment</label>
                                                <textarea class="form-control" id="commentContent" name="commentContent" rows="4" placeholder="Write your comment here..." required></textarea>
                                            </div>

                                            <!-- Submit Button -->
                                            <div class="text-end">
                                                <button type="submit" class="btn btn-primary">Submit</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </c:if>

                            <c:if test="${sessionScope.user==null}">
                                <div class="col-lg-6">
                                    <div class="review_box p-4 shadow-sm border rounded">
                                        <h4 class="mb-4" >Post a Comment</h4>

                                        <!-- Comment Content -->
                                        <div class="form-group mb-3">
                                            <label for="commentContent" class="form-label" style="text-align: center">Login Now to post a comment</label>

                                        </div>
                                        <div style="text-align: center; padding: 6px; background: #0060ce; ">
                                            <a href="login" style="color: white">Login</a>
                                        </div>
                                        <!-- Submit Button -->
                                    </div>
                                </div>
                            </c:if>

                        </div>
                    </div>

                    <div class="tab-pane fade" id="review" role="tabpanel" aria-labelledby="review-tab">
                        <div class="row">
                            <!-- Feedback list section -->
                            <div class="col-lg-8">
                                <div class="comment_list">
                                    <c:forEach items="${feedbacklist}" var="feedback">
                                        <!-- Only show feedback with a valid rating -->
                                        <c:if test="${feedback.getRating() != null && feedback.getRating() > 0 && feedback.getReplyFeedbackId() == 0}">
                                            <div class="review_item" data-rating="${feedback.getRating()}">
                                                <div class="media">
                                                    <div class="media-body">
                                                        <h4>${feedback.getUser().getFullName()} ${feedback.getRating()}&#9733;</h4>
                                                        <h5><fmt:formatDate value="${feedback.getFeedbackDate()}" pattern="dd/MM/yyyy HH:mm" /></h5>
                                                    </div>
                                                </div>
                                                <div class="reply-item">
                                                    <p class="content-wrap">${feedback.getFeedbackContent()}</p>
                                                </div>

                                                <!-- Replies section (show replies only if the parent feedback is visible) -->
                                                <div class="replies">
                                                    <c:forEach items="${feedbacklist}" var="reply">
                                                        <c:if test="${reply.getReplyFeedbackId() == feedback.getId()}">
                                                            <div class="review_item reply" data-rating="${reply.getRating() != null ? reply.getRating() : 0}">
                                                                <div class="media">
                                                                    <div class="media-body">
                                                                        <h4>${reply.getUser().getFullName()} 
                                                                            <c:if test="${reply.getUser().getRole().getId() == 2}">(Saler)</c:if>
                                                                                <span style="font-size: 12px; color: #007bff;">(Reply)</span>
                                                                            </h4>
                                                                            <h5><fmt:formatDate value="${reply.getFeedbackDate()}" pattern="dd/MM/yyyy HH:mm" /></h5>
                                                                    </div>
                                                                </div>
                                                                <p>${reply.getFeedbackContent()}</p>
                                                            </div>
                                                        </c:if>
                                                    </c:forEach>
                                                </div>
                                                <hr>
                                            </div>
                                        </c:if>
                                    </c:forEach>
                                </div>
                            </div>
                            <!-- Filter section for ratings -->
                            <div class="col-lg-3">
                                Rating: ${rating}<span style="color: gold;">&#9733;</span>
                                <c:forEach var="i" begin="0" end="4">
                                    <h4 class="rating-button" style="cursor: pointer;" 
                                        onclick="filterByRating(${5 - i}, this)">
                                        <c:forEach var="j" begin="0" end="4">
                                            <span style="color: ${j < (5 - i) ? 'gold' : 'gray'};">&#9733;</span>
                                        </c:forEach>
                                        ${5 - i} Star (${ratingcount.get(i)} rated)
                                    </h4>
                                </c:forEach>

                                <!-- Button to show all feedback -->
                                <button onclick="showAllFeedback()" 
                                        style="padding: 8px 16px; font-size: 15px; color: black; border-radius: 6px; cursor: pointer;">
                                    Show All Feedback
                                </button>

                            </div>
                        </div>
                    </div>

                    <!-- JavaScript for filtering feedback by rating and showing all feedback -->
                    <script>
                        function filterByRating(rating, element) {
                            const feedbackItems = document.querySelectorAll('.review_item');
                            const replies = document.querySelectorAll('.replies');

                            // Remove "selected" class from all rating buttons
                            const ratingButtons = document.querySelectorAll('.rating-button');
                            ratingButtons.forEach(button => button.classList.remove('selected'));

                            // Add "selected" class to the clicked button
                            element.classList.add('selected');

                            feedbackItems.forEach(item => {
                                const itemRating = parseInt(item.getAttribute('data-rating'), 10);
                                // Show items with matching rating
                                if (itemRating === rating) {
                                    item.style.display = 'block'; // Show items with matching rating
                                } else {
                                    item.style.display = 'none'; // Hide items with other ratings
                                }
                            });

                            // Also show/hide replies based on parent feedback visibility
                            replies.forEach(replyContainer => {
                                const parentFeedback = replyContainer.closest('.review_item');
                                if (parentFeedback && parentFeedback.style.display !== 'none') {
                                    replyContainer.style.display = 'block'; // Show replies if parent feedback is visible
                                } else {
                                    replyContainer.style.display = 'none'; // Hide replies if parent feedback is hidden
                                }
                            });
                        }

                        // Function to show all feedback
                        function showAllFeedback() {
                            const feedbackItems = document.querySelectorAll('.review_item');
                            const replies = document.querySelectorAll('.replies');

                            // Show all feedback items
                            feedbackItems.forEach(item => {
                                item.style.display = 'block'; // Show all feedback
                            });

                            // Show all replies
                            replies.forEach(replyContainer => {
                                replyContainer.style.display = 'block'; // Show all replies
                            });

                            // Remove "selected" class from all rating buttons
                            const ratingButtons = document.querySelectorAll('.rating-button');
                            ratingButtons.forEach(button => button.classList.remove('selected'));
                        }
                    </script>

                    <!-- CSS for the selected button -->
                    <style>
                        .rating-button.selected {
                            background-color: #007bff;  /* Blue background for selected button */
                            color: white;               /* White text for selected button */
                            font-weight: bold;          /* Make the text bold */
                        }
                    </style>



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
                                <img src="${pageContext.request.contextPath}/images/${p.img}" alt="${p.name}">
                                <div class="brand">${p.brand}</div>
                                <h4>${p.name}</h4>
                                <p>Giá: ${p.price}</p>
                            </div>
                        </a>
                    </c:forEach>
                    <c:if test="${empty listproduct}">
                        <p>There is no product to show.</p>
                    </c:if>
                </div>

                <!-- Nút mũi tên để trượt sang phải -->
                <button class="carousel-next" onclick="scrollRight()">❯</button>
            </div>

        </div>
    </section>
    <!--================ end related Product area =================-->  	
    <script>
        // Lấy các phần tử
        const showAllBtn = document.getElementById('showAllBtn');
        const overlay = document.getElementById('overlay');
        const closeBtn = document.querySelector('.close_btn');

        // Khi bấm nút Show All
        showAllBtn.onclick = function () {
            overlay.style.display = 'block'; // Hiển thị overlay
        };

        // Khi bấm nút đóng
        closeBtn.onclick = function () {
            overlay.style.display = 'none'; // Ẩn overlay
        };

        // Đóng overlay khi click ngoài khu vực popup
        window.onclick = function (event) {
            if (event.target === overlay) {
                overlay.style.display = 'none';
            }
        };
    </script>

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