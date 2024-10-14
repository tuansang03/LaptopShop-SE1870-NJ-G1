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
        <%@include file="sidebar.jsp" %>


        <div class="col-md-10 content">
            <h2>Edit Voucher</h2>
            <form action="editVoucher" method="post">
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
                                            <label>ID</label>
                                            <input value="${voucher.getId()}${id}" type="text" name="id" readonly placeholder="Enter code" class="form-control">
                                        </div>
                                        
                                        <div class="div input">
                                            <label>Code</label>
                                            <input value="${voucher.getCode()}${code}" type="text" name="code" placeholder="Enter code" required class="form-control">
                                        </div>
                                        
                                        <div>
                                            <label>Name</label>
                                            <input value="${voucher.getName()}${name}" type="text" name="name" placeholder="Enter name voucher" required class="form-control">
                                        </div>

                                        <div>
                                            <label>DiscountPercent</label>
                                            <input value="${voucher.getDiscountPercent()}${discount}" type="number" name="discount" placeholder="Enter discount percent" required class="form-control">
                                        </div>

                                        <div>
                                            <label>Quantity</label>
                                            <input value="${voucher.getQuantity()}${quantity}" type="number" name="quantity" placeholder="Enter quantity voucher" required class="form-control">
                                        </div>

                                    </div>
                                    <div class="right div div div div label">
                                        <div>
                                            <div>
                                                <label>StartDate</label>
                                                <input value="${voucher.getStartDate()}${startdate}" type="date" name="startdate" placeholder="Enter start date" required class="form-control">
                                            </div>

                                            <div>
                                                <label>EndDate</label>
                                                <input value="${voucher.getEndDate()}${enddate}" type="date" name="enddate" placeholder="Enter end date" required class="form-control">
                                            </div>

                                            <div>
                                                <label>MinValue</label>
                                                <input value="${voucher.getMinValue()}${minvalue}" type="number" name="minvalue" placeholder="Enter min value" required class="form-control">
                                            </div>

                                            <div>
                                                <label>Status</label><br>
                                                <select name="status" >
                                                    <option ${voucher.getStatus() == 1 ? 'selected': ''} value="1" >Active</option>
                                                    <option ${voucher.getStatus() == 0 ? 'selected': ''} value="0" >Expired</option>
                                                </select>
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
                <button type="submit" class="btn btn-primary">Edit Voucher</button>
            </form>

        </div>
        <script src='./tinymce/tinymce.min.js'></script>
        <script src='./js/tinyMceConfig.js'></script>
    </body>
</html>
