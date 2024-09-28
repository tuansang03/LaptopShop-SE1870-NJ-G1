<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>ADMIN System - Manage Products</title>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">
        <link rel="shortcut icon" href="assets/img/logo/favicon.svg">
        <link rel="stylesheet" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/font-awesome.css">
        <link rel="stylesheet" href="assets/css/animate.css">
        <link rel="stylesheet" href="assets/css/main.css">

        <script>
            function confirmDelete() {
                return confirm("Are you sure you want to delete this product?");
            }
        </script>

        <%@include file="sidebar.jsp" %>

    <div class="col-md-10 content">
        <h2>Manage Product</h2>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Brand</th>
                    <th>Category</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="p" items="${pList}">
                    <tr>
                        <td>${p.id}</td>
                        <td>${p.name}</td>
                        <td>${p.brand.name}</td>
                        <td>${p.category.name}</td>
                        <td>
                            <a href="viewDetail?id=${p.id}">View detail</a> 
                            <a href="deleteProduct?id=${p.id}" onclick="return confirmDelete();">
                                <i class="fas fa-trash-alt" style="color:red;"></i>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div>
            <a href="addProduct">Add product</a>
            <div class="list-page">
                <div class="item">
                    <c:if test="${tag > 1}">
                        <a href="readProduct?index=${tag - 1}"><i class="fa fa-long-arrow-left"></i><-</a>
                    </c:if>
                    <c:forEach begin="1" end="${endPage}" var="i">
                        <a class="${tag == i ? 'active' : ''}" href="readProduct?index=${i}">${i}</a>
                    </c:forEach>
                    <c:if test="${tag < endPage}">
                        <a href="readProduct?index=${tag + 1}"><i class="fa fa-long-arrow-right">-></i></a>
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

    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
