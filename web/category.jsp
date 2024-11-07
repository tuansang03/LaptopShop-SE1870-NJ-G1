<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Aroma Shop - Category</title>
        <link rel="icon" href="img/Fevicon.png" type="image/png">
        <link rel="stylesheet" href="vendors/bootstrap/bootstrap.min.css">
        <link rel="stylesheet" href="vendors/fontawesome/css/all.min.css">
        <link rel="stylesheet" href="vendors/themify-icons/themify-icons.css">
        <link rel="stylesheet" href="vendors/linericon/style.css">
        <link rel="stylesheet" href="vendors/owl-carousel/owl.theme.default.min.css">
        <link rel="stylesheet" href="vendors/owl-carousel/owl.carousel.min.css">
        <link rel="stylesheet" href="vendors/nice-select/nice-select.css">
        <link rel="stylesheet" href="vendors/nouislider/nouislider.min.css">
        <link rel="stylesheet" href="css/style.css">
        <style>
            .product-container {
                display: grid;
                grid-template-columns: repeat(3, 1fr);
                gap: 20px;
                padding: 20px;
            }
            .product-item {
                border: 1px solid #ccc;
                padding: 10px;
                text-align: center;
                background-color: #f9f9f9;
                transition: transform 0.2s, box-shadow 0.2s;
            }
            .product-item:hover {
                transform: translateY(-5px);
                box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
            }
            .product-item img {
                width: 150px;
                height: 150px;
                object-fit: cover;
                margin-bottom: 10px;
            }
            .brand {
                font-weight: bold;
                color: #808080;
                font-size: 14px;
                margin-bottom: 5px;
            }
            .product-item h3 {
                font-size: 18px;
                margin: 10px 0;
                color: #333;
            }
            .product-item p {
                font-size: 16px;
                color: #007BFF;
                font-weight: bold;
            }
            .pagination {
                text-align: center;
                margin-top: 20px;
            }
            .pagination a {
                padding: 8px 16px;
                margin: 0 4px;
                text-decoration: none;
                color: #007BFF;
                border: 1px solid #ddd;
                border-radius: 4px;
            }
            .pagination a.active {
                background-color: #007BFF;
                color: white;
            }
            .pagination a:hover {
                background-color: #ddd;
            }
        </style>
    </head>
    <body>
        <c:if test="${sessionScope.user!=null || (sessionScope.user==null && sessionScope.sale==null && sessionScope.admin==null)}">
              <%@include file="header.jsp" %>

              <section class="blog-banner-area" id="category">
                  <div class="container h-100">
                      <div class="blog-banner">
                          <div class="text-center">
                              <h1>Laptop List</h1>
                          </div>
                      </div>
                  </div>
              </section>

              <section class="section-margin--small mb-5">
                  <div class="container">
                      <div class="row">
                          <div class="col-xl-3 col-lg-4 col-md-5">
                              <form action="listproduct" method="get">
                                  <div class="sidebar-filter">
                                      <div class="top-filter-head">Browse Brands</div>
                                      <div class="common-filter">
                                          <ul>
                                              <c:forEach items="${brandlist}" var="b">
                                                  <li class="filter-list">
                                                      <input class="pixel-radio" type="checkbox" name="brand[]" value="${b.name}"
                                                             <c:if test="${fn:contains(selectedBrands, b.name)}">checked</c:if>>
                                                      <label>${b.name}</label>
                                                  </li>
                                              </c:forEach>
                                          </ul>
                                      </div>
                                  </div>

                                  <div class="sidebar-filter">
                                      <div class="top-filter-head">Browse Categories</div>
                                      <div class="common-filter">
                                          <ul>
                                              <c:forEach items="${categorylist}" var="c">
                                                  <li class="filter-list">
                                                      <input class="pixel-radio" type="checkbox" name="category[]" value="${c.name}"
                                                             <c:if test="${fn:contains(selectedCategories, c.name)}">checked</c:if>>
                                                      <label>${c.name}</label>
                                                  </li>    
                                              </c:forEach>
                                          </ul>
                                      </div>
                                  </div>

                                  <input type="submit" value="TÌM KIẾM" style="width: 100px; height: 30px; background-color: background; border-radius: 10px;">
                              </form>

                          </div>

                          <div class="col-xl-9 col-lg-8 col-md-7">
                              <div class="input-group filter-bar-search">
                                  <div class="sorting">
                                      <form action="listproduct">
                                          <select name="price">
                                              <option value="default">Default sorting</option>
                                              <option value="asc">Sort by price ascending</option>
                                              <option value="desc">Sort by price descending</option>   
                                          </select>
                                          <input type="submit"value="Sort" />    
                                      </form>
                                  </div>
                                  <div>
                                      <div class="input-group filter-bar-search">
                                          <form action="listproduct">
                                              <input type="text" name="name" placeholder="Search">
                                              <input type="submit"value="Search" />    
                                          </form>
                                      </div>
                                  </div>
                              </div>

                              <c:if test="${!empty productlist}">
                                  <p class="product-container">(${size} products found)</p>
                              </c:if>

                              <section class="product-container">
                                  <c:forEach var="p" items="${productlist}">
                                      <a href="information?productId=${p.detail}" class="product-link">
                                          <div class="product-item">
                                              <img src="${pageContext.request.contextPath}/images/${p.img}" alt="${p.name}">
                                              <div class="brand">${p.brand}</div>
                                              <h3>${p.name}</h3>
                                              <p>Giá: ${p.price}</p>
                                          </div>
                                      </a>
                                  </c:forEach>

                                  <c:if test="${empty productlist}">
                                      <p>There is no product to show.</p>
                                  </c:if>
                              </section>

                              <!-- Pagination Controls -->
                              <div class="pagination">
                                  <c:if test="${currentPage > 1}">
                                      <a href="listproduct?page=${currentPage - 1}">Previous</a>
                                  </c:if>

                                  <c:forEach var="i" begin="1" end="${totalPages}">
                                      <a href="listproduct?page=${i}" class="<c:if test='${i == currentPage}'>active</c:if>">${i}</a>
                                  </c:forEach>

                                  <c:if test="${currentPage < totalPages}">
                                      <a href="listproduct?page=${currentPage + 1}">Next</a>
                                  </c:if>
                              </div>
                              
                          </div>
                      </div>
                  </div>
              </section>

              <%@include file="footer.jsp" %>
              <script src="vendors/jquery/jquery-3.2.1.min.js"></script>
              <script src="vendors/bootstrap/bootstrap.bundle.min.js"></script>
              <script src="vendors/skrollr.min.js"></script>
              <script src="vendors/owl-carousel/owl.carousel.min.js"></script>
              <script src="vendors/nice-select/jquery.nice-select.min.js"></script>
              <script src="vendors/nouislider/nouislider.min.js"></script>
              <script src="vendors/jquery.ajaxchimp.min.js"></script>
              <script src="vendors/mail-script.js"></script>
              <script src="js/main.js"></script>
        </c:if>
        <c:if test="${sessionScope.sale!=null || sessionScope.admin!=null}">
            <%@include file="notallowpage.jsp" %>
        </c:if>
    </body>
</html>
