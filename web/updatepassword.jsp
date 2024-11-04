<%-- 
    Document   : updatepassword
    Created on : Nov 4, 2024, 6:25:05 PM
    Author     : kieud
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Password</title>
    </head>
    <body>
        <style>
            
            
        </style>
         <div class="password-container">
    <div class="password-box">
        <label class="labels">Password</label>
        
        

        
            <form action="profile?profile=info&action=x" method="post" class="password-form">
                
                <input id="c1" value="${currentPassword}" type="password" class="form-control xx" required placeholder="Enter current password" name="currentPassword">
                <div id="seepassword" class="see-password"> See</div>
                <input value="${newPassword}" type="password" class="form-control xx" required placeholder="Enter new password" name="newPassword">
                <input value="${confirmPassword}" type="password" class="form-control xx" required placeholder="Confirm new password" name="confirmPassword">

                <div class="button-container">
                    <a style="margin: 0 auto; margin-right:  20px;" href="profile?profile=info" class="cancel-button">Cancel</a>
                    <button style="" type="submit" class="save-button">Save</button>
                </div>
            </form>
            <div class="message">
                <h5 style="color: tomato">${error}</h5>
                <h5 style="color: #5cb99a">${message}</h5>
            </div>
       
    </div>
</div>

<style>
    /* Căn giữa toàn bộ phần tử */
    .password-container {
        display: flex;
        align-items: center;
        justify-content: center;
        height: 100vh;
        background-color: #f0f0f5;
    }

    /* Hình vuông chính giữa */
    .password-box {
        width: 350px;
        padding: 50px;
        padding-right:  65px;
        border-radius: 10px;
        box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.2);
        background-color: #ffffff;
        text-align: center;
    }

    .password-box label {
        font-weight: bold;
        display: block;
        margin-bottom: 10px;
    }

    .form-control {
        width: 100%;
        margin-top: 10px;
        margin-bottom: 15px;
        padding: 10px;
        border: 1px solid #ccc;
        border-radius: 5px;
    }

    /* Thẻ link Change Password */
    .password-box a {
        color: #007bff;
        text-decoration: none;
        font-weight: bold;
        margin-top: 10px;
        display: block;
    }

    /* Phần tiêu đề Change Password */
    .password-form h3 {
        margin-bottom: 20px;
        color: #333;
    }

    /* Button Cancel và Save */
    .button-container {
        display: flex;
        justify-content: space-between;
    }

    .cancel-button,
    .save-button {
        padding: 10px;
        width: 48%;
        border: none;
        font-weight: bold;
        cursor: pointer;
        border-radius: 5px;
    }

    .cancel-button {
        background-color: #ffcccb;
        color: #333;
    }

    .save-button {
        background-color: #a0ceff;
        color: #333;
    }

    /* Nút See */
    .see-password {
        cursor: pointer;
        color: #007bff;
        font-size: 14px;
        text-align: left;
        margin-top: -10px;
        margin-bottom: 10px;
    }

    /* Căn giữa lỗi và thông báo */
    .message {
        margin-top: 15px;
        text-align: center;
    }
</style>

<script>
    document.getElementById('seepassword').addEventListener('click', function () {
        const passwordfield = document.getElementById('c1');
        if (passwordfield.type === "password") {
            passwordfield.type = "text";
        } else {
            passwordfield.type = "password";
        }
    });
</script>

    </body>
</html>
