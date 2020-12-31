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
	OrderdetailService orderdetailSvc1 = new OrderdetailService();
	List<OrderdetailVO> list = orderdetailSvc1.count();
	pageContext.setAttribute("list", list);
	String test=(String)request.getAttribute("rl");
	
	Jedis jedis = new Jedis("localhost", 6379);
	jedis.auth("123456");
// 	String rl = jedis.get("1");
// 	System.out.println(rl);
	 Set<String> rl1 = jedis.keys("*"); 
        Iterator<String> it=rl1.iterator() ;   
        while(it.hasNext()){   
            String key = it.next();
            String test1=jedis.get(key);
            request.setAttribute("aa", test1);
//             System.out.println(key);   
        }
     
	jedis.close();

%>


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
	

</style>


<div class="header">
	<jsp:include page="header.jsp"></jsp:include>
</head>

<body>
<h1>

<h3><marquee  onMouseOver="this.stop()" onMouseOut="this.start()">${rl==null ? aa : rl }</marquee>
</h1>
	<div class="content">

		<div class="article">
			<button type="button" class="btn btn-success btn-circle-xl"
				id="buybtn">我要買</button>
			<button type="button" class="btn btn-danger btn-circle-xl"
				id="rentbtn">我要租</button>
			<button type="button" class="btn btn-warning btn-circle-xl"
				id="sellbtn">我要賣</button>
		</div>




		<div class="swiper-container">
			<H1>熱銷商品</H1>
			<div class="swiper-wrapper">
				<c:forEach var="VO" items="${list}">
					<jsp:useBean id="ppService" scope="page"
						class="com.productPicture.model.ProductPictureService" />
					<div class="swiper-slide">
						<a href="<%= request.getContextPath() %>/ProductServlet?action=findthis&pid=${VO.p_id}"><img  alt="沒...沒圖"
							src="<%= request.getContextPath() %>/ShowPicture?type=pp&id=${VO.pp_id}" style="width:227.74px;height:300px;"></a>
						
					</div>

				</c:forEach>
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
	</div>


	<div class="footer">
		<jsp:include page="footer.jsp"></jsp:include>
	</div>


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
	</script>

</body>

</html>