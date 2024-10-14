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
    <link rel="stylesheet" href="assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/font-awesome.css">
    <link rel="stylesheet" href="assets/css/animate.css">
    <link rel="stylesheet" href="assets/css/magnific-popup.css">
    <link rel="stylesheet" href="assets/css/meanmenu.css">
    <link rel="stylesheet" href="assets/css/swiper-bundle.min.css">
    <link rel="stylesheet" href="assets/css/nice-select.css">
    <link rel="stylesheet" href="assets/css/main.css">

    <%@include file="sidebar2.jsp" %>
</head>
<body>
<div class="col-md-10 content">
    <h2>Statistic</h2>

    <!-- Dropdown để chọn loại thống kê -->
    <div class="form-group">
        <label for="revenueType">Select Revenue Type:</label>
        <select id="revenueType" class="form-control" onchange="updateChartType()">
            <option value="brand">Calculated by Brand</option>
            <option value="month">Calculated by Month</option>
            <option value="top5Product">Top 5 Product</option> <!-- Thêm lựa chọn Top 5 Product -->
        </select>
    </div>

    <!-- Biểu đồ số sản phẩm bán được theo thương hiệu -->
    <h3>Product Count by Brand</h3>
    <canvas id="productChart" style="width:100%;max-width:600px"></canvas>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>
    <script>
        // Lấy dữ liệu từ đối tượng brandProductCounts truyền từ Servlet
        const productLabels = [];
        const productCounts = [];

        <c:forEach var="item" items="${brandProductCounts}">
            productLabels.push("${item.brandName}");
            productCounts.push(${item.productCount});
        </c:forEach>

        // Hàm tạo màu ngẫu nhiên
        function getRandomColor() {
            const letters = '0123456789ABCDEF';
            let color = '#';
            for (let i = 0; i < 6; i++) {
                color += letters[Math.floor(Math.random() * 16)];
            }
            return color;
        }

        const productBarColors = productCounts.map(() => getRandomColor());

        new Chart("productChart", {
            type: "bar",
            data: {
                labels: productLabels,
                datasets: [{
                    backgroundColor: productBarColors,
                    data: productCounts
                }]
            },
            options: {
                legend: {display: false},
                title: {
                    display: true,
                    text: "Product Count by Brand"
                }
            }
        });
    </script>

    <!-- Biểu đồ doanh thu theo thương hiệu -->
    <h3 class="mt-5">Revenue by Brand</h3>
    <canvas id="revenueChart" style="width:100%;max-width:600px"></canvas>

    <script>
        // Lấy dữ liệu từ đối tượng brandProductPrice truyền từ Servlet
        const revenueLabels = [];
        const revenueValues = [];

        <c:forEach var="item" items="${brandProductPrice}">
            revenueLabels.push("${item.brandName}");
            revenueValues.push(${item.price});
        </c:forEach>

        const revenueBarColors = revenueValues.map(() => getRandomColor());

        new Chart("revenueChart", {
            type: "bar",
            data: {
                labels: revenueLabels,
                datasets: [{
                    backgroundColor: revenueBarColors,
                    data: revenueValues
                }]
            },
            options: {
                legend: {display: false},
                title: {
                    display: true,
                    text: "Revenue by Brand"
                }
            }
        });
    </script>

    <!-- Biểu đồ doanh thu theo tháng (chỉ hiển thị khi chọn "Calculated by Month") -->
    <h3 class="mt-5" id="monthlyRevenueTitle" style="display:none;">Revenue by Month</h3>
    <canvas id="monthlyRevenueChart" style="width:100%;max-width:600px; display:none;"></canvas>

    <script>
        // Lấy dữ liệu từ đối tượng monthlyRevenueCounts truyền từ Servlet
        const monthLabels = [];
        const monthValues = [];

        <c:forEach var="item" items="${monthlyRevenue}">
            monthLabels.push("${item.monthName}");
            monthValues.push(${item.revenue});
        </c:forEach>

        const monthBarColors = monthValues.map(() => getRandomColor());

        new Chart("monthlyRevenueChart", {
            type: "bar",
            data: {
                labels: monthLabels,
                datasets: [{
                    backgroundColor: monthBarColors,
                    data: monthValues
                }]
            },
            options: {
                legend: {display: false},
                title: {
                    display: true,
                    text: "Revenue by Month"
                }
            }
        });

        // Hàm để chuyển đổi giữa các biểu đồ
        function updateChartType() {
            const selectedType = document.getElementById("revenueType").value;

            if (selectedType === "brand") {
                document.getElementById("productChart").style.display = "block";
                document.getElementById("revenueChart").style.display = "block";
                document.getElementById("monthlyRevenueChart").style.display = "none";
                document.getElementById("monthlyRevenueTitle").style.display = "none"; // Ẩn tiêu đề
                window.location.href = 'SaleStatisticController2?service=listall';  // Chuyển hướng đến servlet xử lý brand
            } else if (selectedType === "month") {
                document.getElementById("productChart").style.display = "none";
                document.getElementById("revenueChart").style.display = "none";
                document.getElementById("monthlyRevenueChart").style.display = "block";
                document.getElementById("monthlyRevenueTitle").style.display = "block"; // Hiển thị tiêu đề
                window.location.href = 'SaleStatisticController2?service=monthlyRevenue';  // Chuyển hướng đến servlet xử lý tháng
            } else if (selectedType === "top5Product") {
                document.getElementById("productChart").style.display = "none";
                document.getElementById("revenueChart").style.display = "none";
                document.getElementById("monthlyRevenueChart").style.display = "none";
                document.getElementById("monthlyRevenueTitle").style.display = "none"; // Ẩn tiêu đề
                window.location.href = 'SaleStatisticController2?service=top5Product';  // Chuyển hướng đến servlet xử lý Top 5 Product
            }
        }
    </script>

    <style>
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
</div>
</body>
</html>
