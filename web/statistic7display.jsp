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
        <link rel="stylesheet" href="assets/css/font-awesome.css">
        <link rel="stylesheet" href="assets/css/animate.css">
        <link rel="stylesheet" href="assets/css/magnific-popup.css">
        <link rel="stylesheet" href="assets/css/meanmenu.css">
        <link rel="stylesheet" href="assets/css/swiper-bundle.min.css">
        <link rel="stylesheet" href="assets/css/nice-select.css">
        <link rel="stylesheet" href="assets/css/main.css">
        <%@include file="sidebar.jsp" %>
        <style>
            .content {
                padding: 20px;
                background-color: #f9f9f9;
            }
        </style>
    </head>
    <body>
        <div class="col-md-10 content">
            <h2>Statistic</h2>

            <!-- Form điều hướng -->
            <form action="StatisticController" method="GET" onsubmit="return validateInputs()">
                <input type="hidden" name="service" value="top3Staff">
                <div class="form-group">
                    <label for="revenueType">Select Revenue Type:</label>
                    <select id="revenueType" class="form-control" onchange="updateChartType()">
                        <option value="brand" <c:if test="${action2 == null || action2 == 'listall'}">selected</c:if>>Calculated by Brand</option>
                        <option value="month" <c:if test="${action2 == 'monthlyRevenue'}">selected</c:if>>Calculated by Month</option>
                        <option value="top5Product" <c:if test="${action2 == 'top5Product'}">selected</c:if>>Top 5 Products</option>
                        <option value="category" <c:if test="${action == 'actionz'}">selected</c:if>>Top 3 Staff</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="monthInput">Enter Month (1-12):</label>
                        <input type="number" id="monthInput" name="month" class="form-control" min="1" max="12" placeholder="Enter month (1-12)"
                               value="${month}" required>
                </div>

                <div class="form-group">
                    <label for="yearInput">Enter Year (1900 - Present):</label>
                    <input type="number" id="yearInput" name="year" class="form-control" min="1900" max="<%= java.util.Calendar.getInstance().get(java.util.Calendar.YEAR) %>"
                           placeholder="Enter year (1900 - present)" value="${year}" required>
                </div>

                <button type="submit" class="btn btn-primary">Submit</button>
            </form>

            <!-- Biểu đồ -->
            <canvas id="myChart" style="width:100%;max-width:600px"></canvas>

            <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>

            <script type="text/javascript">
                        // Chuyển dữ liệu từ danh sách UserSales vào mảng JavaScript
                        const userNames = [];
                        const totalOrders = [];

                <c:forEach var="user" items="${list}">
                        userNames.push("${user.userName}");
                        totalOrders.push(${user.totalOrders});
                </c:forEach>;

                        // Tạo biểu đồ với dữ liệu từ danh sách
                        new Chart("myChart", {
                            type: "bar", // Biểu đồ cột
                            data: {
                                labels: userNames, // Tên của nhân viên
                                datasets: [{
                                        label: 'Total Orders',
                                        backgroundColor: ["#3e95cd", "#8e5ea2", "#3cba9f"],
                                        data: totalOrders // Số lượng đơn hàng của nhân viên
                                    }]
                            },
                            options: {
                                title: {
                                    display: true,
                                    text: "Top 3 Staff with Most Orders"
                                },
                                legend: {
                                    display: false
                                }
                            }
                        });

                        // Điều hướng khi chọn loại thống kê
                        function updateChartType() {
                            const selectedType = document.getElementById("revenueType").value;
                            if (selectedType === "brand") {
                                window.location.href = 'StatisticController?service=listall';
                            } else if (selectedType === "month") {
                                window.location.href = 'StatisticController?service=monthlyRevenue';
                            } else if (selectedType === "top5Product") {
                                window.location.href = 'StatisticController?service=top5Product';
                            } else if (selectedType === "category") {
                                window.location.href = 'StatisticController?service=top5Staff';
                            }
                        }

                        // Validation for month and year inputs on submit
                        function validateInputs() {
                            const month = parseInt(document.getElementById("monthInput").value);
                            const year = parseInt(document.getElementById("yearInput").value);
                            const currentYear = new Date().getFullYear();

                            if (isNaN(month) || month < 1 || month > 12) {
                                alert("Please enter a valid month between 1 and 12.");
                                return false;
                            }

                            if (isNaN(year) || year < 1900 || year > currentYear) {
                                alert("Please enter a valid year between 1900 and " + currentYear + ".");
                                return false;
                            }

                            return true;
                        }
            </script>
        </div>
    </body>
</html>
