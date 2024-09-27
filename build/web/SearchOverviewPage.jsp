<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Search</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Farro:wght@300;400;500;700&display=swap" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="img/Fevicon.png" type="image/png">
    <link rel="stylesheet" href="vendors/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="vendors/fontawesome/css/all.min.css">
    <link rel="stylesheet" href="vendors/themify-icons/themify-icons.css">
    <link rel="stylesheet" href="vendors/linericon/style.css">
    <link rel="stylesheet" href="vendors/owl-carousel/owl.theme.default.min.css">
    <link rel="stylesheet" href="vendors/owl-carousel/owl.carousel.min.css">
    <link rel="stylesheet" href="css/style.css">
    <style>
        .search-section {
            display: flex;
            justify-content: space-between;
            margin-top: 20px;
        }

        .product-results, .post-results {
            width: 48%; 
        }

        .search-results-container, .post-container {
            display: flex;
            flex-wrap: wrap;
            gap: 15px;
        }

        .product-only, .post {
            width: 30%; 
            margin-bottom: 20px;
        }

        .card {
            border: 1px solid #ccc;
            border-radius: 5px;
            overflow: hidden;
            position: relative;
        }

        .card-product__img, .card-blog__img {
            position: relative;
        }

        .card-product__img img, .card-blog__img img {
            width: 100%;
            height: auto;
        }

        .card-product__imgOverlay {
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            display: flex;
            align-items: center;
            justify-content: center;
            opacity: 0;
            transition: opacity 0.3s;
        }

        .card:hover .card-product__imgOverlay {
            opacity: 1;
        }

        .card-body {
            padding: 10px;
            text-align: center;
        }

        .btn {
            background: transparent;
            border: none;
            cursor: pointer;
        }

        .tach {
            margin-right: 15px;
        }
        .card-product__img{
            display: flex;
        }

        .card-blog {
            margin-bottom: 20px;
        }

        .card-product__imgOverlay {
            display: flex !important;
        }

        .x {
            margin-left: 30px;
        }
        .card-product__imgOverlay, .icon-list {
    display: flex;
    justify-content: space-between; 
    align-items: center;
    list-style: none;
    padding: 10px;
    margin: 0;
    border: 1px solid gray;
    
}

.card-product__imgOverlay li, .icon-list li {
    margin-right: 10px;
}

.card-product__imgOverlay li button, .icon-list li button, .icon-list li a {
    background: none; 
    border: none; 
}


        @media (max-width: 768px) {
            .product-only, .post {
                width: 45%; 
            }
        }
    </style>
</head>
<body>
    <%@include file="header.jsp" %>
    <main>
        <section id="search-results">
            <h2 class="x" style="text-align: center; padding: 20px"  >Search Results</h2>
            <br/>
            <h4 class="x" style="color: red">${err}</h4>
            <div class="search-section" style="margin-left: 30px">
                <div class="product-results" style="padding: 20px">
                    <h3 style="border: 1px solid gray; width: 200px;padding: 10px; border-radius: 7px">Products(${pop_size})</h3>
                    <br/>
                    <div class="search-results-container">
                        <c:forEach items="${pop}" var="o3" varStatus="status">
                            <div class="results product-only">
                                <div class="card">
                                    <div class="card-product__img">
                                        <img class="img-fluid" src="${o3.getImage()}" alt="">
                                        <ul class="card-product__imgOverlay">
                                            <a href="information?productId=${o3.getProductDetail().getId()}"><li><button><i class="ti-search"></i></button><a/>
                                            <a class="ti-shopping-cart" href="addtocart?pid=${o3.getProductDetail().getProduct().getId()}&&colorid=${o3.getProductDetail().getColor().getId()}&&confid=${o3.getProductDetail().getConfiguration().getId()}"></a>
                                            <button><i class="ti-heart"></i></button>
                                            
                                            </li>
                                            
                                            
                                        </ul>
                                    </div>
                                    <div class="card-body">
                                        <p class="nhot">${o3.getProductDetail().getProduct().getBrand().getName()}</p>
                                        <h5 class="card-product__title nhot">
                                            <a href="information?productId=${o3.getProductDetail().getId()}"> ${o3.getProductDetail().getProduct().getName()} 
                                            ${o3.getProductDetail().getProduct().getCategory().getName()} ${o3.getProductDetail().getConfiguration().getName()} (${o3.getProductDetail().getColor().getName()})</a>
                                        </h5>   
                                        <p class="card-product__price nhot tien">${o3.getProductDetail().getPrice()} VND</p>
                                        <ul class="icon-list">
                                            <li><button><i class="ti-search"></i></button></li>
                                            <li><a class="ti-shopping-cart" href="addtocart?pid=${o3.getProductDetail().getProduct().getId()}&&colorid=${o3.getProductDetail().getColor().getId()}&&confid=${o3.getProductDetail().getConfiguration().getId()}"></a></li>
                                            <li><button><i class="ti-heart"></i></button></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                     See more ....
                    <div style="text-align: center; margin-top: 30px">
                        <a style="padding: 10px 15px; border: 1px solid black; margin-left: 15px;" href="">1</a>
                        <a style="padding: 10px 15px; border: 1px solid black; margin-left: 15px" href="" >2</a>
                        <a style="padding: 10px 15px; border: 1px solid black; margin-left: 15px" href="">3</a>
                                        
                                    </div>
                    
                </div>
                <div class="post-results">
                    <h3 style="border: 1px solid gray; width: 170px;padding: 10px; border-radius: 7px">Posts (${listPSize})</h3>
                    <div class="post-container">
                        <c:forEach items="${listP}" var="p" varStatus="status">
                            <div class="post tach">
                                <div class="card card-blog">
                                    <div class="card-blog__img">
                                        <img class="card-img rounded-0" src="${p.thumbnail}" alt="">
                                    </div>
                                    <div class="card-body">
                                        <ul class="card-blog__info">
                                            <li><a href="#" class="bx bxs-user"> ${p.user.userName}</a></li>
                                            <li><i class="bx bx-laptop"></i>${p.brand.name}</li>
                                            <li><i class="bx bx-laptop"></i>${p.publishDate}</li>
                                        </ul>
                                        <h4 class="card-blog__title"><a href="single-blog.jsp">${p.tittle}</a></h4>
                                        <p>${p.shortContent}</p>
                                        <a class="card-blog__link" href="postdetail?id=${p.id}">Read More <i class="ti-arrow-right"></i></a>
                                    </div>
                                    
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </section>
    </main>
</body>
</html>
