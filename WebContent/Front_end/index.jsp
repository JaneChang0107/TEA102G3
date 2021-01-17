<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@page import="redis.clients.jedis.Jedis"%>
<%@page import="java.util.List"%>
<%@page import="com.orderdetail.model.OrderdetailVO"%>
<%@page import="com.orderdetail.model.OrderdetailService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
// 	String m_id= session.getAttribute("loginId").toString();
	boolean loginornot=true;
	if(session.getAttribute("loginId")==null){
		loginornot=true;
	}else{
		loginornot=false;
	}
// 	try{
		
// 		String m_id= session.getAttribute("loginId").toString();
		
// 	 	loginornot=false;
// 		System.out.println("try:"+loginornot);
// 	}catch(Exception e) {
// 		loginornot=true;
// 		e.printStackTrace();
// 	}
		System.out.println("try1:"+loginornot);
    OrderdetailService orderdetailSvc1 = new OrderdetailService();
	List<OrderdetailVO> list = orderdetailSvc1.count();
	pageContext.setAttribute("list", list);
	
	List<OrderdetailVO> ranlist = orderdetailSvc1.ran();
	pageContext.setAttribute("ranlist",ranlist);
%>


<!DOCTYPE html>
<html>
<jsp:useBean id="pSvc" scope="page"
	class="com.product.model.ProductService" />
<head>
<meta charset="utf-8">
<title>YuXiKun</title>


<style>
.container my-4 {
	margin-bottom: -2.5rem !important;
}

.container {
	width: 100%;
	padding-right: 0px;
	padding-left: 0px;
	margin-right: auto;
	margin-left: auto;
}

marquee {
	width: 1000px;
	zindex
}

.w-100 {
	opacity: 1;
}

#carousel-inner {
	position: relative;
	width: 152%;
	overflow: hidden;
	right: 290px;
	height: 767px;
	/*     top: -72px; */
}
</style>

<div class="header">
	<jsp:include page="header.jsp"></jsp:include>
</div>


<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/slick.css" />
<!-- Add the new slick-theme.css if you want the default styling -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/slick-theme.css" />


</head>
<jsp:useBean id="ppService" scope="page"
	class="com.productPicture.model.ProductPictureService" />
<body>


<c:if test="<%=loginornot%>"> 
<center>
			<h1><marquee onMouseOver="this.stop()" onMouseOut="this.start()">歡迎光臨</marquee></h1>
</center>
</c:if>
	

<c:if test="<%=!(loginornot)%>"> 
	<center>
		<h1>
		
			<marquee onMouseOver="this.stop()" onMouseOut="this.start()"
				id="announcement"></marquee>
			<h1 />
	</center>
</c:if>
	<div class="content">


		<div class="container my-4" style="margin-bottom: -2.5rem !important";>

			<div id="carouselExample1" class="carousel slide z-depth-1-half"
				data-ride="carousel">
				<div class="carousel-inner" id="carousel-inner">
					<div class="carousel-item active">
						<img class="d-block w-100"
							src="<%=request.getContextPath()%>/images/index1.jpg"
							alt="First slide">
					</div>
					<div class="carousel-item">
						<img class="d-block w-100"
							src="<%=request.getContextPath()%>/images/index6.jfif"
							alt="Second slide">
					</div>
					<!--         <div class="carousel-item"> -->
					<%--           <img class="d-block w-100" src="<%=request.getContextPath()%>/images/index4.jfif" alt="Second slide" > --%>
					<!--         </div> -->
					<div class="carousel-item">
						<img class="d-block w-100"
							src="<%=request.getContextPath()%>/images/index3.png"
							alt="Third slide">
					</div>
				</div>
				<a class="carousel-control-prev" href="#carouselExample1"
					role="button" data-slide="prev"> <!--         <span class="carousel-control-prev-icon" aria-hidden="true"></span> -->
					<span class="sr-only">Previous</span>
				</a> <a class="carousel-control-next" href="#carouselExample1"
					role="button" data-slide="next"> <!--         <span class="carousel-control-next-icon" aria-hidden="true"></span> -->
					<span class="sr-only">Next</span>
				</a>
			</div>
		</div>


		<H2>
			<b style="margin-left: 208px; color: #bd2130";>熱銷商品</b>
		</H2>
		<div class="swiper-container">

			<div class="swiper-wrapper">
				<c:forEach var="VO" items="${list}">

					<div class="swiper-slide">
						<a
							href="<%= request.getContextPath() %>/ProductServlet?action=findthis&pid=${VO.p_id}">
							<img alt="沒...沒圖"
							src="<%= request.getContextPath() %>/ShowPicture?type=pp&id=${VO.pp_id}"
							style="width: 294.74px; height: 300px;">
						</a>

					</div>

				</c:forEach>
			</div>
			<div class="swiper-pagination"></div>
			<div class="swiper-button-next"></div>
			<div class="swiper-button-prev"></div>
		</div>



		<H2>
			<b style="margin-left: 208px; color: #bd2130";>今日推薦</b>
		</H2>
		<div class="swiper-container">

			<div class="swiper-wrapper">
				<c:forEach var="rv" items="${ranlist}">
					<jsp:useBean id="ppService1" scope="page"
						class="com.productPicture.model.ProductPictureService" />
					<div class="swiper-slide">
						<%-- 				<h2>${pSvc.oneProduct(rv.p_id).p_name}</h2> --%>
						<a
							href="<%= request.getContextPath() %>/ProductServlet?action=findthis&pid=${rv.p_id}">
							<img alt="沒...沒圖"
							src="<%= request.getContextPath() %>/ShowPicture?type=pp&id=${rv.pp_id}"
							style="width: 290px; height: 300px;">
						</a>
					</div>
				</c:forEach>

			</div>
			<div class="swiper-pagination"></div>
			<div class="swiper-button-next"></div>
			<div class="swiper-button-prev"></div>
		</div>
	</div>


	<div class="footer">
		<jsp:include page="footer.jsp"></jsp:include>
	</div>


	<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/vendors/jquery-1.11.0.min.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/vendors//jquery-migrate-1.2.1.min.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/js/slick.min.js"></script>


	<script>
		var swiper = new Swiper('.swiper-container', {
			slidesPerView : 4,
			spaceBetween : 30,
			slidesPerGroup : 4,
			loop : false,
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

		announcement()

		function announcement() {

			$.ajax({
				url : context + "/light.do",
				type : "get",
				data : {
					"action" : "announcement",
				},
				dataType : "json",
				success : function(data) {
					$("#announcement").html("");
					http: //localhost:8081/TEA102G3/images/index2.png

					console.log(data)

					let announcement = "";
					for (let i = 0; i < data.length; i++) {
						announcement += ((i + 1) + "."
								+ JSON.parse(data[i]).message + "   ");
					}
					$("#announcement").append(announcement);

				}

			});

		}
		// 		notice()

		// 		function notice() {
		// 			$.ajax({
		// 				url : context + "/light.do",
		// 				type : "get",
		// 				data : {
		// 					"action" : "announcement",
		// 				},
		// 				dataType : "json",
		// 				success : function(data) {
		// 					$("#notice").html("");

		// 					console.log(data)

		// 					let notice = "";
		// 					for (let i = 0; i < data.length; i++) {
		// 						notice += ("<li>"
		// 								+ JSON.parse(data[i]).message + "</li>");
		// 					}

		// 					$("#notice").append(notice);
		// 				}

		// 			});
		// 		}

		$('.carousel').carousel({
			interval : 1500
		});
	</script>


</body>

</html>