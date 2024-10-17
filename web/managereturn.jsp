<%-- 
    Document   : managereturn
    Created on : Oct 14, 2024, 9:52:49 PM
    Author     : PHONG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Return</title>
    </head>
    <body>
        <%@include file="sidebar.jsp" %>

        <div class="col-md-10 content">
            <h2>Manage Return</h2>
            <div style="display: flex; align-items: center">
                <label>Return Status:</label>
                <form action="showreturnrequestbystatus" style="width: 100%;"  class="form-inline mb-3">
                    <select onchange="this.form.submit()" name="op" style="
                            margin-left: 10px;
                            text-align: center;
                            height: 48px;
                            width: 10%;
                            color: black;
                            border: 2px solid #28a745;
                            background-color: white;
                            border-radius: 4px;
                            padding: 5px;
                            font-size: 16px;
                            cursor: pointer;
                            outline: none;
                            ">
                        <option ${op.equals('all') ? 'selected':''} value="all">All</option>
                        <option ${op.equals('wait') ? 'selected':''} value="wait">Wait</option>
                        <option ${op.equals('denied') ? 'selected':''} value="denied">Denied</option>
                        <option ${op.equals('accepted') ? 'selected':''} value="accepted">Accepted</option>
                    </select>
                </form>

            </div>
            <table border="1">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Order Id</th>
                        <th>Return date</th>
                        <th>Total return amount</th>
                        <th>Reason</th>
                        <th>Refund method</th>
                        <th>Refund status</th>
                        <th>Return status</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${list}" var="i">
                        <tr>
                            <td>${i.id}</td>
                            <td>${i.getOder().getId()}</td><!-- Kiểm tra lại 'oder', có thể bạn muốn dùng 'order' -->
                            <td>${i.returnDate}</td>
                            <td>${i.totalReturnAmount}</td>
                            <td>${i.reason}</td>
                            <td>${i.refundMethod}</td>
                            <td>
                                <form action="changestatusrefund" method="get">
                                    <select name="status" onchange="this.form.submit()">
                                        <option ${i.refundStatus == 'wait' ? 'selected' : ''} value="wait">Wait</option>
                                        <option ${i.refundStatus == 'denied' ? 'selected' : ''} value="denied">Denied</option>
                                        <option ${i.refundStatus == 'accepted' ? 'selected' : ''} value="accepted">Accepted</option>
                                    </select>
                                    <input type="hidden" value="${i.id}" name="rid"/>
                                </form>
                            </td>
                            <td>
                                <form action="changestatusreturn" method="get">
                                    <select name="status" onchange="this.form.submit()">
                                        <option ${i.returnStatus == 'wait' ? 'selected' : ''} value="wait">Wait</option>
                                        <option ${i.returnStatus == 'denied' ? 'selected' : ''} value="denied">Denied</option>
                                        <option ${i.returnStatus == 'accepted' ? 'selected' : ''} value="accepted">Accepted</option>
                                    </select>
                                    <input type="hidden" value="${i.id}" name="rid"/>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>


                </tbody>
            </table>

        </div>
    </body>
</html>