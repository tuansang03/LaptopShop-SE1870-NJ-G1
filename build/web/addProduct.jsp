<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add New Product</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">
    <link rel="shortcut icon" href="assets/img/logo/favicon.svg">
    <link rel="stylesheet" href="assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/font-awesome.css">
    <link rel="stylesheet" href="assets/css/animate.css">
    <link rel="stylesheet" href="assets/css/main.css">
</head>
<body>

<div class="container-fluid mt-5">
    <h2>Add New Product</h2>
    <div class="row justify-content-center">
        <div class="col-md-12"> <!-- Sửa từ col-md-8 thành col-md-12 -->
            <div class="card">
                <div class="card-header">Product Details</div>
                <div class="card-body">
                    <form id="addProductForm" action="addProduct" method="post">
                        <!-- Product Name -->
                        <div class="form-group">
                            <label for="productName">Product Name</label>
                            <input type="text" class="form-control" id="productName" name="productName" placeholder="Enter product name" required>
                        </div>
                        <!-- Brand -->
                        <div class="form-group">
                            <label for="productBrand">Brand</label>
                            <select class="form-control" id="productBrand" name="brand" required>
                                <c:forEach var="b" items="${bList}">
                                    <option value="${b.id}">${b.name}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <!-- Category -->
                        <div class="form-group">
                            <label for="productCategory">Category</label>
                            <select class="form-control" id="productCategory" name="category" required>
                                <c:forEach var="c" items="${cList}">
                                    <option value="${c.id}">${c.name}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <!-- Description -->
                        <div class="form-group">
                            <label for="productDescription">Description</label>
                            <textarea class="form-control" id="productDescription" name="description" rows="3" placeholder="Enter product description" required></textarea>
                        </div>

                        <!-- Chọn nhiều màu với checkbox -->
                        <div class="form-group">
                            <label for="productColors">Colors</label><br/>
                            <c:forEach var="color" items="${colorList}">
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="checkbox" id="color${color.id}" name="colors" value="${color.id}">
                                    <label class="form-check-label" for="color${color.id}">${color.name}</label>
                                </div>
                            </c:forEach>
                        </div>

                        <!-- Chọn nhiều configurations với checkbox -->
                        <div class="form-group">
                            <label for="productConfigs">Configurations</label><br/>
                            <c:forEach var="config" items="${configList}">
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="checkbox" id="config${config.id}" name="configurations" value="${config.id}">
                                    <label class="form-check-label" for="config${config.id}">${config.name}</label>
                                </div>
                            </c:forEach>
                        </div>

                        <button type="submit" class="btn btn-success">Add Product</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Import Bootstrap và jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script>
// Kiểm tra trước khi submit form
document.getElementById("addProductForm").addEventListener("submit", function(event) {
    const colors = document.querySelectorAll('input[name="colors"]:checked');
    const configs = document.querySelectorAll('input[name="configurations"]:checked');

    if (colors.length === 0 || configs.length === 0) {
        event.preventDefault();
        alert("Please select at least one color and one configuration.");
    }
});
</script>
</body>
</html>
