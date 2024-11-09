<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Feedback Detail</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f0f2f5;
                color: #333;
            }
            .container {
                max-width: 900px;
                margin: 30px auto;
                padding: 25px;
                background-color: #fff;
                border: 1px solid #a0d8f1; /* Light blue border */
                border-radius: 8px;
                box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            }
            h2 {
                text-align: center;
                color: #444;
                margin-bottom: 25px;
                font-size: 1.8em;
            }
            .details {
                display: flex;
                flex-wrap: wrap;
                gap: 20px;
                justify-content: space-between;
            }
            .detail-box {
                width: 45%;
                background-color: #f9f9f9;
                padding: 15px;
                border: 1px solid #a0d8f1; /* Light blue border for detail boxes */
                border-radius: 5px;
                box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.1);
            }
            .label {
                font-weight: bold;
                color: #555;
                margin-bottom: 8px;
                display: block;
            }
            .highlight {
                color: #ff5722;
                font-weight: bold;
            }
            textarea {
                width: 100%;
                resize: none;
                border: 1px solid #a0d8f1; /* Light blue border for textarea */
                border-radius: 5px;
                padding: 12px;
                font-size: 14px;
                background-color: #f7f7f7;
                height: 90px;
                line-height: 1.4;
                color: #333;
            }
            .reply-section {
                margin-top: 25px;
                padding: 15px;
                border: 1px solid #a0d8f1; /* Light blue border for reply section */
                border-radius: 6px;
                background-color: #f8f8f8;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.05);
            }
            .reply-title {
                font-weight: bold;
                color: #444;
                margin-bottom: 15px;
                font-size: 1.2em;
            }
            .reply-form {
                display: flex;
                gap: 10px;
                align-items: center;
                margin-top: 10px;
            }
            .reply-form input[type="text"] {
                flex-grow: 1;
                padding: 10px;
                border: 1px solid #a0d8f1; /* Light blue border for reply input */
                border-radius: 5px;
                font-size: 14px;
                background-color: #fafafa;
            }
            .reply-form input[type="submit"] {
                background-color: #4CAF50;
                color: white;
                border: none;
                padding: 10px 15px;
                border-radius: 5px;
                cursor: pointer;
                font-size: 14px;
                transition: background-color 0.3s ease;
            }
            .reply-form input[type="submit"]:hover {
                background-color: #45a049;
            }
            .reply-list {
                margin-top: 15px;
            }
            .reply-item {
                margin-bottom: 12px;
                padding: 10px;
                border: 1px solid #a0d8f1; /* Light blue border for each reply */
                border-radius: 5px;
                background-color: #fff;
                box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
            }
            .reply-date {
                font-size: 0.85em;
                color: #777;
                text-align: right;
                margin-top: 5px;
            }
        </style>
    </head>
    <body>
        <%@include file="sidebar2.jsp" %>
        <div class="container">
            <h2>Feedback Detail</h2>
            <div class="details">
                <div class="detail-box">
                    <span class="label">Feedback ID:</span>
                    <span>${feedback.id}</span>
                </div>
                <div class="detail-box">
                    <span class="label">User ID:</span>
                    <span>${feedback.user.id}</span>
                </div>
                <div class="detail-box">
                    <span class="label">User Name:</span>
                    <span>${feedback.user.fullName}</span>
                </div>
                <div class="detail-box">
                    <span class="label">Order Detail ID:</span>
                    <span>${feedback.orderdetailid.id}</span>
                </div>
                <div class="detail-box">
                    <span class="label">Rating:</span>
                    <span class="highlight">${feedback.rating}</span>
                </div>
                <div class="detail-box">
                    <span class="label">Feedback Date:</span>
                    <span>${feedback.getFeedbackDate()}</span>
                </div>
                <div class="detail-box" style="width: 100%;">
                    <span class="label">Feedback Content:</span>
                    <textarea readonly>${feedback.getFeedbackContent()}</textarea>
                </div>
            </div>

            <div class="reply-section">
                <div class="reply-title">Replies</div>
                <div class="reply-list">
                    <c:if test="${!empty listreply}">
                        <c:forEach items="${listreply}" var="r">
                            <div class="reply-item">
                                <div class="reply-date">Date: ${r.getFeedbackDate()}</div>
                                <p>${r.getFeedbackContent()}</p>
                                <div style="text-align: right;">
                                    <a href="deletefeedback?fid=${feedback.id}&&replyid=${r.id}" onclick="return confirmDelete();" class="btn btn-danger btn-sm">
                                        <i class="fas fa-trash-alt"></i>
                                    </a>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>
                </div>
                <script>
                    function confirmDelete() {
                        return confirm("Are you sure you want to delete this reply?");
                    }
                </script>

                <form action="replyfeedback" method="GET" class="reply-form">
                    <input type="hidden" value="${feedback.id}" name="fid"/>
                    <input type="hidden" value="${feedback.rating}" name="rating"/>
                    <input type="hidden" value="${feedback.orderdetailid.getId()}" name="orderdetailid"/>
                    <input type="text" name="reply" placeholder="Type your reply here..." required/>
                    <input type="submit" value="Reply" />
                </form>
            </div>
        </div>
    </body>
</html>
