<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Farro:wght@300;400;500;700&display=swap" rel="stylesheet">
    </head>
    <body>

        <style>

            *{
                font-family: "Farro", sans-serif;
            }
            .logo_h img{
                width: 133px;


            }
            .logo_h{
                position:absolute;
                left: 70px;
            }
            .nav-link:hover{

            }
            .header_button{

            }
            .search-container {
                position: relative;
                display: inline-block;
            }

            .search-btn {

                background: none;
                border: none;
                padding: 8px 12px;
                cursor: pointer;
                border-radius: 4px;
            }

            .search-btn i {
                font-size: 16px;
            }

            .search-field {
                display: none;
                position: absolute;
                top: 100%;
                left: 0;
                width: 300px;
                padding: 8px;
                border: 1px solid #ccc;
                border-radius: 4px;
                margin-top: 5px;
            }

            .suggestions {
                display: none;
                position: absolute;
                top: 150%;
                left: 0;
                width: 300px;
                background-color: white;
                border: 1px solid #ccc;
                border-radius: 4px;
                z-index: 1;
            }

            .suggestions li {
                padding: 8px;
                cursor: pointer;
            }

            .suggestions li:hover {
                background-color: #f0f0f0;
            }

        </style>



        <header class="header_area">
            <div class="main_menu">
                <nav class="navbar navbar-expand-lg navbar-light">
                    <div class="container">
                        <a class="navbar-brand logo_h" href="home"><img src="img/logo.png" alt=""></a>
                        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <div class="collapse navbar-collapse offset" id="navbarSupportedContent">
                            <ul class="nav navbar-nav menu_nav ml-auto mr-auto">
                                <li class="nav-item active"><a class="nav-link" href="home">Home</a></li>
                                <li class="nav-item submenu dropdown">
                                    <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                                       aria-expanded="false">Shop</a>
                                    <ul class="dropdown-menu">
                                        <li class="nav-item"><a class="nav-link" href="category.jsp">Shop Category</a></li>
                                        <li class="nav-item"><a class="nav-link" href="single-product.jsp">Product Details</a></li>
                                        <li class="nav-item"><a class="nav-link" href="checkout.jsp">Product Checkout</a></li>
                                        <li class="nav-item"><a class="nav-link" href="confirmation.jsp">Confirmation</a></li>
                                        <li class="nav-item"><a class="nav-link" href="cart.jsp">Shopping Cart</a></li>
                                    </ul>
                                </li>
                                <li class="nav-item submenu dropdown">
                                    <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                                       aria-expanded="false">Blog</a>    
                                    <ul class="dropdown-menu">
                                        <li class="nav-item"><a class="nav-link" href="postlist">See All Post</a></li>
                                        
                                    </ul>
                                </li>
                                <li class="nav-item submenu dropdown">
                                    <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                                       aria-expanded="false">Pages</a>
                                    <ul class="dropdown-menu">
                                        <li class="nav-item"><a class="nav-link" href="login.jsp">Login</a></li>
                                        <li class="nav-item"><a class="nav-link" href="register.jsp">Register</a></li>
                                        <li class="nav-item"><a class="nav-link" href="tracking-order.jsp">Tracking</a></li>
                                    </ul>
                                </li>
                                <li class="nav-item"><a class="nav-link" class="header_button" href="AboutUs.jsp">About Us</a></li>
                            </ul>

                            <ul class="nav-shop">
<<<<<<< HEAD
                                <li class="nav-item"></li>
                                <div class="search-container">
                                    <button type="button" class="search-btn" onclick="toggleSearchField()">
                                        <i class="ti-search"></i>
                                    </button>

                                    <form action="search" method="get" id="searchForm">
                                        <input type="text" id="searchField" name="query" class="search-field" placeholder="Search for products..." />
                                        <ul id="suggestions" class="suggestions"></ul>
                                    </form>
                                </div>

                                <script>
                                    document.querySelector('.search-btn').addEventListener('click', function () {
                                        var searchField = document.querySelector('.search-field');
                                        searchField.style.display = 'block';
                                        searchField.focus();
                                    });



                                    document.addEventListener('click', function (event) {
                                        var searchField = document.querySelector('.search-field');
                                        var searchBtn = document.querySelector('.search-btn');
                                        var suggestions = document.getElementById('suggestions');

                                        // Kiểm tra nếu phần tử được click không phải là search field, search button, hoặc các gợi ý
                                        if (!searchField.contains(event.target) &&
                                                !searchBtn.contains(event.target) &&
                                                !suggestions.contains(event.target)) {
                                            searchField.style.display = 'none';
                                            suggestions.style.display = 'none';
                                        }
                                    });

                                </script>

                                <li class="nav-item">
                                    <!--                                    da edit o day-->
                                    <a href="cart.jsp">
                                        <button>
                                            <i class="ti-shopping-cart"></i>
                                            <span class="nav-shop__circle">3</span>
                                        </button>
                                    </a>
                                </li>

                                <li class="nav-item">

                                    <a class="nav-link button button-header" href="login.jsp">Login</a>
                                    <a class="button button-header" href="register.jsp">Sign Up</a>
                                </li>

=======
                                <li class="nav-item"><button><i class="ti-search"></i></button></li>
                                <li class="nav-item">
                                    <a href="loadProductCart">
                                        <button>
                                            <i class="ti-shopping-cart"></i><span class="nav-shop__circle">3</span>
                                        </button>
                                    </a>
                                        
                                </li>
                                <li class="nav-item"><a class="button button-header" href="#">Buy Now</a></li>
>>>>>>> 2e6a3e409741c11777b6a412f7a7fcd2921c8513
                            </ul>
                            <!-- Thêm nút đăng nhập và đăng ký -->

                        </div>
                    </div>
                </nav>
            </div>
        </header>
    </body>
</html>
