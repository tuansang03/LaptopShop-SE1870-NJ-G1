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
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <style>
            .img-preview {
                width: 80px;
                height: 80px;
                object-fit: cover; /* Để hình ảnh vừa khít, không bị méo */
                border: 1px solid #ddd; /* Đường viền xám nhạt */
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* Hiệu ứng đổ bóng */
                margin: 5px; /* Khoảng cách giữa các hình ảnh */
                border-radius: 8px; /* Bo góc ảnh cho đẹp */
            }

            .preview-container {
                display: flex;
                flex-wrap: wrap; /* Sắp xếp ảnh theo dạng lưới */
                gap: 10px; /* Khoảng cách giữa các ảnh */
            }
            .img-preview {
                width: 80px;
                height: 80px;
                object-fit: cover;
                border: 1px solid #ddd;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
                margin: 5px;
                border-radius: 8px;
            }

            .btn-danger {
                display: inline-flex;
                justify-content: center;
                align-items: center;
                height: 38px; /* Chiều cao nút xóa */
            }

            .d-flex {
                display: flex;
                gap: 10px; /* Khoảng cách giữa ảnh và nút xóa */
            }

        </style>
        <script>


            function previewImage(input, productId) {
                const imageContainer = document.getElementById('imageFileContainer-' + productId);

                // Tạo container cho ảnh preview mới
                const newPreviewContainer = document.createElement('div');
                newPreviewContainer.className = 'new-preview-container';

                // Hiển thị ảnh mới từ file input
                if (input.files) {
                    Array.from(input.files).forEach(file => {
                        var reader = new FileReader();
                        reader.onload = function (e) {
                            const img = document.createElement("img");
                            img.className = 'img-preview';
                            img.src = e.target.result;
                            newPreviewContainer.appendChild(img); // Thêm ảnh mới vào container riêng
                        };
                        reader.readAsDataURL(file);
                    });

                    // Thêm container chứa ảnh mới vào form-group mà không xóa ảnh cũ
                    imageContainer.appendChild(newPreviewContainer);
                }
            }

            function removeImage(imageId, productId) {
                const form = document.getElementById('deleteForm-' + imageId);
                const confirmation = confirm("Are you sure you want to delete this image?");
                if (confirmation) {
                    form.submit(); // Gửi form xóa ảnh nếu người dùng xác nhận
                }
            }
//            function submitDeleteForm(imageId) {
//                const confirmation = confirm("Are you sure you want to delete this image?");
//                if (confirmation) {
//                    document.getElementById('deleteForm-' + imageId).submit();
//                }
//            }
            function submitDeleteForm(imageId, pid) {
                const confirmation = confirm("Are you sure you want to delete this image?");
                if (confirmation) {
//                    document.getElementById('deleteForm-' + imageId).submit();
                    window.location.href = "deleteImage?imageId=" + imageId + "&productId=" + pid;
                }
            }
function confirmDelete(productId) {
        if (confirm("Are you sure you want to delete this product detail?")) {
            // Nếu người dùng xác nhận, chuyển đến servlet deletePDBeforeUpdate
            window.location.href = "deletePDAfterUpdate?id=" + productId;
        }
    }
        </script>
    </head>
    <body>
        <div class="container-fluid mt-5">
            <h2>Product Variants Update</h2>
            <% String errorMessage = (String) session.getAttribute("errorMessage");
        if (errorMessage != null) { %>
            <div class="alert alert-danger">
                <%= errorMessage %>
            </div>
            <% session.removeAttribute("errorMessage"); } %>

            <form action="viewDetail" method="post" enctype="multipart/form-data">
                <div class="row justify-content-center">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="card-header">Product Variants Update</div>
                            <div class="card-body">
                                <div class="form-group">
                                    <label for="productDescription">Description</label>
                                    <textarea class="form-control" id="productDescription" name="description" rows="3" placeholder="Enter product description" required>${desc}</textarea>
                                </div>

                                <c:forEach var="p" items="${pDList}">
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
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody id="productDetail_${p.id}">
                                            <tr>
                                                <td>${p.product.name} - ${p.color.name} - ${p.configuration.name}</td>
                                                <td><input type="number" name="price_${p.id}" class="form-control" value="${p.price}" required></td>
                                                <td><input type="number" name="quantity_${p.id}" class="form-control" value="${p.quantity}" required></td>
                                                <td>
                                                    <table class="table table-sm">
                                                        <thead>
                                                            <tr>
                                                                <th>Attribute</th>
                                                                <th>Value</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <c:forEach var="att" items="${attList}">
                                                                <tr>
                                                                    <td>${att.name}</td>
                                                                    <td>
                                                                        <c:set var="found" value="false"/>
                                                                        <c:forEach var="attMap" items="${attListMap[p.id]}">
                                                                            <c:if test="${att.id == attMap.attribute.id}">
                                                                                <input type="text" name="attribute_${p.id}_${attMap.attribute.id}" 
                                                                                       class="form-control" 
                                                                                       placeholder="Enter ${att.name}" 
                                                                                       value="${attMap.value}">
                                                                                <c:set var="found" value="true"/>
                                                                            </c:if>
                                                                        </c:forEach>
                                                                        <c:if test="${!found}">
                                                                            <input type="text" name="attribute_${p.id}_${att.id}" 
                                                                                   class="form-control" 
                                                                                   placeholder="Enter ${att.name}" 
                                                                                   value="">
                                                                        </c:if>
                                                                    </td>
                                                                </tr>
                                                            </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </td>
                                                <td><input type="text" name="shortDescription_${p.id}" class="form-control" value="${p.shortDescription}" required></td>
                                                <td>
                                                    <div id="imageFileContainer-${p.id}" class="form-group">
                                                        <!-- Hiển thị các ảnh đã tồn tại -->
                                                        <c:forEach var="img" items="${imgListMap[p.id]}">
                                                            <div class="d-flex align-items-center mb-2" id="imageWrapper-${img.id}">
                                                                <!-- Hiển thị ảnh hiện tại -->
                                                                <img id="imgPreview-${img.id}" src="${pageContext.request.contextPath}/images/${img.image}" alt="Image" class="img-preview">

                                                                <!-- Nút xóa ảnh với căn chỉnh đẹp hơn -->
                                                                <a class="btn btn-danger ml-3" onclick="submitDeleteForm(${img.id},${p.id})" style="height: 38px; display: flex; align-items: center;">
                                                                    <i class="bi bi-trash"></i>
                                                                </a>
                                                            </div>
                                                        </c:forEach>

                                                        <!-- Input để chọn thêm ảnh mới -->
                                                        <input type="file" multiple id="fileInput-${p.id}" name="imageFiles_${p.id}[]" accept="image/*" onchange="previewImage(this, ${p.id})">

                                                        <!-- Ảnh preview sẽ được hiển thị ở đây sau khi chọn -->
                                                    </div>
                                                </td>

                                                <td>
                                                    <button type="button" class="btn btn-danger" onclick="confirmDelete(${p.id})"><i class="bi bi-trash"></i></button>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </c:forEach>

                                <button type="submit" class="btn btn-success">Update All Products</button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>


    </body>
</html>
