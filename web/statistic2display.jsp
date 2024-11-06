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

        <%@include file="sidebar.jsp" %>
    </head>
    <body>
        <div class="col-md-10 content">
            <h2>Statistic</h2>

            <!-- Dropdown để chọn loại thống kê -->
            <div class="form-group">
                <label for="revenueType">Select Revenue Type:</label>
                <select id="revenueType" class="form-control" onchange="updateChartType()">
                    <option value="brand" <c:if test="${action == null}">selected</c:if>>Calculated by Brand</option>
                    <option value="month" <c:if test="${action != null}">selected</c:if>>Calculated by Month</option>
                    <option value="top5Product">Top 5 Products</option> <!-- Thêm tùy chọn Top 5 Products -->
                    <option value="top5Staff">Top 3 Staff</option> <!-- Thêm tùy chọn Top 5 Staff -->
                </select>
            </div>

            <!-- Form nhập năm, chỉ hiện khi chọn "Calculated by Month" -->
            <div id="yearInput" class="form-group" style="display:none;">
                <label for="year">Enter Year:</label>
                <input type="number" id="year" class="form-control" placeholder="Enter year" 
                       value="<c:out value='${year != null ? year : java.time.Year.now().getValue()}'/>"
                min="1900" max="<%= java.time.Year.now().getValue() %>" required>
                <div id="yearError" style="color: red; display: none;">Please enter a valid year (1900 - <%= java.time.Year.now().getValue() %>).</div>
                <button class="btn btn-primary mt-2" onclick="submitYear()">Submit</button>
            </div>

            <script>
                function submitYear() {
                    const yearInput = document.getElementById("year").value;
                    const currentYear = new Date().getFullYear();
                    const yearError = document.getElementById("yearError");

                    // Kiểm tra nếu giá trị năm là không hợp lệ hoặc không phải là số
                    if (!yearInput || yearInput < 1900 || yearInput > currentYear || yearInput.length !== 4) {
                        yearError.style.display = "block";  // Hiển thị lỗi nếu năm không hợp lệ
                    } else {
                        yearError.style.display = "none";   // Ẩn lỗi nếu năm hợp lệ
                        window.location.href = 'StatisticController?service=listByTime&year=' + yearInput;  // Chuyển đến Servlet
                    }
                }
            </script>

            <!-- Biểu đồ doanh thu theo tháng -->
            <h3 class="mt-5">Revenue by Month</h3>
            <canvas id="monthlyRevenueChart" style="width:100%;max-width:600px"></canvas>

            <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>
            <script>
                // Lấy dữ liệu từ đối tượng list truyền từ Servlet
                const monthLabels = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
                const revenueValues = [];

                <c:forEach var="item" items="${list}">
                revenueValues.push(${item.revenue});
                </c:forEach>
                const revenueBarColors = revenueValues.map(() => getRandomColor());

                new Chart("monthlyRevenueChart", {
                    type: "bar",
                    data: {
                        labels: monthLabels,
                        datasets: [{
                                backgroundColor: revenueBarColors,
                                data: revenueValues
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

                // Hàm tạo màu ngẫu nhiên
                function getRandomColor() {
                    const letters = '0123456789ABCDEF';
                    let color = '#';
                    for (let i = 0; i < 6; i++) {
                        color += letters[Math.floor(Math.random() * 16)];
                    }
                    return color;
                }

                // Script chuyển đổi giữa biểu đồ thương hiệu và tháng
                function updateChartType() {
                    const selectedType = document.getElementById("revenueType").value;
                    if (selectedType === "brand") {
                        document.getElementById("yearInput").style.display = "none";  // Ẩn form nhập năm
                        window.location.href = 'StatisticController?service=listall';  // Chuyển hướng đến servlet xử lý brand
                    } else if (selectedType === "month") {
                        document.getElementById("yearInput").style.display = "block";  // Hiển thị form nhập năm
                    } else if (selectedType === "top5Product") {
                        window.location.href = 'StatisticController?service=top5Product';  // Chuyển hướng đến servlet xử lý top 5 sản phẩm
                    } else if (selectedType === "myWork") { // Thêm xử lý cho tùy chọn Work Productivity
                        window.location.href = 'SaleStatisticControlle2?service=myWork';  // Chuyển hướng đến servlet xử lý Work Productivity
                    }
                }

                document.addEventListener("DOMContentLoaded", updateChartType);
            </script>

            <!-- Biểu đồ doanh thu theo sản phẩm thứ hai -->
            <h3 class="mt-5">Quantity by Month</h3>
            <canvas id="myChart" style="width:100%;max-width:600px"></canvas>

            <script>
                const xValues = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
                const yValues = [];

                <c:forEach var="item" items="${list}">
                yValues.push(${item.quantity});
                </c:forEach>

                // Tạo màu ngẫu nhiên cho mỗi cột
                const barColors = yValues.map(() => getRandomColor());

                new Chart("myChart", {
                    type: "bar",
                    data: {
                        labels: xValues,
                        datasets: [{
                                backgroundColor: barColors,
                                data: yValues
                            }]
                    },
                    options: {
                        legend: {display: false},
                        title: {
                            display: true,
                            text: "Quantity by Month"
                        }
                    }
                });
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
