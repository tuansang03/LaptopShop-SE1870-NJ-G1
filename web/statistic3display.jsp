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
        .chart-wrapper {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 400px;
        }
        #myChart {
            width: 100%;
            max-width: 600px;
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
                <option value="category" <c:if test="${action2 == 'categoryReport'}">selected</c:if>>Top 3 Staff</option>  <!-- Thêm lựa chọn mới -->
            </select>
        </div>
            
                        <% int defaultYear = (Integer) request.getAttribute("year"); %>
    <div class="form-group mt-4">
    <form action="StatisticController" method="get">
        <label for="yearInput">Enter Year:</label>
        <input type="number" value="${year}" id="yearInput" name="year" class="form-control" placeholder="Year" required>
        <input type="hidden" name="service" value="list" >
        <button type="submit" class="btn btn-primary mt-2">Submit</button>
    </form>
</div>

        <div class="chart-wrapper">
            <canvas id="myChart"></canvas>
        </div>
    </div>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>
    <script type="text/javascript">
        const xValues = [];
        const yValues = [];
        const barColors = ["#b91d47", "#00aba9", "#2b5797", "#e8c3b9", "#1e7145"];

        <c:forEach var="item" items="${list}">
            xValues.push("${item.productName}");
            yValues.push(${item.totalSold});
        </c:forEach>

        new Chart("myChart", {
            type: "doughnut",
            data: {
                labels: xValues,
                datasets: [{
                    data: yValues,
                    backgroundColor: barColors
                }]
            },
            options: {
                title: {
                    display: true,
                    text: "Top 5 best selling products"
                }
            }
        });

        function updateChartType() {
            const selectedType = document.getElementById("revenueType").value;
            if (selectedType === "brand") {
                window.location.href = 'StatisticController?service=listall';
            } else if (selectedType === "month") {
                window.location.href = 'StatisticController?service=monthlyRevenue';
            } else if (selectedType === "top5Product") {
                window.location.href = 'StatisticController?service=top5Product';
            } else if (selectedType === "category") {  // Xử lý lựa chọn mới
                window.location.href = 'StatisticController?service=top5Staff';
            }
        }
    </script>
</body>
</html>
