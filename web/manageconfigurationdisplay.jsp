<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ADMIN System - Manage Configuration</title>
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
        .action-buttons {
            display: flex;
            gap: 10px;
        }

        .action-buttons .btn {
            padding: 5px 10px;
        }
    </style>
</head>
<body>
    <div class="col-md-10 content">
        <h2>Manage Configuration</h2>

        <!-- Thông báo -->
        <c:if test="${not empty mess}">
            <div class="alert ${mess == 'Update successful' ? 'alert-success' : 'alert-danger'}">
                ${mess}
            </div>
        </c:if>

        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Configuration Name</th>
                    <th>Action</th>
                </tr>
            </thead>

            <tbody>
                <c:forEach var="config" items="${listConfiguration}"> 
                    <tr>
                        <td>${config.id}</td>
                        <td>${config.name}</td>
                        <td>
                            <div class="action-buttons">
                                <!-- Nút Update -->
                                <button class="btn btn-warning" onclick="editConfig('${config.id}');">
                                    <i class="fas fa-edit"></i> Update
                                </button>

                                <!-- Nút Delete -->
                                <button class="btn btn-danger" onclick="deleteConfig('${config.id}');">
                                    <i class="fas fa-trash-alt"></i> Delete
                                </button>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <script>
            function editConfig(configId) {
                if (confirm('Are you sure you want to edit this configuration?')) {
                    // Chuyển hướng đến servlet với tham số
                    window.location.href = 'ConfigurationManageController?id=' + configId + '&service=editConfig';
                }
            }

            function deleteConfig(configId) {
                if (confirm('Are you sure you want to delete this configuration?')) {
                    // Chuyển hướng đến servlet với tham số
                    window.location.href = 'ConfigurationManageController?id=' + configId + '&service=deleteConfig';
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
