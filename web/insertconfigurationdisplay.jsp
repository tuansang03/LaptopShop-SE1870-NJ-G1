<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Configuration</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
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
    <c:if test="${sessionScope.admin!=null}">
    <%@include file="sidebar.jsp" %>

    <div class="col-md-10 content">
        <h2>Add Configuration</h2>
        <form action="ConfigurationManageController" method="GET">
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Name</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>
                          <input type="text" name="name" placeholder="Enter configuration name" 
       required class="form-control" maxlength="30" title="Please enter up to 30 characters.">

                        </td>
                    </tr>
                </tbody>
            </table>

            <!-- Input ẩn để gửi thêm biến service với giá trị addConfig -->
            <input type="hidden" name="service" value="addConfigDone">

            <button type="submit" class="btn btn-primary">Add Configuration</button>

            <c:if test="${not empty mess}">
                <span class="text-success">${mess}</span>
            </c:if>
        </form>
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
    </c:if>
    <c:if test="${sessionScope.sale!=null || sessionScope.user!=null || (sessionScope.user==null && 
                      sessionScope.sale==null && sessionScope.admin==null)}">
            <%@include file="notallowpage.jsp" %>
        </c:if>
</body>
</html>
