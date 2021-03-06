<%@page import="java.util.List"%>
<%@page import="redis.clients.jedis.Jedis"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
	
	<%
//  	String m_id= session.getAttribute("loginId").toString();

	%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<title>YuXiKun</title>
<link rel="shortcut icon" href="<%= request.getContextPath() %>/images/logo.png">
<!-- Bootstrap 的 CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/vendors/bootstrap/css/bootstrap.min.css">
<!-- Link Swiper's CSS -->
<link rel="stylesheet"
	href="https://unpkg.com/swiper/swiper-bundle.min.css">
<!-- Font awesome -->
<script src="https://kit.fontawesome.com/a72ac34f47.js"
	crossorigin="anonymous"></script>
<!-- <script src="alert/dist/sweetalert-dev.js"></script> -->

<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>	
	<script
		src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.5.1.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/vendors/popper/popper.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/vendors/bootstrap/js/bootstrap.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/Front_end/product/js/getType.js"></script>
	<script src="<%=request.getContextPath()%>/Front_end/js/webSocket.js"></script>

<style>

* {
	box-sizing: border-box;
}

html {
	height: 100%;
}

body {
	background-color: #E3F8F6;
	height: 100%;
}

h1 {
/* 	color: rgba(236, 76, 76, 0.548); */
	shape-rendering: auto;
}

.footer {
	position: relative;
	top: 20%
}

#id_footer {
	box-sizing: border-box;
	position: relative;
	bottom: 0px;
	width: 100%;
	height: 200px;
	background:#6CCFF3;
}
#notice{
    position: relative;
    right: 40px;
}
	#buybtn, #rentbtn, #sellbtn {
    padding: 50px 150px;
    font-size: 60px;
    margin: 30px;
    margin-left: 60px;
    border-radius: 30px;
    box-shadow: 3px 3px 5px 6px darkgrey;
	}
	#buybtn{
	color: #fff;
    background-color: cornflowerblue;
    border-color:cornflowerblue;
	}
	#sellbtn{
	color: #212529;
    background-color: #FFC1D6;
    border-color:#FFC1D6;
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
		width: 95px;
		height: 35px;
	}
	#footerlogo {
		display: none;
	}
	div>span.footerwords {
		font-size: 20px;
		padding: 5px;
	}
	#ham, #search {
		display: none;
	}
	#buybtn, #rentbtn, #sellbtn {
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
	.logo {
		width: 95px;
		height: 35px;
	}
	#footerlogo {
		display: none;
	}
	div>span.footerwords {
		font-size: 20px;
		padding: 5px;
	}
	div.swiper-container {
		max-width: 100%;
		max-height: fit-content;
	}
	#buybtn, #rentbtn, #sellbtn {
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
	#buybtn, #rentbtn, #sellbtn {
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

#user,  #cart,#bell {
	float: right;
	position: relative;
	left: -20px;
	padding: 10px;
	color:white;
}

 

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
	left: 50px;
	top: 5px ;
	
}

#footerlogo {
	width: 300px;
	left: 205px;
	top: 31px ;
}

div.header {
	widows: 1280px;
	height: 80px;
	background: #6CCFF3;
}

#searchBar {
	position: relative;
	height: 40px;
	width: 600px;
	left: 91px;
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

div.article {
	text-align: center;
}

div.swiper-container {
    position: relative;
    height: 300px;
    width: 1272px;
/*     background: ivory; */
/*     border: 2px solid powderblue; */
    font-family: Helvetica Neue, Helvetica, Arial, sans-serif;
    font-size: 10px;
    color: #000;
    margin: 30px auto;
    padding: 0;
}

/* .swiper-container { */
/* 	width: 50%; */
/* 	height: 50%; */
/* } */

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
	top: 46px;
	left: 40%;
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
	color: white;
}

.overlay {
	height: 100%;
	width: 0;
	position: fixed;
	z-index: 3;
	top: 0;
	left: 0;
	opacity:0.9;
	background-color: rgb(23 154 184);
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

@media screen and (max-height: 450px) {
	.overlay a {
		font-size: 20px
	}
	.overlay .closebtn {
		font-size: 40px;
		top: 15px;
		right: 35px;
	}
}

div.header {
	z-index: 3
}

#nav {
	position: absolute;
	top: 0;
	right: 0;
	width: 200px;
	z-index: 4;
}

.search {
	display: inline-block;
}

select#ptype {
	position: relative;
	font-size: 20px;
	top: 11px;
	height: 40px;
	left: 96px;
	z-index: 0;
}

#searchBar {
	width: 470px;
	position:relative;
	height:40px;
	left:91px;
	top:10;
}

#showToast{
position: absolute;
right: 18px;
z-index: 100;
}

#buttongroup{
width:460.25px;
height:100px;
font-size:35px;
background-color:rgb(23 154 184);
border-color:rgb(23 154 184);



}

/* #showToast .toast { */
/* position: relative; */
/* left: 1500px; */
/* } */

/* #showToast{ */
/* 	position: fixed; */
/* 	right: 10px; */
/* 	bottom: 10px; */
/* 	z-index: 5; */
/* } */


</style>
</head>

<body>

	<div class="header">

		<i class="fas fa-bars" id="ham" onclick="openNav()"></i>
<!-- 		<div id="myNav" class="overlay"> -->
<!-- 			<a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a> -->
<!-- 			<div class="overlay-content"> -->
<%-- 				<a href="<%=request.getContextPath()%>/ProductServlet?ptype=no&name=&action=findByName">我要買</a>  --%>
<%-- 				<a href="<%=request.getContextPath()%>/member/controller/MemberServlet?action=goSellerIndex">我要賣</a> --%>
<!-- 				<a href="#">關於遊戲堃</a> -->
<!-- 			</div> -->
<!-- 		</div> -->
		
		<div id="myNav" class="overlay">
  <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
  <div class="overlay-content">
 <input type="submit" value="我要買" class="btn btn-primary btn-lg"  id="buttongroup"
    onclick="window.location='<%=request.getContextPath()%>/ProductServlet?ptype=no&name=&action=findByName';" /> 
    <br>
     <input type="submit" value="我要賣" class="btn btn-primary btn-lg" id="buttongroup"
    onclick="window.location='<%=request.getContextPath()%>/member/controller/MemberServlet?action=goSellerIndex';"  class="btn btn-primary btn-lg"/>   
	<br> 
	<input type="submit" value="關於遊戲堃" class="btn btn-primary btn-lg" id="buttongroup"
    onclick="" />
  </div>
</div>

		<a href="<%=request.getContextPath()%>/Front_end/index.jsp"><img
			src="<%=request.getContextPath()%>/images/white_LOGO字在外版(revised).png"
			class="logo" id="headerlogo" type="button"> </a>

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


		<table>
			<tr class="nav" id="nav">

				<td>
					<a href="<%=request.getContextPath()%>/Front_end/shoppingCart/checkBuyPage.jsp"><i class="fas fa-shopping-cart" 
					id="cart"> </i></a>
				</td>

				<td><i class="far fa-bell" id="bell" type="button" 
					data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="true" onclick="notice()"></i>
					<div class="dropdown-menu" aria-labelledby="bell">
				<ul>
				<a class="dropdown-item" id="notice" onclick="belldel()" href="<%=request.getContextPath()%>/Front_end/notice/notice.jsp"></a> 
                </ul>					
					</div> 
				</td>


				<td>
					<!-- user下拉開始 --> 
					<i class="fas fa-user-circle" id="user"
					type="button" id="dropdownMenuButton2" data-toggle="dropdown"
					aria-haspopup="true" aria-expanded="false"></i>
					<div class="dropdown-menu" aria-labelledby="dropdownMenuButton2">

						<a class="dropdown-item"
							href="<%=request.getContextPath()%>/Front_end/members/MyAccount.jsp">
							<%=session.getAttribute("loginName") == null ? "我" : session.getAttribute("loginName")%>的帳戶
						</a>
						<hr>
						<a class="dropdown-item"
							href="<%=request.getContextPath()%>/Front_end/members/LoginPage.jsp">登入/註冊</a>
						<a class="dropdown-item" href="<%=request.getContextPath()%>/member/controller/MemberServlet?action=goSellerIndex">賣家中心</a> 
						<form action="<%=request.getContextPath()%>/member/controller/MemberLogout">
						<button class="dropdown-item" type="submit">登出</button>
						</form>
					</div> 
					<!-- user下拉結束 -->
				</td>

			</tr>
			<tr>
		</table>

	</div>
	
	<div id="showToast">
		
	</div>
	
	<input type="hidden" id="contextPath"
		value="<%=request.getContextPath()%>">
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
			document.getElementById("myNav").style.width = "25%";
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