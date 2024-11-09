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

        /* Style cho nút Add Configuration */
        .btn-add-config {
            margin-bottom: 20px;
        }

        /* Style cho ô tìm kiếm */
        .search-bar {
            display: flex;
            margin-bottom: 20px;
        }

        .search-bar input[type="text"] {
            flex: 1;
            padding: 5px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }

        .search-bar button {
            padding: 5px 10px;
            margin-left: 10px;
            font-size: 16px;
        }
    </style>
</head>
<body>
   
    <div class="col-md-10 content">
        <h2>Manage Configuration</h2>

        <!-- Ô tìm kiếm Configuration -->
<form class="search-bar" action="ConfigurationManageController" method="GET">
    <input type="hidden" name="service" value="searchConfigulation">
    <input type="text" name="search" placeholder="Search configuration..." 
           value="${param.search}" maxlength="25" pattern=".{0,25}" title="Please enter up to 25 characters.">
    <button type="submit" class="btn btn-primary">
        <i class="fas fa-search"></i> Search
    </button>
</form>



        <!-- Nút Add Configuration -->
        <div class="mb-3">
            <button class="btn btn-success btn-add-config" onclick="window.location.href='ConfigurationManageController?service=addConfig';">
                <i class="fas fa-plus"></i> Add Configuration
            </button>
        </div>

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
                window.location.href = 'ConfigurationManageController?id=' + configId + '&service=editConfig';
            }

            function deleteConfig(configId) {
                if (confirm('Are you sure you want to delete this configuration?')) {
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
