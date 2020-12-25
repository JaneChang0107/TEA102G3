<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<title>YuXiKun</title>

<!-- Bootstrap 的 CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/vendors/bootstrap/css/bootstrap.min.css">
<!-- Link Swiper's CSS -->
<link rel="stylesheet"
	href="https://unpkg.com/swiper/swiper-bundle.min.css">
<!-- Font awesome -->
<script src="https://kit.fontawesome.com/a72ac34f47.js"
	crossorigin="anonymous"></script>

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
	bottom: 0px;
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

#user, #bell, #cart {
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

#searchBar {
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

div.article {
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
	right: 45px;
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

<<<<<<< HEAD
#nav{
position:absolute;
 top:0;
 right:0;
 width:200px;
=======
.nav {
	position: absolute;
	top: 0;
	right: 0;
	width: 200px;
>>>>>>> fbf5d7a73f04a8157748a0b2765a246611003d94
}
</style>

<div class="header">

	<i class="fas fa-bars" id="ham" onclick="openNav()"></i>
	<div id="myNav" class="overlay">
		<a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
		<div class="overlay-content">
			<a href="#">About</a> <a href="#">Services</a> <a href="#">Clients</a>
			<a href="#">Contact</a>
		</div>
	</div>

	<a href="<%=request.getContextPath()%>/Front_end/index.jsp"><img
		src="<%=request.getContextPath()%>/images/white_LOGO字在外版(revised).png"
		class="logo" id="headerlogo"> </a>
		<input type="text" id="searchBar">

	<i class="fas fa-angle-down" id="arrowdown"></i> <i
		class="fas fa-search" id="search"></i>


	<table>
		<tr class="nav" id="nav">

			<td>
				<!-- cart下拉 開始--> 
				<i class="fas fa-shopping-cart" type="button"
				id="cart" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="true"></i>
				<div class="dropdown-menu" aria-labelledby="cart">
					<a class="dropdown-item" href="#">租用車</a> <a class="dropdown-item"
						href="#">購買車</a>
				</div> 
				<!-- cart下拉結束 -->
			</td>

			<td><i class="far fa-bell" id="bell"></i></td>
			
			
			<td>
				<!-- user下拉開始 --> 
				<i class="fas fa-user-circle" id="user"
				type="button" id="dropdownMenuButton2" data-toggle="dropdown"
				aria-haspopup="true" aria-expanded="false"></i>
				<div class="dropdown-menu" aria-labelledby="dropdownMenuButton2">
					<a class="dropdown-item" href="<%=request.getContextPath()%>/Front_end/members/MyAccount.jsp">我的帳戶</a>
					<!--如有登入改為XXX的帳戶   -->
					<hr>
					<a class="dropdown-item"
						href="<%=request.getContextPath()%>/Front_end/members/LoginPage.jsp">登入/註冊</a>
					<!--如已有登入則改為另一個連結 -->
					<a class="dropdown-item" href="#">賣家中心</a> <a class="dropdown-item"
						href="#">登出</a>
				</div> 
				<!-- user下拉結束 -->
			</td>

		</tr>
		<tr>
	</table>










	<span></span>

	<div></div>
	<div></div>
</head>

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
		document.getElementById("myNav").style.width = "35%";
	}

	function closeNav() {
		document.getElementById("myNav").style.width = "0%";
	}
</script>
<script
	src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.5.1.min.js"></script>
<script src="<%=request.getContextPath()%>/vendors/popper/popper.min.js"></script>
<script
	src="<%=request.getContextPath()%>/vendors/bootstrap/js/bootstrap.min.js"></script>

</body>

</html>