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

    <%@include file="sidebar2.jsp" %>
    <style>
        .content {
            padding: 20px;
            background-color: #f9f9f9;
        }
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
        .error-message {
            color: red;
            display: none; /* Ẩn thông báo lỗi mặc định */
        }
    </style>
</head>
<body>
    <div class="col-md-10 content">
        <h2>Statistic</h2>

        <div class="form-group">
            <label for="revenueType">Select Revenue Type:</label>
            <select id="revenueType" class="form-control" onchange="updateChartType()">
                <option value="brand" <c:if test="${action2 == null || action2 == 'listall'}">selected</c:if>>Calculated by Brand</option>
                <option value="month" <c:if test="${action2 == 'monthlyRevenue'}">selected</c:if>>Calculated by Month</option>
                <option value="top5Product" <c:if test="${action2 != null}">selected</c:if>>Top 5 Products</option>
                <c:if test="${action == 'actionz'}">
                    <option value="workProductivity" selected>Work Productivity</option>
                </c:if>
                <c:if test="${action != 'actionz'}">
                    <option value="workProductivity">Work Productivity</option>
                </c:if>
            </select>
        </div>

        <!-- Thêm ô nhập năm và nút submit -->
        <form action="SaleStatisticController2" method="GET" onsubmit="return validateYear()">
            <div class="form-group">
                <label for="searchYear">Enter Year:</label>
                <input type="number"  value="${year}" id="searchYear" name="year" class="form-control" required>
                <div id="yearError" class="error-message">Please enter a year between 1900 and the current year.</div> <!-- Phần tử hiển thị thông báo lỗi -->
            </div>
            <input type="hidden" name="service" value="myWorkDone">
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>

        <!-- Biểu đồ -->
        <canvas id="myChart" style="width:100%;max-width:600px"></canvas>
    </div>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>
    <script>
        const xValues = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
                const yValues = [];
                
                 <c:forEach var="item" items="${list}">
                    yValues.push(${item});
                </c:forEach>

        new Chart("myChart", {
            type: "line",
            data: {
                labels: xValues,
                datasets: [{
                    fill: false,
                    lineTension: 0,
                    backgroundColor: "rgba(0,0,255,1.0)",
                    borderColor: "rgba(0,0,255,0.1)",
                    data: yValues
                }]
            },
            options: {
                legend: {display: false},
                scales: {
                    yAxes: [{ticks: {min: 6, max: 16}}],
                }
            }
        });

        function updateChartType() {
            const selectedType = document.getElementById("revenueType").value;
            if (selectedType === "brand") {
                window.location.href = 'SaleStatisticController2?service=listall';
            } else if (selectedType === "month") {
                window.location.href = 'SaleStatisticController2?service=monthlyRevenue';
            } else if (selectedType === "top5Product") {
                window.location.href = 'SaleStatisticController2?service=top5Product';
            } else if (selectedType === "workProductivity") {
                window.location.href = 'SaleStatisticController2?service=myWork';
            }
        }

        function validateYear() {
            const yearInput = document.getElementById("searchYear");
            const currentYear = new Date().getFullYear();
            const enteredYear = parseInt(yearInput.value);
            const yearError = document.getElementById("yearError");

            if (enteredYear < 1900 || enteredYear > currentYear) {
                yearError.style.display = "block";
                return false;
            } else {
                yearError.style.display = "none";
            }
            return true;
        }
    </script>
</body>
</html>
