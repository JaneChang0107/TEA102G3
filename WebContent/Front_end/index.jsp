<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <title>YuXiKun</title>

  <!-- Bootstrap 的 CSS -->
  <link rel="stylesheet" href="../vendors/bootstrap/css/bootstrap.min.css">
  <!-- Link Swiper's CSS -->
  <link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css">
  <!-- Font awesome -->
  <script src="https://kit.fontawesome.com/a72ac34f47.js" crossorigin="anonymous"></script>

  <style>
    * {
      box-sizing: border-box;
    }

    body {
      background: #E3F8F6;
    }

    h1 {
      color: rgba(236, 76, 76, 0.548);
      shape-rendering: auto;
    }

    #id_footer {
      box-sizing: border-box;
      position: relative;
      bottom: -300px;
      width: 100%;
      height: 200px;
      background: #6CCFF3;
      
    }

    /*RWD開始--------------------------------------------*/
    @media screen and (max-width: 576px) {
      div.header {
        max-width: 100%;
      }

      #searchBar {
        max-width: 100px;
      }

      div.swiper-container {
        max-width: 100%;
        max-height: fit-content;
      }

      .logo {
        width: 20%;
        position: absolute;
        right: 5px;
        width:95px;
        height:35px;
      }
      #footerlogo {
        display: none;
      }
      div>span.footerwords{
        font-size: 20px;
        padding:5px;
      }
      
      #ham,
      #search {
        display: none;
      }

      #buybtn,
      #rentbtn,
      #sellbtn {
        width: 150px;
        height: 150px;
        left: 10px;
        font-size: 30px;
      }

    }

    @media screen and (min-width: 576px) and (max-width:768px) {
      #searchBar {
        max-width: 100px;
      }

      #ham {
        display: none;

      }
      .logo{
        width:95px;
        height:35px;
      }

      #footerlogo {
        display: none;
      }
      div>span.footerwords{
        font-size: 20px;
        padding:5px;
      }

      div.swiper-container {
        max-width: 100%;
        max-height: fit-content;
      }

      #buybtn,
      #rentbtn,
      #sellbtn {
        width: 150px;
        height: 150px;
        left: 10px;
        font-size: 30px;
      }
    }

    @media screen and (min-width: 768px) and (max-width: 1199px) {
      div.header {
        width: 100%;
      }

      div.swiper-container {
        max-width: 100%;
        max-height: fit-content;
      }

      #searchBar {
        max-width: 200px;
      }

      #buybtn,
      #rentbtn,
      #sellbtn {
        width: 180px;
        height: 180px;
        left: 50px;
      }
    }

    @media screen and (min-width: 1200px) {
      div.header {
        width: 100%;
      }

      #searchBar {
        max-width: 100%;
      }
    }

    /*RWD結束----------------------------------------------------*/

    i {
      font-size: 5ex;
      position: relative;
      left: 20px;
      top: 15px;
      padding: 5px;
    }

    #user,
    #bell,
    #cart {
      float: right;
      position: relative;
      left: -20px;
      padding: 10px;
    }

    #arrowdown {
      position: relative;
      left: -20px;
      top: 20px;
    }

    #search {
      font-size: 4ex;
      left: -15px;
    }

    .logo {
      position: relative;
      left: 50px;
      top: 10px;
    }

    #footerlogo {
      width: 300px;
    }

    div.header {
      widows: 1280px;
      height: 80px;
      background: #6CCFF3;
    }

    input {
      position: relative;
      height: 40px;
      width: 600px;
      left: 70px;
      top: 10px;
    }

    .btn-circle-xl {
      position: relative;
      width: 270px;
      height: 270px;
      padding: 30px;
      font-size: 50px;
      line-height: 1.33;
      border-radius: 135px;
      margin: 30px;
      box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
    }

    div.article{
      text-align: center;
    }

    div.swiper-container {
      position: relative;
      height: 300px;
      width: 1000px;
      background: #eee;
      font-family: Helvetica Neue, Helvetica, Arial, sans-serif;
      font-size: 10px;
      color: #000;
      margin: 20px auto;
      padding: 0;
    }

    .swiper-container {
      width: 50%;
      height: 50%;
    }

    .swiper-slide {
      text-align: center;
      font-size: 60px;
      background: rgb(255, 255, 255);

      /* Center slide text vertically */
      display: -webkit-box;
      display: -ms-flexbox;
      display: -webkit-flex;
      display: flex;
      -webkit-box-pack: center;
      -ms-flex-pack: center;
      -webkit-justify-content: center;
      justify-content: center;
      -webkit-box-align: center;
      -ms-flex-align: center;
      -webkit-align-items: center;
      align-items: center;
    }

    div.footerwords {
      position: absolute;
      display: inline;
      top: 20px;
      left: 50%;
    }

    span.footerwords {
      font-size: 30px;
      padding: 20px 30px;

    }

    div.copyright {
      position: absolute;
      bottom: 0px;
      left: 50%;
      padding: 10px;
      transform: translateX(-50%);
      color: gray;


    }
  </style>


  <div class="header">
    <i class="fas fa-bars" id="ham"></i>
 
    <img src="../images/white_LOGO字在外版(revised).png" class="logo" id="headerlogo">
    <input type="text" id="searchBar">
    <i class="fas fa-angle-down" id="arrowdown"></i>
    <i class="fas fa-search" id="search"></i>
    <i class="fas fa-user-circle" id="user"></i>
    <i class="far fa-bell" id="bell"></i>
    <i class="fas fa-shopping-cart" id="cart"></i>

  </div>

</head>



<body>

  <div class="article" style="border:1px solid red;">

    
    <button type="button" class="btn btn-success btn-circle-xl" id="buybtn">我要買</button>
    <button type="button" class="btn btn-danger btn-circle-xl" id="rentbtn">我要租</button>
    <button type="button" class="btn btn-warning btn-circle-xl" id="sellbtn">我要賣</button>


  </div>


  <div class="swiper-container">
    <H1>熱銷商品</H1>
    <div class="swiper-wrapper">
      <div class="swiper-slide">Slide 1</div>
      <div class="swiper-slide">Slide 2</div>
      <div class="swiper-slide">Slide 3</div>
      <div class="swiper-slide">Slide 4</div>
      <div class="swiper-slide">Slide 5</div>
      <div class="swiper-slide">Slide 6</div>
      <div class="swiper-slide">Slide 7</div>
      <div class="swiper-slide">Slide 8</div>
    </div>
    <div class="swiper-pagination"></div>
    <div class="swiper-button-next"></div>
    <div class="swiper-button-prev"></div>
  </div>


  <div class="swiper-container">
    <H1>熱銷出租品</H1>
    <div class="swiper-wrapper">
      <div class="swiper-slide">Slide 1</div>
      <div class="swiper-slide">Slide 2</div>
      <div class="swiper-slide">Slide 3</div>
      <div class="swiper-slide">Slide 4</div>
      <div class="swiper-slide">Slide 5</div>
      <div class="swiper-slide">Slide 6</div>
      <div class="swiper-slide">Slide 7</div>
      <div class="swiper-slide">Slide 8</div>
    </div>
    <div class="swiper-pagination"></div>
    <div class="swiper-button-next"></div>
    <div class="swiper-button-prev"></div>
  </div>


  <footer id="id_footer">

    <img src="../images/white_LOGO字在外版(revised).png" class="logo" id="footerlogo">
    <div class="footerwords" id="footerwords">
      <span class="footerwords">關於我們</span><span class="footerwords">隱私權政策</span><br>
      <span class="footerwords">網站地圖</span><span class="footerwords">常用問答</span><br>
      <span class="footerwords">服務條款</span>
    </div>

    <div class="copyright">Copyright © 2020 YuXiKun Co. ,Ltd. All rights reserved</div>



  </footer>


  <script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>



  <script>
    var swiper = new Swiper('.swiper-container', {
      slidesPerView: 4,
      spaceBetween: 30,
      slidesPerGroup: 4,
      loop: true,
      loopFillGroupWithBlank: true,
      pagination: {
        el: '.swiper-pagination',
        clickable: true,
      },
      navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev',
      },
    });
  </script>


  <!-- body 結束標籤之前，載入Bootstrap 的 JS 及其相依性安裝(jQuery、Popper) -->
  <script src="../vendors/jquery/jquery-3.5.1.min.js"></script>
  <script src="../vendors/popper/popper.min.js"></script>
  <script src="../vendors/bootstrap/js/bootstrap.min.js"></script>
</body>

</html>