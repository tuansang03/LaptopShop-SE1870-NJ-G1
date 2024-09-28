<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
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

                // Tạo một div mới để chứa input và nút xóa
                const newDiv = document.createElement("div");
                newDiv.className = "form-group mt-2"; // Thêm lớp để định dạng

                // Tạo input cho URL
                const newInput = document.createElement("input");
                newInput.type = "text";
                newInput.name = "imageUrls_" + productDetailId + "[]";
                newInput.className = "form-control";
                newInput.placeholder = "Enter image URL";

                // Tạo nút xóa
                const deleteButton = document.createElement("button");
                deleteButton.type = "button";
                deleteButton.className = "btn btn-danger mt-2";
                deleteButton.innerHTML = '<i class="bi bi-trash"></i>';
                deleteButton.onclick = function () {
                    newDiv.remove(); // Xóa div chứa input và nút xóa
                };

                // Thêm input và nút xóa vào div mới
                newDiv.appendChild(newInput);
                newDiv.appendChild(deleteButton);

                // Thêm div mới vào container
                imageContainer.appendChild(newDiv);
            }



        </script>
    </head>
    <body>
      

        <div class="container-fluid mt-5">
            <h2>Product Variants</h2>
            <% String errorMessage = (String) session.getAttribute("errorMessage");
   if (errorMessage != null) { %>
   <div class="alert alert-danger">
       <%= errorMessage %>
   </div>
<% session.removeAttribute("errorMessage"); } %>

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
                                                                               placeholder="Enter ${att.name}" required>
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
                                                        <input type="text" name="imageUrls_${p.id}[]" class="form-control" placeholder="Enter image URL" required>
                                                    </div>
                                                    <button type="button" class="btn btn-secondary" onclick="addImageUrl('imageUrlContainer-${p.id}', '${p.id}')">Add Another Image URL</button>
                                                </td>

                                                <!-- Action Column: Nút xóa -->
                                                <td>
                                                    <button type="button" class="btn btn-danger" onclick="confirmDelete(${p.id})"> <i class="bi bi-trash"></i></button>
                                                   
                                                </td>

                                        <script>
                                            function confirmDelete(productId) {
                                                const confirmation = confirm("Are you sure you want to delete this product?");
                                                if (confirmation) {
                                                    // Nếu người dùng xác nhận, điều hướng đến URL xóa
                                                    window.location.href = "deletePDBeforeUpdate?id=" + productId;
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
