<%-- 
    Document   : userprofile
    Created on : Oct 8, 2024, 1:22:21 PM
    Author     : kieud
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <style>
            body {
                background: rgb(99, 39, 120)
            }

            .form-control:focus {
                box-shadow: none;
                border-color: #BA68C8
            }

            .profile-button {
                background: rgb(99, 39, 120);
                box-shadow: none;
                border: none
            }

            .profile-button:hover {
                background: #682773
            }

            .profile-button:focus {
                background: #682773;
                box-shadow: none
            }

            .profile-button:active {
                background: #682773;
                box-shadow: none
            }

            .back:hover {
                color: #682773;
                cursor: pointer
            }

            .labels {
                font-size: 11px
            }

            .add-experience:hover {
                background: #BA68C8;
                color: #fff;
                cursor: pointer;
                border: solid 1px #BA68C8
            }
            .option-profile{
                margin-top: 110px;
                
                height: 30%;
            }
            .option{
                margin-top: 20px;
                margin-bottom: 15px;
                border: 2px solid gray;
                font-size: 15px;
                border-radius: 40px;
                text-align: center;
                width: 100%;
                height: 20%;
                padding: 10px;
            }



        </style>


        <section>
             <div class="container rounded bg-white mt-5 mb-5 margin-left">
            <div class="row">
                <div class="col-md-2 option-profile">
                    <ul>
                        <li><a href="" class="option">Profile Information</a></li>
                        <li><a href="" class="option">Order Manage</a></li>
                        
                    </ul>
                </div>
                <div class="col-md-3 border-right">
                    <div class="d-flex flex-column align-items-center text-center p-3 py-5"><img class="rounded-circle mt-5" width="150px" src="https://st3.depositphotos.com/15648834/17930/v/600/depositphotos_179308454-stock-illustration-unknown-person-silhouette-glasses-profile.jpg"><span class="font-weight-bold">${sessionScope.user.fullName}</span><span class="text-black-50"></span><span> </span></div>
                </div>
                <!--CHo nay node de code de phan truong hop khi nguoi dung chon cac tuy chon-->
                <div class="col-md-7 border-right ">
                    <div class="p-3 py-5">
                        <div class="d-flex justify-content-between align-items-center mb-3">
                            <h4 class="text-right">Profile Settings</h4>
                        </div>
                        <div class="row mt-2">
                            <div class="col-md-12"><label class="labels">Full Name</label><input type="text" class="form-control" placeholder="enter user profile" value="${sessionScope.user.getFullName()}"></div>  
                        </div>
                        <div class="row mt-3" >
                            <div class="col-md-12"><label class="labels">Address</label><input type="text" class="form-control" placeholder="enter address line 1" value=""></div>
                            <div class="col-md-12"><label class="labels">Email ID</label><input type="text" class="form-control" placeholder="enter email id" value="${sessionScope.user.getEmail()}"></div>
                        </div>
                        <div class="row mt-3">
                            <div class="col-md-6"><label class="labels">UserName</label><input type="text" class="form-control" placeholder="enter username" value="${sessionScope.user.getUserName()}"></div>
                            <div class="col-md-6"><label class="labels">Password</label><input type="text" class="form-control" value="*********" placeholder="enter password" readonly></div>
                        </div>
                        <div class="mt-5 text-center"><button class="btn btn-primary profile-button" type="button">Save Profile</button></div>
                    </div>
                </div>
                 <!--CHo nay node de end code de phan truong hop khi nguoi dung chon cac tuy chon-->
<!--                <div class="col-md-4">
                    <div class="p-3 py-5">
                        <div class="d-flex justify-content-between align-items-center experience"><span>Edit Experience</span><span class="border px-3 p-1 add-experience"><i class="fa fa-plus"></i>&nbsp;Experience</span></div><br>
                        <div class="col-md-12"><label class="labels">Experience in Designing</label><input type="text" class="form-control" placeholder="experience" value=""></div> <br>
                        <div class="col-md-12"><label class="labels">Additional Details</label><input type="text" class="form-control" placeholder="additional details" value=""></div>
                    </div>
                </div>-->
            </div>
        </div>
        </section>
    </div>
</div>




<%@include file="footer.jsp" %>


</body>
</html>
