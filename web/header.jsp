<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<%@ page import="dal.CartDAOS" %>
<%@ page import="model.Cart" %>
<%@ page import="model.CartItem" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <style>
            
            
            .logo_h img{
                width: 180px;

                
            }
            .logo_h{
                position:absolute;
                left: 70px;
            }
            .nav-link:hover{
                
            }
            
            
        </style>
            
            
        
       <header class="header_area">
            <div class="main_menu">
                <nav class="navbar navbar-expand-lg navbar-light">
                    <div class="container">
                        <a class="navbar-brand logo_h" href="index.jsp"><img src="img/logo.png" alt=""></a>
                        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <div class="collapse navbar-collapse offset" id="navbarSupportedContent">
                            <ul class="nav navbar-nav menu_nav ml-auto mr-auto">
                                <li class="nav-item active"><a class="nav-link" href="index.jsp">Home</a></li>
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
                                        <li class="nav-item"><a class="nav-link" href="blog.jsp">Blog</a></li>
                                        <li class="nav-item"><a class="nav-link" href="single-blog.jsp">Blog Details</a></li>
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
                                <li class="nav-item"><a class="nav-link" href="contact.jsp">Contact</a></li>
                            </ul>

                            <%
                                 List<CartItem> listCart = null;
                                 
                                 User user = (User) session.getAttribute("user");
                                 CartDAOS cartDAO = new CartDAOS();
                                 if (!(user == null)) {
                                    Cart cart = cartDAO.getCartByUserID(user.getId());
                                    listCart = cartDAO.getAllProductOfCartItem(cart.getId());
                                 }   
                                    
                                 int quantity = 0;
                                    if(!(listCart == null)) {
                                        quantity = listCart.size();
                                    }
                            %>
                            
                            <ul class="nav-shop">
                                <li class="nav-item"><button><i class="ti-search"></i></button></li>
                                <li class="nav-item">
                                    <a href="loadProductCart">
                                        <button>
                                            <i class="ti-shopping-cart"></i><span class="nav-shop__circle"><%= quantity%></span>
                                        </button>
                                    </a>
                                        
                                </li>
                                <li class="nav-item"><a class="button button-header" href="#">Buy Now</a></li>
                            </ul>
                            <!-- Thêm nút đăng nhập và đăng ký -->
                            <ul class="navbar-nav">
                                <li class="nav-item">
                                    <a class="nav-link" href="login.jsp">Login</a>
                                </li>
                                
                            </ul>
                        </div>
                    </div>
                </nav>
            </div>
        </header>
    </body>
</html>
