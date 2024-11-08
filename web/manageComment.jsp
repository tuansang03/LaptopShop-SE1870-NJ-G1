<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>ADMIN System - Manage Comments</title>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">
        <link rel="shortcut icon" href="assets/img/logo/favicon.svg">
        <link rel="stylesheet" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/font-awesome.css">
        <link rel="stylesheet" href="assets/css/animate.css">
        <link rel="stylesheet" href="assets/css/main.css">

        <%@include file="sidebar2.jsp" %>
    </head>
    <body>
<c:if test="${sessionScope.sale!=null}">
        <div class="col-md-10 content">
            <c:if test="${not empty errorMessage}">
                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    <strong>Error:</strong> ${errorMessage}
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
            </c:if>
            <style>
                .alert {
                    margin-top: 20px;
                    font-size: 16px;
                }

                .alert-danger {
                    background-color: #f8d7da;
                    color: #721c24;
                    border-color: #f5c6cb;
                }

                .alert-dismissible .close {
                    position: absolute;
                    right: 10px;
                    top: 10px;
                    color: inherit;
                }
            </style>

            <form action="handleFilterProduct" method="POST">
                <label for="productFilter">Filter by Product:</label>
                <select id="productFilter" name="productId">
                    <option value="">---Select All---</option>
                    <c:forEach items="${pList}" var="p">
                        <option value="${p.id}">${p.name}</option>
                    </c:forEach>
                </select>
                <input type="submit" value="Search">
            </form>

            <h2>Manage Comments</h2>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Product Name</th>
                        <th>Comment Content</th>
                        <th>User Id</th>
                        <th>Full Name</th>
                        <th>Date</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- Vòng lặp để in ra comment chính -->
                    <c:forEach var="comment" items="${commentList}">
                        <c:if test="${comment.repplyCommentId == null}">
                            <tr>
                                <td>${comment.id}</td>
                                <td>${comment.product.name}</td>
                                <td>${comment.commentContent}</td>
                                <td>${comment.user.id}</td>
                                <td>${comment.user.fullName}</td>
                                <td><fmt:formatDate value="${comment.commentDate}" pattern="dd/MM/yyyy HH:mm" /></td>
                                <td>
                                    <a href="deleteComment?id=${comment.id}" onclick="return confirmDelete();" class="btn btn-danger btn-sm">
                                        <i class="fas fa-trash-alt"></i>
                                    </a>
                                    <!-- Nút để mở modal nhập reply -->
                                    <button class="btn btn-success btn-sm" data-toggle="modal" data-target="#replyModal${comment.id}">
                                        <i class="fas fa-reply"></i> Reply
                                    </button>
                                </td>
                            </tr>

                            <!-- Modal cho việc nhập comment reply -->
                        <div class="modal fade" id="replyModal${comment.id}" tabindex="-1" role="dialog" aria-labelledby="replyModalLabel${comment.id}" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="replyModalLabel${comment.id}">Reply to Comment ID: ${comment.id}</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <form action="addReplyComment" method="POST">
                                        <div class="modal-body">
                                            <input type="hidden" name="repplyCommentId" value="${comment.id}" />
                                            <input type="hidden" name="productId" value="${comment.product.id}" /> <!-- Thêm trường productId -->
                                            <div class="form-group">
                                                <label for="commentContent">Reply:</label>
                                                <textarea class="form-control" id="commentContent" name="commentContent" rows="4" placeholder="Enter your reply here..." required></textarea>
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                            <button type="submit" class="btn btn-primary">Submit Reply</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>


                        <!-- In ra các reply tương ứng với comment chính -->
                        <c:forEach var="reply" items="${commentList}">
                            <c:if test="${reply.repplyCommentId == comment.id}">
                                <tr style="background-color: #f9f9f9; padding-left: 20px;">
                                    <td>${reply.id}</td>
                                    <td>${reply.product.name}</td>
                                    <td><strong>Reply:</strong> ${reply.commentContent}</td>
                                    <td>${reply.user.id}</td>
                                    <td>${reply.user.fullName}</td>
                                    <td><fmt:formatDate value="${reply.commentDate}" pattern="dd/MM/yyyy HH:mm" /></td>
                                    <td>
                                        <a href="deleteComment?id=${reply.id}" onclick="return confirmDelete();" class="btn btn-danger btn-sm">
                                            <i class="fas fa-trash-alt"></i>
                                        </a>
                                    </td>
                                </tr>
                            </c:if>
                        </c:forEach>
                    </c:if>
                </c:forEach>
                </tbody>

                <script>
                    function confirmDelete() {
                        return confirm("Are you sure you want to delete this comment?");
                    }

                    function toggleReplyForm(commentId) {
                        const form = document.getElementById(`replyForm${commentId}`);
                        if (form.style.display === "none") {
                            form.style.display = "table-row";
                        } else {
                            form.style.display = "none";
                        }
                    }
                </script>



            </table>

            <div>
                <!-- Nút "Add Comment" để mở modal -->
                <a href="#" class="btn btn-success" data-toggle="modal" data-target="#addCommentModal">Add Comment</a>

                <!-- Modal để nhập comment mới -->
                <div class="modal fade" id="addCommentModal" tabindex="-1" role="dialog" aria-labelledby="addCommentModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="addCommentModalLabel">Add New Comment</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <form action="addComment" method="POST">
                                <div class="modal-body">
                                    <div class="form-group">
                                        <label for="productFilter">Add comment to Product:</label>
                                        <select id="productFilter" name="productId" class="form-control" required>
                                            <!-- Thêm option cho lựa chọn không có sản phẩm -->
                                            <c:forEach items="${pList}" var="p">
                                                <option value="${p.id}" required>${p.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="commentContent">Comment:</label>
                                        <textarea class="form-control" id="commentContent" name="commentContent" rows="4" placeholder="Enter your comment here..." required></textarea>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                    <button type="submit" class="btn btn-primary">Submit Comment</button>
                                </div>
                            </form>

                        </div>
                    </div>
                </div>

                <div class="list-page">
                    <div class="item">
                        <!-- Nút Previous chỉ hiện nếu trang hiện tại lớn hơn 1 -->
                        <c:if test="${tag > 1}">
                            <a href="readComment?index=${tag - 1}">
                                <i class="fa fa-long-arrow-left"></i> Previous
                            </a>
                        </c:if>

                        <!-- Vòng lặp để hiển thị các số trang -->
                        <c:forEach begin="1" end="${endPage}" var="i">
                            <a class="${tag == i ? 'active' : ''}" href="readComment?index=${i}">
                                ${i}
                            </a>
                        </c:forEach>

                        <!-- Nút Next chỉ hiện nếu trang hiện tại nhỏ hơn tổng số trang -->
                        <c:if test="${tag < endPage}">
                            <a href="readComment?index=${tag + 1}">
                                Next <i class="fa fa-long-arrow-right"></i>
                            </a>
                        </c:if>
                    </div>
                </div>

            </div>

            <style>
                .btn-success {
                    transition: background-color 0.3s, color 0.3s;
                }

                .btn-success:hover {
                    background-color: #28a745; /* màu xanh lá cây đậm */
                    color: #ffffff; /* màu chữ trắng */
                }

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
        <c:if test="${sessionScope.admin!=null || sessionScope.user!=null || (sessionScope.user==null && 
                      sessionScope.sale==null && sessionScope.admin==null)}">
            <%@include file="notallowpage.jsp" %>
        </c:if>

        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
