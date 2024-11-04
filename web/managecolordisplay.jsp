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

    <div class="col-md-7 content">
        <h2>Manage Color</h2>

        <!-- Form tìm kiếm thương hiệu -->



        <!-- Nút Add Brand -->
        <form action="ColorController" method="GET" class="form-inline mb-3">
            <input type="hidden" name="service" value="addColorRequest">
            <button type="submit" class="btn btn-success">Add New Color</button>
        </form>

        <!-- Thông báo -->
        <c:if test="${not empty mess}">
            <div>
                <c:if test="${mess == 'Add Color successful!' || mess == 'Delete successfull!' ||mess == 'Update successfull!!'}">
                    <c:set var="color" value="#2dad2d"></c:set>
                </c:if>
                
                <div style="background-color: ${color}; color: Black; padding: 10px">${mess}</div>
            </div>
        </c:if>

        <!-- Phần hiển thị bảng trong Manage Brand -->
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Action</th>
                </tr>
            </thead>

            <tbody>
                <c:forEach var="p" items="${listColor}"> 
                    <tr>
                        <td style="text-align: center">${p.id}</td>
                        <td  style="text-align: center">${p.name} <div style="padding: 15px; background: ${p.name.toLowerCase()};"></div></td>
                        <td>
                            <div class="action-buttons">
                                <!-- Nút Update -->
                                <button class="btn btn-warning" onclick="editBrand('${p.id}');">
                                    <i class="fas fa-edit"></i> Update
                                </button>

                                <!-- Nút Delete -->
                                <form action="ColorController" method="GET" style="display:inline;">
                                    <input type="hidden" name="id" value="${p.id}">
                                    <input type="hidden" name="service" value="deleteColor">
                                    <button type="submit" onclick="return confirm('Are you sure you want to delete this Color ?');" class="btn btn-danger">
                                        <i class="fas fa-trash-alt"></i> Delete
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
    function editBrand(brandId) {
        // Chuyển hướng trực tiếp đến servlet mà không cần xác nhận
        window.location.href = 'ColorController?id=' + brandId + '&service=updateColorRequest';
    }
</script>



</body>
</html>
