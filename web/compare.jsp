<%-- 
    Document   : compare.jsp
    Created on : Oct 7, 2024, 10:04:32 PM
    Author     : PHONG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Aroma Shop - Compare Laptop</title>
        <link rel="icon" href="img/Fevicon.png" type="image/png">
        <link rel="stylesheet" href="vendors/themify-icons/themify-icons.css">
        <style>
            .image-container {
                position: relative;
                width: 100%; /* Chiều rộng theo nhu cầu */
            }

            .image-container img {
                display: block;
                width: 100%; /* Đảm bảo ảnh chiếm hết khung */
                height: auto;
            }

            .image-container .overlay {
                position: absolute;
                top: 0;
                left: 0;
                right: 0;
                bottom: 0;
                background-color: rgba(0, 0, 0, 0.3); /* Màu nền trong suốt cho overlay */
                color: white;
                display: flex;
                justify-content: center; /* Căn giữa theo chiều ngang */
                align-items: center; /* Căn giữa theo chiều dọc */
                opacity: 0; /* Ẩn overlay ban đầu */
                transition: opacity 0.3s ease; /* Hiệu ứng chuyển đổi mượt mà */
            }

            .image-container:hover .overlay {
                opacity: 1; /* Hiện overlay khi di chuột */
            }

            .icon-container {
                display: flex; /* Sử dụng Flexbox để căn giữa */
                justify-content: space-around; /* Tạo khoảng cách đều giữa các biểu tượng */
                width: 50%; /* Chiếm 50% chiều rộng */
            }

            .icon-container a {
                font-size: 25px; /* Kích thước biểu tượng lớn hơn */
                color: white; /* Màu mặc định của biểu tượng */
                transition: color 0.3s ease; /* Hiệu ứng chuyển đổi màu */
            }

            .icon-container a:hover {
                color: #00BFFF; /* Màu xanh da trời khi di chuột vào từng biểu tượng */
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
                        <h1>Compare Laptop</h1>
                    </div>
                </div>
            </div>
        </section>
        <!-- ================ end banner area ================= -->



        <div class="container">
            <table class="table table-bordered">
                <colgroup>
                    <col style="width: 19%;">
                    <col style="width: 27%;">
                    <col style="width: 27%;">
                    <col style="width: 27%;">
                </colgroup>
                <thead>
                    <tr>
                        <th style="text-align: center; vertical-align: middle; padding: 10px;">
                            <h3>Compare laptop </h3>
                        </th>
                        <th style="text-align: center; vertical-align: middle; padding: 10px;">
                            <div style="height: 100%; display: flex; flex-direction: column; justify-content: space-between;" >
                                <a class="product-link" style="display: block; height: 100%; text-decoration: none; color: inherit;">

                                    <!-- Hiển thị ảnh sản phẩm -->
                                    <div class="image-container">
                                        <img src="${pageContext.request.contextPath}/images/${img1.image}" alt="Product Image" style="max-width: 100%; height: auto;">
                                        <div class="overlay">
                                            <div class="icon-container">
                                                <a href="information?productId=${img1.productDetail.id}" class="ti-search"></a>
                                                <a href="addtocart?pid=${img1.productDetail.product.id}&&colorid=${img1.productDetail.color.id}&&confid=${img1.productDetail.configuration.id}" class="ti-shopping-cart" ></a>
                                                <a href="addtowishlist?pid=${img1.productDetail.id}&&uid=${user.id}" class="ti-heart"></a>
                                            </div>
                                        </div>
                                    </div>


                                    <!-- Hiển thị thương hiệu -->
                                    <div class="brand">${img1.productDetail.product.brand.name}</div>

                                    <!-- Hiển thị tên sản phẩm -->
                                    <h5>${img1.productDetail.product.name}</h5>
                                    <p>Color: ${img1.productDetail.color.name}</p>

                                    <!-- Hiển thị giá tiền -->
                                    <fmt:formatNumber value="${img1.productDetail.price}" type="number"/>đ

                                </a>
                            </div>
                        </th>
                        <th style="text-align: center; vertical-align: middle; padding: 10px;">
                            <c:if test="${empty img2}">
                                <button id="openModal">Add product</button>
                            </c:if>
                            <c:if test="${!empty img2}">
                                <a style="float: right; font-size: 30px;" href="compare?productid=${img1.productDetail.id}&&productid3=${img3.productDetail.id}" >&times;</a>
                                <div style="height: 100%; display: flex; flex-direction: column; justify-content: space-between;" >
                                    <a href="information?productId=${img2.productDetail.id}" class="product-link" style="display: block; height: 100%; text-decoration: none; color: inherit;">

                                        <!-- Hiển thị ảnh sản phẩm -->
                                        <div class="image-container">
                                            <img src="${pageContext.request.contextPath}/images/${img2.image}" alt="Product Image" style="max-width: 100%; height: auto;">
                                            <div class="overlay">
                                                <div class="icon-container">
                                                    <a href="information?productId=${img2.productDetail.id}" class="ti-search"></a>
                                                    <a href="addtocart?pid=${img2.productDetail.product.id}&&colorid=${img2.productDetail.color.id}&&confid=${img2.productDetail.configuration.id}" class="ti-shopping-cart" ></a>
                                                    <a href="addtowishlist?pid=${img2.productDetail.id}&&uid=${user.id}" class="ti-heart"></a>
                                                </div>
                                            </div>
                                        </div>

                                        <!-- Hiển thị thương hiệu -->
                                        <div class="brand">${img2.productDetail.product.brand.name}</div>

                                        <!-- Hiển thị tên sản phẩm -->
                                        <h5>${img2.productDetail.product.name}</h5>
                                        <p>Color: ${img2.productDetail.color.name}</p>

                                        <!-- Hiển thị giá tiền -->
                                        <fmt:formatNumber value="${img2.productDetail.price}" type="number"/>đ

                                    </a>
                                </div>
                            </c:if>

                        </th>
                        <th style="text-align: center; vertical-align: middle; padding: 10px;">
                            <c:if test="${!empty img2}">
                                <c:if test="${empty img3}">
                                    <button id="openModal">Add product</button>
                                </c:if> 
                                <c:if test="${!empty img3}">
                                    <a style="float: right; font-size: 30px;" href="compare?productid=${img1.productDetail.id}&&productid2=${img2.productDetail.id}" >&times;</a>
                                    <div style="height: 100%; display: flex; flex-direction: column; justify-content: space-between;" >
                                        <a  class="product-link" style="display: block; height: 100%; text-decoration: none; color: inherit;">

                                            <!-- Hiển thị ảnh sản phẩm -->
                                            <div class="image-container">
                                                <img src="${pageContext.request.contextPath}/images/${img3.image}" alt="Product Image" style="max-width: 100%; height: auto;">
                                                <div class="overlay">
                                                    <div class="icon-container">
                                                        <a href="information?productId=${img3.productDetail.id}" class="ti-search"></a>
                                                        <a href="addtocart?pid=${img3.productDetail.product.id}&&colorid=${img3.productDetail.color.id}&&confid=${img3.productDetail.configuration.id}" class="ti-shopping-cart" ></a>
                                                        <a href="addtowishlist?pid=${img3.productDetail.id}&&uid=${user.id}" class="ti-heart"></a>
                                                    </div>
                                                </div>
                                            </div>

                                            <!-- Hiển thị thương hiệu -->
                                            <div class="brand">${img3.productDetail.product.brand.name}</div>

                                            <!-- Hiển thị tên sản phẩm -->
                                            <h5>${img3.productDetail.product.name}</h5>
                                            <p>Color: ${img3.productDetail.color.name}</p>

                                            <!-- Hiển thị giá tiền -->
                                            <fmt:formatNumber value="${img3.productDetail.price}" type="number"/>đ

                                        </a>
                                    </div>  
                                </c:if>
                            </c:if>




                        </th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item1" items="${list1}" varStatus="status1">
                        <tr>
                            <td><h5>${item1.attribute.name}</h5></td>
                            <td>
                                <c:choose>
                                    <c:when test="${status1.index < fn:length(list1)}">
                                        ${list1[status1.index].value}
                                    </c:when>
                                    <c:otherwise>
                                        <!-- Ô trống nếu list2 ngắn hơn -->
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${status1.index < fn:length(list2)}">
                                        ${list2[status1.index].value}
                                    </c:when>
                                    <c:otherwise>
                                        <!-- Ô trống nếu list3 ngắn hơn -->
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${status1.index < fn:length(list3)}">
                                        ${list3[status1.index].value}
                                    </c:when>
                                    <c:otherwise>
                                        <!-- Ô trống nếu list4 ngắn hơn -->
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>

            </table>
        </div>



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

        <div id="myModal" class="modal">
            <div class="modal-content">
                <a style="text-align: right; font-size: 40px;" class="close">&times;</a>

                <div class="row">
                    <div class="col-md-1"></div>
                    <div class="col-md-3">
                        <h2>Choose a product</h2>
                        <input type="text" id="searchInput" oninput="filterProducts()" placeholder="Search">
                    </div>

                    <div class="col-md-8" id="productList"> <!-- Thêm ID cho div chứa danh sách sản phẩm -->
                        <c:forEach items="${mlist}" var="i">
                            <a class="image-link" 
                               data-name="${i.productDetail.product.name}" 
                               <c:choose>
                                   <c:when test="${empty img2}">
                                       href="compare?productid=${img1.productDetail.id}&&productid2=${i.productDetail.id}"
                                   </c:when>
                                   <c:otherwise>
                                       href="compare?productid=${img1.productDetail.id}&&productid2=${img2.productDetail.id}&&productid3=${i.productDetail.id}"
                                   </c:otherwise>
                               </c:choose>
                               >
                                <img src="${pageContext.request.contextPath}/images/${i.image}" alt="Product Image" />
                                <label>
                                    ${i.productDetail.product.name} (${i.productDetail.color.name})<br>
                                    ${i.productDetail.product.brand.name}<br>
                                    ${i.productDetail.configuration.name}<br>
                                    <fmt:formatNumber value="${i.productDetail.price}" type="number"/>đ
                                </label>
                            </a>
                            <br>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>


        <script>
// Lấy phần tử modal
            var modal = document.getElementById("myModal");

// Lấy nút để mở modal
            var btn = document.getElementById("openModal");

// Lấy phần tử span để đóng modal
            var span = document.getElementsByClassName("close")[0];

// Khi người dùng nhấn vào nút, mở modal
            btn.onclick = function () {
                modal.style.display = "block";
            }

// Khi người dùng nhấn vào <span> (x), đóng modal
            span.onclick = function () {
                modal.style.display = "none";
            }

// Khi người dùng nhấn bên ngoài modal, đóng modal
            window.onclick = function (event) {
                if (event.target == modal) {
                    modal.style.display = "none";
                }
            }

            function filterProducts() {
                // Lấy giá trị từ ô tìm kiếm
                const searchInput = document.getElementById("searchInput").value.toLowerCase();
                const productList = document.getElementById("productList");
                const products = productList.getElementsByTagName("a"); // Lấy tất cả các thẻ <a> sản phẩm

                // Duyệt qua tất cả các sản phẩm
                for (let i = 0; i < products.length; i++) {
                    const productName = products[i].getAttribute("data-name").toLowerCase(); // Lấy tên sản phẩm từ thuộc tính data-name

                    // Kiểm tra xem tên sản phẩm có chứa chuỗi tìm kiếm không
                    if (productName.includes(searchInput)) {
                        products[i].style.display = ""; // Hiện sản phẩm nếu tên chứa chuỗi tìm kiếm
                    } else {
                        products[i].style.display = "none"; // Ẩn sản phẩm nếu không chứa
                    }
                }
            }

        </script>


        <style>
            /* Ẩn modal theo mặc định */
            .modal {
                display: none;
                position: fixed;
                z-index: 1;
                left: 0;
                top: 0;
                width: 100%;
                height: 100%;
                background-color: rgba(0, 0, 0, 0.4); /* Màu nền trong suốt */
            }

            /* Nội dung của modal */
            .modal-content {
                background-color: #fff;
                margin: 5% auto; /* Căn giữa theo chiều dọc */
                padding: 20px;
                border: 1px solid #888;
                width: 50vw; /* Thu hẹp chiều rộng thành 60% */
                height: 70vh; /* Chiều cao chiếm 70% chiều cao màn hình */
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
                border-radius: 10px; /* Bo góc */
                overflow-y: auto; /* Tự động cuộn nếu nội dung vượt quá khung */
            }


            /* Nút đóng modal */
            .close {
                color: #aaa;
                float: right;
                font-size: 28px;
                font-weight: bold;
                cursor: pointer;
            }

            .close:hover,
            .close:focus {
                color: black;
            }
            .image-link {
                display: flex;                  /* Sử dụng flexbox để sắp xếp nội dung */
                align-items: center;           /* Căn giữa nội dung theo chiều dọc */
                width: 600px;                  /* Chiều rộng của khung, điều chỉnh theo nhu cầu */
                height: 160px;                 /* Chiều cao của khung, điều chỉnh theo nhu cầu */
                border: 1px solid #ccc;        /* Đường viền để dễ thấy khung */
                border-radius: 5px;           /* Bo góc khung (nếu cần) */
                text-decoration: none;         /* Bỏ gạch chân ở liên kết */
                overflow: hidden;              /* Ẩn phần nội dung bị tràn */
            }

            .image-link img {
                max-width: 200px;              /* Đảm bảo ảnh có chiều rộng tối đa, điều chỉnh theo nhu cầu */
                max-height: 100%;              /* Đảm bảo ảnh không vượt quá chiều cao khung */
                object-fit: cover;             /* Cắt ảnh theo tỉ lệ mà không làm méo */
                margin-right: 10px;            /* Khoảng cách giữa ảnh và label */
            }

            .image-link label {
                flex: 1;                       /* Chiếm không gian còn lại */
                font-size: 14px;              /* Kích thước font cho label */
                color: #333;                  /* Màu chữ */
            }

            /* Style cho button */
            #openModal {
                padding: 10px 20px;         /* Giảm padding để button không quá lớn */
                font-size: 16px;            /* Kích thước chữ nhỏ gọn */
                background-color: #f0f0f0;  /* Màu nền xám nhạt, nhẹ nhàng */
                color: #333;                /* Màu chữ tối nhưng không quá nổi bật */
                border: 1px solid #ccc;     /* Đường viền mỏng màu xám nhạt */
                border-radius: 8px;         /* Bo góc nhẹ cho button */
                cursor: pointer;            /* Hiển thị hình bàn tay khi hover */
                transition: background-color 0.3s ease, transform 0.3s ease; /* Hiệu ứng chuyển đổi nhẹ */
            }

            /* Hiệu ứng khi hover vào button */
            #openModal:hover {
                background-color: #e0e0e0;  /* Đổi màu nền nhẹ hơn khi hover */
                transform: scale(1.02);     /* Phóng to nhẹ button khi hover */
            }

            /* Hiệu ứng khi click vào button */
            #openModal:active {
                transform: scale(0.98);     /* Thu nhỏ nhẹ khi click */
            }
        </style>



        <!--================ Start footer Area  =================-->	
        <%@include file="footer.jsp" %>
        <!--================ End footer Area  =================-->
    </body>
</html>
