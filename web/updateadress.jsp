<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Address</title>
    <link rel="stylesheet" type="text/css" href="styles.css"> <!-- Đường dẫn đến tệp CSS của bạn -->
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }

        h1 {
            text-align: center;
            color: #333;
        }

        .container {
            max-width: 700px;
            margin: 0 auto;
            background: white;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }

        form {
            display: flex;
            flex-direction: column;
        }

        label {
            margin-bottom: 5px;
            font-weight: bold;
        }

        input[type="text"],
        input[type="checkbox"] {
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
        }

        input[type="checkbox"] {
            width: auto;
            margin-bottom: 10px;
        }

        input[type="submit"] {
            background-color: #5cb85c;
            color: white;
            border: none;
            padding: 10px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }

        input[type="submit"]:hover {
            background-color: #4cae4c;
        }

        .back-btn {
            display: inline-block;
            margin-top: 10px;
            text-align: center;
            text-decoration: none;
            color: #5bc0de;
        }

        .back-btn:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <h1>Update address</h1>
    <div class="container">
        <form action="address?id=${address.id}&action=updateDone" method="post">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" value="${address.namereceive}" required>

            <label for="phone">Phone Number:</label>
            <input type="text" id="phone" name="phone" value="${address.phonenumber}" required>

            <label for="address">Address:</label>
            <input type="text" id="address" value="${address.address}" name="address" required>

            <label for="address"><p style="font-size: 14px">Set as default
                    address <input style="margin: 0; padding: 0;"
                                   type="checkbox" id="default" name="default" ></p></label>

            <input type="submit" value="Update">
            <br><!-- comment -->
            <p>${mess}</p>
        </form>
        <a href="address?id=${sessionScope.user.id}&action=default" class="back-btn">Back to Manage Addresses</a> <!-- Đường dẫn trở lại trang quản lý địa chỉ -->
    </div>
</body>
</html>
