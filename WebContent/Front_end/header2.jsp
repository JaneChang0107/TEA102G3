<%@page import="java.util.List"%>
<%@page import="redis.clients.jedis.Jedis"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	

<!DOCTYPE html>
<html lang="en">
<jsp:useBean id="pSvc" scope="page"	class="com.product.model.ProductService" />

<head>
<meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
<title>YuXiKun</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="<%=request.getContextPath()%>/assets/img/favicon.ico" />
        <!-- Font Awesome icons (free version)-->
        <script src="https://use.fontawesome.com/releases/v5.15.1/js/all.js" crossorigin="anonymous"></script>
        <!-- Google fonts-->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="<%=request.getContextPath()%>/assets/css/styles.css" rel="stylesheet" />

<style>


#arrowdown {
	position: relative;
	left: -20px;
	top: 20px;
}

#search {
	font-size: 4ex;
}

.logo {
	position: relative;
	left: 0px;
	top: 0px;
}

#footerlogo {
	width: 300px;
}

div.header {
	widows: 1280px;
	height: 60px;
	background: #6CCFF3;
}

#searchBar {
	position: relative;
	height: 40px;
	width: 600px;
	left: 70px;
	top: 0px;
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

div.article {
	text-align: center;
}

div.swiper-container {
    position: relative;
    height: 300px;
    width: 1000px;
    background: ivory;
    border: 2px solid powderblue;
    font-family: Helvetica Neue, Helvetica, Arial, sans-serif;
    font-size: 10px;
    color: #000;
    margin: 40px auto;
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

.overlay {
	height: 100%;
	width: 0;
	position: fixed;
	z-index: 3;
	top: 0;
	left: 0;
	background-color: rgb(0, 0, 0);
	background-color: rgba(28, 65, 145, 0.9);
	overflow-x: hidden;
	transition: 0.5s;
}

.overlay-content {
	position: relative;
	top: 25%;
	width: 100%;
	text-align: center;
	margin-top: 30px;
}

.overlay a {
	padding: 8px;
	text-decoration: none;
	font-size: 36px;
	color: #FFFFFF;
	display: block;
	transition: 0.3s;
}

.overlay a:hover, .overlay a:focus {
	color: #f1f1f1;
}

.overlay .closebtn {
	position: absolute;
	top: 20px;
	left: 45px;
	font-size: 60px;
}
svg:not(:root).svg-inline--fa {
    overflow: visible;
    width: 29px;
    height: 42px;
}
.search {
	display: inline-block;
}

select#ptype {
	position: relative;
	font-size: 20px;
	top: 2px;
	height: 40px;
	left: 75px;
	z-index: 1;
}

#searchBar {
	width: 470px;
}

#showToast{
position: absolute;
right: 18px;
}
</style>
</head>
<body id="page-top">
<div class="header">
<!-- Navigation-->
<nav class="navbar navbar-expand-lg bg-secondary text-uppercase fixed-top" id="mainNav">
<div class="container">
            
<!-- -----漢堡------ -->
<!--             <i class="fas fa-bars" id="ham"  style="margin-left: -60px;bgcolor:white" onclick="openNav()"></i> -->
<!-- 		    <div id="myNav" class="overlay"> -->
<!-- 			<a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a> -->
<!-- 			  <div class="overlay-content"> -->
<!-- 				<a href="http://localhost:8081/TEA102G3/ProductServlet?ptype=no&name=&action=findByName">我要買</a>  -->
<%-- 				<a class="dropdown-item" href="<%=request.getContextPath()%>/member/controller/MemberServlet?action=goSellerIndex">我要賣</a> --%>
<!-- 				<a href="#">關於遊戲堃</a> -->
<!-- 			  </div> -->
<!-- 		   </div> -->
            
<!-- -----logo------ -->
                <a class="navbar-brand js-scroll-trigger"
                href="<%=request.getContextPath()%>/Front_end/index.jsp">
                <img src="<%=request.getContextPath()%>/images/white_LOGO字在外版(revised).png"
			     class="logo" id="headerlogo" type="button"></a>
               
<!-- -----搜尋------ -->              
        <div class="search">
			<form action="<%=request.getContextPath()%>/ProductServlet"
				method="get" id="searchForm">
				<select name="ptype" id="ptype">
				</select>
				<input type="text" id="searchBar" name="name">
				<i	class="fas fa-search" id="search">
				 	<input type="hidden" name="action" value="findByName">
				</i>
			</form>
		</div>
               
  
<!-- -----rwd menu------ -->                 
<!--                 <button class="navbar-toggler navbar-toggler-right text-uppercase font-weight-bold bg-primary text-white rounded" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation"> -->
<!--                     Menu -->
<!--                     <i class="fas fa-bars"></i> -->
<!--                 </button> -->



<!-- -----購物車通知會員------ -->                 
      <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
<!--                         <li class="nav-item mx-0 mx-lg-1"><a class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger" href="#portfolio">Portfolio</a></li> -->
<!--                         <li class="nav-item mx-0 mx-lg-1"><a class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger" href="#about">About</a></li> -->
<!--                         <li class="nav-item mx-0 mx-lg-1"><a class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger" href="#contact">Contact</a></li> -->
                        <li class="nav-item mx-0 mx-lg-1">
                        <a class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger" 
                        href="<%=request.getContextPath()%>/Front_end/shoppingCart/checkBuyPage.jsp">
                        <i class="fas fa-shopping-cart" id="cart"> </i></a></li>
                        
                        
                        
 <li class="nav-item mx-0 mx-lg-1">
 <a class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger" id="bell" type="button" 
	data-toggle="dropdown" aria-haspopup="true" aria-expanded="true" onclick="notice()">
 <i class="far fa-bell"></i></a>
	<div class="dropdown-menu" aria-labelledby="bell">
	<ul>
	<a class="dropdown-item" id="notice" onclick="belldel()" href="<%=request.getContextPath()%>/Front_end/notice/notice.jsp"></a> 
    </ul>					
	</div>
	
</li>
                        
                        
                        
<li class="nav-item mx-0 mx-lg-1">
<a class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger" 
id="user" type="button" id="dropdownMenuButton2" data-toggle="dropdown"
	aria-haspopup="true" aria-expanded="false">
<i class="fas fa-user-circle"></i></a>

  <!-- user下拉開始 --> 
	<div class="dropdown-menu" aria-labelledby="dropdownMenuButton2">
        <a class="dropdown-item" href="<%=request.getContextPath()%>/Front_end/members/MyAccount.jsp">
		<%=session.getAttribute("loginName") == null ? "我" : session.getAttribute("loginName")%>的帳戶</a>
		<hr>
		
		<a class="dropdown-item"
		href="<%=request.getContextPath()%>/Front_end/members/LoginPage.jsp">登入/註冊</a>
		
		<a class="dropdown-item" href="<%=request.getContextPath()%>/member/controller/MemberServlet?action=goSellerIndex">賣家中心</a> 
		
		<form action="<%=request.getContextPath()%>/member/controller/MemberLogout">
			<button class="dropdown-item" type="submit">登出</button>
		</form>
	</div> 
	
</li>
<!-- user下拉結束 -->                  
      
      </ul>
   </div>
           

</div> <!--container-->     
</nav>   <!--nav-->
</div>  <!--header-->

	
	<div id="showToast">	
	</div>
	
	<input type="hidden" id="contextPath"
		value="<%=request.getContextPath()%>">





<!-- Bootstrap core JS-->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Third party plugin JS-->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>
        <!-- Contact form JS-->
        <script src="assets/mail/jqBootstrapValidation.js"></script>
        <script src="assets/mail/contact_me.js"></script>
       
<!-- Core theme JS-->
        <script src="<%=request.getContextPath()%>/assets/js/scripts.js"></script>
  
  
  
   <script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
	<script>
		var swiper = new Swiper('.swiper-container', {
			slidesPerView : 4,
			spaceBetween : 30,
			slidesPerGroup : 4,
			loop : true,
			loopFillGroupWithBlank : true,
			pagination : {
				el : '.swiper-pagination',
				clickable : true,
			},
			navigation : {
				nextEl : '.swiper-button-next',
				prevEl : '.swiper-button-prev',
			},
		});

		function openNav() {
			document.getElementById("myNav").style.width = "30%";
		}

		function closeNav() {
			document.getElementById("myNav").style.width = "0%";
		}
// var context="/TEA102G3"		
		
		function belldel(){
			$.ajax({
				url:context + "/light.do",
				type:"get",
				data:{
					"action":"delete",
					},
				dataType:"json",
				success:function(data){
					console.log(data)
				}
			});
			
		}
		
		notice()
		function notice() {
			$.ajax({
				url : context + "/light.do",
				type : "get",
				data : {
					"action" : "announcement",
				},
				dataType : "json",
				success : function(data) {
					$("#notice").html("");

					console.log(data)

					let notice = "";
					for (let i = 0; i < data.length; i++) {
						notice += ("<li>"
								+ JSON.parse(data[i]).message + "</li>");
					}

					$("#notice").append(notice);
				}

			});
		}
	</script>
   
   
   
    </body>
</html>
