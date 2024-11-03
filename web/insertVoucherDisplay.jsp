<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Add Post</title>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link rel="shortcut icon" href="assets/img/logo/favicon.svg">
        <link rel="stylesheet" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/font-awesome.css">
        <link rel="stylesheet" href="assets/css/animate.css">
        <link rel="stylesheet" href="assets/css/magnific-popup.css">
        <link rel="stylesheet" href="assets/css/meanmenu.css">
        <link rel="stylesheet" href="assets/css/swiper-bundle.min.css">
        <link rel="stylesheet" href="assets/css/nice-select.css">
        <link rel="stylesheet" href="assets/css/main.css">
        <style>
            .flex {
                display: flex;
                justify-content: space-between;

            }

            .left {
                width: 47%;
            }
            
            .div div {
                padding-bottom: 20px;
            } 
            
            .div div label {
                font-weight: 500;
            }
            
            .right {
                width: 47%;
            }

            .div input {


            }
        </style>
    </head>
    <body>
        <c:if test="${sessionScope.admin!=null}">
        <%@include file="sidebar.jsp" %>


        <div class="col-md-10 content">
            <h2>Add Voucher</h2>
            <form action="addVoucher" method="">
                <table class="table table-bordered" style="width: 80%;">
                    <thead>
                        <tr>
                            <th>Name</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                <div class="flex">
                                    <div class="left div div div div label">
                                        <div class="div input">
                                            <label>Code</label>
                                            <input type="text" name="code" value="${code}" placeholder="Enter code" required class="form-control">
                                        </div>

                                        <div>
                                            <label>Name</label>
                                            <input type="text" name="name" value="${name}" placeholder="Enter name voucher" required class="form-control">
                                        </div>

                                        <div>
                                            <label>DiscountPercent</label>
                                            <input type="number" name="discount" value="${discount}" placeholder="Enter discount percent" required class="form-control">
                                        </div>

                                        <div>
                                            <label>Quantity</label>
                                            <input type="number" name="quantity" value="${quantity}" placeholder="Enter quantity voucher" required class="form-control">
                                        </div>

                                    </div>
                                    <div class="right div div div div label">
                                        <div>
                                            <div>
                                                <label>StartDate</label>
                                                <input type="date" name="startdate" value="${startdate}" placeholder="Enter start date" required class="form-control">
                                            </div>

                                            <div>
                                                <label>EndDate</label>
                                                <input type="date" name="enddate" value="${enddate}" placeholder="Enter end date" required class="form-control">
                                            </div>

                                            <div>
                                                <label>MinValue</label>
                                                <input type="number" name="minvalue" value="${minvalue}" placeholder="Enter min value" required class="form-control">
                                            </div>

                                            <div>
                                                <p></p>
                                                <p style="color: red; font-size: 18px">${error}</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </tbody>
                </table>


                <button type="submit" class="btn btn-primary">Add Voucher</button>

                <c:if test="${not empty mess}">
                    <span class="text-success">${mess}</span>
                </c:if>
            </form>

        </div>
        <script src='./tinymce/tinymce.min.js'></script>
        <script src='./js/tinyMceConfig.js'></script>
        </c:if>
        <c:if test="${sessionScope.sale!=null || sessionScope.user!=null || (sessionScope.user==null && 
                      sessionScope.sale==null && sessionScope.admin==null)}">
            <%@include file="notallowpage.jsp" %>
        </c:if>
    </body>
</html>
