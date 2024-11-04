<%-- 
    Document   : viewOrderDetail
    Created on : Oct 11, 2024, 11:55:11 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>View OrderDetail</title>
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
    </head>
    <body>
        <%@include file="sidebar2.jsp" %>

        <div class="col-md-10 content">
            <h2>View OrderDetail</h2>

            <!-- Bảng hiển thị danh mục -->
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Image</th>
                        <th>Product Name</th>
                        <th>Brand</th>
                        <th>Color</th>
                        <th>Configuration</th>
                        <th>Quantity</th>
                        <th>Unit Price</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${listOrderDetail}" var="odt">
                        <tr>
                            <td>${odt.getId()}</td>
                            <td>
                                <c:forEach items="${listImages}" var="i">
                                    <div class="d-flex">
                                        <c:if test="${odt.getProductDetail().getId() == i.getProductDetail().getId()}">
                                            <img style="width: 100px" src="${pageContext.request.contextPath}/images/${i.getImage()}" alt="">
                                        </c:if>
                                    </div>
                                </c:forEach>
                            </td>
                            <td>${odt.getProductDetail().getProduct().getName()}</td>
                            <td>${odt.getProductDetail().getProduct().getBrand().getName()}</td>
                            <td>${odt.getProductDetail().getColor().getName()}</td>
                            <td>${odt.getProductDetail().getConfiguration().getName()}</td>
                            <td>${odt.getQuantity()}</td>
                            <td><fmt:formatNumber value="${odt.getUnitPrice()}" pattern="#,###" /></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
