<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Feedback</title>
    </head>
    <body>
        <%@include file="sidebar2.jsp" %>

        <div class="col-md-10 content">
            <h2>Manage Feedback</h2>
            <div style="display: flex; align-items: center">
                <label>By Rated Point:</label>
                <form action="feedbackmanager" style="width: 100%;"  class="form-inline mb-3">
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
                        <option ${op.equals('5') ? 'selected':''} value="5">5 Star</option>
                        <option ${op.equals('4') ? 'selected':''} value="4">4 Star</option>
                        <option ${op.equals('3') ? 'selected':''} value="3">3 Star</option>
                        <option ${op.equals('2') ? 'selected':''} value="2">2 Star</option>
                        <option ${op.equals('1') ? 'selected':''} value="1">1 Star</option>
                    </select>
                </form>
                <label>Total: ${total}</label>
            </div>

            <table border="1" style="width: 100%; table-layout: fixed;">
                <thead>
                    <tr>
                        <th style="width: 5%;">Id</th>
                        <th style="width: 5%;">User Id</th>
                        <th style="width: 12%;">User name</th>
                        <th style="width: 10%;">Order Detail Id</th>
                        <th style="width: 7%;">Rating</th>
                        <th style="width: 36%;">Feedback Content</th>
                        <th style="width: 15%;">Feedback Date</th>
                        <th style="width: 10%;">Reply Feedback</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${feedbacklist}" var="f">
                        <tr>
                            <td>${f.id}</td>
                            <td>${f.user.id}</td>
                            <td>${f.user.fullName}</td>
                            <td>${f.orderdetailid.id}</td>
                            <td>${f.rating}</td>
                            <td style="overflow: hidden; text-overflow: ellipsis; white-space: nowrap;">${f.getFeedbackContent()}</td>
                            <td>${f.getFeedbackDate()}</td>
                            <td>
                                <c:if test="${f.getReplyFeedbackId() == 0}">
                                    <a href="seefeedbackdetail?feedbackid=${f.id}">Reply Feedback</a>
                                </c:if>
                            </td>
                        </tr>    
                    </c:forEach>
                </tbody>
            </table>
        </div>

    </body>
</html>
