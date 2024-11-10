
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            width: 90%;
            
            margin-left: 150px;
            gap:10px;
        }

        .search-results-container, .post-container {
            display: flex;
            flex-wrap: wrap;
            gap: 15px;
        }

        .product-only {
            width: 16%; /* Ensure 3 products per row */
        }
        .post{
            width: 20%
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
    
    gap: 10px; 
}



.card {
    display: flex;
    height: 100%;
}

.card-body {
    padding: 15px;
}
.sidebar {
    width: 200px;
}

.sidebar button {
    background-color: #f0f0f0;
    border: none;
    padding: 10px;
    width: 100%;
    text-align: left;
    cursor: pointer;
    margin-bottom: 5px;
}

.sidebar button:hover {
    background-color: #ddd;
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
        <br />
        <h4 class="x" style="color: red">${err}</h4>
        
        <div class="search-section" style="display: flex; margin-left: 30px">
            <!-- Sidebar cho các tùy chọn -->
            <div class="sidebar" style="margin-right: 20px;">
                <h3 style="padding: 10px; border-bottom: 1px solid gray;">Options</h3>
                <ul style="list-style: none; padding: 0;">
                    <li><button onclick="showResults('product-results')">Products (${pop_size})</button></li>
                    <li><button onclick="showResults('post-results')">Posts (${listPSize})</button></li>
                </ul>
            </div>

            <!-- Kết quả tìm kiếm -->
            <div class="results-section" style="flex-grow: 1;">
                <!-- Phần hiển thị sản phẩm -->
                <div id="product-results" class="product-results" style="display: none;">
                     
                    <br />
                    <div class="search-results-container">
                        <h3 style="text-align: center; width: 90%;">${errorProduct}</h3>
                        <c:forEach items="${pop}" var="o3">
                            <div class="results product-only">
                                <div class="card">
                                    <div class="card-product__img">
                                        <img src="${pageContext.request.contextPath}/images/${o3.getImage()}" alt="">
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

                <!-- Phần hiển thị bài viết -->
                <div id="post-results" class="post-results" style="display: none;">
 
                    <div class="post-container">
                        <h3 style="text-align: center; width: 90%">${errorPost}</h3>
                        <c:forEach items="${listP}" var="p">
                            <div class="post postOnly tach">
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
                                        <h4 class="card-blog__title"><a href="postdetail?id=${p.id}&postmove=yes&min=${min}&max=${max}">${p.tittle}</a></h4>
                                        <p>${p.shortContent}</p>
                                        <a class="card-blog__link" href="postdetail?id=${p.id}&postmove=yes">Read More -> <i class="ti-arrow-right"></i></a>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </section>
</main>

<script>
    // Hàm để hiển thị phần kết quả theo tùy chọn
    function showResults(sectionId) {
        document.getElementById('product-results').style.display = 'none';
        document.getElementById('post-results').style.display = 'none';
        document.getElementById(sectionId).style.display = 'block';
    }

    // Hiển thị phần sản phẩm mặc định
    showResults('product-results');
</script>

      
</body>
</html>
