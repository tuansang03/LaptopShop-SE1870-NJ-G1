<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Product Variants</title>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <script>
            function addImageUrl(containerId, productDetailId) {
                const imageContainer = document.getElementById(containerId);
                const newInput = document.createElement("input");
                newInput.type = "text";
                newInput.name = "imageUrls_" + productDetailId + "[]";  // Tên phải khớp với tên trong form ban đầu
                newInput.className = "form-control mt-2";
                newInput.placeholder = "Enter image URL";
                imageContainer.appendChild(newInput);
            }

           
        </script>
    </head>
    <body>

        <div class="container-fluid mt-5">
            <h2>Product Variants</h2>
            <form action="inputProductDetail" method="post">
                <div class="row justify-content-center">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="card-header">Product Variants</div>
                            <div class="card-body">
                                <c:forEach var="p" items="${pDList}">
                                    <!-- 1 Product -->
                                    <h5>Product Detail for ${p.product.name}</h5>
                                    <table class="table table-bordered">
                                        <thead>
                                            <tr>
                                                <th>Product Detail</th>
                                                <th>Price</th>
                                                <th>Quantity</th>
                                                <th>Attributes</th>
                                                <th>Short Description</th>
                                                <th>Images</th>
                                                <th>Action</th> <!-- Thêm cột Action -->
                                            </tr>
                                        </thead>
                                        <tbody id="productDetail_${p.id}"> <!-- Thêm ID cho tbody -->
                                            <tr>
                                                <td>${p.product.name} - ${p.color.name} - ${p.configuration.name}</td>
                                                <!-- Price -->
                                                <td><input type="number" name="price_${p.id}" class="form-control" value="${p.price}" required></td>
                                                <!-- Quantity -->
                                                <td><input type="number" name="quantity_${p.id}" class="form-control" value="${p.quantity}" required></td>

                                                <!-- Attributes -->
                                                <td>
                                                    <table class="table table-sm">
                                                        <thead>
                                                            <tr><th>Attribute</th><th>Value</th></tr>
                                                        </thead>
                                                        <tbody>
                                                            <c:forEach var="att" items="${attList}">
                                                                <tr>
                                                                    <td>${att.name}</td>
                                                                    <td>
                                                                        <input type="text" name="attribute_${p.id}_${att.id}" 
                                                                               class="form-control" 
                                                                               placeholder="Enter ${att.name}">
                                                                    </td>
                                                                </tr>
                                                            </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </td>

                                                <!-- Short Description -->
                                                <td><input type="text" name="shortDescription_${p.id}" class="form-control" value="${p.shortDescription}" required></td>

                                                <!-- Image URLs -->
                                                <td>
                                                    <div id="imageUrlContainer-${p.id}" class="form-group">
                                                        <input type="text" name="imageUrls_${p.id}[]" class="form-control" placeholder="Enter image URL">
                                                    </div>
                                                    <button type="button" class="btn btn-secondary" onclick="addImageUrl('imageUrlContainer-${p.id}', '${p.id}')">Add Another Image URL</button>
                                                </td>

                                                <!-- Action Column: Nút xóa -->
                                                <td>
                                                    <button type="button" class="btn btn-danger" onclick="confirmDelete(${p.id})">Delete</button>
                                                </td>

                                        <script>
                                            function confirmDelete(productId) {
                                                const confirmation = confirm("Are you sure you want to delete this product?");
                                                if (confirmation) {
                                                    // Nếu người dùng xác nhận, điều hướng đến URL xóa
                                                    window.location.href = "deletePDBeforeUpdate?" + productId;
                                                }
                                            }
                                        </script>
                                        </tr>
                                        </tbody>
                                    </table>
                                </c:forEach>

                                <!-- Submit tất cả sản phẩm -->
                                <button type="submit" class="btn btn-success">Add All Products</button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
