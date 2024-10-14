<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Update Post</title>
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
        <style>
            .list-page {
                display: flex;
                justify-content: center;
                margin-top: 20px;
            }
            .select{
                width: 100%;
                display: flex;
                justify-content: center;
                gap: 3%;
                margin-top: 1%;
            }
            .select select{
                width: 48.3%;
                padding: 0.5%;
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
        <%@include file="sidebar.jsp" %>

        <div class="col-md-10 content">
            <h2>Manage Post</h2>
            <form action="postmanage" method="POST">
                <table class="table table-bordered" style="width: 80%;">
                    <thead>
                        <tr>
                            <th>Name</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                <input type="hidden" name="id" value="${updatepostdisplay.getId()}">
                                <input type="text" name="title" placeholder="Enter post title" value="${updatepostdisplay.tittle}" required class="form-control" maxlength="250">


                                <input type="text" name="thum" placeholder="Enter thumbnail link"  value="${updatepostdisplay.thumbnail}" required class="form-control" style="margin-top: 1%">
                                <div class="select">

                                    <select required name="brand">

                                        <option value="${updatepostdisplay.brand.id}"  selected>${updatepostdisplay.brand.name}</option>
                                        <c:forEach items="${brandList}" var="brand">
                                            <option value="${brand.getId()}">${brand.getName()}</option>
                                        </c:forEach>

                                    </select>


                                    <select required name='category'>

                                        <option value="${updatepostdisplay.category.id}"  selected>${updatepostdisplay.category.name}</option>
                                        <c:forEach items="${categoryList}" var="cate">
                                            <option value="${cate.getId()}">${cate.getName()}</option>
                                        </c:forEach>


                                    </select>
<!--                                    <input value="${sessionScope.user.fullName}" disabled selected>-->


                                </div>

                                <textarea name="short" placeholder="Enter short content" required class="form-control"
                                          style="margin-top: 1%; width: 100%; height: 100px;" maxlength="500">${updatepostdisplay.shortContent}</textarea>
                                <p id="charCount">Max 500 characters</p>

                                <textarea name='full' id='default' placeholder="Enter Full content">${updatepostdisplay.fullContent}</textarea>
                            </td>



                        </tr>
                    </tbody>
                </table>

                <!-- Input ẩn để gửi thêm biến service với giá trị updatedone -->
                <input type="hidden" name="service" value="updatedone">

                <button type="submit" class="btn btn-primary">Update Post</button>

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
        <script src='./tinymce/tinymce.min.js'></script>
        <script src='./js/tinyMceConfig.js'></script>
    </body>
</html>
