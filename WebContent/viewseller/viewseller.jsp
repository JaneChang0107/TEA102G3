<%@page import="com.sun.xml.internal.bind.ValidationEventLocatorEx"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.viewseller.model.*"%>

<%
	ViewsellerVO viewsellerVO = (ViewsellerVO) request.getAttribute("viewsellerVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>賣家評價頁面</title>

<link rel="stylesheet"
	href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<script
	src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

<style>

</style>

</head>


<body>
	<div class="header">
		<jsp:include page="/Front_end/header.jsp"></jsp:include>
	</div>
	
	
	<div class="class-card" style="height: 50px; margin-top: 50px;">
			<h2>評價</h2>
		</div>
		<div class="row">
			<div>
				<div class="col-sm-12" style="margin-top: 20px;">
					<div class="card-deck">
						<c:forEach var="class_infoVO" items="${list}" varStatus="status" begin="0" end="3">
							<div class="card">
								<a
									href="<%=request.getContextPath()%>/Class_info/Class_learnServlet?action=class_Introduction&class_id=${class_infoVO.class_id}">

									<img class="card-img-top"
									src="<%=request.getContextPath()%>/Class_info/Class_Introduction?action=class_pic_sm&class_id=${class_infoVO.class_id}"
									alt="Card image cap">
									<div class="card-body">
										<h6 class="card-title" style="height: 30px">${class_infoVO.class_name}</h6>
								</a>
								<p class="card-text" style="line-height: 1">
									<small class="text-muted"> 授課老師：<a
										href="<%=request.getContextPath()%>/Class_info/Class_Introduction?action=class_Introduction&class_id=${class_infoVO.class_id}">
											${class_infoVO.member_id}</a><br>
										課程評價：${class_infoVO.member_id}<br>
										課程時數：${class_infoVO.member_id}<br>
										購買人數：${class_infoVO.member_id}<br>
									</small>
								</p>
							</div>
					</div>
					</c:forEach>
				</div>
			</div>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
			<div class="progress">
				<div class="progress-bar progress-bar-success" role="progressbar"
					aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"
					style="width: 60%;">
					<span class="sr-only">好評</span>
				</div>
				<div class="progress-bar progress-bar-info" role="progressbar"
					aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"
					style="width: 40%;">
					<span class="sr-only">負評</span>
				</div>
			</div>
			
			
			<div class="tabs-container">
				<div class="tabs-pages">
					<div class="tab active">全部</div>
					<div class="tab">好評</div>
					<div class="tab">壞評</div>
				</div>
				<div class="tabs-contents">
					<div class="tab-c active">
						<div class="inner-content">
							全部 <br /> hello
						</div>

					</div>
					<div class="tab-c">
						<div class="inner-content">
							好評 <br /> hello
						</div>
					</div>
					<div class="tab-c">
						<div class="inner-content">
							壞評 <br /> hello
						</div>
					</div>
				</div>
			</div>




		</div>
		<div class="footer">
			<jsp:include page="/Front_end/footer_chat.jsp"></jsp:include>

			<script
				src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

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

				<script>
				$(".tab").each(function(index) {
					$(this).click(function(e) {
						triggletab();
						triigletabcontent();
						$(this).toggleClass("active");
						$(".tab-c").eq(index).toggleClass("active");
					});
				});
				//to remove all tab headers
				function triggletab() {
					$(".tab").each(function() {
						$(this).removeClass("active");
					});
				}

				//triggle the tab content
				function triigletabcontent() {
					$(".tab-c").each(function() {
						$(this).removeClass("active");
					});
				}
			</script>
</body>
</html>