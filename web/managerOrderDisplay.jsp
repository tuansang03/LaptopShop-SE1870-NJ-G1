<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Manage Order</title>
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
        <%@include file="sidebar.jsp" %>

        <div class="col-md-10 content">
            <h2>Manage Order</h2>

            <!-- Form tìm kiếm danh mục -->
            <div style="display: flex; align-items: center">
                <form style="width: 100%;"  class="form-inline mb-3">
                    <input type="text" name="search" value="${search}" class="form-control mr-2" placeholder="Search Voucher">
                    <button type="submit" class="btn btn-primary">Search</button>
                </form>
            </div>

            <!-- Bảng hiển thị danh mục -->
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Address</th>
                        <th>Phone</th>
                        <th>Voucher</th>
                        <th>Total Amount After</th>
                        <th>PayMent Method</th>
                        <th>PayMent Status</th>
                        <th>VnPayID</th>
                        <th>Order Date</th>
                        <th>End Date</th>
                        <th>Order Status</th>
                        <th>Note</th>
                    </tr>
                </thead>
                <tbody>

                    <c:forEach items="${listOrder}" var="o">

                        <tr>
                            <td>
                                ${o.getId()}
                                <a href="viewOrderDetail?id=${o.getId()}"><i class="fas fa-eye"></i></a>
                            </td>
                            <td>${o.getName()}</td>
                            <td>${o.getAddress()}</td>
                            <td>${o.getPhone()}</td>
                            <td>
                                <c:if test="${o.getVoucher().getName()== null}">
                                    None
                                </c:if>
                                <c:if test="${o.getVoucher().getName()!= null}">
                                    ${o.getVoucher().getName()}
                                </c:if>    
                            </td>
                            <td>
                                <fmt:formatNumber value="${o.getTotalAmountAfter()}" type="number" />
                            </td>
                            <td>${o.getPaymentMethod()}</td>
                            <td>
                                <c:if test="${o.getPaymentStatus() == null}">
                                    None
                                </c:if>

                                <c:if test="${o.getPaymentStatus() != null}">
                                    <form action="changeStatusOrder" method="get">
                                        <select name="opPaymentStatus" onchange="this.form.submit()">
                                            <option ${o.getPaymentStatus().equals("pending") ? 'selected':''} value="pending">Pending</option>
                                            <option ${o.getPaymentStatus().equals("completed") ? 'selected':''} value="completed">Completed</option>
                                            <option ${o.getPaymentStatus().equals("fail") ? 'selected':''} value="fail">Fail</option>
                                        </select>
                                        <input type="hidden" value="${o.getId()}" name="oid"/>
                                    </form>
                                </c:if>    
                            </td>
                            <td>
                                <c:if test="${o.getVnPayTransactionId() == null}">
                                    None
                                </c:if>
                                <c:if test="${o.getVnPayTransactionId() != null}">
                                    ${o.getVnPayTransactionId()}
                                </c:if> 
                            </td>
                            <td>
                                ${o.getOrderDate()}
                            </td>
                            <td>
                                <c:if test="${o.getEndDate() == null}">
                                    None
                                </c:if>
                                <c:if test="${o.getEndDate() != null}">
                                    ${o.getEndDate()}
                                </c:if> 
                            </td>
                            <td>
                                <form action="changeStatusOrder" method="post">
                                    <select name="opOrderStatus" onchange="this.form.submit()">
                                        <option ${o.getOrderStatus().equals("wait") ? 'selected':''} value="wait">Wait</option>
                                        <option ${o.getOrderStatus().equals("accepted") ? 'selected':''} value="accepted">Accepted</option>
                                    </select>
                                    <input type="hidden" value="${o.getId()}" name="oid"/>
                                </form>
                            </td>
                            <td>
                                <c:if test="${o.getNote().isEmpty()}">
                                    None
                                </c:if>
                                <c:if test="${!o.getNote().isEmpty()}">
                                    ${o.getNote()}
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
