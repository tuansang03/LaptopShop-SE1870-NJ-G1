<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="model.Address" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Address Management</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                display: flex;
                flex-direction: column;
                align-items: center;
            }
            .container {
                display: flex;
                flex-wrap: wrap;
                gap: 20px;
                justify-content: center;
                margin-top: 20px;
            }
            .address-box {
                width: 250px;
                padding: 20px;
                border: 1px solid #ccc;
                border-radius: 8px;
                background-color: #f9f9f9;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
                display: flex;
                flex-direction: column;
                align-items: center;
                text-align: center;
            }
            .address-box h3 {
                margin: 0;
                font-size: 18px;
                color: #333;
            }
            .address-box p {
                margin: 5px 0;
                color: #555;
            }
            .button-group {
                display: flex;
                gap: 10px;
                margin-top: 10px;
            }
            .button-group a, .button-group button {
                padding: 8px 12px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                color: #fff;
                text-decoration: none;
                font-size: 14px;
            }
            .edit-btn { background-color: #4CAF50; }
            .delete-btn { background-color: #f44336; }
            .add-btn { background-color: #2196F3; padding: 10px 20px; margin-top: 20px; }
        </style>
    </head>
    <body>
         
        <h1>Manage Addresses</h1>
        <div class="container">
            <%
                List<Address> addresses = (List<Address>) request.getAttribute("addresses");
                if (addresses != null ) {
                    for (Address address : addresses) {
            %>
            <div class="address-box">
                
                <h3><%= address.getNamereceive() %></h3>
                <p>Phone: <%= address.getPhonenumber() %></p>
                <p>Address: <%= address.getAddress() %></p>
                <p>Default: <%= address.isIsdefault() ? "Yes" : "No" %></p>
                <div class="button-group">
                    <a href="address?id=<%= address.getId() %>&action=edit" class="edit-btn">Edit</a>
                    <a href="address?id=<%= address.getId() %>&action=delete" class="delete-btn" onclick="return confirm('Are you sure you want to delete this address?');">Delete</a>
                </div>
            </div>
            <% 
                    } 
                } else { 
            %>
            <p>No addresses available. Please add a new address.</p>
            <% } %>
        </div>
            <a href="address?id=${sessionScope.user.id}&action=addNew" class="add-btn" style="text-decoration: none; color: white">Add New Address</a>
            <a href="profile?profile=info" class="add-btn"; style="text-decoration: none; color: white">Back</a>

    </body>
</html>
