<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Search</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="img/Fevicon.png" type="image/png">
    <link rel="stylesheet" href="vendors/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="vendors/fontawesome/css/all.min.css">
    <link rel="stylesheet" href="css/style.css">
    <style>
        /* General Layout */
        .search-section {
            display: flex;
            justify-content: space-between;
            margin-top: 20px;
        }

        .product-results, .post-results {
            width: 48%;
            margin-right: 30px;
        }

        .search-results-container, .post-container {
            display: flex;
            flex-wrap: wrap;
            gap: 15px;
        }

        .product-only, .post {
            width: 32%; /* Ensure 3 products per row */
        }

        .card {
            border: 1px solid #ccc;
            border-radius: 10px;
            overflow: hidden;
            transition: transform 0.3s, box-shadow 0.3s;
            background-color: #fff;
        }

        .card:hover {
            transform: translateY(-10px);
            box-shadow: 0 8px 16px rgba(0,0,0,0.1);
        }

        .card-product__img img, .card-blog__img img {
            width: 100%;
            height: 230px; /* Fixed height for product images */
            object-fit: contain;
            padding-bottom: 30px;
        }

        .card-body {
            margin: 20px;
            padding: 15px;
            text-align: center;
        }

        /* Button and Overlay Styles */
        .card-product__imgOverlay {
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            opacity: 0;
            background-color: rgba(0, 0, 0, 0.5);
            transition: opacity 0.3s ease-in-out;
        }

        .card:hover .card-product__imgOverlay {
            opacity: 1;
        }

        .card-product__imgOverlay li {
            margin: 0 10px;
        }

        .card-product__imgOverlay button, .card-product__imgOverlay a {
            color: #fff;
            font-size: 20px;
        }

        /* Text Styles */
        .card-product__title {
            font-size: 16px;
            font-weight: 600;
            color: #333;
        }

        .card-product__price {
            font-size: 18px;
            font-weight: bold;
            color: #e74c3c;
        }

        .icon-list {
            margin-top: 10px;
            padding: 0;
            display: flex;
            justify-content: center;
            gap: 10px;
        }

        .icon-list li {
            list-style: none;
        }

        .icon-list button {
            background-color: transparent;
            border: none;
            color: #888;
            font-size: 18px;
        }
      
.card-product__title a:hover {
    color: #3498db; 
    text-decoration: none; 
}

.card-blog__title a:hover {
    color: #3498db;  
    text-decoration: none; 
}

/* Optional: Add transition for smooth color change */
.card-product__title a, .card-blog__title a {
    transition: color 0.3s ease; 
}

/* Keep the overlay effect on hover */
.card:hover .card-product__imgOverlay {
    opacity: 1;
}
.post-container {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between; 
    gap: 20px; 
}

.post {
    flex: 1 1 calc(33.333% - 20px); 
    box-sizing: border-box; 
    margin-bottom: 30px; 
}

.card {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    height: 100%;
}

.card-body {
    padding: 15px;
}



        /* Responsive Adjustments */
        @media (max-width: 768px) {
            .product-only, .post {
                width: 100%; /* 2 products per row on mobile */
            }
        }

        @media (max-width: 576px) {
            .product-only, .post {
                width: 100%; /* Full width for single column on small screens */
            }
        }
    </style>
</head>
<body>
    <%@include file="header.jsp" %>
    <main>
        <section id="search-results">
            <h2 class="x" style="text-align: center; padding: 20px">Search Results</h2>
            <br/>
            <h4 class="x" style="color: red">${err}</h4>
            <div class="search-section" style="margin-left: 30px">
                <div class="product-results">
                    <h3 style="border: 1px solid gray; width: 200px; padding: 10px; border-radius: 7px">Products(${pop_size})</h3>
                    <br/>
                    <div class="search-results-container">
                        <c:forEach items="${pop}" var="o3">
                            <div class="results product-only">
                                <div class="card">
                                    <div class="card-product__img">
                                        <img src="${o3.getImage()}" alt="">
                                        <ul class="card-product__imgOverlay">
                                            <li><a href="information?productId=${o3.getProductDetail().getId()}"><i class="ti-search"></i></a></li>
                                            <li><a class="ti-shopping-cart" href="addtocart?pid=${o3.getProductDetail().getProduct().getId()}&&colorid=${o3.getProductDetail().getColor().getId()}&&confid=${o3.getProductDetail().getConfiguration().getId()}"></a></li>
                                            <li><button><i class="ti-heart"></i></button></li>
                                        </ul>
                                    </div>
                                    <div class="card-body">
                                        <p>${o3.getProductDetail().getProduct().getBrand().getName()}</p>
                                        <h5 class="card-product__title">
                                            <a href="information?productId=${o3.getProductDetail().getId()}">${o3.getProductDetail().getProduct().getName()} ${o3.getProductDetail().getConfiguration().getName()} (${o3.getProductDetail().getColor().getName()})</a>
                                        </h5>
                                        <p class="card-product__price">${o3.getProductDetail().getPrice()} VND</p>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <div class="post-results">
                    <h3 style="border: 1px solid gray; width: 170px; padding: 10px; border-radius: 7px; margin-bottom: 33px;">Posts (${listPSize})</h3>
                    <div class="post-container">
                        <c:forEach items="${listP}" var="p">
                            <div class="post tach">
                                <div class="card card-blog">
                                    <div class="card-blog__img">
                                        <img class="card-img rounded-0" src="${p.thumbnail}" alt="">
                                    </div>
                                    <div class="card-body">
                                        <ul class="card-blog__info">
                                            <li><a href="#" class="bx bxs-user">${p.user.userName}</a></li>
                                            <li><i class="bx bx-laptop"></i>${p.brand.name}</li>
                                            <li><i class="bx bx-laptop"></i>${p.publishDate}</li>
                                        </ul>
                                        <h4 class="card-blog__title"><a href="single-blog.jsp">${p.tittle}</a></h4>
                                        <p>${p.shortContent}</p>
                                        <a class="card-blog__link" href="postdetail?id=${p.id}">Read More -> <i class="ti-arrow-right"></i></a>
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
