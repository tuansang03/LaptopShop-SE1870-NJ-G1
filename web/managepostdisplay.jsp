<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Manage Post</title>
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
        <c:if test="${sessionScope.sale!=null}">
        <%@include file="sidebar2.jsp" %>

        <div class="col-md-10 content">
            <h2>Manage Post</h2>

            <!-- Form tìm kiếm danh mục -->
            <form action="postmanage" method="POST" class="form-inline mb-3">
                <input type="text" name="search" class="form-control mr-2" placeholder="Search Category">
                <input type="hidden" name="service" value="searchPost">
                <button type="submit" class="btn btn-primary">Search</button>
            </form>
            <!-- Nút Add Category -->
            <form action="postmanage" method="POST" class="form-inline mb-3">
                <input type="hidden" name="service" value="addPostRequest">
                <button type="submit" class="btn btn-success">Add Post</button>
            </form>

            <!-- Thông báo -->
            <c:if test="${not empty mess}">
                <div class="alert ${mess == 'Delete successful' ? 'alert-success' : 'alert-danger'}">
                    ${mess}
                </div>
            </c:if>

            <!-- Bảng hiển thị danh mục -->
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Author</th>

                        <th>Preview</th>

                        <th>Action</th>
                    </tr>
                </thead>

                <tbody>
                <style>
                    .tede{
                        max-height: 300px !important;
                        width: 100% !important;
                    }
                    .tede img{

                    }

                </style>
                <c:forEach var="p" items="${listPost}"> 
                    <tr class="tede">
                        <td>${p.getId()}</td>
                        <td>${p.getUser().getUserName()}</td>

                        <td style="height: 100%; width: 80%">
                            <div class="row needToFix">


                                <style>
                                    .tach{
                                        margin-right: 15px;
                                    }
                                    .needToFix{
                                        width: 100%;
                                    }
                                    .card-blog{
                                        display: flex !important;
                                    }
                                    .dip{
                                        padding: 0px 30px;

                                    }
                                    .dip img{
                                        height: 300px;
                                        width: 320px;
                                    }

                                </style>
                                <div class="post tach" >
                                    <div class="card card-blog" >
                                        <div class="row no-gutters" style="width: 100%">
                                            <!-- Cột hiển thị hình ảnh thumbnail -->
                                            <div class="col-md-2 dip">
                                                <div class="card-blog__img">
                                                    <img style="width: 100%; height: 150px" class="card-img rounded-0" src="${p.thumbnail}" alt="${p.tittle}">
                                                </div>
                                            </div>
                                            <!-- Cột hiển thị thông tin bài viết -->
                                            <div class="col-md-9">
                                                <div class="card-body">
                                                   
                                                    <h4 class="card-blog__title">
                                                        <a href="postdetail?id=${p.id}"style="font-size: 70%">${p.tittle}</a>
                                                    </h4>
                                                        <p style="font-size: 15px">${p.shortContent}</p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </td>




                        <td>
                            <div class="action-buttons">

                                <button class="btn btn-warning" onclick="editPost('${p.id}');">
                                    <i class="fas fa-edit"></i> Update
                                </button>


                                <form action="postmanage" method="POST" style="display:inline;">
                                    <input type="hidden" name="id" value="${p.id}">
                                    <input type="hidden" name="service" value="deletePost">
                                    <button type="submit" onclick="return confirm('Are you sure you want to delete this category?');" class="btn btn-danger">
                                        <i class="fas fa-trash-alt"></i> Delete <br/>

                                    </button>
                                </form>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

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

        <script>
            function editPost(postId) {
                // Chuyển hướng trực tiếp đến servlet mà không cần xác nhận
                window.location.href = 'postmanage?id=' + postId + '&service=updatePostRequest';
            }
        </script>
        </c:if>
        <c:if test="${sessionScope.admin!=null || sessionScope.user!=null || (sessionScope.user==null && 
                      sessionScope.sale==null && sessionScope.admin==null)}">
            <%@include file="notallowpage.jsp" %>
        </c:if>
    </body>
</html>
