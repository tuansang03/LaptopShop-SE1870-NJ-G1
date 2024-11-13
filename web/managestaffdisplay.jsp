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
        <style>
            .status-icon {
                font-size: 24px;
                cursor: pointer;
                transition: color 0.3s;
            }

            .status-icon:hover {
                color: #28a745; /* Màu xanh cho trạng thái hoạt động */
            }

            .status-icon.banned:hover {
                color: #dc3545; /* Màu đỏ cho trạng thái bị ban */
            }
        </style>
    </head>
    <body>
        <div class="col-md-10 content">
            <h2>Manage Staff</h2>



            <!-- Thêm form tìm kiếm user -->
            <form action="CustomerManageController" method="GET" class="form-inline mb-3">
                <input type="text" name="keyword" class="form-control mr-2" placeholder="Search by username or email" value="${param.keyword}">
                <input type="hidden" name="service" value="searchStaff">
                <button type="submit" class="btn btn-primary">Search</button>
            </form>
            <!-- Add Staff Button -->
            <a href="CustomerManageController?service=addStaff" class="btn btn-success mb-3">Add Staff</a>

            <!-- Thông báo -->
            <c:if test="${not empty mess}">
                <div class="alert ${mess == 'Ban successful' || mess == 'Unban successful' || mess == 'Delete successful' ? 'alert-success' : 'alert-danger'}">
                    ${mess}
                </div>
            </c:if>

            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>User Name</th>
                        <th>Full Name</th>
                        <th>Email</th>
                        <th>Status</th>
                        <!--<th>Action</th>  Cột Action -->
                    </tr>
                </thead>

                <tbody>
                    <c:forEach var="user" items="${listStaff}">
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.userName}</td>
                            <td>${user.fullName}</td>
                            <td>${user.email}</td>
                            <!-- Hiển thị trạng thái -->
                            <td>
                                <c:choose>
                                    <c:when test="${user.status != 'ban'}">
                                        <!-- Khi nhấn vào dấu tích sẽ gọi hàm updateStatus để ban staff -->
                                        <span class="text-success status-icon" onclick="updateStatus('${user.id}', 'ban');">&#10003;</span>
                                    </c:when>
                                    <c:otherwise>
                                        <!-- Khi nhấn vào dấu x sẽ gọi hàm updateStatus để unban staff -->
                                        <span class="text-danger status-icon banned" onclick="updateStatus('${user.id}', 'unban');">&#10006;</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
<!--                            <td>
                                <div class="action-buttons">
                                     Nút Delete 
                                    <form action="CustomerManageController" method="GET" style="display:inline;">
                                        <input type="hidden" name="id" value="${user.id}">
                                        <input type="hidden" name="service" value="deleteStaff">
                                        <button type="submit" onclick="return confirm('Are you sure you want to delete this staff?');" class="btn btn-danger">
                                            <i class="fas fa-trash-alt"></i> Delete
                                        </button>
                                    </form>
                                </div>
                            </td>-->
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <script>
                function updateStatus(userId, action) {
                    let confirmMessage = action === 'ban'
                            ? 'Are you sure you want to ban this staff?'
                            : 'Are you sure you want to unban this staff?';

                    if (confirm(confirmMessage)) {
                        // Chuyển hướng đến servlet với tham số service tương ứng
                        window.location.href = 'CustomerManageController?id=' + userId + '&service=' + action + 'Staff';
                    }
                }
            </script>

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
