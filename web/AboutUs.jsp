<%-- 
    Document   : AboutUs.jsp
    Created on : Sep 20, 2024, 3:36:17 PM
    Author     : kieud
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
       
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>About Us</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        
        <link rel="icon" href="img/Fevicon.png" type="image/png">
        <link rel="stylesheet" href="vendors/bootstrap/bootstrap.min.css">
        <link rel="stylesheet" href="vendors/fontawesome/css/all.min.css">
        <link rel="stylesheet" href="vendors/themify-icons/themify-icons.css">
        <link rel="stylesheet" href="vendors/linericon/style.css">
        <link rel="stylesheet" href="vendors/owl-carousel/owl.theme.default.min.css">
        <link rel="stylesheet" href="vendors/owl-carousel/owl.carousel.min.css">
        <link rel="stylesheet" href="css/style.css">

    </head>
    <body>
        <!--Header-->
        <%@include file="header.jsp" %>
        
        <style>
            

*,
*:before,
*:after {
  -moz-box-sizing: border-box;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
}

body {
  margin: 0;
}

.wk-desk-1 {
  width: 8.333333%;
}

.wk-desk-2 {
  width: 16.666667%;
}

.wk-desk-3 {
  width: 25%;
}

.wk-desk-4 {
  width: 33.333333%;
}

.wk-desk-5 {
  width: 41.666667%;
}

.wk-desk-6 {
  width: 50%;
}

.wk-desk-7 {
  width: 58.333333%;
}

.wk-desk-8 {
  width: 66.666667%;
}

.wk-desk-9 {
  width: 75%;
}

.wk-desk-10 {
  width: 83.333333%;
}

.wk-desk-11 {
  width: 91.666667%;
}

.wk-desk-12 {
  width: 100%;
}

@media (max-width: 1024px) {
  .wk-ipadp-1 {
    width: 8.333333%;
  }

  .wk-ipadp-2 {
    width: 16.666667%;
  }

  .wk-ipadp-3 {
    width: 25%;
  }

  .wk-ipadp-4 {
    width: 33.333333%;
  }

  .wk-ipadp-5 {
    width: 41.666667%;
  }

  .wk-ipadp-6 {
    width: 50%;
  }

  .wk-ipadp-7 {
    width: 58.333333%;
  }

  .wk-ipadp-8 {
    width: 66.666667%;
  }

  .wk-ipadp-9 {
    width: 75%;
  }

  .wk-ipadp-10 {
    width: 83.333333%;
  }

  .wk-ipadp-11 {
    width: 91.666667%;
  }

  .wk-ipadp-12 {
    width: 100%;
  }
}

@media (max-width: 768px) {
  .wk-tab-1 {
    width: 8.333333%;
  }

  .wk-tab-2 {
    width: 16.666667%;
  }

  .wk-tab-3 {
    width: 25%;
  }

  .wk-tab-4 {
    width: 33.333333%;
  }

  .wk-tab-5 {
    width: 41.666667%;
  }

  .wk-tab-6 {
    width: 50%;
  }

  .wk-tab-7 {
    width: 58.333333%;
  }

  .wk-tab-8 {
    width: 66.666667%;
  }

  .wk-tab-9 {
    width: 75%;
  }

  .wk-tab-10 {
    width: 83.333333%;
  }

  .wk-tab-11 {
    width: 91.666667%;
  }

  .wk-tab-12 {
    width: 100%;
  }
}

@media (max-width: 500px) {
  .wk-mobile-1 {
    width: 8.333333%;
  }

  .wk-mobile-2 {
    width: 16.666667%;
  }

  .wk-mobile-3 {
    width: 25%;
  }

  .wk-mobile-4 {
    width: 33.333333%;
  }

  .wk-mobile-5 {
    width: 41.666667%;
  }

  .wk-mobile-6 {
    width: 50%;
  }

  .wk-mobile-7 {
    width: 58.333333%;
  }

  .wk-mobile-8 {
    width: 66.666667%;
  }

  .wk-mobile-9 {
    width: 75%;
  }

  .wk-mobile-10 {
    width: 83.333333%;
  }

  .wk-mobile-11 {
    width: 91.666667%;
  }

  .wk-mobile-12 {
    width: 100%;
  }
}
            * {
  font-family: Nunito, sans-serif;
}

.text-blk {
  margin-top: 0px;
  margin-right: 0px;
  margin-bottom: 0px;
  margin-left: 0px;
  line-height: 25px;
  padding-top: 0px;
  padding-right: 0px;
  padding-bottom: 0px;
  padding-left: 0px;
}

.responsive-cell-block {
  min-height: 75px;
}

.responsive-container-block {
  min-height: 75px;
  height: fit-content;
  width: 100%;
  padding-top: 10px;
  padding-right: 10px;
  padding-bottom: 10px;
  padding-left: 10px;
  display: flex;
  flex-wrap: wrap;
  margin-top: 0px;
  margin-right: auto;
  margin-bottom: 0px;
  margin-left: auto;
  justify-content: flex-start;
}

.responsive-container-block.bigContainer {
  padding-top: 0px;
  padding-right: 50px;
  padding-bottom: 0px;
  padding-left: 50px;
  margin-top: 50px;
  margin-right: 0px;
  margin-bottom: 50px;
  margin-left: 0px;
}

.responsive-container-block.Container {
  max-width: 1320px;
  justify-content: space-evenly;
  align-items: center;
  padding-top: 10px;
  padding-right: 10px;
  padding-bottom: 0px;
  padding-left: 10px;
  position: relative;
  overflow-x: hidden;
  overflow-y: hidden;
  margin-top: 0px;
  margin-right: auto;
  margin-bottom: 0px;
  margin-left: auto;
}

.mainImg {
  width: 100%;
  height: 800px;
  object-fit: cover;
}

.blueDots {
  position: absolute;
  top: 150px;
  right: 15%;
  z-index: -1;
  left: auto;
  width: 80%;
  height: 500px;
  object-fit: cover;
}

.imgContainer {
  position: relative;
  width: 48%;
}

.responsive-container-block.textSide {
  width: 48%;
  padding-top: 0px;
  padding-right: 0px;
  padding-bottom: 0px;
  padding-left: 0px;
  z-index: 100;
}

.text-blk.heading {
  font-size: 36px;
  line-height: 40px;
  font-weight: 700;
  margin-top: 0px;
  margin-right: 0px;
  margin-bottom: 20px;
  margin-left: 0px;
}

.text-blk.subHeading {
  font-size: 18px;
  line-height: 26px;
  margin-top: 0px;
  margin-right: 0px;
  margin-bottom: 20px;
  margin-left: 0px;
}

.cardImg {
  width: 31px;
  height: 31px;
}

.cardImgContainer {
  padding-top: 20px;
  padding-right: 20px;
  padding-bottom: 20px;
  padding-left: 20px;
  border-top-width: 1px;
  border-right-width: 1px;
  border-bottom-width: 1px;
  border-left-width: 1px;
  border-top-style: solid;
  border-right-style: solid;
  border-bottom-style: solid;
  border-left-style: solid;
  border-top-color: rgb(229, 229, 229);
  border-right-color: rgb(229, 229, 229);
  border-bottom-color: rgb(229, 229, 229);
  border-left-color: rgb(229, 229, 229);
  border-image-source: initial;
  border-image-slice: initial;
  border-image-width: initial;
  border-image-outset: initial;
  border-image-repeat: initial;
  border-top-left-radius: 10px;
  border-top-right-radius: 10px;
  border-bottom-right-radius: 10px;
  border-bottom-left-radius: 10px;
  margin-top: 0px;
  margin-right: 10px;
  margin-bottom: 0px;
  margin-left: 0px;
}

.responsive-cell-block.wk-desk-6.wk-ipadp-6.wk-tab-12.wk-mobile-12 {
  display: flex;
  justify-content: center;
  align-items: center;
  padding-top: 10px;
  padding-right: 15px;
  padding-bottom: 10px;
  padding-left: 0px;
}

.text-blk.cardHeading {
  font-size: 18px;
  line-height: 28px;
  font-weight: 700;
  margin-top: 0px;
  margin-right: 0px;
  margin-bottom: 10px;
  margin-left: 0px;
}

.text-blk.cardSubHeading {
  color: rgb(153, 153, 153);
  line-height: 22px;
}

.explore {
  font-size: 18px;
  line-height: 20px;
  font-weight: 700;
  color: white;
  background-color: rgb(244, 152, 146);
  box-shadow: rgba(244, 152, 146, 0.25) 0px 10px 20px;
  border-top-left-radius: 10px;
  border-top-right-radius: 10px;
  border-bottom-right-radius: 10px;
  border-bottom-left-radius: 10px;
  cursor: pointer;
  border-top-width: 0px;
  border-right-width: 0px;
  border-bottom-width: 0px;
  border-left-width: 0px;
  border-top-style: initial;
  border-right-style: initial;
  border-bottom-style: initial;
  border-left-style: initial;
  border-top-color: initial;
  border-right-color: initial;
  border-bottom-color: initial;
  border-left-color: initial;
  border-image-source: initial;
  border-image-slice: initial;
  border-image-width: initial;
  border-image-outset: initial;
  border-image-repeat: initial;
  padding-top: 17px;
  padding-right: 40px;
  padding-bottom: 17px;
  padding-left: 40px;
}

.explore:hover {
  background-image: initial;
  background-position-x: initial;
  background-position-y: initial;
  background-size: initial;
  background-repeat-x: initial;
  background-repeat-y: initial;
  background-attachment: initial;
  background-origin: initial;
  background-clip: initial;
  background-color: rgb(244, 182, 176);
}

#ixvck {
  margin-top: 60px;
  margin-right: 0px;
  margin-bottom: 0px;
  margin-left: 0px;
}

.redDots {
  position: absolute;
  bottom: -350px;
  right: -100px;
  height: 500px;
  width: 400px;
  object-fit: cover;
  top: auto;
}

@media (max-width: 1024px) {
  .responsive-container-block.Container {
    position: relative;
    align-items: flex-start;
    justify-content: center;
  }

  .mainImg {
    bottom: 0px;
  }

  .imgContainer {
    position: absolute;
    bottom: 0px;
    left: 0px;
    height: auto;
    width: 60%;
  }

  .responsive-container-block.textSide {
    margin-top: 0px;
    margin-right: 0px;
    margin-bottom: 0px;
    margin-left: auto;
    width: 70%;
  }

  .responsive-container-block.Container {
    flex-direction: column-reverse;
  }

  .imgContainer {
    position: relative;
    width: auto;
    margin-top: 0px;
    margin-right: auto;
    margin-bottom: 0px;
    margin-left: auto;
  }

  .responsive-container-block.textSide {
    margin-top: 0px;
    margin-right: 0px;
    margin-bottom: 20px;
    margin-left: 0px;
    width: 100%;
  }

  .responsive-container-block.Container {
    flex-direction: row-reverse;
  }

  .responsive-container-block.Container {
    flex-direction: column-reverse;
  }
}

@media (max-width: 768px) {
  .responsive-container-block.textSide {
    width: 100%;
    align-items: center;
    flex-direction: column;
    justify-content: center;
  }

  .text-blk.subHeading {
    text-align: center;
    font-size: 17px;
    max-width: 520px;
  }

  .text-blk.heading {
    text-align: center;
  }

  .imgContainer {
    opacity: 0.8;
  }

  .imgContainer {
    height: 500px;
  }

  .imgContainer {
    width: 30px;
  }

  .responsive-container-block.Container {
    flex-direction: column-reverse;
  }

  .responsive-container-block.Container {
    flex-wrap: nowrap;
  }

  .responsive-container-block.textSide {
    width: 100%;
    margin-top: 0px;
    margin-right: 0px;
    margin-bottom: 20px;
    margin-left: 0px;
  }

  .imgContainer {
    width: 90%;
  }

  .imgContainer {
    height: 450px;
    margin-top: 5px;
    margin-right: 33.9062px;
    margin-bottom: 0px;
    margin-left: 33.9062px;
  }

  .redDots {
    display: none;
  }

  .explore {
    font-size: 16px;
    line-height: 14px;
  }
}

@media (max-width: 500px) {
  .imgContainer {
    position: static;
    height: 450px;
  }

  .mainImg {
    height: 100%;
  }

  .blueDots {
    width: 100%;
    left: 0px;
    top: 0px;
    bottom: auto;
    right: auto;
  }

  .imgContainer {
    width: 100%;
  }

  .responsive-container-block.textSide {
    margin-top: 0px;
    margin-right: 0px;
    margin-bottom: 0px;
    margin-left: 0px;
  }

  .responsive-container-block.Container {
    padding-top: 0px;
    padding-right: 0px;
    padding-bottom: 0px;
    padding-left: 0px;
    overflow-x: visible;
    overflow-y: visible;
  }

  .responsive-container-block.bigContainer {
    padding-top: 10px;
    padding-right: 20px;
    padding-bottom: 10px;
    padding-left: 20px;
    padding: 0 30px 0 30px;
  }

  .redDots {
    display: none;
  }

  .text-blk.subHeading {
    font-size: 16px;
    line-height: 23px;
  }

  .text-blk.heading {
    font-size: 28px;
    line-height: 28px;
  }

  .responsive-container-block.textSide {
    margin-top: 40px;
    margin-right: 0px;
    margin-bottom: 50px;
    margin-left: 0px;
  }

  .imgContainer {
    margin-top: 5px;
    margin-right: auto;
    margin-bottom: 0px;
    margin-left: auto;
    width: 100%;
    position: relative;
  }

  .explore {
    padding-top: 17px;
    padding-right: 0px;
    padding-bottom: 17px;
    padding-left: 0px;
    width: 100%;
  }

  #ixvck {
    width: 90%;
    margin-top: 40px;
    margin-right: 0px;
    margin-bottom: 0px;
    margin-left: 0px;
    font-size: 15px;
  }

  .blueDots {
    bottom: 0px;
    width: 100%;
    height: 80%;
    top: 10%;
  }

  .text-blk.cardHeading {
    font-size: 16px;
    margin-top: 0px;
    margin-right: 0px;
    margin-bottom: 8px;
    margin-left: 0px;
    line-height: 25px;
  }

  .responsive-cell-block.wk-desk-6.wk-ipadp-6.wk-tab-12.wk-mobile-12 {
    padding-top: 10px;
    padding-right: 0px;
    padding-bottom: 10px;
    padding-left: 0px;
  }
}
            
            
            
        </style>
        <!-- ================ start banner area ================= -->
        <section class="blog-banner-area" id="contact">
            <div class="container h-100">
                <div class="blog-banner">
                    <div class="text-center">
                        <h1>About Us</h1>
                        <nav aria-label="breadcrumb" class="banner-breadcrumb">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="#">Home</a></li>
                                <li class="breadcrumb-item active" aria-current="page">About Us</li>
                            </ol>
                        </nav>
                    </div>
                </div>
            </div>
        </section>
        <!-- ================ end banner area ================= -->
        
<div class="responsive-container-block bigContainer">
  <div class="responsive-container-block Container">
    <div class="imgContainer">
      <img class="blueDots" src="https://workik-widget-assets.s3.amazonaws.com/widget-assets/images/aw3.svg">
      <img class="mainImg" src="https://workik-widget-assets.s3.amazonaws.com/widget-assets/images/aw2.svg">
    </div>
    <div class="responsive-container-block textSide">
      <p class="text-blk heading">
        About Us
      </p>
      <div class="text-blk subHeading">
          <h3>About Us</h3> 
          <p>
Welcome to LaptopShop, your ultimate destination for top-quality laptops! Whether you're a professional seeking a high-performance machine, a student needing a reliable device, or a gamer in search of the latest in cutting-edge technology, we've got you covered.
</p>
<p>
At LaptopShop, we take pride in offering a diverse selection of laptops from the world's most trusted and innovative brands. Our lineup includes:
</p>
<p>
MacBook: Sleek, powerful, and incredibly user-friendly, MacBooks are perfect for those who value seamless performance and elegant design.
Acer: Known for affordability and versatility, Acer laptops deliver great functionality for everyday use.
ASUS: ASUS combines creativity and performance with a range of laptops designed for both gaming enthusiasts and productivity-focused users.
Dell: With a reputation for durability and robust features, Dell laptops offer exceptional performance for both personal and business use.
HP: HP's innovative designs and performance-driven laptops are perfect for professionals and casual users alike.
Lenovo: Famous for its business-oriented ThinkPad series, Lenovo delivers powerful laptops with unmatched durability and flexibility.
MSI: For gamers and creators alike, MSI offers high-performance laptops with advanced graphics and processing power.
LG: Ultra-light and ultra-portable, LG laptops combine stunning displays with long-lasting battery life, ideal for work and entertainment on the go.
Our mission is to provide you with not only the best laptops but also exceptional customer service. We believe in helping our customers find the right laptop to meet their specific needs, whether it's for work, play, or both.
</p>
<p>
At LaptopShop, we stand by our commitment to quality and customer satisfaction. Our team is always available to offer expert advice and support, ensuring that you make an informed decision with your purchase.
</p>
<p>
Thank you for choosing LaptopShop as your go-to store for all your laptop needs. Let’s make your tech experience truly exceptional!
</p>
      </div>
      <div class="responsive-cell-block wk-desk-6 wk-ipadp-6 wk-tab-12 wk-mobile-12">
        <div class="cardImgContainer">
          <img class="cardImg" src="https://workik-widget-assets.s3.amazonaws.com/widget-assets/images/id2.svg">
        </div>
        <div class="cardText">
          <p class="text-blk cardHeading">
            Value
          </p>
          <p class="text-blk cardSubHeading">
            Lorem ipsum dolor sit amet, consectetur adipiscing elit.
          </p>
        </div>
      </div>
      <div class="responsive-cell-block wk-desk-6 wk-ipadp-6 wk-tab-12 wk-mobile-12">
        <div class="cardImgContainer">
          <img class="cardImg" src="https://workik-widget-assets.s3.amazonaws.com/widget-assets/images/id2.svg">
        </div>
        <div class="cardText">
          <p class="text-blk cardHeading">
            Value
          </p>
          <p class="text-blk cardSubHeading">
            Lorem ipsum dolor sit amet, consectetur adipiscing elit.
          </p>
        </div>
      </div>
      <div class="responsive-cell-block wk-desk-6 wk-ipadp-6 wk-tab-12 wk-mobile-12">
        <div class="cardImgContainer">
          <img class="cardImg" src="https://workik-widget-assets.s3.amazonaws.com/widget-assets/images/id2.svg">
        </div>
        <div class="cardText">
          <p class="text-blk cardHeading">
            Value
          </p>
          <p class="text-blk cardSubHeading">
            Lorem ipsum dolor sit amet, consectetur adipiscing elit.
          </p>
        </div>
      </div>
      <div class="responsive-cell-block wk-desk-6 wk-ipadp-6 wk-tab-12 wk-mobile-12">
        <div class="cardImgContainer">
          <img class="cardImg" src="https://workik-widget-assets.s3.amazonaws.com/widget-assets/images/id2.svg">
        </div>
        <div class="cardText">
          <p class="text-blk cardHeading">
            Value
          </p>
          <p class="text-blk cardSubHeading">
            Lorem ipsum dolor sit amet, consectetur adipiscing elit.
          </p>
        </div>
      </div>
      <a>
        <button class="explore">
          Explore our Services
        </button>
      </a>
    </div>
    <img class="redDots" src="https://workik-widget-assets.s3.amazonaws.com/widget-assets/images/cw3.svg">
  </div>
</div>
        
        <%@include file="footer.jsp" %>
    </body>
</html>
