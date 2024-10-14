<%-- 
    Document   : wishlist
    Created on : Oct 11, 2024, 12:37:07 AM
    Author     : PHONG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="img/Fevicon.png" type="image/png">
        <link rel="stylesheet" href="vendors/themify-icons/themify-icons.css">
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

        <title>Wish list</title>
        <style>
            body {
                font-family: Arial, sans-serif; /* Font chữ cho toàn bộ body */
                background-color: #f9f9f9; /* Màu nền nhẹ cho trang */
            }

            table {
                width: 70%; /* Bảng chiếm 70% chiều ngang màn hình */
                margin: 30px auto; /* Căn bảng ở giữa màn hình với khoảng cách trên/bên dưới */
                border-collapse: collapse; /* Gộp viền bảng */
                box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1); /* Hiệu ứng đổ bóng cho bảng */
                border-radius: 8px; /* Bo tròn các góc của bảng */
                overflow: hidden; /* Đảm bảo các góc bo tròn được hiển thị */
            }

            th, td {
                padding: 15px; /* Khoảng cách trong các ô */
                border-bottom: 1px solid #ddd; /* Viền dưới cho các ô */
                vertical-align: middle; /* Căn giữa theo chiều dọc */
                text-align: left; /* Căn trái nội dung trong ô */
            }

            th {
                background-color: #f2f2f2; /* Màu nền cho các tiêu đề */
                font-weight: bold; /* Đậm chữ cho các tiêu đề */
                color: #333; /* Màu chữ cho các tiêu đề */
            }

            td img {
                width: 150px; /* Đặt kích thước cố định cho ảnh lớn hơn */
                height: 150px; /* Chiều cao cố định cho ảnh */
                object-fit: cover; /* Ảnh sẽ hiển thị kín khung và giữ tỉ lệ */
                display: block; /* Ảnh hiển thị dạng block */
                margin: 0 auto; /* Ảnh căn giữa theo chiều ngang */
                border-radius: 8px; /* Bo tròn các góc của ảnh */
            }

            td {
                max-width: 300px; /* Kích thước tối đa của cột */
                overflow: hidden; /* Ẩn nội dung vượt quá chiều rộng */
                white-space: nowrap; /* Không cho phép xuống dòng */
                text-overflow: ellipsis; /* Thêm dấu "..." cho các ô có nội dung quá dài */
            }

            a {
                text-decoration: none; /* Bỏ gạch chân cho liên kết */
                color: #007BFF; /* Màu chữ cho liên kết */
                transition: color 0.3s ease; /* Hiệu ứng chuyển màu khi di chuột */
            }

            a:hover {
                color: #0056b3; /* Màu chữ khi di chuột */
            }

            tr:hover {
                background-color: #f5f5f5; /* Hiệu ứng nền khi di chuột qua hàng */
            }
        </style>
        <script>
            function deleteWishlist(uid, pid) {
                if (confirm("Do you want to delete this product from your wishlist?")) {
                    window.location = "deletefromwishlist?uid=" + uid + "&&pid=" + pid;
                }
            }
            function deleteAllWishlist(uid) {
                if (confirm("Do you want to delete all product from your wishlist?")) {
                    window.location = "deleteallwishlist?uid=" + uid;
                }
            }
        </script>
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
                        <h1>Wish List</h1>
                    </div>
                </div>
            </div>
        </section>
        <!-- ================ end banner area ================= -->

        <section>
            <table border="0">
                <thead>
                    <tr>
                        <th>Product</th>
                        <th></th>
                        <th>Price</th>
                        <th>Add to cart</th>
                        <th><input type="button"  value="Delete all" onclick="deleteAllWishlist(${user.id})" href=""/></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${wishlist}" var="i">
                        <tr>
                            <td><img src="${pageContext.request.contextPath}/images/${i.image}" alt="Product Image"/></td>
                            <td><a href="information?productId=${i.productDetail.id}">${i.productDetail.product.name}</a> (${i.productDetail.color.name})<br>
                                ${i.productDetail.product.brand.name}<br>
                                ${i.productDetail.configuration.name}
                            </td>
                            <td><fmt:formatNumber value="${i.productDetail.price}" type="number"/>đ </td>
                            <td><a  href="addtocart?pid=${i.productDetail.product.id}&&colorid=${i.productDetail.color.id}&&confid=${i.productDetail.configuration.id}">Add to cart</a></td>
                            <td>
                                <a style="font-size: 30px;" onclick="deleteWishlist(${user.id}, ${i.productDetail.id})" href="">&times;</a><br>
                                
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </section>

        <!--================ Start footer Area  =================-->
        <%@include file="footer.jsp" %>
        <!--================ End footer Area  =================-->
    </body>
</html>
