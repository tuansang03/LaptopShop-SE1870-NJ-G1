<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="icon" href="img/Fevicon.png" type="image/png">
        <link rel="stylesheet" href="vendors/themify-icons/themify-icons.css">
        <title>Feedback</title>
        <style>
            body {
                font-family: 'Arial', sans-serif;
                background-color: #f8f9fa; /* Light background color for better contrast */
                margin: 0;
                padding: 0;
                line-height: 1.6;
            }
            .container {
                max-width: 1200px;
                margin: 0 auto;
                padding: 20px;
            }
            .blog-banner-area {
                background: #007bff; /* Bootstrap primary color */
                color: white;
                padding: 60px 0;
                text-align: center;
            }
            h1 {
                font-size: 2.5rem;
                margin-bottom: 0;
            }
            .image-container {
                width: 100%;
                max-width: 300px;
                margin: 0 auto;
                overflow: hidden;
                border-radius: 8px; /* Rounded corners for the image container */
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* Soft shadow for depth */
            }
            .image-container img {
                width: 100%;
                height: auto;
                object-fit: cover; /* Cover ensures the image fills the space */
            }
            .feedback-card {
                background-color: white; /* White background for the feedback section */
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                margin: 20px auto; /* Center the feedback card */
                max-width: 600px; /* Limit width of the feedback card */
            }
            .star-rating {
                display: flex;
                flex-direction: row-reverse;
                font-size: 2rem;
                justify-content: left;
                margin: 20px 0;
            }
            .star-rating input[type="radio"] {
                display: none;
            }
            .star-rating label {
                color: gray;
                cursor: pointer;
                transition: color 0.3s; /* Smooth color transition */
            }
            .star-rating input[type="radio"]:checked ~ label {
                color: gold;
            }
            .input-feedback {
                width: 100%;
                max-width: 500px;
                height: 150px;
                padding: 10px;
                font-size: 16px;
                resize: vertical;
                border: 1px solid #ced4da; /* Light border */
                border-radius: 5px;
                margin: 10px 0;
                transition: border 0.3s;
            }
            .input-feedback:focus {
                border-color: #007bff; /* Change border color on focus */
                outline: none;
            }
            input[type="submit"] {
                background-color: #007bff; /* Bootstrap primary color */
                color: white;
                padding: 10px 20px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                font-size: 16px;
                transition: background-color 0.3s;
                margin-top: 10px; /* Space above button */
            }
            input[type="submit"]:hover {
                background-color: #0056b3; /* Darker shade on hover */
            }
            .feedback-details {
                background-color: white; /* White background for contrast */
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                margin-top: 20px; /* Space above feedback details */
            }
            .feedback-details p {
                margin: 10px 0; /* Spacing between product details */
            }
            .error-message {
                color: red;
                font-size: 0.9rem;
                margin-top: 5px;
            }
        </style>
        <script>
            function validateForm(event) {
                const ratingInputs = document.querySelectorAll('input[name="rating"]');
                const feedbackInput = document.querySelector('textarea[name="feedback"]');
                let ratingSelected = false;

                // Check if a rating has been selected
                ratingInputs.forEach(input => {
                    if (input.checked) {
                        ratingSelected = true;
                    }
                });

                // Show error message if no rating is selected
                if (!ratingSelected) {
                    event.preventDefault(); // Prevent form submission
                    document.getElementById('rating-error').textContent = 'Please select a rating.';
                } else {
                    document.getElementById('rating-error').textContent = ''; // Clear error message
                }

                // Check if feedback is empty and show error if necessary
                if (feedbackInput.value.trim() === '') {
                    event.preventDefault(); // Prevent form submission
                    document.getElementById('feedback-error').textContent = 'Feedback is required.';
                } else {
                    document.getElementById('feedback-error').textContent = ''; // Clear error message
                }
            }
        </script>
    </head>
    <body>
        <%@include file="header.jsp" %>

        <!-- ================ start banner area ================= -->	
        <section class="blog-banner-area" id="blog">
            <div class="container h-100">
                <div class="blog-banner">
                    <div class="text-center">
                        <h1>Feedback</h1>
                    </div>
                </div>
            </div>
        </section>
        <!-- ================ end banner area ================= -->

        <div class="container">
            <div class="row">
                <div class="col-md-4">
                    <a class="image-container" href="information?productId=${orderdetail.getProductDetail().getId()}">
                        <img src="${pageContext.request.contextPath}/images/${productimage.image}" alt="Product Image"/>
                    </a>
                    <div class="feedback-details">
                        <p><strong>Product Name:</strong> ${orderdetail.getProductDetail().getProduct().name}</p>
                        <p><strong>Brand:</strong> ${orderdetail.getProductDetail().getProduct().brand.name}</p>
                        <p><strong>Category:</strong> ${orderdetail.getProductDetail().getProduct().category.name}</p>
                        <p><strong>Configuration:</strong> ${orderdetail.getProductDetail().getConfiguration().getName()}</p>
                    </div>
                </div>

                <div class="col-md-8">

                    <c:if test="${orderdetail.getOrder().getOrderStatus() != 'done'}">
                        <h4 style="color: red;">${message}</h4>
                    </c:if>

                    <c:if test="${orderdetail.getOrder().getOrderStatus() == 'done'}">


                        <div class="feedback-card"> <!-- Feedback card starts here -->
                            <c:if test="${!empty myfeedback}">
                                <div class="star-rating">
                                    <input type="radio" id="star5" name="rating" value="5" disabled />
                                    <label for="star5">&#9733;</label>
                                    <input type="radio" id="star4" name="rating" value="4" disabled />
                                    <label for="star4">&#9733;</label>
                                    <input type="radio" id="star3" name="rating" value="3" disabled />
                                    <label for="star3">&#9733;</label>
                                    <input type="radio" id="star2" name="rating" value="2" disabled />
                                    <label for="star2">&#9733;</label>
                                    <input type="radio" id="star1" name="rating" value="1" disabled />
                                    <label for="star1">&#9733;</label>
                                    Rating:
                                </div>
                                Feedback date: ${myfeedback.getFeedbackDate()}<br>

                                <script>
                                    let ratingValue = ${myfeedback.getRating()};
                                    if (ratingValue >= 1 && ratingValue <= 5) {
                                        document.getElementById('star' + ratingValue).checked = true;
                                    }
                                </script>

                                <label>Your feedback:</label>
                                <textarea name="feedback" class="input-feedback" placeholder="${myfeedback.getFeedbackContent()}" disabled ></textarea>
                            </c:if>

                            <style>
                                .reply-section {
                                    background-color: #f9f9f9;
                                    padding: 15px;
                                    border-radius: 8px;
                                    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
                                    margin-bottom: 15px;
                                }
                                .reply-header {
                                    font-size: 0.9rem;
                                    color: #555;
                                    display: flex;
                                    justify-content: space-between;
                                    align-items: center;
                                    margin-bottom: 5px;
                                }
                                .reply-content {
                                    font-size: 1rem;
                                    color: #333;
                                    line-height: 1.6;
                                }
                            </style>

                            <c:forEach items="${listreply}" var="r">
                                <div class="reply-section">
                                    <div class="reply-header">
                                        <span><strong>${r.getUser().getFullName()}</strong> (Saler) Reply</span>
                                        <span>${r.getFeedbackDate()}</span>
                                    </div>
                                    <div class="reply-content">
                                        ${r.getFeedbackContent()}
                                    </div>
                                </div>
                            </c:forEach>


                            <c:if test="${empty myfeedback}">
                                <form action="feedback" method="get" onsubmit="validateForm(event)">
                                    <div class="star-rating">
                                        <input type="radio" id="star5" name="rating" value="5"/>
                                        <label for="star5">&#9733;</label>
                                        <input type="radio" id="star4" name="rating" value="4" />
                                        <label for="star4">&#9733;</label>
                                        <input type="radio" id="star3" name="rating" value="3" />
                                        <label for="star3">&#9733;</label>
                                        <input type="radio" id="star2" name="rating" value="2" />
                                        <label for="star2">&#9733;</label>
                                        <input type="radio" id="star1" name="rating" value="1" />
                                        <label for="star1">&#9733;</label>
                                        Rating:
                                    </div>
                                    <div id="rating-error" class="error-message"></div>

                                    <textarea name="feedback" class="input-feedback" placeholder="Feedback (max 300 characters)" maxlength="300" required></textarea>


                                    <input type="hidden" value="${user.id}" name="uid"/>
                                    <input type="hidden" value="${orderdetail.id}" name="orderdetailid"/>
                                    <input type="submit" value="Save" />
                                </form>
                            </c:if>
                        </div> <!-- Feedback card ends here -->
                    </c:if>

                </div>
            </div>
        </div>

        <%@include file="footer.jsp" %>
    </body>
</html>
