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
	
	List<OrderdetailVO> ranlist = orderdetailSvc1.ran();
	pageContext.setAttribute("ranlist",ranlist);
%>


<!DOCTYPE html>
<html>
<jsp:useBean id="pSvc" scope="page"	class="com.product.model.ProductService" />
<head>
<meta charset="utf-8">
<title>YuXiKun</title>


<style>
marquee {
	width: 1000px;
}

.w-100{
    opacity: 0.4;
}
#carousel-inner {
    position: relative;
    width: 1800px;
    overflow: hidden;
    right: 350px;
    height:500px;
}



</style>


<div class="header">
	<jsp:include page="header.jsp"></jsp:include>
</div>
</head>

<body>

	<center>

		<h3>
			<marquee onMouseOver="this.stop()" onMouseOut="this.start()"
				id="announcement"></marquee>
		</h3>
	</center>

	<div class="content">


<div class="container my-4">

    <div id="carouselExample1" class="carousel slide z-depth-1-half" data-ride="carousel">
      <div class="carousel-inner" id="carousel-inner">
        <div class="carousel-item active">
          <img class="d-block w-100" src="<%=request.getContextPath()%>/images/ps4.png" alt="First slide" width=1300px height=500px >
        </div>
        <div class="carousel-item">
          <img class="d-block w-100" src="<%=request.getContextPath()%>/images/switch.png" alt="Second slide"  width=1300px height=500px>
        </div>
        <div class="carousel-item">
          <img class="d-block w-100" src="<%=request.getContextPath()%>/images/xbox.png" alt="Third slide" width=1300px height=500px>
        </div>
      </div>
      <a class="carousel-control-prev" href="#carouselExample1" role="button" data-slide="prev">
<!--         <span class="carousel-control-prev-icon" aria-hidden="true"></span> -->
        <span class="sr-only">Previous</span>
      </a>
      <a class="carousel-control-next" href="#carouselExample1" role="button" data-slide="next">
<!--         <span class="carousel-control-next-icon" aria-hidden="true"></span> -->
        <span class="sr-only">Next</span>
      </a>
</div> 
</div>

		<div class="article">
			<a
				href="<%=request.getContextPath()%>/ProductServlet?ptype=no&name=&action=findByName"><button
					type="button" class="btn btn-success" id="buybtn">我要買</button></a>
            <a href="<%=request.getContextPath()%>/member/controller/MemberServlet?action=goSellerIndex">
			<button type="button" class="btn btn-warning"
				id="sellbtn">我要賣</button></a>
				
		</div>


		<div class="swiper-container">
			<H2><b>熱銷商品</b></H2>
			<div class="swiper-wrapper">
				<c:forEach var="VO" items="${list}">
					<jsp:useBean id="ppService" scope="page"
						class="com.productPicture.model.ProductPictureService" />
					<div class="swiper-slide">
						<a href="<%= request.getContextPath() %>/ProductServlet?action=findthis&pid=${VO.p_id}">
							<img alt="沒...沒圖"
							src="<%= request.getContextPath() %>/ShowPicture?type=pp&id=${VO.pp_id}"
							style="width: 227.74px; height: 300px;"></a>

					</div>

				</c:forEach>
			</div>
			<div class="swiper-pagination"></div>
			<div class="swiper-button-next"></div>
			<div class="swiper-button-prev"></div>
		</div>

		<div class="swiper-container">
			<H2><b>今日推薦</b></H2>
			<div class="swiper-wrapper">
			<c:forEach var="rv" items="${ranlist}">
				<jsp:useBean id="ppService1" scope="page" class="com.productPicture.model.ProductPictureService" />
				<div class="swiper-slide">
<%-- 				<h2>${pSvc.oneProduct(rv.p_id).p_name}</h2> --%>
						<a href="<%= request.getContextPath() %>/ProductServlet?action=findthis&pid=${rv.p_id}">
							<img alt="沒...沒圖"
							src="<%= request.getContextPath() %>/ShowPicture?type=pp&id=${rv.pp_id}"
							style="width: 227.74px; height: 300px;"></a>
				
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

		announcement()

		function announcement() {

			$
					.ajax({
						url : context + "/light.do",
						type : "get",
						data : {
							"action" : "announcement",
						},
						dataType : "json",
						success : function(data) {
							$("#announcement").html("");

							console.log(data)

							let announcement = "";
							for (let i = 0; i < data.length; i++) {
								announcement += ((i+1) +"."+JSON.parse(data[i]).message +"   ");
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
  interval: 1500
});
	</script>


</body>

</html>