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
            <div style="display: flex; align-items: center;">
                <label>By Rated Point:</label>
                <form action="feedbackmanager" style="width: 100%;" class="form-inline mb-3">
                    <select name="op" style="
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
                            outline: none;">
                        <option value="all" <c:if test="${op == 'all'}">selected</c:if>>All</option>
                        <option value="5" <c:if test="${op == '5'}">selected</c:if>>5 Star</option>
                        <option value="4" <c:if test="${op == '4'}">selected</c:if>>4 Star</option>
                        <option value="3" <c:if test="${op == '3'}">selected</c:if>>3 Star</option>
                        <option value="2" <c:if test="${op == '2'}">selected</c:if>>2 Star</option>
                        <option value="1" <c:if test="${op == '1'}">selected</c:if>>1 Star</option>
                    </select>

                    <label>By reply status:</label>
                    <select name="status" style="
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
                            outline: none;">
                        <option value="all" <c:if test="${status == 'all'}">selected</c:if>>All</option>
                        <option value="notreplied" <c:if test="${status == 'notreplied'}">selected</c:if>>Not replied</option>
                        <option value="replied" <c:if test="${status == 'replied'}">selected</c:if>>Replied</option>
                    </select>
                    
                    <input type="submit" value="Show" style="margin-left: 10px; padding: 5px 10px; font-size: 16px; background-color: #28a745; color: white; border: none; border-radius: 4px; cursor: pointer;"/>
                </form>
                <label>Total: ${total}</label>
            </div>

            <table border="1" style="width: 100%; table-layout: fixed; margin-top: 20px;">
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
                            <td style="overflow: hidden; text-overflow: ellipsis; white-space: nowrap;">${f.feedbackContent}</td>
                            <td>${f.feedbackDate}</td>
                            <td>
                                <a href="seefeedbackdetail?feedbackid=${f.id}">Reply Feedback</a>
                            </td>
                        </tr>    
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
