<%-- 
    Document   : billOrder.jsp
    Created on : Oct 28, 2024, 5:18:23 PM
    Author     : ADMIN
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Laptop Purchase Invoice</title>
        <link rel="stylesheet" href="styles.css">
        <style>
            body {
                font-family: 'Arial', sans-serif;
                background-color: #f0f2f5;
                margin: 0;
                padding: 20px;
            }

            .container {
                max-width: 600px;
                margin: auto;
                background: white;
                padding: 30px;
                border-radius: 10px;
                box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
            }

            h1 {
                text-align: center;
                color: #333;
                margin-bottom: 20px;
                font-size: 24px;
                text-transform: uppercase;
            }

            .bill-info {
                margin-bottom: 30px;
                padding: 15px;
                background: #e7f1ff;
                border-radius: 5px;
            }

            .bill-info p {
                margin: 5px 0;
            }

            table {
                width: 100%;
                border-collapse: collapse;
                margin-bottom: 20px;
            }

            th, td {
                padding: 12px;
                border: 1px solid #ddd;
                text-align: left;
            }

            th {
                background-color: #007bff;
                color: white;
                font-weight: bold;
            }

            tr:nth-child(even) {
                background-color: #f2f2f2;
            }

            tr:hover {
                background-color: #d1ecf1;
            }

            .total {
                text-align: right;
                font-size: 20px;
                font-weight: bold;
                margin-top: 20px;
                border-top: 2px solid #007bff;
                padding-top: 10px;
            }

        </style>
    </head>
    <body>
        <div class="container">
            <h1>Laptop Purchase Invoice</h1>
            <div class="bill-info">
                <p><strong>Customer Name:</strong> ${order.getName()}</p>
                <p><strong>Purchase Date:</strong> ${order.getOrderDateAsDateString()}</p>
            </div>

            <table>
                <thead>
                    <tr>
                        <th>Image</th>
                        <th>Product Name</th>
                        <th>Quantity</th>
                        <th>Voucher</th>
                        <th>Price (VND)</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${listOrderDetail}" var="odt">
                        <tr>
                            <td>
                                <c:forEach items="${listImages}" var="i">
                                    <div class="d-flex">
                                        <c:if test="${odt.getProductDetail().getId() == i.getProductDetail().getId()}">
                                            <img style="width: 50px" src="${pageContext.request.contextPath}/images/${i.getImage()}" alt="">
                                        </c:if>
                                    </div>
                                </c:forEach>
                            </td>
                            <td> ${odt.getProductDetail().getProduct().getName()} </td>
                            <td> ${odt.getQuantity()} </td>
                            <c:if test="${order.getVoucher() == null}">
                                <td>Null</td>
                            </c:if>
                            <c:if test="${order.getVoucher() != null}">
                                <td> ${order.getVoucher().getName()} </td>
                            </c:if>    

                            <td><fmt:formatNumber value="${odt.getUnitPrice()}" /> VND</td>
                        </tr>
                    </c:forEach>


                </tbody>
            </table>

            <div class="total">
                <c:if test="${order.getVoucher() == null}">
                    <p><strong>Total Amount Before:</strong> <fmt:formatNumber value="${order.getTotalAmountBefore()}" /></p>
                    <p><strong>Discount:</strong> 0%</p>
                </c:if>
                <c:if test="${order.getVoucher() != null}">
                    <p>
                        <strong style="display: inline;">Total Amount Before:</strong>
                        <span style="display: inline; text-decoration: line-through; color: #a19c9c;">
                            <fmt:formatNumber value="${order.getTotalAmountBefore()}" />
                        </span>
                    </p>
                    <p><strong>Discount:</strong> ${order.getVoucher().getDiscountPercent()}%</p>
                </c:if>
                <p><strong>Total Amount:</strong> <fmt:formatNumber value="${order.getTotalAmountAfter()}" /></p>
            </div>
        </div>
    </body>
</html>
