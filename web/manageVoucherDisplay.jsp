<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Manage Voucher</title>
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
        <%@include file="sidebar.jsp" %>

        <div class="col-md-10 content">
            <h2>Manage Voucher</h2>

            <!-- Form tìm kiếm danh mục -->
            <div style="display: flex; align-items: center">
                <form style="width: 100%;" action="searchVoucher" class="form-inline mb-3">
                    <input type="text" name="search" value="${search}" class="form-control mr-2" placeholder="Search Voucher">
                    <button type="submit" class="btn btn-primary">Search</button>

                    <select name="op" style="
                            margin-left: 10px;
                            text-align: center;
                            height: 48px;
                            width: 10%;
                            color: black;
                            border: 2px solid #28a745;
                            background-color: white;
                            border-radius: 4px;
                            padding: 5px;
                            font-size: 16px;
                            cursor: pointer;
                            outline: none;
                            ">
                        <option ${op.equals('all') ? 'selected':''} value="all">All</option>
                        <option ${op.equals('active') ? 'selected':''} value="active">Active</option>
                        <option ${op.equals('expired') ? 'selected':''} value="expired">Expired</option>
                    </select>

                </form>

            </div>
            <!-- Nút Add Category -->
            <form action="insertVoucherDisplay.jsp" method="POST" class="form-inline mb-3">
                <input type="hidden" name="service" value="addPostRequest">
                <button type="submit" class="btn btn-success">Add Voucher</button>
            </form>

            <!-- Bảng hiển thị danh mục -->
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Code</th>
                        <th>Name</th>
                        <th>DiscountPercent</th>
                        <th>Quantity</th>
                        <th>UsedQuantity</th>
                        <th>StartDate</th>
                        <th>EndDate</th>
                        <th>MinValue</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach items="${listVoucher}" var="v"> 
                        <tr>
                            <td>${v.getId()}</td>
                            <td>${v.getCode()}</td>
                            <td>${v.getName()}</td>
                            <td>${v.getDiscountPercent()}</td>
                            <td>${v.getQuantity()}</td>
                            <td>${v.getUsedQuantity()}</td>
                            <td>${v.getStartDate()}</td>
                            <td>${v.getEndDate()}</td>
                            <td><fmt:formatNumber value="${v.minValue}" /></td>
                            <c:if test="${v.getStatus() == 1}">
                                <td><span class="text-success status-icon"">&#10003;</span></span></td>
                            </c:if>
                            <c:if test="${v.getStatus() == 0}">
                                <td><span class="text-danger status-icon">&#10006;</span></td>
                            </c:if>    
                            <td >
                                <div style="display: flex; justify-content: space-around" >
                                    <div>
                                        <a href="deleteVoucher?id=${v.getId()}">
                                            <i class="fas fa-trash-alt"></i>
                                        </a>
                                    </div>
                                    <div>
                                        <a href="editVoucher?id=${v.getId()}">
                                            <i class="fas fa-edit"></i>
                                        </a>
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
