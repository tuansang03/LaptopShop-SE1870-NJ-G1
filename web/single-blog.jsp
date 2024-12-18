<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>  
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Aroma Shop - Blog Details</title>
        <link rel="icon" href="img/Fevicon.png" type="image/png">
        <link rel="stylesheet" href="vendors/bootstrap/bootstrap.min.css">
        <link rel="stylesheet" href="vendors/fontawesome/css/all.min.css">
        <link rel="stylesheet" href="vendors/themify-icons/themify-icons.css">
        <link rel="stylesheet" href="vendors/linericon/style.css">
        <link rel="stylesheet" href="vendors/owl-carousel/owl.theme.default.min.css">
        <link rel="stylesheet" href="vendors/owl-carousel/owl.carousel.min.css">

        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        

        <!--================ Start Header Menu Area =================-->

        <c:if test="${!(sessionScope.admin != null || sessionScope.sale != null)}">
            <%@include file="header.jsp" %>
        </c:if>
  

        
        <!--================ End Header Menu Area =================-->


        <style>
            .h-100 {

                background-image: url('https://img.freepik.com/free-photo/laptop-pencils-arrangement_23-2148128294.jpg?t=st=1726983433~exp=1726987033~hmac=a7a8fb714723647f753b6f315b3f3c8ac4de65ba64313295a5fcc38db4055518&w=1380');
                background-size: cover; /* Để hình ảnh phù hợp với kích thước phần tử */
                background-position: center;
                width: 100%; 
                height: 400px; 

            }

            *{
                font-style: normal;
            }
            .image img{
                width: 100%;
            }
    .container-fluid {
        max-width: 100%;
        padding: 0 15px;
    }

    .blog_area {
        padding-top: 80px;
    }

    .posts-list {
        padding-right: 15px;
    }

    .single-post {
        margin-bottom: 50px;
    }

    .feature-img img {
        width: 100%;
        height: auto;
    }

    .blog_details h2 {
        font-size: 20px;
        line-height: 1.5;
        margin-bottom: 20px;
    }
    h2{
        font-size: 25px !important;
    }
    h3{
        font-size: 20px !important;
    }

    .blog_info {
        margin-bottom: 30px;
    }

    .quotes {
        
        line-height: 1.8;
        margin-bottom: 30px;
        padding: 20px;
        background: #f9f9ff;
        border-left: 5px solid #ffba00;
    }

    .navigation-area {
        padding: 50px 0;
        border-top: 1px solid #f1f1f1;
    }

    .nav-left, .nav-right {
        display: flex;
        align-items: center;
    }

    .nav-left .thumb, .nav-right .thumb {
        margin-right: 15px;
    }

    .thumb img {
        max-width: 100px;
        height: auto;
    }

    .arrow {
        margin-right: 15px;
    }

    .arrow span {
        font-size: 20px;
        color: #fff;
    }
    p{
        text-align: justify;
    }
    li{
        text-align: justify;
    }

     .detials a{
        font-size: 18px;
        color: #000;
                
     }.detials a:hover{
         transform: translate(10px,0px);
     }
    .wp-block-heading{
        font-size: 22px;
        color: #4e555b;
        padding: 10px;
    }
    .wp-post-image{
        width: 100%;
    }
    .single-post{
        width: 1300px;
        
    }
    img {width: 100%}
    .post_tag{
        text-align: left;
    }
    
    
</style>



        </style>


        <!-- ================ start banner area ================= -->	
        <section class="blog-banner-area x1" id="blog">
            <div class="container h-100">
                <div class="blog-banner">
                    <div class="text-center">
                        <h1>Post Details</h1>
                        <nav aria-label="breadcrumb" class="banner-breadcrumb">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="#">Home</a></li>
                                <li class="breadcrumb-item active" aria-current="page">Post Details</li>
                            </ol>
                        </nav>
                    </div>
                </div>
            </div>
        </section>
        <!-- ================ end banner area ================= -->



        <!--================Blog Area =================-->
        <section class="blog_area single-post-area py-80px section-margin--small">


            <div class="container">
                <div class="row">
                    <div class="col-lg-8 posts-list">
                        <div class="single-post row">
                            <div class="col-lg-12">
                                <div class="feature-img">
                                    <img class="img-fluid" src="${postDetail.thumbnail}" alt="${postDetail.thumbnail}">
                                </div>
                            </div>
                            <div class="col-lg-3  col-md-3">
                                <div class="blog_info text-right">
                                    <div class="post_tag">
                                        <c:if test="${postDetail.category.name ne 'None'}">
                                            
                                            
                                        Type:   <a class="active" style="font-size: 15px;" href="listproduct?category%5B%5D=${postDetail.category.name}">${postDetail.category.name}</a>
                                        </c:if>
                                    </div>
                                    <div class="post_tag">
                                        <c:if test="${postDetail.brand.name ne 'None'}">
                                        Brand: <a class="active" style="font-size: 15px;" href="listproduct?brand%5B%5D=${postDetail.brand.name}">${postDetail.brand.name}</a>
                                        </c:if>
                                    </div>
                                    <ul class="blog_meta list">
                                        <li>
                                            <a href="">${postDetail.user.userName}
                                                <i class="lnr lnr-user"></i>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="#">${postDetailTime}
                                                <i class="lnr lnr-calendar-full"></i>
                                            </a>
                                        </li>
                                                                                <li>
                                                                                    <a href="#">not yet
                                                                                        <i class="lnr lnr-eye"></i>
                                                                                    </a>
                                                                                </li>
                                                                                <li>
                                                                                    <a href="#">not yet
                                                                                       <i class="lnr lnr-bubble"></i>
                                                                                    </a>
                                                                                </li>
                                    </ul>
<!--                                                                        <ul class="social-links">
                                                                            <li>
                                                                                <a href="#">
                                                                                    <i class="fab fa-facebook-f"></i>
                                                                                </a>
                                                                            </li>
                                                                            <li>
                                                                                <a href="#">
                                                                                    <i class="fab fa-twitter"></i>																				
                                                                                </a>
                                                                            </li>
                                                                            <li>
                                                                                <a href="#">
                                                                                    <i class="fab fa-github"></i>																				
                                                                                </a>
                                                                            </li>
                                                                            <li>
                                                                                <a href="#">
                                                                                    <i class="fab fa-behance"></i>																				
                                                                                </a>
                                                                            </li>
                                                                        </ul>-->
                                </div>
                            </div>
                            <div class="col-lg-9 col-md-9 blog_details">
                                <h2>${postDetail.tittle}</h2>
                                <p class="excert">
                                    ${postDetail.shortContent}
                                </p>

                            </div>
                            <div class="col-lg-12">
                                
                                 <div class="quotes">
                                    ${postDetail.fullContent}
                                </div>
                                
                                
                                <div class="quotes">
                                    We have so much inspiration. It's everywhere... So I always have a pen with me and a laptop, and I write everything down.
                                    <br/>
                                    <br/>
                                    <h5>Bill Kaulitz</h5>

                                </div>
<!--                                <div class="row">
                                    <div class="col-6">
                                        <img class="img-fluid" src="img/blog/post-img1.jpg" alt="">
                                    </div>
                                    <div class="col-6">
                                        <img class="img-fluid" src="img/blog/post-img2.jpg" alt="">
                                    </div>
                                </div>-->
                            </div>
                        </div>
                                
                                
                                    <c:if test="${param.postmove eq 'yes'}">
                                        
                                                <div class="navigation-area">
                            <div class="row">
                                <div class="col-lg-6 col-md-6 col-12 nav-left flex-row d-flex justify-content-start align-items-center">
 
                                </div>
                             
                            </div>
                        </div>
                                        
                                    </c:if>
                
<!--                        <div class="comments-area">
                            <h4>05 Comments</h4>
                            <div class="comment-list">
                                <div class="single-comment justify-content-between d-flex">
                                    <div class="user justify-content-between d-flex">
                                        <div class="thumb">
                                            <img src="img/blog/c1.jpg" alt="">
                                        </div>
                                        <div class="desc">
                                            <h5>
                                                <a href="#">Emilly Blunt</a>
                                            </h5>
                                            <p class="date">December 4, 2017 at 3:12 pm </p>
                                            <p class="comment">
                                                Never say goodbye till the end comes!
                                            </p>
                                        </div>
                                    </div>
                                    <div class="reply-btn">
                                        <a href="#" class="btn-reply text-uppercase">reply</a>
                                    </div>
                                </div>
                            </div>
                            <div class="comment-list left-padding">
                                <div class="single-comment justify-content-between d-flex">
                                    <div class="user justify-content-between d-flex">
                                        <div class="thumb">
                                            <img src="img/blog/c2.jpg" alt="">
                                        </div>
                                        <div class="desc">
                                            <h5>
                                                <a href="#">Elsie Cunningham</a>
                                            </h5>
                                            <p class="date">December 4, 2017 at 3:12 pm </p>
                                            <p class="comment">
                                                Never say goodbye till the end comes!
                                            </p>
                                        </div>
                                    </div>
                                    <div class="reply-btn">
                                        <a href="#" class="btn-reply text-uppercase">reply</a>
                                    </div>
                                </div>
                            </div>
                            <div class="comment-list left-padding">
                                <div class="single-comment justify-content-between d-flex">
                                    <div class="user justify-content-between d-flex">
                                        <div class="thumb">
                                            <img src="img/blog/c3.jpg" alt="">
                                        </div>
                                        <div class="desc">
                                            <h5>
                                                <a href="#">Annie Stephens</a>
                                            </h5>
                                            <p class="date">December 4, 2017 at 3:12 pm </p>
                                            <p class="comment">
                                                Never say goodbye till the end comes!
                                            </p>
                                        </div>
                                    </div>
                                    <div class="reply-btn">
                                        <a href="#" class="btn-reply text-uppercase">reply</a>
                                    </div>
                                </div>
                            </div>
                            <div class="comment-list">
                                <div class="single-comment justify-content-between d-flex">
                                    <div class="user justify-content-between d-flex">
                                        <div class="thumb">
                                            <img src="img/blog/c4.jpg" alt="">
                                        </div>
                                        <div class="desc">
                                            <h5>
                                                <a href="#">Maria Luna</a>
                                            </h5>
                                            <p class="date">December 4, 2017 at 3:12 pm </p>
                                            <p class="comment">
                                                Never say goodbye till the end comes!
                                            </p>
                                        </div>
                                    </div>
                                    <div class="reply-btn">
                                        <a href="#" class="btn-reply text-uppercase">reply</a>
                                    </div>
                                </div>
                            </div>
                            <div class="comment-list">
                                <div class="single-comment justify-content-between d-flex">
                                    <div class="user justify-content-between d-flex">
                                        <div class="thumb">
                                            <img src="img/blog/c5.jpg" alt="">
                                        </div>
                                        <div class="desc">
                                            <h5>
                                                <a href="#">Ina Hayes</a>
                                            </h5>
                                            <p class="date">December 4, 2017 at 3:12 pm </p>
                                            <p class="comment">
                                                Never say goodbye till the end comes!
                                            </p>
                                        </div>
                                    </div>
                                    <div class="reply-btn">
                                        <a href="#" class="btn-reply text-uppercase">reply</a>
                                    </div>
                                </div>
                            </div>
                        </div>-->
<!--                        <div class="comment-form">
                            <h4>Leave a Reply</h4>
                            <form>
                                <div class="form-group form-inline">
                                    <div class="form-group col-lg-6 col-md-6 name">
                                        <input type="text" class="form-control" id="name" placeholder="Enter Name" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Enter Name'">
                                    </div>
                                    <div class="form-group col-lg-6 col-md-6 email">
                                        <input type="email" class="form-control" id="email" placeholder="Enter email address" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Enter email address'">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control" id="subject" placeholder="Subject" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Subject'">
                                </div>
                                <div class="form-group">
                                    <textarea class="form-control mb-10" rows="5" name="message" placeholder="Messege" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Messege'"
                                              required=""></textarea>
                                </div>
                                <a href="#" class="button button-postComment button--active">Post Comment</a>
                            </form>
                        </div>-->
                    </div>
<!--                    <div class="col-lg-4">
                        <div class="blog_right_sidebar">
                            <aside class="single_sidebar_widget search_widget">
                                <div class="input-group">
                                    <input type="text" class="form-control" placeholder="Search Posts">
                                    <span class="input-group-btn">
                                        <button class="btn btn-default" type="button">
                                            <i class="lnr lnr-magnifier"></i>
                                        </button>
                                    </span>
                                </div>
                                 /input-group 
                                <div class="br"></div>
                            </aside>
                            <aside class="single_sidebar_widget author_widget">
                                <img class="author_img rounded-circle" src="img/blog/author.png" alt="">
                                <h4>Charlie Barber</h4>
                                <p>Senior blog writer</p>
                                <div class="social_icon">
                                    <a href="#">
                                        <i class="fab fa-facebook-f"></i>
                                    </a>
                                    <a href="#">
                                        <i class="fab fa-twitter"></i>
                                    </a>
                                    <a href="#">
                                        <i class="fab fa-github"></i>
                                    </a>
                                    <a href="#">
                                        <i class="fab fa-behance"></i>
                                    </a>
                                </div>
                                <p>Boot camps have its supporters andit sdetractors. Some people do not understand why you should
                                    have to spend money on boot camp when you can get. Boot camps have itssuppor ters andits
                                    detractors.
                                </p>
                                <div class="br"></div>
                            </aside>
                            <aside class="single_sidebar_widget popular_post_widget">
                                <h3 class="widget_title">Popular Posts</h3>
                                <div class="media post_item">
                                    <img src="img/blog/popular-post/post1.jpg" alt="post">
                                    <div class="media-body">
                                        <a href="blog-details.jsp">
                                            <h3>Space The Final Frontier</h3>
                                        </a>
                                        <p>02 Hours ago</p>
                                    </div>
                                </div>
                                <div class="media post_item">
                                    <img src="img/blog/popular-post/post2.jpg" alt="post">
                                    <div class="media-body">
                                        <a href="blog-details.jsp">
                                            <h3>The Amazing Hubble</h3>
                                        </a>
                                        <p>02 Hours ago</p>
                                    </div>
                                </div>
                                <div class="media post_item">
                                    <img src="img/blog/popular-post/post3.jpg" alt="post">
                                    <div class="media-body">
                                        <a href="blog-details.jsp">
                                            <h3>Astronomy Or Astrology</h3>
                                        </a>
                                        <p>03 Hours ago</p>
                                    </div>
                                </div>
                                <div class="media post_item">
                                    <img src="img/blog/popular-post/post4.jpg" alt="post">
                                    <div class="media-body">
                                        <a href="blog-details.jsp">
                                            <h3>Asteroids telescope</h3>
                                        </a>
                                        <p>01 Hours ago</p>
                                    </div>
                                </div>
                                <div class="br"></div>
                            </aside>
                            <aside class="single_sidebar_widget ads_widget">
                                <a href="#">
                                    <img class="img-fluid" src="img/blog/add.jpg" alt="">
                                </a>
                                <div class="br"></div>
                            </aside>
                            <aside class="single_sidebar_widget post_category_widget">
                                <h4 class="widget_title">Post Catgories</h4>
                                <ul class="list cat-list">
                                    <li>
                                        <a href="#" class="d-flex justify-content-between">
                                            <p>Technology</p>
                                            <p>37</p>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#" class="d-flex justify-content-between">
                                            <p>Lifestyle</p>
                                            <p>24</p>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#" class="d-flex justify-content-between">
                                            <p>Fashion</p>
                                            <p>59</p>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#" class="d-flex justify-content-between">
                                            <p>Art</p>
                                            <p>29</p>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#" class="d-flex justify-content-between">
                                            <p>Food</p>
                                            <p>15</p>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#" class="d-flex justify-content-between">
                                            <p>Architecture</p>
                                            <p>09</p>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#" class="d-flex justify-content-between">
                                            <p>Adventure</p>
                                            <p>44</p>
                                        </a>
                                    </li>
                                </ul>
                                <div class="br"></div>
                            </aside>
                            <aside class="single-sidebar-widget newsletter_widget">
                                <h4 class="widget_title">Newsletter</h4>
                                <p>
                                    Here, I focus on a range of items and features that we use in life without giving them a second thought.
                                </p>
                                <div class="form-group d-flex flex-row">
                                    <div class="input-group">
                                        <div class="input-group-prepend">
                                            <div class="input-group-text">
                                                <i class="fa fa-envelope" aria-hidden="true"></i>
                                            </div>
                                        </div>
                                        <input type="text" class="form-control" id="inlineFormInputGroup" placeholder="Enter email address" onfocus="this.placeholder = ''"
                                               onblur="this.placeholder = 'Enter email'">
                                    </div>
                                    <a href="#" class="bbtns">Subcribe</a>
                                </div>
                                <p class="text-bottom">You can unsubscribe at any time</p>
                                <div class="br"></div>
                            </aside>
                            <aside class="single-sidebar-widget tag_cloud_widget">
                                <h4 class="widget_title">Tag Clouds</h4>
                                <ul class="list">
                                    <li>
                                        <a href="#">Technology</a>
                                    </li>
                                    <li>
                                        <a href="#">Fashion</a>
                                    </li>
                                    <li>
                                        <a href="#">Architecture</a>
                                    </li>
                                    <li>
                                        <a href="#">Fashion</a>
                                    </li>
                                    <li>
                                        <a href="#">Food</a>
                                    </li>
                                    <li>
                                        <a href="#">Technology</a>
                                    </li>
                                    <li>
                                        <a href="#">Lifestyle</a>
                                    </li>
                                    <li>
                                        <a href="#">Art</a>
                                    </li>
                                    <li>
                                        <a href="#">Adventure</a>
                                    </li>
                                    <li>
                                        <a href="#">Food</a>
                                    </li>
                                    <li>
                                        <a href="#">Lifestyle</a>
                                    </li>
                                    <li>
                                        <a href="#">Adventure</a>
                                    </li>
                                </ul>
                            </aside>
                        </div>
                    </div>-->
                </div>
            </div>

        </section>
        <!--================Blog Area =================-->


<!--        ================Instagram Area =================
        <section class="instagram_area">
            <div class="container box_1620">
                <div class="insta_btn">
                    <a class="btn theme_btn" href="#">Follow us on instagram</a>
                </div>
                <div class="instagram_image row m0">
                    <a href="#"><img src="img/instagram/ins-1.jpg" alt=""></a>
                    <a href="#"><img src="img/instagram/ins-2.jpg" alt=""></a>
                    <a href="#"><img src="img/instagram/ins-3.jpg" alt=""></a>
                    <a href="#"><img src="img/instagram/ins-4.jpg" alt=""></a>
                    <a href="#"><img src="img/instagram/ins-5.jpg" alt=""></a>
                    <a href="#"><img src="img/instagram/ins-6.jpg" alt=""></a>
                </div>
            </div>
        </section>
        ================End Instagram Area =================-->


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