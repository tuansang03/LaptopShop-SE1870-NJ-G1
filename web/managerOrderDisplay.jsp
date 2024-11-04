<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

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

        <script>
            function doDeleteOrder() {
                return confirm("Do you want to delete this order?");
            }
        </script>

        <style>
            /* Bố cục cho container chính */
            div[style*="display: flex"] {
                justify-content: space-evenly;
                flex-wrap: wrap; /* Để hàng nút tự xuống dòng nếu cần */
                padding-bottom: 20px;
            }

            /* Phong cách cho nút Button */
            Button {
                background: none;
                border: none;
                cursor: pointer;
                padding: 0;
                outline: none;
                transition: transform 0.2s ease; /* Hiệu ứng phóng to khi di chuột */
            }

            Button:hover {
                transform: scale(1.05); /* Phóng to nút khi di chuột */
            }

            /* Thiết lập cho phần tử background bên trong Button */
            .background {
                background-color: #f0f5f9;
                padding: 20px 15px 20px 15px;
                border-radius: 12px;
                box-shadow: 0 4px 10px rgba(0, 0, 0, 0.15); /* Đổ bóng để tạo chiều sâu */
                width: 100%;
                text-align: center;
                transition: background-color 0.3s ease; /* Hiệu ứng thay đổi màu nền */
            }

            .backgroundSelect {
                background: linear-gradient(135deg, #bd9aff, #f3ebe8);
                padding: 20px 15px 20px 15px;
                border-radius: 12px;
                box-shadow: 0 4px 10px rgba(0, 0, 0, 0.15); /* Đổ bóng để tạo chiều sâu */
                width: 100%;
                text-align: center;
                transition: background-color 0.3s ease; /* Hiệu ứng thay đổi màu nền */
            }

            Button:hover .background {
                background-color: #e2e8f0; /* Đổi màu nền khi di chuột */
            }

            /* Phong cách cho tiêu đề */
            .background h5 {
                font-size: 1.2em;
                color: #2d3748; /* Màu sắc nhã nhặn */
                font-weight: 600;
                margin-bottom: 10px;
            }

            /* Phong cách cho các văn bản mô tả */
            .background h6 {
                font-size: 0.9em;
                color: #718096;
                margin: 6px 0;
                font-weight: 500;
            }



            body {
                font-family: Arial, sans-serif;
                background-color: #f8f9fa;
                margin: 0;
                padding: 20px;
            }

            .order-table {
                width: 100%;
                border-collapse: collapse;
                margin: 20px 0;
                box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
                border-radius: 8px;
                overflow: hidden;
            }

            .order-table th {
                background-color: #007bff;
                color: #ffffff;
                padding: 12px;
                text-align: left;
            }

            .order-table td {
                background-color: #ffffff;
                padding: 12px;
                text-align: left;
                border-bottom: 1px solid #dddddd;
            }

            .order-table tr:hover td {
                background-color: #f1f1f1;
            }

            .order-table .fas {
                color: #007bff;
                margin-left: 10px;
                cursor: pointer;
            }

            .order-table .action-button {
                border: none;
                border-radius: 4px;
                padding: 8px 12px;
                margin: 5px;
                cursor: pointer;
                transition: background-color 0.3s;
                font-weight: bold;
                color: white;
            }

            .order-table .accepted-button {
                background-color: #28a745; /* Màu xanh lá cho thành công */
            }

            .order-table .accepted-button:hover {
                background-color: #218838; /* Màu xanh lá đậm khi hover */
            }

            .order-table .rejected-button {
                background-color: #dc3545; /* Màu đỏ cho thất bại */
            }

            .order-table .rejected-button:hover {
                background-color: #c82333; /* Màu đỏ đậm khi hover */
            }

            .order-table .status-success {
                color: #ffffff; /* Chữ trắng cho nền xanh lá */
                background-color: #28a745; /* Nền xanh lá cho trạng thái thành công */
                border-radius: 4px;
                padding: 5px 10px;
                text-align: center;
                font-weight: bold;
            }

            .order-table .status-fail {
                color: #ffffff; /* Chữ trắng cho nền đỏ */
                background-color: #dc3545; /* Nền đỏ cho trạng thái thất bại */
                border-radius: 4px;
                padding: 5px 10px;
                text-align: center;
                font-weight: bold;
            }

            .order-table .status-none {
                color: #0d6efd; /* Màu xanh dương đậm */
                background-color: #d6e4f0; /* Xanh dương nhạt cho nền */
                border-radius: 4px;
                padding: 5px 10px;
                text-align: center;
                font-weight: bold;
            }

            /* Responsive Styles */
            @media (max-width: 768px) {
                .order-table {
                    font-size: 14px;
                }

                .order-table th, .order-table td {
                    padding: 8px;
                }

                .order-table .action-button {
                    padding: 6px 10px;
                    font-size: 14px;
                }
            }

            input[type="date"] {
                width: 150px; /* Đặt chiều rộng cho input */
                padding: 8px; /* Khoảng cách trong */
                border-radius: 5px; /* Bo góc cho input */
                border: 1px solid #ccc; /* Đặt viền cho input */
                font-size: 14px;
            }

            /* Căn chỉnh khoảng cách giữa các input */
            .form-control {
                margin-right: 10px; /* Khoảng cách giữa các input */
            }

            /* Thêm style cho label nếu muốn */
            label {
                margin:0 5px 0 10px;
            }

        </style>

    </head>
    <body style="padding: 0">
        <%@include file="sidebar2.jsp" %>

        <div class="col-md-10 content">
            <h2>Manage Order</h2>
            <form action="selectOrderbyStatus">
                <div style="display: flex">

                    <button name="action" value="wait" style="outline: none">
                        <div class="${action == 'wait' ? 'backgroundSelect': 'background'}">
                            <h5>Processing</h5>
                            <h6 style="font-size: 11px">Total Order:${totalOrderWait} </h6>
                            <h6 style="font-size: 11px">Total Money:
                                <fmt:formatNumber value="${totalAmountWait}" pattern="#,###" />
                            </h6>
                        </div>
                    </button>
                    <button name="action" value="rejected" style="outline: none">
                        <div class="${action == 'rejected' ? 'backgroundSelect': 'background'}">
                            <h5>Rejected</h5>
                            <h6 style="font-size: 11px">Total Order:${totalOrderRejected} </h6>
                            <h6 style="font-size: 11px">Total Money:
                                <fmt:formatNumber value="${totalAmountRejected}" pattern="#,###" />
                            </h6>
                        </div>
                    </button>
                    <button name="action" value="accepted" style="outline: none">
                        <div class="${action == 'accepted' ? 'backgroundSelect': 'background'}">
                            <h5>Accepted</h5>
                            <h6 style="font-size: 11px">Total Order:${totalOrderAccepted} </h6>
                            <h6 style="font-size: 11px">Total Money:
                                <fmt:formatNumber value="${totalAmountAccepted}" pattern="#,###" />
                            </h6>
                        </div>
                    </button>
                    <button name="action" value="intransit" style="outline: none">
                        <div class="${action == 'intransit' ? 'backgroundSelect': 'background'}">
                            <h5>In Transit</h5>
                            <h6 style="font-size: 11px">Total Order:${totalOrderIntransit} </h6>
                            <h6 style="font-size: 11px">Total Money:
                                <fmt:formatNumber value="${totalAmountIntransit}" pattern="#,###" />
                            </h6>
                        </div>
                    </Button>
                    <button name="action" value="failed" style="outline: none">
                        <div class="${action == 'failed' ? 'backgroundSelect': 'background'}">
                            <h5>Shipment Failed</h5>
                            <h6 style="font-size: 11px">Total Order:${totalOrderFailed} </h6>
                            <h6 style="font-size: 11px">Total Money:
                                <fmt:formatNumber value="${totalAmountFailed}" pattern="#,###" />
                            </h6>
                        </div>
                    </button>
                    <button name="action" value="done" style="outline: none">
                        <div class="${action == 'done' ? 'backgroundSelect': 'background'}">
                            <h5>Done</h5>
                            <h6 style="font-size: 11px">Total Order:${totalOrderDone} </h6>
                            <h6 style="font-size: 11px">Total Money:
                                <fmt:formatNumber value="${totalAmountDone}" pattern="#,###" />
                            </h6>
                        </div>
                    </button>
                </div>
            </form>

            <!-- Form tìm kiếm danh mục -->
            <div style="display: flex; align-items: center">
                <form style="width: 100%;" action="searchOrder" class="form-inline mb-3">
                    <input type="text" name="searchOrder" value="${search}" class="form-control mr-2"
                           placeholder="Search Customer">
                    <button type="submit" class="btn btn-primary">Search</button>

                    <label for="startDate">Start Date:</label>
                    <input type="date" name="startDate" value="${startDate}" class="form-control mr-2">
                    <input type="hidden" name="action" value="${action}" />
                    <label for="endDate">End Date:</label>
                    <input type="date" name="endDate" value="${endDate}" class="form-control mr-2">

                </form>
            </div>
            <p style="color: red;" class="text-center">${error}</p>


            <table class="order-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Customer</th>
                        <th>Voucher</th>
                        <th>Total<br>Amount</th>
                        <th>Payment<br>Method</th>
                        <th>Payment<br>Status</th>
                        <th>VNPayID</th>
                        <th>Order Date</th>
                        <!--                        <th>Order Time</th>-->

                        <c:if test="${action == 'rejected'}">
                            <th>Reject Date</th>
                            </c:if>

                        <c:if test="${action == 'accepted'}">
                            <th>Accepted Date</th>
                            <th>Tracking Code</th>
                            </c:if>    

                        <c:if test="${action == 'intransit'}">
                            <th>InTransit Date</th>
                            </c:if>

                        <c:if test="${action == 'failed'}">
                            <th>Shipment Date</th>
                            </c:if>
                            <c:if test="${action == 'done'}">
                            <th>Date End</th>
                            </c:if>

                        <th>Note</th>
                            <c:if test="${action != 'done'}">
                            <th>Actions</th>
                            </c:if>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${listOrder}" var="o">
                        <tr>
                            <td>
                                ${o.getId()}
                                <a href="viewOrderDetail?id=${o.getId()}"><i class="fas fa-eye"></i></a>
                            </td>
                            <td>
                                <h6>${o.getName()}</h6>
                                <h6>${o.getAddress()}</h6>
                                <h6>${o.getPhone()}</h6>
                            </td>
                            <td>
                                <c:if test="${o.getVoucher().getName() == null}">None</c:if>
                                <c:if test="${o.getVoucher().getName() != null}">${o.getVoucher().getName()}</c:if>
                                </td>
                                <td>
                                <fmt:formatNumber value="${o.getTotalAmountAfter()}" type="number" />
                            </td>
                            <td>
                                ${o.getPaymentMethod()}
                            </td>
                            <td>
                                <c:if test="${o.getPaymentStatus() == null}">
                                    <span class="status-none">None</span>
                                </c:if>
                                <c:if test="${o.getPaymentStatus() != null}">
                                    <span
                                        class="${o.getPaymentStatus() == 'Success' ? 'status-success' : 'status-fail'}">
                                        ${o.getPaymentStatus()}
                                    </span>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${o.getVnPayTransactionId() == null}">None</c:if>
                                <c:if test="${o.getVnPayTransactionId() != null}">
                                    ${o.getVnPayTransactionId()}
                                </c:if>
                            </td>
                            <td>
                                ${o.getOrderDateAsDateTimeString()}
                            </td>
                            <!--                            <td>
                            ${o.getOrderDateAsTimeString()}
                        </td>-->

                            <c:if test="${action == 'rejected'}">
                                <td>${o.getRejectDateAsDateTimeString()}</td>
                            </c:if>

                            <c:if test="${action == 'accepted'}">
                                <td>
                                    ${o.getAcceptedDateAsDateTimeString()}
                                </td>
                                <td>
                                    <input style="width: 60px" type="text" id="codeInput" required name="code"/>
                                </td>
                            </c:if>

                            <c:if test="${action == 'intransit'}">
                                <td>
                                    ${o.getIntransitDateAsDateTimeString()}
                                </td>
                            </c:if> 

                            <c:if test="${action == 'failed'}">
                                <td>
                                    ${o.getShipmentFailedDateAsDateTimeString()}
                                </td>
                            </c:if>

                            <c:if test="${action == 'done'}">
                                <td>
                                    ${o.getDoneDateAsDateTimeString()}
                                </td>
                            </c:if>

                            <td>
                                <c:if test="${o.getNote().isEmpty() || o.getNote() == null}">None</c:if>
                                <c:if test="${!o.getNote().isEmpty()}">${o.getNote()}</c:if>
                                </td>

                            <c:if test="${action == 'wait'}">
                        <form action="changeStatusOrder" method="get">
                            <td>
                                <button name="action" value="accepted"
                                        class="action-button accepted-button">Accepted
                                </button>
                                <button name="action" value="rejected"
                                        class="action-button rejected-button">Rejected
                                </button>
                            </td>
                            <input name="oid" type="hidden" value="${o.getId()}"/>
                        </form>
                    </c:if>

                    <c:if test="${action == 'rejected'}">
                        <form action="changeStatusOrderDoneAndDelete" method="get" >
                            <td>
                                <button onclick="if (doDeleteOrder()) {
                                            this.form.submit();
                                        }" name="op" value="rejected"
                                        class="action-button rejected-button">Delete
                                </button>
                            </td>
                            <input name="oid" type="hidden" value="${o.getId()}"/>
                        </form>
                    </c:if>

                    <c:if test="${action == 'accepted'}">
                        <form action="changeStatusOrder" method="post" onsubmit="copyCodeToForm()">
                            <td>
                                <button name="action" value="intransit"
                                        class="action-button accepted-button">In Transit
                                </button>
                            </td>
                            <input name="oid" type="hidden" value="${o.getId()}" />
                            <input type="hidden" id="hiddenCodeInput" name="code"/>
                        </form>
                    </c:if>
                    <script>
                        function copyCodeToForm() {
                            var codeValue = document.getElementById("codeInput").value;
                            document.getElementById("hiddenCodeInput").value = codeValue;
                        }
                    </script>
                    <c:if test="${action == 'intransit'}">
                        <form action="changeStatusOrderDoneAndDelete" method="post">
                            <td>
                                <button style="font-size: 10px" name="action" value="done"
                                        class="action-button accepted-button">Done
                                </button>
                                <button style="font-size: 10px" name="action" value="failed"
                                        class="action-button rejected-button">Shipment Failed
                                </button>
                            </td>
                            <input name="oid" type="hidden" value="${o.getId()}" />
                        </form>
                    </c:if>

                    <c:if test="${action == 'failed'}">
                        <form action="changeStatusOrderDoneAndDelete" method="get" >
                            <td>
                                <button onclick="if (doDeleteOrder()) {
                                            this.form.submit();
                                        }" name="op" value="failed"
                                        class="action-button rejected-button">Delete
                                </button>
                            </td>
                            <input name="oid" type="hidden" value="${o.getId()}" />
                        </form>
                    </c:if>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
