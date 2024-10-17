<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="model.OrderDetail" %>





<!DOCTYPE html>
<html>
<head>
    <title>Chi tiết đơn hàng</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script>
        var totalQuantity = 0;
        var totalRefundAmount = 0;

        function updateQuantity(productId, maxQuantity) {
            var inputField = document.getElementById('quantity_' + productId);
            var currentQuantity = parseInt(inputField.value);

            if (currentQuantity < maxQuantity) {
                inputField.value = currentQuantity + 1;
                totalQuantity++;
            }
            updateRefundAmount(productId);
        }

        function decreaseQuantity(productId) {
            var inputField = document.getElementById('quantity_' + productId);
            var currentQuantity = parseInt(inputField.value);

            if (currentQuantity > 0 && totalQuantity > 1) {
                inputField.value = currentQuantity - 1;
                totalQuantity--;
            }
            updateRefundAmount(productId);
        }

        function updateRefundAmount(productId) {
            var inputField = document.getElementById('quantity_' + productId);
            var unitPriceField = document.getElementById('unitPrice_' + productId);
            var refundAmountField = document.getElementById('refundAmount_' + productId);

            var currentQuantity = parseInt(inputField.value);
            var unitPrice = parseInt(unitPriceField.innerText);

            var refundAmount = currentQuantity * unitPrice;
            refundAmountField.innerText = refundAmount;

            calculateTotalRefundAmount();
        }

        function calculateTotalRefundAmount() {
            totalRefundAmount = 0;
            var refundAmountFields = document.querySelectorAll('td[id^="refundAmount_"]');
            refundAmountFields.forEach(function(field) {
                totalRefundAmount += parseInt(field.innerText);
            });
            document.getElementById('totalRefundAmount').innerText = totalRefundAmount;
        }

        function validateForm() {
            if (totalQuantity < 1) {
                alert("Vui lòng chọn ít nhất một sản phẩm để hoàn lại.");
                return false;
            }
            return true;
        }

        function calculateTotalQuantity() {
            var inputs = document.querySelectorAll('input[id^="quantity_"]');
            inputs.forEach(function(input) {
                totalQuantity += parseInt(input.value);
            });
            calculateTotalRefundAmount();
        }

        window.onload = calculateTotalQuantity;
    </script>
</head>
<body>
    <div class="container mt-5">
        <h2>Chi tiết đơn hàng</h2>
<form action="ReturnController" method="get" onsubmit="return handleFormSubmit()" enctype="multipart/form-data">
        <table class="table table-bordered table-hover">
            <thead class="thead-dark">
                <tr>
                    <th scope="col">Product Name</th>
                    <th scope="col">Price (at the time of purchase)</th>
                    <th scope="col">Number of returned products</th>
                    <th scope="col">Refund amount</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${not empty listOrderDetail}">
                        <c:forEach var="product" items="${listOrderDetail}" varStatus="status">
                            <tr>
                                <td>${product.productDetail.product.name} (${product.productDetail.color.name}, ${product.productDetail.configuration.name})</td>
                                <td id="unitPrice_${product.productDetail.product.id}_${status.index}">${product.unitPrice}</td>
                                <td>
                                    <button type="button" onclick="decreaseQuantity('${product.productDetail.product.id}_${status.index}')" class="btn btn-danger btn-sm">-</button>
                                    <input name="orderDetailId_${product.id}" type="number" id="quantity_${product.productDetail.product.id}_${status.index}" value="${product.quantity}" min="0" max="${product.quantity}" readonly style="width: 50px; text-align: center;">
                                    <button type="button" onclick="updateQuantity('${product.productDetail.product.id}_${status.index}', ${product.quantity})" class="btn btn-success btn-sm">+</button>
                                    
                                </td>
                                <td id="refundAmount_${product.productDetail.product.id}_${status.index}">${product.quantity * product.unitPrice}</td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan="4" class="text-center">Không có thông tin để hiển thị.</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </tbody>
            <tfoot>
                <tr class="table-active">
                    <td colspan="3" class="text-right font-weight-bold">Tổng số tiền hoàn lại:</td>
                    <td id="totalRefundAmount" class="font-weight-bold">0</td>
                </tr>
            </tfoot>
        </table>

        <h4>Lý do hoàn hàng</h4>
<!--        <form action="ReturnController" method="get" onsubmit="return handleFormSubmit()" enctype="multipart/form-data">-->
            <input type="hidden" name="service" value="returnDone">
            <input type="hidden" name="money" id="hiddenRefundAmount">

            <input type="hidden" name="orderId" value="${orderId}">

            <div class="form-group">
                <label for="refundReason">Nhập lý do hoàn hàng:</label>
                <textarea class="form-control" id="refundReason" name="reason" rows="4" required></textarea>
            </div>

            <div class="form-group">
                <label for="refundMethod">Chọn phương thức hoàn tiền:</label>
                <select class="form-control" id="refundMethod" name="refundMethod" required>
                    <option value="Hoàn trả bằng tiền mặt">Hoàn trả bằng tiền mặt</option>
                    <option value="Hoàn trả bằng tiền tài khoảnorderId">Hoàn trả bằng tiền tài khoản</option>
                </select>
            </div>

            <div class="form-group">
                <label for="refundImage">Tải lên hình ảnh (nếu có):</label>
                <input type="file" class="form-control-file" id="refundImage" name="refundImage" accept="image/*">
            </div>

            <button type="submit" class="btn btn-primary">Gửi lý do</button>
        </form>
    </div>

    <script>
        function handleFormSubmit() {
            document.getElementById('hiddenRefundAmount').value = totalRefundAmount;
            return validateForm();
        }
    </script>
</body>
</html>