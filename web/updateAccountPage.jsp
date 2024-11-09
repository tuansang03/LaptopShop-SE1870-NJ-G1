<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ADMIN System - Update Account</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">
    <link rel="shortcut icon" href="assets/img/logo/favicon.svg">
    <link rel="stylesheet" href="assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/font-awesome.css">
    <link rel="stylesheet" href="assets/css/animate.css">
    <link rel="stylesheet" href="assets/css/main.css">

    <style>
        .content {
            padding: 20px;
        }
    </style>
</head>
<body>
   

        <c:if test="${sessionScope.sale!=null}">
        <%@ include file="sidebar2.jsp" %>
        </c:if>
        <c:if test="${sessionScope.admin!=null}">
        <%@ include file="sidebar.jsp" %>
        </c:if>

<div class="col-md-10 content">
    <h2>Update account</h2>

    <!-- Thông báo thành công hoặc thất bại -->
    <c:if test="${not empty message}">
        <div class="alert alert-success">
            ${message}
        </div>
    </c:if>
    <c:if test="${not empty error}">
        <div class="alert alert-danger">
            ${error}
        </div>
</c:if>

    <form action="updateAccountAction" method="post" class="bg-light p-4 border rounded">
        <c:choose>
            <c:when test="${not empty sessionScope.admin}">
                <!-- Hiển thị thông tin admin -->
                <div class="form-group">
                    <label for="username">Username:</label>
                    <input type="text" id="username" name="username" class="form-control" value="${sessionScope.admin.userName}" readonly>
                </div>

                <div class="form-group">
                    <label for="fullName">Full Name:</label>
                    <input type="text" name="fullName" class="form-control" value="${sessionScope.admin.fullName}" required>
                </div>
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" class="form-control" value="${sessionScope.admin.email}" required>
                </div>
            </c:when>
            <c:otherwise>
                <!-- Hiển thị thông tin saler -->
                <div class="form-group">
                    <label for="username">Username:</label>
                    <input type="text" id="username" name="username" class="form-control" value="${sessionScope.sale.userName}"  readonly>
                </div>

                <div class="form-group">
                    <label for="fullName">Full Name:</label>
                    <input type="text" name="fullName" class="form-control" value="${sessionScope.sale.fullName}" required>
                </div>
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" class="form-control" value="${sessionScope.sale.email}" required>
                </div>
            </c:otherwise>
        </c:choose>

        <button type="submit" class="btn btn-primary">Update</button>
    </form>

    <h3 class="mt-4">Change password</h3>
    <form action="changePassword" method="post" class="bg-light p-4 border rounded">
        <div class="form-group">
            <label for="currentPassword">Current Password:</label>
            <input type="password" id="currentPassword" name="currentPassword" class="form-control" value="${currentPassword}" required>
        </div>
        <div class="form-group">
            <label for="newPassword">New Password:</label>
            <input type="password" id="newPassword" name="newPassword" class="form-control" value ="${newPassword}" required>
        </div>
        <div class="form-group">
            <label for="confirmPassword">Comfirm new password:</label>
            <input type="password" id="confirmPassword" name="confirmPassword" class="form-control" value ="${confirmPassword}" required>
        </div>

        <button type="submit" class="btn btn-success">Change Password</button>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
