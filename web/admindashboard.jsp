<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>ADMIN System</title>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">
        <link rel="shortcut icon" href="assets/img/logo/favicon.svg">
        <link rel="stylesheet" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/font-awesome.css">
        <link rel="stylesheet" href="assets/css/animate.css">
        <link rel="stylesheet" href="assets/css/magnific-popup.css">
        <link rel="stylesheet" href="assets/css/meanmenu.css">
        <link rel="stylesheet" href="assets/css/swiper-bundle.min.css">
        <link rel="stylesheet" href="assets/css/nice-select.css">
        <link rel="stylesheet" href="assets/css/main.css">

        <%@include file="sidebar.jsp" %>


    <div class="col-md-10 content">
        <h2>Quản Lý Sản Phẩm</h2>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Hình Ảnh</th>
                    <th>Tên</th>
                    <th>Mô Tả</th>
                    <th>Giá</th>
                    <th>Tồn Kho</th>
                    <th>Danh Mục</th>
                    <th>Hành Động</th>
                </tr>
            </thead>

            <tbody>
                <c:forEach var="p" items="${pList}"> 

                    <tr>
                        <td>${p.product_id}</td>

                        <td><img src="${p.img}" alt="Product Image" width="100"></td>
                        <td>${p.product_name}</td>
                        <td>${p.description}</td>
                        <td>${p.price}</td>
                        <td>${p.stock}</td>
                        <c:forEach var="c" items="${cList}">
                            <c:if test="${p.category.category_id==c.category_id}">
                                <td>${c.category_name}</td>
                            </c:if>
                        </c:forEach>
                        <td>
                            <button class="btn btn-primary edit-btn"><a href="updateProduct?id=${p.product_id}&index=${tag}">Edit</a></button>
                            <c:choose>
                                <c:when test="${p.active==1}">
                                    <button class="btn btn-warning"><a href="hidden?id=${p.product_id}&index=${tag}">Hidden</a></button>
                                </c:when>
                                <c:otherwise>
                                    <button class="btn btn-primary"><a href="hidden?id=${p.product_id}&index=${tag}">Active</a></button>
                                </c:otherwise>
                            </c:choose>


                            <button class="btn btn-danger"><a href="DeleteProduct?id=${p.product_id}&index=${tag}">Delete</a></button>
                            
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div>
            <button class="btn btn-primary addProductBtn"><a href="addProduct">Thêm sản phẩm mới</a></button>


            <div class="list-page">
                <div class="item">
                    <c:if test="${tag > 1}">
                        <a href="manageProduct?index=${tag - 1}"><i class="fa fa-long-arrow-left"></i><-</a>
                    </c:if>
                    <c:forEach begin="1" end="${endPage}" var="i">
                        <a class="${tag == i ? 'active' : ''}" href="manageProduct?index=${i}">${i}</a>
                    </c:forEach>
                    <c:if test="${tag < endPage}">
                        <a href="manageProduct?index=${tag + 1}"><i class="fa fa-long-arrow-right">-></i></a>
                    </c:if>
                </div>
            </div>

        </div>
        <style>
            .list-page {
                display: flex;
                justify-content: center;
                margin-top: 20px;
            }

            .list-page .item {
                display: flex;
                align-items: center;
            }

            .list-page .item a {
                text-decoration: none;
                color: #333;
                padding: 5px 10px;
                margin: 0 2px;
                border: 1px solid #ccc;
                border-radius: 3px;
            }

            .list-page .item a.active {
                background-color: #007bff;
                color: #fff;
                border-color: #007bff;
            }

            .list-page .item a:hover {
                background-color: #f0f0f0;
            }

        </style>
    </div>

</body>
</html>